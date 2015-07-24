package controller.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
				studentManager.deleteStudent(currentStudent);
				currentStudent = null;
				clearUI();
				btnAction.setEnabled(false);
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
