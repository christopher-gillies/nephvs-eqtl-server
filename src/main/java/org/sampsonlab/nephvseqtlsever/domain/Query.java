package org.sampsonlab.nephvseqtlsever.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Query {

	private final String query;
	private final Type type;
	
	protected static Pattern dbSNPPattern = Pattern.compile("((rs)|(RS))[0-9]+");
	protected static Pattern variantPattern = Pattern.compile("([cC][hH][rR])?([0-9XYxy]{1,2})[:]([0-9]+)");
	protected static Pattern regionPattern = Pattern.compile("([cC][hH][rR])?([0-9XYxy]{1,2})[:]([0-9]+)[-]([0-9]+)");
	protected static Pattern entrezPattern = Pattern.compile("[0-9]+");
	protected static Pattern ensemblPattern = Pattern.compile("[Ee][Nn][Ss][Gg][0-9]+");
	
	public Query(String query) {
		query = query.trim();
		this.type = assignType(query);
		if(this.type != Type.dbSNP) {
			this.query = query.toUpperCase();
		} else {
			this.query = query.toLowerCase();
		}
		
	}
	
	public enum Type {
		GeneSymbol,
		Variant,
		Region,
		dbSNP,
		Entrez,
		Ensembl
	}
	
	public enum Status {
		NoQuery,
		Valid,
		StartGreaterThanEnd,
		RegionToLarge
	}
	
	
	private static Type assignType(String query) {
		
		Matcher dbSNPMatcher = dbSNPPattern.matcher(query);
		Matcher variantMatcher = variantPattern.matcher(query);
		Matcher regionMatcher = regionPattern.matcher(query);
		Matcher entrezMatcher = entrezPattern.matcher(query);
		Matcher EnsemblMatcher = ensemblPattern.matcher(query);
		
		if(dbSNPMatcher.matches()) {
			return Type.dbSNP;
		} else if(regionMatcher.matches()) {
			return Type.Region;
		} else if(variantMatcher.matches()) {
			return Type.Variant;
		} else if( entrezMatcher.matches() ) {
			return Type.Entrez;
		} else if( EnsemblMatcher.matches() ) {
			return Type.Ensembl;
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
	
	public Status isValid() {
		
		if(this.type == Type.Region) {
			Region r = Region.createFromQuery(this);
			if(r.getEnd() - r.getStart() > 5000000) {
				return Status.RegionToLarge;
			} else if( r.getStart() >  r.getEnd()) {
				return Status.StartGreaterThanEnd;
			} else {
				return Status.Valid;
			}
		} else {
			if(this.query.length() > 0) {
				return Status.Valid;
			} else {
				return Status.NoQuery;
			}
		}
	}
	
	public Long getEntrez() {
		if(this.getType() == Type.Entrez) {
			return Long.parseLong(getQuery());
		} else {
			return null;
		}
	}
	
}
