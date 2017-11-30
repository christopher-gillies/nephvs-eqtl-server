package org.sampsonlab.nephvseqtlserver.repositories;

import java.util.List;

import org.sampsonlab.nephvseqtlserver.entities.Gene;
import org.sampsonlab.nephvseqtlserver.entities.PeerEQTL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface GeneRepository extends Repository<Gene, Long> { 
	@Query("select g.symbol from Gene g order by g.symbol")
	List<String> findAllSymbols();
}
