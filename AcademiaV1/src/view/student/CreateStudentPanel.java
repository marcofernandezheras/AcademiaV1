package view.student;

@SuppressWarnings("serial")
public abstract class CreateStudentPanel extends StudentInfoPanel {
	

	/**
	 * Create the panel.
	 */
	public CreateStudentPanel() {
		super();
	}
	
	@Override
	protected String getButtonLabel() {
		return "Guardar";
	}
}
