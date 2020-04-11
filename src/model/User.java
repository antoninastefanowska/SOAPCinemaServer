package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "User")
@XmlType(name = "User")
@XmlAccessorType(XmlAccessType.NONE)
public class User {
	private Authentication authentication;
	private Map<Integer, Reservation> reservations;
	
	public User() { 
		this.reservations = new HashMap<>();
	}
	
	public User(Authentication authentication) {
		this.authentication = authentication;
		this.reservations = new HashMap<>();
	}

	@XmlElement(name = "Authentication")
	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	
	public String addReservation(Reservation reservation) {
		int id = reservation.generateId();
		String code = reservation.generateCode();
		reservations.put(id, reservation);
		return code;
	}
	
	public void removeReservation(Reservation reservation) {
		int id = reservation.getId();
		reservations.remove(id);
	}
	
	public void setReservation(int id, Reservation reservation) {
		Reservation oldReservation = reservations.get(id);
		reservation.setId(id);
		reservation.setCode(oldReservation.getCode());
		reservations.put(id, reservation);
	}
	
	public Reservation getReservation(int id) {
		return reservations.get(id);
	}

	public List<Reservation> getReservations() {
		return new ArrayList<>(reservations.values());
	}
	
	public boolean reservationExists(int id) {
		return reservations.containsKey(id);
	}
}
