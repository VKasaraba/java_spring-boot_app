package ua.lviv.iot.kasaraba.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.kasaraba.model.VersionUpdate;

import java.sql.Date;
import java.util.Objects;

public class VersionUpdateDTO extends ResourceSupport {
  private final VersionUpdate versionUpdate;

  public VersionUpdateDTO(VersionUpdate versionUpdate, Link selfLink) {
    this.versionUpdate = versionUpdate;
    add(selfLink);
  }

  public Integer getVersionUpdateId() {
    return versionUpdate.getId();
  }

  public Date getVersionUpdateUpdateDay() {
    return versionUpdate.getUpdateDay();
  }

  public String getVersionUpdateThingsUpdated() {
    return versionUpdate.getThingsUpdated();
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

    VersionUpdateDTO that = (VersionUpdateDTO) o;

    return Objects.equals(versionUpdate, that.versionUpdate);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (versionUpdate != null ? versionUpdate.hashCode() : 0);
    return result;
  }

}
