package com.kaluzny.qrok.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RewardRepository extends JpaRepository<Reward, Integer> {
}