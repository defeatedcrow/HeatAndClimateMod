package defeatedcrow.hac.core.recipe.smelting;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class ClimateSmelting implements IClimateSmelting {

	private Ingredient ingredient;
	private final ItemStack result;

	private final int frequency;
	private List<DCHeatTier> heat = new ArrayList<DCHeatTier>();
	private List<DCHumidity> hum = new ArrayList<DCHumidity>();
	private List<DCAirflow> air = new ArrayList<DCAirflow>();

	public ClimateSmelting(String group, ItemStack o, List<String> t, List<String> h, List<String> a, int freq, Ingredient i) {
		this(o, DCHeatTier.getListFromName(t), DCHumidity.getListFromName(h), DCAirflow.getListFromName(a), freq, DCItemUtil.getIngredient(i));
	}

	public ClimateSmelting(ItemStack o, List<DCHeatTier> t, List<DCHumidity> h, List<DCAirflow> a, int freq,
			Ingredient i) {
		ingredient = DCItemUtil.getIngredient(i);
		result = o;
		if (t != null && !t.isEmpty()) {
			heat.addAll(t);
		} else {
			heat.addAll(DCHeatTier.elements());
		}
		if (h != null && !h.isEmpty()) {
			hum.addAll(h);
		} else {
			hum.addAll(DCHumidity.elements());
		}
		if (a != null && !a.isEmpty()) {
			air.addAll(a);
		} else {
			air.addAll(DCAirflow.elements());
		}
		frequency = freq;
	}

	@Override
	public Ingredient getInput() {
		return ingredient;
	}

	@Override
	public ItemStack getOutput() {
		return result.copy();
	}

	@Override
	public boolean matcheInput(ItemStack item) {
		if (item.isEmpty() || ingredient.isEmpty()) {
			return false;
		}
		return ingredient.test(item);
	}

	@Override
	public boolean matchClimate(int code) {
		IClimate clm = ClimateAPI.helper.getClimateFromInt(code);
		return matchClimate(clm);
	}

	@Override
	public boolean matchClimate(IClimate climate) {
		boolean t = requiredHeat().isEmpty() || requiredHeat().contains(climate.getHeat());
		boolean h = requiredHum().isEmpty() || requiredHum().contains(climate.getHumidity());
		boolean a = requiredAir().isEmpty() || requiredAir().contains(climate.getAirflow());
		return t && h && a;
	}

	@Override
	public boolean hasBlockProcess() {
		if (result.getItem() instanceof BlockItem) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasEntityProcess() {
		if (result.getItem() instanceof IEntityItem) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasDropItemProcess() {
		if (ConfigCommonBuilder.INSTANCE.enDropSmelting.get()) {
			return true;
		}
		return false;
	}

	@Override
	public List<DCHeatTier> requiredHeat() {
		return heat;
	}

	@Override
	public List<DCHumidity> requiredHum() {
		return hum;
	}

	@Override
	public List<DCAirflow> requiredAir() {
		return air;
	}

	@Override
	public int recipeFrequency() {
		return frequency;
	}

	@Override
	public boolean additionalRequire(Level level, BlockPos pos) {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ClimateSmelting))
			return false;
		ClimateSmelting target = (ClimateSmelting) obj;
		return DCItemUtil.isSameItem(result, target.getOutput(), false) && target.getInput() == ingredient;
	}

	@Override
	public boolean hasRandomTick() {
		if (ingredient.isEmpty())
			return false;

		ItemStack top = ingredient.getItems()[0];
		if (top.getItem() instanceof BlockItem) {
			Block block = ((BlockItem) top.getItem()).getBlock();
			return block.isRandomlyTicking(block.defaultBlockState());
		} else if (top.getItem() instanceof IEntityItem) {
			return true;
		}
		return true;
	}

	@Override
	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("type", "dcs_climate:climate_smelting");
		json.addProperty("group", "climate_smelting");

		JsonObject ret = new JsonObject();
		ret.addProperty("item", ForgeRegistries.ITEMS.getKey(result.getItem()).toString());
		json.add("result", ret);

		json.add("ingredient", ingredient.toJson());
		json.addProperty("frequency", frequency);

		JsonArray heats = new JsonArray();
		heat.forEach(h -> heats.add(h.name()));
		json.add("heat", heats);

		JsonArray hums = new JsonArray();
		hum.forEach(h -> hums.add(h.name()));
		json.add("hum", hums);

		JsonArray airs = new JsonArray();
		air.forEach(h -> airs.add(h.name()));
		json.add("air", airs);

		return json;
	}
}
