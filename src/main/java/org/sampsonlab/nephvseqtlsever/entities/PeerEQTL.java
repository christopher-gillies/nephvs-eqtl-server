package org.sampsonlab.nephvseqtlsever.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "peerEQTL")
public class PeerEQTL {

	@EmbeddedId
	private PeerEQTLKey key;

	@Column(name="beta")	
	private Double beta;
	
	@Column(name="tStat")	
	private Double tStat;
	
	@Column(name="pVal")	
	private Double pVal;
	
	@MapsId("entrezId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="entrezId", referencedColumnName="entrezId")
	private Gene gene;
	
	public PeerEQTLKey getKey() {
		return key;
	}

	public void setKey(PeerEQTLKey key) {
		this.key = key;
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
	
	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PeerEQTL) {
			PeerEQTL cast = (PeerEQTL) obj;
			return this.getKey().equals(cast.getKey());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.getKey().hashCode();
	}
	
}
