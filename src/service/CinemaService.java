package service;

import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;

import exceptions.AuthenticationException;
import exceptions.InvalidIdException;
import exceptions.NoSuchUserException;
import exceptions.SeatAlreadyTakenException;
import exceptions.UserAlreadyExistsException;
import exceptions.WrongPasswordException;
import model.Authentication;
import model.Database;
import model.Film;
import model.Reservation;
import model.Seat;
import model.Showing;
import model.User;

@MTOM
@BindingType(value = SOAPBinding.SOAP11HTTP_MTOM_BINDING)
@WebService(endpointInterface = "service.ICinemaService")
public class CinemaService implements ICinemaService {
	private Database database;
	
	public CinemaService() {
		database = Database.getInstance();
	}
	
	@Override
	public List<Showing> getShowings() {
		return database.getShowings();
	}

	@Override
	public Showing getShowing(int id) 
			throws InvalidIdException {
		
		Showing showing = database.getShowing(id);
		if (showing == null)
			throw new InvalidIdException();
		
		return showing;
	}
	
	@Override
	public Film getFilm(int id) 
			throws InvalidIdException {
		
		Film film = database.getFilm(id);
		if (film == null)
			throw new InvalidIdException();
		
		return film;
	}
	
	@Override
	public List<Seat> getTakenSeats(int showingId) 
			throws InvalidIdException {
		
		Showing showing = database.getShowing(showingId);
		if (showing == null)
			throw new InvalidIdException();
		
		return showing.getTakenSeats();
	}

	@Override
	public void createUser(Authentication authentication) 
			throws UserAlreadyExistsException {
		
		if (database.userExists(authentication.getUsername()))
			throw new UserAlreadyExistsException();
		
		database.addUser(new User(authentication));
		database.saveData();
		System.out.println("Utworzono u¿ytkownika: " + authentication.getUsername());
	}

	@Override
	public void login(Authentication authentication)
			throws NoSuchUserException, WrongPasswordException {
		
		User user = database.getUser(authentication.getUsername());
		if (user == null)
			throw new NoSuchUserException();
		if (!user.getAuthentication().getPassword().equals(authentication.getPassword()))
			throw new WrongPasswordException();
		
		System.out.println("Zalogowano u¿ytkownika: " + authentication.getUsername());
	}

	@Override
	public List<Reservation> getReservations(Authentication authentication)
			throws InvalidIdException, AuthenticationException {
		
		User user = database.getUser(authentication.getUsername());
		if (user == null || !user.getAuthentication().checkAuthentication(authentication))
			throw new AuthenticationException();
		
		return user.getReservations();
	}

	@Override
	public Reservation getReservation(Authentication authentication, int reservationId) 
			throws InvalidIdException, AuthenticationException {
		
		User user = database.getUser(authentication.getUsername());		
		if (user == null || !user.getAuthentication().checkAuthentication(authentication))
			throw new AuthenticationException();
		
		Reservation reservation = user.getReservation(reservationId);
		if (reservation == null)
			throw new InvalidIdException();
		
		return reservation;
	}

	@Override
	public String makeReservation(Authentication authentication, Reservation reservation) 
			throws SeatAlreadyTakenException, InvalidIdException, AuthenticationException {
		
		
		User user = database.getUser(authentication.getUsername());
		if (user == null || !user.getAuthentication().checkAuthentication(authentication))
			throw new AuthenticationException();
		
		Showing showing = database.getShowing(reservation.getShowingId());
		if (showing == null)
			throw new InvalidIdException();
		
		showing.makeReservation(reservation);
		System.out.println("Utworzono rezerwacjê.");
		String reservationCode = user.addReservation(reservation);
		database.saveData();
		return reservationCode;
	}

	@Override
	public void cancelReservation(Authentication authentication, int reservationId) 
			throws InvalidIdException, AuthenticationException {
		
		User user = database.getUser(authentication.getUsername());
		if (user == null || !user.getAuthentication().checkAuthentication(authentication))
			throw new AuthenticationException();
		
		Reservation reservation = user.getReservation(reservationId);
		if (reservation == null)
			throw new InvalidIdException();
		
		Showing showing = database.getShowing(reservation.getShowingId());
		showing.cancelReservation(reservation);
		user.removeReservation(reservation);
		System.out.println("Anulowano rezerwacjê.");
		database.saveData();
	}

	@Override
	public void updateReservation(Authentication authentication, int reservationId, Reservation reservation)
			throws SeatAlreadyTakenException, InvalidIdException, AuthenticationException {
		
		User user = database.getUser(authentication.getUsername());
		if (user == null || !user.getAuthentication().checkAuthentication(authentication))
			throw new AuthenticationException();
		
		Reservation oldReservation = user.getReservation(reservationId);
		if (oldReservation == null)
			throw new InvalidIdException();
		
		Showing showing = database.getShowing(oldReservation.getShowingId());
		if (showing == null)
			throw new InvalidIdException();
		
		showing.cancelReservation(oldReservation);
		showing.makeReservation(reservation);
		user.setReservation(reservationId, reservation);
		System.out.println("Zaktualizowano rezerwacjê.");
		database.saveData();
	}
}
