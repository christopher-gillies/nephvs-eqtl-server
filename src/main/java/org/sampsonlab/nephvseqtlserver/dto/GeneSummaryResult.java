package org.sampsonlab.nephvseqtlserver.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.sampsonlab.nephvseqtlserver.entities.DAPGeneSummary;

public class GeneSummaryResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 925336928826460652L;
	private List<GeneSummary> glomSummaries;
	private List<GeneSummary> tubSummaries;
	
	private GeneSummaryResult() {
		glomSummaries = new LinkedList<>();
		tubSummaries = new LinkedList<>();
	}
	
	public static GeneSummaryResult create(Collection<DAPGeneSummary> dgsList) {
		GeneSummaryResult result = new GeneSummaryResult();
		
		for(DAPGeneSummary dgs : dgsList) {
			GeneSummary gs = GeneSummary.create(dgs);
			if(gs.getTissue().equals("glom")) {
				result.glomSummaries.add(gs);
			} else {
				result.tubSummaries.add(gs);
			}
		}
		
		
		return result;
	}

	public List<GeneSummary> getGlomSummaries() {
		return glomSummaries;
	}

	public void setGlomSummaries(List<GeneSummary> glomSummaries) {
		this.glomSummaries = glomSummaries;
	}

	public List<GeneSummary> getTubSummaries() {
		return tubSummaries;
	}

	public void setTubSummaries(List<GeneSummary> tubSummaries) {
		this.tubSummaries = tubSummaries;
	}

	
	
	
}
