package ua.lviv.iot.kasaraba.model;


import javax.persistence.*;
import java.sql.SQLException;
import java.util.Objects;

@Entity
@Table(name = "single_developer", schema = "kasaraba_lab5")
public class SingleDeveloper {
  private Integer id;
  private String firstName;
  private String lastName;
  private Integer authorId;
  private Author authorByAuthorId;

  public SingleDeveloper(Integer id, String firstName, String lastName, Integer authorId) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.authorId = authorId;
  }

  public SingleDeveloper(String firstName, String lastName, Integer authorId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.authorId = authorId;
  }

  public SingleDeveloper() {}

  public static String getHeaders() {
    return String.format("%-3s %-30s %-30s %-8s", "id", "firstName", "lastName", "authorId");
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
  @Column(name = "first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Basic
  @Column(name = "last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

    SingleDeveloper that = (SingleDeveloper) o;

    if (!Objects.equals(firstName, that.firstName)) {
      return false;
    }
    if (!Objects.equals(lastName, that.lastName)) {
      return false;
    }
    return Objects.equals(authorId, that.authorId);
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
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
    return String.format("%-3s %-30s %-30s %-8s", id, firstName, lastName, authorId);
  }
}
