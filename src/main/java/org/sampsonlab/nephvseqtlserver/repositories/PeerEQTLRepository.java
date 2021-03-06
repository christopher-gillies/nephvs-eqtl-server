package org.sampsonlab.nephvseqtlserver.repositories;

import java.util.List;

import org.sampsonlab.nephvseqtlserver.entities.PeerEQTL;
import org.sampsonlab.nephvseqtlserver.entities.PeerEQTLKey;
import org.sampsonlab.nephvseqtlserver.entities.VariantSubject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface PeerEQTLRepository extends Repository<PeerEQTL, PeerEQTLKey> {

	@Query("select p from PeerEQTL p where p.key.entrezId = ?1")
	List<PeerEQTL> findByEntrezId(Long entrezId);

	@Query("select p, g.symbol, v.dbSNPId, v.overallAf from PeerEQTL p inner join fetch p.gene g inner join fetch p.variant v where g.symbol = ?1 and p.pVal < ?2")
	List<Object[]> findByGeneSymbolAndMaxPVal(String symbol, Double maxPVal);
	
	@Query("select p, g.symbol, v.dbSNPId, v.overallAf from PeerEQTL p inner join fetch p.gene g inner join fetch p.variant v where g.entrezId = ?1 and p.pVal < ?2")
	List<Object[]> findByEntrezIdAndMaxPVal(Long entrezId, Double maxPVal);
	
	@Query("select p, g.symbol, v.dbSNPId, v.overallAf from PeerEQTL p inner join fetch p.gene g inner join fetch p.variant v where g.ensg = ?1 and p.pVal < ?2")
	List<Object[]> findByEnsemblAndMaxPVal(String ensembl, Double maxPVal);
	
	@Query("select p, g.symbol, v.dbSNPId, v.overallAf from PeerEQTL p inner join fetch p.gene g inner join fetch p.variant v where v.dbSNPId = ?1")
	List<Object[]> findBydbSNP(String dbSNPId);
	
	@Query("select p, g.symbol, v.dbSNPId, v.overallAf from PeerEQTL p inner join fetch p.gene g inner join fetch p.variant v where v.chrom = ?1 and v.pos >= ?2 and v.pos <= ?3")
	List<Object[]> findByRegion(String chrom, int start, int end);
	
	
	@Query("select p from PeerEQTL p inner join fetch p.gene g inner join fetch p.variant v inner join fetch g.geneCoord gc left join fetch g.geneExpr ge where p.key.entrezId = ?1 and p.key.variantStr = ?2 and p.key.dataType = ?3 and ge.key.tissue = ?3")
	PeerEQTL findByEntrezIdAndVariantStrAndDataType(Long entrezId, String variantStr, String dataType);
	
	@Query("select vs from VariantSubject vs where vs.key.variantStr = ?1")
	List<VariantSubject> findByVariantStr(String variantStr);
}
