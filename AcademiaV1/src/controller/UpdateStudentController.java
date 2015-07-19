package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import model.StudentManager;
import view.UpdateStudentPanel;

@SuppressWarnings("serial")
public class UpdateStudentController extends UpdateStudentPanel {

	public UpdateStudentController(StudentManager studentManager) {
		super(studentManager);
		btnSave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveStudentChanges();
			}
		});
	}

	@Override
	protected void searchStudent() {
		super.searchStudent();
		toogleReadOnlyGUI(currentStudent != null);	
	}				
	
	private void toogleReadOnlyGUI(boolean writable) {
		txtDni.setEditable(writable);
		txtName.setEditable(writable);
		txtSurnames.setEditable(writable);
		txtBornDate.setEnabled(writable);
		txtBornDate.getComponent(1).setEnabled(writable);
		btnSave.setEnabled(writable);		
	}

	private void saveStudentChanges() {
		if(insertNewValuesOnStudent()){
			studentManager.updateStudent(currentStudent);
			clearGui();				
		}
	}
	
	private boolean insertNewValuesOnStudent() {
		String name = txtName.getText();
		String surnames = txtSurnames.getText();
		String dni = txtDni.getText();				
		
		boolean valid = !name.isEmpty();
		valid &= !surnames.isEmpty();
		valid &= !dni.isEmpty();
		if(valid)
		{
			Object value = txtBornDate.getModel().getValue();				
			if(value == null || !(value instanceof Date))
			{
				JOptionPane.showMessageDialog(this, "Debe introducir una fecha de nacimiento", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}		
			Date bornDate = (Date)value;
			currentStudent.setDni(dni);
			currentStudent.setName(name);
			currentStudent.setSurnames(surnames);
			currentStudent.setBornDate(bornDate);
			return true;
		}
		JOptionPane.showMessageDialog(this, "Todos los datos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	@Override
	protected void clearGui() {
		super.clearGui();
		toogleReadOnlyGUI(false);
		currentStudent = null;
	}
}
