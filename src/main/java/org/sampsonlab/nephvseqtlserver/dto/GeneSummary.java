package org.sampsonlab.nephvseqtlserver.dto;

import java.io.Serializable;

import org.sampsonlab.nephvseqtlserver.entities.DAPGeneSummary;

public class GeneSummary implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3709145121573918293L;

	private GeneSummary() {
		
	}
	
	private Long entrezId;
	private String symbol;
	private String description;
	private Double geneNull;
	private Double fdr;
	private Double expSize;
	private transient String tissue;
	
	public static GeneSummary create(DAPGeneSummary dgs) {
		GeneSummary gs = new GeneSummary();
		
		gs.entrezId = dgs.getGene().getEntrezId();
		gs.symbol = dgs.getGene().getSymbol();
		gs.description = dgs.getGene().getDescription();
		gs.geneNull = dgs.getGeneNull();
		gs.fdr = dgs.getFdr();
		gs.expSize = dgs.getExpSize();
		gs.tissue = dgs.getKey().getDataType();
		
		return gs;
	}

	public Long getEntrezId() {
		return entrezId;
	}

	public void setEntrezId(Long entrezId) {
		this.entrezId = entrezId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getGeneNull() {
		return geneNull;
	}

	public void setGeneNull(Double geneNull) {
		this.geneNull = geneNull;
	}

	public Double getFdr() {
		return fdr;
	}

	public void setFdr(Double fdr) {
		this.fdr = fdr;
	}

	public Double getExpSize() {
		return expSize;
	}

	public void setExpSize(Double expSize) {
		this.expSize = expSize;
	}

	public String getTissue() {
		return tissue;
	}

	public void setTissue(String tissue) {
		this.tissue = tissue;
	}
	
	
	
}
