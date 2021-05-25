package ua.lviv.iot.kasaraba.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.kasaraba.model.SingleDeveloper;

import java.util.Objects;

public class SingleDeveloperDTO extends ResourceSupport {
  private final SingleDeveloper singleDeveloper;

  public SingleDeveloperDTO(SingleDeveloper singleDeveloper, Link selfLink) {
    this.singleDeveloper = singleDeveloper;
    add(selfLink);
  }

  public Integer getSingleDeveloperId() {
    return singleDeveloper.getId();
  }

  public String getSingleDeveloperFirstName() {
    return singleDeveloper.getFirstName();
  }

  public String getSingleDeveloperLastName() {
    return singleDeveloper.getLastName();
  }

  public Integer getSingleDeveloperAuthorId() {
    return singleDeveloper.getAuthorId();
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

    SingleDeveloperDTO that = (SingleDeveloperDTO) o;

    return Objects.equals(singleDeveloper, that.singleDeveloper);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (singleDeveloper != null ? singleDeveloper.hashCode() : 0);
    return result;
  }
}
