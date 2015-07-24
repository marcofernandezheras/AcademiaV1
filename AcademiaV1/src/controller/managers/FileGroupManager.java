package controller.managers;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Group;
import model.Student;
import model.Teacher;
import model.utils.Constants;
import model.utils.FileObjectReader;
import model.utils.FileObjectWriter;
import model.utils.ObjectReader;
import model.utils.ObjectWriter;

public class FileGroupManager implements GroupManager {

	final private TeacherManager teacherManager;
	final private StudentManager studentManager;
	
	private List<Group> groupList = new ArrayList<Group>();
	private static int lastId = 1;
	
	public FileGroupManager(TeacherManager teacherManager,
			StudentManager studentManager) throws Exception {
		super();
		this.teacherManager = teacherManager;
		this.studentManager = studentManager;
		
		try (ObjectReader reader = new FileObjectReader(Constants.GROUPS_FILE))
		{
			while(true)
			{
				Object object = reader.nextObject();
				Group group = (Group)object;
				groupList.add(group);
				if(group.getId() > lastId)
					lastId = group.getId() + 1;
			}
		} catch (EOFException e) {
			//We've read all groups
		}
		catch (FileNotFoundException e) {
			// We don't have groups yet
		}
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
		
		groupList.add(group);
		return group;
	}

	@Override
	public void updateGroup(Group group) {
		deleteGroup(group);
		writeGroupToFile(group);
		groupList.add(group);
	}	

	@Override
	public void deleteGroup(Group group) {
		if(!groupList.remove(group))
			throw new IllegalArgumentException("Group not found");
		new File(Constants.GROUPS_FILE).delete();
		for (Group storedGroup : groupList) {
			writeGroupToFile(storedGroup);
		}
	}

	private void writeGroupToFile(Group group) {
		try(FileObjectWriter writer = new FileObjectWriter(Constants.GROUPS_FILE))
		{
			writer.writeObject(group);			
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@Override
	public List<Student> getStudentsFromGroup(Group group) {
		List<Student> students = new ArrayList<Student>();
		for (Integer id : group.getStudentsIds()) {
			students.add(studentManager.getStudent(id));
		}
		return students;
	}

	@Override
	public Teacher getTeacherFromGroup(Group group) {
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

}
