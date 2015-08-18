package controller.teacher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import controller.managers.TeacherManager;
import model.Teacher;
import view.teacher.ViewTeacherPanel;

@SuppressWarnings("serial")
public class ViewTeacherController extends ViewTeacherPanel {

	protected TeacherManager teacherManager;
	protected Teacher currentTeacher;
	protected final List<Predicate<Teacher> > predicates;

	public ViewTeacherController(TeacherManager teacherManager) {
		super();
		this.teacherManager = teacherManager;
		predicates = new ArrayList<Predicate<Teacher>>();
		predicates.add(t -> t.getDni().equalsIgnoreCase(txtSearch.getText()));
		predicates.add(t -> t.getName().toLowerCase().startsWith(txtSearch.getText().toLowerCase()));
		predicates.add(t -> t.getSurnames().toLowerCase().startsWith(txtSearch.getText().toLowerCase()));
		
		bindEvents();
	}

	private void bindEvents() {
		btnSearch.addActionListener(e -> doSearch());		
		listTeacher.addListSelectionListener(e -> showTeacher());
	}

	protected void showTeacher() {
		currentTeacher = listTeacher.getSelectedValue();
		populateGuiData();
	}

	protected void doSearch() {
		if(!txtSearch.getText().isEmpty())
		{
			int predicateUsed = cboSearchType.getSelectedIndex();

			List<Teacher> teachers = teacherManager.getTeachers(predicates.get(predicateUsed));
			
			if(teachers.isEmpty())
				JOptionPane.showMessageDialog(this, "No existen resultados");
			
			DefaultListModel<Teacher> model = (DefaultListModel<Teacher>) listTeacher.getModel();
			model.removeAllElements();
			for (Teacher teacher : teachers) {
				model.addElement(teacher);
			}	
		}
	}

	protected void populateGuiData() {
		clearUI();
		if(currentTeacher != null)
		{
			txtSearch.setText("");
			
			txtDni.setText(currentTeacher.getDni());
			txtName.setText(currentTeacher.getName());
			txtSurnames.setText(currentTeacher.getSurnames());
			
			Date bornDate = currentTeacher.getBornDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(bornDate);
			txtBornDate.getModel().setDate(
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			txtBornDate.getModel().setSelected(true);
			
			spinSalary.setValue(currentTeacher.getSalary().doubleValue());
		}	
	}
	
	
}
