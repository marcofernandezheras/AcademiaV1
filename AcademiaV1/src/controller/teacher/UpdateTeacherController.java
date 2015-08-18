package controller.teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import model.Teacher;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;
import controller.managers.TeacherManager;

@SuppressWarnings("serial")
public class UpdateTeacherController extends ViewTeacherController {

	public UpdateTeacherController(TeacherManager teacherManager) {
		super(teacherManager);
		btnAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTeacher();				
			}
		});
		
		btnAction.setEnabled(false);
		toogleReadOnlyGUI(false);
	}

	protected void updateTeacher() {
		if(dataValid() && currentTeacher != null)
		{
			String name = txtName.getText();
			String surnames = txtSurnames.getText();
			String dni = txtDni.getText();
			Date bornDate = (Date)txtBornDate.getModel().getValue();
			Double salary = (Double)spinSalary.getValue();
			
			currentTeacher.setDni(dni);
			currentTeacher.setName(name);
			currentTeacher.setSurnames(surnames);
			currentTeacher.setBornDate(bornDate);
			currentTeacher.setSalary(salary);
			
			try {
				teacherManager.updateTeacher(currentTeacher);			
				clearUI();
				currentTeacher = null;
				toogleReadOnlyGUI(false);
				DefaultListModel<Teacher> model = (DefaultListModel<Teacher>) listTeacher.getModel();
				model.removeAllElements();
				JOptionPane.showMessageDialog(this, "Guardado");
			} catch (NotFoundException | UpdateException e) {				
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} 
		}
	}

	@Override
	protected String getButtonLabel() {		
		return "Modificar";
	}
	
	@Override
	protected void showTeacher() {	
		super.showTeacher();
		toogleReadOnlyGUI(currentTeacher != null);
	}

	private void toogleReadOnlyGUI(boolean writable) {
		txtDni.setEditable(writable);
		txtName.setEditable(writable);
		txtSurnames.setEditable(writable);
		txtBornDate.setEnabled(writable);
		txtBornDate.getComponent(1).setEnabled(writable);
		spinSalary.setEnabled(writable);
		btnAction.setEnabled(writable);	
	}
}
