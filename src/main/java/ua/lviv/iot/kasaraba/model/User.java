package ua.lviv.iot.kasaraba.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user", schema = "kasaraba_lab5")
public class User {
  private Integer id;
  private String firstName;
  private String lastName;
  private String middleName;
  private String nationality;
  private String email;
  private Integer yearOfRegistration;
  private Set<Application> applications;

  public User(
      Integer id,
      String firstName,
      String lastName,
      String middleName,
      String nationality,
      String email,
      Integer yearOfRegistration,
      Set<ua.lviv.iot.kasaraba.model.Application> applications) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.nationality = nationality;
    this.email = email;
    this.yearOfRegistration = yearOfRegistration;
    this.applications = applications;
  }

  public User(
      Integer id,
      String firstName,
      String lastName,
      String middleName,
      String nationality,
      String email,
      Integer yearOfRegistration) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.nationality = nationality;
    this.email = email;
    this.yearOfRegistration = yearOfRegistration;
  }

  public User(
      String firstName,
      String lastName,
      String middleName,
      String nationality,
      String email,
      Integer yearOfRegistration) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.nationality = nationality;
    this.email = email;
    this.yearOfRegistration = yearOfRegistration;
  }

  public User() {}

  public static String getHeaders() {
    return String.format(
        "%-3s  %-10s %-10s %-14s %-14s %-25s %-20s %-12s",
        "id",
        "firstName",
        "lastName",
        "middleName",
        "nationality",
        "email",
        "yearOfRegistration",
        "applicationIds");
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
  @Column(name = "middle_name")
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  @Basic
  @Column(name = "nationality")
  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Basic
  @Column(name = "email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "year_of_registration")
  public Integer getYearOfRegistration() {
    return yearOfRegistration;
  }

  public void setYearOfRegistration(Integer yearOfRegistration) {
    this.yearOfRegistration = yearOfRegistration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User that = (User) o;

    if (!Objects.equals(id, that.id)) {
      return false;
    }
    if (!Objects.equals(firstName, that.firstName)) {
      return false;
    }
    if (!Objects.equals(lastName, that.lastName)) {
      return false;
    }
    if (!Objects.equals(middleName, that.middleName)) {
      return false;
    }
    if (!Objects.equals(nationality, that.nationality)) {
      return false;
    }
    if (!Objects.equals(email, that.email)) {
      return false;
    }
    return Objects.equals(yearOfRegistration, that.yearOfRegistration);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
    result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (yearOfRegistration != null ? yearOfRegistration.hashCode() : 0);
    return result;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_application",
      schema = "kasaraba_lab5",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false),
      inverseJoinColumns =
          @JoinColumn(name = "application_id", referencedColumnName = "id", nullable = false))
  public Set<ua.lviv.iot.kasaraba.model.Application> getApplications() {
    return applications;
  }

  public void setApplications(Set<ua.lviv.iot.kasaraba.model.Application> applications) {
    this.applications = applications;
  }

  public List<Integer> showApplicationIds() {
    List<Integer> applicationIds = new ArrayList<>();
    for (ua.lviv.iot.kasaraba.model.Application application : this.getApplications()) {
      applicationIds.add(application.getId());
    }
    return applicationIds;
  }

  @Override
  public String toString() {
    return String.format(
        "%-3s  %-10s %-10s %-14s %-14s %-25s %-20s %-12s",
        id,
        firstName,
        lastName,
        middleName,
        nationality,
        email,
        yearOfRegistration,
        showApplicationIds());
  }
}
