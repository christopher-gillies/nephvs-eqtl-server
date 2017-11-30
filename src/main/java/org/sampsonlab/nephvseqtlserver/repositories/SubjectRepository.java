package org.sampsonlab.nephvseqtlserver.repositories;

import java.util.List;


import org.sampsonlab.nephvseqtlserver.entities.Subject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface SubjectRepository extends Repository<Subject, Integer> { 
	
	@Cacheable("subjectIds")
	@Query("select s.subjectId from Subject s")
	List<Integer> findAllIds();
}
