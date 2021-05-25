package ua.lviv.iot.kasaraba.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.lviv.iot.kasaraba.DTO.MessageDTO;
import ua.lviv.iot.kasaraba.exception.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NoSuchApplicationException.class)
  ResponseEntity<MessageDTO> NoSuchApplicationException() {
    return new ResponseEntity<>(
        new MessageDTO("Such Application is not present in database"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoSuchUserException.class)
  ResponseEntity<MessageDTO> NoSuchUserException() {
    return new ResponseEntity<>(
            new MessageDTO("Such User is not present in database"), HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(NoSuchAuthorException.class)
  ResponseEntity<MessageDTO> NoSuchAuthorException() {
    return new ResponseEntity<>(
            new MessageDTO("Such Author is not present in database"), HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(NoSuchFeedbackException.class)
  ResponseEntity<MessageDTO> NoSuchFeedbackException() {
    return new ResponseEntity<>(
            new MessageDTO("Such Feedback is not present in database"), HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(NoSuchOrganizationException.class)
  ResponseEntity<MessageDTO> NoSuchOrganizationException() {
    return new ResponseEntity<>(
            new MessageDTO("Such Organization is not present in database"), HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(NoSuchSecuredException.class)
  ResponseEntity<MessageDTO> NoSuchSecuredException() {
    return new ResponseEntity<>(
            new MessageDTO("Such Secured is not present in database"), HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(NoSuchSingleDeveloperException.class)
  ResponseEntity<MessageDTO> NoSuchSingleDeveloperException() {
    return new ResponseEntity<>(
            new MessageDTO("Such Single Developer is not present in database"), HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(NoSuchVersionUpdateException.class)
  ResponseEntity<MessageDTO> NoSuchVersionUpdateException() {
    return new ResponseEntity<>(
            new MessageDTO("Such Version Update is not present in database"), HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(NoSuchVirtualWalletException.class)
  ResponseEntity<MessageDTO> NoSuchVirtualWalletException() {
    return new ResponseEntity<>(
            new MessageDTO("Such Virtual Wallet is not present in database"), HttpStatus.NOT_FOUND);
  }
}
