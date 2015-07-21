package controller.teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import model.TeacherManager;

@SuppressWarnings("serial")
public class UpdateTeacherController extends ViewTeacherController {

	public UpdateTeacherController(TeacherManager teacherManager) {
		super(teacherManager);
		btnAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doAction();				
			}
		});
		
		btnAction.setEnabled(false);
	}

	protected void doAction() {
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
			
			teacherManager.updateTeacher(currentTeacher);
			
			clearUI();
			currentTeacher = null;
			toogleReadOnlyGUI(false);
		}
	}

	@Override
	protected String getButtonLabel() {		
		return "Modificar";
	}
	
	@Override
	protected void doSearch() {
		super.doSearch();
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
