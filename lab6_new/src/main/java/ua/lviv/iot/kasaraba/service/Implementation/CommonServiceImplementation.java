package ua.lviv.iot.kasaraba.service.Implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.kasaraba.service.CommonService;

import javax.transaction.Transactional;
import java.util.List;

public abstract class CommonServiceImplementation<T, ID> implements CommonService<T, ID> {

  protected abstract JpaRepository<T, ID> getRepository();

  protected abstract void throwException();

  protected abstract T mergeEntities(T changedEntity, T incomeEntity);

  @Override
  public List<T> getEntities() {
    return getRepository().findAll();
  }

  @Override
  public T getEntity(ID entityId) {
    if (getRepository().existsById(entityId)) {
      return getRepository().findById(entityId).get();
    }
    throwException();
    return null;
  }

  @Override
  @Transactional
  public T createEntity(T entity) {
    return getRepository().save(entity);
  }

  @Override
  @Transactional
  public T updateEntity(T entity, ID entityId) {

    if (getRepository().existsById(entityId)) {
      T newEntity = getRepository().findById(entityId).get();

      return getRepository().save(mergeEntities(newEntity, entity));
    }
    throwException();
    return null;
  }

  @Override
  @Transactional
  public void deleteEntity(ID entityId) {
    if (getRepository().existsById(entityId)) {
      getRepository().deleteById(entityId);
      return;
    }
    throwException();
  }
}