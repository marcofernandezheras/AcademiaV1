package model;

@SuppressWarnings("serial")
public class Student extends Person{
	
	private String comments; 
	
	public Student(int id, String dni, String name, String surnames) {
		super(id, surnames, surnames, surnames);		
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}	
	
}
