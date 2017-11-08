package org.sampsonlab.nephvseqtlsever;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface PeerEQTLRepository extends Repository<PeerEQTL, PeerEQTLKey> {

	@Query("select p from PeerEQTL p where p.key.entrezId = ?1")
	List<PeerEQTL> findByEntrezId(Integer entrezId);
	
}
