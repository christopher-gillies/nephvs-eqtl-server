package org.sampsonlab.nephvseqtlsever.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Query {

	private final String query;
	private final Type type;
	
	private static Pattern dbSNPPattern = Pattern.compile("((rs)|(RS))[0-9]+");
	private static Pattern variantPattern = Pattern.compile("[0-9XYxy]{1,2}[:][0-9]+");
	private static Pattern regionPattern = Pattern.compile("[0-9XYxy]{1,2}[:][0-9]+[-][0-9]+");
	
	public Query(String query) {
		this.query = query.toUpperCase();
		this.type = assignType();
	}
	
	public enum Type {
		GeneSymbol,
		Variant,
		Region,
		dbSNP
	}
	
	
	private Type assignType() {
		
		Matcher dbSNPMatcher = dbSNPPattern.matcher(this.query);
		Matcher variantMatcher = variantPattern.matcher(this.query);
		Matcher regionMatcher = regionPattern.matcher(this.query);
		
		if(dbSNPMatcher.matches()) {
			return Type.dbSNP;
		} else if(regionMatcher.matches()) {
			return Type.Region;
		} else if(variantMatcher.matches()) {
			return Type.Variant;
		} else {
			return Type.GeneSymbol;
		}
		
	}
	
	public String getQuery() {
		return this.query;
	}
	
	public Type getType() {
		return this.type;
	}
	
}
