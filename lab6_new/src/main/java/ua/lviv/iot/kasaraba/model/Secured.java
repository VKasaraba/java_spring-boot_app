package ua.lviv.iot.kasaraba.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "secured", schema = "kasaraba_lab5")
public class Secured {
  private Integer cardNumber;
  private String password;

  public static String getHeaders() {
    return String.format("%-16s  %-16s", "cardNumber", "password");
  }

  @Id
  @Column(name = "card_number")
  public Integer getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(Integer cardNumber) {
    this.cardNumber = cardNumber;
  }

  @Basic
  @Column(name = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Secured that = (Secured) o;

    if (!Objects.equals(cardNumber, that.cardNumber)) {
      return false;
    }
    return Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    int result = cardNumber != null ? cardNumber.hashCode() : 0;
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return String.format("%-16s  %-16s", cardNumber, password);
  }
}
