package org.sampsonlab.nephvseqtlsever.controllers;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/query")
public class QueryController {

	@RequestMapping("/validate")
	public Boolean validateQuery(@RequestParam(value="query") String query) {
		if(query.length() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
