package tests.panelTests.teacher;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.managers.FileTeacherManager;
import controller.teacher.UpdateTeacherController;

@SuppressWarnings("serial")
public class UpdateTeachertestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateTeachertestFrame frame = new UpdateTeachertestFrame();
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
	public UpdateTeachertestFrame() throws IOException, Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new UpdateTeacherController(new FileTeacherManager());
		setContentPane(contentPane);
	}

}
