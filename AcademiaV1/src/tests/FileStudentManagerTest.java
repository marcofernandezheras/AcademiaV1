package tests;

import static org.junit.Assert.*;

import java.io.File;

import model.Constants;
import model.FileStudentManager;
import model.Student;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileStudentManagerTest {

	@BeforeClass
	public static void backupStudentFile()
	{
		File testFile = new File(Constants.STUDENTS_FILE);
		if (testFile.exists())
			testFile.renameTo(new File("backup"+Constants.STUDENTS_FILE));
		assertFalse(testFile.exists());
	}			
	
	@Before
	@Test
	public void deleteStudentFile()
	{
		File testFile = new File(Constants.STUDENTS_FILE);
		if (testFile.exists())
			testFile.delete();
		assertFalse(testFile.exists());
	}
	
	@Test
	public void testGetAllStudents() {
		try {
			FileStudentManager manager = new FileStudentManager();
			assertEquals(0, manager.getAllStudents().size());
			
			Student student = manager.createStudent("dni", "name", "surnames");
			assertNotNull(student);
			
			assertEquals(1, manager.getAllStudents().size());
			
			manager.deleteStudent(student);
			assertEquals(0, manager.getAllStudents().size());
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetStudentInt() {
		try {
			FileStudentManager manager = new FileStudentManager();
			assertEquals(0, manager.getAllStudents().size());
			
			Student student = manager.createStudent("dni", "name", "surnames");
			assertNotNull(student);
			
			assertNotNull(manager.getStudent(student.getId()));
			assertEquals(student, manager.getStudent(student.getId()));
			
			manager.deleteStudent(student);
			assertNull(manager.getStudent(student.getId()));
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetStudentString() {
		try {
			FileStudentManager manager = new FileStudentManager();
			assertEquals(0, manager.getAllStudents().size());
			
			Student student = manager.createStudent("dni", "name", "surnames");
			assertNotNull(student);
			
			assertNotNull(manager.getStudent(student.getDni()));
			assertEquals(student, manager.getStudent(student.getDni()));
			
			manager.deleteStudent(student);
			assertNull(manager.getStudent(student.getDni()));
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testCreateStudent() {
		try {
			FileStudentManager manager = new FileStudentManager();
			assertEquals(0, manager.getAllStudents().size());

			Student student = manager.createStudent("dni", "name", "surnames");
			assertNotNull(student);
			assertEquals(1, manager.getAllStudents().size());
			
			Student secondStudent = manager.createStudent("dni", "name", "surnames");
			assertNotNull(secondStudent);
			assertEquals(2, manager.getAllStudents().size());
			assertEquals(student.getId() + 1, secondStudent.getId());

			FileStudentManager secondManager = new FileStudentManager();
			assertEquals(2, secondManager.getAllStudents().size());
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
		
	}

	@Test
	public void testUpdateStudent() {
		try {
			FileStudentManager manager = new FileStudentManager();
			assertEquals(0, manager.getAllStudents().size());
			
			Student student = manager.createStudent("dni", "name", "surnames");
			assertNotNull(student);
			student.setName("name changed");
			
			manager.updateStudent(student);
			
			assertNotNull(manager.getStudent(student.getDni()));
			assertEquals(student, manager.getStudent(student.getDni()));
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeleteStudent() {
		try {
			FileStudentManager manager = new FileStudentManager();
			assertEquals(0, manager.getAllStudents().size());
			
			Student student = manager.createStudent("dni", "name", "surnames");
			assertNotNull(student);
			
			assertEquals(1, manager.getAllStudents().size());
			manager.deleteStudent(student);
			assertEquals(0, manager.getAllStudents().size());
			
			FileStudentManager secondManager = new FileStudentManager();
			assertEquals(0, secondManager.getAllStudents().size());
			
			FileStudentManager thirdManager = new FileStudentManager();
			assertEquals(0, thirdManager.getAllStudents().size());
			
			Student newstudent = thirdManager.createStudent("dni", "name", "surnames");
			assertNotNull(newstudent);
			Student anotherStudent = thirdManager.createStudent("dni", "name", "surnames");
			assertNotNull(anotherStudent);
			
			thirdManager.deleteStudent(newstudent);
			assertEquals(1, thirdManager.getAllStudents().size());
			
			FileStudentManager forthManager = new FileStudentManager();
			assertEquals(1, forthManager.getAllStudents().size());
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}
	
	@AfterClass
	public static void restoreStudentFile(){
		File backup = new File("backup"+Constants.STUDENTS_FILE);
		if(backup.exists())
			backup.renameTo(new File(Constants.STUDENTS_FILE));
	}

}
