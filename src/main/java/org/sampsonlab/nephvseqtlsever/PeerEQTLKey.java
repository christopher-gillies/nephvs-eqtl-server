package org.sampsonlab.nephvseqtlsever;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PeerEQTLKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6494748337545436249L;

	@Column(name="dataType", nullable = false)	
	private String dataType;
	
	@Column(name="variantStr", nullable = false)	
	private String variantStr; 
	
	@Column(name="entrezId", nullable = false)	
	private Integer entrezId; 
	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getVariantStr() {
		return variantStr;
	}

	public void setVariantStr(String variantStr) {
		this.variantStr = variantStr;
	}

	public Integer getEntrezId() {
		return entrezId;
	}

	public void setEntrezId(Integer entrezId) {
		this.entrezId = entrezId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PeerEQTLKey) {
			PeerEQTLKey cast = (PeerEQTLKey) obj;
			return this.getDataType().equals(cast.getDataType()) && 
					this.getEntrezId().equals(cast.getEntrezId()) && 
					this.getVariantStr().equals(cast.getVariantStr());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int start = 17;
		start = 31 * start + this.getDataType().hashCode();
		start = 31 * start + this.getVariantStr().hashCode();
		start = 31 * start + this.getEntrezId();
		return start;
	}
	
}
