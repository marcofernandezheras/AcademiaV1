package model;

public enum DiaLectivo {
	
	LUNES("Lunes"),
	MARTES("Martes"),
	MIERCOLES("Miercoles"),
	JUEVES("Jueves"),
	VIERNES("Viernes");
	
	private final String nombre;
	
	private DiaLectivo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	
	@Override
	public String toString() {
		return nombre;
	}
		
	public static void main(String[] args) {
		DiaLectivo hoy = DiaLectivo.MARTES;
		System.out.println(hoy.getNombre());
		System.out.println(hoy);
		
		DiaLectivo[] values = DiaLectivo.values();
		for (DiaLectivo diaLectivo : values) {
			System.out.println(diaLectivo);
		}
		
		System.out.println(DiaLectivo.LUNES.name());
		System.out.println(DiaLectivo.LUNES.ordinal());
		System.out.println(DiaLectivo.values()[0]);
		System.out.println(DiaLectivo.LUNES.compareTo(DiaLectivo.MARTES));		
	}
}
