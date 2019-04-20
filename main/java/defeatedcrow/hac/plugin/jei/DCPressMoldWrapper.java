package defeatedcrow.hac.plugin.jei;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.main.api.IPressMold;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class DCPressMoldWrapper implements IRecipeWrapper {

	private final IPressMold rec;
	private final ItemStack mold;
	private final List<ItemStack> input;
	private final List<ItemStack> output;

	@SuppressWarnings("unchecked")
	public DCPressMoldWrapper(MoldItem recipe) {
		mold = recipe.mold;
		rec = (recipe.rec);
		input = Lists.newArrayList();
		output = Lists.newArrayList();
		input.addAll(rec.getInputs(mold));
		output.add(0, rec.getOutput(mold));
	}

	@Override
	public void getIngredients(IIngredients ing) {
		List<ItemStack> in = Lists.newArrayList();
		in.add(mold);
		in.addAll(input);
		ing.setInputs(VanillaTypes.ITEM, in);
		ing.setOutputs(VanillaTypes.ITEM, output);
	}

	public List<ItemStack> getInput() {
		return input;
	}

	public List<ItemStack> getOutputs() {
		return output;
	}

	public ItemStack getMold() {
		return mold.copy();
	}

	@Override
	public void drawInfo(Minecraft mc, int wid, int hei, int mouseX, int mouseY) {
		mc.fontRenderer.drawSplitString(I18n.format("dcs.tip.pressmold.jei"), 32, 5, 110, 0x000000);
	}

}
