package controller.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import view.student.CreateStudentPanel;
import model.Student;
import model.StudentManager;

@SuppressWarnings("serial")
public class CreateStudentController extends CreateStudentPanel {
	
	private final StudentManager studentManager;
	
	public CreateStudentController(StudentManager studentManager) {
		this.studentManager = studentManager;
		bindEvents();
	}

	private void bindEvents() {
		btnCreate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getDataAndCreateNewStudent();					
			}
		});		
	}

	private void getDataAndCreateNewStudent() {
		String name = txtName.getText();
		String surnames = txtSurnames.getText();
		String dni = txtDni.getText();
		
		Object value = txtBornDate.getModel().getValue();
		if(value == null || !(value instanceof Date))
		{
			JOptionPane.showMessageDialog(this, "Debe introducir una fecha de nacimiento", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}		
		Date bornDate = (Date)value;
		
		if(validateData(name, surnames, dni))
		{
			createNewStudent(name, surnames, dni, bornDate);
			clearGUI();
		}
		else
			JOptionPane.showMessageDialog(this, "Todos los datos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
	}

	private boolean validateData(String name, String surnames, String dni) {
		boolean valid = !name.isEmpty();
		valid &= !surnames.isEmpty();
		valid &= !dni.isEmpty();
		return valid;
	}

	private void createNewStudent(String name, String surnames, String dni,
			Date bornDate) {
		Student student = studentManager.createStudent(dni, name, surnames);
		student.setBornDate(bornDate);
		student.setComments(txtComments.getText());
		studentManager.updateStudent(student);
		JOptionPane.showMessageDialog(this, "Nuevo alumno creado con éxito");
	}
	
	private void clearGUI() {
		txtName.setText("");
		txtSurnames.setText("");
		txtDni.setText("");
		txtComments.setText("");
		txtBornDate.getModel().setSelected(false);
	}
}
