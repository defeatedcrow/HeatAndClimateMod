package defeatedcrow.hac.api.recipe;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * ItemとFluidどちらも登録可能な燃料登録レシピ。<br>
 * 材料は辞書対応。<br>
 */
public interface IDeviceFuel extends ISerealizedRecipe {

	FuelTypeDC getType();

	Ingredient getInput();

	TagKey<Fluid> getInputFluid();

	boolean matcheInput(ItemStack item);

	boolean matcheInputFluid(FluidStack inputFluid);

	int getBurnTime();
}
