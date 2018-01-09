package org.sampsonlab.nephvseqtlserver.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "dapVariantSummary")
public class DAPVariantSummary {

	@EmbeddedId
	private DAPVariantSummaryKey key;
	
	@Column(name="cluster")
	private Integer cluster;
	
	@Column(name="clusterPip")
	private Double clusterPIP;
	
	@Column(name="numSnpsInCluster")
	private Integer numSnps;
	
	@Column(name="snpPip")
	private Double snpPIP;

	public DAPVariantSummaryKey getKey() {
		return key;
	}

	public void setKey(DAPVariantSummaryKey key) {
		this.key = key;
	}

	public Integer getCluster() {
		return cluster;
	}

	public void setCluster(Integer cluster) {
		this.cluster = cluster;
	}

	public Double getClusterPIP() {
		return clusterPIP;
	}

	public void setClusterPIP(Double clusterPIP) {
		this.clusterPIP = clusterPIP;
	}

	public Integer getNumSnps() {
		return numSnps;
	}

	public void setNumSnps(Integer numSnps) {
		this.numSnps = numSnps;
	}

	public Double getSnpPIP() {
		return snpPIP;
	}

	public void setSnpPIP(Double snpPIP) {
		this.snpPIP = snpPIP;
	}
	
	public static DAPVariantSummary create(String variantStr, Long entrezId, String dataType, 
			Integer cluster, Double clusterPIP, Integer numSnps, Double snpPIP) {
		
		DAPVariantSummary dvs = new DAPVariantSummary();
		DAPVariantSummaryKey key = new DAPVariantSummaryKey();
		key.setVariantStr(variantStr);
		key.setEntrezId(entrezId);
		key.setDataType(dataType);
		
		dvs.setKey(key);
		dvs.setCluster(cluster);
		dvs.setClusterPIP(clusterPIP);
		dvs.setNumSnps(numSnps);
		dvs.setSnpPIP(snpPIP);
		
		return dvs;
		
	}
	
}
