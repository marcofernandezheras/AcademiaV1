package controller.teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import controller.managers.TeacherManager;

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
				try {
					teacherManager.deleteTeacher(currentTeacher);
					clearUI();
					currentTeacher = null;
					btnAction.setEnabled(false);
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
		btnAction.setEnabled(currentTeacher != null);
	}
	
	@Override
	protected String getButtonLabel() {
		return "Borrar";
	}
	
	
}
