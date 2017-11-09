package org.sampsonlab.nephvseqtlsever.dto;

import org.sampsonlab.nephvseqtlsever.entities.PeerEQTL;

public class EQTLEntry {

	private EQTLEntry () {
		
	}
	
	public static EQTLEntry createEQTLEntryFromPeerEQTL(PeerEQTL peerEQTL) {
		EQTLEntry result = new EQTLEntry();
		result.setBeta(peerEQTL.getBeta());
		result.setEntrezId(peerEQTL.getKey().getEntrezId());
		result.setGeneSymbol(peerEQTL.getGene().getSymbol());
		result.setpVal(peerEQTL.getpVal());
		result.settStat(peerEQTL.gettStat());
		result.setVariantStr(peerEQTL.getKey().getVariantStr());
		return result;
	}
	

	private String variantStr; 
	
	private Long entrezId; 

	private Double beta;
	
	private Double tStat;
	
	private Double pVal;
	
	private String geneSymbol;

	public String getVariantStr() {
		return variantStr;
	}

	public void setVariantStr(String variantStr) {
		this.variantStr = variantStr;
	}

	public Long getEntrezId() {
		return entrezId;
	}

	public void setEntrezId(Long entrezId) {
		this.entrezId = entrezId;
	}

	public Double getBeta() {
		return beta;
	}

	public void setBeta(Double beta) {
		this.beta = beta;
	}

	public Double gettStat() {
		return tStat;
	}

	public void settStat(Double tStat) {
		this.tStat = tStat;
	}

	public Double getpVal() {
		return pVal;
	}

	public void setpVal(Double pVal) {
		this.pVal = pVal;
	}

	public String getGeneSymbol() {
		return geneSymbol;
	}

	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}
	
	
	
}
