package controller.teacher;

import java.util.Date;

import javax.swing.JOptionPane;

import controller.managers.TeacherManager;
import model.Teacher;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;
import view.teacher.CreateTeacherPanel;

@SuppressWarnings("serial")
public class CreateTeacherController extends CreateTeacherPanel {
	
	private final TeacherManager teacherManager;
	public CreateTeacherController(TeacherManager teacherManager) {
		this.teacherManager = teacherManager;
		bindEvents();
	}
	
	private void bindEvents() {	
		btnAction.addActionListener(e -> createTeacher());
	}

	protected void createTeacher() {
		if(dataValid())
		{
			String name = txtName.getText();
			String surnames = txtSurnames.getText();
			String dni = txtDni.getText();
			Date bornDate = (Date)txtBornDate.getModel().getValue();
			Double salary = (Double)spinSalary.getValue();
			
			try {
				Teacher teacher = teacherManager.createTeacher(dni, name, surnames);
				teacher.setBornDate(bornDate);
				teacher.setSalary(salary);	
				teacherManager.updateTeacher(teacher);
				clearUI();
			} catch (CreateException | NotFoundException | UpdateException e) {				
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Todos los datos son obligatorio", "Error", JOptionPane.WARNING_MESSAGE);;
		}
	}
}
