package org.edupoll.repository;

import java.util.List;

import org.edupoll.model.entity.Reply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	List<Reply> findByMoimId(String moimId, Pageable pageable);
}
