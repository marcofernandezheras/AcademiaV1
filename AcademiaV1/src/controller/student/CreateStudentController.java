package controller.student;

import java.util.Date;

import javax.swing.JOptionPane;

import controller.managers.StudentManager;
import view.student.CreateStudentPanel;
import model.Student;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;

@SuppressWarnings("serial")
public class CreateStudentController extends CreateStudentPanel {
	
	private final StudentManager studentManager;
	
	public CreateStudentController(StudentManager studentManager) {
		this.studentManager = studentManager;
		bindEvents();
	}

	private void bindEvents() {
		btnAction.addActionListener(e -> getDataAndCreateNewStudent());		
	}

	private void getDataAndCreateNewStudent() {
		
		if(dataValid())
		{
			String name = txtName.getText();
			String surnames = txtSurnames.getText();
			String dni = txtDni.getText();
			Date bornDate = (Date)txtBornDate.getModel().getValue();
			
			try {
				Student student = studentManager.createStudent(dni, name, surnames);
				student.setBornDate(bornDate);
				student.setComments(txtComments.getText());
				studentManager.updateStudent(student);
				JOptionPane.showMessageDialog(this, "Nuevo alumno creado con éxito");			
				clearUI();
			} catch (CreateException | UpdateException | NotFoundException e) {				
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} 
		}		
		else
			JOptionPane.showMessageDialog(this, "Todos los datos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	protected void clearUI() {
		super.clearUI();
		txtComments.setText("");
	}
}
