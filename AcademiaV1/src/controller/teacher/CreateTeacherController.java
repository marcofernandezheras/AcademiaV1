package controller.teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import model.Teacher;
import model.TeacherManager;
import view.teacher.CreateTeacherPanel;

@SuppressWarnings("serial")
public class CreateTeacherController extends CreateTeacherPanel {
	
	private final TeacherManager teacherManager;
	public CreateTeacherController(TeacherManager teacherManager) {
		this.teacherManager = teacherManager;
		bindEvents();
	}
	
	private void bindEvents() {	
		btnAction.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				createTeacher();
			}
		});
	}

	protected void createTeacher() {
		if(dataValid())
		{
			String name = txtName.getText();
			String surnames = txtSurnames.getText();
			String dni = txtDni.getText();
			Date bornDate = (Date)txtBornDate.getModel().getValue();
			Double salary = (Double)spinSalary.getValue();
			
			Teacher teacher = teacherManager.createTeacher(dni, name, surnames);
			teacher.setBornDate(bornDate);
			teacher.setSalary(salary);	
			teacherManager.updateTeacher(teacher);
			clearUI();
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Todos los datos son obligatorio", "Error", JOptionPane.WARNING_MESSAGE);;
		}
	}
}
