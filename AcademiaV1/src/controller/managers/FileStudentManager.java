package controller.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import model.Student;
import model.exceptions.WriteException;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;
import model.utils.Constants;
import model.utils.FileObjectReader;
import model.utils.FileObjectWriter;

public class FileStudentManager implements StudentManager {

	private static int lastId = 1;
	List<Student> studentList = new ArrayList<Student>();
	
	public FileStudentManager() throws IOException, Exception{
		try(FileObjectReader<Student> reader = new FileObjectReader<Student>(Constants.STUDENTS_FILE))
		{
			while(true){
				Student student = reader.nextObject();
				if(student.getId() >= lastId)
					lastId = student.getId() + 1;
				studentList.add(student);
			}
		} catch (FileNotFoundException e) {
			//We don't have students yet
		}		
		catch (NotFoundException e) {
			//We've read all students
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Student> getAllStudents() {
		return studentList;
	}

	@Override
	public Student getStudent(int id) throws NotFoundException {
		List<Student> validStudents = getStudents(s -> s.getId() == id);
		if(validStudents.isEmpty())
			throw new NotFoundException("Studend for ID "+ id + " not found");
		return validStudents.get(0);
	}

	@Override
	public Student getStudent(String dni) throws NotFoundException {
		List<Student> validStudents = getStudents(s -> s.getDni().equalsIgnoreCase(dni));
		if(validStudents.isEmpty())
			throw new NotFoundException("Studend for DNI "+ dni + " not found");
		return validStudents.get(0);
	}

	@Override
	public List<Student> getStudents(Predicate<Student> predicate) {
		List<Student> validStudents = new ArrayList<Student>();
		for (Student student : studentList) {
			if(predicate.test(student))
				validStudents.add(student);
		}
		return validStudents;
	}
	
	@Override
	public Student createStudent(String dni, String name, String surnames) throws CreateException {
		try {
			Student newstudent = new Student(lastId++, dni, name, surnames);
			writeStudentToFile(newstudent);	
			studentList.add(newstudent);
			return newstudent;
		} catch (WriteException e) {
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void updateStudent(Student student) throws NotFoundException, UpdateException {
		try {
			deleteStudent(student);
			writeStudentToFile(student);
			studentList.add(student);
		} catch (DeleteException | WriteException e) {
			throw new UpdateException(e.getMessage());
		} 	
	}

	@Override
	public void deleteStudent(Student student) throws DeleteException, NotFoundException {
		if(!studentList.remove(student))
			throw new NotFoundException("Student not found");
		new File(Constants.STUDENTS_FILE).delete();
		for (Student storedStudent : studentList) {
			try {
				writeStudentToFile(storedStudent);
			} catch (WriteException e) {
				throw new DeleteException(e.getMessage());
			}
		}
	}

	private void writeStudentToFile(Student student) throws WriteException{		
		try(FileObjectWriter<Student> writer = new FileObjectWriter<Student>(Constants.STUDENTS_FILE))
		{
			writer.writeObject(student);	
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new WriteException(e1.getMessage());
		}
	}	
}
