package org.sampsonlab.nephvseqtlsever.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "geneCoord")
public class GeneCoord {
	
	
	@Id
	@Column(name="entrezId")	
	private Long entrezId;
	
	@Column(name="chrom")	
	private String chrom;
	
	@Column(name="tss")	
	private Long tss;
	
	@Column(name="tes")	
	private Long tes;
	
	public Long getEntrezId() {
		return entrezId;
	}

	public void setEntrezId(Long entrezId) {
		this.entrezId = entrezId;
	}

	public String getChrom() {
		return chrom;
	}

	public void setChrom(String chrom) {
		this.chrom = chrom;
	}

	public Long getTss() {
		return tss;
	}

	public void setTss(Long tss) {
		this.tss = tss;
	}

	public Long getTes() {
		return tes;
	}

	public void setTes(Long tes) {
		this.tes = tes;
	}

	
}
