package ua.lviv.iot.kasaraba.service.Implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.kasaraba.exception.NoSuchSingleDeveloperException;
import ua.lviv.iot.kasaraba.model.SingleDeveloper;

@Service
public class SingleDeveloperService extends CommonServiceImplementation<SingleDeveloper, Integer>{
  private final JpaRepository<SingleDeveloper, Integer> jpaRepository;

  public SingleDeveloperService(JpaRepository<SingleDeveloper, Integer> jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  protected JpaRepository<SingleDeveloper, Integer> getRepository() {
    return jpaRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchSingleDeveloperException();
  }

  @Override
  protected SingleDeveloper mergeEntities(SingleDeveloper changedEntity, SingleDeveloper incomeEntity) {
    changedEntity.setLastName(
            incomeEntity.getLastName() != null ? incomeEntity.getLastName() : changedEntity.getLastName()
    );
    changedEntity.setFirstName(
            incomeEntity.getFirstName() != null ? incomeEntity.getFirstName() : changedEntity.getFirstName()
    );
    changedEntity.setAuthorByAuthorId(
            incomeEntity.getAuthorByAuthorId() != null ? incomeEntity.getAuthorByAuthorId() :
                    changedEntity.getAuthorByAuthorId()
    );
    changedEntity.setAuthorId(
            incomeEntity.getAuthorId() != null ? incomeEntity.getAuthorId() : changedEntity.getAuthorId()
    );
    return null;
  }
}
