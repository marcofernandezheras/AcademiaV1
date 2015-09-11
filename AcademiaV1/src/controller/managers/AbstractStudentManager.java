package controller.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import model.Student;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;

public abstract class AbstractStudentManager implements StudentManager {

	protected List<Student> studentList = new ArrayList<Student>();

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
	public void deleteStudent(Student student) throws NotFoundException,
			DeleteException {
		if(!studentList.remove(student))
			throw new NotFoundException("Student not found");
	}
}
