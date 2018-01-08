package org.sampsonlab.nephvseqtlserver.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DAPVariantSummaryKey implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7180165171026991266L;

	@Column(name="dataType", nullable = false)	
	private String dataType; 
	
	@Column(name="entrezId", nullable = false)	
	private Long entrezId;
	
	@Column(name="variantStr", nullable = false)	
	private String variantStr;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getEntrezId() {
		return entrezId;
	}

	public void setEntrezId(Long entrezId) {
		this.entrezId = entrezId;
	}

	public String getVariantStr() {
		return variantStr;
	}

	public void setVariantStr(String variantStr) {
		this.variantStr = variantStr;
	} 
	
	
	
}
