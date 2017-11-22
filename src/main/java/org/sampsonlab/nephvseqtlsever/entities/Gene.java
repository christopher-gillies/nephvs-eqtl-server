package org.sampsonlab.nephvseqtlsever.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

	
	@MapsId("entrezId")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="entrezId", referencedColumnName="entrezId")
	private GeneCoord geneCoord;
	
	@MapsId("entrezId")
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="entrezId", referencedColumnName="entrezId")
	private List<GeneExpr >geneExpr;
	

	public List<GeneExpr> getGeneExpr() {
		return geneExpr;
	}

	public void setGeneExpr(List<GeneExpr> geneExpr) {
		this.geneExpr = geneExpr;
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

	public GeneCoord getGeneCoord() {
		return geneCoord;
	}

	public void setGeneCoord(GeneCoord geneCoord) {
		this.geneCoord = geneCoord;
	}
	
	
	
	
}
