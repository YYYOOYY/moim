package org.edupoll.service;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.dto.response.MoimResponseData;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class MoimService {

	@Autowired
	MoimRepository moimRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<MoimResponseData> findByAll(int page) {
		List<Moim> moims = moimRepository.findAll(PageRequest.of(page - 1, 12, Sort.by(Direction.ASC, "targetDate"))).toList();
		
		List<MoimResponseData> respList = new ArrayList<>();
		
		for(Moim moim : moims) {
			respList.add(new MoimResponseData(moim));
		}
		
		return respList;
	}

	public void create(Moim moim, String logonId) {
		moim.setCurrentPerson(1);
		User user = userRepository.findById(logonId).get();
		moim.setManager(user);;

		moimRepository.save(moim);

	}

	public List<String> page() {
		long cnt = moimRepository.count();
		List<String> pages = new ArrayList<>();
		for (int i = 1; i <= cnt / 12 + (cnt % 12 > 0 ? 1 : 0); i++) {
			pages.add(String.valueOf(i));
		}
		return pages;
	}

	public MoimResponseData findByPost(String moimId) {
		Moim moim = moimRepository.findById(moimId).orElse(null);
		MoimResponseData respMoim = new MoimResponseData(moim);
		
		return respMoim;
	}	

}
