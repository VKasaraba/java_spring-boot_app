package ua.lviv.iot.kasaraba.controller.Implementation;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.kasaraba.DTO.SecuredDTO;
import ua.lviv.iot.kasaraba.controller.CommonController;
import ua.lviv.iot.kasaraba.model.Secured;
import ua.lviv.iot.kasaraba.service.Implementation.SecuredService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SecuredController implements CommonController<SecuredDTO, Secured> {

  private final SecuredService securedService;

  public SecuredController(SecuredService securedService) {
    this.securedService = securedService;
  }

  @GetMapping(value = "/api/secured")
  public ResponseEntity<List<SecuredDTO>> getSecureds() {
    List<Secured> secureds = securedService.getEntities();
    List<SecuredDTO> securedsDTO = createDTOs(secureds);

    return new ResponseEntity<>(securedsDTO, HttpStatus.OK);
  }

  @GetMapping(value = "/api/secured/{securedId}")
  public ResponseEntity<SecuredDTO> getSecured(@PathVariable String securedId) {
    Secured secured = securedService.getEntity(securedId);
    SecuredDTO securedDTO = createDTO(secured);

    return new ResponseEntity<>(securedDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/api/secured")
  public ResponseEntity<SecuredDTO> setSecured(@RequestBody Secured secured) {
    Secured newSecured = securedService.createEntity(secured);
    SecuredDTO securedDTO = createDTO(newSecured);

    return new ResponseEntity<>(securedDTO, HttpStatus.OK);
  }

  @PutMapping(value = "/api/secured/{securedId}")
  public ResponseEntity<SecuredDTO> updateSecured(
          @RequestBody Secured secured, @PathVariable String securedId) {
    Secured newSecured = securedService.updateEntity(secured, securedId);
    SecuredDTO securedDTO = createDTO(newSecured);

    return new ResponseEntity<>(securedDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/api/secured/{securedId}")
  public ResponseEntity<SecuredDTO> deleteSecured(@PathVariable String securedId) {
    securedService.deleteEntity(securedId);

    return new ResponseEntity<>(HttpStatus.OK);
  }


  @Override
  public List<SecuredDTO> createDTOs(Iterable<Secured> secureds) {
    Link link = linkTo(methodOn(SecuredController.class).getSecureds()).withSelfRel();
    List<SecuredDTO> securedsDTO = new ArrayList<>();
    for (Secured secured : secureds) {
      Link selfLink = new Link(link.getHref() + "/" + secured.getCardNumber());
      SecuredDTO securedDTO = new SecuredDTO(secured, selfLink);
      securedsDTO.add(securedDTO);
    }
    return securedsDTO;
  }

  @Override
  public SecuredDTO createDTO(Secured secured) {
    Link selfLink =
            linkTo(methodOn(ApplicationController.class).getApplication(secured.getCardNumber()))
                    .withSelfRel();
    return new SecuredDTO(secured, selfLink);
  }
}
