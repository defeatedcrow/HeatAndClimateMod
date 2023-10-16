package defeatedcrow.hac.core.recipe.device;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.core.ClimateCore;

/**
 * @author defeatedcrow
 */
public class DeviceRecipeConfig {

	private DeviceRecipeConfig() {};

	public static DeviceRecipeConfig INSTANCE = new DeviceRecipeConfig();

	private static Map<String, DeviceRecipe> list = new LinkedHashMap<>();

	public static void addRecipe(String name, DeviceRecipe recipe) {
		INSTANCE.list.put(name, recipe);
	}

	public static void initFile() {

		if (ClimateCore.dataDir == null)
			return;

		// configフォルダに生成する
		// 生成は最初のみ
		File dir = new File(ClimateCore.dataDir, "/recipes/device_recipe/");
		if (!dir.getParentFile().exists()) {
			dir.getParentFile().mkdirs();
		}

		for (Entry<String, DeviceRecipe> table : INSTANCE.list.entrySet()) {
			File f = new File(dir, table.getValue().getType().toString() + "_" + table.getKey() + ".json");

			// すでにファイルが有る場合は何もしない。
			if (!f.exists()) {

				// ファイルを生成する。
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}

				try {
					f.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// JSONファイルの生成。
				try {
					if (f.canWrite()) {
						// Streamは開けたら閉めるを徹底しよう。ご安全に!
						FileOutputStream fos = new FileOutputStream(f.getPath());
						OutputStreamWriter osw = new OutputStreamWriter(fos);
						JsonWriter jsw = new JsonWriter(osw);

						// ここでインデントのスペースの数を調整でき、ファイルの内容が適宜改行されるようになる。
						jsw.setIndent("  ");
						Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
						JsonObject json = table.getValue().toJson();
						gson.toJson(json, jsw);

						osw.close();
						fos.close();
						jsw.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
