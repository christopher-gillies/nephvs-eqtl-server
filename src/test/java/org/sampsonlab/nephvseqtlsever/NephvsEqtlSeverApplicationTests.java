package org.sampsonlab.nephvseqtlsever;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
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
	
	@Test
	public void contextLoads() {
		assertNotNull(peerEQTLRepository);
	}
	
	@Test
	public void testFindByEntrez() {
		List<PeerEQTL> res = peerEQTLRepository.findByEntrezId(79501);
		for(PeerEQTL p : res) {
			logger.info(p.getKey().getVariantStr());
		}
		
	}
	
	

}
