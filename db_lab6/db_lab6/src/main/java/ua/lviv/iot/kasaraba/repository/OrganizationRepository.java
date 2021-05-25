package ua.lviv.iot.kasaraba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.kasaraba.model.Organization;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
