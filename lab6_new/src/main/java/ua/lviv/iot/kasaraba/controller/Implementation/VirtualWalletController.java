package ua.lviv.iot.kasaraba.controller.Implementation;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.kasaraba.DTO.VirtualWalletDTO;
import ua.lviv.iot.kasaraba.controller.CommonController;
import ua.lviv.iot.kasaraba.model.VirtualWallet;
import ua.lviv.iot.kasaraba.service.Implementation.VirtualWalletService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class VirtualWalletController implements CommonController<VirtualWalletDTO, VirtualWallet> {
  private final VirtualWalletService virtualWalletService;

  public VirtualWalletController(VirtualWalletService virtualWalletService) {
    this.virtualWalletService = virtualWalletService;
  }


  @GetMapping(value = "/api/virtualWallet")
  public ResponseEntity<List<VirtualWalletDTO>> getVirtualWallets() {
    List<VirtualWallet> virtualWallets = virtualWalletService.getEntities();
    List<VirtualWalletDTO> virtualWalletDTOs = createDTOs(virtualWallets);

    return new ResponseEntity<>(virtualWalletDTOs, HttpStatus.OK);
  }

  @GetMapping(value = "/api/virtualWallet/{virtualWalletId}")
  public ResponseEntity<VirtualWalletDTO> getVirtualWallet(@PathVariable Integer virtualWalletId) {
    VirtualWallet virtualWallet = virtualWalletService.getEntity(virtualWalletId);
    VirtualWalletDTO virtualWalletDTO = createDTO(virtualWallet);

    return new ResponseEntity<>(virtualWalletDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/api/virtualWallet")
  public ResponseEntity<VirtualWalletDTO> setVirtualWallet(@RequestBody VirtualWallet virtualWallet) {
    VirtualWallet newVirtualWallet = virtualWalletService.createEntity(virtualWallet);
    VirtualWalletDTO virtualWalletDTO = createDTO(newVirtualWallet);

    return new ResponseEntity<>(virtualWalletDTO, HttpStatus.OK);
  }

  @PutMapping(value = "/api/virtualWallet/{virtualWalletId}")
  public ResponseEntity<VirtualWalletDTO> updateVirtualWallet(
          @RequestBody VirtualWallet virtualWallet, @PathVariable Integer virtualWalletId) {
    VirtualWallet newVirtualWallet = virtualWalletService.updateEntity(virtualWallet, virtualWalletId);
    VirtualWalletDTO virtualWalletDTO = createDTO(newVirtualWallet);

    return new ResponseEntity<>(virtualWalletDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/api/virtualWallet/{virtualWalletId}")
  public ResponseEntity<VirtualWalletDTO> deleteVirtualWallet(@PathVariable Integer virtualWalletId) {
    virtualWalletService.deleteEntity(virtualWalletId);

    return new ResponseEntity<>(HttpStatus.OK);
  }


  @Override
  public List<VirtualWalletDTO> createDTOs(Iterable<VirtualWallet> virtualWallets) {
    Link link = linkTo(methodOn(VirtualWalletController.class).getVirtualWallets()).withSelfRel();
    List<VirtualWalletDTO> virtualWalletDTOs = new ArrayList<>();
    for (VirtualWallet virtualWallet : virtualWallets) {
      Link selfLink = new Link(link.getHref() + "/" + virtualWallet.getId());
      VirtualWalletDTO virtualWalletDTO = new VirtualWalletDTO(virtualWallet, selfLink);
      virtualWalletDTOs.add(virtualWalletDTO);
    }
    return virtualWalletDTOs;
  }

  @Override
  public VirtualWalletDTO createDTO(VirtualWallet virtualWallet) {
    Link selfLink =
            linkTo(methodOn(VirtualWalletController.class).getVirtualWallet(virtualWallet.getId()))
                    .withSelfRel();
    return new VirtualWalletDTO(virtualWallet, selfLink);
  }
}
