package panelTests;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.FileStudentManager;
import controller.SearchStudentController;

@SuppressWarnings("serial")
public class SearchStudentTestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchStudentTestFrame frame = new SearchStudentTestFrame();
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
	public SearchStudentTestFrame() throws IOException, Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new SearchStudentController(new FileStudentManager());		
		setContentPane(contentPane);
	}

}
