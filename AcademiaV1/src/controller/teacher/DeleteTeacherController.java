package controller.teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.TeacherManager;

@SuppressWarnings("serial")
public class DeleteTeacherController extends ViewTeacherController {

	public DeleteTeacherController(TeacherManager teacherManager) {
		super(teacherManager);
		btnAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteTeacher();
			}
		});
		btnAction.setEnabled(false);
	}

	protected void deleteTeacher() {
		if(currentTeacher != null)
		{
			int confirmed = JOptionPane.showConfirmDialog(this, "¿Realente desea borrar este alumno?");
			if(confirmed == JOptionPane.OK_OPTION)
			{
				teacherManager.deleteTeacher(currentTeacher);
				clearUI();
				btnAction.setEnabled(false);
			}
		}
	}

	@Override
	protected void doSearch() {	
		super.doSearch();
		btnAction.setEnabled(currentTeacher != null);
	}
	
	@Override
	protected String getButtonLabel() {
		return "Borrar";
	}
	
	
}
