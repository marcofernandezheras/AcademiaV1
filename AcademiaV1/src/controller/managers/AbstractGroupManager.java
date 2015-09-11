package controller.managers;

import java.util.ArrayList;
import java.util.List;

import model.Group;
import model.Student;
import model.Teacher;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.RetrieveException;

public abstract class AbstractGroupManager implements GroupManager {

	final protected TeacherManager teacherManager;
	final protected StudentManager studentManager;
	
	protected List<Group> groupList = new ArrayList<Group>();
	
	public AbstractGroupManager(TeacherManager teacherManager,
			StudentManager studentManager) {
		this.teacherManager = teacherManager;
		this.studentManager = studentManager;
	}

	@Override
	public List<Group> getAllGroups() {		
		return groupList;
	}

	@Override
	public List<Student> getStudentsFromGroup(Group group) throws RetrieveException, NotFoundException {
		List<Student> students = new ArrayList<Student>();
		for (Integer id : group.getStudentsIds()) {
			students.add(studentManager.getStudent(id));
		}
		return students;
	}

	@Override
	public Teacher getTeacherFromGroup(Group group) throws RetrieveException, NotFoundException {
		return teacherManager.getTeacher(group.getIdTeacher());
	}

	@Override
	public List<Group> getAllGroupsByTeacher(Teacher teacher) {
		List<Group> teacherGroups = new ArrayList<Group>();
		
		for (Group group : groupList) {
			if(group.getIdTeacher() == teacher.getId())
				teacherGroups.add(group);
		}
		
		return teacherGroups;
	}

	@Override
	public List<Group> getAllGroupsByStudent(Student student) {
		List<Group> studentGroups = new ArrayList<Group>();
		
		for (Group group : groupList) {
			if(group.getStudentsIds().contains(student.getId()))
				studentGroups.add(group);
		}
		
		return studentGroups;
	}

	@Override
	public StudentManager getStudentManager() {		
		return studentManager;
	}

	@Override
	public TeacherManager getTeacherManager() {		
		return teacherManager;
	}

	protected List<Integer> translateStudendList(List<Student> studentList){
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for (Student student : studentList) {
			idList.add(student.getId());
		}
		return idList;
	}
}
