package org.sampsonlab.nephvseqtlserver.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class GroupInfoTest {

	@Test
	public void testCreate1() {
		GroupInfo res;
		
		List<ExpressionAndGenotype> expGts = new LinkedList<ExpressionAndGenotype>();
		
		for(int i = 1; i <= 100; i++) {
			expGts.add(ExpressionAndGenotype.create((double) (i), 1));
		}
		
		res = GroupInfo.create(expGts, 1, "A", "T");
		
		assertNotNull(res);
		
		assertEquals("A/T (Coded: 1)",res.getGroup());
		
		
		assertEquals(100.0,res.getMax(),0.001);
		assertEquals(1.0,res.getMin(),0.001);
		assertEquals(50.5,res.getMean(),0.001);
		
		assertEquals(100, (int) res.getN());
		
		assertTrue( res.getOutliers().size() == 0);
		
		assertEquals(29.01 ,res.getStdDev(),0.01);
		
		assertEquals(25.0,res.getQuartile()[0],1.0);
		assertEquals(50.0,res.getQuartile()[1],1.0);
		assertEquals(75.0,res.getQuartile()[2],1.0);
		
		assertEquals(1,res.getWhiskers()[0],0.001);
		assertEquals(100,res.getWhiskers()[1],0.001);
	}
	
	
	@Test
	public void testCreate2() {
		GroupInfo res;
		
		List<ExpressionAndGenotype> expGts = new LinkedList<ExpressionAndGenotype>();
		
		for(int i = 1; i <= 100; i++) {
			expGts.add(ExpressionAndGenotype.create((double) (i), 1));
		}
		
		expGts.add(ExpressionAndGenotype.create(-1000.0, 1));
		expGts.add(ExpressionAndGenotype.create(1000.0, 1));
		
		res = GroupInfo.create(expGts, 1, "A", "T");
		
		assertEquals(25.0,res.getQuartile()[0],1.0);
		assertEquals(50.0,res.getQuartile()[1],1.0);
		assertEquals(75.0,res.getQuartile()[2],1.0);
		
		assertTrue( res.getOutliers().size() == 2);
		
		assertEquals(res.getQuartile()[0] - (res.getQuartile()[2] - res.getQuartile()[0]) * 1.5 ,res.getWhiskers()[0],0.1);
		assertEquals(res.getQuartile()[2] + (res.getQuartile()[2] - res.getQuartile()[0]) * 1.5 ,res.getWhiskers()[1],0.1);
	}
}
