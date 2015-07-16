package model;

public interface StudentManager {
	public List<Student> getAllStudents();
	public Student getStudent(int id);
	public void updateStudent(Student student);
	public void deleteStudent(Student student);
}
