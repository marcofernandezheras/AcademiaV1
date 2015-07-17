package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStudentManager implements StudentManager {

	private int lastId = 1;
	List<Student> studentList = new ArrayList<Student>();
	
	public FileStudentManager() throws IOException, Exception{
		try(FileObjectReader reader = new FileObjectReader(Constants.STUDENTS_FILE))
		{
			while(true){
				Object object = reader.nextObject();
				Student student = (Student) object;
				studentList.add(student);
			}
		} catch (FileNotFoundException e) {
			//We don't have students yet
		}		
		catch (EOFException e) {
			//We've read all students
		}
	}
	
	@Override
	public List<Student> getAllStudents() {
		return studentList;
	}

	@Override
	public Student getStudent(int id) {
		for (Student student : studentList) {
			if(student.getId() == id)
				return student;
		}
		return null;
	}

	@Override
	public Student getStudent(String dni) {
		for (Student student : studentList) {
			if(student.getDni().equalsIgnoreCase(dni))
				return student;
		}
		return null;
	}

	@Override
	public Student createStudent(String dni, String name, String surnames) {
		Student newstudent = new Student(lastId++, dni, name, surnames);
		writeStudentToFile(newstudent);	
		studentList.add(newstudent);
		return newstudent;
	}

	@Override
	public void updateStudent(Student student) {
		deleteStudent(student);
		writeStudentToFile(student);
		studentList.add(student);
	}

	@Override
	public void deleteStudent(Student student) {
		if(!studentList.remove(student))
			throw new IllegalArgumentException("Student not found");
		new File(Constants.STUDENTS_FILE).delete();
		for (Student storedStudent : studentList) {
			writeStudentToFile(storedStudent);
		}
	}

	private void writeStudentToFile(Student student){		
		try(FileObjectWriter writer = new FileObjectWriter(Constants.STUDENTS_FILE))
		{
			writer.writeObject(student);			
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException();
		}
	}
}
