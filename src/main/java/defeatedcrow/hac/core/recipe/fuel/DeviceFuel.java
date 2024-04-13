package defeatedcrow.hac.core.recipe.fuel;

import com.google.gson.JsonObject;

import defeatedcrow.hac.api.recipe.FuelTypeDC;
import defeatedcrow.hac.api.recipe.IDeviceFuel;
import defeatedcrow.hac.core.tag.TagDC.FluidTag;
import defeatedcrow.hac.core.util.DCItemUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class DeviceFuel implements IDeviceFuel {

	private final FuelTypeDC type;
	private Ingredient ingredient;
	private String inputFluidName;

	private final int burnTime;

	public DeviceFuel(String group, int burn, String f, Ingredient i) {
		type = FuelTypeDC.getType(group);
		ingredient = DCItemUtil.getIngredient(i);
		inputFluidName = f;
		burnTime = burn;
	}

	@Override
	public FuelTypeDC getType() {
		return type;
	}

	@Override
	public Ingredient getInput() {
		return ingredient;
	}

	@Override
	public TagKey<Fluid> getInputFluid() {
		if (isEmptyFluid(inputFluidName))
			return FluidTag.AIR;
		ResourceLocation res = new ResourceLocation(inputFluidName);
		TagKey<Fluid> tagkey = TagKey.create(Registry.FLUID_REGISTRY, res);
		if (tagkey != null)
			return tagkey;
		return FluidTag.AIR;
	}

	public static boolean isEmptyFluid(String s) {
		return s == null || s.equalsIgnoreCase("empty");
	}

	@Override
	public boolean matcheInput(ItemStack item) {
		if (item.isEmpty() || ingredient.isEmpty()) {
			return false;
		}
		return ingredient.test(item);
	}

	@Override
	public boolean matcheInputFluid(FluidStack input) {
		boolean f1 = false;
		boolean f2 = false;
		TagKey<Fluid> tag = getInputFluid();
		if (tag == FluidTag.AIR) {
			return input.isEmpty();
		} else {
			return !input.isEmpty() && input.getFluid().is(tag);
		}
	}

	@Override
	public int getBurnTime() {
		return burnTime;
	}

	@Override
	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("type", "dcs_climate:device_fuel");
		json.addProperty("group", type.toString());

		json.add("ingredient", ingredient.toJson());
		json.addProperty("input_fluid", inputFluidName);
		json.addProperty("burn_time", burnTime);

		return json;
	}

}
