package model;

import java.util.List;

public class Group {
	
	final int id;
	final int idTeacher;
	final List<Integer> studentsIds;
	
	public Group(int id, int idTeacher, List<Integer> studentsIds) {
		super();
		this.id = id;
		this.idTeacher = idTeacher;
		this.studentsIds = studentsIds;
	}

	public int getId() {
		return id;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public List<Integer> getStudentsIds() {
		return studentsIds;
	}		
}
