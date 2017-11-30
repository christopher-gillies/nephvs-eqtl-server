package org.sampsonlab.nephvseqtlserver.domain;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sampsonlab.nephvseqtlserver.domain.Query.Type;

public class Region implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5960820295003267131L;
	private String chrom;
	private int start;
	private int end;
	
	public String getChrom() {
		return chrom;
	}
	public void setChrom(String chrom) {
		this.chrom = chrom;
	}
	public int getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	
	public static Region createFromQuery(Query query) {
		Region r = new Region();
		if(query.getType() == Type.Variant) {
			Pattern p = Query.variantPattern;
			Matcher m = p.matcher(query.getQuery());
			if(m.matches()) {
				r.chrom = m.group(2);
				r.start = Integer.parseInt(m.group(3));
				r.end = r.start;
			}
			
		} else if(query.getType() == Type.Region) {
			Pattern p = Query.regionPattern;
			Matcher m = p.matcher(query.getQuery());
			if(m.matches()) {
				r.chrom = m.group(2);
				r.start = Integer.parseInt(m.group(3));
				r.end = Integer.parseInt(m.group(4));
			}
		}
		return r;
	}
	
}
