package defeatedcrow.hac.core.recipe.device;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.api.recipe.RecipeTypeDC;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public class DeviceRecipe implements IDeviceRecipe {

	private final RecipeTypeDC type;

	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	private List<String> inputFluid = new ArrayList<String>();

	private ItemStack result = ItemStack.EMPTY;
	private ItemStack secondary = ItemStack.EMPTY;
	private ItemStack tertiary = ItemStack.EMPTY;
	private int secondaryRate = 0;
	private int tertiaryRate = 0;
	private FluidStack resultFluid = FluidStack.EMPTY;

	private List<DCHeatTier> heat = new ArrayList<DCHeatTier>();
	private List<DCHumidity> hum = new ArrayList<DCHumidity>();
	private List<DCAirflow> air = new ArrayList<DCAirflow>();

	private int priority = 1;

	public DeviceRecipe(String group, ItemStack o, ItemStack sec, int secRate, ItemStack ter, int terRate, FluidStack oF, List<String> t, List<String> h, List<String> a, List<String> inF, List<Ingredient> in) {
		this(RecipeTypeDC.getType(group), o, sec, secRate, ter, terRate, oF, DCHeatTier.getListFromName(t), DCHumidity.getListFromName(h), DCAirflow.getListFromName(a), inF, in);
	}

	public DeviceRecipe(RecipeTypeDC typeIn, ItemStack o, ItemStack sec, int secRate, ItemStack ter, int terRate, FluidStack oF, List<DCHeatTier> t, List<DCHumidity> h, List<DCAirflow> a, List<String> inF,
			List<Ingredient> in) {
		type = typeIn;
		int c = 0;
		if (in != null && !in.isEmpty()) {
			ingredients = in;
			c += ingredients.size();
		}
		if (inF != null && !inF.isEmpty()) {
			inputFluid.addAll(inF);
			c += inputFluid.size();
		}
		result = o;
		resultFluid = oF;
		secondary = sec;
		secondaryRate = secRate;
		tertiary = ter;
		tertiaryRate = terRate;
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
		priority = c;
	}

	@Override
	public RecipeTypeDC getType() {
		return type;
	}

	@Override
	public ItemStack getOutput() {
		return result.copy();
	}

	@Override
	public ItemStack getSecondaryOutput() {
		return secondary.copy();
	}

	@Override
	public int getSecondaryRate() {
		return secondaryRate;
	}

	@Override
	public ItemStack getTertiaryOutput() {
		return tertiary.copy();
	}

	@Override
	public int getTertiaryRate() {
		return tertiaryRate;
	}

	@Override
	public FluidStack getOutputFluid() {
		return resultFluid.copy();
	}

	@Override
	public List<Ingredient> getInputs() {
		return ingredients;
	}

	@Override
	public List<TagKey<Fluid>> getInputFluids() {
		List<TagKey<Fluid>> ret = Lists.newArrayList();
		for (String name : inputFluid) {
			ResourceLocation res = new ResourceLocation(name);
			TagKey<Fluid> tagkey = TagKey.create(Registry.FLUID_REGISTRY, res);
			if (tagkey != null)
				ret.add(tagkey);
		}
		return ImmutableList.copyOf(ret);
	}

	@Override
	public int[] matcheInput(List<ItemStack> items) {
		ArrayList<Ingredient> required = Lists.newArrayList();
		required.addAll(ingredients);

		if (required.isEmpty()) {
			return new int[items.size()];
		} else if (items.size() > 0) {
			int[] ret = new int[items.size()];
			for (int x = 0; x < items.size(); x++) {
				ItemStack slot = items.get(x);
				if (DCUtil.isEmpty(slot) || required.isEmpty()) {
					continue;
				}
				Iterator<Ingredient> req = required.iterator();
				while (req.hasNext()) {
					int match = 0;
					Ingredient next = req.next();
					if (next == null) {
						continue;
					}
					for (ItemStack check : next.getItems()) {
						if (DCItemUtil.isSameItem(check, slot, false) && check.getCount() <= slot.getCount()) {
							match += check.getCount();
							break;
						}
					}
					if (match > 0) {
						ret[x] += match;
						required.remove(next);
						break;
					}
				}
				req = null;
			}
			if (required.isEmpty()) {
				return ret;
			}
		}
		return new int[0];
	}

	@Override
	public boolean matcheInputFluid(FluidStack input1, FluidStack input2) {
		boolean f1 = false;
		boolean f2 = false;
		if (getInputFluids().isEmpty()) {
			return true;
		} else if (getInputFluids().size() == 1) {
			TagKey<Fluid> tag1 = getInputFluids().get(0);
			f1 = !input1.isEmpty() && input1.getFluid().is(tag1);
			f2 = true;
		} else if (getInputFluids().size() > 1) {
			TagKey<Fluid> tag1 = getInputFluids().get(0);
			TagKey<Fluid> tag2 = getInputFluids().get(1);
			f1 = !input1.isEmpty() && input1.getFluid().is(tag1);
			f2 = !input2.isEmpty() && input2.getFluid().is(tag2);
		}
		return f1 && f2;
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
	public int getPriority() {
		return priority;
	}

	@Override
	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("type", "dcs_climate:device_recipe");
		json.addProperty("group", type.toString());

		JsonObject ret = new JsonObject();
		ret.addProperty("item", ForgeRegistries.ITEMS.getKey(result.getItem()).toString());
		ret.addProperty("count", result.getCount());
		json.add("result", ret);

		if (!secondary.isEmpty()) {
			JsonObject sec = new JsonObject();
			ret.addProperty("item", ForgeRegistries.ITEMS.getKey(secondary.getItem()).toString());
			ret.addProperty("count", secondary.getCount());
			json.add("secondary", ret);

			json.addProperty("secondary_rate", secondaryRate);
		}

		if (!tertiary.isEmpty()) {
			JsonObject ter = new JsonObject();
			ret.addProperty("item", ForgeRegistries.ITEMS.getKey(tertiary.getItem()).toString());
			ret.addProperty("count", tertiary.getCount());
			json.add("tertiary", ret);

			json.addProperty("tertiary_rate", tertiaryRate);
		}

		if (!resultFluid.isEmpty()) {
			JsonObject retF = new JsonObject();
			retF.addProperty("fluid", ForgeRegistries.FLUIDS.getKey(resultFluid.getFluid()).toString());
			retF.addProperty("amount", resultFluid.getAmount());
			json.add("result_fluid", ret);
		}

		JsonArray ings = new JsonArray();
		ingredients.forEach(i -> ings.add(i.toJson()));
		json.add("ingredients", ings);

		if (!inputFluid.isEmpty()) {
			JsonArray inF = new JsonArray();
			inputFluid.forEach(f -> inF.add(f));
			json.add("input_fluids", inF);
		}

		if (!heat.isEmpty()) {
			JsonArray heats = new JsonArray();
			heat.forEach(h -> heats.add(h.name()));
			json.add("heat", heats);
		}

		if (!hum.isEmpty()) {
			JsonArray hums = new JsonArray();
			hum.forEach(h -> hums.add(h.name()));
			json.add("hum", hums);
		}

		if (!air.isEmpty()) {
			JsonArray airs = new JsonArray();
			air.forEach(h -> airs.add(h.name()));
			json.add("air", airs);
		}

		return json;
	}

}
