package ua.lviv.iot.kasaraba.controller.Implementation;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/healthcheck")
@RestController
public class HealthCheckController {

    @RequestMapping(method = RequestMethod.GET)

    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}