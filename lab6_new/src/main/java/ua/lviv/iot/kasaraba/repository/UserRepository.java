package ua.lviv.iot.kasaraba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.kasaraba.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
