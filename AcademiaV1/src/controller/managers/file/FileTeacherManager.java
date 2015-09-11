package controller.managers.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import controller.managers.AbstractTeacherManager;
import model.Teacher;
import model.exceptions.WriteException;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;
import model.utils.Constants;
import model.utils.FileObjectReader;
import model.utils.FileObjectWriter;

public class FileTeacherManager extends AbstractTeacherManager {

	private static int lastId = 1;
	
	
	public FileTeacherManager() throws IOException, Exception {
		try(FileObjectReader<Teacher> reader = new FileObjectReader<Teacher>(Constants.TEACHERS_FILE))
		{
			while(true){
				Teacher Teacher = reader.nextObject();
				if(Teacher.getId() > lastId)
					lastId = Teacher.getId() + 1;
				teacherList.add(Teacher);
			}
		} catch (FileNotFoundException e) {
			//We don't have Teachers yet
		}		
		catch (NotFoundException e) {
			//We've read all Teachers
		}
	}		
	
	@Override
	public Teacher createTeacher(String dni, String name, String surnames) throws CreateException {
		try {
			Teacher newTeacher = new Teacher(lastId++, dni, name, surnames);
			writeTeacherToFile(newTeacher);	
			teacherList.add(newTeacher);
			return newTeacher;
		} catch (WriteException e) {
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void updateTeacher(Teacher teacher) throws UpdateException, NotFoundException {
		try {
			deleteTeacher(teacher);
			writeTeacherToFile(teacher);
			teacherList.add(teacher);
		} catch (DeleteException | WriteException e) {
			throw new UpdateException(e.getMessage());
		} 
	}

	@Override
	public void deleteTeacher(Teacher teacher) throws NotFoundException, DeleteException {
		if(!teacherList.remove(teacher))
			throw new NotFoundException("Teacher not found");
		new File(Constants.TEACHERS_FILE).delete();
		for (Teacher storedTeacher : teacherList) {
			try {
				writeTeacherToFile(storedTeacher);
			} catch (WriteException e) {
				throw new DeleteException(e.getMessage());
			}
		}
	}

	private void writeTeacherToFile(Teacher student) throws WriteException{		
		try(FileObjectWriter<Teacher> writer = new FileObjectWriter<Teacher>(Constants.TEACHERS_FILE))
		{
			writer.writeObject(student);			
		} catch (Exception e1) {
			throw new WriteException(e1.getMessage());
		}
	}
}