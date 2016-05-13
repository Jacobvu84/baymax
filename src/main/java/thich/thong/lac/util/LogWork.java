package thich.thong.lac.util;

import org.apache.log4j.Logger;

public class LogWork {
	// initialize the app logs
		public static Logger logs = Logger.getLogger("devpinoyLogger");

		public LogWork() {
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		}
		
		public static void log_debug(String msg){
			logs.debug(">> debug: | " + msg);
		}
}
