package ua.lviv.iot.kasaraba.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.kasaraba.model.VirtualWallet;

import java.util.Objects;

public class VirtualWalletDTO extends ResourceSupport {
  private final VirtualWallet virtualWallet;

  public VirtualWalletDTO(VirtualWallet virtualWallet, Link selfLink) {
    this.virtualWallet = virtualWallet;
    add(selfLink);
  }

  public Integer getVirtualWalletId() {
    return virtualWallet.getId();
  }

  public Integer getVirtualWalletBalanceInDol() {
    return virtualWallet.getBalanceInDol();
  }

  public String getVirtualWalletSubscriptionPlan() {
    return virtualWallet.getSubscriptionPlan();
  }

  public Byte getVirtualWalletPayAutomatically() {
    return virtualWallet.getPayAutomatically();
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

    VirtualWalletDTO that = (VirtualWalletDTO) o;

    return Objects.equals(virtualWallet, that.virtualWallet);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (virtualWallet != null ? virtualWallet.hashCode() : 0);
    return result;
  }
}
