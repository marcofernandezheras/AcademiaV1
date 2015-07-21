package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import controller.managers.FileStudentManager;
import controller.managers.FileTeacherManager;
import controller.managers.StudentManager;
import controller.managers.TeacherManager;
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
	
	public MainWindowController() throws IOException, Exception {
		super();
		studentManager = new FileStudentManager();
		teacherManager = new FileTeacherManager();
		bindEvents();
	}
	
	private void bindEvents() {
		studentsMenuEvents();
		teachersMenuEvents();
	}

	private void studentsMenuEvents() {
		mntmCreateStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePanel(new CreateStudentController(studentManager));
			}
		});
		
		mntmDeleteStudent.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				changePanel(new DeleteStudentController(studentManager));
			}
		});
		
		mntmUpdateStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePanel(new UpdateStudentController(studentManager));
			}
		});
		
		mntmViewStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePanel(new ViewStudentController(studentManager));
			}
		});
	}

	private void teachersMenuEvents() {
		mntmCreateTeacher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePanel(new CreateTeacherController(teacherManager));
			}
		});
		
		mntmDeleteTeacher.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				changePanel(new DeleteTeacherController(teacherManager));
			}
		});
		
		mntmUpdateTeacher.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				changePanel(new UpdateTeacherController(teacherManager));
			}
		});
		
		mntmViewTeacher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePanel(new ViewTeacherController(teacherManager));
			}
		});
	}
	
	private void changePanel(JPanel newPanel)
	{
		contentPane = null;
		contentPane = newPanel;	
		this.setContentPane(contentPane);
		this.validate();
	}

}
