package controller.managers;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Teacher;
import model.utils.Constants;
import model.utils.FileObjectReader;
import model.utils.FileObjectWriter;

public class FileTeacherManager implements TeacherManager {

	private static int lastId = 1;
	List<Teacher> teacherList = new ArrayList<Teacher>();
	
	public FileTeacherManager() throws IOException, Exception {
		try(FileObjectReader reader = new FileObjectReader(Constants.TEACHERS_FILE))
		{
			while(true){
				Object object = reader.nextObject();
				Teacher Teacher = (Teacher) object;
				if(Teacher.getId() > lastId)
					lastId = Teacher.getId() + 1;
				teacherList.add(Teacher);
			}
		} catch (FileNotFoundException e) {
			//We don't have Teachers yet
		}		
		catch (EOFException e) {
			//We've read all Teachers
		}
	}
	
	@Override
	public List<Teacher> getAllTeachers() {
		return teacherList;
	}

	@Override
	public Teacher getTeacher(int id) {
		for (Teacher teacher : teacherList) {
			if(teacher.getId() == id)
				return teacher;
		}
		return null;
	}

	@Override
	public Teacher getTeacher(String dni) {
		for (Teacher teacher : teacherList) {
			if(teacher.getDni().equalsIgnoreCase(dni))
				return teacher;
		}
		return null;
	}

	@Override
	public Teacher createTeacher(String dni, String name, String surnames) {
		Teacher newTeacher = new Teacher(lastId++, dni, name, surnames);
		writeTeacherToFile(newTeacher);	
		teacherList.add(newTeacher);
		return newTeacher;
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		deleteTeacher(teacher);
		writeTeacherToFile(teacher);
		teacherList.add(teacher);
	}

	@Override
	public void deleteTeacher(Teacher teacher) {
		if(!teacherList.remove(teacher))
			throw new IllegalArgumentException("Teacher not found");
		new File(Constants.TEACHERS_FILE).delete();
		for (Teacher storedTeacher : teacherList) {
			writeTeacherToFile(storedTeacher);
		}
	}

	private void writeTeacherToFile(Teacher student){		
		try(FileObjectWriter writer = new FileObjectWriter(Constants.TEACHERS_FILE))
		{
			writer.writeObject(student);			
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException();
		}
	}
}
