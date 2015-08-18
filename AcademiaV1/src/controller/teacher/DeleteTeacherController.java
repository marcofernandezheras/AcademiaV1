package controller.teacher;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import model.Teacher;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import controller.managers.TeacherManager;

@SuppressWarnings("serial")
public class DeleteTeacherController extends ViewTeacherController {

	public DeleteTeacherController(TeacherManager teacherManager) {
		super(teacherManager);
		btnAction.addActionListener(e -> deleteTeacher());
		btnAction.setEnabled(false);
		setEnabledDateWidget(false);
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
					DefaultListModel<Teacher> model = (DefaultListModel<Teacher>) listTeacher.getModel();
					model.removeAllElements();
					JOptionPane.showMessageDialog(this, "Borrado");
				} catch (NotFoundException | DeleteException e) {					
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	@Override
	protected void showTeacher() {	
		super.showTeacher();
		btnAction.setEnabled(currentTeacher != null);
	}
	
	@Override
	protected String getButtonLabel() {
		return "Borrar";
	}
	
	
}
