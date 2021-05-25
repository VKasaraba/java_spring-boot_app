package ua.lviv.iot.kasaraba.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.kasaraba.model.User;
import org.springframework.hateoas.Link;

import java.util.Objects;

public class UserDTO extends ResourceSupport {
  private final User user;

  public UserDTO(User user, Link selfLink) {
    this.user = user;
    add(selfLink);
  }


  public Integer getUserId() {
    return user.getId();
  }

  public String getUserFirstName() {
    return user.getFirstName();
  }

  public String getUserLastName() {
    return user.getLastName();
  }

  public String getUserMiddleName() {
    return user.getMiddleName();
  }

  public String getUserNationality() {
    return user.getNationality();
  }

  public String getUserEmail() {
    return user.getEmail();
  }

  public Integer getUserYearOfRegistration() {
    return user.getYearOfRegistration();
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

    UserDTO that = (UserDTO) o;

    return Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (user != null ? user.hashCode() : 0);
    return result;
  }
}
