package org.sampsonlab.nephvseqtlsever.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "geneExprMicroArray")
public class GeneExpr {


	@EmbeddedId
	private GeneExprKey key;

	@Column(name="expr")
	private Double expr;

	public GeneExprKey getKey() {
		return key;
	}

	public void setKey(GeneExprKey key) {
		this.key = key;
	}

	public Double getExpr() {
		return expr;
	}

	public void setExpr(Double expr) {
		this.expr = expr;
	}
	
	
	
}
