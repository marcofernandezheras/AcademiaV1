package model.utils;

public class Constants {
	private Constants(){}
	
	//------------------------- FILES -------------------------------------------------------
	public static final String STUDENTS_FILE = "students.dat";
	public static final String TEACHERS_FILE = "teachers.dat";
	public static final String GROUPS_FILE = "groups.dat";
	
	//--------------------------- STUDENTS SQL ----------------------------------------------
	public static final String SELECT_ALL_STUDENTS = "Select * from student";
	
	public static final String INSERT_STUDENT = "INSERT INTO `student`" +
												"(`dni`," 				+
												"`name`," 				+
												"`surnames`," 			+
												"`bornDate`," 			+
												"`comments`)" 			+
												"VALUES (?,?,?,?,?);";
	
	public static final String UPDATE_STUDENT = "UPDATE `student`"	+
												"SET"				+
												"`dni` = ?,"		+
												"`name` = ?,"		+
												"`surnames` = ?,"	+
												"`bornDate` = ?,"	+
												"`comments` = ?"	+
												"WHERE `id` = ?;"	;
	
	public static final String DELETE_STUDENT = "DELETE FROM `student` WHERE `id` = ?;";
	
	//--------------------------- TEACHERS SQL ----------------------------------------------
	public static final String SELECT_ALL_TEACHERS = "SELECT * FROM teacher;";
	
	public static final String INSERT_TEACHER = "INSERT INTO `teacher` " +
												"(`dni`," 				 +
												"`name`," 				 +
												"`surnames`," 			 +
												"`bornDate`," 			 +
												"`salary`)" 			 +
												"VALUES" 				 +
												"(?,?,?,?,?);";
	
	public static final String UPDATE_TEACHER = "UPDATE `teacher`"  +
												"SET" 				+
												"`dni` = ?," 		+
												"`name` = ?," 		+
												"`surnames` = ?," 	+
												"`bornDate` = ?," 	+
												"`salary` = ?" 		+
												" WHERE `id` = ?;" ;
	
	public static final String DELETE_TEACHER = "DELETE FROM `teacher` WHERE id = ?;";
	
	//--------------------------- GROUPS SQL ----------------------------------------------
	public static final String SELECT_ALL_GROUPS = "SELECT * FROM `group`;";
	public static final String SELECT_STUDENT_FROM_GROUP = "SELECT * FROM studentgroup where idgroup = ? ;";
	
	public static final String INSERT_GROUP = "INSERT INTO `group` (`name`,`idteacher`) VALUES (?,?);";
	public static final String INSERT_GROUP_STUDENT = "INSERT INTO `studentgroup` (`idgroup`,`idstudent`) VALUES (?,?);";
	
	public static final String UPDATE_GROUP = "UPDATE `group` SET `name` = ?, `idteacher` = ? WHERE `id` = ?;";
	
	public static final String DELETE_GROUP = "DELETE FROM `group` WHERE id = ?;";
	public static final String DELETE_STUDENTS_FROM_GROUP = "DELETE FROM `studentgroup` WHERE idgroup = ?;";
}
