package view.student;

import controller.student.ViewStudentController;
import model.StudentManager;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public abstract class UpdateStudentPanel extends ViewStudentController {
	protected JButton btnSave;

	public UpdateStudentPanel(StudentManager studentManager) {
		super(studentManager);
		GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		btnSave = new JButton("Guardar");
		btnSave.setEnabled(false);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 9;
		add(btnSave, gbc_btnSave);
	}
}
