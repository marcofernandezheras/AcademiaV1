package controller.group;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Student;
import model.Teacher;
import controller.managers.GroupManager;
import controller.managers.StudentManager;
import controller.managers.TeacherManager;
import view.group.CreateGroupPanel;

@SuppressWarnings("serial")
public class CreateGroupController extends CreateGroupPanel {
	
	private final GroupManager groupManager;
	private final StudentManager studentManager;
	
	protected JTextField txtGroup = null;
	public CreateGroupController(GroupManager groupManager) {
		super();
		this.groupManager = groupManager;
		this.studentManager = groupManager.getStudentManager();
		if(cboGroup instanceof JTextField)
			txtGroup = (JTextField)cboGroup;
		fillTeachersCombo();
		fillStudentsCombo();
		bindEvents();
	}
	
	private void fillTeachersCombo() {
		TeacherManager teacherManager = groupManager.getTeacherManager();
		DefaultComboBoxModel<Teacher> comboBoxModel = (DefaultComboBoxModel<Teacher>) cboTeacher.getModel();
		for (Teacher teacher : teacherManager.getAllTeachers()) {
			comboBoxModel.addElement(teacher);			
		}
	}
	
	private void fillStudentsCombo() {
		StudentManager studentManager = groupManager.getStudentManager();
		DefaultComboBoxModel<Student> model = (DefaultComboBoxModel<Student>) cboStudent.getModel();
		for (Student student : studentManager.getAllStudents()) {
			model.addElement(student);
		}
	}
	
	private void bindEvents() {
		btnAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonEvent();
			}
		});
		
		btnAddStudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addStudentToTable();
			}
		});
		
		btnremoveStudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeStudentFromTable();
			}
		});
	}
	
	
	protected void buttonEvent() {
		DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();		
		List<Student> studentList = new ArrayList<Student>(model.getRowCount());
		
		for (int i = 0; i < model.getRowCount(); i++) {
			String dni = model.getValueAt(i, 0).toString();
			Student student = studentManager.getStudent(dni);
			studentList.add(student);			
		}
		
		Teacher teacher = (Teacher) cboTeacher.getSelectedItem();
		String groupName = txtGroup.getText();
		if(!(teacher==null || groupName.isEmpty() || studentList.isEmpty())){
			groupManager.createGroup(groupName, teacher, studentList);
			clearUi();
			JOptionPane.showMessageDialog(this, "Grupo creado con éxito");
		}
		else
			JOptionPane.showMessageDialog(this, "El grupo debe tener profesor, alumnos y nombre");
	}
	
	private void clearUi() {
		DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();	
		model.setRowCount(0);
		if(txtGroup != null)
			txtGroup.setText("");
	}

	protected void addStudentToTable() {
		Student student = (Student) cboStudent.getSelectedItem();
		DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
		boolean alreadyInTable = false;
		for(int i = 0; i < model.getRowCount(); i++)
		{
			if(model.getValueAt(i, 0).equals(student.getDni()))
			{
				alreadyInTable = true;
				break;
			}					
		}
		
		if(!alreadyInTable)
		{
			model.addRow(new Object[]{student.getDni(), student.getName(),
										student.getSurnames()});
		}
	}
	
	protected void removeStudentFromTable() {
		DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
		if(studentsTable.getSelectedRow() < model.getRowCount() 
				&& studentsTable.getSelectedRow() != -1)
			model.removeRow(studentsTable.getSelectedRow());
	}
}
