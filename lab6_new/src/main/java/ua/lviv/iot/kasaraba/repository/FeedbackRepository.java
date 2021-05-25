package ua.lviv.iot.kasaraba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.kasaraba.model.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
