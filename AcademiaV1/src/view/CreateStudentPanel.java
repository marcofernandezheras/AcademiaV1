package view;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")
public abstract class CreateStudentPanel extends JPanel {
	protected JTextField txtName;
	protected JTextField txtSurnames;
	protected JDatePickerImpl txtBornDate;
	protected JTextField txtDni;
	protected JButton btnCreate;

	/**
	 * Create the panel.
	 */
	public CreateStudentPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 20, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblCrearAlumno = new JLabel("Crear Alumno");
		GridBagConstraints gbc_lblCrearAlumno = new GridBagConstraints();
		gbc_lblCrearAlumno.gridwidth = 2;
		gbc_lblCrearAlumno.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrearAlumno.gridx = 1;
		gbc_lblCrearAlumno.gridy = 1;
		add(lblCrearAlumno, gbc_lblCrearAlumno);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 3;
		add(lblNombre, gbc_lblNombre);
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 3;
		add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 4;
		add(lblApellidos, gbc_lblApellidos);
		
		txtSurnames = new JTextField();
		GridBagConstraints gbc_txtSurnames = new GridBagConstraints();
		gbc_txtSurnames.insets = new Insets(0, 0, 5, 5);
		gbc_txtSurnames.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSurnames.gridx = 2;
		gbc_txtSurnames.gridy = 4;
		add(txtSurnames, gbc_txtSurnames);
		txtSurnames.setColumns(10);
		
		JLabel lblDni = new JLabel("Dni");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.anchor = GridBagConstraints.EAST;
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.gridx = 1;
		gbc_lblDni.gridy = 5;
		add(lblDni, gbc_lblDni);
		
		txtDni = new JTextField();
		GridBagConstraints gbc_txtDni = new GridBagConstraints();
		gbc_txtDni.insets = new Insets(0, 0, 5, 5);
		gbc_txtDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDni.gridx = 2;
		gbc_txtDni.gridy = 5;
		add(txtDni, gbc_txtDni);
		txtDni.setColumns(10);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
		gbc_lblFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimiento.gridx = 1;
		gbc_lblFechaNacimiento.gridy = 6;
		add(lblFechaNacimiento, gbc_lblFechaNacimiento);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Hoy");
		p.put("text.month", "Mes");
		p.put("text.year", "Año");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		
		txtBornDate = new JDatePickerImpl(datePanel, new AbstractFormatter() {
			
			private String datePattern = "dd/MM/yyyy";
		    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		    @Override
		    public Object stringToValue(String text) throws ParseException {
		        return dateFormatter.parseObject(text);
		    }

		    @Override
		    public String valueToString(Object value) throws ParseException {
		        if (value != null) {
		            Calendar cal = (Calendar) value;
		            return dateFormatter.format(cal.getTime());
		        }

		        return "";
		    }
		});
		
		GridBagConstraints gbc_txtBornDate = new GridBagConstraints();
		gbc_txtBornDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtBornDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBornDate.gridx = 2;
		gbc_txtBornDate.gridy = 6;
		add(txtBornDate, gbc_txtBornDate);
		
		btnCreate = new JButton("Crear");
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.anchor = GridBagConstraints.EAST;
		gbc_btnCreate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreate.gridx = 2;
		gbc_btnCreate.gridy = 7;
		add(btnCreate, gbc_btnCreate);

	}

}
