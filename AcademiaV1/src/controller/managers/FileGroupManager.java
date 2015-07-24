package controller.managers;

import java.util.List;

import model.Group;
import model.Student;
import model.Teacher;

public class FileGroupManager implements GroupManager {

	final private TeacherManager teacherManager;
	final private StudentManager studentManager;
	
	
	public FileGroupManager(TeacherManager teacherManager,
			StudentManager studentManager) {
		super();
		this.teacherManager = teacherManager;
		this.studentManager = studentManager;
	}

	@Override
	public List<Group> getAllGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group createGroup(String groupName, Teacher teacher,
			List<Student> students) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Student> getStudentsFromGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher getTeacherFromGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getAllGroupsByTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getAllGroupsByStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

}
