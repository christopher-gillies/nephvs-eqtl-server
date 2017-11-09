package org.sampsonlab.nephvseqtlsever.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gene")
public class Gene {
	
	@Id
	@Column(name="entrezId")	
	private Long entrezId;
	
	@Column(name="symbol")	
	private String symbol;
	
	@Column(name="ensg")	
	private String ensg;
	
	@Column(name="geneType")	
	private String geneType;
	
	@Column(name="description")	
	private String description;

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

	public String getEnsg() {
		return ensg;
	}

	public void setEnsg(String ensg) {
		this.ensg = ensg;
	}

	public String getGeneType() {
		return geneType;
	}

	public void setGeneType(String geneType) {
		this.geneType = geneType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
