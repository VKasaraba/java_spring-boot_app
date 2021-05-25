package ua.lviv.iot.kasaraba.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.kasaraba.model.Feedback;

import java.util.Objects;

public class FeedbackDTO extends ResourceSupport {
  private final Feedback feedback;

  public FeedbackDTO(Feedback feedback, Link selfLink) {
    this.feedback = feedback;
    add(selfLink);
  }

  public Integer getFeedbackId() {
    return feedback.getId();
  }

  public Integer getFeedbackStarsGiven() {
    return feedback.getStarsGiven();
  }

  public String getFeedbackTextFeedback() {
    return feedback.getTextFeedback();
  }

  public Byte getFeedbackWouldRecommend() {
    return feedback.getWouldRecommend();
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

    FeedbackDTO that = (FeedbackDTO) o;

    return Objects.equals(feedback, that.feedback);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
    return result;
  }
}
