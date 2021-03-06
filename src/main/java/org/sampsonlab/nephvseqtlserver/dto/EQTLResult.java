package org.sampsonlab.nephvseqtlserver.dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.sampsonlab.nephvseqtlserver.domain.Query;
import org.sampsonlab.nephvseqtlserver.entities.PeerEQTL;

public class EQTLResult {
	
	private EQTLResult() {
		
	}
	
	public static EQTLResult createEmpty() {
		return new EQTLResult();
	}
	
	public static PeerEQTL objectEQTLtoPeerEQTL(Object[] obj) {
		return (PeerEQTL) obj[0];
	}
	
	public static EQTLResult createFromListObjectEQTL(List<Object[]> objEqtls, Query query) {
		List<PeerEQTL> peerEqtls = new ArrayList<>(objEqtls.size());
		objEqtls.forEach(obj -> {
			peerEqtls.add(objectEQTLtoPeerEQTL(obj));
		});
		
		
		return EQTLResult.createFromListPeerEQTL(peerEqtls, query);
	}
	
	public static EQTLResult createFromListPeerEQTL(List<PeerEQTL> peerEqtls, Query query) {
		EQTLResult result = new EQTLResult();
		result.setQuery(query);
		//split into glom and tub
		peerEqtls.forEach((PeerEQTL eqtl) -> {
			if(eqtl.getKey().getDataType().equals("glom")) {
				result.glom.add(EQTLEntry.createEQTLEntryFromPeerEQTL(eqtl));
			} else {
				result.tub.add(EQTLEntry.createEQTLEntryFromPeerEQTL(eqtl));
			}
		});
		
		
		return result;
	}
	
	private List<EQTLEntry> glom = new LinkedList<>();
	private List<EQTLEntry> tub = new LinkedList<>();;
	private Query query;
	
	private DAPPlotResult dapResult;
	
	public List<EQTLEntry> getGlom() {
		return glom;
	}
	
	public void setGlom(List<EQTLEntry> glom) {
		this.glom = glom;
	}
	
	public List<EQTLEntry> getTub() {
		return tub;
	}
	
	public void setTub(List<EQTLEntry> tub) {
		this.tub = tub;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public DAPPlotResult getDapResult() {
		return dapResult;
	}

	public void setDapResult(DAPPlotResult dapResult) {
		this.dapResult = dapResult;
	}
	
	
	
	
	
}
