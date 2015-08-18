package tests.panelTests.group;


import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.group.UpdateGroupController;
import controller.managers.FileGroupManager;
import controller.managers.FileStudentManager;
import controller.managers.FileTeacherManager;
import controller.managers.StudentManager;
import controller.managers.TeacherManager;

@SuppressWarnings("serial")
public class UpdateGroupTestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateGroupTestFrame frame = new UpdateGroupTestFrame();
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
	 * @throws IOException 
	 */
	public UpdateGroupTestFrame() throws IOException, Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		TeacherManager teacherManager = new FileTeacherManager();
		StudentManager studentManager = new FileStudentManager();
		contentPane = new UpdateGroupController(new FileGroupManager(teacherManager, studentManager));
		setContentPane(contentPane);
	}

}
