package ua.lviv.iot.kasaraba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.kasaraba.model.Secured;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuredRepository extends JpaRepository<Secured, String> {
}
