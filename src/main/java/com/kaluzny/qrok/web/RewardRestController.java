package com.kaluzny.qrok.web;

import com.kaluzny.qrok.domain.Reward;
import com.kaluzny.qrok.exception.AlreadyExistsException;
import com.kaluzny.qrok.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;

@Service
@RestController("reward")
@RequestMapping("/api/v1/")
public class RewardRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardRestController.class);

    private RewardService rewardService;

    @Inject
    public RewardRestController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    /* Create a reward */
    @RequestMapping(
            value = "reward",
            method = RequestMethod.POST)
    public ResponseEntity<Reward> createReward(@RequestBody Reward reward, UriComponentsBuilder ucBuilder) {
        LOGGER.debug(">>> Creating address with id: " + reward.getId());
        if (rewardService.isExist(reward)) {
            LOGGER.debug("An address with name " + reward.getId() + "exist.");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        rewardService.save(reward);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("reward/{id}").buildAndExpand(reward.getId()).toUri());
        return new ResponseEntity<>(reward, headers, HttpStatus.CREATED);
    }

    /* Reading single reward */
    @RequestMapping(
            value = "reward/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reward> getReward(@PathVariable("id") int id) {
        LOGGER.debug("Fetching address with id: " + id);
        Reward reward = rewardService.findById(id);
        if (reward == null) {
            LOGGER.debug("Address with id: " + id + ", not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reward, HttpStatus.OK);
    }

    /*Update a reward*/
    @RequestMapping(
            value = "rewards/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Reward> updateBookFromDB(@PathVariable("id") int id,
                                                 @RequestBody Reward reward) {
        LOGGER.debug(">>> Updating book with id: " + id);
        Reward currentReward = rewardService.findById(id);

        if (currentReward == null) {
            LOGGER.debug("<<< Book with id: " + id + ", not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentReward.setTitle(reward.getTitle());
        currentReward.setYear(reward.getYear());

        rewardService.update(currentReward);
        return new ResponseEntity<>(currentReward, HttpStatus.OK);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(AlreadyExistsException exception) {
        return exception.getMessage();
    }
}
