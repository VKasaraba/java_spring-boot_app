package ua.lviv.iot.kasaraba.service.Implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ua.lviv.iot.kasaraba.exception.NoSuchVirtualWalletException;
import ua.lviv.iot.kasaraba.model.VirtualWallet;

@Service
public class VirtualWalletService extends CommonServiceImplementation<VirtualWallet, Integer>{
  @Override
  protected JpaRepository<VirtualWallet, Integer> getRepository() {
    return null;
  }

  @Override
  protected void throwException() {
    throw new NoSuchVirtualWalletException();
  }

  @Override
  protected VirtualWallet mergeEntities(VirtualWallet changedEntity, VirtualWallet incomeEntity) {
    changedEntity.setBalanceInDol(
            incomeEntity.getBalanceInDol() != null ? incomeEntity.getBalanceInDol() : changedEntity.getBalanceInDol()
    );
    changedEntity.setPayAutomatically(
            incomeEntity.getPayAutomatically() != null ? incomeEntity.getPayAutomatically() :
                    changedEntity.getPayAutomatically()
    );
    changedEntity.setSecuredBySecuredCardNumber(
            incomeEntity.getSecuredBySecuredCardNumber() != null ? incomeEntity.getSecuredBySecuredCardNumber() :
                    changedEntity.getSecuredBySecuredCardNumber()
    );
    changedEntity.setSubscriptionPlan(
            incomeEntity.getSubscriptionPlan() != null ? incomeEntity.getSubscriptionPlan() :
                    changedEntity.getSubscriptionPlan()
    );
    changedEntity.setUserByUserId(
            incomeEntity.getUserByUserId() != null ? incomeEntity.getUserByUserId() : changedEntity.getUserByUserId()
    );
    return null;
  }
}
