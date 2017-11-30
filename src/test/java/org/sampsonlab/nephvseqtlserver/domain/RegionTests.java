package org.sampsonlab.nephvseqtlserver.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sampsonlab.nephvseqtlserver.domain.Query;
import org.sampsonlab.nephvseqtlserver.domain.Region;

public class RegionTests {

	@Test
	public void testVariant() {
		Query q = new Query("chr1:123");
		
		Region r = Region.createFromQuery(q);
		
		assertEquals(r.getChrom(), "1");
		assertEquals(r.getStart(), 123);
		assertEquals(r.getEnd(), 123);
	}

	@Test
	public void testRegion() {
		Query q = new Query("chr1:123-234");
		
		Region r = Region.createFromQuery(q);
		
		assertEquals(r.getChrom(), "1");
		assertEquals(r.getStart(), 123);
		assertEquals(r.getEnd(), 234);
	}
}
