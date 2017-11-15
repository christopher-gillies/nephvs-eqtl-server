package org.sampsonlab.nephvseqtlsever.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "variant")
public class Variant {
	
	@Id
	@Column(name="variantStr")
	private String variantStr;
	
	@Column(name="chrom")
	private String chrom;
	
	@Column(name="pos")
	private Integer pos;
	
	@Column(name="ref")
	private String ref;
	
	@Column(name="alt")
	private String alt;
	
	@Column(name="dbSNPId")
	private String dbSNPId;
	
	@Column(name="overall_AF")
	private Double overallAf;
	
	@Column(name="AFR_AF")
	private Double afrAf;
	
	@Column(name="AMR_AF")
	private Double amrAf;
	
	@Column(name="ASN_AF")
	private Double asnAf;
	
	@Column(name="EUR_AF")
	private Double eurAf;
	
	@Column(name="1KG_AF")
	private Double _1kgAf;
	
	@Column(name="1KG_AFR_AF")
	private Double _1kgAfrAf;
	
	@Column(name="1KG_AMR_AF")
	private Double _1kgAmrAf;
	
	@Column(name="1KG_EAS_AF")
	private Double _1kgEasAf;
	
	@Column(name="1KG_SAS_AF")
	private Double _1kgSasAf;
	
	@Column(name="1KG_EUR_AF")
	private Double _1kgEurAf;
	
	public String getVariantStr() {
		return variantStr;
	}
	public void setVariantStr(String variantStr) {
		this.variantStr = variantStr;
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
	public String getDbSNPId() {
		return dbSNPId;
	}
	public void setDbSNPId(String dbSNPId) {
		this.dbSNPId = dbSNPId;
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
	public Double get_1kgEurAf() {
		return _1kgEurAf;
	}
	public void set_1kgEurAf(Double _1kgEurAf) {
		this._1kgEurAf = _1kgEurAf;
	}
	public Double get_1kgAf() {
		return _1kgAf;
	}
	public void set_1kgAf(Double _1kgAf) {
		this._1kgAf = _1kgAf;
	}
	
	
	
}
