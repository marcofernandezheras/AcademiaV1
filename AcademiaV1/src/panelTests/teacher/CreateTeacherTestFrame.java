package panelTests.teacher;


import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


import model.FileTeacherManager;
import controller.teacher.CreateTeacherController;

@SuppressWarnings("serial")
public class CreateTeacherTestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTeacherTestFrame frame = new CreateTeacherTestFrame();
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
	public CreateTeacherTestFrame() throws IOException, Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new CreateTeacherController(new FileTeacherManager());
		setContentPane(contentPane);
	}

}
