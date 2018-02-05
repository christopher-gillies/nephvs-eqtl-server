package org.sampsonlab.nephvseqtlserver.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExpressionAndGenotypeCodedTest {
	
	@Test
	public void testGroupKey() {
		String key = ExpressionAndGenotypeCoded.createGroupKey(0, "A", "T");
		assertEquals("A/A (Coded: 0)", key);
		
		key = ExpressionAndGenotypeCoded.createGroupKey(1, "A", "T");
		assertEquals("A/T (Coded: 1)", key);
		
		key = ExpressionAndGenotypeCoded.createGroupKey(2, "A", "T");
		assertEquals("T/T (Coded: 2)", key);
		
	}
	
	@Test
	public void testCreate() {
		ExpressionAndGenotype in = ExpressionAndGenotype.create(-1.114, 1);
		ExpressionAndGenotypeCoded coded = ExpressionAndGenotypeCoded.create(in, "A", "T");
		
		assertEquals("-1.11", coded.getY());
		assertEquals("A/T (Coded: 1)", coded.getGroup());
		
		
	}

}
