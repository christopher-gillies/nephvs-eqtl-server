package org.sampsonlab.nephvseqtlsever.repositories;

import java.util.List;

import org.sampsonlab.nephvseqtlsever.entities.PeerEQTL;
import org.sampsonlab.nephvseqtlsever.entities.PeerEQTLKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface PeerEQTLRepository extends Repository<PeerEQTL, PeerEQTLKey> {

	@Query("select p from PeerEQTL p where p.key.entrezId = ?1")
	List<PeerEQTL> findByEntrezId(Long entrezId);

	@Query("select p, g.symbol, v.dbSNPId from PeerEQTL p inner join fetch p.gene g inner join fetch p.variant v where g.symbol = ?1 and p.pVal < ?2")
	List<Object[]> findByGeneSymbolAndMaxPVal(String symbol, Double maxPVal);
}
