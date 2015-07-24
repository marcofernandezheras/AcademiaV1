package view.teacher;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

import view.PersonInfoPanel;

import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public abstract class TeacherInfoPanel extends PersonInfoPanel {

	protected JSpinner spinSalary;
	protected JButton btnAction;
	/**
	 * Create the panel.
	 */
	public TeacherInfoPanel() {
		super();				
		GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		
		JLabel lblNewLabel = new JLabel("Sueldo");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);
		
		spinSalary = new JSpinner();
		spinSalary.setModel(new SpinnerNumberModel(new Double(100), new Double(100), null, new Double(1)));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 5;
		add(spinSalary, gbc_spinner);
		
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
