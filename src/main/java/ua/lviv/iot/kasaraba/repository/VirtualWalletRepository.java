package ua.lviv.iot.kasaraba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.iot.kasaraba.model.VirtualWallet;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualWalletRepository extends JpaRepository<VirtualWallet, Integer> {
}
