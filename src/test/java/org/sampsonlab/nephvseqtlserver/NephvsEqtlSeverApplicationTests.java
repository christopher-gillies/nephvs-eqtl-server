package org.sampsonlab.nephvseqtlserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sampsonlab.nephvseqtlserver.domain.Query;
import org.sampsonlab.nephvseqtlserver.domain.Region;
import org.sampsonlab.nephvseqtlserver.dto.EQTLEntry;
import org.sampsonlab.nephvseqtlserver.dto.EQTLResult;
import org.sampsonlab.nephvseqtlserver.entities.DAPGeneSummary;
import org.sampsonlab.nephvseqtlserver.entities.DAPVariantSummary;
import org.sampsonlab.nephvseqtlserver.entities.PeerEQTL;
import org.sampsonlab.nephvseqtlserver.entities.VariantSubject;
import org.sampsonlab.nephvseqtlserver.repositories.DAPRepository;
import org.sampsonlab.nephvseqtlserver.repositories.GeneRepository;
import org.sampsonlab.nephvseqtlserver.repositories.PeerEQTLRepository;
import org.sampsonlab.nephvseqtlserver.repositories.SubjectRepository;
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
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private DAPRepository dapRepository;
	
	
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
	
	@Test
	public void testFindByVariantStrTest() {
		logger.info( "testFindByVariantStrTest" );

		
		List<VariantSubject> res = peerEQTLRepository.findByVariantStr("1:10108_C/CT");
		
		assertNotNull(res);
		assertTrue(res.size() > 0);
		logger.info(res.get(0).getGenotypeInt().toString());
		logger.info(Integer.toString(res.size()));
	}
	
	@Test
	public void testFindAllSubjects() {
		logger.info( "testFindAllSubjects" );
		List<Integer> ids = subjectRepository.findAllIds();
		
		assertTrue(ids.size() > 0);
	}
	
	@Test
	public void testFindDAPGeneSummaryByEntrezId() {
		logger.info( "testFindDAPGeneSummaryByEntrezId" );
		
		Set<DAPGeneSummary> res = dapRepository.findByEntrezId(79501L);
		
		logger.info( "Result size: " + Integer.toString(res.size()) );
		
		assertTrue(res.size() == 2);
		for(DAPGeneSummary dg : res) {
			logger.info( Integer.toString(dg.hashCode()  ));
			assertTrue( dg.getVariants().size() > 0);
			assertNotNull(dg.getGene());
			assertNotNull(dg.getGene().getGeneCoord());
			
			logger.info(dg.getGene().getSymbol());
			logger.info(dg.getGene().getGeneCoord().getChrom());
			
			for(int i = 0; i < dg.getVariants().size() && i < 10; i++) {
				DAPVariantSummary var = dg.getVariants().get(i);
				logger.info(var.getKey().getVariantStr()  + ": " + var.getClusterPIP() + ": " + var.getSnpPIP());
			}
		}
		
	
		
		

	}
}
