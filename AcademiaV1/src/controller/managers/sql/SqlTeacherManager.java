package controller.managers.sql;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.managers.AbstractTeacherManager;
import model.Teacher;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;
import model.utils.Constants;
import model.utils.SqlConnectionManager;

public class SqlTeacherManager extends AbstractTeacherManager {

	private Connection connection;

	public SqlTeacherManager() {
		this.connection = SqlConnectionManager.getConnection();
		
		try(PreparedStatement stm = connection.prepareStatement(Constants.SELECT_ALL_TEACHERS);
				ResultSet rs = stm.executeQuery())
		{
			while (rs.next()) {
				int id = rs.getInt("id");
				String dni = rs.getString("dni");
				String name = rs.getString("name");
				String surnames = rs.getString("surnames");
				Date bornDate = rs.getDate("bornDate");
				BigDecimal salary = rs.getBigDecimal("salary");
				teacherList.add(new Teacher(id, dni, name, surnames, bornDate, salary));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public Teacher createTeacher(String dni, String name, String surnames)
			throws CreateException {
		try(PreparedStatement stm = connection.prepareStatement(Constants.INSERT_TEACHER, PreparedStatement.RETURN_GENERATED_KEYS))
		{
			stm.setString(1, dni);
			stm.setString(2, name);
			stm.setString(3, surnames);
			stm.setDate(4, new Date(0));
			stm.setBigDecimal(5, BigDecimal.ZERO);
			int rows = stm.executeUpdate();
			if(rows != 1)
				throw new CreateException("Error on create teacher: " + rows + " rows affected, 1 expected");
			
			int id = 0;
			ResultSet generatedKeys = stm.getGeneratedKeys();
			if(generatedKeys.next())
				id = generatedKeys.getInt(1);
			generatedKeys.close();
			
			Teacher teacher = new Teacher(id, dni, name, surnames);
			teacherList.add(teacher);
			return teacher;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void updateTeacher(Teacher teacher) throws NotFoundException,
			UpdateException {
		if(!teacherList.contains(teacher))
			throw new NotFoundException("Teacher not found for update");
		try(PreparedStatement stm = connection.prepareStatement(Constants.UPDATE_TEACHER))
		{
			stm.setString(1, teacher.getDni());
			stm.setString(2, teacher.getName());
			stm.setString(3, teacher.getSurnames());
			stm.setDate(4, new Date(teacher.getBornDate().getTime()));
			stm.setBigDecimal(5, teacher.getSalary());
			stm.setInt(6, teacher.getId());
			int rows = stm.executeUpdate();
			if(rows != 1)
				throw new UpdateException("Error on update teacher: " + rows + " rows affected, 1 expected");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UpdateException(e.getMessage());
		}		
	}

	@Override
	public void deleteTeacher(Teacher teacher) throws NotFoundException,
			DeleteException {
		if(!teacherList.contains(teacher))
			throw new NotFoundException("Student not found for delete");
		try(PreparedStatement stm = connection.prepareStatement(Constants.DELETE_TEACHER))
		{
			stm.setInt(1, teacher.getId());
			int rows = stm.executeUpdate();
			if(rows != 1)
				throw new DeleteException("Error on delete teacher: " + rows + " rows affected, 1 expected");
			teacherList.remove(teacher);
		} catch (Exception e) {			
			e.printStackTrace();
			throw new DeleteException(e.getMessage());
		}
	}

}
