package defeatedcrow.hac.core.recipe.smelting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.JsonFileFilter;

/**
 * @author defeatedcrow
 */
public class ClimateSmeltingConfig {

	private ClimateSmeltingConfig() {};

	public static ClimateSmeltingConfig INSTANCE = new ClimateSmeltingConfig();

	private static Map<String, ClimateSmeltingJson> list = new LinkedHashMap<>();

	public static void addRecipe(String name, ClimateSmeltingJson recipe) {
		INSTANCE.list.put(name, recipe);
	}

	public static int recipeVersion = 1;
	public static boolean clean = false;

	public static void initFile() {

		if (ClimateCore.configDir == null)
			return;

		// configフォルダに生成する
		// 生成は最初のみ
		File dir = new File(ClimateCore.configDir, "/recipes/climate_smelting/");
		if (!dir.getParentFile().exists()) {
			dir.getParentFile().mkdirs();
		}

		// バージョンチェック
		boolean update = false;
		File ver = new File(dir, "_recipe_version.json");
		if (ver.exists()) {
			try {
				if (ver.canRead()) {
					FileInputStream fis = new FileInputStream(ver.getPath());
					InputStreamReader isr = new InputStreamReader(fis);
					JsonReader jsr = new JsonReader(isr);
					Gson gson = new Gson();
					Map get = gson.fromJson(jsr, Map.class);

					isr.close();
					fis.close();
					jsr.close();

					if (get != null && !get.isEmpty()) {
						if (get.containsKey("version") && get.get("version") instanceof Number) {
							int v = ((Number) get.get("version")).intValue();

							if (v < recipeVersion) {
								update = true;
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			update = true;
		}

		if (update) {
			clean = true;
			Map<String, Integer> vermap = Maps.newHashMap();
			vermap.put("version", recipeVersion);
			// レシピバージョンの更新
			try {
				ver.createNewFile();
				if (ver.canWrite()) {
					// Streamは開けたら閉めるを徹底しよう。ご安全に!
					FileOutputStream fos = new FileOutputStream(ver.getPath());
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					JsonWriter jsw = new JsonWriter(osw);

					// ここでインデントのスペースの数を調整でき、ファイルの内容が適宜改行されるようになる。
					jsw.setIndent("  ");
					Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
					gson.toJson(vermap, Map.class, jsw);

					osw.close();
					fos.close();
					jsw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (Entry<String, ClimateSmeltingJson> table : INSTANCE.list.entrySet()) {
			File f = new File(dir, table.getKey() + ".json");

			// すでにファイルが有る場合は何もしない。
			if (!f.exists() || clean) {

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
						gson.toJson(table.getValue(), ClimateSmeltingJson.class, jsw);

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

	public static void loadFiles() {

		if (ClimateCore.configDir == null)
			return;

		File dir = new File(ClimateCore.configDir, "/recipes/climate_smelting/");
		File[] files = dir.listFiles(new JsonFileFilter());

		if (dir != null && files != null) {
			try {
				for (File file : files) {
					if (file.canRead()) {
						if (file.getCanonicalPath().contains("recipe_version")) {
							continue;
						}
						FileInputStream fis = new FileInputStream(file.getPath());
						InputStreamReader isr = new InputStreamReader(fis);
						JsonReader jsr = new JsonReader(isr);
						Gson gson = new Gson();
						ClimateSmeltingJson get = gson.fromJson(jsr, ClimateSmeltingJson.class);

						isr.close();
						fis.close();
						jsr.close();

						if (get != null) {
							ClimateSmelting recipe = get.toRecipe();
							if (!DCRecipes.INSTANCE.SMELTING.contains(recipe))
								if (DCRecipes.INSTANCE.SMELTING.add(recipe)) {
									DCLogger.traceLog("Register ClimateSmelting: " + recipe.getOutput().getHoverName().getString());
								}
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
