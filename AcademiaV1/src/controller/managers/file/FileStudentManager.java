package controller.managers.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import controller.managers.AbstractStudentManager;
import model.Student;
import model.exceptions.WriteException;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;
import model.utils.Constants;
import model.utils.FileObjectReader;
import model.utils.FileObjectWriter;

public class FileStudentManager extends AbstractStudentManager {

	private static int lastId = 1;	
	
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
	public Student createStudent(String dni, String name, String surnames) throws CreateException {
		try {
			Student newstudent = new Student(lastId++, dni, name, surnames);
			studentList.add(newstudent);
			writeStudentToFile(newstudent);				
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
		super.deleteStudent(student);
		
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
