package ua.lviv.iot.kasaraba.model;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.Objects;

@Entity
@Table(name = "organization", schema = "kasaraba_lab5")
public class Organization {
  private Integer id;
  private String name;
  private Integer authorId;
  private Author authorByAuthorId;

  public Organization(Integer id, String name, Integer authorId) {
    this.id = id;
    this.name = name;
    this.authorId = authorId;
  }

  public Organization(String name, Integer authorId)  {
    this.name = name;
    this.authorId = authorId;
  }

  public Organization() {}

  public static String getHeaders() {
    return String.format("%-3s %-30s %-8s", "id", "name", "authorId");
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

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "author_id")
  public Integer getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Integer authorId) {
    this.authorId = authorId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Organization that = (Organization) o;

    if (!Objects.equals(name, that.name)) {
      return false;
    }
    return Objects.equals(authorId, that.authorId);
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
    return result;
  }

  @ManyToOne
  @JoinColumn(
      name = "author_id",
      referencedColumnName = "id",
      nullable = false,
      insertable = false,
      updatable = false)
  public ua.lviv.iot.kasaraba.model.Author getAuthorByAuthorId() {
    return authorByAuthorId;
  }

  public void setAuthorByAuthorId(ua.lviv.iot.kasaraba.model.Author authorByAuthorId) {
    this.authorByAuthorId = authorByAuthorId;
  }

  @Override
  public String toString() {
    return String.format("%-3s %-30s %-8s", id, name, authorId);
  }
}
