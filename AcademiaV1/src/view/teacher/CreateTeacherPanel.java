package view.teacher;


@SuppressWarnings("serial")
public abstract class CreateTeacherPanel extends TeacherInfoPanel {

	public CreateTeacherPanel() {
		super();
	}

	@Override
	protected String getButtonLabel() {
		return "Guardar";
	}
}
