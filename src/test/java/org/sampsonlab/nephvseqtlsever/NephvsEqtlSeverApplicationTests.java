package org.sampsonlab.nephvseqtlsever;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sampsonlab.nephvseqtlsever.domain.Query;
import org.sampsonlab.nephvseqtlsever.domain.Region;
import org.sampsonlab.nephvseqtlsever.dto.EQTLEntry;
import org.sampsonlab.nephvseqtlsever.dto.EQTLResult;
import org.sampsonlab.nephvseqtlsever.entities.PeerEQTL;
import org.sampsonlab.nephvseqtlsever.repositories.GeneRepository;
import org.sampsonlab.nephvseqtlsever.repositories.PeerEQTLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NephvsEqtlSeverApplicationTests {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PeerEQTLRepository peerEQTLRepository;
	
	@Autowired
	private GeneRepository geneRepository;
	
	@Test
	public void contextLoads() {
		assertNotNull(peerEQTLRepository);
	}
	
	@Test
	public void testFindByEntrez() {
		List<PeerEQTL> res = peerEQTLRepository.findByEntrezId(79501L);
	/*
		for(PeerEQTL p : res) {
			logger.info(p.getKey().getVariantStr());
		}
	*/
		
		assertTrue(res.size() > 0);
	}
	
	
	@Test
	public void testFindAllSymbols() {
		List<String> symbols = geneRepository.findAllSymbols();
		/*
		symbols.forEach((s) -> {
			logger.info(s);
		});
		*/
		
		assertTrue(symbols.size() > 0);
	}
	
	
	@Test
	public void testFindByGeneSymbol() {
		logger.info( "testFindByGeneSymbol" );
		Query q =  new Query("inf2");
		List<Object[]> res = peerEQTLRepository.findByGeneSymbolAndMaxPVal(q.getQuery(), 0.05);
		
		logger.info( Integer.toString(res.size()));
		
		assertTrue(res.size() > 0);
		
		EQTLResult result = EQTLResult.createFromListObjectEQTL(res, q);
		/*
		result.getGlom().forEach(eqtl -> {
			logger.info(eqtl.getDbSNPId() + " " + eqtl.getVariantStr());
		});
		*/
		assertNotNull(result);
	}

	@Test
	public void findByEntrezIdAndMaxPVal() {
		logger.info( "findByEntrezIdAndMaxPVal" );
		Query q =  new Query("64423");
		List<Object[]> res = peerEQTLRepository.findByEntrezIdAndMaxPVal(q.getEntrez(), 0.05);
		
		logger.info( Integer.toString(res.size()));
		
		assertTrue(res.size() > 0);
		
		EQTLResult result = EQTLResult.createFromListObjectEQTL(res, q);
		/*
		result.getGlom().forEach(eqtl -> {
			logger.info(eqtl.getDbSNPId() + " " + eqtl.getVariantStr());
		});
		*/
		assertNotNull(result);
	}
	
	@Test
	public void findByEnsemblAndMaxPVal() {
		logger.info( "findByEntrezIdAndMaxPVal" );
		Query q =  new Query("ENSG00000203485");
		List<Object[]> res = peerEQTLRepository.findByEnsemblAndMaxPVal(q.getQuery(), 0.05);
		
		logger.info( Integer.toString(res.size()));
		
		assertTrue(res.size() > 0);
		
		EQTLResult result = EQTLResult.createFromListObjectEQTL(res, q);
		/*
		result.getGlom().forEach(eqtl -> {
			logger.info(eqtl.getDbSNPId() + " " + eqtl.getVariantStr());
		});
		*/
		assertNotNull(result);
	}
	
	@Test
	public void testFindBydbSNPId() {
		logger.info( "testFindBydbSNPId" );
		Query q =  new Query("RS78786296");
		List<Object[]> res = peerEQTLRepository.findBydbSNP(q.getQuery());
		
		logger.info( Integer.toString(res.size()));
		
		assertTrue(res.size() > 0);
		
	}
	
	@Test
	public void testFindByVariant() {
		logger.info( "testFindByVariant" );
		Query q =  new Query("1:10108");
		Region r = Region.createFromQuery(q);
		
		List<Object[]> res = peerEQTLRepository.findByRegion(r.getChrom(), r.getStart(), r.getEnd());
		
		logger.info( Integer.toString(res.size()));
		
		assertTrue(res.size() > 0);
		
	}
	
	@Test
	public void testFindByRegion() {
		logger.info( "testFindByRegion" );
		Query q =  new Query("1:10108-10144");
		Region r = Region.createFromQuery(q);
		
		List<Object[]> res = peerEQTLRepository.findByRegion(r.getChrom(), r.getStart(), r.getEnd());
		
		logger.info( Integer.toString(res.size()));
		
		assertTrue(res.size() > 0);
		
	}
	
	@Test
	public void testFindByEntrezIdAndVariantStrAndDataType() {
		logger.info( "testfindByEntrezIdAndVariantStrAndDataType" );

		
		PeerEQTL res = peerEQTLRepository.findByEntrezIdAndVariantStrAndDataType(79501L, "1:10108_C/CT","glom");
		
		assertNotNull(res);
		assertNotNull(res.getGene().getGeneExpr());
		assertEquals(res.getGene().getGeneExpr().get(0).getKey().getTissue(),"glom");
		logger.info(res.getGene().getGeneCoord().getChrom());
		logger.info(res.getGene().getGeneCoord().getTes().toString());
		

		logger.info(res.getGene().getGeneExpr().get(0).getExpr().toString());
		logger.info(res.getGene().getGeneExpr().get(0).getKey().getTissue());
		
	}
}
