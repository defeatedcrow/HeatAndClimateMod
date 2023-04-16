package defeatedcrow.hac.core.recipe.datapack;

import java.util.List;
import java.util.stream.StreamSupport;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

public class DataUtilDC {

	public static Ingredient getIng(JsonObject json) {
		JsonElement element = GsonHelper.isArrayNode(json, "ingredient") ? GsonHelper.getAsJsonArray(json, "ingredient") : GsonHelper.getAsJsonObject(json, "ingredient");
		return Ingredient.fromJson(element);
	}

	public static ItemStack getStack(JsonObject json, String key) {
		ItemStack stack = new ItemStack(CoreInit.NULL_ITEM.get());
		if (GsonHelper.isValidNode(json, key) && json.get(key).isJsonObject())
			stack = net.minecraftforge.common.crafting.CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, key), true, true);
		else {
			String s1 = GsonHelper.getAsString(json, key);
			ResourceLocation res = new ResourceLocation(s1);
			if (ForgeRegistries.ITEMS.containsKey(res))
				stack = new ItemStack(ForgeRegistries.ITEMS.getValue(res));
		}
		return stack;
	}

	public static List<String> getList(JsonObject json, String key) {
		List<String> ret = Lists.newArrayList();
		if (GsonHelper.isValidNode(json, key) && json.get(key).isJsonArray()) {
			JsonArray array = GsonHelper.getAsJsonArray(json, key);
			StreamSupport.stream(array.spliterator(), false).filter(e -> e.isJsonPrimitive()).forEach(e -> ret.add(e.getAsJsonPrimitive().getAsString()));
		}
		return ret;
	}

	public static List<DCHeatTier> getHeatTier(JsonObject json) {
		return DCHeatTier.getListFromName(getList(json, "heat"));
	}

	public static List<DCHumidity> getHumidity(JsonObject json) {
		return DCHumidity.getListFromName(getList(json, "hum"));
	}

	public static List<DCAirflow> getAirflow(JsonObject json) {
		return DCAirflow.getListFromName(getList(json, "air"));
	}

	public static List<String> getHeatTierName(List<DCHeatTier> list) {
		List<String> ret = Lists.newArrayList();
		if (list.isEmpty()) {
			ret.add("ANY");
		} else {
			list.forEach(h -> ret.add(h.name()));
		}
		return ret;
	}

	public static List<String> getHumidityName(List<DCHumidity> list) {
		List<String> ret = Lists.newArrayList();
		if (list.isEmpty()) {
			ret.add("ANY");
		} else {
			list.forEach(h -> ret.add(h.name()));
		}
		return ret;
	}

	public static List<String> getAirflowName(List<DCAirflow> list) {
		List<String> ret = Lists.newArrayList();
		if (list.isEmpty()) {
			ret.add("ANY");
		} else {
			list.forEach(h -> ret.add(h.name()));
		}
		return ret;
	}

}
