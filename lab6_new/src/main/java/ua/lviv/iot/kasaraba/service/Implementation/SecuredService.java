package ua.lviv.iot.kasaraba.service.Implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.kasaraba.exception.NoSuchSecuredException;
import ua.lviv.iot.kasaraba.model.Secured;

@Service
public class SecuredService extends CommonServiceImplementation<Secured, String>{
  private final JpaRepository<Secured, String> jpaRepository;

  public SecuredService(JpaRepository<Secured, String> jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  protected JpaRepository<Secured, String> getRepository() {
    return jpaRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchSecuredException();
  }

  @Override
  protected Secured mergeEntities(Secured changedEntity, Secured incomeEntity) {
    changedEntity.setCardNumber(
            incomeEntity.getCardNumber() != null ? incomeEntity.getCardNumber() : changedEntity.getCardNumber()

    );
    changedEntity.setPassword(
            incomeEntity.getPassword() != null ? incomeEntity.getPassword() : changedEntity.getPassword()

    );
    return null;
  }
}
