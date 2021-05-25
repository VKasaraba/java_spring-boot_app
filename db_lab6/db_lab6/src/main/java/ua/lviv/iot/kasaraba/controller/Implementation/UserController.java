package ua.lviv.iot.kasaraba.controller.Implementation;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.kasaraba.DTO.UserDTO;
import ua.lviv.iot.kasaraba.controller.CommonController;
import ua.lviv.iot.kasaraba.model.User;
import ua.lviv.iot.kasaraba.service.Implementation.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController implements CommonController<UserDTO, User> {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping(value = "/api/user")
  public ResponseEntity<List<UserDTO>> getUsers() {
    List<User> users = userService.getEntities();
    List<UserDTO> usersDTO = createDTOs(users);

    return new ResponseEntity<>(usersDTO, HttpStatus.OK);
  }

  @GetMapping(value = "/api/user/{userId}")
  public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) {
    User user = userService.getEntity(userId);
    UserDTO userDTO = createDTO(user);

    return new ResponseEntity<>(userDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/api/user")
  public ResponseEntity<UserDTO> setUser(@RequestBody User user) {
    User newUser = userService.createEntity(user);
    UserDTO userDTO = createDTO(newUser);

    return new ResponseEntity<>(userDTO, HttpStatus.OK);
  }

  @PutMapping(value = "/api/user/{userId}")
  public ResponseEntity<UserDTO> updateUser(
          @RequestBody User user, @PathVariable Integer userId) {
    User newUser = userService.updateEntity(user, userId);
    UserDTO userDTO = createDTO(newUser);

    return new ResponseEntity<>(userDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/api/user/{userId}")
  public ResponseEntity<UserDTO> deleteUser(@PathVariable Integer userId) {
    userService.deleteEntity(userId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public List<UserDTO> createDTOs(Iterable<User> users) {
    Link link = linkTo(methodOn(UserController.class).getUsers()).withSelfRel();
    List<UserDTO> usersDTO = new ArrayList<>();
    for (User user : users) {
      Link selfLink = new Link(link.getHref() + "/" + user.getId());
      UserDTO userDTO = new UserDTO(user, selfLink);
      usersDTO.add(userDTO);
    }
    return usersDTO;
  }

  @Override
  public UserDTO createDTO(User user) {
    Link selfLink =
            linkTo(methodOn(UserController.class).getUser(user.getId()))
                    .withSelfRel();
    return new UserDTO(user, selfLink);
  }
}
