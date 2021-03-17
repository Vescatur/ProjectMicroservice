package soa.group5.emergency;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import soa.group5.emergency.EmergencyNotFoundException;

@ControllerAdvice
public class EmergencyNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EmergencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmergencyNotFoundException ex) {
        return ex.getMessage();
    }
}
