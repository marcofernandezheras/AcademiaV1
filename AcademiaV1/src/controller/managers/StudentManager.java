package controller.managers;
import java.util.List;
import java.util.function.Predicate;

import model.Student;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.RetrieveException;
import model.exceptions.crud.UpdateException;

public interface StudentManager {
	public List<Student> getAllStudents();
	public List<Student> getStudents(Predicate<Student> predicate);
	public Student getStudent(int id) throws RetrieveException, NotFoundException;
	public Student getStudent(String dni) throws RetrieveException, NotFoundException;	
	public Student createStudent(String dni, String name, String surnames) throws CreateException;
	public void updateStudent(Student student) throws NotFoundException, UpdateException;
	public void deleteStudent(Student student) throws NotFoundException, DeleteException;
}
