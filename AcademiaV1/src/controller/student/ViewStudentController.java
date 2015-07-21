package controller.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import controller.managers.StudentManager;
import model.Student;
import view.student.ViewStudentPanel;

@SuppressWarnings("serial")
public class ViewStudentController extends ViewStudentPanel {

	protected Student currentStudent;
	protected final StudentManager studentManager;
	
	public ViewStudentController(StudentManager studentManager) {
		super();
		this.studentManager = studentManager;
		bindEvents();
	}

	private void bindEvents() {
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doSearch();
			}
		});
	}

	protected void doSearch() {
		if(!txtSearch.getText().isEmpty())
		{
			currentStudent = studentManager.getStudent(txtSearch.getText());
			populateGuiData();
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
		else
			JOptionPane.showMessageDialog(this, "Profesor no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
	}
	
	@Override
	protected void clearUI() {
		super.clearUI();
		txtComments.setText("");
	}
}
