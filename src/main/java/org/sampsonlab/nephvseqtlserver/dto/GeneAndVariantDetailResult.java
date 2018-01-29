package org.sampsonlab.nephvseqtlserver.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.sampsonlab.nephvseqtlserver.entities.PeerEQTL;
import org.sampsonlab.nephvseqtlserver.entities.VariantSubject;

public class GeneAndVariantDetailResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 251073185529523843L;
	
	//private static final Random random = new Random(0L);
	
	private GeneAndVariantDetailResult () {
		
	}
	
	public static GeneAndVariantDetailResult createFromPeerEQTL(PeerEQTL peerEQTL) {
		GeneAndVariantDetailResult result = new GeneAndVariantDetailResult();
		
		result.setVariantStr(peerEQTL.getKey().getVariantStr());
		result.setBeta(peerEQTL.getBeta());
		result.settStat(peerEQTL.gettStat());
		result.setpVal(peerEQTL.getpVal());
		
		result.setGeneEntrezId(peerEQTL.getKey().getEntrezId());
		result.setGeneEnsemblId(peerEQTL.getGene().getEnsg());
		result.setGeneSymbol(peerEQTL.getGene().getSymbol());
		result.setGeneType(peerEQTL.getGene().getGeneType());
		result.setGeneDescription(peerEQTL.getGene().getDescription());
		result.setGeneChrom(peerEQTL.getGene().getGeneCoord().getChrom());
		result.setGeneTSS(peerEQTL.getGene().getGeneCoord().getTss());
		result.setGeneTES(peerEQTL.getGene().getGeneCoord().getTes());
		
		result.setVariantChrom(peerEQTL.getVariant().getChrom());
		result.setVariantPos(peerEQTL.getVariant().getPos());
		result.setVariantRef(peerEQTL.getVariant().getRef());
		result.setVariantAlt(peerEQTL.getVariant().getAlt());
		result.setVariantDbSNPId(peerEQTL.getVariant().getDbSNPId());
		
		result.setOverallAf(peerEQTL.getVariant().getOverallAf());
		result.setAmrAf(peerEQTL.getVariant().getAmrAf());
		result.setAsnAf(peerEQTL.getVariant().getAsnAf());
		result.setAfrAf(peerEQTL.getVariant().getAfrAf());
		result.setEurAf(peerEQTL.getVariant().getEurAf());
		
		result.set_1kgOverallAf(peerEQTL.getVariant().get_1kgAf());
		result.set_1kgAmrAf(peerEQTL.getVariant().get_1kgAmrAf());
		result.set_1kgSasAf(peerEQTL.getVariant().get_1kgSasAf());
		result.set_1kgEasAf(peerEQTL.getVariant().get_1kgEasAf());
		result.set_1kgAfrAf(peerEQTL.getVariant().get_1kgAfrAf());
		result.set_1kgEurAf(peerEQTL.getVariant().get_1kgEurAf());
		
		List<ExpressionAndGenotype> exprAndGtForSubs = new ArrayList<>(peerEQTL.getGene().getGeneExpr().size());
		
		//map genotypes and expression
		HashMap<Integer,ExpressionAndGenotype> exprAndGenotypeBySubjectId = new HashMap<>();
		HashMap<Integer,VariantSubject> variantSubjectMap = new HashMap<>();
		
		//store variants into a hash by id
		peerEQTL.getVariant().getVariantSubject().forEach(v -> {
			variantSubjectMap.put(v.getKey().getSubjectId(), v);
		});
		
		//store expression and variants into hash
		peerEQTL.getGene().getGeneExpr().forEach(ge -> {
			//get variant for this person
			VariantSubject v = variantSubjectMap.get(ge.getKey().getSubjectId());
			
			//store expression and integer
			ExpressionAndGenotype eg = ExpressionAndGenotype.create(ge.getExpr(), v.getGenotypeInt());
			
			exprAndGenotypeBySubjectId.put(ge.getKey().getSubjectId(), eg);
		});
		
		
		
		
		//Store into
		exprAndGtForSubs.addAll(exprAndGenotypeBySubjectId.values());
		
		/*
		 * Randomly shuffle the order of the genotypes and expression
		 */
		Collections.shuffle(exprAndGtForSubs);
		result.setExprAndGtForSubs(exprAndGtForSubs);
		
		//compute the count of individuals per genotype
		HashMap<Integer,Integer> groups = new HashMap<>();
		
		exprAndGtForSubs.forEach(geAndGeno -> {
			if(groups.containsKey(geAndGeno.getGt())) {
				Integer count = groups.get(geAndGeno.getGt());
				count++;
				groups.put(geAndGeno.getGt(), count);
			} else {
				groups.put(geAndGeno.getGt(), 1);
			}
		});
		
		result.setHomRef(groups.get(0));
		result.setHet(groups.get(1));
		result.setHomAlt(groups.get(2));
		
		return result;
	}
	
	
	private String variantStr; 
	
	
	
	private Double beta;
	private Double tStat;
	private Double pVal;
	
	
	
	private Long geneEntrezId; 
	private String geneEnsemblId; 
	private String geneSymbol;
	private String geneType;
	private String geneDescription;
	private String geneChrom;
	private Long geneTSS;
	private Long geneTES;
	
	
	private String variantChrom;
	private Integer variantPos;
	private String variantRef;
	private String variantAlt;
	private String variantDbSNPId;
	
	private Integer homRef;
	private Integer het;
	private Integer homAlt;
	
	private Double overallAf;
	private Double afrAf;
	private Double amrAf;
	private Double asnAf;
	private Double eurAf;
	
	
	private Double _1kgOverallAf;
	private Double _1kgAfrAf;
	private Double _1kgAmrAf;
	private Double _1kgEasAf;
	private Double _1kgSasAf;
	private Double _1kgEurAf;

	private List<ExpressionAndGenotype> exprAndGtForSubs;
	
	
	public List<ExpressionAndGenotype> getExprAndGtForSubs() {
		return exprAndGtForSubs;
	}

	public void setExprAndGtForSubs(List<ExpressionAndGenotype> exprAndGtForSubs) {
		this.exprAndGtForSubs = exprAndGtForSubs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getVariantStr() {
		return variantStr;
	}

	public void setVariantStr(String variantStr) {
		this.variantStr = variantStr;
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

	public Long getGeneEntrezId() {
		return geneEntrezId;
	}

	public void setGeneEntrezId(Long geneEntrezId) {
		this.geneEntrezId = geneEntrezId;
	}

	public String getGeneEnsemblId() {
		return geneEnsemblId;
	}

	public void setGeneEnsemblId(String geneEnsemblId) {
		this.geneEnsemblId = geneEnsemblId;
	}

	public String getGeneSymbol() {
		return geneSymbol;
	}

	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}

	public String getGeneType() {
		return geneType;
	}

	public void setGeneType(String geneType) {
		this.geneType = geneType;
	}

	public String getGeneDescription() {
		return geneDescription;
	}

	public void setGeneDescription(String geneDescription) {
		this.geneDescription = geneDescription;
	}

	public String getGeneChrom() {
		return geneChrom;
	}

	public void setGeneChrom(String geneChrom) {
		this.geneChrom = geneChrom;
	}

	public Long getGeneTSS() {
		return geneTSS;
	}

	public void setGeneTSS(Long geneTSS) {
		this.geneTSS = geneTSS;
	}

	public Long getGeneTES() {
		return geneTES;
	}

	public void setGeneTES(Long geneTES) {
		this.geneTES = geneTES;
	}

	public String getVariantChrom() {
		return variantChrom;
	}

	public void setVariantChrom(String variantChrom) {
		this.variantChrom = variantChrom;
	}

	public Integer getVariantPos() {
		return variantPos;
	}

	public void setVariantPos(Integer variantPos) {
		this.variantPos = variantPos;
	}

	public String getVariantRef() {
		return variantRef;
	}

	public void setVariantRef(String variantRef) {
		this.variantRef = variantRef;
	}

	public String getVariantAlt() {
		return variantAlt;
	}

	public void setVariantAlt(String variantAlt) {
		this.variantAlt = variantAlt;
	}

	public String getVariantDbSNPId() {
		return variantDbSNPId;
	}

	public void setVariantDbSNPId(String variantDbSNPId) {
		this.variantDbSNPId = variantDbSNPId;
	}

	public Double getOverallAf() {
		return overallAf;
	}

	public void setOverallAf(Double overallAf) {
		this.overallAf = overallAf;
	}

	public Double getAfrAf() {
		return afrAf;
	}

	public void setAfrAf(Double afrAf) {
		this.afrAf = afrAf;
	}

	public Double getAmrAf() {
		return amrAf;
	}

	public void setAmrAf(Double amrAf) {
		this.amrAf = amrAf;
	}

	public Double getAsnAf() {
		return asnAf;
	}

	public void setAsnAf(Double asnAf) {
		this.asnAf = asnAf;
	}

	public Double getEurAf() {
		return eurAf;
	}

	public void setEurAf(Double eurAf) {
		this.eurAf = eurAf;
	}

	public Double get_1kgOverallAf() {
		return _1kgOverallAf;
	}

	public void set_1kgOverallAf(Double _1kgOverallAf) {
		this._1kgOverallAf = _1kgOverallAf;
	}

	public Double get_1kgAfrAf() {
		return _1kgAfrAf;
	}

	public void set_1kgAfrAf(Double _1kgAfrAf) {
		this._1kgAfrAf = _1kgAfrAf;
	}

	public Double get_1kgAmrAf() {
		return _1kgAmrAf;
	}

	public void set_1kgAmrAf(Double _1kgAmrAf) {
		this._1kgAmrAf = _1kgAmrAf;
	}

	public Double get_1kgEurAf() {
		return _1kgEurAf;
	}

	public void set_1kgEurAf(Double _1kgEurAf) {
		this._1kgEurAf = _1kgEurAf;
	}

	public Double get_1kgEasAf() {
		return _1kgEasAf;
	}

	public void set_1kgEasAf(Double _1kgEasAf) {
		this._1kgEasAf = _1kgEasAf;
	}

	public Double get_1kgSasAf() {
		return _1kgSasAf;
	}

	public void set_1kgSasAf(Double _1kgSasAf) {
		this._1kgSasAf = _1kgSasAf;
	}

	public Integer getHomRef() {
		return homRef;
	}

	public void setHomRef(Integer homRef) {
		this.homRef = homRef;
	}

	public Integer getHet() {
		return het;
	}

	public void setHet(Integer het) {
		this.het = het;
	}

	public Integer getHomAlt() {
		return homAlt;
	}

	public void setHomAlt(Integer homAlt) {
		this.homAlt = homAlt;
	}
	
	
	
}
