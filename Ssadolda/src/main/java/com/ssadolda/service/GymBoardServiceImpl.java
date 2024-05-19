package com.ssadolda.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssadolda.model.dao.GymDao;
import com.ssadolda.model.dao.GymStatisticsDao;
import com.ssadolda.model.dto.Gym;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class GymBoardServiceImpl implements GymBoardService {

	private final GymDao gymdao;
	private final GymStatisticsDao gymstatisticdao;
	@Transactional
	@Override
	public int registGym(Gym gym) {
		   int result = gymdao.insertGym(gym);
	        if (result != 0) {
	        	gymdao.insertGymStatistics(gym.getGymSeq());
	        	System.out.println("생성된gmseq : "+gym.getGymSeq());
	        }
	        return result;
	}
	
	@Override
	public int insertGymStatistics(int gymSeq) {
		return gymdao.insertGymStatistics(gymSeq);
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
	
	@Override
	  public void incrementViewCount(int gymSeq) {
	        gymstatisticdao.incrementViewCount(gymSeq); // 조회수 증가 로직 호출
	    }
	
	

}
