package org.sampsonlab.nephvseqtlserver.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sampsonlab.nephvseqtlserver.domain.Query;
import org.sampsonlab.nephvseqtlserver.domain.Query.Type;

public class QueryTests {

	@Test
	public void testCreateDbSNP() {
		Query q = new Query("rs123");
		assertTrue(q.getType() == Type.dbSNP);
	}
	
	@Test
	public void testCreateVariant() {
		Query q = new Query("X:123");
		assertTrue(q.getType() == Type.Variant);
	}
	
	@Test
	public void testCreateVariant2() {
		Query q = new Query("22:123");
		assertTrue(q.getType() == Type.Variant);
	}
	
	@Test
	public void testCreateRegion() {
		Query q = new Query("22:123-1244");
		assertTrue(q.getType() == Type.Region);
	}
	
	@Test
	public void testCreateGeneSymbol() {
		Query q = new Query("APOL1");
		assertTrue(q.getType() == Type.GeneSymbol);
	}
}
