package org.sampsonlab.nephvseqtlsever.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "variantSubject")
public class VariantSubject {

	@EmbeddedId
	private VariantSubjectKey key;
	
	@Column(name="genotypeInt")
	private Integer genotypeInt;

	public VariantSubjectKey getKey() {
		return key;
	}

	public void setKey(VariantSubjectKey key) {
		this.key = key;
	}

	public Integer getGenotypeInt() {
		return genotypeInt;
	}

	public void setGenotypeInt(Integer genotypeInt) {
		this.genotypeInt = genotypeInt;
	}
	
	
	
}
