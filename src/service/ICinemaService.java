package service;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import exceptions.AuthenticationException;
import exceptions.InvalidIdException;
import exceptions.NoSuchUserException;
import exceptions.SeatAlreadyTakenException;
import exceptions.UserAlreadyExistsException;
import exceptions.WrongPasswordException;
import model.Authentication;
import model.Film;
import model.Reservation;
import model.Seat;
import model.Showing;

@WebService
@HandlerChain(file = "handler-chain.xml")
public interface ICinemaService {

	@WebMethod(operationName = "GetShowings")
	List<Showing> getShowings();
	
	@WebMethod(operationName = "GetShowing")
	Showing getShowing(
			@WebParam(name = "id") int id) 
			throws InvalidIdException;

	@WebMethod(operationName = "GetFilm")
	Film getFilm(
			@WebParam(name = "id") int id) 
			throws InvalidIdException;
	
	@WebMethod(operationName = "GetTakenSeats")
	List<Seat> getTakenSeats(
			@WebParam(name = "showingID") int showingId) 
			throws InvalidIdException;
	
	@WebMethod(operationName = "CreateUser")
	public void createUser(
			@WebParam(name = "authentication") Authentication authentication) 
			throws UserAlreadyExistsException;
	
	@WebMethod(operationName = "Login")
	public void login(
			@WebParam(name = "authentication") Authentication authentication)
			throws NoSuchUserException, WrongPasswordException;

	@WebMethod(operationName = "GetReservations")
	public List<Reservation> getReservations(
			@WebParam(name = "authentication") Authentication authentication)
			throws InvalidIdException, AuthenticationException;

	@WebMethod(operationName = "GetReservation")
	public Reservation getReservation(
			@WebParam(name = "authentication") Authentication authentication, 
			@WebParam(name = "reservationID") int reservationId) 
			throws InvalidIdException, AuthenticationException;

	@WebMethod(operationName = "MakeReservation")
	public String makeReservation(
			@WebParam(name = "authentication") Authentication authentication, 
			@WebParam(name = "reservation") Reservation reservation) 
			throws SeatAlreadyTakenException, InvalidIdException, AuthenticationException;

	@WebMethod(operationName = "CancelReservation")
	public void cancelReservation(
			@WebParam(name = "authentication") Authentication authentication, 
			@WebParam(name = "reservationID") int reservationId) 
			throws InvalidIdException, AuthenticationException;

	@WebMethod(operationName = "UpdateReservation")
	public void updateReservation(
			@WebParam(name = "authentication") Authentication authentication, 
			@WebParam(name = "reservationID") int reservationId, 
			@WebParam(name = "reservation") Reservation reservation)
			throws SeatAlreadyTakenException, InvalidIdException, AuthenticationException;
}
