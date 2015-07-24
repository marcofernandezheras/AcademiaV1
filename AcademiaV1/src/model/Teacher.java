package model;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Teacher extends Person{

	private BigDecimal salary;
	
	public Teacher(int id, String dni, String name, String surnames) {
		super(id, dni, name, surnames);
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = new BigDecimal(salary);
		this.salary.setScale(2, BigDecimal.ROUND_CEILING);
	}	
}
