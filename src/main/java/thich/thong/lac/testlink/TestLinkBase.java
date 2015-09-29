package thich.thong.lac.testlink;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.constants.TestImportance;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import thich.thong.lac.util.LoadObject;

public class TestLinkBase {
	public static String pathFeature="";
	public static String pathFileToWriteTestLink="";
	public static String devKey="";
	public static String url="";
	public static String project="";
	public static String testProjectPrefix="";
	public static String platformName="";
	public static String reportPath="";
	public static String tagName="";
	public static String planName="";
	public static String buildName="";

	public void getSystemProperty() throws IOException{
		pathFeature = System.getProperty("pathFeature");
		pathFileToWriteTestLink = System.getProperty("pathFileToWriteTestLink");
		devKey = System.getProperty("devKey");
		url = System.getProperty("url");
		project = System.getProperty("project");
		platformName = System.getProperty("platformName");
		reportPath = System.getProperty("reportPath");
		tagName = System.getProperty("tagName");
		planName = System.getProperty("planName");
		buildName = System.getProperty("buildName");

		if (pathFeature==null || pathFeature=="") 
			pathFeature = LoadObject.loading_config_sys("setting.properties").getProperty("testlink.pathFeature");
		if (pathFileToWriteTestLink==null || pathFileToWriteTestLink=="") 
			pathFileToWriteTestLink = LoadObject.loading_config_sys("setting.properties").getProperty("testlink.pathFileToWriteTestLink");
		if (devKey==null || devKey=="") 
			devKey = LoadObject.loading_config_sys("setting.properties").getProperty("testlink.devKey");
		if (url==null || url=="") 
			url = LoadObject.loading_config_sys("setting.properties").getProperty("testlink.url");
		if (project==null || project=="") 
			project = LoadObject.loading_config_sys("setting.properties").getProperty("testlink.project");
		if (testProjectPrefix==null || testProjectPrefix=="") 
			testProjectPrefix = LoadObject.loading_config_sys("setting.properties").getProperty("testlink.testProjectPrefix");
		if (platformName==null || platformName=="") 
			platformName = LoadObject.loading_config_sys("setting.properties").getProperty("testlink.platformName");
		if (reportPath==null || reportPath=="") 
			reportPath = LoadObject.loading_config_sys("setting.properties").getProperty("testlink.reportPath");
		if (planName==null || planName=="") 
			planName = LoadObject.loading_config_sys("setting.properties").getProperty("testlink.planName");
		if (buildName==null || buildName=="") 
			buildName = LoadObject.loading_config_sys("setting.properties").getProperty("testlink.buildName");

		String fs = File.separator;
		pathFeature=System.getProperty("user.dir")+"/"+pathFeature.replace("/", fs).replace("\\", fs);
		//pathFileToWriteTestLink=System.getProperty("user.dir")+"/"+pathFileToWriteTestLink.replace("/", fs).replace("\\", fs);
		reportPath=System.getProperty("user.dir")+"/"+reportPath.replace("/", fs).replace("\\", fs);
	}

	public TestLinkAPI connectTestLink(String url, String devkey){
		System.out.println("open testlink");
		URL testlinkURL = null;
		TestLinkAPI testLinkApi = null;
		try {
			testlinkURL = new URL(url);
		} catch ( MalformedURLException mue ) {
			mue.printStackTrace( System.err );
		}

		try {
			testLinkApi=new TestLinkAPI(testlinkURL,devkey);
			System.out.println("connected");
		} catch( TestLinkAPIException te) {
			te.printStackTrace( System.err );
		}
		return testLinkApi;
	}

	/**
	 * Get test projects from test link
	 * @author phuong_dt
	 * @param api
	 * @return
	 */
	ArrayList<TestProject> getTestProjects(TestLinkAPI api){
		ArrayList<TestProject> list = new ArrayList<TestProject>();
		TestProject[]arrTestProjects = api.getProjects();
		for(int i = 0; i<arrTestProjects.length; i++){
			list.add(i,arrTestProjects[i]);
		}
		return list;
	}

	/**
	 * verify project is exist or not
	 * @param api
	 * @param projectName
	 * @return
	 */
	Boolean isProjectPresent(TestLinkAPI api, String projectName){
		Boolean isPresent = false;
		ArrayList<TestProject> projectList = getTestProjects(api);
		for(int i=0; i<projectList.size(); i++){
			if(projectList.get(i).getName().toLowerCase().equals(projectName.toLowerCase())){
				isPresent=true;
				break;
			}
		}
		return isPresent;
	}

	/**
	 * get projectId
	 * @param api
	 * @param projectName
	 * @return
	 */
	Integer getProjectIdByName(TestLinkAPI api, String projectName){
		Integer projectId = null;
		ArrayList<TestProject> projectList = getTestProjects(api);
		for(int i=0; i<projectList.size(); i++){
			if(projectList.get(i).getName().toLowerCase().equals(projectName.toLowerCase())){
				projectId=projectList.get(i).getId();
				break;
			}
		}
		return projectId;
	}
	/**
	 * Get test plan
	 * @author phuong_dt
	 * @param api
	 * @param projectId
	 */
	ArrayList<TestPlan> getTestPlan(TestLinkAPI api, Integer projectId){
		ArrayList<TestPlan> list = new ArrayList<TestPlan>();
		TestPlan[]arrTestPlans = api.getProjectTestPlans(projectId);
		for(int i = 0; i<arrTestPlans.length; i++){
			list.add(i,arrTestPlans[i]);
		}
		return list;
	}

	/**
	 * verify test plan exist or not
	 * @param api
	 * @param projectName
	 * @param planName
	 * @return
	 */
	Boolean isTestPlanPresent(TestLinkAPI api, String projectName, String planName){
		Boolean isPresent=false;
		Integer projectId = getProjectIdByName(api,projectName);
		ArrayList<TestPlan> list = getTestPlan(api,projectId);
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getName().toLowerCase().equals(planName.toLowerCase())){
				isPresent=true;
				break;
			}
		}
		return isPresent;
	}

	/**
	 * Get test suite
	 * @author phuong_dt
	 * @param projectName
	 * @param api
	 */
	ArrayList<TestSuite> getFirstLevelTestSuite(TestLinkAPI api, String projectName){
		ArrayList<TestSuite> list = new ArrayList<TestSuite>();
		TestSuite[]arrTestSuites;
		try {
			Integer projectId = api.getTestProjectByName(projectName).getId();
			arrTestSuites = api.getFirstLevelTestSuitesForTestProject(projectId);
			for(int i = 0; i<arrTestSuites.length; i++){
				list.add(i,arrTestSuites[i]);
			}
		} catch(TestLinkAPIException te) {
		}
		return list;
	}

	/**
	 * Get test suite for one test plan
	 * @author phuong_dt
	 * @param api
	 * @param planName
	 * @param projectName
	 * @return
	 */
	ArrayList<TestSuite> getTestSuiteForTestPlan(TestLinkAPI api,  String planName, String projectName){
		ArrayList<TestSuite> list = new ArrayList<TestSuite>();
		TestSuite[]arrTestSuites;
		try {
			Integer testPlanId = getTestPlanId(api, planName, projectName);
			arrTestSuites = api.getTestSuitesForTestPlan(testPlanId);
			for(int i = 0; i<arrTestSuites.length; i++){
				list.add(i,arrTestSuites[i]);
			}
		} catch(TestLinkAPIException te) {
		}
		return list;
	}

	/**
	 * get test suite by name
	 * @param api
	 * @param suiteName
	 * @param planName
	 * @param projectName
	 * @return
	 */
	Integer getTestSuiteByname(TestLinkAPI api, String suiteName, String planName, String projectName){
		Integer suiteId=null;
		ArrayList<TestSuite> testSuite;
		try {
			testSuite = getTestSuiteForTestPlan(api, planName, projectName);
			System.out.println(testSuite.size());
			for(int i = 0; i<testSuite.size(); i++){
				System.out.println(testSuite.get(i).getName());
				if(testSuite.get(i).getName().trim()==suiteName){
					suiteId=testSuite.get(i).getId();
					break;
				}
			}
		} catch(TestLinkAPIException te) {
		}
		return suiteId;
	}

	/**
	 * Get Testsuite by id
	 * @author phuong_dt
	 * @param api
	 * @param suiteId
	 */
	TestSuite getTestSuiteById(TestLinkAPI api, Integer suiteId){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(suiteId);
		TestSuite[] suiteName = api.getTestSuiteByID(ids);
		return suiteName[0];
	}


	/**
	 * Get Test case
	 * @author phuong_dt
	 * @param api
	 * @param testSuiteId
	 */
	ArrayList<TestCase> getTestCase(TestLinkAPI api, Integer testSuiteId){
		ArrayList<TestCase> list = new ArrayList<TestCase>();
		TestCase[]arrTestCases;
		try {
			arrTestCases = api.getTestCasesForTestSuite(testSuiteId, true, TestCaseDetails.FULL);
			for(int i = 0; i<arrTestCases.length; i++){
				list.add(i,arrTestCases[i]);
			}
		} catch(TestLinkAPIException te) {
		}
		return list;
	}

	/**
	 * get sub test suite from test suites
	 * @param api
	 * @param testSuiteId
	 * @return
	 */
	ArrayList<TestSuite> getTestSuite(TestLinkAPI api, Integer testSuiteId){
		ArrayList<TestSuite> list = new ArrayList<TestSuite>();
		TestSuite[]arrTestSuites;
		try {
			arrTestSuites = api.getTestSuitesForTestSuite(testSuiteId);
			for(int i = 0; i<arrTestSuites.length; i++){
				list.add(i,arrTestSuites[i]);
			}
		} catch(TestLinkAPIException te) {
		}
		return list;
	}

	/**
	 * Write feature file by suiteid
	 * @author phuong_dt
	 * @param pathFile
	 * @param api
	 * @param suiteId
	 * @throws InterruptedException 
	 */
	public void generateFeatureFileBySuiteId(String pathFile,TestLinkAPI api, Integer suiteId) throws InterruptedException{
		String suiteName=getTestSuiteById(api,suiteId).getName();
		String backGround=getTestSuiteById(api,suiteId).getDetails();
		String fs = File.separator;
		try {
			if(suiteName!=null&&suiteName!=""){
				if(getTestSuite(api,suiteId).size()==0){
					if(getTestCase(api,suiteId).size()>0)
					{
						System.out.println("generate feature file for feature "+suiteName+" with id ="+suiteId);
						StringBuilder stringBuilder = new StringBuilder();
						if(tagName!=""&&tagName!=null)
							stringBuilder.append("@"+tagName);
						stringBuilder.append("\n");
						stringBuilder.append("Feature: "+suiteName);
						String upperFileName=CucumberUtil.toUpper(suiteName.replace("-", " "));
						String upperPackageName=upperFileName.replaceAll("\\s","").substring(upperFileName.indexOf(":")+1).toLowerCase();
						String fileName = upperFileName.replaceAll("\\s","").substring(upperFileName.indexOf(":")+1);
						File f = new File((pathFile+"features/"
								+upperPackageName
								+"/"
								+fileName+".feature")
								.replace("/", fs).replace("\\", fs));
						stringBuilder.append("\n"+CucumberUtil.processSpecialString(CucumberUtil.addCucumberString(CucumberUtil.processSpecialString(backGround))));
						ArrayList<TestCase> testCase = getTestCase(api,suiteId); 
						for(int k = 0; k<testCase.size(); k++){
							if(testCase.get(k).getExecutionType().getValue()==2){
								Integer caseId = testCase.get(k).getId();
								String caseName = testCase.get(k).getName().trim();
								String caseSummary = CucumberUtil.processSpecialString(CucumberUtil.addCucumberString(CucumberUtil.processSpecialString(testCase.get(k).getSummary()).trim()));
								//String caseSummary = testCase.get(k).getSummary();
								System.out.println("caseName "+caseName);
								stringBuilder.append("\n#Case ID:"+ caseId);
								stringBuilder.append("\n#Case Name:"+ caseName);
								if(!caseName.contains("Scenario")){
									if(caseSummary.toLowerCase().contains("examples"))
										stringBuilder.append("\nScenario Outline: "+caseName);
									else
										stringBuilder.append("\nScenario: "+caseName);
								}
								stringBuilder .append("\n\t"+caseSummary);
							}
						}

						if(f.exists()){
							File f1 = new File((pathFile+"features/"
									+upperPackageName
									+"/"
									+fileName+CucumberUtil.getCurrentDate("ddMMyyyyHHmmss")+".feature")
									.replace("/", fs).replace("\\", fs)); 
							File backUpFile = new File(pathFile+"bkfeatures/"+upperPackageName);
							f.renameTo(f1);
							CucumberUtil.copyFileToDirectory(f1,backUpFile);
							f1.delete();
						}
						FileUtils.writeStringToFile(f, stringBuilder.toString());
					}
				} 
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Write feature file
	 * @author phuong_dt
	 * @param pathFile
	 * @param api
	 * @param projectName
	 */
	public void generateAllFeatureFile(String pathFile,TestLinkAPI api, String projectName, String planName){
		ArrayList<TestSuite> testSuite;
		if(planName!="" || planName!=null)
			testSuite = getTestSuiteForTestPlan(api, planName, projectName);
		else
			testSuite = getFirstLevelTestSuite(api,projectName); 
		for(int j = 0; j<testSuite.size(); j++){
			Integer suiteId=testSuite.get(j).getId();
			try {
				System.out.println("Suite name is "+testSuite.get(j).getName()+" and id = "+suiteId);
				generateFeatureFileBySuiteId(pathFile,api,suiteId);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * get test plan by name
	 * @author phuong_dt
	 * @param api
	 * @param planName
	 * @param projectName
	 */
	public Integer getTestPlanId(TestLinkAPI api, String planName, String projectName){
		return api.getTestPlanByName(planName, projectName).getId();
	}

	/**
	 * get build id
	 * @author phuong_dt
	 * @param api
	 * @param planName
	 * @param projectName
	 * @return
	 */
	public Integer getLatestBuildId(TestLinkAPI api, String planName, String projectName){
		Integer id = api.getLatestBuildForTestPlan(getTestPlanId(api, planName, projectName)).getId();
		System.out.println(String.valueOf(id));
		return id;
	}

	/**
	 * get build id
	 * @author phuong_dt
	 * @param api
	 * @param buildName
	 * @param planName
	 * @param projectName
	 * @return
	 */
	public Integer getBuildIdByName(TestLinkAPI api, String buildName, String planName, String projectName){
		Build[] arrayBuild=api.getBuildsForTestPlan(getTestPlanId(api, planName, projectName));
		Integer id = null;
		for(int i=0; i<arrayBuild.length; i++){
			if(arrayBuild[i].getName().equals(buildName)){
				id=arrayBuild[i].getId();
				break;
			}
		}
		System.out.println(String.valueOf(id));
		return id;
	}

	/**
	 * get platform of plan
	 * @author phuong_dt
	 * @param api
	 * @param planName
	 * @param projectName
	 * @return
	 */
	ArrayList<Platform> getPlatformOfPlan(TestLinkAPI api, String planName, String projectName){
		Integer planId=getTestPlanId(api, planName, projectName);
		ArrayList<Platform> list = new ArrayList<Platform>();
		Platform[]arrTestCases;
		try {
			arrTestCases = api.getTestPlanPlatforms(planId);
			for(int i = 0; i<arrTestCases.length; i++){
				list.add(i,arrTestCases[i]);
			}
		} catch(TestLinkAPIException te) {
		}
		return list;
	}
	/**
	 * Get test case id from testcase name
	 * @author phuong_dt
	 * @param api
	 * @param testCaseName
	 * @param testProjectName
	 * @return
	 */
	public Integer getTestCaseIdFromName(TestLinkAPI api,String testCaseName, String testProjectName){
		Integer tcid=0;
		tcid=api.getTestCaseIDByName(testCaseName, null, testProjectName, null);
		System.out.println("test case id is "+String.valueOf(tcid));
		return tcid;
	}
	/**
	 * report test execution to TestLink API
	 * @author phuong_dt
	 * @param api
	 * @param projectName
	 * @param planName
	 * @param tcid
	 * @param status
	 */
	public void reportTCResultToTestLink(TestLinkAPI api, String projectName,String planName, String buildName, String testCaseName, String status)
	{
		Integer tpid = getTestPlanId(api, planName, projectName);

		try 
		{
			XmlRpcClient rpcClient;
			XmlRpcClientConfigImpl config;

			config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(url));
			rpcClient = new XmlRpcClient();
			rpcClient.setConfig(config);		

			ArrayList<Object> params = new ArrayList<Object>();
			Hashtable<String, Object> executionData = new Hashtable<String, Object>();				
			executionData.put("devKey", devKey);
			executionData.put("testcaseid", getTestCaseIdFromName(api,testCaseName,projectName));
			executionData.put("testplanid", tpid);
			if(buildName!=""&&buildName!=null)
				executionData.put("buildid", getBuildIdByName(api, buildName, planName, projectName));
			else
				executionData.put("buildid", getLatestBuildId(api,planName,projectName));
			executionData.put("status", status);
			executionData.put("platformname", platformName);
			params.add(executionData);
			Object[] result = (Object[]) rpcClient.execute("tl.reportTCResult", params);
			System.out.println("Result was: "+result.length);				
			for (int i=0; i< result.length; i++)
			{
				Map<?, ?> item = (Map<?, ?>)result[i];
				System.out.println("Keys: " + item.keySet().toString() + " values: " + item.values().toString());
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (XmlRpcException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create new project on testlink
	 * @param api
	 * @param testProjectName
	 * @param testProjectPrefix
	 * @param notes
	 * @param enableRequirements
	 * @param enableTestPriority
	 * @param enableAutomation
	 * @param enableInventory
	 * @param isActive
	 * @param isPublic
	 */
	public void createProjectOnTestLink(TestLinkAPI api,
			String testProjectName, 
			String testProjectPrefix, 
			String notes, 
			Boolean enableRequirements, 
			Boolean enableTestPriority, 
			Boolean enableAutomation, 
			Boolean enableInventory, 
			Boolean isActive, 
			Boolean isPublic){
		if(isProjectPresent(api,testProjectName)){
			System.out.println("Project "+testProjectName+" exists");
		}
		else{
			System.out.println("Create one project "+testProjectName+" on testlink");
			api.createTestProject(testProjectName, testProjectPrefix, notes, enableRequirements, enableTestPriority, enableAutomation, enableInventory, isActive, isPublic);
		}
	}

	/**
	 * create new plan on testlink
	 * @param api
	 * @param planName
	 * @param projectName
	 * @param notes
	 * @param isActive
	 * @param isPublic
	 */
	public void createPlanOnTestLinK(TestLinkAPI api,
			String planName, 
			String projectName, 
			String notes, 
			Boolean isActive, 
			Boolean isPublic){
		if(isTestPlanPresent(api, projectName, planName)){
			System.out.println("Plan "+planName+" exists in project "+projectName);
		}
		else{
			System.out.println("Create one plan "+planName+" on project "+projectName+" on testlink");
			api.createTestPlan(planName, projectName, notes, isActive, isPublic);
		}
	}

	/**
	 * create one build for test plan
	 * @param api
	 * @param projectName
	 * @param planName
	 * @param buildName
	 * @param buildNotes
	 */
	public void createBuildOnTestLink(TestLinkAPI api,
			String projectName,
			String planName, 
			String buildName, 
			String buildNotes){
		System.out.println("Create one build "+buildName+" on project "+projectName+" on testlink");
		Integer testPlanId = getTestPlanId(api, planName, projectName);
		api.createBuild(testPlanId, buildName, buildNotes);
	}

	/**
	 * create one testsuite 
	 * @param api
	 * @param projectName
	 * @param suiteName
	 * @param details
	 * @param parentId
	 * @param order
	 * @param checkDuplicatedName
	 * @param actionOnDuplicatedName
	 */
	public Integer createSuiteOnTestLink(TestLinkAPI api,
			String projectName, 
			String suiteName,
			String details, 
			Integer parentId, 
			Integer order, 
			Boolean checkDuplicatedName, 
			ActionOnDuplicate actionOnDuplicatedName){
		System.out.println("Create test suite "+suiteName+" on project "+projectName+" on testlink");
		Integer testProjectId = api.getTestProjectByName(projectName).getId();
		TestSuite suite=api.createTestSuite(testProjectId, suiteName, details, parentId, order, checkDuplicatedName, actionOnDuplicatedName);
		return suite.getId();
	}

	/**
	 * create new test case
	 * @param api
	 * @param testCaseName
	 * @param planName
	 * @param testSuiteId
	 * @param projectName
	 * @param summary
	 * @param importance
	 * @param execution
	 * @param checkDuplicatedName
	 * @param actionOnDuplicatedName
	 */
	public void createTestCase(TestLinkAPI api,String testCaseName,
			String planName,
			Integer testSuiteId,
			String projectName,
			String summary,
			TestImportance importance,
			ExecutionType execution,
			Boolean checkDuplicatedName,
			ActionOnDuplicate actionOnDuplicatedName){
		System.out.println("Create test case "+testCaseName+" on project "+projectName+"/"+planName+"/"+testSuiteId+" on testlink");
		Integer testProjectId = api.getTestProjectByName(projectName).getId();
		api.createTestCase(testCaseName, testSuiteId, testProjectId, System.getProperty("user.name"), summary, null, null, importance, execution, null, null, true, actionOnDuplicatedName);
	}
}
