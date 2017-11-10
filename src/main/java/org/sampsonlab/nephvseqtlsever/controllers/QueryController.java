package org.sampsonlab.nephvseqtlsever.controllers;



import java.util.ArrayList;
import java.util.List;

import org.sampsonlab.nephvseqtlsever.domain.Query;
import org.sampsonlab.nephvseqtlsever.dto.EQTLResult;
import org.sampsonlab.nephvseqtlsever.entities.PeerEQTL;
import org.sampsonlab.nephvseqtlsever.repositories.PeerEQTLRepository;
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
			@RequestParam(name="maxPVal",defaultValue = "0.05", required = false ) Double maxPVal ) {
		
		EQTLResult result = null;
		Query query = new Query(queryStr);
		if(query.getType() == Query.Type.GeneSymbol) {
			List<Object[]> objectEqtls = peerEQTLRepository.findByGeneSymbolAndMaxPVal(query.getQuery(), maxPVal);
			result = EQTLResult.createFromListObjectEQTL(objectEqtls, query);
		}
		
		return result;
		
	}
	
	@RequestMapping("/validate")
	public Boolean validateQuery(@RequestParam(value="query") String query) {
		if(query.length() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
