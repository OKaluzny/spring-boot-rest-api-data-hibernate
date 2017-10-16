package com.kaluzny.qrok.service;

import com.kaluzny.qrok.domain.Reward;

public interface RewardService {

    boolean isExist(Reward reward);

    Reward save(Reward reward);

    Reward findById(int id);

    Reward update(Reward reward);
}
