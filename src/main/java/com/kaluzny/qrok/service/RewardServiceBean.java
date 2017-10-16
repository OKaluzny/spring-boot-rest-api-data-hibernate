package com.kaluzny.qrok.service;

import com.kaluzny.qrok.domain.Reward;
import com.kaluzny.qrok.domain.RewardRepository;
import com.kaluzny.qrok.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Validated
@Transactional
public class RewardServiceBean implements RewardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardService.class);

    private RewardRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    public RewardServiceBean(RewardRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isExist(Reward reward) {
        return findById(reward.getId()) != null;
    }

    @Override
    public Reward save(Reward reward) {
        LOGGER.debug("Save {}", reward);
        Reward existing = repository.findOne(reward.getId());
        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a book with id = %s", reward.getId()));
        }
        return repository.save(reward);
    }

    @Override
    public Reward findById(int id) {
        LOGGER.debug("Search book by id: " + id);
        return repository.findOne(id);
    }

    @Override
    public Reward update(Reward reward) {
        LOGGER.debug("Author with id: " + reward.getId() + " updated! ");
        if (!entityManager.contains(reward))
            reward = entityManager.merge(reward);
        return reward;
    }
}
