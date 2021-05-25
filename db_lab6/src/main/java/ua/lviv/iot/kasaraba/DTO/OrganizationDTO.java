package ua.lviv.iot.kasaraba.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.kasaraba.model.Organization;

import java.util.Objects;

public class OrganizationDTO extends ResourceSupport {
  private final Organization organization;

  public OrganizationDTO(Organization organization, Link selfLink) {
    this.organization = organization;
    add(selfLink);
  }


  public Integer getOrganizationId() {
    return organization.getId();
  }

  public String getOrganizationName() {
    return organization.getName();
  }

  public Integer getOrganizationAuthorId() {
    return organization.getAuthorId();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    OrganizationDTO that = (OrganizationDTO) o;

    return Objects.equals(organization, that.organization);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (organization != null ? organization.hashCode() : 0);
    return result;
  }
}
