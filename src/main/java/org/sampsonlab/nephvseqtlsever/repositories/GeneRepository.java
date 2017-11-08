package org.sampsonlab.nephvseqtlsever.repositories;

import java.util.List;

import org.sampsonlab.nephvseqtlsever.entities.Gene;
import org.sampsonlab.nephvseqtlsever.entities.PeerEQTL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface GeneRepository extends Repository<Gene, Long> { 
	@Query("select g.symbol from Gene g order by g.symbol")
	List<String> findAllSymbols();
}
