package defeatedcrow.hac.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

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

	// デバッグモード
	public static boolean checkDebugModePass() {
		// コンフィグ階層のチェック
		if (ClimateCore.configDir == null)
			return false;

		String pass = "null";

		File dir = new File(ClimateCore.configDir, "debug_pass.json");
		if (!dir.getParentFile().exists()) {
			dir.getParentFile().mkdirs();
		}
		if (dir.exists()) {
			try {
				if (dir.canRead()) {
					FileInputStream fis = new FileInputStream(dir.getPath());
					InputStreamReader isr = new InputStreamReader(fis);
					JsonReader jsr = new JsonReader(isr);
					Gson gson = new Gson();
					Map get = gson.fromJson(jsr, Map.class);

					isr.close();
					fis.close();
					jsr.close();

					if (get != null && !get.isEmpty()) {
						if (get.containsKey("pass") && get.get("pass") instanceof String) {
							pass = (String) get.get("pass");
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		byte[] b = null;
		String get = "";
		MessageDigest md5;

		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(pass.getBytes());
			b = md5.digest();
		} catch (NoSuchAlgorithmException e) {
			DCLogger.LOGGER.warn("Failed to check password...", e);
		}

		get = getStringFromBytes(b);
		DCLogger.infoLog("Get String : " + get);

		if (!get.isEmpty()) {
			boolean match = get.matches("7805f2fa0adc68cd9a8f7cb2135e0b57");
			DCLogger.infoLog("DebugMode : " + match);
			return match;
		}

		return true;
	}

	private static String getStringFromBytes(byte[] b) {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < b.length; i++) {

			if ((b[i] & 0xff) < 0x10) {
				builder.append("0");
			}
			builder.append(Integer.toHexString(0xff & b[i]));
		}

		return builder.toString();
	}

}
