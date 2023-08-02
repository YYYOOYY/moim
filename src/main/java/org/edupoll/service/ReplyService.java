package org.edupoll.service;

import java.util.List;

import org.edupoll.model.dto.request.AddReplyRequestData;
import org.edupoll.model.entity.Reply;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
	
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	MoimRepository moimRepository;
	
	public void create(AddReplyRequestData data) {

		Reply reply = new Reply();
		reply.setText(data.getText());
		reply.setPassword(data.getPassword());
		reply.setMoim(moimRepository.findById(data.getMoimId()).get());
		replyRepository.save(reply);
		
		return;
	}
	
	public List<Reply> findByAll(String moimId, int p) {
		
		return replyRepository.findByMoimId(moimId, PageRequest.of(p - 1, 5, Sort.by(Direction.ASC, "id")));
		
	}

	public void delete(Integer id) {
		
		replyRepository.deleteById(id);
	}
}
