package controller.managers.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import controller.managers.AbstractGroupManager;
import controller.managers.StudentManager;
import controller.managers.TeacherManager;
import model.Group;
import model.Student;
import model.Teacher;
import model.exceptions.WriteException;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;
import model.utils.Constants;
import model.utils.FileObjectReader;
import model.utils.FileObjectWriter;
import model.utils.ObjectReader;
import model.utils.ObjectWriter;

public class FileGroupManager extends AbstractGroupManager {
	
	private static int lastId = 1;
	
	public FileGroupManager(TeacherManager teacherManager,
			StudentManager studentManager) throws Exception {
		super(teacherManager, studentManager);
		
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
	public Group createGroup(String groupName, Teacher teacher,
			List<Student> students) throws CreateException {
		ArrayList<Integer> idList = (ArrayList<Integer>) translateStudendList(students);
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

}
