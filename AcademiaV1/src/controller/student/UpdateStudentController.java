package controller.student;

import java.util.Date;

import javax.swing.JOptionPane;

import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;
import controller.managers.StudentManager;

@SuppressWarnings("serial")
public class UpdateStudentController extends ViewStudentController {

	public UpdateStudentController(StudentManager studentManager) {
		super(studentManager);
		toogleReadOnlyGUI(false);
		btnAction.addActionListener(e -> updateStudent());		
		btnAction.setEnabled(false);
	}

	protected void updateStudent() {
		if(dataValid() && currentStudent != null)
		{
			String name = txtName.getText();
			String surnames = txtSurnames.getText();
			String dni = txtDni.getText();
			Date bornDate = (Date)txtBornDate.getModel().getValue();
			String comments = txtComments.getText();
			
			currentStudent.setDni(dni);
			currentStudent.setName(name);
			currentStudent.setSurnames(surnames);
			currentStudent.setBornDate(bornDate);
			currentStudent.setComments(comments);
			
			try {
				studentManager.updateStudent(currentStudent);			
				clearUI();
				currentStudent = null;
				toogleReadOnlyGUI(false);
			} catch (NotFoundException | UpdateException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} 
		}
	}
	
	private void toogleReadOnlyGUI(boolean writable) {
		txtDni.setEditable(writable);
		txtName.setEditable(writable);
		txtSurnames.setEditable(writable);
		txtBornDate.setEnabled(writable);
		txtBornDate.getComponent(1).setEnabled(writable);
		txtComments.setEditable(writable);
		txtComments.setEnabled(writable);
		btnAction.setEnabled(writable);	
	}	

	@Override
	protected void clearUI() {
		super.clearUI();
		toogleReadOnlyGUI(true);
	}
	
	@Override
	protected String getButtonLabel() {		
		return "Modificar";
	}
}
