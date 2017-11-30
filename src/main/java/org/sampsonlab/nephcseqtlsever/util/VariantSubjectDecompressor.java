package org.sampsonlab.nephcseqtlsever.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

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
	public static List<VariantSubject> decompress(List<VariantSubject> vs, List<Integer> allIds) {
		
		if(vs.size() == 0) {
			throw new IllegalArgumentException("There are no variant subjects to decompress");
		}
		
		List<VariantSubject> result = new ArrayList<>(allIds.size());
		
		//store subjects into a set
		
		Set<Integer> idSet = new HashSet<>();
		idSet.addAll(allIds);
		
		String variantStr = vs.get(0).getKey().getVariantStr();
		
		HashMap<Integer,List<VariantSubject>> vsMap = new HashMap<>();
		vsMap.put(0, new LinkedList<>());
		vsMap.put(1, new LinkedList<>());
		vsMap.put(2, new LinkedList<>());
		
		/*
		 * Store all subjects into corresponding categories
		 */
		vs.forEach(v -> {
			List<VariantSubject> vsList = vsMap.get(v.getGenotypeInt());
			vsList.add(v);
			//remove identifier from the set so we know who to add to the vsMap later
			idSet.remove(v.getKey().getSubjectId());
		});
		
		if(vsMap.get(0).size() == 0 && vsMap.get(2).size() > 0) {
			//add all subjects with 0
			idSet.forEach(id -> {
				VariantSubject v = VariantSubject.create(id, variantStr, 0);
				List<VariantSubject> vsList = vsMap.get(0);
				vsList.add(v);
			});
		} else if(vsMap.get(2).size() == 0 && vsMap.get(0).size() > 0) {
			//add all subjects with 2
			idSet.forEach(id -> {
				VariantSubject v = VariantSubject.create(id, variantStr, 2);
				List<VariantSubject> vsList = vsMap.get(2);
				vsList.add(v);
			});
		} else {
			throw new IllegalArgumentException("There are no variant subjects have no homozygotes");
		}
		
		//store all into list
		for(Entry<Integer, List<VariantSubject>> entry : vsMap.entrySet()) {
			result.addAll(entry.getValue());
		}
		
		return result;
		
	}
}
