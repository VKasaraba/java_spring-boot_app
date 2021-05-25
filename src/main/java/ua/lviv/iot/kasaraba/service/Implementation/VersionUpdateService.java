package ua.lviv.iot.kasaraba.service.Implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.kasaraba.exception.NoSuchVersionUpdateException;
import ua.lviv.iot.kasaraba.model.VersionUpdate;

@Service
public class VersionUpdateService extends CommonServiceImplementation<VersionUpdate, Integer>{
  @Override
  protected JpaRepository<VersionUpdate, Integer> getRepository() {
    return null;
  }

  @Override
  protected void throwException() {
    throw new NoSuchVersionUpdateException();
  }

  @Override
  protected VersionUpdate mergeEntities(VersionUpdate changedEntity, VersionUpdate incomeEntity) {
    changedEntity.setThingsUpdated(
            incomeEntity.getThingsUpdated() != null ? incomeEntity.getThingsUpdated() : changedEntity.getThingsUpdated()
    );
    changedEntity.setApplicationByApplicationId(
            incomeEntity.getApplicationByApplicationId() != null ? incomeEntity.getApplicationByApplicationId() :
                    changedEntity.getApplicationByApplicationId()
    );
    changedEntity.setUpdateDay(
            incomeEntity.getUpdateDay() != null ? incomeEntity.getUpdateDay() : changedEntity.getUpdateDay()
    );
    return changedEntity;
  }
}
