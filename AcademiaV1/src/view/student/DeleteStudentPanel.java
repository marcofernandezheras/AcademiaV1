package view.student;

import model.StudentManager;
import controller.student.ViewStudentController;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public abstract class DeleteStudentPanel extends ViewStudentController {
	protected JButton btnDelete;

	public DeleteStudentPanel(StudentManager studentManager) {
		super(studentManager);
		
		btnDelete = new JButton("Borrar");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 9;
		add(btnDelete, gbc_btnDelete);
	}

}
