package org.sampsonlab.nephcseqtlserver.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.sampsonlab.nephvseqtlserver.entities.VariantSubject;

import java.util.Set;

public class VariantSubjectDecompressor {
	
	/**
	 * decompress
	 * Assumes variants are bialleleic and all are from the same variantStr
	 * @param List<VariantSubject> vs
	 * @param List<Integer> allIds
	 * @return List<VariantSubject> for all subjects if 0s are missing then we add subjects with 0s if 2s are missing we add subjects with 2s
	 */
	public static List<VariantSubject> decompress(List<VariantSubject> vs, List<Integer> allIds, double af) {
		
		if(vs.size() == 0) {
			throw new IllegalArgumentException("There are no variant subjects to decompress");
		}
		
		List<VariantSubject> result = new ArrayList<>(allIds.size());
		
		//store subjectsId into a set
		Set<Integer> idSet = new HashSet<>();
		idSet.addAll(allIds);
		
		String variantStr = vs.get(0).getKey().getVariantStr();
		
		
		/*
		 * Store all subjects known subjects into list and remove their id from the idSet so we don't add them twice
		 */
		vs.forEach(v -> {
			result.add(v);
			//remove identifier from the set so we know who to add to the vsMap later
			idSet.remove(v.getKey().getSubjectId());
		});
		
		if(af < 0.5) {
			//the alternative allele is the minor allele
			//therefore only alternative alleles were stored
			//therefore we must generate reference alleles for not stored subjects
			
			idSet.forEach(id -> {
				VariantSubject v = VariantSubject.create(id, variantStr, 0);
				result.add(v);
			});
			
		} else {
			//the reference allele is the minor allele
			//therefore only reference alleles were stored
			//therefore we must generate alternative alleles for not stored subjects
			
			idSet.forEach(id -> {
				VariantSubject v = VariantSubject.create(id, variantStr, 2);
				result.add(v);
			});
		}
		
		return result;
		
	}
}
