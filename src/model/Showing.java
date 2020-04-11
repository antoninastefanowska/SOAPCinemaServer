package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import exceptions.SeatAlreadyTakenException;

@XmlRootElement(name = "Showing")
@XmlType(name = "Showing")
@XmlAccessorType(XmlAccessType.NONE)
public class Showing {
	private final static int ROWS = 10;
	private final static int COLUMNS = 15;
	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	
	private static Counter indexCounter = new Counter();
	
	private int id;
	private int filmId;
	private String filmTitle;
	private Date date;
	private List<Seat> takenSeats;
	
	public Showing() { 
		this.takenSeats = new ArrayList<>();
	}
	
	public Showing(Film film, String dateString) {
		this.takenSeats = new ArrayList<>();
		this.filmId = film.getId();
		this.filmTitle = film.getTitle();
		try {
			this.date = DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@XmlElement(name = "FilmID")
	public int getFilmId() {
		return filmId;
	}
	
	@XmlElement(name = "FilmTitle")
	public String getFilmTitle() {
		return filmTitle;
	}
	
	@XmlElement(name = "DateEpoch")
	public long getDateEpoch() {
		return date.getTime();
	}
	
	public List<Seat> getTakenSeats() {
		return takenSeats;
	}
	
	@XmlElement(name = "SeatsRowNumber")
	public int getRowNumber() {
		return ROWS;
	}
	
	@XmlElement(name = "SeatsColumnNumber")
	public int getColumnNumber() {
		return COLUMNS;
	}
	
	private Seat findSeat(Seat seat) {
		for (Seat takenSeat : takenSeats)
			if (takenSeat.getRow() == seat.getRow() && takenSeat.getColumn() == seat.getColumn())
				return takenSeat;
		return null;
	}
	
	public Boolean isSeatTaken(Seat seat) {
		return findSeat(seat) != null;
	}
	
	public void takeSeat(Seat seat) {
		takenSeats.add(seat);
	}
	
	public void freeSeat(Seat seat) {
		Seat toRemove = findSeat(seat);
		takenSeats.remove(toRemove);
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
	
	public void makeReservation(Reservation reservation) throws SeatAlreadyTakenException {
		for (Seat seat : reservation.getSeats()) {
			if (isSeatTaken(seat))
				throw new SeatAlreadyTakenException();
			else
				takeSeat(seat);
		}
	}
	
	public void cancelReservation(Reservation reservation) {
		for (Seat seat : reservation.getSeats())
			freeSeat(seat);
	}
}
