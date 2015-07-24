package model;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable{
	
	final int id;
	final int idTeacher;
	final List<Integer> studentsIds;
	private String name;
	
	public Group(int id, String name, int idTeacher, List<Integer> studentsIds) {
		super();
		this.id = id;
		this.idTeacher = idTeacher;
		this.studentsIds = studentsIds;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}			
}
