package com.petcoco.app.model;

import java.sql.Date;

public class Record {
	private int idUser;
	private int idPet;
	private Date date;

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdPet() {
		return idPet;
	}
	public void setIdPet(int idPet) {
		this.idPet = idPet;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Record [idUser=" + idUser + ", idPet=" + idPet + ", date=" + date + "]";
	}
}
