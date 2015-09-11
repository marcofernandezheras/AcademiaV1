package model;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class Teacher extends Person{

	private BigDecimal salary;
	
	public Teacher(int id, String dni, String name, String surnames) {
		super(id, dni, name, surnames);
	}

	public Teacher(int id, String dni, String name, String surnames,
			BigDecimal salary) {
		super(id, dni, name, surnames);
		this.salary = salary;
	}

	public Teacher(int id, String dni, String name, String surnames, Date born,
			BigDecimal salary){
		this(id, dni, name, surnames, salary);
		this.setBornDate(born);
	}
	
	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = new BigDecimal(salary);
		this.salary.setScale(2, BigDecimal.ROUND_CEILING);
	}	
}
