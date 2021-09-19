package defeatedcrow.hac.main.config.recipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.config.ClimateConfig;
import defeatedcrow.hac.config.recipe.EnumRecipeReg;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.JsonUtilDC;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.recipes.FoodAgingRecipe;
import net.minecraftforge.fluids.FluidStack;

public class AgingRecipeJson {

	public static AgingRecipeJson INSTANCE = new AgingRecipeJson();

	private static final Map<String, Map<String, Object>> jsonMap = new LinkedHashMap<>();
	private static final Map<String, Map<String, Object>> dummyMap = new LinkedHashMap<>();

	private static void load() {
		if (!INSTANCE.jsonMap.isEmpty()) {
			for (Entry<String, Map<String, Object>> e : INSTANCE.jsonMap.entrySet()) {
				if (e.getKey() == null || e.getValue() == null || e.getValue().isEmpty()) {
					continue;
				}
				String recipeID = e.getKey();
				if (recipeID != null && !recipeID.contains("Sample")) {

					Map<String, Object> map = e.getValue();
					if (map != null && !map.isEmpty()) {

						EnumRecipeReg reg = EnumRecipeReg.ADD;
						FluidStack outputF = null;
						FluidStack inputF = null;
						int count = 30;

						if (map.containsKey("recipe_type")) {
							Object o1 = map.get("recipe_type");
							if (o1 instanceof String) {
								String r2 = (String) o1;
								reg = EnumRecipeReg.getFromName(r2);
							}
						}

						if (map.containsKey("input_fluid")) {
							Object of = map.get("input_fluid");
							if (of instanceof Map) {
								try {
									Map<String, Object> items = (Map) of;
									inputF = JsonUtilDC.getFluid(items);
								} catch (Error err) {
									DCLogger.debugLog("AgingRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								} catch (Exception exp) {
									DCLogger.debugLog("AgingRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								}
							}
						}

						if (map.containsKey("output_fluid")) {
							Object of = map.get("output_fluid");
							if (of instanceof Map) {
								try {
									Map<String, Object> items = (Map) of;
									outputF = JsonUtilDC.getFluid(items);
								} catch (Error err) {
									DCLogger.debugLog("AgingRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								} catch (Exception exp) {
									DCLogger.debugLog("AgingRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								}
							}
						}

						if (map.containsKey("number_of_days")) {
							String i = map.get("number_of_days").toString();
							count = JsonUtilDC.parseInt(i, 30);
						}

						boolean b = outputF != null;
						boolean b2 = inputF != null;

						if (reg == EnumRecipeReg.ADD && b) {
							if (addRecipe(outputF, inputF, count))
								DCLogger.debugLog("AgingRecipeJson : Successfully added a aging recipe. " + recipeID);
						} else if (reg == EnumRecipeReg.REPLACE && b) {
							if (changeRecipe(outputF, inputF, count))
								DCLogger.debugLog("AgingRecipeJson : Successfully replaced a aging recipe. " + recipeID);
						} else if (reg == EnumRecipeReg.REMOVE && b2) {
							if (removeRecipe(inputF))
								DCLogger.debugLog("AgingRecipeJson : Successfully removed a aging recipe. " + recipeID);
						}
					}
				}
			}
		}
	}

	private static boolean addRecipe(FluidStack outputF,
			FluidStack inputF, int day) {
		FoodAgingRecipe recipe = new FoodAgingRecipe(outputF, inputF, day);
		MainAPIManager.brewingRegister.addAgingRecipe(recipe);
		return true;
	}

	private static boolean changeRecipe(FluidStack outputF,
			FluidStack inputF, int day) {
		if (removeRecipe(inputF)) {
			return addRecipe(outputF, inputF, day);
		}
		return false;
	}

	private static boolean removeRecipe(FluidStack inputF) {
		return MainAPIManager.brewingRegister.removeAgingRecipe(inputF);
	}

	public static void pre() {
		File file = new File(ClimateConfig.configDir + "/recipe/aging_recipe.json");

		if (file != null) {
			try {
				if (!file.exists()) {
					return;
				}

				if (file.canRead()) {
					FileInputStream fis = new FileInputStream(file.getPath());
					InputStreamReader isr = new InputStreamReader(fis);
					JsonReader jsr = new JsonReader(isr);
					Gson gson = new Gson();
					Map get = gson.fromJson(jsr, Map.class);

					isr.close();
					fis.close();
					jsr.close();

					if (get != null && !get.isEmpty()) {
						INSTANCE.jsonMap.putAll(get);

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (!INSTANCE.jsonMap.isEmpty()) {
			load();
		}
	}

	// 生成は初回のみ
	public static void post() {

		if (INSTANCE.jsonMap.isEmpty()) {
			registerDummy();

			File file = new File(ClimateConfig.configDir + "/recipe/aging_recipe.json");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			try {
				if (!file.exists() && !file.createNewFile()) {
					return;
				}

				if (file.canWrite()) {
					FileOutputStream fos = new FileOutputStream(file.getPath());
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					JsonWriter jsw = new JsonWriter(osw);
					jsw.setIndent("    ");
					Gson gson = new Gson();
					gson.toJson(INSTANCE.dummyMap, Map.class, jsw);

					osw.close();
					fos.close();
					jsw.close();
				}

			} catch (IOException e) {
				DCLogger.debugInfoLog("AgingRecipeJson : Failed to create json file: aging_recipe.json");
				e.printStackTrace();
			}

		} else {
			DCLogger.debugInfoLog("AgingRecipeJson : Recipe custom data json is already exists.");
			return;
		}

	}

	public static void registerDummy() {

		String id1 = "SampleRecipeID1 (Any string.)";
		Map<String, Object> recipe1 = new LinkedHashMap<>();
		recipe1.put("recipe_type", "ADD");

		Map<String, Object> outputF = new HashMap<>();
		outputF.put("fluid", "sample_fluid_output");
		outputF.put("amount", 900);
		recipe1.put("output_fluid", outputF);

		Map<String, Object> inputF = new HashMap<>();
		inputF.put("fluid", "sample_fluid_input");
		inputF.put("amount", 1000);
		recipe1.put("input_fluid", inputF);

		recipe1.put("number_of_days", 30);

		INSTANCE.dummyMap.put(id1, recipe1);

		String id2 = "SampleRecipeID2";
		Map<String, Object> recipe2 = new LinkedHashMap<>();
		recipe2.put("recipe_type", "REMOVE");

		Map<String, Object> inputF2 = new HashMap<>();
		inputF2.put("fluid", "sample_fluid_input");
		inputF2.put("amount", 500);
		recipe2.put("input_fluid", inputF2);

		INSTANCE.dummyMap.put(id2, recipe2);

	}

}
