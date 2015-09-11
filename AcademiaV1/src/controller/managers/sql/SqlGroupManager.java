package controller.managers.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.managers.AbstractGroupManager;
import controller.managers.StudentManager;
import controller.managers.TeacherManager;
import model.Group;
import model.Student;
import model.Teacher;
import model.exceptions.crud.CreateException;
import model.exceptions.crud.DeleteException;
import model.exceptions.crud.NotFoundException;
import model.exceptions.crud.UpdateException;
import model.utils.Constants;
import model.utils.SqlConnectionManager;

public class SqlGroupManager extends AbstractGroupManager{
	
	public SqlGroupManager(TeacherManager teacherManager,
			StudentManager studentManager) {
		super(teacherManager, studentManager);
		Connection connection = SqlConnectionManager.getConnection();
		try (PreparedStatement stm = connection.prepareStatement(Constants.SELECT_ALL_GROUPS);
				ResultSet rs = stm.executeQuery();){
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int idTeacher = rs.getInt("idteacher");
				List<Integer> studentList = new ArrayList<Integer>();
				try(PreparedStatement stmStudent = connection.prepareStatement(Constants.SELECT_STUDENT_FROM_GROUP)){
					stmStudent.setInt(1, id);
					ResultSet students = stmStudent.executeQuery();
					while(students.next())
					{
						studentList.add(students.getInt("idstudent"));
					}
					students.close();
				}
				groupList.add(new Group(id, name, idTeacher, studentList));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Group createGroup(String groupName, Teacher teacher,
			List<Student> students) throws CreateException {
		Connection connection = SqlConnectionManager.getConnection();
		try(PreparedStatement insertGroup = connection.prepareStatement(Constants.INSERT_GROUP, PreparedStatement.RETURN_GENERATED_KEYS)){
			SqlConnectionManager.startTransaccion();
			insertGroup.setString(1, groupName);
			insertGroup.setInt(2, teacher.getId());
			int rowcount = insertGroup.executeUpdate();
			if(rowcount == 1)
			{
				int idGroup = 0;
				ResultSet generatedKeys = insertGroup.getGeneratedKeys();
				if(generatedKeys.next())
					idGroup = generatedKeys.getInt(1);
				generatedKeys.close();
				
				//Insert Students on studentgroup table			
				List<Integer> studentsIds = translateStudendList(students);
				insertGroupStudents(connection, studentsIds,idGroup);
				
				Group group = new Group(idGroup, groupName, teacher.getId(), studentsIds);
				groupList.add(group);
				SqlConnectionManager.commit();
				return group;
			}
			else
				throw new CreateException("Too many rows affected on insert group "+rowcount+" vs 1");
		} catch (Exception e) {
			e.printStackTrace();
			SqlConnectionManager.rollback();
			throw new CreateException(e.getMessage());
		}
	}

	protected void insertGroupStudents(Connection connection, List<Integer> studentsIds,
			int idGroup) throws SQLException, CreateException {		
		for (Integer idStudent : studentsIds) {
			try(PreparedStatement insertStudent = connection.prepareStatement(Constants.INSERT_GROUP_STUDENT)){
				insertStudent.setInt(1, idGroup);
				insertStudent.setInt(2, idStudent);
				int studentRowCount = insertStudent.executeUpdate();
				if(studentRowCount != 1)
					throw new CreateException("Too many rows affected on insert student "+studentRowCount+" vs 1");
			}
		}
	}

	@Override
	public void updateGroup(Group group) throws NotFoundException,
			UpdateException {
		if(!groupList.contains(group))
			throw new NotFoundException("Group not found for update");
		Connection connection = SqlConnectionManager.getConnection();
		SqlConnectionManager.startTransaccion();
		try(PreparedStatement deleteStudents = connection.prepareStatement(Constants.DELETE_STUDENTS_FROM_GROUP)){
			deleteStudents.setInt(1, group.getId());
			deleteStudents.executeUpdate();
			try (PreparedStatement updateGroup = connection.prepareStatement(Constants.UPDATE_GROUP)){
				updateGroup.setString(1, group.getName());
				updateGroup.setInt(2, group.getIdTeacher());
				updateGroup.setInt(3, group.getId());
				int groupRow = updateGroup.executeUpdate();
				if(groupRow == 1){
					//Insert students back to group
					insertGroupStudents(connection, group.getStudentsIds(), group.getId());
					SqlConnectionManager.commit();
				}
				else {
					throw new UpdateException("Too many rows affected on update group "+groupRow+" vs 1");
				}
			} 
		} catch (Exception e) {			
			e.printStackTrace();
			SqlConnectionManager.rollback();
			throw new UpdateException(e.getMessage());
		}
	}

	@Override
	public void deleteGroup(Group group) throws NotFoundException,
			DeleteException {
		if(!groupList.contains(group))
			throw new NotFoundException("Group not found for delete");
		
		Connection connection = SqlConnectionManager.getConnection();
		SqlConnectionManager.startTransaccion();
		try(PreparedStatement deleteGroup = connection.prepareStatement(Constants.DELETE_GROUP)){
			deleteGroup.setInt(1, group.getId());
			int rows = deleteGroup.executeUpdate();
			if(rows == 1){
				groupList.remove(group);
				SqlConnectionManager.commit();
			}		
			else {
				throw new DeleteException("Too many rows affected on delete group "+rows+" vs 1");
			}				
		} catch (Exception e) {
			e.printStackTrace();
			SqlConnectionManager.rollback();
			throw new DeleteException(e.getMessage());
		}
	}

}
