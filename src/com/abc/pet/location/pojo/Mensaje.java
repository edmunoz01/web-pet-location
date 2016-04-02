package com.abc.pet.location.pojo;

/**
 * Clase que define el contenido de un mensaje en el que se envia la informacion
 * de una mascota
 * 
 * @author Carlos Mario
 * 
 */
public class Mensaje {

	/**
	 * Parte de un par ordenado que define la latitud de la mascota
	 */
	private String latitud;
	/**
	 * Parte de un par ordenado que define la longitud de la mascota
	 */
	private String longitud;
	/**
	 * Identificador del collar asignado a la mascota, identifica la mascota
	 */
	private Long idCollar;
	/**
	 * Informacion biometrica de la mascota que permite conocer la frecuencia
	 * respiratoria
	 */
	private String frecuenciaRespiratoria;
	/**
	 * Informacion biometrica de la mascota que permite conocer la frecuencia
	 * cardiaca
	 */
	private String frecuenciaCardiaca;

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public Long getIdCollar() {
		return idCollar;
	}

	public void setIdCollar(Long idCollar) {
		this.idCollar = idCollar;
	}

	public String getFrecuenciaRespiratoria() {
		return frecuenciaRespiratoria;
	}

	public void setFrecuenciaRespiratoria(String frecuenciaRespiratoria) {
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
	}

	public String getFrecuenciaCardiaca() {
		return frecuenciaCardiaca;
	}

	public void setFrecuenciaCardiaca(String frecuenciaCardiaca) {
		this.frecuenciaCardiaca = frecuenciaCardiaca;
	}

}
