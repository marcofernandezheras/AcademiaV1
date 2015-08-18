package view.teacher;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import model.Teacher;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public abstract class ViewTeacherPanel extends TeacherInfoPanel {
	protected JTextField txtSearch;
	protected JButton btnSearch;
	protected JList<Teacher> listTeacher;
	protected JComboBox<String> cboSearchType;

	/**
	 * Create the panel.
	 */
	public ViewTeacherPanel() {
		super();
		spinSalary.setEnabled(false);
		txtSurnames.setEditable(false);
		txtName.setEditable(false);
		txtDni.setEditable(false);
		GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 100, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(searchPanel, gbc_lblNewLabel);
		GridBagLayout gbl_searchPanel = new GridBagLayout();
		gbl_searchPanel.columnWidths = new int[]{20, 23, 114, 82, 20, 0};
		gbl_searchPanel.rowHeights = new int[]{10, 25, 10, 0};
		gbl_searchPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_searchPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		searchPanel.setLayout(gbl_searchPanel);
		
		cboSearchType = new JComboBox<String>();
		cboSearchType.setModel(new DefaultComboBoxModel<String>(new String[] {"DNI", "Nombre", "Apellidos"}));
		GridBagConstraints gbc_cboSearchType = new GridBagConstraints();
		gbc_cboSearchType.insets = new Insets(0, 0, 5, 5);
		gbc_cboSearchType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboSearchType.gridx = 1;
		gbc_cboSearchType.gridy = 1;
		searchPanel.add(cboSearchType, gbc_cboSearchType);
		
		txtSearch = new JTextField();
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.fill = GridBagConstraints.BOTH;
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 2;
		gbc_txtSearch.gridy = 1;
		searchPanel.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		
		listTeacher = new JList<Teacher>();
		listTeacher.setBorder(new LineBorder(new Color(0, 0, 0)));
		listTeacher.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_listTeacher = new GridBagConstraints();
		gbc_listTeacher.fill = GridBagConstraints.BOTH;
		gbc_listTeacher.gridheight = 5;
		gbc_listTeacher.insets = new Insets(0, 0, 5, 5);
		gbc_listTeacher.gridx = 4;
		gbc_listTeacher.gridy = 1;
		add(listTeacher, gbc_listTeacher);
		
		DefaultListModel<Teacher> model = new DefaultListModel<Teacher>();
		listTeacher.setModel(model);
		
		btnSearch = new JButton("Buscar");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 1;
		searchPanel.add(btnSearch, gbc_btnSearch);
	}

	@Override
	protected String getButtonLabel() {		
		return null;
	}
}
