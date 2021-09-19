package defeatedcrow.hac.main.config.recipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.config.ClimateConfig;
import defeatedcrow.hac.config.recipe.EnumRecipeReg;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.core.util.JsonUtilDC;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.recipes.FoodStillRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class StillRecipeJson {

	public static StillRecipeJson INSTANCE = new StillRecipeJson();

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
						Object[] list = null;
						ItemStack output = ItemStack.EMPTY;
						FluidStack outputF = null;
						FluidStack inputF = null;
						DCHeatTier hot = DCHeatTier.BOIL;
						DCHeatTier cold = DCHeatTier.COOL;

						if (map.containsKey("recipe_type")) {
							Object o1 = map.get("recipe_type");
							if (o1 instanceof String) {
								String r2 = (String) o1;
								reg = EnumRecipeReg.getFromName(r2);
							}
						}

						if (map.containsKey("climate_temperature_heating")) {
							Object o1 = map.get("climate_temperature_heating");
							if (o1 instanceof String) {
								String heat2 = (String) o1;
								hot = DCHeatTier.getFromName(heat2);
							}
						}

						if (map.containsKey("climate_temperature_cooling")) {
							Object o1 = map.get("climate_temperature_cooling");
							if (o1 instanceof String) {
								String heat2 = (String) o1;
								cold = DCHeatTier.getFromName(heat2);
							}
						}

						if (map.containsKey("input")) {
							Object o2 = map.get("input");
							if (o2 instanceof List) {
								try {
									List<String> inputs = (List) o2;
									list = JsonUtilDC.getInputObjects(inputs);
								} catch (Error err) {
									DCLogger.debugLog("StillRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								} catch (Exception exp) {
									DCLogger.debugLog("StillRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								}
							}
						}

						if (map.containsKey("output")) {
							Object o2 = map.get("output");
							if (o2 instanceof Map) {
								try {
									Map<String, Object> items = (Map) o2;
									output = JsonUtilDC.getOutput(items);
								} catch (Error err) {
									DCLogger.debugLog("StillRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								} catch (Exception exp) {
									DCLogger.debugLog("StillRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								}
							}
						}

						if (map.containsKey("input_fluid")) {
							Object of = map.get("input_fluid");
							if (of instanceof Map) {
								try {
									Map<String, Object> items = (Map) of;
									inputF = JsonUtilDC.getFluid(items);
								} catch (Error err) {
									DCLogger.debugLog("StillRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								} catch (Exception exp) {
									DCLogger.debugLog("StillRecipeJson : Error entry found. This entry is ignored. " + recipeID);
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
									DCLogger.debugLog("StillRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								} catch (Exception exp) {
									DCLogger.debugLog("StillRecipeJson : Error entry found. This entry is ignored. " + recipeID);
									continue;
								}
							}
						}

						boolean b = !DCUtil.isEmpty(output) || outputF != null;
						boolean b2 = list != null || inputF != null;

						if (reg == EnumRecipeReg.ADD && b) {
							if (addRecipe(hot, cold, output, outputF, inputF, list))
								DCLogger.debugLog("StillRecipeJson : Successfully added a still recipe. " + recipeID);
						} else if (reg == EnumRecipeReg.REPLACE && b) {
							if (changeRecipe(hot, cold, output, outputF, inputF, list))
								DCLogger.debugLog("StillRecipeJson : Successfully replaced a still recipe. " + recipeID);
						} else if (reg == EnumRecipeReg.REMOVE && b2) {
							if (removeRecipe(hot, cold, inputF, list))
								DCLogger.debugLog("StillRecipeJson : Successfully removed a still recipe. " + recipeID);
						}
					}
				}
			}
		}
	}

	private static boolean addRecipe(DCHeatTier hot, DCHeatTier cold, ItemStack output, FluidStack outputF,
			FluidStack inputF, Object[] input) {
		FoodStillRecipe recipe = new FoodStillRecipe(output, outputF, inputF, input);
		recipe.setTemp(hot, cold);
		MainAPIManager.brewingRegister.addStillRecipe(recipe);
		return true;
	}

	private static boolean changeRecipe(DCHeatTier hot, DCHeatTier cold, ItemStack output, FluidStack outputF,
			FluidStack inputF, Object[] input) {
		if (removeRecipe(hot, cold, inputF, input)) {
			return addRecipe(hot, cold, output, outputF, inputF, input);
		}
		return false;
	}

	private static boolean removeRecipe(DCHeatTier hot, DCHeatTier cold, FluidStack inputF,
			Object[] input) {
		List<ItemStack> check = JsonUtilDC.getInputLists(input);
		if (check.isEmpty())
			return false;
		return MainAPIManager.brewingRegister.removeStillRecipe(hot, cold, check, inputF);
	}

	public static void pre() {
		File file = new File(ClimateConfig.configDir + "/recipe/still_recipe.json");

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

			File file = new File(ClimateConfig.configDir + "/recipe/still_recipe.json");
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
				DCLogger.debugInfoLog("StillRecipeJson : Failed to create json file: still_recipe.json");
				e.printStackTrace();
			}

		} else {
			DCLogger.debugInfoLog("StillRecipeJson : Recipe custom data json is already exists.");
			return;
		}

	}

	public static void registerDummy() {

		String id1 = "SampleRecipeID1 (Any string.)";
		Map<String, Object> recipe1 = new LinkedHashMap<>();
		recipe1.put("recipe_type", "ADD");

		recipe1.put("climate_temperature_heating", "BOIL (If this is removed, it will be forced to BOIL.)");
		recipe1.put("climate_temperature_cooling", "COOL (If this is removed, it will be forced to COOL.)");

		Map<String, Object> output1 = new HashMap<>();
		output1.put("item", "samplemod:sample_output:metadata (removable)");
		recipe1.put("output", output1);

		Map<String, Object> outputF = new HashMap<>();
		outputF.put("fluid", "sample_fluid_output (removable)");
		outputF.put("amount", 100);
		recipe1.put("output_fluid", outputF);

		Map<String, Object> inputF = new HashMap<>();
		inputF.put("fluid", "sample_fluid_input (removable)");
		inputF.put("amount", 1000);
		recipe1.put("input_fluid", inputF);

		List<String> input1 = Lists.newArrayList();
		input1.add("samplemod:sample_output:0");
		input1.add("ore_dict name is acceptable as input.");
		input1.add("Up to 3 inputs can be added.");
		recipe1.put("input", input1);

		recipe1.put("comment", "At a minimum, either an item or a fluid is required for input.");
		INSTANCE.dummyMap.put(id1, recipe1);

		String id2 = "SampleRecipeID2";
		Map<String, Object> recipe2 = new LinkedHashMap<>();
		recipe2.put("recipe_type", "REMOVE");

		recipe2.put("climate_temperature_heating", "BOIL (If this is removed, it will be forced to BOIL.)");
		recipe2.put("climate_temperature_cooling", "COOL (If this is removed, it will be forced to COOL.)");

		Map<String, Object> input2 = new HashMap<>();
		input2.put("item", "samplemod:remove_input:0");
		recipe2.put("input", input2);

		Map<String, Object> inputF2 = new HashMap<>();
		inputF2.put("fluid", "sample_fluid_input (removable)");
		inputF2.put("amount", 500);
		recipe2.put("input_fluid", inputF2);

		INSTANCE.dummyMap.put(id2, recipe2);

	}

}
