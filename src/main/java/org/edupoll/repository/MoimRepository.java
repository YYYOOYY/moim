package org.edupoll.repository;

import java.util.List;

import org.edupoll.model.entity.Moim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoimRepository extends JpaRepository<Moim, String>{
	
	List<Moim> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleKeyword, String descriptionKeyowrd);
}
