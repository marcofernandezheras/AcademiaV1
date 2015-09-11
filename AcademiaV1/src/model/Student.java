package model;

import java.util.Date;

@SuppressWarnings("serial")
public class Student extends Person{
	
	private String comments; 
	
	public Student(int id, String dni, String name, String surnames) {
		super(id, dni, name, surnames);		
	}
	
	public Student(int id, String dni, String name, String surnames, Date born,
			String comments) {
		super(id, dni, name, surnames);
		this.comments = comments;
		this.setBornDate(born);
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}	
	
}
