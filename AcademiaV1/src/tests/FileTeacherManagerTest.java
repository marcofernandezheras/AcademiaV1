package tests;

import static org.junit.Assert.*;

import java.io.File;

import model.Teacher;
import model.utils.Constants;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.managers.FileTeacherManager;

public class FileTeacherManagerTest {

	@BeforeClass
	public static void backupTeacherFile()
	{
		File testFile = new File(Constants.TEACHERS_FILE);
		if (testFile.exists())
			testFile.renameTo(new File("backup"+Constants.TEACHERS_FILE));
		assertFalse(testFile.exists());
	}			
	
	@Before
	@Test
	public void deleteTeacherFile()
	{
		File testFile = new File(Constants.TEACHERS_FILE);
		if (testFile.exists())
			testFile.delete();
		assertFalse(testFile.exists());
	}
	
	@Test
	public void testGetAllTeachers() {
		try {
			FileTeacherManager manager = new FileTeacherManager();
			assertEquals(0, manager.getAllTeachers().size());
			
			Teacher teacher = manager.createTeacher("dni", "name", "surnames");
			assertNotNull(teacher);
			
			assertEquals(1, manager.getAllTeachers().size());
			
			manager.deleteTeacher(teacher);
			assertEquals(0, manager.getAllTeachers().size());
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetTeacherInt() {
		try {
			FileTeacherManager manager = new FileTeacherManager();
			assertEquals(0, manager.getAllTeachers().size());
			
			Teacher teacher = manager.createTeacher("dni", "name", "surnames");
			assertNotNull(teacher);
			
			assertNotNull(manager.getTeacher(teacher.getId()));
			assertEquals(teacher, manager.getTeacher(teacher.getId()));
			
			manager.deleteTeacher(teacher);
			assertNull(manager.getTeacher(teacher.getId()));
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetTeacherString() {
		try {
			FileTeacherManager manager = new FileTeacherManager();
			assertEquals(0, manager.getAllTeachers().size());
			
			Teacher teacher = manager.createTeacher("dni", "name", "surnames");
			assertNotNull(teacher);
			
			assertNotNull(manager.getTeacher(teacher.getDni()));
			assertEquals(teacher, manager.getTeacher(teacher.getDni()));
			
			manager.deleteTeacher(teacher);
			assertNull(manager.getTeacher(teacher.getDni()));
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testCreateTeacher() {
		try {
			FileTeacherManager manager = new FileTeacherManager();
			assertEquals(0, manager.getAllTeachers().size());

			Teacher teacher = manager.createTeacher("dni", "name", "surnames");
			assertNotNull(teacher);
			assertEquals(1, manager.getAllTeachers().size());
			
			Teacher secondTeacher = manager.createTeacher("dni", "name", "surnames");
			assertNotNull(secondTeacher);
			assertEquals(2, manager.getAllTeachers().size());
			assertEquals(teacher.getId() + 1, secondTeacher.getId());

			FileTeacherManager secondManager = new FileTeacherManager();
			assertEquals(2, secondManager.getAllTeachers().size());
			
			Teacher thirdTeacher = secondManager.createTeacher("dni", "name", "surnames");
			assertNotNull(thirdTeacher);
			assertEquals(3, secondManager.getAllTeachers().size());
			assertEquals(secondTeacher.getId() + 1, thirdTeacher.getId());
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testUpdateTeacher() {
		try {
			FileTeacherManager manager = new FileTeacherManager();
			assertEquals(0, manager.getAllTeachers().size());
			
			Teacher teacher = manager.createTeacher("dni", "name", "surnames");
			assertNotNull(teacher);
			teacher.setName("name changed");
			
			manager.updateTeacher(teacher);
			
			assertNotNull(manager.getTeacher(teacher.getDni()));
			assertEquals(teacher, manager.getTeacher(teacher.getDni()));
			
			FileTeacherManager secondManager = new FileTeacherManager();
			assertEquals(1, secondManager.getAllTeachers().size());
			
			assertNotNull(manager.getTeacher(teacher.getDni()));
			assertEquals(teacher, manager.getTeacher(teacher.getDni()));
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeleteTeacher() {
		try {
			FileTeacherManager manager = new FileTeacherManager();
			assertEquals(0, manager.getAllTeachers().size());
			
			Teacher teacher = manager.createTeacher("dni", "name", "surnames");
			assertNotNull(teacher);
			
			assertEquals(1, manager.getAllTeachers().size());
			manager.deleteTeacher(teacher);
			assertEquals(0, manager.getAllTeachers().size());
			
			FileTeacherManager secondManager = new FileTeacherManager();
			assertEquals(0, secondManager.getAllTeachers().size());
			
			FileTeacherManager thirdManager = new FileTeacherManager();
			assertEquals(0, thirdManager.getAllTeachers().size());
			
			Teacher newTeacher = thirdManager.createTeacher("dni", "name", "surnames");
			assertNotNull(newTeacher);
			Teacher anotherTeacher = thirdManager.createTeacher("dni", "name", "surnames");
			assertNotNull(anotherTeacher);
			
			thirdManager.deleteTeacher(newTeacher);
			assertEquals(1, thirdManager.getAllTeachers().size());
			
			FileTeacherManager forthManager = new FileTeacherManager();
			assertEquals(1, forthManager.getAllTeachers().size());
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@AfterClass
	public static void restoreTeacherFile(){
		File backup = new File("backup"+Constants.TEACHERS_FILE);
		if(backup.exists())
			backup.renameTo(new File(Constants.TEACHERS_FILE));
	}
}
