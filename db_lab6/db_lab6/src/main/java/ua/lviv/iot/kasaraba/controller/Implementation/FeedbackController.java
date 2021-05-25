package ua.lviv.iot.kasaraba.controller.Implementation;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.kasaraba.DTO.ApplicationDTO;
import ua.lviv.iot.kasaraba.DTO.FeedbackDTO;
import ua.lviv.iot.kasaraba.controller.CommonController;
import ua.lviv.iot.kasaraba.model.Application;
import ua.lviv.iot.kasaraba.model.Feedback;
import ua.lviv.iot.kasaraba.service.Implementation.FeedbackService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class FeedbackController implements CommonController<FeedbackDTO, Feedback> {

  private final FeedbackService feedbackService;

  public FeedbackController(FeedbackService feedbackService) {
    this.feedbackService = feedbackService;
  }


  @GetMapping(value = "/api/feedback")
  public ResponseEntity<List<FeedbackDTO>> getFeedbacks() {
    List<Feedback> feedbacks = feedbackService.getEntities();
    List<FeedbackDTO> feedbacksDTO = createDTOs(feedbacks);

    return new ResponseEntity<>(feedbacksDTO, HttpStatus.OK);
  }

  @GetMapping(value = "/api/feedback/{feedbackId}")
  public ResponseEntity<FeedbackDTO> getFeedback(@PathVariable Integer feedbackId) {
    Feedback feedback = feedbackService.getEntity(feedbackId);
    FeedbackDTO feedbackDTO = createDTO(feedback);

    return new ResponseEntity<>(feedbackDTO, HttpStatus.OK);
  }

  @PostMapping(value = "/api/feedback")
  public ResponseEntity<FeedbackDTO> setFeedback(@RequestBody Feedback feedback) {
    Feedback newFeedback = feedbackService.createEntity(feedback);
    FeedbackDTO feedbackDTO = createDTO(newFeedback);

    return new ResponseEntity<>(feedbackDTO, HttpStatus.OK);
  }

  @PutMapping(value = "/api/feedback/{feedbackId}")
  public ResponseEntity<FeedbackDTO> updateFeedback(
          @RequestBody Feedback feedback, @PathVariable Integer feedbackId) {
    Feedback newFeedback = feedbackService.updateEntity(feedback, feedbackId);
    FeedbackDTO feedbackDTO = createDTO(newFeedback);

    return new ResponseEntity<>(feedbackDTO, HttpStatus.OK);
  }

  @DeleteMapping(value = "/api/feedback/{feedbackId}")
  public ResponseEntity<FeedbackDTO> deleteFeedback(@PathVariable Integer feedbackId) {
    feedbackService.deleteEntity(feedbackId);

    return new ResponseEntity<>(HttpStatus.OK);
  }


  @Override
  public List<FeedbackDTO> createDTOs(Iterable<Feedback> feedbacks) {
    Link link = linkTo(methodOn(FeedbackController.class).getFeedbacks()).withSelfRel();
    List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
    for (Feedback feedback : feedbacks) {
      Link selfLink = new Link(link.getHref() + "/" + feedback.getId());
      FeedbackDTO feedbackDTO = new FeedbackDTO(feedback, selfLink);
      feedbackDTOs.add(feedbackDTO);
    }
    return feedbackDTOs;
  }

  @Override
  public FeedbackDTO createDTO(Feedback feedback) {
    Link selfLink =
            linkTo(methodOn(FeedbackController.class).getFeedback(feedback.getId()))
                    .withSelfRel();
    return new FeedbackDTO(feedback, selfLink);
  }

}
