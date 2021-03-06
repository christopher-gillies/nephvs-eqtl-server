package org.sampsonlab.nephvseqtlserver.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sampsonlab.nephvseqtlserver.entities.DAPGeneSummary;
import org.sampsonlab.nephvseqtlserver.entities.DAPVariantSummary;



import java.io.Serializable;


public class DAPPlotResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8211661364226827090L;
	private Gene gene;
	private HashMap<String,Tissue> tissues;

	
	public DAPPlotResult() {
		this.tissues = new HashMap<>();
	}
	
	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public HashMap<String, Tissue> getTissues() {
		return tissues;
	}

	public void setTissues(HashMap<String, Tissue> tissues) {
		this.tissues = tissues;
	}



	public static class Tissue implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -578525817689834121L;
		String tissue;
		Double expSize;
		List<Cluster> clusters;
		Double geneNull;
		Double fdr;
		
		public Tissue() {
			this.clusters = new LinkedList<>();
		}

		public String getTissue() {
			return tissue;
		}

		public void setTissue(String tissue) {
			this.tissue = tissue;
		}

		public Double getExpSize() {
			return expSize;
		}

		public void setExpSize(Double expSize) {
			this.expSize = expSize;
		}

		public List<Cluster> getClusters() {
			return clusters;
		}

		public void setClusters(List<Cluster> clusters) {
			this.clusters = clusters;
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
		
		
		
		
	}
	
	public static class Cluster implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 3762909989069737463L;
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
	
	public static class Variant implements Serializable {
		
		private Variant() {
			
		}
		
		
		public static Pattern variantPattern = Pattern.compile("([cC][hH][rR])?([0-9XYxy]{1,2})[:]([0-9]+)([_]([^/]+)[/]([^/]+))?");
		
		public static Variant create(String variantStr, Double pip, Double af, String dbSNPId) {
			Variant v = new Variant();
			
			v.pip = pip;
			v.variantStr = variantStr;
			v.af = af;
			v.dbSNPId = dbSNPId;
			
			Matcher variantMatcher = variantPattern.matcher(variantStr);
			
			if(variantMatcher.matches()) {
				//0 should be full match
				//1 will be chr
				v.chrom = variantMatcher.group(2);
				v.pos = Integer.parseInt(variantMatcher.group(3));
				if(variantMatcher.groupCount() == 6) {
					v.ref = variantMatcher.group(5);
					v.alt = variantMatcher.group(6);
				}
			} else {
				throw new IllegalArgumentException("variantStr is not formatted correctly");
			}
			
			return v;
		}
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 9146652300605523340L;
		
		private String variantStr;
		private Double pip;
		private String chrom;
		private Integer pos;
		private String ref;
		private String alt;
		private Double af;
		private String dbSNPId;
		
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

		public String getChrom() {
			return chrom;
		}

		public void setChrom(String chrom) {
			this.chrom = chrom;
		}

		public Integer getPos() {
			return pos;
		}

		public void setPos(Integer pos) {
			this.pos = pos;
		}

		public String getRef() {
			return ref;
		}

		public void setRef(String ref) {
			this.ref = ref;
		}

		public String getAlt() {
			return alt;
		}

		public void setAlt(String alt) {
			this.alt = alt;
		}
		public Double getAf() {
			return af;
		}
		public void setAf(Double af) {
			this.af = af;
		}
		public String getDbSNPId() {
			return dbSNPId;
		}
		public void setDbSNPId(String dbSNPId) {
			this.dbSNPId = dbSNPId;
		}
		
		
		
		
	}
	
	
	public static class Gene implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -6554808569450465857L;
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
		public String getChrom() {
			return chrom;
		}
		public void setChrom(String chrom) {
			this.chrom = chrom;
		}
		public Long getStart() {
			return start;
		}
		public void setStart(Long start) {
			this.start = start;
		}
		public Long getEnd() {
			return end;
		}
		public void setEnd(Long end) {
			this.end = end;
		}
		public String getStrand() {
			return strand;
		}
		public void setStrand(String strand) {
			this.strand = strand;
		}
	}
	

	public static DAPPlotResult createFromCollectionOfDAPGeneSummary(Collection<DAPGeneSummary> summaries) {
		// TODO: IMPLEMENT
		
		
		DAPPlotResult result = new DAPPlotResult();
		
		DAPPlotResult.Gene gene = new Gene();
		//assign gene to result
		result.setGene(gene);
		
		for(DAPGeneSummary summary : summaries) {
			
			// Set Gene attributes
			if(gene.entrezId == null) {
				
				gene.entrezId = summary.getGene().getEntrezId();
				gene.description = summary.getGene().getDescription();
				gene.ensg = summary.getGene().getEnsg();
				gene.symbol = summary.getGene().getSymbol();
				gene.chrom = summary.getGene().getGeneCoord().getChrom();
				
				Long tss = summary.getGene().getGeneCoord().getTss();
				Long tes = summary.getGene().getGeneCoord().getTes();
				
				gene.start = Math.min( tss,tes);
				gene.end = Math.max(tss, tes);
				
				if(tss <= tes) {
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
			//Tissues will be constant going forward
			String tissue = summary.getKey().getDataType();
			Tissue t = new Tissue();
			t.tissue = tissue;
			t.expSize = summary.getExpSize();
			t.clusters = new LinkedList<>();
			t.geneNull = summary.getGeneNull();
			t.fdr = summary.getFdr();
			HashMap<String,Tissue> tissues = result.getTissues();
			
			if(tissues.containsKey(tissue)) {
				throw new IllegalStateException("There are repeated tissues in this collection");
			} else {
				//assign tissue to result
				tissues.put(tissue, t);
			}
			
			//Create cluster and variants
			HashMap<Integer,Cluster> clusterMap = new HashMap<>();
			for(DAPVariantSummary dapVariantSummary : summary.getVariants()) {
				Cluster cluster = null;
				
				if(clusterMap.containsKey(dapVariantSummary.getCluster())) {
					cluster = clusterMap.get(dapVariantSummary.getCluster());
				} else {
					cluster = new Cluster();
					cluster.cluster = dapVariantSummary.getCluster();
					cluster.numVariants = dapVariantSummary.getNumSnps();
					cluster.pip = dapVariantSummary.getClusterPIP();
					cluster.variants = new LinkedList<>();
					//add clusters to map
					clusterMap.put(cluster.cluster, cluster);
				}
				
				Variant v = Variant.create(dapVariantSummary.getKey().getVariantStr(), 
						dapVariantSummary.getSnpPIP(), dapVariantSummary.getVariant().getOverallAf(), dapVariantSummary.getVariant().getDbSNPId());
				//add variants to cluster
				cluster.variants.add(v);
			}
			
			//add clusters to tissues
			t.clusters.addAll(clusterMap.values());
			
			
			
		}
		
		
		return result;
	}
	
	
}
