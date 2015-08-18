package controller.group;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Group;
import model.Student;
import model.Teacher;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.RetrieveException;
import controller.managers.GroupManager;
import controller.managers.TeacherManager;
import view.group.GroupInfoPanel;

@SuppressWarnings("serial")
public class ViewGroupController extends GroupInfoPanel {

	protected final GroupManager groupManager;	
	protected final JComboBox<Group> comboGroup;
	@SuppressWarnings("unchecked")
	public ViewGroupController(GroupManager groupManager) {
		super();
		this.groupManager = groupManager;	
		comboGroup = (JComboBox<Group>)cboGroup;
		bindEvents();
		fillCboTeacher();
	}
	
	private void fillCboTeacher() {
		TeacherManager teacherManager = groupManager.getTeacherManager();
		DefaultComboBoxModel<Teacher> comboBoxModel = (DefaultComboBoxModel<Teacher>) cboTeacher.getModel();
		for (Teacher teacher : teacherManager.getAllTeachers()) {
			comboBoxModel.addElement(teacher);			
		}
	}

	private void bindEvents() {		
		cboTeacher.addActionListener(e -> changeTeacher((Teacher) cboTeacher.getSelectedItem()));		
		comboGroup.addActionListener(e -> fillStudentTable((Group) comboGroup.getSelectedItem()));
	}	

	protected void changeTeacher(Teacher currentTeacher) {
		DefaultComboBoxModel<Group> model = (DefaultComboBoxModel<Group>) comboGroup.getModel();
		model.removeAllElements();
		List<Group> groupsByTeacher = groupManager.getAllGroupsByTeacher(currentTeacher);
		for (Group group : groupsByTeacher) {
			model.addElement(group);
		}
	}

	protected void fillStudentTable(Group currentGroup) {
		DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
		model.setRowCount(0);
		if(currentGroup != null)
		{
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
	protected
	String getButtonLabel() {
		return null;
	}

}
