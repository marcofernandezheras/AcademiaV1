package tests.panelTests.group;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.group.DeleteGroupController;
import controller.managers.FileGroupManager;
import controller.managers.FileStudentManager;
import controller.managers.FileTeacherManager;
import controller.managers.StudentManager;
import controller.managers.TeacherManager;

@SuppressWarnings("serial")
public class DeleteGroupTestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteGroupTestFrame frame = new DeleteGroupTestFrame();
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
	public DeleteGroupTestFrame() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		TeacherManager teacherManager = new FileTeacherManager();
		StudentManager studentManager = new FileStudentManager();
		contentPane = new DeleteGroupController(new FileGroupManager(teacherManager, studentManager));
		setContentPane(contentPane);
	}

}
