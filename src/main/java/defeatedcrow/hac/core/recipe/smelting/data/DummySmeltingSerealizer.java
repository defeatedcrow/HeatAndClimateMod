package defeatedcrow.hac.core.recipe.smelting.data;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

import com.google.gson.JsonObject;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.recipe.datapack.DataUtilDC;
import defeatedcrow.hac.core.recipe.smelting.ClimateSmelting;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

/**
 * It's just a pathway for data packs. DummyRecipe does not participate in HaC's machine recipe implementation.
 */
public class DummySmeltingSerealizer implements RecipeSerializer<DummySmelting> {

	@Override
	public DummySmelting fromJson(ResourceLocation res, JsonObject json) {
		String group = GsonHelper.getAsString(json, "group", "");
		ItemStack ret = DataUtilDC.getStack(json, "result");
		Ingredient ing = DataUtilDC.getIng(json);
		int f = GsonHelper.getAsInt(json, "frequency");
		List<String> heat = DataUtilDC.getList(json, "heat");
		List<String> hum = DataUtilDC.getList(json, "hum");
		List<String> air = DataUtilDC.getList(json, "air");
		if (!DCUtil.isEmpty(ret)) {
			ClimateSmelting smelting = new ClimateSmelting(group, ret, heat, hum, air, f, ing);
			if (smelting != null) {
				DCRecipes.INSTANCE.SMELTING.put(res, smelting);
				DCLogger.traceLog("ClimateSmelting loaded from json: " + res);
			}
		}
		return new DummySmelting(res, group, ret, heat, hum, air, f, ing);
	}

	@Override
	public DummySmelting fromNetwork(ResourceLocation res, FriendlyByteBuf buf) {
		String group = buf.readUtf();
		ItemStack ret = buf.readItem();
		Ingredient ing = Ingredient.fromNetwork(buf);
		int f = buf.readVarInt();
		List<String> heat = Lists.newArrayList();
		List<String> hum = Lists.newArrayList();
		List<String> air = Lists.newArrayList();
		int i1 = buf.readVarInt();
		for (int j1 = 0; j1 < i1; ++j1) {
			heat.add(buf.readUtf());
		}
		int i2 = buf.readVarInt();
		for (int j2 = 0; j2 < i2; ++j2) {
			hum.add(buf.readUtf());
		}
		int i3 = buf.readVarInt();
		for (int j3 = 0; j3 < i3; ++j3) {
			air.add(buf.readUtf());
		}
		if (!DCUtil.isEmpty(ret)) {
			try {
				ClimateSmelting smelting = new ClimateSmelting(group, ret, heat, hum, air, f, ing);
				if (smelting != null) {
					DCRecipes.INSTANCE.SMELTING.put(res, smelting);
					DCLogger.traceLog("ClimateSmelting loaded from json: " + res);
				}
			} catch (Exception e) {
				DCLogger.infoLog("ClimateSmelting load failed: " + res);
			}
		}
		return new DummySmelting(res, group, ret, heat, hum, air, f, ing);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buf, DummySmelting recipe) {
		buf.writeUtf(recipe.group);
		buf.writeItem(recipe.result);
		recipe.ingredient.toNetwork(buf);
		buf.writeInt(recipe.frequency);
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
