package panelTests.teacher;


import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.FileTeacherManager;
import controller.teacher.DeleteTeacherController;

@SuppressWarnings("serial")
public class DeleteTeacherTestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteTeacherTestFrame frame = new DeleteTeacherTestFrame();
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
	public DeleteTeacherTestFrame() throws IOException, Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new DeleteTeacherController(new FileTeacherManager());
		setContentPane(contentPane);
	}

}
