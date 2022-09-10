package defeatedcrow.hac.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DCLogger {

	public static final Logger LOGGER = LogManager.getLogger(ClimateCore.MOD_ID);

	public static void debugLog(String s) {
		if (ClimateCore.isDebug) {
			LOGGER.debug(s);
		}
	}

	public static void debugInfoLog(String s) {
		if (ClimateCore.isDebug) {
			LOGGER.info(s);
		}
	}

	public static void debugTrace(String s) {
		if (ClimateCore.isDebug) {
			LOGGER.trace(s);
		}
	}

	public static void traceLog(String s) {
		LOGGER.trace(s);
	}

	public static void infoLog(String id, String s) {
		LOGGER.info(id + ": " + s);
	}

	public static void infoLog(String s) {
		LOGGER.info(s);
	}

	public static void warnLog(String s) {
		LOGGER.warn(s);
	}

}
