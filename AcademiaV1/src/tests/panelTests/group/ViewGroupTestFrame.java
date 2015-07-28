package tests.panelTests.group;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.group.ViewGroupController;
import controller.managers.FileGroupManager;
import controller.managers.FileStudentManager;
import controller.managers.FileTeacherManager;
import controller.managers.StudentManager;
import controller.managers.TeacherManager;

@SuppressWarnings("serial")
public class ViewGroupTestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewGroupTestFrame frame = new ViewGroupTestFrame();
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
	public ViewGroupTestFrame() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		TeacherManager teacherManager = new FileTeacherManager();
		StudentManager studentManager = new FileStudentManager();
		contentPane = new ViewGroupController(new FileGroupManager(teacherManager, studentManager));
		setContentPane(contentPane);
	}

}
