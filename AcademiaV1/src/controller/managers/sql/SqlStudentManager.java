package controller.managers.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.managers.AbstractStudentManager;
import model.Student;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;
import model.utils.Constants;
import model.utils.SqlConnectionManager;

public class SqlStudentManager extends AbstractStudentManager {

	private final Connection connection;
	public SqlStudentManager() throws SQLException {
		this.connection = SqlConnectionManager.getConnection();
		
		try(PreparedStatement stm = connection.prepareStatement(Constants.SELECT_ALL_STUDENTS);
				ResultSet rs = stm.executeQuery())
		{
			while (rs.next()) {
				int id = rs.getInt("id");
				String dni = rs.getString("dni");
				String name = rs.getString("name");
				String surnames = rs.getString("surnames");
				Date bornDate = rs.getDate("bornDate");
				String comments = rs.getString("comments");
				studentList.add(new Student(id, dni, name, surnames, bornDate, comments));
			}
		} 
	}

	@Override
	public Student createStudent(String dni, String name, String surnames)
			throws CreateException {		
		try(PreparedStatement stm = connection.prepareStatement(Constants.INSERT_STUDENT, PreparedStatement.RETURN_GENERATED_KEYS))
		{
			stm.setString(1, dni);
			stm.setString(2, name);
			stm.setString(3, surnames);
			stm.setDate(4, new Date(0));
			stm.setString(5, "");
			int rows = stm.executeUpdate();
			if(rows != 1)
				throw new CreateException("Error on create student: " + rows + " rows affected, 1 expected");
			
			int id = 0;
			ResultSet generatedKeys = stm.getGeneratedKeys();
			if(generatedKeys.next())
				id = generatedKeys.getInt(1);
			generatedKeys.close();
			
			Student student = new Student(id, dni, name, surnames);
			studentList.add(student);
			return student;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void updateStudent(Student student) throws NotFoundException,
			UpdateException {
		if(!studentList.contains(student))
			throw new NotFoundException("Student not found for update");
		try(PreparedStatement stm = connection.prepareStatement(Constants.UPDATE_STUDENT))
		{
			stm.setString(1, student.getDni());
			stm.setString(2, student.getName());
			stm.setString(3, student.getSurnames());
			stm.setDate(4, new Date(student.getBornDate().getTime()));
			stm.setString(5, student.getComments());
			stm.setInt(6, student.getId());
			int rows = stm.executeUpdate();
			if(rows != 1)
				throw new UpdateException("Error on update student: " + rows + " rows affected, 1 expected");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UpdateException(e.getMessage());
		}		
	}	

	@Override
	public void deleteStudent(Student student) throws NotFoundException,
			DeleteException {
		if(!studentList.contains(student))
			throw new NotFoundException("Student not found for delete");
		try(PreparedStatement stm = connection.prepareStatement(Constants.DELETE_STUDENT))
		{
			stm.setInt(1, student.getId());
			int rows = stm.executeUpdate();
			if(rows != 1)
				throw new DeleteException("Error on delete student: " + rows + " rows affected, 1 expected");
			super.deleteStudent(student);
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new DeleteException(e.getMessage());
		}
		
	}
}
