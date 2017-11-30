package org.sampsonlab.nephvseqtlserver.controllers;



import java.util.List;

import org.sampsonlab.nephvseqtlserver.domain.Query;
import org.sampsonlab.nephvseqtlserver.domain.Region;
import org.sampsonlab.nephvseqtlserver.dto.EQTLResult;
import org.sampsonlab.nephvseqtlserver.dto.GeneAndVariantDetailResult;
import org.sampsonlab.nephvseqtlserver.entities.PeerEQTL;
import org.sampsonlab.nephvseqtlserver.entities.VariantSubject;
import org.sampsonlab.nephvseqtlserver.repositories.PeerEQTLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/query")
public class QueryController {

	@Autowired
	private  PeerEQTLRepository peerEQTLRepository;
	
	@RequestMapping("")
	public EQTLResult query(@RequestParam(name="query") String queryStr,
			@RequestParam(name="maxPVal", defaultValue = "0.05", required = false ) Double maxPVal ) {
		
		EQTLResult result = null;
		List<Object[]> objectEqtls = null;
		Query query = new Query(queryStr);
		
		switch(query.getType()) {
			case GeneSymbol: 
				objectEqtls = peerEQTLRepository.findByGeneSymbolAndMaxPVal(query.getQuery(), maxPVal);
				break;
			case Entrez: 
				objectEqtls = peerEQTLRepository.findByEntrezIdAndMaxPVal(query.getEntrez(), maxPVal);
				break;
			case Ensembl: 
				objectEqtls = peerEQTLRepository.findByEnsemblAndMaxPVal(query.getQuery(), maxPVal);
				break;
			case dbSNP:
				objectEqtls = peerEQTLRepository.findBydbSNP(query.getQuery());
				break;
			case Region:
			case Variant:
				Region r = Region.createFromQuery(query);
				objectEqtls = peerEQTLRepository.findByRegion(r.getChrom(), r.getStart(), r.getEnd());
				break;
			default:
				result = EQTLResult.createEmpty();
		}
		
		if(objectEqtls != null) {
			result = EQTLResult.createFromListObjectEQTL(objectEqtls, query);
		}
		return result;
		
	}
	
	@RequestMapping("/validate")
	public Query validateQuery(@RequestParam(value="query") String queryStr) {
		Query query = new Query(queryStr);
		return query;
	}
	
	
	@RequestMapping("/detail")
	public GeneAndVariantDetailResult validateQuery(@RequestParam(value="entrezId") Long entrezId,
			@RequestParam(value="variantStr") String variantStr, @RequestParam(value="tissue") String tissue) {
		/*
		 * We run 2 queries here b/c hibernate was complaining about pull multiple "bags" from the database
		 */
		PeerEQTL res = peerEQTLRepository.findByEntrezIdAndVariantStrAndDataType(entrezId, variantStr, tissue);
		List<VariantSubject> vs = peerEQTLRepository.findByVariantStr(variantStr);
		res.getVariant().setVariantSubject(vs);
		return GeneAndVariantDetailResult.createFromPeerEQTL(res);
	}
	
}
