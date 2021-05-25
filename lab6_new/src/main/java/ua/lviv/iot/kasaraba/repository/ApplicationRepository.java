package ua.lviv.iot.kasaraba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.kasaraba.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
