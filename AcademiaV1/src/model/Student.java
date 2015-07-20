package model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Student implements Serializable{
	private int id;
	private String dni;
	private String name; 
	private String surnames;
	private String comments; 
	private Date bornDate;
	
	public Student(int id, String dni, String name, String surnames) {
		super();
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.surnames = surnames;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}		
	
}
