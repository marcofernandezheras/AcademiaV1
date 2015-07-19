package view;

import controller.ViewStudentController;
import model.StudentManager;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public abstract class UpdateStudentPanel extends ViewStudentController {
	protected JButton btnSave;

	public UpdateStudentPanel(StudentManager studentManager) {
		super(studentManager);
		
		btnSave = new JButton("Guardar");
		btnSave.setEnabled(false);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 8;
		add(btnSave, gbc_btnSave);
	}
}
