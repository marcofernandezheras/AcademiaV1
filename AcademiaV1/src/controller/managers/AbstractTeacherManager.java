package controller.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import model.Teacher;
import model.exceptions.crud.NotFoundException;

public abstract class AbstractTeacherManager implements TeacherManager {

	protected List<Teacher> teacherList = new ArrayList<Teacher>();

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherList;
	}

	@Override
	public Teacher getTeacher(int id) throws NotFoundException {
		List<Teacher> validTeachers = getTeachers(s -> s.getId() == id);
		if(validTeachers.isEmpty())
			throw new NotFoundException("Teacher for ID "+ id + " not found");
		return validTeachers.get(0);
	}

	@Override
	public Teacher getTeacher(String dni) throws NotFoundException {
		List<Teacher> validTeachers = getTeachers(s -> s.getDni().equalsIgnoreCase(dni));
		if(validTeachers.isEmpty())
			throw new NotFoundException("Teacher for DNI "+ dni + " not found");
		return validTeachers.get(0);
	}

	@Override
	public List<Teacher> getTeachers(Predicate<Teacher> predicate) {
		List<Teacher> validTeachers = new ArrayList<Teacher>();
		for (Teacher teacher : teacherList) {
			if(predicate.test(teacher))
				validTeachers.add(teacher);
		}
		return validTeachers;
	}

}
