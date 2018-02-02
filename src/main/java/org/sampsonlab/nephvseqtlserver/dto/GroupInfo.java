package org.sampsonlab.nephvseqtlserver.dto;

import java.util.List;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.rank.Max;

public class GroupInfo {
	
	private GroupInfo() {
		
	}
	
	public static GroupInfo create(List<ExpressionAndGenotype> expGts, Integer gt, String ref, String alt) {
		GroupInfo res = new GroupInfo();
		res.whiskers = new Double[2];
		res.quartile = new Double[3];
		
		StandardDeviation sd = new StandardDeviation();
		Percentile perc = new Percentile();
		Min min = new Min();
		Max max = new Max();
		
		
		return res;
	}
	
	private Double min;
	private Double max;
	private Integer n;
	private String groupKey;
	private List<ExpressionAndGenotype> outliers;
	private Double stdDev;
	private Double[] whiskers;
	//.25, .5, .75
	private Double[] quartile;
}
