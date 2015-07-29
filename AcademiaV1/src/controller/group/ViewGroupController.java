package controller.group;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import model.Group;
import model.Student;
import model.Teacher;
import controller.managers.GroupManager;
import controller.managers.TeacherManager;
import view.group.GroupInfoPanel;

@SuppressWarnings("serial")
public class ViewGroupController extends GroupInfoPanel {

	private final GroupManager groupManager;	
	private final JComboBox<Group> comboGroup;
	@SuppressWarnings("unchecked")
	public ViewGroupController(GroupManager groupManager) {
		super();
		this.groupManager = groupManager;	
		comboGroup = (JComboBox<Group>)cboGroup;
		fillCboTeacher();
		bindEvents();
	}
	
	private void fillCboTeacher() {
		TeacherManager teacherManager = groupManager.getTeacherManager();
		DefaultComboBoxModel<Teacher> comboBoxModel = (DefaultComboBoxModel<Teacher>) cboTeacher.getModel();
		for (Teacher teacher : teacherManager.getAllTeachers()) {
			comboBoxModel.addElement(teacher);			
		}
	}

	private void bindEvents() {		
		cboTeacher.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Teacher currentTeacher = (Teacher) cboTeacher.getSelectedItem();
				changeTeacher(currentTeacher);
			}
		});
		
		comboGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Group currentGroup = (Group) comboGroup.getSelectedItem();
				fillStudentTable(currentGroup);
			}
		});
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
		List<Student> studentsFromGroup = groupManager.getStudentsFromGroup(currentGroup);
		for (Student student : studentsFromGroup) {
			model.addRow(new Object[]{ student.getDni(), student.getName(), student.getSurnames() });
		}
	}
	
	@Override
	protected
	String getButtonLabel() {
		return null;
	}

}
