package ua.lviv.iot.kasaraba.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.kasaraba.model.Secured;

import java.util.Objects;

public class SecuredDTO extends ResourceSupport {
  private final Secured secured;

  public SecuredDTO(Secured secured, Link selfLink) {
    this.secured = secured;
    add(selfLink);
  }

  public Integer getSecuredCardNumber() {
    return secured.getCardNumber();
  }

  public String getSecuredPassword() {
    return secured.getPassword();
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

    SecuredDTO that = (SecuredDTO) o;

    return Objects.equals(secured, that.secured);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (secured != null ? secured.hashCode() : 0);
    return result;
  }
}
