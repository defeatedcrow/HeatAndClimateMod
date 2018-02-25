package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.main.block.fluid.FluidDropItemDC;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFluidDrop extends DCItem {

	private final String name;
	private final String contain;

	public ItemFluidDrop(String n, String fluid) {
		super();
		name = n;
		contain = fluid;
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/drop_" + name;
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new FluidDropItemDC(stack, contain);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		String s = "";
		Fluid f = FluidRegistry.getFluid(contain);
		if (f != null) {
			tooltip.add(TextFormatting.YELLOW.toString() + "Fluid: " + f.getLocalizedName(new FluidStack(f, 200)));
			tooltip.add(TextFormatting.YELLOW.toString() + "Amount: " + 200);
		} else {
			tooltip.add(TextFormatting.YELLOW.toString() + "Fluid is empty: " + contain);
		}
	}

}
