package si.plapt.redclone.exceptions;

public class UserNotFoundException extends Exception{
  
  private static final long serialVersionUID = 6477428014465820753L;

  
  public UserNotFoundException() {
  }

  public UserNotFoundException(String msg) {
    super(msg);    
  }
  
  public UserNotFoundException(String msg, Throwable cause){
    super(msg, cause);    
  }

}