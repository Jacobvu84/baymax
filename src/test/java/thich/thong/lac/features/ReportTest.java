package thich.thong.lac.features;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import thich.thong.lac.testlink.CucumberReport;
import thich.thong.lac.testlink.TestLinkBase;

public class ReportTest {
	
	@Test
	public void testReport() throws ParserConfigurationException, SAXException, IOException 
	{
		TestLinkBase genFile = new TestLinkBase();
		CucumberReport cumReport = new CucumberReport();
		genFile.getSystemProperty();
		TestLinkAPI testLinkApi = genFile.connectTestLink(TestLinkBase.url,TestLinkBase.devKey);
		ArrayList<HashMap<String, String>> params = cumReport.getResultFromJunitReport(TestLinkBase.reportPath);
		for(int i = 0; i<params.size(); i++){
			genFile.reportTCResultToTestLink(testLinkApi, TestLinkBase.project, TestLinkBase.planName, TestLinkBase.buildName,params.get(i).get("name"), params.get(i).get("result"));
		}
		//genFile.reportTCResultToTestLink(testLinkApi, TestLinkBase.project, "M1", 1337, "p");
	}
}
