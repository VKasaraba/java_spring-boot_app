package ua.lviv.iot.kasaraba.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.kasaraba.model.Author;
import org.springframework.hateoas.Link;

import java.util.Objects;

public class AuthorDTO extends ResourceSupport {
  private final Author author;

  public AuthorDTO(Author author, Link selfLink) {
    this.author = author;
    add(selfLink);
  }

  public Integer getAuthorId() {
    return author.getId();
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

    AuthorDTO that = (AuthorDTO) o;

    return Objects.equals(author, that.author);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (author != null ? author.hashCode() : 0);
    return result;
  }
}
