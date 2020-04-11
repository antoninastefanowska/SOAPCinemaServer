package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Reservation")
@XmlType(name = "Reservation")
@XmlAccessorType(XmlAccessType.NONE)
public class Reservation {
	private final static String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String LOWER = UPPER.toLowerCase();
    private final static String NUM = "1234567890";
    private final static String ALPHANUM = UPPER + LOWER + NUM;
	
	private static Counter indexCounter = new Counter();
	
	private int id;
	private int showingId;
	private String code;
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
	
	public String generateCode() {
		int length = 15;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHANUM.length());
            sb.append(ALPHANUM.charAt(index));
        }
        code = sb.toString();
        return code;
    }
	
	@XmlElement(name = "Code")
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
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
