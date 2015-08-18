package controller.managers;

import java.util.List;

import model.Group;
import model.Student;
import model.Teacher;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.RetrieveException;
import model.exceptions.crud.UpdateException;

public interface GroupManager {
	public List<Group> getAllGroups();
	public Group createGroup(String groupName, Teacher teacher, List<Student> students) throws CreateException;
	public void updateGroup(Group group) throws NotFoundException, UpdateException;
	public void deleteGroup(Group group) throws NotFoundException, DeleteException;
	
	public List<Student> getStudentsFromGroup(Group group) throws RetrieveException, NotFoundException;
	public Teacher getTeacherFromGroup(Group group) throws RetrieveException, NotFoundException;
	
	public List<Group> getAllGroupsByTeacher(Teacher teacher);
	public List<Group> getAllGroupsByStudent(Student student);
	
	public StudentManager getStudentManager();
	public TeacherManager getTeacherManager();
}
