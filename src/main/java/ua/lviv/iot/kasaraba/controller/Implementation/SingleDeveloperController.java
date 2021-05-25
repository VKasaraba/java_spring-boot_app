package ua.lviv.iot.kasaraba.controller.Implementation;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.kasaraba.DTO.ApplicationDTO;
import ua.lviv.iot.kasaraba.DTO.SingleDeveloperDTO;
import ua.lviv.iot.kasaraba.controller.CommonController;
import ua.lviv.iot.kasaraba.model.Application;
import ua.lviv.iot.kasaraba.model.SingleDeveloper;
import ua.lviv.iot.kasaraba.service.Implementation.SingleDeveloperService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SingleDeveloperController implements CommonController<SingleDeveloperDTO, SingleDeveloper> {
  private final SingleDeveloperService singleDeveloperService;

  public SingleDeveloperController(SingleDeveloperService singleDeveloperService) {
    this.singleDeveloperService = singleDeveloperService;
  }


  @GetMapping(value = "/api/singleDeveloper")
  public ResponseEntity<List<SingleDeveloperDTO>> getSingleDevelopers() {
    List<SingleDeveloper> singleDevelopers = singleDeveloperService.getEntities();
    List<SingleDeveloperDTO> singleDevelopersDTOS = createDTOs(singleDevelopers);

    return new ResponseEntity<>(singleDevelopersDTOS, HttpStatus.OK);
  }

  @GetMapping(value = "/api/singleDeveloper/{singleDeveloperId}")
  public ResponseEntity<SingleDeveloperDTO> getSingleDeveloper(@PathVariable Integer singleDeveloperId) {
    SingleDeveloper singleDeveloper = singleDeveloperService.getEntity(singleDeveloperId);
    SingleDeveloperDTO singleDeveloperDTO = createDTO(singleDeveloper);

    return new ResponseEntity<>(singleDeveloperDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/api/singleDeveloper")
  public ResponseEntity<SingleDeveloperDTO> setSingleDeveloper(@RequestBody SingleDeveloper singleDeveloper) {
    SingleDeveloper newSingleDeveloper = singleDeveloperService.createEntity(singleDeveloper);
    SingleDeveloperDTO singleDeveloperDTO = createDTO(newSingleDeveloper);

    return new ResponseEntity<>(singleDeveloperDTO, HttpStatus.OK);
  }

  @PutMapping(value = "/api/singleDeveloper/{singleDeveloperId}")
  public ResponseEntity<SingleDeveloperDTO> updateSingleDeveloper(
          @RequestBody SingleDeveloper singleDeveloper, @PathVariable Integer singleDeveloperId) {
    SingleDeveloper newSingleDeveloper = singleDeveloperService.updateEntity(singleDeveloper, singleDeveloperId);
    SingleDeveloperDTO singleDeveloperDTO = createDTO(newSingleDeveloper);

    return new ResponseEntity<>(singleDeveloperDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/api/singleDeveloper/{singleDeveloperId}")
  public ResponseEntity<SingleDeveloperDTO> deleteSingleDeveloper(@PathVariable Integer singleDeveloperId) {
    singleDeveloperService.deleteEntity(singleDeveloperId);

    return new ResponseEntity<>(HttpStatus.OK);
  }


  @Override
  public List<SingleDeveloperDTO> createDTOs(Iterable<SingleDeveloper> singleDevelopers) {
    Link link = linkTo(methodOn(SingleDeveloperController.class).getSingleDevelopers()).withSelfRel();
    List<SingleDeveloperDTO> singleDevelopersDTO = new ArrayList<>();
    for (SingleDeveloper singleDeveloper : singleDevelopers) {
      Link selfLink = new Link(link.getHref() + "/" + singleDeveloper.getId());
      SingleDeveloperDTO singleDeveloperDTO = new SingleDeveloperDTO(singleDeveloper, selfLink);
      singleDevelopersDTO.add(singleDeveloperDTO);
    }
    return singleDevelopersDTO;
  }

  @Override
  public SingleDeveloperDTO createDTO(SingleDeveloper singleDeveloper) {
    Link selfLink =
            linkTo(methodOn(SingleDeveloperController.class).getSingleDeveloper(singleDeveloper.getId()))
                    .withSelfRel();
    return new SingleDeveloperDTO(singleDeveloper, selfLink);
  }

}
