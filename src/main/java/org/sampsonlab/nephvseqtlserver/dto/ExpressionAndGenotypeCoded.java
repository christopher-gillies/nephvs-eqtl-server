package org.sampsonlab.nephvseqtlserver.dto;

import java.io.Serializable;

import org.apache.commons.math3.util.Precision;

public class ExpressionAndGenotypeCoded implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1628305341291796752L;

	private ExpressionAndGenotypeCoded() {
		
	}
	
	public static ExpressionAndGenotypeCoded create(ExpressionAndGenotype expGt, String ref, String alt) {
		ExpressionAndGenotypeCoded res = new ExpressionAndGenotypeCoded();
		res.y = Double.toString(Precision.round(expGt.getExpr(), 2));
		res.group = createGroupKey(expGt.getGt(), ref, alt);
		return res;
	}
	
	public static String createGroupKey(Integer gt, String ref, String alt) {
		if(gt == 0) {
			return ref + "/" + ref + " (Coded: 0)";
		} else if(gt == 1) {
			return ref + "/" + alt + " (Coded: 1)";
		} else if(gt == 2) {
			return alt + "/" + alt + " (Coded: 2)";
		} else {
			throw new IllegalStateException("gt is not 0,1 or 2");
		}
	}
	
	//Expression
	private String y;
	
	//Group
	private String group;

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	
}
