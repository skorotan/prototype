package prototype.exceptionhandler;

import org.libvirt.LibvirtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import prototype.api.Response;
import prototype.api.ResponseError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * Exception handler.
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    /**
     * Logger
     */
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Method that handles MethodArgumentNotValidException exception
     * @param exception MethodArgumentNotValidException
     * @return Response
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(MethodArgumentNotValidException exception) {
        Object message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        LOGGER.error("MethodArgumentNotValidException was raised, detailed message is: {}", message);
        return new ResponseError(message);
    }


    /**
     * Method that handles ConstraintViolationException exception
     * @param exception ConstraintViolationException
     * @return Response
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(ConstraintViolationException exception) {
        Object message =exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        LOGGER.error("ConstraintViolationException was raised, detailed message is: {}", message);
        return new ResponseError(message);
    }

    /**
     * Method that handles LibvirtException exception
     * @param exception LibvirtException
     * @return Response
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(LibvirtException exception) {
        LOGGER.error("LibvirtException was raised, detailed message is: {}", exception);
        return new ResponseError(exception.getMessage());
    }

    /**
     * Method that handles LinkageError error
     * @param error LinkageError
     * @return Response
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handle(LinkageError error) {
        LOGGER.error("LinkageError was raised, detailed message is: {}", error);
        return new ResponseError(error.getMessage());
    }

}
