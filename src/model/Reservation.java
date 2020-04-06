package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Reservation")
@XmlType(name = "Reservation")
@XmlAccessorType(XmlAccessType.NONE)
public class Reservation {
	private static Counter indexCounter = new Counter();
	
	private int id;
	private int showingId;
	private List<Seat> seats;
	
	public Reservation() {
		this.seats = new ArrayList<>();
	}
	
	public Reservation(int showingId) {
		this.showingId = showingId;
		this.seats = new ArrayList<>();
	}
	
	@XmlElement(name = "ID")
	public int getId() {
		return id;
	}
	
	public int generateId() {
		int id = indexCounter.nextValue();
		this.id = id;
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addSeat(int row, int column) {
		seats.add(new Seat(row, column));
	}

	@XmlElement(name = "Seats")
	public List<Seat> getSeats() {
		return seats;
	}

	@XmlElement(name = "ShowingID")
	public int getShowingId() {
		return showingId;
	}

	public void setShowingId(int showingId) {
		this.showingId = showingId;
	}
}
