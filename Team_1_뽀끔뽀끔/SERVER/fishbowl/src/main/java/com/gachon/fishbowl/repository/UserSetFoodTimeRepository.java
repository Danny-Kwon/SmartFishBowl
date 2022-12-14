package com.gachon.fishbowl.repository;

import com.gachon.fishbowl.entity.Sensing;
import com.gachon.fishbowl.entity.UserSet;
import com.gachon.fishbowl.entity.UserSetFoodTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSetFoodTimeRepository extends JpaRepository<UserSetFoodTime, Long> {
    Optional<List<UserSetFoodTime>> findAllByUserSet(UserSet userSet);
    Optional<UserSetFoodTime> findByUserSet(UserSet userSet);
}
