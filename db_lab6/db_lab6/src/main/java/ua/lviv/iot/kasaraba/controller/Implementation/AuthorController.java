package ua.lviv.iot.kasaraba.controller.Implementation;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.kasaraba.DTO.AuthorDTO;
import ua.lviv.iot.kasaraba.controller.CommonController;
import ua.lviv.iot.kasaraba.model.Author;
import ua.lviv.iot.kasaraba.service.Implementation.AuthorService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class AuthorController implements CommonController<AuthorDTO, Author> {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping(value = "/api/author")
  public ResponseEntity<List<AuthorDTO>> getAuthors() {
    List<Author> authors = authorService.getEntities();
    List<AuthorDTO> authorsDTO = createDTOs(authors);

    return new ResponseEntity<>(authorsDTO, HttpStatus.OK);
  }

  @GetMapping(value = "/api/author/{authorId}")
  public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Integer authorId) {
    Author author = authorService.getEntity(authorId);
    AuthorDTO authorDTO = createDTO(author);

    return new ResponseEntity<>(authorDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/api/author")
  public ResponseEntity<AuthorDTO> setAuthor(@RequestBody Author author) {
    Author newAuthor = authorService.createEntity(author);
    AuthorDTO authorDTO = createDTO(newAuthor);

    return new ResponseEntity<>(authorDTO, HttpStatus.OK);
  }

  @PutMapping(value = "/api/author/{authorId}")
  public ResponseEntity<AuthorDTO> updateAuthor(
          @RequestBody Author author, @PathVariable Integer authorId) {
    Author newAuthor = authorService.updateEntity(author, authorId);
    AuthorDTO authorDTO = createDTO(newAuthor);

    return new ResponseEntity<>(authorDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/api/author/{authorId}")
  public ResponseEntity<AuthorDTO> deleteAuthor(@PathVariable Integer authorId) {
    authorService.deleteEntity(authorId);

    return new ResponseEntity<>(HttpStatus.OK);
  }


  @Override
  public List<AuthorDTO> createDTOs(Iterable<Author> authors) {
    Link link = linkTo(methodOn(AuthorController.class).getAuthors()).withSelfRel();
    List<AuthorDTO> authorsDTO = new ArrayList<>();
    for (Author author : authors) {
      Link selfLink = new Link(link.getHref() + "/" + author.getId());
      AuthorDTO authorDTO = new AuthorDTO(author, selfLink);
      authorsDTO.add(authorDTO);
    }
    return authorsDTO;
  }

  @Override
  public AuthorDTO createDTO(Author author) {
    Link selfLink =
            linkTo(methodOn(ApplicationController.class).getApplication(author.getId()))
                    .withSelfRel();
    return new AuthorDTO(author, selfLink);
  }
}
