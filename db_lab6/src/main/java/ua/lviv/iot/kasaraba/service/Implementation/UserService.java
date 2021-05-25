package ua.lviv.iot.kasaraba.service.Implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.kasaraba.exception.NoSuchApplicationException;
import ua.lviv.iot.kasaraba.exception.NoSuchUserException;
import ua.lviv.iot.kasaraba.model.Application;
import ua.lviv.iot.kasaraba.model.User;

import java.util.Set;

@Service
public class UserService extends CommonServiceImplementation<User, Integer>{
  private final JpaRepository<User, Integer> jpaRepository;
  private final JpaRepository<Application, Integer> applicationJpaRepository;

  public UserService(JpaRepository<User, Integer> jpaRepository, JpaRepository<Application, Integer> applicationJpaRepository) {
    this.jpaRepository = jpaRepository;
    this.applicationJpaRepository = applicationJpaRepository;
  }

  public Set<User> getUsersByApplicationId(Integer applicationId){
    Application application = applicationJpaRepository.findById(applicationId).get();
    if (application == null) throw new NoSuchApplicationException();
    return application.getUsers();
  }


  @Override
  protected JpaRepository<User, Integer> getRepository() {
    return jpaRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchUserException();
  }

  @Override
  protected User mergeEntities(User changedEntity, User incomeEntity) {
    changedEntity.setEmail(
            incomeEntity.getEmail() != null ? incomeEntity.getEmail() : changedEntity.getEmail()
    );
    changedEntity.setApplications(
            incomeEntity.getApplications() != null ? incomeEntity.getApplications() : changedEntity.getApplications()
    );
    changedEntity.setFirstName(
            incomeEntity.getFirstName() != null ? incomeEntity.getFirstName() : changedEntity.getFirstName()
    );
    changedEntity.setLastName(
            incomeEntity.getLastName() != null ? incomeEntity.getLastName() : changedEntity.getLastName()
    );
    changedEntity.setMiddleName(
            incomeEntity.getMiddleName() != null ? incomeEntity.getMiddleName() : changedEntity.getMiddleName()
    );
    changedEntity.setNationality(
            incomeEntity.getNationality() != null ? incomeEntity.getNationality() : changedEntity.getNationality()
    );
    changedEntity.setYearOfRegistration(
            incomeEntity.getYearOfRegistration() != null ? incomeEntity.getYearOfRegistration() :
                    changedEntity.getYearOfRegistration()
    );
    return changedEntity;
  }
}
