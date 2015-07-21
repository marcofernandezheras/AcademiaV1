package controller.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Student;
import model.StudentManager;
import view.student.SearchStudentPanel;

@SuppressWarnings("serial")
public class SearchStudentController extends SearchStudentPanel {
	protected Student currentStudent;
	protected final StudentManager studentManager;
	
	public SearchStudentController(StudentManager studentManager) {
		this.studentManager = studentManager;
		bindEvents();
	}

	protected void bindEvents() {
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchStudent();
			}
		});
	}

	protected void searchStudent() {
		String dni = txtSearchDni.getText();
		if(!dni.isEmpty())
		{
			currentStudent = studentManager.getStudent(dni);
			if(currentStudent == null)
				JOptionPane.showMessageDialog(this, "Alumno no encontrado","Error",JOptionPane.WARNING_MESSAGE);
			else
				txtSearchDni.setText("");
		}
		else
			JOptionPane.showMessageDialog(this, "Debe introducir un dni para buscar","Error",JOptionPane.WARNING_MESSAGE);
	}
}
