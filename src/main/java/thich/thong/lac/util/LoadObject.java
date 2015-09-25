package thich.thong.lac.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class LoadObject {

	private static Properties OBJECT = null;
	private static Properties CONFIG = null;
	static String fseparator = File.separator;

	public static Properties loading_object_repository(String objectRepos) throws IOException {
		FileInputStream fs = null;
		OBJECT = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		URL url = classLoader.getResource(objectRepos);

		try {
			File folder = new File(url.toURI());
			if (folder.isDirectory()) {
				File[] files = folder.listFiles();
				for (File file : files) {
					fs = new FileInputStream(file);
					OBJECT.load(fs);
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return OBJECT;
	}

	public static Properties loading_config_sys(String fileName) throws IOException {
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(System.getProperty("user.dir") + fseparator + fileName);
			CONFIG = new Properties();
			CONFIG.load(fs);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return CONFIG;
	}

}
