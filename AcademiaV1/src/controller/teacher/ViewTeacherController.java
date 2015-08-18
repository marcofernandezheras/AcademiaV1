package controller.teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import controller.managers.TeacherManager;
import model.Teacher;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.RetrieveException;
import view.teacher.ViewTeacherPanel;

@SuppressWarnings("serial")
public class ViewTeacherController extends ViewTeacherPanel {

	protected TeacherManager teacherManager;
	protected Teacher currentTeacher;

	public ViewTeacherController(TeacherManager teacherManager) {
		super();
		this.teacherManager = teacherManager;
		bindEvents();
	}

	private void bindEvents() {
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doSearch();
			}
		});
	}

	protected void doSearch() {
		if(!txtSearch.getText().isEmpty())
		{
			try {
				currentTeacher = teacherManager.getTeacher(txtSearch.getText());
				populateGuiData();
			} catch (RetrieveException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (NotFoundException e) {				
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Profesor no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected void populateGuiData() {
		clearUI();
		if(currentTeacher != null)
		{
			txtSearch.setText("");
			
			txtDni.setText(currentTeacher.getDni());
			txtName.setText(currentTeacher.getName());
			txtSurnames.setText(currentTeacher.getSurnames());
			
			Date bornDate = currentTeacher.getBornDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(bornDate);
			txtBornDate.getModel().setDate(
					calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
			txtBornDate.getModel().setSelected(true);
			
			spinSalary.setValue(currentTeacher.getSalary());
		}	
		else
			JOptionPane.showMessageDialog(this, "Profesor no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
	}
	
	
}
