package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import model.Group;
import model.Student;
import model.Teacher;
import model.utils.Constants;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.managers.FileGroupManager;
import controller.managers.FileStudentManager;
import controller.managers.FileTeacherManager;
import controller.managers.StudentManager;
import controller.managers.TeacherManager;

public class FileGroupManagerTest {

	static TeacherManager teacherManager;
	static StudentManager studentManager;
	
	@BeforeClass
	public static void backupGroupsFile() throws IOException, Exception
	{
		FileTeacherManagerTest.backupTeacherFile();
		FileStudentManagerTest.backupStudentFile();
		
		File groupsFile = new File(Constants.GROUPS_FILE);
		if (groupsFile.exists())
			groupsFile.renameTo(new File("backup"+Constants.GROUPS_FILE));
		assertFalse(groupsFile.exists());
		
		teacherManager = new FileTeacherManager();
		studentManager = new FileStudentManager();
		
		teacherManager.createTeacher("teacherDni1", "teacherName1", "teacherSurname1");
		teacherManager.createTeacher("teacherDni2", "teacherName2", "teacherSurname2");
		teacherManager.createTeacher("teacherDni3", "teacherName3", "teacherSurname3");
		
		studentManager.createStudent("studentDni1", "studentName1", "studentSurname1");
		studentManager.createStudent("studentDni2", "studentName2", "studentSurname2");
		studentManager.createStudent("studentDni3", "studentName3", "studentSurname3");
	}			
	
	@Before
	@Test
	public void deleteGroupsFile()
	{
		File testFile = new File(Constants.GROUPS_FILE);
		if (testFile.exists())
			testFile.delete();
		assertFalse(testFile.exists());
	}
	
	@Test
	public void testGetAllGroups() {
		try {
			FileGroupManager manager = new FileGroupManager(teacherManager, studentManager);
			assertEquals(0, manager.getAllGroups().size());
			
			Teacher group1Teacher = teacherManager.getTeacher("teacherDni1");
			
			Group groupOne = manager.createGroup("group1", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupOne);
						
			assertEquals(1, manager.getAllGroups().size());
			
			manager.deleteGroup(groupOne);
			assertEquals(0, manager.getAllGroups().size());
			
			FileGroupManager managerTwo = new FileGroupManager(teacherManager, studentManager);
			assertEquals(0, managerTwo.getAllGroups().size());
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testCreateGroup() {
		try {
			FileGroupManager manager = new FileGroupManager(teacherManager, studentManager);
			assertEquals(0, manager.getAllGroups().size());
			
			Teacher group1Teacher = teacherManager.getTeacher("teacherDni1");
			
			Group groupOne = manager.createGroup("group1", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupOne);									
			assertEquals(1, manager.getAllGroups().size());
			
			assertEquals("group1", groupOne.getId());	
			
			assertEquals(studentManager.getAllStudents().size(), groupOne.getStudentsIds().size());
			for (Integer id : groupOne.getStudentsIds()) {
				boolean found = false;
				for (Student student : studentManager.getAllStudents()) {
					if(student.getId() == id)
						found = true;
				}
				assertTrue(found);
			}
			
			assertEquals(group1Teacher.getId(), groupOne.getIdTeacher());
			
			FileGroupManager managerTwo = new FileGroupManager(teacherManager, studentManager);
			assertEquals(1, managerTwo.getAllGroups().size());
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testUpdateGroup() {
		try {
			FileGroupManager manager = new FileGroupManager(teacherManager, studentManager);
			assertEquals(0, manager.getAllGroups().size());
			
			Teacher group1Teacher = teacherManager.getTeacher("teacherDni1");
			
			Group groupOne = manager.createGroup("group1", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupOne);						
			assertEquals(1, manager.getAllGroups().size());
			
			List<Student> newStudents = studentManager.getAllStudents().subList(0, 2);
			
			groupOne.getStudentsIds().clear();
			for (Student student : newStudents) {
				groupOne.getStudentsIds().add(student.getId());
			}
			
			manager.updateGroup(groupOne);	
			
			FileGroupManager managerTwo = new FileGroupManager(teacherManager, studentManager);
			assertEquals(1, managerTwo.getAllGroups().size());
			
			Group group = managerTwo.getAllGroups().get(0);
			
			for (Integer id : group.getStudentsIds()) {
				boolean found = false;
				for (Student student : newStudents) {
					if(student.getId() == id)
						found = true;
				}
				assertTrue(found);
			}
			
			assertEquals(group1Teacher.getId(), group.getIdTeacher());	
			assertEquals(group1Teacher.getId(), group.getIdTeacher());	
			
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeleteGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStudentsFromGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTeacherFromGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllGroupsByTeacher() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllGroupsByStudent() {
		fail("Not yet implemented");
	}

	@AfterClass
	public static void restoreStudentFile(){
		FileTeacherManagerTest.restoreTeacherFile();
		FileStudentManagerTest.restoreStudentFile();
		
		File groupsBackup = new File("backup"+Constants.GROUPS_FILE);
		if(groupsBackup.exists())
			groupsBackup.renameTo(new File(Constants.GROUPS_FILE));
	}
}
