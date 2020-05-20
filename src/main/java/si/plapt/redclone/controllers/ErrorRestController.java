package si.plapt.redclone.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ErrorRestController implements ErrorController{

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	@GetMapping("/error")
	public String handleGetError(HttpServletRequest request) {
		return handleError(request);
	}
	
	@PutMapping("/error")
	public String handlePutError(HttpServletRequest request) {
		return handleError(request);
	}
	
	@PostMapping("/error")
	public String handlePostError(HttpServletRequest request) {
		return handleError(request);
	}
	
	@DeleteMapping("/error")
	public String handleDeleteError(HttpServletRequest request) {
		return handleError(request);
	}


	private String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    String message = (String)request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
	    
	    log.info("Status:{}", status);
	     
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        return statusCode + ": " + message;
	    }
	    
	    return "error";
	}
  
}