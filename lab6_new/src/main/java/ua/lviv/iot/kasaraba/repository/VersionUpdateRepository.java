package ua.lviv.iot.kasaraba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.kasaraba.model.VersionUpdate;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionUpdateRepository extends JpaRepository<VersionUpdate, Integer> {
}
