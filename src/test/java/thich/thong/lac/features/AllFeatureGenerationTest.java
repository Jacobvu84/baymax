package thich.thong.lac.features;

import java.io.IOException;

import org.junit.Test;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import thich.thong.lac.testlink.TestLinkBase;

public class AllFeatureGenerationTest {

	@Test
	public void generateMultiFeatureFiles() throws IOException{
		TestLinkBase genFile = new TestLinkBase();
		genFile.getSystemProperty();
		TestLinkAPI testLinkApi = genFile.connectTestLink(TestLinkBase.url,TestLinkBase.devKey);
		genFile.generateAllFeatureFile(TestLinkBase.pathFeature,testLinkApi,TestLinkBase.project,TestLinkBase.planName);
	}
}
