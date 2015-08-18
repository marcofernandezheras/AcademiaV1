package controller.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import model.Group;
import model.Student;
import model.Teacher;
import model.exceptions.WriteException;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.RetrieveException;
import model.exceptions.crud.UpdateException;
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
		
		try (ObjectReader<Group> reader = new FileObjectReader<Group>(Constants.GROUPS_FILE))
		{
			while(true)
			{
				Group group = reader.nextObject();
				groupList.add(group);
				if(group.getId() > lastId)
					lastId = group.getId() + 1;
			}
		} catch (NotFoundException e) {
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
			List<Student> students) throws CreateException {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for (Student student : students) {
			idList.add(student.getId());
		}
		Group group = new Group(lastId++, groupName, teacher.getId(), idList);
		
		try(ObjectWriter<Group> writer = new FileObjectWriter<Group>(Constants.GROUPS_FILE))
		{
			writer.writeObject(group);
		} catch (Exception e) {
			throw new CreateException(e.getMessage());
		}
		
		groupList.add(group);
		return group;
	}

	@Override
	public void updateGroup(Group group) throws UpdateException, NotFoundException {
		try {
			deleteGroup(group);
			writeGroupToFile(group);
			groupList.add(group);
		} catch (WriteException | DeleteException e) {
			throw new UpdateException(e.getMessage());
		} 
	}	

	@Override
	public void deleteGroup(Group group) throws NotFoundException, DeleteException {
		if(!groupList.remove(group))
			throw new NotFoundException("Group not found");
		new File(Constants.GROUPS_FILE).delete();
		for (Group storedGroup : groupList) {
			try {
				writeGroupToFile(storedGroup);
			} catch (WriteException e) {
				throw new DeleteException(e.getMessage());
			}
		}
	}

	private void writeGroupToFile(Group group) throws WriteException  {
		try(FileObjectWriter<Group> writer = new FileObjectWriter<Group>(Constants.GROUPS_FILE))
		{
			writer.writeObject(group);			
		} 
		catch (Exception e) {
			throw new WriteException(e.getMessage());
		}
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

}
