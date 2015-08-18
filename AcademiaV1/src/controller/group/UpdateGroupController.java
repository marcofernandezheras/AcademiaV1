package controller.group;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Group;
import model.Student;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.RetrieveException;
import model.exceptions.crud.UpdateException;
import controller.managers.GroupManager;

@SuppressWarnings("serial")
public class UpdateGroupController extends CreateGroupController {

	private JComboBox<Group> groupsCombo;
	private Group currentGroup;
	@SuppressWarnings("unchecked")
	public UpdateGroupController(GroupManager groupManager) {
		super(groupManager);	
		groupsCombo = (JComboBox<Group>) cboGroup;		
		groupsCombo.addActionListener(e -> groupChanged());
		fillGroupsCombo();
	}

	private void fillGroupsCombo() {
		DefaultComboBoxModel<Group> model = (DefaultComboBoxModel<Group>) groupsCombo.getModel();		
		
		for (Group group : groupManager.getAllGroups()) {
			model.addElement(group);
		}
	}
	
	protected void groupChanged() {
		if(groupsCombo.getSelectedItem() instanceof Group)
		{
			currentGroup = (Group) groupsCombo.getSelectedItem();				
			DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
			model.setRowCount(0);
			try {
				List<Student> studentsFromGroup = groupManager.getStudentsFromGroup(currentGroup);
				for (Student student : studentsFromGroup) {
					model.addRow(new Object[]{ student.getDni(), student.getName(), student.getSurnames() });
				}
			} catch (RetrieveException | NotFoundException e) {				
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
	}

	@Override
	protected void buttonEvent() {
		if(currentGroup != null)
		{
			String newName = currentGroup.getName();
			if(groupsCombo.getSelectedItem() instanceof String)
			{
				newName = (String) groupsCombo.getSelectedItem();							
			}

			List<Integer> studentIds = new ArrayList<Integer>();
			for (Student student : getStudentsFromTable()) {
				studentIds.add(student.getId());
			}	

			if(!(newName.isEmpty() || studentIds.isEmpty()))
			{
				currentGroup.setName(newName);
				currentGroup.setStudentsIds(studentIds);		
				try {
					groupManager.updateGroup(currentGroup);
					JOptionPane.showMessageDialog(this, "Grupo guardado con éxito");
				} catch (NotFoundException | UpdateException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "El grupo debe tener alumnos y nombre");			
			}
		}
	}
	
	@Override
	protected JComponent getGroupEditor() {
		JComboBox<Group> groupEditor = new JComboBox<Group>();				
		groupEditor.setEditable(true);
		return groupEditor;
	}
}
