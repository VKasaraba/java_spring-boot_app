package ua.lviv.iot.kasaraba.service.Implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.kasaraba.exception.NoSuchOrganizationException;
import ua.lviv.iot.kasaraba.model.Organization;

@Service
public class OrganizationService extends CommonServiceImplementation<Organization, Integer>{
  private final JpaRepository<Organization, Integer> jpaRepository;

  public OrganizationService(JpaRepository<Organization, Integer> jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  protected JpaRepository<Organization, Integer> getRepository() {
    return jpaRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchOrganizationException();
  }

  @Override
  protected Organization mergeEntities(Organization changedEntity, Organization incomeEntity) {
    changedEntity.setName(
            incomeEntity.getName() != null ? incomeEntity.getName() : changedEntity.getName()
    );
    changedEntity.setAuthorByAuthorId(
            incomeEntity.getAuthorByAuthorId() != null ? incomeEntity.getAuthorByAuthorId() :
                    changedEntity.getAuthorByAuthorId()
    );
    changedEntity.setAuthorId(
            incomeEntity.getAuthorId() != null ? incomeEntity.getAuthorId() : changedEntity.getAuthorId()
    );
    return changedEntity;
  }
}
