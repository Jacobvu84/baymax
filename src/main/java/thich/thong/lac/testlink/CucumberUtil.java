package thich.thong.lac.testlink;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CucumberUtil {

	public static String toUpper(String s) {
		final StringBuilder result = new StringBuilder(s.length());
		String[] words = s.split("\\s");
		for (int i = 0, l = words.length; i < l; ++i) {
			if (i > 0)
				result.append(" ");
			result.append(Character.toUpperCase(words[i].charAt(0))).append(
					words[i].substring(1));
		}
		return result.toString();
	}

	public static String addCucumberString(String value){
		String retString="";
		String[] tempString=value.replaceAll("[\\\r\\\n]+","\n").split("\n");
		tempString[0]=tempString[0].trim();
		if(!tempString[0].startsWith("Scenario")){
			if(!tempString[0].startsWith("#")){
				if(!tempString[0].isEmpty())
				{
					tempString[0]="When "+tempString[0].trim();	
					retString=retString+tempString[0]+"\n";
				}
			}
			else{
				retString=retString+tempString[0].trim()+"\n";	
			}
		}
		for(int i = 1; i<tempString.length; i++){
			String temp = tempString[i].toLowerCase().trim();
			if(temp.startsWith("#")){
				tempString[i]=tempString[i].trim();
			}
			else if(temp.startsWith("@")){
				tempString[i]=tempString[i].trim();
			}
			else if(temp.contains("scenario")||(temp.contains("background"))||(temp.contains("feature"))){
				tempString[i]=tempString[i].trim();
			}
			else if(temp.startsWith("follow")){
				tempString[i]="\tWhen "+tempString[i].trim();	
			}
			else if(temp.startsWith("open")){
				tempString[i]="\tWhen "+tempString[i].trim();	
			}
			else if((!temp.startsWith("when"))
					&&(!temp.startsWith("and"))
					&&(!temp.contains("given"))
					&&(!temp.contains("scenario"))
					&&(!temp.startsWith("then"))
					&&(!temp.contains("background"))
					&&(!temp.contains("feature"))
					&&(!temp.startsWith("example"))
					&&(!temp.startsWith("|"))){
				if(temp.contains("should")){
					tempString[i]="\tThen "+tempString[i].trim();
				}
				else if (!temp.isEmpty()){
					tempString[i]="\tWhen "+tempString[i].trim();	
				}
			}
			else{
				tempString[i]="\t"+tempString[i].trim();	
			}
			retString=retString+tempString[i]+"\n".replace("When When", "When");
		}
		return retString;
	}

	public static String processSpecialString(String value){
		String retString="";
		retString=value.replaceAll("<p>", "")
				.replaceAll("</p>", "")
				.replaceAll("<ol>", "")
				.replaceAll("</ol>", "")
				.replaceAll("<li>", "")
				.replaceAll("</li>", "")
				.replaceAll("<br>", "")
				.replaceAll("<br />", "")
				.replaceAll("</br>", "")
				.replaceAll(";", "")
				.replaceAll("&nbsp", "")
				.replaceAll("&quot", "\"")
				.replaceAll("<div>", "")
				.replaceAll("</div>", "")
				.replaceAll("<strong>", "")
				.replaceAll("</strong>", "")
				.replaceAll("<em>", "")
				.replaceAll("</em>", "")
				.replaceAll("<span class=\"Apple-tab-span\" style=\"white-space:pre\">", "")
				.replaceAll("<span aria-expanded=\"true\" class=\"nodeLabelBox repTarget \">", "")
				.replaceAll("<span class=\"nodeLabelBox repTarget \">", "")
				.replaceAll("<span class=\"nodeAttr \"><span class=\"nodeValue \">", "")
				//.replaceAll("/span", "")
				.replaceAll("&#39", "'")
				.replaceAll("&gt", ">")
				.replaceAll("&lt", "<")
				.replaceAll("<>", " ")
				.replaceAll("<file>", "")
				.replaceAll("</file>", "")
				.replace(" /", "/")
				.replace("  /", "/")
				.replace("&amp", "&")
				.replace("When When", "When")
				.replace("jpgpnggifjpegbmppicttiftiff", "jpg;png;gif;jpeg;bmp;pict;tif;tiff")
				.replaceAll("[\\\r\\\n]+","\n")
				.replaceAll("[\n\r]+", "\n");
		return retString;
	}

	public static  String getCharacterDataFromElement(Element e) {
		NodeList list = e.getChildNodes();
		String data;
		for(int index = 0; index < list.getLength(); index++){
			if(list.item(index) instanceof CharacterData){
				CharacterData child = (CharacterData) list.item(index);
				data = child.getData();
				if(data != null && data.trim().length() > 0)
					return child.getData();
			}
		}
		return "";
	}	

	public static String getCurrentDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return (dateFormat.format(date));
	}

	public static String getAbsoluteFilePathFromFile(String path) {
		String fs = File.separator;
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + path;
		return absolutePath.replace("/", fs).replace("\\", fs);
	}

	public static void copyFileToDirectory(File source, File dest)
			throws IOException {
		FileUtils.copyFileToDirectory(source, dest);
	}
}
