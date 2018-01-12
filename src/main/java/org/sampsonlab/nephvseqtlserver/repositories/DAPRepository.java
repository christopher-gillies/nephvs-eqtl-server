package org.sampsonlab.nephvseqtlserver.repositories;


import java.util.Set;

import org.sampsonlab.nephvseqtlserver.entities.DAPGeneSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface DAPRepository extends Repository<DAPGeneSummary, Long> { 
	
	@Query("select dg from DAPGeneSummary dg join fetch dg.variants as dv inner join fetch dg.gene as g inner join fetch g.geneCoord inner join fetch dv.variant v where g.entrezId = ?1")
	Set<DAPGeneSummary> findByEntrezId(Long id);
	
	@Query("select dg from DAPGeneSummary dg inner join fetch dg.gene as g where dg.fdr < ?1")
	Set<DAPGeneSummary> findByFDR(Double fdr);
}
