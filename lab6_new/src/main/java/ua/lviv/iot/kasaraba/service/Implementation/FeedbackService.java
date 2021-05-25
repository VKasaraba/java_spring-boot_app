package ua.lviv.iot.kasaraba.service.Implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.kasaraba.exception.NoSuchFeedbackException;
import ua.lviv.iot.kasaraba.model.Feedback;

@Service
public class FeedbackService extends CommonServiceImplementation<Feedback, Integer>{
  private final JpaRepository<Feedback, Integer> jpaRepository;

  public FeedbackService(JpaRepository<Feedback, Integer> jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  protected JpaRepository<Feedback, Integer> getRepository() {
    return jpaRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchFeedbackException();
  }

  @Override
  protected Feedback mergeEntities(Feedback changedEntity, Feedback incomeEntity) {
    changedEntity.setStarsGiven(
            incomeEntity.getStarsGiven() != null ? incomeEntity.getStarsGiven() : changedEntity.getStarsGiven()
    );
    changedEntity.setTextFeedback(
            incomeEntity.getTextFeedback() != null ? incomeEntity.getTextFeedback() : changedEntity.getTextFeedback()
    );
    changedEntity.setWouldRecommend(
            incomeEntity.getWouldRecommend() != null ? incomeEntity.getWouldRecommend() : changedEntity.getWouldRecommend()
    );
    changedEntity.setApplicationByApplicationId(
            incomeEntity.getApplicationByApplicationId() != null ? incomeEntity.getApplicationByApplicationId() :
                    changedEntity.getApplicationByApplicationId()
    );
    changedEntity.setUserByUserId(
            incomeEntity.getUserByUserId() != null ? incomeEntity.getUserByUserId() : changedEntity.getUserByUserId()
    );
    return changedEntity;
  }
}
