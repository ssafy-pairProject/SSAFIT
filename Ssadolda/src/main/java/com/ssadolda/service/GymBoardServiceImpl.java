package com.ssadolda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssadolda.model.dao.GymDao;
import com.ssadolda.model.dto.Gym;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class GymBoardServiceImpl implements GymBoardService {

	private final GymDao gymdao;
	
	@Override
	public int registGym(Gym gym) {
		return gymdao.insertGym(gym);
	}

	@Override
	public int modifyGym(Gym gym) {
		return gymdao.updateGym(gym);
	}

	@Override
	public int deleteGym(int gymSeq) {
		return gymdao.deleteGym(gymSeq);
	}

	@Override
	public List<Gym> readAllGym() {
		return gymdao.selectAllGym();
	}

	@Override
	public Gym gymDetail(int gymSeq) {
		
		return gymdao.detailGym(gymSeq);
	}

	@Override
	public int gymLikeBtn() {
		return 0;
	}

	@Override
	public List<Gym> arrayGym() {
		// TODO Auto-generated method stub
		return null;
	}

}
