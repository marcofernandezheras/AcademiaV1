package controller.student;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import controller.managers.StudentManager;
import model.Student;
import view.student.ViewStudentPanel;

@SuppressWarnings("serial")
public class ViewStudentController extends ViewStudentPanel {

	protected Student currentStudent;
	protected final StudentManager studentManager;
	protected final List<Predicate<Student> > predicates;
	
	public ViewStudentController(StudentManager studentManager) {
		super();
		this.studentManager = studentManager;
		
		predicates = new ArrayList<Predicate<Student>>();
		predicates.add(s -> s.getDni().equalsIgnoreCase(txtSearch.getText()));
		predicates.add(s -> s.getName().toLowerCase().startsWith(txtSearch.getText().toLowerCase()));
		predicates.add(s -> s.getSurnames().toLowerCase().startsWith(txtSearch.getText().toLowerCase()));
		
		bindEvents();
	}

	private void bindEvents() {
		btnSearch.addActionListener(e -> doSearch());		
		listStudent.addListSelectionListener(e -> showStudent());
	}

	protected void doSearch() {
		if(!txtSearch.getText().isEmpty())
		{			
			int predicateUsed = cboSearchType.getSelectedIndex();

			List<Student> students = studentManager.getStudents(predicates.get(predicateUsed));
			
			if(students.isEmpty())
				JOptionPane.showMessageDialog(this, "No existen resultados");
			
			DefaultListModel<Student> model = (DefaultListModel<Student>) listStudent.getModel();
			model.removeAllElements();
			for (Student student : students) {
				model.addElement(student);
			}								
		}
	}


	protected void populateGuiData() {
		clearUI();
		if(currentStudent != null)
		{
			txtSearch.setText("");
			
			txtDni.setText(currentStudent.getDni());
			txtName.setText(currentStudent.getName());
			txtSurnames.setText(currentStudent.getSurnames());
			
			Date bornDate = currentStudent.getBornDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(bornDate);
			txtBornDate.getModel().setDate(
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			txtBornDate.getModel().setSelected(true);
			
			txtComments.setText(currentStudent.getComments());
		}			
	}
	
	@Override
	protected void clearUI() {
		super.clearUI();
		txtComments.setText("");
	}

	protected void showStudent() {
		currentStudent = listStudent.getSelectedValue();
		populateGuiData();
	}
}
