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
		btnAction.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getDataAndCreateNewStudent();					
			}
		});		
	}

	private void getDataAndCreateNewStudent() {
		
		if(dataValid())
		{
			String name = txtName.getText();
			String surnames = txtSurnames.getText();
			String dni = txtDni.getText();
			Date bornDate = (Date)txtBornDate.getModel().getValue();
			
			Student student = studentManager.createStudent(dni, name, surnames);
			student.setBornDate(bornDate);
			student.setComments(txtComments.getText());
			studentManager.updateStudent(student);
			JOptionPane.showMessageDialog(this, "Nuevo alumno creado con éxito");
			
			clearUI();
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
