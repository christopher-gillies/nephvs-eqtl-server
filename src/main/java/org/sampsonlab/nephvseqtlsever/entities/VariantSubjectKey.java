package org.sampsonlab.nephvseqtlsever.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VariantSubjectKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7181918892092424046L;

	@Column(name="variantStr", nullable = false)	
	private String variantStr; 
	
	@Column(name="subjectId", nullable = false)	
	private String subjectId;

	public String getVariantStr() {
		return variantStr;
	}

	public void setVariantStr(String variantStr) {
		this.variantStr = variantStr;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
	
	
}
