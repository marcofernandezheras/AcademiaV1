package view;

import model.StudentManager;
import controller.SearchStudentController;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


@SuppressWarnings("serial")
public abstract class ViewStudentPanel extends SearchStudentController {
	protected JTextField txtDni;
	protected JTextField txtName;
	protected JTextField txtSurnames;
	protected JDatePickerImpl txtBornDate;

	/**
	 * Create the panel.
	 */
	public ViewStudentPanel(StudentManager studentManager) {
		super(studentManager);
		GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 20};
		
		JLabel lblDni = new JLabel("Dni");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.anchor = GridBagConstraints.EAST;
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.gridx = 1;
		gbc_lblDni.gridy = 4;
		add(lblDni, gbc_lblDni);
		
		txtDni = new JTextField();
		txtDni.setEditable(false);
		GridBagConstraints gbc_txtDni = new GridBagConstraints();
		gbc_txtDni.gridwidth = 2;
		gbc_txtDni.insets = new Insets(0, 0, 5, 5);
		gbc_txtDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDni.gridx = 2;
		gbc_txtDni.gridy = 4;
		add(txtDni, gbc_txtDni);
		txtDni.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 5;
		add(lblNombre, gbc_lblNombre);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.gridwidth = 2;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 5;
		add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 6;
		add(lblApellidos, gbc_lblApellidos);
		
		txtSurnames = new JTextField();
		txtSurnames.setEditable(false);
		GridBagConstraints gbc_txtSurnames = new GridBagConstraints();
		gbc_txtSurnames.gridwidth = 2;
		gbc_txtSurnames.insets = new Insets(0, 0, 5, 5);
		gbc_txtSurnames.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSurnames.gridx = 2;
		gbc_txtSurnames.gridy = 6;
		add(txtSurnames, gbc_txtSurnames);
		txtSurnames.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeNacimiento.gridx = 1;
		gbc_lblFechaDeNacimiento.gridy = 7;
		add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
		
		
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
		
		txtBornDate.getComponent(1).setEnabled(false);
		
		GridBagConstraints gbc_txtBornDate = new GridBagConstraints();
		gbc_txtBornDate.gridwidth = 2;
		gbc_txtBornDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtBornDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBornDate.gridx = 2;
		gbc_txtBornDate.gridy = 7;
		add(txtBornDate, gbc_txtBornDate);
	}

}
