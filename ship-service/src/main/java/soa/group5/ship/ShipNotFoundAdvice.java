package soa.group5.ship;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import soa.group5.ship.ShipNotFoundException;

@ControllerAdvice
public class ShipNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ShipNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(ShipNotFoundException ex) {
        return ex.getMessage();
    }
}
