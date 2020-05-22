package si.plapt.redclone.exceptions;

public class LinkNotFoundException extends Exception {

  
  private static final long serialVersionUID = 3413210258761644195L;

  public LinkNotFoundException() {
  }

  public LinkNotFoundException(String message) {
    super(message);
  }

  public LinkNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

 
   
}