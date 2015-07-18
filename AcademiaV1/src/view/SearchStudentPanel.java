package view;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SearchStudentPanel extends JPanel {
	protected JTextField txtSearchDni;
	protected JButton btnSearch;

	/**
	 * Create the panel.
	 */
	public SearchStudentPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 20, 0, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblBuscarAlumno = new JLabel("Buscar Alumno");
		GridBagConstraints gbc_lblBuscarAlumno = new GridBagConstraints();
		gbc_lblBuscarAlumno.gridwidth = 3;
		gbc_lblBuscarAlumno.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscarAlumno.gridx = 1;
		gbc_lblBuscarAlumno.gridy = 1;
		add(lblBuscarAlumno, gbc_lblBuscarAlumno);
		
		JLabel lblDni = new JLabel("Dni");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.anchor = GridBagConstraints.EAST;
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.gridx = 1;
		gbc_lblDni.gridy = 2;
		add(lblDni, gbc_lblDni);
		
		txtSearchDni = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		add(txtSearchDni, gbc_textField);
		txtSearchDni.setColumns(10);
		
		btnSearch = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 2;
		add(btnSearch, gbc_btnBuscar);

	}

}
