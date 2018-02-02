package org.sampsonlab.nephvseqtlserver.dto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BoxPlotMetaData {
	
	private BoxPlotMetaData() {
		
	}
	
	public static BoxPlotMetaData create(List<ExpressionAndGenotype> exprAndGts, String ref, String alt) {
		BoxPlotMetaData res = new BoxPlotMetaData();
		
		//Store expression,genotype pairs by genotype in a hashtable
		HashMap<Integer, List<ExpressionAndGenotype>> groupMap = new HashMap<>();
		for(ExpressionAndGenotype expGt : exprAndGts) {
			if(groupMap.containsKey(expGt.getGt())) {
				List<ExpressionAndGenotype> groupList = groupMap.get(expGt.getGt());
				groupList.add(expGt);
			} else {
				List<ExpressionAndGenotype> groupList = new LinkedList<>();
				groupList.add(expGt);
			}
		}
		
		//Store the genotypes ids (keys)
		List<Integer> gts = new LinkedList<>();
		gts.addAll(groupMap.keySet());
		
		return res;
	}
	
}
