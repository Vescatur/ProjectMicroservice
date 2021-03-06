package soa.group5.schedule;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import soa.group5.schedule.ScheduleNotFoundException;

@ControllerAdvice
public class ScheduleNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ScheduleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(ScheduleNotFoundException ex) {
        return ex.getMessage();
    }
}
