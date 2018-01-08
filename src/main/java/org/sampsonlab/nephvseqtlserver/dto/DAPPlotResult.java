package org.sampsonlab.nephvseqtlserver.dto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.sampsonlab.nephvseqtlserver.entities.DAPGeneSummary;
import org.sampsonlab.nephvseqtlserver.entities.DAPVariantSummary;

import java.util.Collection;


public class DAPPlotResult {

	private Gene gene;
	private HashMap<String,Tissue> tissues;

	
	private DAPPlotResult() {
		this.tissues = new HashMap<>();
	}
	
	public DAPPlotResult createFromCollectionOfDAPGeneSummary(Collection<DAPGeneSummary> summaries) {
		// TODO: IMPLEMENT
		
		
		DAPPlotResult result = new DAPPlotResult();
		
		Gene gene = new Gene();
		result.gene = gene;
		
		for(DAPGeneSummary summary : summaries) {
			
			// Set Gene attributes
			if(gene.entrezId == null) {
				
				gene.entrezId = summary.getGene().getEntrezId();
				gene.description = summary.getGene().getDescription();
				gene.ensg = summary.getGene().getEnsg();
				gene.symbol = summary.getGene().getSymbol();
				gene.chrom = summary.getGene().getGeneCoord().getChrom();
				gene.start = summary.getGene().getGeneCoord().getTss();
				gene.end = summary.getGene().getGeneCoord().getTes();
				
				if(gene.start < gene.end) {
					gene.strand = "+";
				} else {
					gene.strand = "-";
				}
				
			} else {
				long id1 = summary.getGene().getEntrezId();
				long id2 = gene.entrezId;
				if(id1 != id2) {
					throw new IllegalStateException("There are multiple genes in the collection");
				}
			}
			
			//Create Tissue
			String tissue = summary.getKey().getDataType();
			Tissue t = new Tissue();
			t.tissue = tissue;
			t.expSize = summary.getExpSize();
			t.clusters = new LinkedList<>();
			
			if(result.tissues.containsKey(tissue)) {
				throw new IllegalStateException("There are repeated tissues in this collection");
			}
			
			//Create cluster and variants
			
			for(DAPVariantSummary dapVariantSummary : summary.getVariants()) {
				Cluster cluster = null;
				
			}
			
			
		}
		
		
		return result;
	}
	
	public class Tissue {
		String tissue;
		Double expSize;
		List<Cluster> clusters;
		
		public Tissue() {
			this.clusters = new LinkedList<>();
		}
	}
	
	public class Cluster {
		
		Integer cluster;
		Double pip;
		Integer numVariants;
		List<Variant> variants;
		
		public Integer getCluster() {
			return cluster;
		}
		public void setCluster(Integer cluster) {
			this.cluster = cluster;
		}
		public Double getPip() {
			return pip;
		}
		public void setPip(Double pip) {
			this.pip = pip;
		}
		public Integer getNumVariants() {
			return numVariants;
		}
		public void setNumVariants(Integer numVariants) {
			this.numVariants = numVariants;
		}
		public List<Variant> getVariants() {
			return variants;
		}
		public void setVariants(List<Variant> variants) {
			this.variants = variants;
		}
		
		
		
	}
	
	public class Variant {
		
		String variantStr;
		Double pip;
		
		public String getVariantStr() {
			return variantStr;
		}
		public void setVariantStr(String variantStr) {
			this.variantStr = variantStr;
		}
		public Double getPip() {
			return pip;
		}
		public void setPip(Double pip) {
			this.pip = pip;
		}
		
		
	}
	
	
	public class Gene {
		
		Long entrezId = null;
		String symbol = null;
		String ensg = null;
		String geneType = null;
		String description = null;
		String chrom = null;
		Long start = null;
		Long end = null;
		String strand = null;
		
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
	
	
	
	
}
