package view.student;

import view.PersonInfoPanel;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextArea;

import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public abstract class StudentInfoPanel extends PersonInfoPanel {
	
	protected JTextArea txtComments;
	protected JButton btnAction;

	/**
	 * Create the panel.
	 */
	public StudentInfoPanel() {
		super();
		GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		
		JLabel lblComnetarios = new JLabel("Comentarios");
		GridBagConstraints gbc_lblComnetarios = new GridBagConstraints();
		gbc_lblComnetarios.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblComnetarios.insets = new Insets(0, 0, 0, 5);
		gbc_lblComnetarios.gridx = 1;
		gbc_lblComnetarios.gridy = 5;
		add(lblComnetarios, gbc_lblComnetarios);
		
		txtComments = new JTextArea();
		GridBagConstraints gbc_txtComments = new GridBagConstraints();
		gbc_txtComments.insets = new Insets(0, 0, 0, 5);
		gbc_txtComments.fill = GridBagConstraints.BOTH;
		gbc_txtComments.gridx = 2;
		gbc_txtComments.gridy = 5;
		add(txtComments, gbc_txtComments);
		
		if(getButtonLabel() != null)
		{
			btnAction = new JButton(getButtonLabel());
			GridBagConstraints gbc_btnAction = new GridBagConstraints();
			gbc_btnAction.anchor = GridBagConstraints.EAST;
			gbc_btnAction.insets = new Insets(0, 0, 0, 5);
			gbc_btnAction.gridx = 2;
			gbc_btnAction.gridy = 6;
			add(btnAction, gbc_btnAction);
		}
	}

	protected abstract String getButtonLabel();
}
