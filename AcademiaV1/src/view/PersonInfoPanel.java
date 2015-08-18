package view;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")
public abstract class PersonInfoPanel extends JPanel {
	protected JTextField txtDni;
	protected JTextField txtName;
	protected JTextField txtSurnames;
	protected JDatePickerImpl txtBornDate;

	protected GridBagLayout gridBagLayout;
	/**
	 * Create the panel.
	 */
	public PersonInfoPanel() {
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblDni = new JLabel("Dni");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.anchor = GridBagConstraints.EAST;
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.gridx = 1;
		gbc_lblDni.gridy = 1;
		add(lblDni, gbc_lblDni);
		
		txtDni = new JTextField();
		GridBagConstraints gbc_txtDni = new GridBagConstraints();
		gbc_txtDni.insets = new Insets(0, 0, 5, 5);
		gbc_txtDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDni.gridx = 2;
		gbc_txtDni.gridy = 1;
		add(txtDni, gbc_txtDni);
		txtDni.setColumns(10);
		
		JLabel lblNomnre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNomnre = new GridBagConstraints();
		gbc_lblNomnre.anchor = GridBagConstraints.EAST;
		gbc_lblNomnre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomnre.gridx = 1;
		gbc_lblNomnre.gridy = 2;
		add(lblNomnre, gbc_lblNomnre);
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 2;
		add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 3;
		add(lblApellidos, gbc_lblApellidos);
		
		txtSurnames = new JTextField();
		GridBagConstraints gbc_txtSurnames = new GridBagConstraints();
		gbc_txtSurnames.insets = new Insets(0, 0, 5, 5);
		gbc_txtSurnames.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSurnames.gridx = 2;
		gbc_txtSurnames.gridy = 3;
		add(txtSurnames, gbc_txtSurnames);
		txtSurnames.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeNacimiento.gridx = 1;
		gbc_lblFechaDeNacimiento.gridy = 4;
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
		GridBagConstraints gbc_txtBornDate = new GridBagConstraints();
		gbc_txtBornDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtBornDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBornDate.gridx = 2;
		gbc_txtBornDate.gridy = 4;
		add(txtBornDate, gbc_txtBornDate);
	}
	
	protected void setEnabledDateWidget(boolean state){
		txtBornDate.getComponent(1).setEnabled(state);
	}

	protected boolean dataValid(){
		boolean valid = !txtName.getText().isEmpty();
		valid &= !txtSurnames.getText().isEmpty();
		valid &= !txtDni.getText().isEmpty();
		Object date = txtBornDate.getModel().getValue();
		valid &= (date != null) && (date instanceof Date);		
		return valid;
	}
	
	protected void clearUI(){
		txtName.setText("");
		txtSurnames.setText("");
		txtDni.setText("");
		txtBornDate.getModel().setSelected(false);
	}
}
