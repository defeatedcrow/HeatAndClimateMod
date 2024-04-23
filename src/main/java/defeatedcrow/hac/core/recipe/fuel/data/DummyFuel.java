package defeatedcrow.hac.core.recipe.fuel.data;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class DummyFuel implements IDummyFuel {

	protected final ResourceLocation id;
	protected final String group;
	protected final String inputFluid;
	protected final Ingredient ingredient;
	protected final int burnTime;

	public DummyFuel(ResourceLocation res, String g, int burn, String f, Ingredient i) {
		this.id = res;
		this.group = g;
		this.inputFluid = f;
		this.ingredient = i;
		this.burnTime = burn;
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
		list.add(ingredient);
		return list;
	}

	public float getExperience() {
		return 0F;
	}

	@Override
	public ItemStack getResultItem() {
		return ItemStack.EMPTY;
	}

	@Override
	public String getGroup() {
		return group;
	}

	public int getCookingTime() {
		return burnTime;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return CoreInit.DEVICE_FUEL_SEREALIZER.get();
	}

}
