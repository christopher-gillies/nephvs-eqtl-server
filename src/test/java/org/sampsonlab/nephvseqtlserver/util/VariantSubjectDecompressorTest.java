package org.sampsonlab.nephvseqtlserver.util;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.sampsonlab.nephcseqtlsever.util.VariantSubjectDecompressor;
import org.sampsonlab.nephvseqtlserver.entities.VariantSubject;

public class VariantSubjectDecompressorTest {

	@Test
	public void testVariantSubjectDecompressorTest0() {
		List<VariantSubject> vs = new LinkedList<>();
		List<Integer> ids = new LinkedList<>();
		
		//create ids
		for(int i = 0; i < 10; i++) {
			ids.add(i);
		}
		
		//store alt alleles
		VariantSubject v1 = VariantSubject.create(0, "aaa", 2);
		VariantSubject v2 = VariantSubject.create(1, "aaa", 1);
		
		vs.add(v1);
		vs.add(v2);
		
		
		List<VariantSubject> result = VariantSubjectDecompressor.decompress(vs, ids, 0.1);
		
		int count0 = 0;
		int count1 = 0;
		int count2 = 0;
		Set<Integer> resIds = new HashSet<Integer>();
		
		for(VariantSubject v : result) {
			if(v.getGenotypeInt().equals(0)) {
				count0++;
			} else if(v.getGenotypeInt().equals(1)) {
				count1++;
			} else {
				count2++;
			}
			resIds.add(v.getKey().getSubjectId());
		};
		
		assertEquals(count0, 8);
		assertEquals(count1, 1);
		assertEquals(count2, 1);
		
		assertEquals(resIds.size(), 10);
	}
	
	@Test
	public void testVariantSubjectDecompressorTest2() {
		List<VariantSubject> vs = new LinkedList<>();
		List<Integer> ids = new LinkedList<>();
		
		//create ids
		for(int i = 0; i < 10; i++) {
			ids.add(i);
		}
		
		//ref is minor allele
		VariantSubject v1 = VariantSubject.create(5, "aaa", 1);
		VariantSubject v3 = VariantSubject.create(3, "aaa", 1);
		VariantSubject v2 = VariantSubject.create(7, "aaa", 0);
		
		vs.add(v1);
		vs.add(v2);
		vs.add(v3);
		
		List<VariantSubject> result = VariantSubjectDecompressor.decompress(vs, ids,0.6);
		
		int count0 = 0;
		int count1 = 0;
		int count2 = 0;
		
		Set<Integer> resIds = new HashSet<Integer>();
		
		for(VariantSubject v : result) {
			if(v.getGenotypeInt().equals(0)) {
				count0++;
			} else if(v.getGenotypeInt().equals(1)) {
				count1++;
			} else {
				count2++;
			}
			resIds.add(v.getKey().getSubjectId());
		};
		
		assertEquals(count0, 1);
		assertEquals(count1, 2);
		assertEquals(count2, 7);
		
		assertEquals(resIds.size(), 10);
	}
	
	
	@Test
	public void testVariantSubjectDecompressorTest3() {
		List<VariantSubject> vs = new LinkedList<>();
		List<Integer> ids = new LinkedList<>();
		
		//create ids
		for(int i = 0; i < 10; i++) {
			ids.add(i);
		}
		
		//ref is minor allele
		VariantSubject v1 = VariantSubject.create(5, "aaa", 1);
		VariantSubject v2 = VariantSubject.create(3, "aaa", 1);
		
		vs.add(v1);
		vs.add(v2);
		
		List<VariantSubject> result = VariantSubjectDecompressor.decompress(vs, ids,0.6);
		
		int count0 = 0;
		int count1 = 0;
		int count2 = 0;
		
		Set<Integer> resIds = new HashSet<Integer>();
		
		for(VariantSubject v : result) {
			if(v.getGenotypeInt().equals(0)) {
				count0++;
			} else if(v.getGenotypeInt().equals(1)) {
				count1++;
			} else {
				count2++;
			}
			resIds.add(v.getKey().getSubjectId());
		};
		
		assertEquals(count0, 0);
		assertEquals(count1, 2);
		assertEquals(count2, 8);
		
		assertEquals(resIds.size(), 10);
	}
	
	
	@Test
	public void testVariantSubjectDecompressorTest4() {
		List<VariantSubject> vs = new LinkedList<>();
		List<Integer> ids = new LinkedList<>();
		
		//create ids
		for(int i = 0; i < 10; i++) {
			ids.add(i);
		}
		
		//alt is minor allele
		VariantSubject v1 = VariantSubject.create(5, "aaa", 1);
		VariantSubject v2 = VariantSubject.create(3, "aaa", 1);
		
		vs.add(v1);
		vs.add(v2);
		
		List<VariantSubject> result = VariantSubjectDecompressor.decompress(vs, ids,0.4);
		
		int count0 = 0;
		int count1 = 0;
		int count2 = 0;
		
		Set<Integer> resIds = new HashSet<Integer>();
		
		for(VariantSubject v : result) {
			if(v.getGenotypeInt().equals(0)) {
				count0++;
			} else if(v.getGenotypeInt().equals(1)) {
				count1++;
			} else {
				count2++;
			}
			resIds.add(v.getKey().getSubjectId());
		};
		
		assertEquals(count0, 8);
		assertEquals(count1, 2);
		assertEquals(count2, 0);
		
		assertEquals(resIds.size(), 10);
	}
}
