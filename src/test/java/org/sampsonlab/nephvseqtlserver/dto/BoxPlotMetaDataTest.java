package org.sampsonlab.nephvseqtlserver.dto;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class BoxPlotMetaDataTest {
	
	
	@Test
	public void testCreate() {
		
		
		List<ExpressionAndGenotype> expGts = new LinkedList<ExpressionAndGenotype>();
		
		for(int i = 1; i <= 100; i++) {
			expGts.add(ExpressionAndGenotype.create((double) (i), 0));
		}
		
		for(int i = 101; i <= 200; i++) {
			expGts.add(ExpressionAndGenotype.create((double) (i), 1));
		}
		
		for(int i = 201; i <= 300; i++) {
			expGts.add(ExpressionAndGenotype.create((double) (i), 2));
		}
		
		
		BoxPlotMetaData data = BoxPlotMetaData.create(expGts, "A", "T");
		
		
		assertEquals(300.0, data.getMaxY(), 0.001);
		assertEquals(1.0, data.getMinY(), 0.001);
		
		assertEquals(3, data.getGroupKeys().size());
		
		assertEquals("A/A (Coded: 0)", data.getGroupKeys().get(0));
		assertEquals("A/T (Coded: 1)", data.getGroupKeys().get(1));
		assertEquals("T/T (Coded: 2)", data.getGroupKeys().get(2));
		
		assertEquals(3, data.getGroupInfos().size());
	}
}
