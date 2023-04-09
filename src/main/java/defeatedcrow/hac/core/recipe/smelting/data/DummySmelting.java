package defeatedcrow.hac.core.recipe.smelting.data;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

/**
 * It's just a pathway for data packs. DummyRecipe does not participate in HaC's machine recipe implementation.
 */
public class DummySmelting implements IDummySmelting {
	protected final ResourceLocation id;
	protected final String group;
	protected final ItemStack result;
	protected final Ingredient ingredient;
	protected final int frequency;
	protected final List<String> heats = Lists.newArrayList();
	protected final List<String> hums = Lists.newArrayList();
	protected final List<String> airs = Lists.newArrayList();

	public DummySmelting(ResourceLocation res, String g, ItemStack out, List<String> t, List<String> h, List<String> a, int freq, Ingredient i) {
		this.id = res;
		this.group = g;
		this.result = out;
		this.ingredient = i;
		this.frequency = freq;
		heats.addAll(t);
		hums.addAll(h);
		airs.addAll(a);
	}

	@Override
	public boolean matches(Container cont, Level level) {
		return false;
	}

	@Override
	public ItemStack assemble(Container cont) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean canCraftInDimensions(int i, int k) {
		return false;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> list = NonNullList.create();
		return list;
	}

	public float getExperience() {
		return 0F;
	}

	@Override
	public ItemStack getResultItem() {
		return this.result.copy();
	}

	@Override
	public String getGroup() {
		return group;
	}

	public int getCookingTime() {
		return frequency;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return CoreInit.SMELTING_SEREALIZER.get();
	}

}
