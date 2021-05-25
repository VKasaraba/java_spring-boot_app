package ua.lviv.iot.kasaraba.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.kasaraba.model.Application;

import java.util.Objects;

public class ApplicationDTO extends ResourceSupport {
  private final Application application;

  public ApplicationDTO(Application application, Link selfLink) {
    this.application = application;
    add(selfLink);
  }


  public Integer getApplicationId() {
    return application.getId();
  }

  public String getApplicationName() {
    return application.getName();
  }

  public Integer getApplicationPriceInDol() {
    return application.getPriceInDol();
  }

  public Integer getApplicationReleaseYear() {
    return application.getReleaseYear();
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

    ApplicationDTO that = (ApplicationDTO) o;

    return Objects.equals(application, that.application);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (application != null ? application.hashCode() : 0);
    return result;
  }
}
