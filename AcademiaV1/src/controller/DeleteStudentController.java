package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.StudentManager;
import view.DeleteStudentPanel;

@SuppressWarnings("serial")
public class DeleteStudentController extends DeleteStudentPanel {

	public DeleteStudentController(StudentManager studentManager) {
		super(studentManager);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteStudent();
			}
		});
	}

	protected void deleteStudent() {
		if(currentStudent != null)
		{
			int confirmed = JOptionPane.showConfirmDialog(this, "¿Realente desea borrar este alumno?");
			if(confirmed == JOptionPane.OK_OPTION)
			{
				studentManager.deleteStudent(currentStudent);
				currentStudent = null;
				clearGui();
			}
		}
	}

}
