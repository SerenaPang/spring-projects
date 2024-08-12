package com.tucyticket.app.model;

public class Reservation {
	private Integer idReservation;
	private Integer idUser;
	private Integer idEvent;

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", idUser=" + idUser + ", idEvent=" + idEvent + "]";
	}
}
