package controller;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JPanel;

import controller.group.CreateGroupController;
import controller.group.DeleteGroupController;
import controller.group.UpdateGroupController;
import controller.group.ViewGroupController;
import controller.managers.GroupManager;
import controller.managers.StudentManager;
import controller.managers.TeacherManager;
import controller.managers.sql.SqlGroupManager;
import controller.managers.sql.SqlStudentManager;
import controller.managers.sql.SqlTeacherManager;
import controller.student.CreateStudentController;
import controller.student.DeleteStudentController;
import controller.student.UpdateStudentController;
import controller.student.ViewStudentController;
import controller.teacher.CreateTeacherController;
import controller.teacher.DeleteTeacherController;
import controller.teacher.UpdateTeacherController;
import controller.teacher.ViewTeacherController;
import view.MainWindow;

@SuppressWarnings("serial")
public class MainWindowController extends MainWindow {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowController frame = new MainWindowController();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	private JPanel contentPane;
	private final StudentManager studentManager;
	private final TeacherManager teacherManager;
	private final GroupManager groupManager;
	
	public MainWindowController() throws IOException, Exception {
		super();
		studentManager = new SqlStudentManager();
		teacherManager = new SqlTeacherManager();
		groupManager = new SqlGroupManager(teacherManager, studentManager);
		bindEvents();
	}
	
	private void bindEvents() {
		studentsMenuEvents();
		teachersMenuEvents();
		groupMenuEvents();
	}

	private void studentsMenuEvents() {
		mntmCreateStudent.addActionListener(e -> changePanel(new CreateStudentController(studentManager)));		
		mntmDeleteStudent.addActionListener(e -> changePanel(new DeleteStudentController(studentManager)));
		mntmUpdateStudent.addActionListener(e -> changePanel(new UpdateStudentController(studentManager)));
		mntmViewStudent.addActionListener(e -> changePanel(new ViewStudentController(studentManager)));
	}

	private void teachersMenuEvents() {
		mntmCreateTeacher.addActionListener(e -> changePanel(new CreateTeacherController(teacherManager)));
		mntmDeleteTeacher.addActionListener(e -> changePanel(new DeleteTeacherController(teacherManager)));
		mntmUpdateTeacher.addActionListener(e -> changePanel(new UpdateTeacherController(teacherManager)));
		mntmViewTeacher.addActionListener(e -> changePanel(new ViewTeacherController(teacherManager)));
	}

	protected void groupMenuEvents() {
		mntmCreateGroup.addActionListener(e -> changePanel(new CreateGroupController(groupManager)));
		mntmViewGroup.addActionListener(e -> changePanel(new ViewGroupController(groupManager)));
		mntmUpdateGroup.addActionListener(e -> changePanel(new UpdateGroupController(groupManager)));
		mntmDeleteGroup.addActionListener(e -> changePanel(new DeleteGroupController(groupManager)));
	}
	
	private void changePanel(JPanel newPanel)
	{
		contentPane = null;
		contentPane = newPanel;	
		this.setContentPane(contentPane);
		this.validate();
	}

}
