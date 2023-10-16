package defeatedcrow.hac.core.recipe.device.data;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

public class DummyDeviceRecipe implements IDummyDeviceRecipe {
	protected final ResourceLocation id;
	protected final String group;

	protected List<Ingredient> ingredients = new ArrayList<Ingredient>();
	protected List<String> inputFluid = new ArrayList<String>();

	protected ItemStack result = ItemStack.EMPTY;
	protected ItemStack secondary = ItemStack.EMPTY;
	protected ItemStack tertiary = ItemStack.EMPTY;
	protected int secondaryRate = 0;
	protected int tertiaryRate = 0;
	protected FluidStack resultFluid = FluidStack.EMPTY;

	protected final List<String> heats = Lists.newArrayList();
	protected final List<String> hums = Lists.newArrayList();
	protected final List<String> airs = Lists.newArrayList();

	public DummyDeviceRecipe(ResourceLocation res, String g, ItemStack o, ItemStack sec, int secRate, ItemStack ter, int terRate, FluidStack oF, List<String> t, List<String> h, List<String> a, List<String> inF,
			List<Ingredient> in) {
		id = res;
		group = g;
		ingredients = in;
		inputFluid = inF;
		result = o;
		secondary = sec;
		tertiary = ter;
		secondaryRate = secRate;
		tertiaryRate = terRate;
		resultFluid = oF;
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

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return CoreInit.DEVICE_RECIPE_SEREALIZER.get();
	}
}
