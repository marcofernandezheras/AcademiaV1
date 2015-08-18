package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public abstract class MainWindow extends JFrame {

	protected JPanel contentPane;
	protected JMenuItem mntmDeleteStudent;
	protected JMenuItem mntmUpdateStudent;
	protected JMenuItem mntmViewStudent;
	protected JMenuItem mntmCreateStudent;	
	protected JMenu mnProfesores;
	protected JMenuItem mntmCreateTeacher;
	protected JMenuItem mntmViewTeacher;
	protected JMenuItem mntmUpdateTeacher;
	protected JMenuItem mntmDeleteTeacher;
	protected JMenu mnGrupos;
	protected JMenuItem mntmCreateGroup;
	protected JMenuItem mntmViewGroup;
	protected JMenuItem mntmUpdateGroup;
	protected JMenuItem mntmDeleteGroup;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnStudents = new JMenu("Alumnos");
		menuBar.add(mnStudents);
		
		mntmCreateStudent = new JMenuItem("Crear");
		mnStudents.add(mntmCreateStudent);
		
		mntmViewStudent = new JMenuItem("Consultar");
		mnStudents.add(mntmViewStudent);
		
		mntmUpdateStudent = new JMenuItem("Modificar");
		mnStudents.add(mntmUpdateStudent);
		
		mntmDeleteStudent = new JMenuItem("Borrar");
		mnStudents.add(mntmDeleteStudent);
		
		mnProfesores = new JMenu("Profesores");
		menuBar.add(mnProfesores);
		
		mntmCreateTeacher = new JMenuItem("Crear");
		mnProfesores.add(mntmCreateTeacher);
		
		mntmViewTeacher = new JMenuItem("Consultar");
		mnProfesores.add(mntmViewTeacher);
		
		mntmUpdateTeacher = new JMenuItem("Modificar");
		mnProfesores.add(mntmUpdateTeacher);
		
		mntmDeleteTeacher = new JMenuItem("Borrar");
		mnProfesores.add(mntmDeleteTeacher);
		
		mnGrupos = new JMenu("Grupos");
		menuBar.add(mnGrupos);
		
		mntmCreateGroup = new JMenuItem("Crear");
		mnGrupos.add(mntmCreateGroup);
		
		mntmViewGroup = new JMenuItem("Consultar");
		mnGrupos.add(mntmViewGroup);
		
		mntmUpdateGroup = new JMenuItem("Modificar");
		mnGrupos.add(mntmUpdateGroup);
		
		mntmDeleteGroup = new JMenuItem("Borrar");
		mnGrupos.add(mntmDeleteGroup);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
