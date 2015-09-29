package thich.thong.lac.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class FeatureUtil {
	public static String feature="";
	public static String background="";
	public static ArrayList<HashMap<String,String>> caseList=null;
	private static BufferedReader br;
	private static BufferedReader tempbr;

	/**
	 * Get file list in folder
	 * @param folder
	 * @return
	 */
	public static ArrayList<String> listFilesForFolder(String path) {
		String fs = File.separator;
		String tempFile="";
		String temp="";
		File folder = new File(path);
		ArrayList<String> fileName=new ArrayList<String>();
		if(folder.isFile()){
			fileName.add(path.replace("/", fs).replace("\\", fs));
		}
		else{
			for (File fileEntry : folder.listFiles()) {
				if (fileEntry.isFile()) {
					temp = fileEntry.getName();
					if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("feature")){
						tempFile = folder.getAbsolutePath()+ "/" + fileEntry.getName();
						System.out.println(tempFile);
						fileName.add(tempFile.replace("/", fs).replace("\\", fs));
					}
					tempFile="";
				}

			}
		}
		return fileName;
	}
	/**
	 * Read file
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void readFile(String filename) throws FileNotFoundException, IOException{
		String fs = File.separator;
		br = new BufferedReader(new FileReader(filename.replace("/", fs).replace("\\", fs)));
		tempbr = new BufferedReader(new FileReader(filename.replace("/", fs).replace("\\", fs)));
		caseList=new ArrayList<HashMap<String,String>>();
		feature="";
		background="";
		StringBuilder fture = new StringBuilder();
		StringBuilder bground= new StringBuilder();
		StringBuilder tcase = new StringBuilder();
		StringBuilder tcaseSummary = new StringBuilder();
		HashMap<String,String> testcase = new HashMap<String,String>();
		String line = br.readLine();
		String tempLine = tempbr.readLine();
		tempLine = tempbr.readLine();
		while (line != null) {
			if(line.toLowerCase().contains("feature")){
				//Get feature
				fture.append(line);
				fture.append(System.lineSeparator());
			}
			if(line.toLowerCase().contains("background")){
				//Get background
				bground.append(line);
				bground.append(System.lineSeparator());
				while(tempLine != null){
					if(tempLine.toLowerCase().contains("scenario")){
						break;
					}
					bground.append("<br>");
					bground.append(tempLine);
					bground.append("</br>");
					bground.append(System.lineSeparator());
					tempLine = tempbr.readLine();
					line = br.readLine();
				}
			}
			if(line.toLowerCase().contains("scenario")){
				//get test case
				tcase.append(line);
				tcase.append(System.lineSeparator());
				while(tempLine != null){
					if(tempLine.toLowerCase().contains("scenario")){
						break;
					}
					tcaseSummary.append("<br>");
					tcaseSummary.append(tempLine);
					tcaseSummary.append("</br>");
					tcaseSummary.append(System.lineSeparator());
					tempLine = tempbr.readLine();
					line = br.readLine();
				}
				testcase.put("name",tcase.toString().substring(tcase.toString().indexOf(":")+1).trim());
				testcase.put("summary",tcaseSummary.toString());
				caseList.add(testcase);
				tcase = new StringBuilder();
				tcaseSummary = new StringBuilder();
				testcase = new HashMap<String,String>();
			}
			tempLine = tempbr.readLine();
			line = br.readLine();
		}
		background=bground.toString();
		feature=fture.toString().substring(fture.toString().indexOf(":")+1).trim();
	}
}
