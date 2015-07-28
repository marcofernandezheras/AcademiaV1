package view.group;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import model.Group;
import model.Teacher;

@SuppressWarnings("serial")
public abstract class GroupInfoPanel extends JPanel {
	
	protected JComboBox<Teacher> cboTeacher;
	protected JComboBox<Group> cboGroup;
	protected JTable studentsTable;
	protected JButton btnAction;
	protected GridBagLayout gridBagLayout;

	public GroupInfoPanel() {
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblProfesor = new JLabel("Profesor");
		GridBagConstraints gbc_lblProfesor = new GridBagConstraints();
		gbc_lblProfesor.insets = new Insets(0, 0, 5, 5);
		gbc_lblProfesor.anchor = GridBagConstraints.EAST;
		gbc_lblProfesor.gridx = 1;
		gbc_lblProfesor.gridy = 1;
		add(lblProfesor, gbc_lblProfesor);
		
		cboTeacher = new JComboBox<Teacher>();
		GridBagConstraints gbc_cboTeacher = new GridBagConstraints();
		gbc_cboTeacher.insets = new Insets(0, 0, 5, 5);
		gbc_cboTeacher.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboTeacher.gridx = 2;
		gbc_cboTeacher.gridy = 1;
		add(cboTeacher, gbc_cboTeacher);
		
		JLabel lblGrupo = new JLabel("Grupo");
		GridBagConstraints gbc_lblGrupo = new GridBagConstraints();
		gbc_lblGrupo.anchor = GridBagConstraints.EAST;
		gbc_lblGrupo.insets = new Insets(0, 0, 5, 5);
		gbc_lblGrupo.gridx = 1;
		gbc_lblGrupo.gridy = 2;
		add(lblGrupo, gbc_lblGrupo);
		
		cboGroup = new JComboBox<Group>();
		GridBagConstraints gbc_cboGroup = new GridBagConstraints();
		gbc_cboGroup.insets = new Insets(0, 0, 5, 5);
		gbc_cboGroup.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboGroup.gridx = 2;
		gbc_cboGroup.gridy = 2;
		add(cboGroup, gbc_cboGroup);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		
		studentsTable = new JTable();
		scrollPane.setViewportView(studentsTable);
		
		DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Dni", "Nombre", "Apellidos"}, 0){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		studentsTable.setModel(tableModel);
		
		if(getButtonLabel() != null)
		{
			btnAction = new JButton(/*getButtonLabel()*/);
			GridBagConstraints gbc_btnAction = new GridBagConstraints();
			gbc_btnAction.anchor = GridBagConstraints.EAST;
			gbc_btnAction.insets = new Insets(0, 0, 5, 5);
			gbc_btnAction.gridx = 2;
			gbc_btnAction.gridy = 5;
			add(btnAction, gbc_btnAction);
		}
	}

	abstract protected String getButtonLabel();
}
