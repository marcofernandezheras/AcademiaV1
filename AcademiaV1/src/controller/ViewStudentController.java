package controller;

import java.util.Calendar;
import java.util.Date;

import model.StudentManager;
import view.ViewStudentPanel;

@SuppressWarnings("serial")
public class ViewStudentController extends ViewStudentPanel {

	public ViewStudentController(StudentManager studentManager) {
		super(studentManager);
	}

	@Override
	protected void searchStudent() {
		super.searchStudent();
		if(currentStudent != null)
			showStudentData();
		else
			clearStudentData();
	}


	private void showStudentData() {
		txtDni.setText(currentStudent.getDni());
		txtName.setText(currentStudent.getName());
		txtSurnames.setText(currentStudent.getSurnames());
		Date bornDate = currentStudent.getBornDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(bornDate);
		txtBornDate.getModel().setDate(
				calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		txtBornDate.getModel().setSelected(true);
	}
	
	protected void clearStudentData() {
		txtDni.setText("");
		txtName.setText("");
		txtSurnames.setText("");
		txtBornDate.getModel().setSelected(false);
	}
}
