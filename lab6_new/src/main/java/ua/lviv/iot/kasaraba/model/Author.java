package ua.lviv.iot.kasaraba.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "author", schema = "kasaraba_lab5")
public class Author {
  private Integer id;

  public Author(Integer id) {
    this.id = id;
  }

  public Author() {}

  public static String getHeaders() {
    return "id";
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Author that = (Author) o;

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public String toString() {
    return String.format("%s", id);
  }
}
