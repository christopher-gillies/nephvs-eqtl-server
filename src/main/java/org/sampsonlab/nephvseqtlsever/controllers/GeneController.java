package org.sampsonlab.nephvseqtlsever.controllers;

import java.util.List;

import org.sampsonlab.nephvseqtlsever.repositories.GeneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/gene")
public class GeneController {
	
	@Autowired
	private GeneRepository geneRepository; 
	
	@RequestMapping("/symbols")
	public List<String> symbols() {
		return geneRepository.findAllSymbols();
	}
}
