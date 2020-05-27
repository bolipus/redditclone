package si.plapt.redclone.exceptions;

public class AuthException extends Exception {

  private static final long serialVersionUID = -8576825355422010810L;

  public AuthException(String message) {
    super(message);
  }

  public AuthException(String message, Throwable cause) {
    super(cause);
  }
  
  
}