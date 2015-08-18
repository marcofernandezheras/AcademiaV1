package controller.student;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import model.Student;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import controller.managers.StudentManager;

@SuppressWarnings("serial")
public class DeleteStudentController extends ViewStudentController {

	public DeleteStudentController(StudentManager studentManager) {
		super(studentManager);
		btnAction.addActionListener(e -> deleteStudent());
		btnAction.setEnabled(false);
	}

	protected void deleteStudent() {
		if(currentStudent != null)
		{
			int confirmed = JOptionPane.showConfirmDialog(this, "¿Realente desea borrar este alumno?");
			if(confirmed == JOptionPane.OK_OPTION)
			{
				try {
					studentManager.deleteStudent(currentStudent);
					currentStudent = null;
					clearUI();
					btnAction.setEnabled(false);
					DefaultListModel<Student> model = (DefaultListModel<Student>) listStudent.getModel();
					model.removeAllElements();
					JOptionPane.showMessageDialog(this, "Alumno borrado");
				} catch (NotFoundException | DeleteException e) {					
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} 
			}
		}
	}
	
	@Override
	protected void showStudent() {	
		super.showStudent();
		btnAction.setEnabled(currentStudent != null);
	}
	
	@Override
	protected String getButtonLabel() {
		return "Borrar";
	}
}
