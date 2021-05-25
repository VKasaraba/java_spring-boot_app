package ua.lviv.iot.kasaraba.service.Implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.kasaraba.exception.NoSuchApplicationException;
import ua.lviv.iot.kasaraba.exception.NoSuchUserException;
import ua.lviv.iot.kasaraba.model.Application;
import ua.lviv.iot.kasaraba.model.Author;
import ua.lviv.iot.kasaraba.model.User;

import java.util.Set;

@Service
public class ApplicationService extends CommonServiceImplementation<Application, Integer> {

  private final JpaRepository<Application, Integer> jpaRepository;
  private final JpaRepository<User, Integer> userJpaRepository;

  public ApplicationService(JpaRepository<Application, Integer> jpaRepository, JpaRepository<Author, Integer> authorJpaRepository, JpaRepository<User, Integer> userJpaRepository) {
    this.jpaRepository = jpaRepository;
    this.userJpaRepository = userJpaRepository;
  }

  public Set<Application> getApplicationsByUserId(Integer userId){
    User user = userJpaRepository.findById(userId).get();
    if (user == null) throw new NoSuchUserException();
    return user.getApplications();
  }

  @Override
  protected JpaRepository<Application, Integer> getRepository() {
    return jpaRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchApplicationException();
  }

  @Override
  protected Application mergeEntities(Application changedEntity, Application incomeEntity) {
    changedEntity.setName(
            incomeEntity.getName() != null ? incomeEntity.getName() : changedEntity.getName()
    );
    changedEntity.setPriceInDol(
            incomeEntity.getPriceInDol() != null ? incomeEntity.getPriceInDol() : changedEntity.getPriceInDol()
    );
    changedEntity.setReleaseYear(
            incomeEntity.getReleaseYear() != null ? incomeEntity.getReleaseYear() : changedEntity.getReleaseYear()
    );
    changedEntity.setUsers(
            incomeEntity.getUsers() != null ? incomeEntity.getUsers() : changedEntity.getUsers()
    );
    changedEntity.setAuthorByAuthorId(
            incomeEntity.getAuthorByAuthorId() != null ? incomeEntity.getAuthorByAuthorId() :
                    changedEntity.getAuthorByAuthorId()
    );
    return changedEntity;
  }
}
