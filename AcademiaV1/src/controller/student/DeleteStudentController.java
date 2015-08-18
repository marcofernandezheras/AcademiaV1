package controller.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import controller.managers.StudentManager;

@SuppressWarnings("serial")
public class DeleteStudentController extends ViewStudentController {

	public DeleteStudentController(StudentManager studentManager) {
		super(studentManager);
		btnAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteStudent();
			}
		});
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
					JOptionPane.showMessageDialog(this, "Alumno borrado");
				} catch (NotFoundException | DeleteException e) {					
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} 
			}
		}
	}

	@Override
	protected void doSearch() {	
		super.doSearch();
		btnAction.setEnabled(currentStudent != null);
	}
	
	@Override
	protected String getButtonLabel() {
		return "Borrar";
	}
}
