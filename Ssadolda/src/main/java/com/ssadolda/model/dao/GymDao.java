package com.ssadolda.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssadolda.model.dto.Gym;

@Repository
public interface GymDao {
	
public int insertGym(Gym gym);

public int updateGym(Gym gym);

public int deleteGym(int gymSeq);

public List<Gym> selectAllGym();

public Gym detailGym(int gymSeq);

public List<Gym> arrayGym();

public int insertGymStatistics(int gymSeq);


}
