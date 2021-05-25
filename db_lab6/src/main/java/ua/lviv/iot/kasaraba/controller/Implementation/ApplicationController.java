package ua.lviv.iot.kasaraba.controller.Implementation;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.kasaraba.DTO.ApplicationDTO;
import ua.lviv.iot.kasaraba.controller.CommonController;
import ua.lviv.iot.kasaraba.model.Application;
import ua.lviv.iot.kasaraba.service.Implementation.ApplicationService;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


import java.util.ArrayList;
import java.util.List;

@RestController
public class ApplicationController implements CommonController<ApplicationDTO, Application> {
  private final ApplicationService applicationService;

  public ApplicationController(ApplicationService applicationService) {
    this.applicationService = applicationService;
  }

  @GetMapping(value = "/api/application")
  public ResponseEntity<List<ApplicationDTO>> getApplications() {
    List<Application> applications = applicationService.getEntities();
    List<ApplicationDTO> applicationsDTO = createDTOs(applications);

    return new ResponseEntity<>(applicationsDTO, HttpStatus.OK);
  }

  @GetMapping(value = "/api/application/{applicationId}")
  public ResponseEntity<ApplicationDTO> getApplication(@PathVariable Integer applicationId) {
    Application application = applicationService.getEntity(applicationId);
    ApplicationDTO applicationDTO = createDTO(application);

    return new ResponseEntity<>(applicationDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/api/application")
  public ResponseEntity<ApplicationDTO> setApplication(@RequestBody Application application) {
    Application newApplication = applicationService.createEntity(application);
    ApplicationDTO applicationDTO = createDTO(newApplication);

    return new ResponseEntity<>(applicationDTO, HttpStatus.OK);
  }

  @PutMapping(value = "/api/application/{applicationId}")
  public ResponseEntity<ApplicationDTO> updateApplication(
          @RequestBody Application application, @PathVariable Integer applicationId) {
    Application newApplication = applicationService.updateEntity(application, applicationId);
    ApplicationDTO applicationDTO = createDTO(newApplication);

    return new ResponseEntity<>(applicationDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/api/application/{applicationId}")
  public ResponseEntity<ApplicationDTO> deleteApplication(@PathVariable Integer applicationId) {
    applicationService.deleteEntity(applicationId);

    return new ResponseEntity<>(HttpStatus.OK);
  }


  @Override
  public List<ApplicationDTO> createDTOs(Iterable<Application> applications) {
    Link link = linkTo(methodOn(ApplicationController.class).getApplications()).withSelfRel();
    List<ApplicationDTO> applicationsDTO = new ArrayList<>();
    for (Application application : applications) {
      Link selfLink = new Link(link.getHref() + "/" + application.getId());
      ApplicationDTO applicationDto = new ApplicationDTO(application, selfLink);
      applicationsDTO.add(applicationDto);
    }
    return applicationsDTO;
  }

  @Override
  public ApplicationDTO createDTO(Application application) {
    Link selfLink =
            linkTo(methodOn(ApplicationController.class).getApplication(application.getId()))
                    .withSelfRel();
    return new ApplicationDTO(application, selfLink);
  }
}
