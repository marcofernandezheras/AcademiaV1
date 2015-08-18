package controller.managers;

import java.util.List;
import java.util.function.Predicate;
import model.Teacher;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.RetrieveException;
import model.exceptions.crud.UpdateException;

public interface TeacherManager {
	public List<Teacher> getAllTeachers();
	public List<Teacher> getTeachers(Predicate<Teacher> predicate);
	public Teacher getTeacher(int id) throws RetrieveException, NotFoundException;
	public Teacher getTeacher(String dni) throws RetrieveException, NotFoundException;
	public Teacher createTeacher(String dni, String name, String surnames) throws CreateException;
	public void updateTeacher(Teacher teacher) throws NotFoundException, UpdateException;
	public void deleteTeacher(Teacher teacher) throws NotFoundException, DeleteException;
}
