package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Group;
import model.Student;
import model.Teacher;
import model.utils.Constants;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.managers.StudentManager;
import controller.managers.TeacherManager;
import controller.managers.file.FileGroupManager;
import controller.managers.file.FileStudentManager;
import controller.managers.file.FileTeacherManager;

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
			
			assertEquals("group1", groupOne.getName());	
			assertEquals(group1Teacher.getId(), groupOne.getIdTeacher());	
			
			assertEquals(studentManager.getAllStudents().size(), groupOne.getStudentsIds().size());
			for (Integer id : groupOne.getStudentsIds()) {
				boolean found = false;
				for (Student student : studentManager.getAllStudents()) {
					if(student.getId() == id)
						found = true;
				}
				assertTrue(found);
			}			
			
			FileGroupManager managerTwo = new FileGroupManager(teacherManager, studentManager);
			assertEquals(1, managerTwo.getAllGroups().size());
			
			Group groupTwo = managerTwo.getAllGroups().get(0);
			
			assertEquals("group1", groupTwo.getName());	
			assertEquals(group1Teacher.getId(), groupTwo.getIdTeacher());	
			
			assertEquals(studentManager.getAllStudents().size(), groupTwo.getStudentsIds().size());
			for (Integer id : groupTwo.getStudentsIds()) {
				boolean found = false;
				for (Student student : studentManager.getAllStudents()) {
					if(student.getId() == id)
						found = true;
				}
				assertTrue(found);
			}		
			
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
			
			groupOne.setName("group2");
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
			assertEquals("group2", group.getName());	
			
		} catch (Exception e) {		
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeleteGroup() {
		try {
			FileGroupManager manager = new FileGroupManager(teacherManager, studentManager);
			assertEquals(0, manager.getAllGroups().size());
			
			Teacher group1Teacher = teacherManager.getTeacher("teacherDni1");
			
			Group groupOne = manager.createGroup("group1", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupOne);									
			assertEquals(1, manager.getAllGroups().size());
			
			Group groupTwo = manager.createGroup("group2", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupTwo);									
			assertEquals(2, manager.getAllGroups().size());
			
			manager.deleteGroup(groupOne);
			assertEquals(1, manager.getAllGroups().size());
			
			FileGroupManager manageTwo = new FileGroupManager(teacherManager, studentManager);
			assertEquals(1, manageTwo.getAllGroups().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetStudentsFromGroup() {
		try {
			FileGroupManager manager = new FileGroupManager(teacherManager, studentManager);
			assertEquals(0, manager.getAllGroups().size());
			
			Teacher group1Teacher = teacherManager.getTeacher("teacherDni1");
			
			Group groupOne = manager.createGroup("group1", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupOne);									
			assertEquals(1, manager.getAllGroups().size());
			
			FileGroupManager manageTwo = new FileGroupManager(teacherManager, studentManager);
			Group group = manageTwo.getAllGroups().get(0);
			
			List<Student> studentsFromGroup = manageTwo.getStudentsFromGroup(group);
			for (Student student : studentsFromGroup) {
				boolean found = false;
				for (Student s : studentManager.getAllStudents()) {
					if(s.equals(student))
						found = true;
				}
				assertTrue(found);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetTeacherFromGroup() {
		try {
			FileGroupManager manager = new FileGroupManager(teacherManager, studentManager);
			assertEquals(0, manager.getAllGroups().size());
			
			Teacher group1Teacher = teacherManager.getTeacher("teacherDni1");
			
			Group groupOne = manager.createGroup("group1", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupOne);									
			assertEquals(1, manager.getAllGroups().size());
			
			FileGroupManager manageTwo = new FileGroupManager(teacherManager, studentManager);
			Group group = manageTwo.getAllGroups().get(0);
			
			assertEquals(group1Teacher, manager.getTeacherFromGroup(group));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		} 
	}

	@Test
	public void testGetAllGroupsByTeacher() {
		try {
			FileGroupManager manager = new FileGroupManager(teacherManager, studentManager);
			assertEquals(0, manager.getAllGroups().size());
			
			Teacher group1Teacher = teacherManager.getTeacher("teacherDni1");
			
			Group groupOne = manager.createGroup("group1", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupOne);									
			assertEquals(1, manager.getAllGroups().size());
			
			Group groupTwo = manager.createGroup("group2", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupTwo);									
			assertEquals(2, manager.getAllGroups().size());
			
			List<Group> allGroupsByTeacher = manager.getAllGroupsByTeacher(group1Teacher);
			assertEquals(2, allGroupsByTeacher.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetAllGroupsByStudent() {
		try {
			FileGroupManager manager = new FileGroupManager(teacherManager, studentManager);
			assertEquals(0, manager.getAllGroups().size());
			
			Teacher group1Teacher = teacherManager.getTeacher("teacherDni1");
			
			Group groupOne = manager.createGroup("group1", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupOne);									
			assertEquals(1, manager.getAllGroups().size());
			
			Group groupTwo = manager.createGroup("group2", group1Teacher , studentManager.getAllStudents());
			assertNotNull(groupTwo);									
			assertEquals(2, manager.getAllGroups().size());
			
			List<Group> allGroupsByStudent = manager.getAllGroupsByStudent(studentManager.getAllStudents().get(0));
			assertEquals(2, allGroupsByStudent.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
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
