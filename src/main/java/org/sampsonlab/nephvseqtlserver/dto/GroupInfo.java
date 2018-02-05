package org.sampsonlab.nephvseqtlserver.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.rank.Max;

public class GroupInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1380082604726467143L;

	private GroupInfo() {
		
	}
	
	public static GroupInfo create(List<ExpressionAndGenotype> expGts, Integer gt, String ref, String alt) {
		GroupInfo res = new GroupInfo();
		res.whiskers = new Double[2];
		res.quartile = new Double[3];
		
		
		
		double[] exprs = new double[expGts.size()];
		//Copy expression to double array for computation
		int index = 0;
		for(ExpressionAndGenotype expGt : expGts) {
			exprs[index++] = expGt.getExpr();
			if(!gt.equals(expGt.getGt())) {
				throw new IllegalArgumentException("Genotype does not match input gt");
			}
		}
		
		//Compute Standard Deviation
		StandardDeviation sd = new StandardDeviation(true);
		res.stdDev = sd.evaluate(exprs);
		
		//compute quartiles
		res.quartile = new Double[3];
		
		Percentile perc = new Percentile();
		perc = perc.withEstimationType(EstimationType.R_7);

		perc.setData(exprs);
		res.quartile[0] = perc.evaluate(25.0);
		res.quartile[1] = perc.evaluate(50.0);
		res.quartile[2] = perc.evaluate(75.0);
		
		//set min and max
		Min min = new Min();
		res.min = Precision.round( min.evaluate(exprs),  2);
		
		Max max = new Max();
		res.max = Precision.round(max.evaluate(exprs), 2);
		
		Mean mean = new Mean();
		res.mean = mean.evaluate(exprs);
		
		//set lower and upper whiskers
		double iqr = res.quartile[2] - res.quartile[0];
		double lowerWhisker = res.quartile[0] - 1.5 * iqr;
		double upperWhisker = res.quartile[2] + 1.5 * iqr;
		res.whiskers = new Double[2];
		res.whiskers[0] = Math.max(res.min, lowerWhisker);
		res.whiskers[1] = Math.min(res.max, upperWhisker);
		
		//set outliers
		res.outliers = new LinkedList<ExpressionAndGenotypeCoded>();
		for(ExpressionAndGenotype expGt : expGts) {
			if(expGt.getExpr() > upperWhisker || expGt.getExpr() < lowerWhisker) {
				res.outliers.add(ExpressionAndGenotypeCoded.create(expGt, ref, alt));
			}
		}
		
		//set groupKey
		res.group = ExpressionAndGenotypeCoded.createGroupKey(gt, ref, alt);
		
		//set n;
		res.n = expGts.size();
		
		return res;
	}
	
	private Double min;
	private Double max;
	private Integer n;
	//
	private String group;
	private List<ExpressionAndGenotypeCoded> outliers;
	private Double stdDev;
	private Double mean;
	
	private Double[] whiskers;
	//.25, .5, .75
	private Double[] quartile;
	
	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<ExpressionAndGenotypeCoded> getOutliers() {
		return outliers;
	}

	public void setOutliers(List<ExpressionAndGenotypeCoded> outliers) {
		this.outliers = outliers;
	}

	public Double getStdDev() {
		return stdDev;
	}

	public void setStdDev(Double stdDev) {
		this.stdDev = stdDev;
	}

	public Double[] getWhiskers() {
		return whiskers;
	}

	public void setWhiskers(Double[] whiskers) {
		this.whiskers = whiskers;
	}

	public Double[] getQuartile() {
		return quartile;
	}

	public void setQuartile(Double[] quartile) {
		this.quartile = quartile;
	}

	public Double getMean() {
		return mean;
	}

	public void setMean(Double mean) {
		this.mean = mean;
	}
	
	
	
	
}
