package view.group;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import model.Student;

@SuppressWarnings("serial")
public abstract class CreateGroupPanel extends GroupInfoPanel {
	protected JComboBox<Student> cboStudent;
	protected JButton btnAddStudent;
	protected JButton btnremoveStudent;
	/**
	 * Create the panel.
	 */
	public CreateGroupPanel() {
		super();
		GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 20, 0};
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblAlumno = new JLabel("Alumno");
		GridBagConstraints gbc_lblAlumno = new GridBagConstraints();
		gbc_lblAlumno.insets = new Insets(0, 0, 0, 5);
		gbc_lblAlumno.anchor = GridBagConstraints.EAST;
		gbc_lblAlumno.gridx = 0;
		gbc_lblAlumno.gridy = 0;
		panel.add(lblAlumno, gbc_lblAlumno);
		
		cboStudent = new JComboBox<Student>();
		GridBagConstraints gbc_cboStudent = new GridBagConstraints();
		gbc_cboStudent.insets = new Insets(0, 0, 0, 5);
		gbc_cboStudent.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboStudent.gridx = 1;
		gbc_cboStudent.gridy = 0;
		panel.add(cboStudent, gbc_cboStudent);
		
		btnAddStudent = new JButton("+");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 0;
		panel.add(btnAddStudent, gbc_button);
		
		btnremoveStudent = new JButton("-");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.gridx = 3;
		gbc_button_1.gridy = 0;
		panel.add(btnremoveStudent, gbc_button_1);		
	}

	@Override
	protected
	String getButtonLabel() {		
		return "Guardar";
	}

}
