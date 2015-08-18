package model;

public class Horario {
	
	private DiaLectivo dia;
	private HoraClase hora;
	private Group grupo;
	
	public Horario(DiaLectivo dia, HoraClase hora, Group grupo) {
		super();
		this.dia = dia;
		this.hora = hora;
		this.grupo = grupo;
	}
	
	public DiaLectivo getDia() {
		return dia;
	}
	public HoraClase getHora() {
		return hora;
	}
	public Group getGrupo() {
		return grupo;
	}

	public void setDia(DiaLectivo dia) {
		this.dia = dia;
	}

	public void setHora(HoraClase hora) {
		this.hora = hora;
	}
	
}
