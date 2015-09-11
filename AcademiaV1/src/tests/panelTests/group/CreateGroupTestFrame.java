package tests.panelTests.group;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.group.CreateGroupController;
import controller.managers.StudentManager;
import controller.managers.TeacherManager;
import controller.managers.file.FileGroupManager;
import controller.managers.file.FileStudentManager;
import controller.managers.file.FileTeacherManager;

@SuppressWarnings("serial")
public class CreateGroupTestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateGroupTestFrame frame = new CreateGroupTestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public CreateGroupTestFrame() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		TeacherManager teacherManager = new FileTeacherManager();
		StudentManager studentManager = new FileStudentManager();
		contentPane = new CreateGroupController(new FileGroupManager(teacherManager, studentManager));
		setContentPane(contentPane);
	}

}
