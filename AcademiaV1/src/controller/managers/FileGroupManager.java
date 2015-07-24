package controller.managers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Group;
import model.Student;
import model.Teacher;
import model.utils.Constants;
import model.utils.FileObjectWriter;
import model.utils.ObjectWriter;

public class FileGroupManager implements GroupManager {

	final private TeacherManager teacherManager;
	final private StudentManager studentManager;
	
	private List<Group> groupList = new ArrayList<Group>();
	private static int lastId = 1;
	
	public FileGroupManager(TeacherManager teacherManager,
			StudentManager studentManager) {
		super();
		this.teacherManager = teacherManager;
		this.studentManager = studentManager;
	}

	@Override
	public List<Group> getAllGroups() {		
		return groupList;
	}

	@Override
	public Group createGroup(String groupName, Teacher teacher,
			List<Student> students) {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for (Student student : students) {
			idList.add(student.getId());
		}
		Group group = new Group(lastId++, groupName, teacher.getId(), idList);
		
		try(ObjectWriter writer = new FileObjectWriter(Constants.GROUPS_FILE))
		{
			writer.writeObject(group);
		} catch (IOException e) {			
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return group;
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
