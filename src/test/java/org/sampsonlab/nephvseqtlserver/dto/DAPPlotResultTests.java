package org.sampsonlab.nephvseqtlserver.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.sampsonlab.nephvseqtlserver.dto.DAPPlotResult.Tissue;
import org.sampsonlab.nephvseqtlserver.dto.DAPPlotResult.Variant;
import org.sampsonlab.nephvseqtlserver.entities.DAPGeneSummary;
import org.sampsonlab.nephvseqtlserver.entities.DAPVariantSummary;
import org.sampsonlab.nephvseqtlserver.entities.Gene;
import org.sampsonlab.nephvseqtlserver.entities.GeneCoord;

public class DAPPlotResultTests {

	
	@Test
	public void testCreate() {
		
		Gene g = new Gene();
		g.setEntrezId(123L);
		GeneCoord gc = new GeneCoord();
		gc.setChrom("1");
		gc.setEntrezId(123L);
		gc.setTes(10L);
		gc.setTss(1L);
		g.setGeneCoord(gc);
		
		DAPGeneSummary t1 = DAPGeneSummary.create(g, "glom", 0.1, 0.99);
		
		assertNotNull(t1.getGene());
		
		List<DAPVariantSummary> t1Vs = new LinkedList<>();
		
		DAPVariantSummary v1 = DAPVariantSummary.create("chr1:1",  123L, "glom", 1, 0.9, 2, 0.4);
		DAPVariantSummary v2 = DAPVariantSummary.create("chr1:2",  123L, "glom", 1, 0.9, 2, 0.5);
		DAPVariantSummary v3 = DAPVariantSummary.create("chr1:3",  123L, "glom", 2, 0.2, 1, 0.2);
		
		t1Vs.add(v1);
		t1Vs.add(v2);
		t1Vs.add(v3);
		
		t1.setVariants(t1Vs);
		
		
		DAPGeneSummary t2 = DAPGeneSummary.create(g, "tube", 0.4, 0.1);
		
		assertNotNull(t2.getGene());
		List<DAPVariantSummary> t2Vs = new LinkedList<>();
		
		DAPVariantSummary v4 = DAPVariantSummary.create("chr1:4",  123L, "tube", 1, 0.9, 2, 0.4);
		t2Vs.add(v4);
		t2.setVariants(t2Vs);
		
		List<DAPGeneSummary> geneSummaries = new LinkedList<>();
		geneSummaries.add(t1);
		geneSummaries.add(t2);
		
		DAPPlotResult result = DAPPlotResult.createFromCollectionOfDAPGeneSummary(geneSummaries);
		
		//check tissues
		assertTrue(result.getTissues().keySet().size() == 2);
		assertTrue(result.getTissues().containsKey("glom"));
		assertTrue(result.getTissues().containsKey("tube"));
		
		//check number of clusters
		Tissue glom = result.getTissues().get("glom");
		assertEquals(2,glom.clusters.size());
		
		Tissue tube = result.getTissues().get("tube");
		assertEquals(1,tube.clusters.size());
		
		//check cluster sizes
		assertEquals(2, glom.clusters.get(0).variants.size());
		assertEquals(1, glom.clusters.get(1).variants.size());
		
		assertEquals(1, tube.clusters.get(0).variants.size());
		
		//check cluster pips
		assertEquals(0.9, glom.clusters.get(0).pip,0.00001);
		assertEquals(0.2, glom.clusters.get(1).pip,0.00001);
		
		//check values values
		assertTrue(glom.clusters.get(0).variants.get(0).getVariantStr().equals("chr1:1"));
		assertTrue(glom.clusters.get(0).variants.get(0).getPip().equals(0.4));
		assertTrue(glom.clusters.get(0).variants.get(1).getVariantStr().equals("chr1:2"));
		assertTrue(glom.clusters.get(1).variants.get(0).getVariantStr().equals("chr1:3"));
		assertTrue(tube.clusters.get(0).variants.get(0).getVariantStr().equals("chr1:4"));
	}
	
	
	@Test
	public void testCreateVariant1() {
		String v1 = "chr11:22";
		
		Variant var = Variant.create(v1, 0.0);
		
		assertEquals("11",var.getChrom());
		assertEquals((int) 22, (int) var.getPos());
		assertNull(var.getRef());
		assertNull(var.getAlt());
	}
	
	@Test
	public void testCreateVariant2() {
		String v1 = "11:22";
		
		Variant var = Variant.create(v1, 0.0);
		
		assertEquals("11",var.getChrom());
		assertEquals((int) 22, (int) var.getPos());
		assertNull(var.getRef());
		assertNull(var.getAlt());
	}
	
	@Test
	public void testCreateVariant3() {
		String v1 = "11:22_AAA/TTT";
		
		Variant var = Variant.create(v1, 0.0);
		
		assertEquals("11",var.getChrom());
		assertEquals((int) 22, (int) var.getPos());
		assertEquals("AAA",var.getRef());
		assertEquals("TTT",var.getAlt());
	}
}
