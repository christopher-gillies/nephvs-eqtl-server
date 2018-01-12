package org.sampsonlab.nephvseqtlserver.dto;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sampsonlab.nephvseqtlserver.entities.DAPGeneSummary;
import org.sampsonlab.nephvseqtlserver.entities.Gene;

public class GeneSummaryTest {

	@Test
	public void testCreateGeneSummary() {
		Gene gene = new Gene();
		gene.setEntrezId(1L);
		
		DAPGeneSummary dgs = DAPGeneSummary.create(gene, "glom", 0.01, 0.9);
		dgs.setFdr(0.1);
		
		GeneSummary gs = GeneSummary.create(dgs);
		
		assertTrue(gs.getFdr() == 0.1);
	}
}
