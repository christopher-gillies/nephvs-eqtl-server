package org.sampsonlab.nephvseqtlsever;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
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

}
