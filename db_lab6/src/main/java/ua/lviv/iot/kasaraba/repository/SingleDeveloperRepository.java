package ua.lviv.iot.kasaraba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.kasaraba.model.SingleDeveloper;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleDeveloperRepository extends JpaRepository<SingleDeveloper, Integer> {
}
