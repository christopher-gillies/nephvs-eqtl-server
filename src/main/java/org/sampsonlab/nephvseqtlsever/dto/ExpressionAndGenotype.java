package org.sampsonlab.nephvseqtlsever.dto;

import java.io.Serializable;

public class ExpressionAndGenotype implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6158796348386597954L;
	private Double expr;
	private Integer gt;
	
	public Double getExpr() {
		return expr;
	}
	public void setExpr(Double expr) {
		this.expr = expr;
	}
	public Integer getGt() {
		return gt;
	}
	public void setGt(Integer gt) {
		this.gt = gt;
	}
	
	public static ExpressionAndGenotype create(Double expr, Integer gt) {
		ExpressionAndGenotype a = new ExpressionAndGenotype();
		a.expr = expr;
		a.gt = gt;
		return a;
	}
	
	
	
	
}
