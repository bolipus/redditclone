package si.plapt.redclone.controllers.advices;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import si.plapt.redclone.exceptions.LinkNotFoundException;
import si.plapt.redclone.exceptions.RoleNotFoundException;
import si.plapt.redclone.exceptions.UserNotFoundException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
  
  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)  
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String userNotFoundHandler(UserNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(LinkNotFoundException.class)  
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String userNotFoundHandler(LinkNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(RoleNotFoundException.class)  
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String roleNotFoundHandler(RoleNotFoundException ex) {
    return ex.getMessage();
  }
  
}