package model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class Person implements Serializable{
	private int id;
	private String dni;
	private String name; 
	private String surnames;
	private Date bornDate;
	
	public Person(int id, String dni, String name, String surnames) {
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

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {		
		return String.format("[%s] %s %s", dni, name, surnames);
	}
}
