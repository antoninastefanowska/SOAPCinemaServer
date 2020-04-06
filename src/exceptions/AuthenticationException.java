package exceptions;

@SuppressWarnings("serial")
public class AuthenticationException extends Exception {

	public AuthenticationException() {
		super("B³¹d autentykacji. Nie masz uprawnieñ do tej operacji.");
	}
}
