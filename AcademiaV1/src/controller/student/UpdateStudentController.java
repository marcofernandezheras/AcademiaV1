package controller.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import controller.managers.StudentManager;

@SuppressWarnings("serial")
public class UpdateStudentController extends ViewStudentController {

	public UpdateStudentController(StudentManager studentManager) {
		super(studentManager);
btnAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateStudent();				
			}
		});
		
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
			
			studentManager.updateStudent(currentStudent);
			
			clearUI();
			currentStudent = null;
		}
	}
	
	private void toogleReadOnlyGUI(boolean writable) {
		txtDni.setEditable(writable);
		txtName.setEditable(writable);
		txtSurnames.setEditable(writable);
		txtBornDate.setEnabled(writable);
		txtBornDate.getComponent(1).setEnabled(writable);
		txtComments.setEditable(writable);
		btnAction.setEnabled(writable);	
	}	

	@Override
	protected void clearUI() {
		super.clearUI();
		toogleReadOnlyGUI(false);
		currentStudent = null;
	}
	
	@Override
	protected String getButtonLabel() {		
		return "Modificar";
	}
}
