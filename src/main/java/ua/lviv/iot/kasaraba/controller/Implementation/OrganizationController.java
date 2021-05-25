package ua.lviv.iot.kasaraba.controller.Implementation;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.kasaraba.DTO.ApplicationDTO;
import ua.lviv.iot.kasaraba.DTO.OrganizationDTO;
import ua.lviv.iot.kasaraba.controller.CommonController;
import ua.lviv.iot.kasaraba.model.Application;
import ua.lviv.iot.kasaraba.model.Organization;
import ua.lviv.iot.kasaraba.service.Implementation.OrganizationService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class OrganizationController implements CommonController<OrganizationDTO, Organization> {

  private final OrganizationService organizationService;

  public OrganizationController(OrganizationService organizationService) {
    this.organizationService = organizationService;
  }


  @GetMapping(value = "/api/organization")
  public ResponseEntity<List<OrganizationDTO>> getOrganizations() {
    List<Organization> organizations = organizationService.getEntities();
    List<OrganizationDTO> organizationsDTO = createDTOs(organizations);

    return new ResponseEntity<>(organizationsDTO, HttpStatus.OK);
  }

  @GetMapping(value = "/api/organization/{organizationId}")
  public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable Integer organizationId) {
    Organization organization = organizationService.getEntity(organizationId);
    OrganizationDTO organizationDTO = createDTO(organization);

    return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/api/organization")
  public ResponseEntity<OrganizationDTO> setOrganization(@RequestBody Organization organization) {
    Organization newOrganization = organizationService.createEntity(organization);
    OrganizationDTO organizationDTO = createDTO(newOrganization);

    return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
  }

  @PutMapping(value = "/api/organization/{organizationId}")
  public ResponseEntity<OrganizationDTO> updateOrganization(
          @RequestBody Organization organization, @PathVariable Integer organizationId) {
    Organization newOrganization = organizationService.updateEntity(organization, organizationId);
    OrganizationDTO organizationDTO = createDTO(newOrganization);

    return new ResponseEntity<>(organizationDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/api/organization/{organizationId}")
  public ResponseEntity<OrganizationDTO> deleteOrganization(@PathVariable Integer organizationId) {
    organizationService.deleteEntity(organizationId);

    return new ResponseEntity<>(HttpStatus.OK);
  }


  @Override
  public List<OrganizationDTO> createDTOs(Iterable<Organization> organizations) {
    Link link = linkTo(methodOn(OrganizationController.class).getOrganizations()).withSelfRel();
    List<OrganizationDTO> organizationsDTO = new ArrayList<>();
    for (Organization organization  : organizations) {
      Link selfLink = new Link(link.getHref() + "/" + organization.getId());
      OrganizationDTO organizationDTO = new OrganizationDTO(organization, selfLink);
      organizationsDTO.add(organizationDTO);
    }
    return organizationsDTO;
  }

  @Override
  public OrganizationDTO createDTO(Organization organization) {
    Link selfLink =
            linkTo(methodOn(OrganizationController.class).getOrganization(organization.getId()))
                    .withSelfRel();
    return new OrganizationDTO(organization, selfLink);
  }
}
