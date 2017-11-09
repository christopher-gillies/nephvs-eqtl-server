package org.sampsonlab.nephvseqtlsever.dto;

import java.util.LinkedList;
import java.util.List;

import org.sampsonlab.nephvseqtlsever.entities.PeerEQTL;

public class EQTLResult {
	
	private EQTLResult() {
		
	}
	
	public static EQTLResult createFromListPeerEQTL(List<PeerEQTL> peerEqtls) {
		EQTLResult result = new EQTLResult();
		
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
	
	
	
}
