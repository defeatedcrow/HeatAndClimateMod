package defeatedcrow.hac.core.recipe.device.data;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;

import defeatedcrow.hac.api.recipe.RecipeTypeDC;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.recipe.datapack.DataUtilDC;
import defeatedcrow.hac.core.recipe.device.DeviceRecipe;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;

public class DummyDeviceRecipeSerealizer implements RecipeSerializer<DummyDeviceRecipe> {
	@Override
	public DummyDeviceRecipe fromJson(ResourceLocation res, JsonObject json) {
		String type = GsonHelper.getAsString(json, "type", "");
		String group = GsonHelper.getAsString(json, "group", "");
		ItemStack ret = DataUtilDC.getStackOrEmpty(json, "result");
		ItemStack sec = DataUtilDC.getStackOrEmpty(json, "secondary");
		ItemStack ter = DataUtilDC.getStackOrEmpty(json, "tertiary");
		int sR = DataUtilDC.getIntOrZero(json, "secondary_rate");
		int tR = DataUtilDC.getIntOrZero(json, "tertiary_rate");
		FluidStack retF = DataUtilDC.getFluidStack(json, "result_fluid");
		List<Ingredient> ings = DataUtilDC.getIngs(json);
		List<String> inF = DataUtilDC.getList(json, "input_fluids");
		List<String> heat = DataUtilDC.getList(json, "heat");
		List<String> hum = DataUtilDC.getList(json, "hum");
		List<String> air = DataUtilDC.getList(json, "air");
		if (!DCUtil.isEmpty(ret)) {
			DeviceRecipe recipe = new DeviceRecipe(group, ret, sec, sR, ter, tR, retF, heat, hum, air, inF, ings);
			if (recipe != null) {
				RecipeTypeDC id = RecipeTypeDC.getType(group);
				if (id == RecipeTypeDC.PULVERISE) {
					DCRecipes.INSTANCE.PULVERISE.put(res, recipe);
					DCLogger.traceLog("Pulverise recipe loaded from json: " + res);
				}
				if (id == RecipeTypeDC.SQUEEZE) {
					DCRecipes.INSTANCE.SQUEEZE.put(res, recipe);
					DCLogger.traceLog("Squeeze recipe loaded from json: " + res);
				}
				if (id == RecipeTypeDC.SIEVE) {
					DCRecipes.INSTANCE.SIEVE.put(res, recipe);
					DCLogger.traceLog("Sieve recipe loaded from json: " + res);
				}
				if (id == RecipeTypeDC.COOKING) {
					DCRecipes.INSTANCE.COOKING.put(res, recipe);
					DCLogger.traceLog("Cooking recipe loaded from json: " + res);
				}
				if (id == RecipeTypeDC.TEA) {
					DCRecipes.INSTANCE.TEA.put(res, recipe);
					DCLogger.traceLog("Tea recipe loaded from json: " + res);
				}
				if (id == RecipeTypeDC.FERMENTATION) {
					DCRecipes.INSTANCE.FERMENTATION.put(res, recipe);
					DCLogger.traceLog("Fermentation recipe loaded from json: " + res);
				}

			}
		}
		return new DummyDeviceRecipe(res, group, ret, sec, sR, ter, tR, retF, heat, hum, air, inF, ings);
	}

	@Override
	public DummyDeviceRecipe fromNetwork(ResourceLocation res, FriendlyByteBuf buf) {
		String type = buf.readUtf();
		String group = buf.readUtf();
		ItemStack ret = buf.readItem();
		int sR = buf.readInt();
		ItemStack sec = buf.readItem();
		int tR = buf.readInt();
		ItemStack ter = buf.readItem();
		FluidStack retF = buf.readFluidStack();
		int i1 = buf.readInt();
		List<Ingredient> ings = Lists.newArrayList();
		for (int j1 = 0; j1 < i1; ++j1) {
			ings.add(Ingredient.fromNetwork(buf));
		}
		int i2 = buf.readInt();
		List<String> inF = Lists.newArrayList();
		for (int j2 = 0; j2 < i2; ++j2) {
			inF.add(buf.readUtf());
		}
		List<String> heat = Lists.newArrayList();
		List<String> hum = Lists.newArrayList();
		List<String> air = Lists.newArrayList();
		int i3 = buf.readInt();
		for (int j3 = 0; j3 < i3; ++j3) {
			heat.add(buf.readUtf());
		}
		int i4 = buf.readInt();
		for (int j4 = 0; j4 < i4; ++j4) {
			hum.add(buf.readUtf());
		}
		int i5 = buf.readInt();
		for (int j5 = 0; j5 < i5; ++j5) {
			air.add(buf.readUtf());
		}
		if (!DCUtil.isEmpty(ret)) {
			try {
				DeviceRecipe recipe = new DeviceRecipe(group, ret, sec, sR, ter, tR, retF, heat, hum, air, inF, ings);
				if (recipe != null) {
					RecipeTypeDC id = RecipeTypeDC.getType(group);
					if (id == RecipeTypeDC.PULVERISE) {
						DCRecipes.INSTANCE.PULVERISE.put(res, recipe);
						DCLogger.traceLog("Pulverise recipe loaded from json: " + res);
					}
					if (id == RecipeTypeDC.SQUEEZE) {
						DCRecipes.INSTANCE.SQUEEZE.put(res, recipe);
						DCLogger.traceLog("Squeeze recipe loaded from json: " + res);
					}
					if (id == RecipeTypeDC.SIEVE) {
						DCRecipes.INSTANCE.SIEVE.put(res, recipe);
						DCLogger.traceLog("Sieve recipe loaded from json: " + res);
					}
					if (id == RecipeTypeDC.COOKING) {
						DCRecipes.INSTANCE.COOKING.put(res, recipe);
						DCLogger.traceLog("Cooking recipe loaded from json: " + res);
					}
					if (id == RecipeTypeDC.TEA) {
						DCRecipes.INSTANCE.TEA.put(res, recipe);
						DCLogger.traceLog("Tea recipe loaded from json: " + res);
					}

				}
			} catch (Exception e) {
				DCLogger.infoLog("Devicerecipe load failed: " + res);
			}
		}
		return new DummyDeviceRecipe(res, group, ret, sec, sR, ter, tR, retF, heat, hum, air, inF, ings);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buf, DummyDeviceRecipe recipe) {
		buf.writeUtf(recipe.getType().toString());
		buf.writeUtf(recipe.group);
		buf.writeItem(recipe.result);
		buf.writeInt(recipe.secondaryRate);
		buf.writeItem(recipe.secondary);
		buf.writeInt(recipe.tertiaryRate);
		buf.writeItem(recipe.tertiary);
		buf.writeFluidStack(recipe.resultFluid);
		buf.writeInt(recipe.ingredients.size());
		for (Ingredient i : recipe.ingredients) {
			i.toNetwork(buf);
		}
		buf.writeInt(recipe.inputFluid.size());
		for (String f : recipe.inputFluid) {
			buf.writeUtf(f);
		}
		buf.writeInt(recipe.heats.size());
		for (String h : recipe.heats) {
			buf.writeUtf(h);
		}
		buf.writeInt(recipe.hums.size());
		for (String h : recipe.hums) {
			buf.writeUtf(h);
		}
		buf.writeInt(recipe.airs.size());
		for (String h : recipe.airs) {
			buf.writeUtf(h);
		}
	}
}
