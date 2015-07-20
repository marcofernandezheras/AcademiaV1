package model;

import java.util.List;

public interface TeacherManager {
	public List<Teacher> getAllTeachers();
	public Teacher getTeacher(int id);
	public Teacher getTeacher(String dni);
	public Teacher createTeacher(String dni, String name, String surnames);
	public void updateTeacher(Teacher teacher);
	public void deleteTeacher(Teacher teacher);
}
