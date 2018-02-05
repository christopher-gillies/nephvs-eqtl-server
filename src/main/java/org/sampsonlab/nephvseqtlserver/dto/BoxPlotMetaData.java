package org.sampsonlab.nephvseqtlserver.dto;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;


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
				groupMap.put(expGt.getGt(), groupList);
			}
		}
		
		//Store the genotypes ids (keys)
		List<Integer> gts = new LinkedList<>();
		gts.addAll(groupMap.keySet());
		//sort so they are in ascending order
		gts.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				int a = arg0;
				int b = arg1;
				if(a < b) {
					return -1;
				} else if (a > b) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		
		List<String> groupKeys = new LinkedList<String>();
		List<GroupInfo> groupInfos = new LinkedList<GroupInfo>();
		double mins[] = new double[gts.size()];
		double maxs[] = new double[gts.size()];
		
		//create each groups groupinfo
		//store mins and maxes to get overal values for these
		int index = 0;
		for(Integer gt : gts) {
			GroupInfo info = GroupInfo.create(groupMap.get(gt), gt, ref, alt);
			groupInfos.add(info);
			groupKeys.add(info.getGroup());
			mins[index] = info.getMin();
			maxs[index] = info.getMax();
			index++;
		}
		
		Min min = new Min();
		Max max = new Max();
		
		double minY = min.evaluate(mins);
		double maxY = max.evaluate(maxs);
		
		res.groupInfos = groupInfos;
		res.groupKeys = groupKeys;
		res.minY = minY;
		res.maxY = maxY;
		
		return res;
	}
	
	
	private List<GroupInfo> groupInfos;
	private List<String> groupKeys;
	private Double minY;
	private Double maxY;
	
	public List<GroupInfo> getGroupInfos() {
		return groupInfos;
	}

	public void setGroupInfos(List<GroupInfo> groupInfos) {
		this.groupInfos = groupInfos;
	}

	public List<String> getGroupKeys() {
		return groupKeys;
	}

	public void setGroupKeys(List<String> groupKeys) {
		this.groupKeys = groupKeys;
	}

	public Double getMinY() {
		return minY;
	}

	public void setMinY(Double minY) {
		this.minY = minY;
	}

	public Double getMaxY() {
		return maxY;
	}

	public void setMaxY(Double maxY) {
		this.maxY = maxY;
	}
	
	
}
