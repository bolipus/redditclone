package si.plapt.redclone.exceptions;

public class RedCloneException extends Exception{

  private static final long serialVersionUID = -3622476098446282561L;

  public RedCloneException() {
  }

  public RedCloneException(String message) {
    super(message);
  }

  public RedCloneException(String message, Throwable cause) {
    super(message, cause);
  }

  
  
}