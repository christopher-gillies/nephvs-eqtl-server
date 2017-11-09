package org.sampsonlab.nephvseqtlsever.repositories;

import java.util.List;

import org.sampsonlab.nephvseqtlsever.entities.PeerEQTL;
import org.sampsonlab.nephvseqtlsever.entities.PeerEQTLKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface PeerEQTLRepository extends Repository<PeerEQTL, PeerEQTLKey> {

	@Query("select p from PeerEQTL p where p.key.entrezId = ?1")
	List<PeerEQTL> findByEntrezId(Long entrezId);

	@Query("select p from PeerEQTL p inner join fetch p.gene g where g.symbol = ?1")
	List<PeerEQTL> findByGeneSymbol(String symbol);
}
