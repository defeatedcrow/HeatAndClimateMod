package defeatedcrow.hac.core.recipe.fuel.data;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonObject;

import defeatedcrow.hac.api.recipe.FuelTypeDC;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.recipe.datapack.DataUtilDC;
import defeatedcrow.hac.core.recipe.fuel.DeviceFuel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class DummyFuelSerealizer implements RecipeSerializer<DummyFuel> {

	@Override
	public DummyFuel fromJson(ResourceLocation res, JsonObject json) {
		DCLogger.debugInfoLog("fuel recipe: " + res.toString());
		String type = GsonHelper.getAsString(json, "type", "");
		String group = GsonHelper.getAsString(json, "group", "");
		Ingredient ing = DataUtilDC.getIng(json);
		String fluid = GsonHelper.getAsString(json, "input_fluid", "");
		int b = GsonHelper.getAsInt(json, "burn_time");
		if (!ing.isEmpty() || DeviceFuel.isEmptyFluid(fluid)) {
			DeviceFuel fuel = new DeviceFuel(group, b, fluid, ing);
			if (fuel != null) {
				FuelTypeDC id = FuelTypeDC.getType(group);
				if (id == FuelTypeDC.BIOMASS && !DCRecipes.INSTANCE.BIOMASS_FUEL.containsKey(res)) {
					DCRecipes.INSTANCE.BIOMASS_FUEL.put(res, fuel);
					DCLogger.traceLog("Biomass fuel data from json: " + res);
				}
				if (id == FuelTypeDC.THERMAL && !DCRecipes.INSTANCE.THERMAL_FUEL.containsKey(res)) {
					DCRecipes.INSTANCE.THERMAL_FUEL.put(res, fuel);
					DCLogger.traceLog("Thermal fuel data  from json: " + res);
				}
				if (id == FuelTypeDC.FLUID && !DCRecipes.INSTANCE.FLUID_FUEL.containsKey(res)) {
					DCRecipes.INSTANCE.FLUID_FUEL.put(res, fuel);
					DCLogger.traceLog("Fluid fuel data  from json: " + res);
				}
				if (id == FuelTypeDC.GAS && !DCRecipes.INSTANCE.GAS_FUEL.containsKey(res)) {
					DCRecipes.INSTANCE.GAS_FUEL.put(res, fuel);
					DCLogger.traceLog("Gas fuel data  from json: " + res);
				}
			}
		}
		return new DummyFuel(res, group, b, fluid, ing);
	}

	@Override
	public @Nullable DummyFuel fromNetwork(ResourceLocation res, FriendlyByteBuf buf) {
		String type = buf.readUtf();
		String group = buf.readUtf();
		String fluid = buf.readUtf();
		int burn = buf.readInt();
		Ingredient ing = Ingredient.fromNetwork(buf);
		if (!ing.isEmpty() || DeviceFuel.isEmptyFluid(fluid)) {
			DeviceFuel fuel = new DeviceFuel(group, burn, fluid, ing);
			if (fuel != null) {
				FuelTypeDC id = FuelTypeDC.getType(group);
				if (id == FuelTypeDC.BIOMASS && !DCRecipes.INSTANCE.BIOMASS_FUEL.containsKey(res)) {
					DCRecipes.INSTANCE.BIOMASS_FUEL.put(res, fuel);
					DCLogger.traceLog("Biomass fuel data from bytebuf: " + res);
				}
				if (id == FuelTypeDC.THERMAL && !DCRecipes.INSTANCE.THERMAL_FUEL.containsKey(res)) {
					DCRecipes.INSTANCE.THERMAL_FUEL.put(res, fuel);
					DCLogger.traceLog("Thermal fuel data  from bytebuf: " + res);
				}
				if (id == FuelTypeDC.FLUID && !DCRecipes.INSTANCE.FLUID_FUEL.containsKey(res)) {
					DCRecipes.INSTANCE.FLUID_FUEL.put(res, fuel);
					DCLogger.traceLog("Fluid fuel data  from bytebuf: " + res);
				}
				if (id == FuelTypeDC.GAS && !DCRecipes.INSTANCE.GAS_FUEL.containsKey(res)) {
					DCRecipes.INSTANCE.GAS_FUEL.put(res, fuel);
					DCLogger.traceLog("Gas fuel data  from bytebuf: " + res);
				}
			}
		}
		return new DummyFuel(res, group, burn, fluid, ing);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buf, DummyFuel recipe) {
		buf.writeUtf(recipe.getType().toString());
		buf.writeUtf(recipe.group);
		buf.writeUtf(recipe.inputFluid);
		buf.writeInt(recipe.burnTime);
		recipe.ingredient.toNetwork(buf);
	}

}
