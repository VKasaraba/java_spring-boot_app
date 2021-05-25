package ua.lviv.iot.kasaraba.controller.Implementation;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.kasaraba.DTO.VersionUpdateDTO;
import ua.lviv.iot.kasaraba.controller.CommonController;
import ua.lviv.iot.kasaraba.model.VersionUpdate;
import ua.lviv.iot.kasaraba.service.Implementation.VersionUpdateService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class VersionUpdateController implements CommonController<VersionUpdateDTO, VersionUpdate> {
  private final VersionUpdateService versionUpdateService;

  public VersionUpdateController(VersionUpdateService applicationService) {
    this.versionUpdateService = applicationService;
  }

  @GetMapping(value = "/api/versionUpdate")
  public ResponseEntity<List<VersionUpdateDTO>> getVersionUpdates() {
    List<VersionUpdate> versionUpdates = versionUpdateService.getEntities();
    List<VersionUpdateDTO> versionUpdateDTOs = createDTOs(versionUpdates);

    return new ResponseEntity<>(versionUpdateDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/api/versionUpdate/{versionUpdateId}")
  public ResponseEntity<VersionUpdateDTO> getVersionUpdate(@PathVariable Integer versionUpdateId) {
    VersionUpdate versionUpdate = versionUpdateService.getEntity(versionUpdateId);
    VersionUpdateDTO versionUpdateDTO = createDTO(versionUpdate);

    return new ResponseEntity<>(versionUpdateDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/api/versionUpdate")
  public ResponseEntity<VersionUpdateDTO> setVersionUpdate(@RequestBody VersionUpdate versionUpdate) {
    VersionUpdate newVersionUpdate = versionUpdateService.createEntity(versionUpdate);
    VersionUpdateDTO versionUpdateDTO = createDTO(newVersionUpdate);

    return new ResponseEntity<>(versionUpdateDTO, HttpStatus.OK);
  }

  @PutMapping(value = "/api/versionUpdate/{versionUpdateId}")
  public ResponseEntity<VersionUpdateDTO> updateVersionUpdate(
          @RequestBody VersionUpdate versionUpdate, @PathVariable Integer versionUpdateId) {
    VersionUpdate newVersionUpdate = versionUpdateService.updateEntity(versionUpdate, versionUpdateId);
    VersionUpdateDTO versionUpdateDTO = createDTO(newVersionUpdate);

    return new ResponseEntity<>(versionUpdateDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/api/versionUpdate/{versionUpdateId}")
  public ResponseEntity<VersionUpdateDTO> deleteVersionUpdate(@PathVariable Integer versionUpdateId) {
    versionUpdateService.deleteEntity(versionUpdateId);

    return new ResponseEntity<>(HttpStatus.OK);
  }


  @Override
  public List<VersionUpdateDTO> createDTOs(Iterable<VersionUpdate> versionUpdates) {
    Link link = linkTo(methodOn(VersionUpdateController.class).getVersionUpdates()).withSelfRel();
    List<VersionUpdateDTO> versionUpdateDTOs = new ArrayList<>();
    for (VersionUpdate versionUpdate : versionUpdates) {
      Link selfLink = new Link(link.getHref() + "/" + versionUpdate.getId());
      VersionUpdateDTO versionUpdateDTO = new VersionUpdateDTO(versionUpdate, selfLink);
      versionUpdateDTOs.add(versionUpdateDTO);
    }
    return versionUpdateDTOs;
  }

  @Override
  public VersionUpdateDTO createDTO(VersionUpdate versionUpdate) {
    Link selfLink =
            linkTo(methodOn(VersionUpdateController.class).getVersionUpdate(versionUpdate.getId()))
                    .withSelfRel();
    return new VersionUpdateDTO(versionUpdate, selfLink);
  }
}
