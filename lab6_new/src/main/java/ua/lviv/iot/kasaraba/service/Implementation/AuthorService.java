package ua.lviv.iot.kasaraba.service.Implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.kasaraba.exception.NoSuchAuthorException;
import ua.lviv.iot.kasaraba.model.Author;

@Service
public class AuthorService extends CommonServiceImplementation<Author, Integer>{
  private final JpaRepository<Author, Integer> jpaRepository;

  public AuthorService(JpaRepository<Author, Integer> jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  protected JpaRepository<Author, Integer> getRepository() {
    return jpaRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchAuthorException();
  }

  @Override
  protected Author mergeEntities(Author changedEntity, Author incomeEntity) {
    return changedEntity;
  }
}
