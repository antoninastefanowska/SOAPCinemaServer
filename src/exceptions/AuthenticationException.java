package exceptions;

@SuppressWarnings("serial")
public class AuthenticationException extends Exception {

	public AuthenticationException() {
		super("B��d autentykacji. Nie masz uprawnie� do tej operacji.");
	}
}
