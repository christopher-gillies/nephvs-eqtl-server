package org.sampsonlab.nephvseqtlserver.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dapGeneSummary")
public class DAPGeneSummary {

	@EmbeddedId
	private DAPGeneSummaryKey key;
	
	@Column(name="geneNull")
	private Double geneNull;
	
	@Column(name="expSize")
	private Double expSize;
	
	

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumns({ 
		@JoinColumn(name="entrezId", referencedColumnName="entrezId"),
		@JoinColumn(name="dataType", referencedColumnName="dataType")
	})
	private List<DAPVariantSummary> variants;
	
	@MapsId("entrezId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="entrezId", referencedColumnName="entrezId")
	private Gene gene;
	
	public DAPGeneSummaryKey getKey() {
		return key;
	}
	
	public void setKey(DAPGeneSummaryKey key) {
		this.key = key;
	}
	
	public Double getGeneNull() {
		return geneNull;
	}
	
	public void setGeneNull(Double geneNull) {
		this.geneNull = geneNull;
	}
	
	public Double getExpSize() {
		return expSize;
	}
	
	public void setExpSize(Double expSize) {
		this.expSize = expSize;
	}

	public List<DAPVariantSummary> getVariants() {
		return variants;
	}

	public void setVariants(List<DAPVariantSummary> variants) {
		this.variants = variants;
	}

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}
	
	public static DAPGeneSummary create(Gene gene, String dataType, Double expSize, Double geneNull) {
		DAPGeneSummary dgs = new DAPGeneSummary();
		
		DAPGeneSummaryKey key = new DAPGeneSummaryKey();
		
		dgs.setGene(gene);
		
		key.setDataType(dataType);
		key.setEntrezId(gene.getEntrezId());
		
		dgs.setKey(key);
		dgs.setExpSize(expSize);
		dgs.setGeneNull(geneNull);
		
		
		return dgs;
	}
	
	
}
