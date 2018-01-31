package defeatedcrow.hac.food.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import net.minecraft.client.util.ITooltipFlag;
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

public class ItemFluidPack extends DCItem {

	private static String[] names = {
			"empty", "water", "milk", "cream", "oil", "vegi"
	};

	public static final String[] FLUIDS = {
			"empty", "water", "milk", "dcs.milk_cream", "dcs.seed_oil", "dcs.vegetable_juice"
	};

	public ItemFluidPack() {
		super();
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		if (stack.getItemDamage() == 0)
			return null;
		return new ItemStack(this);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return stack.getItemDamage() != 0;
	}

	@Override
	public int getMaxMeta() {
		return 5;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		int i = meta;
		if (i > getMaxMeta()) {
			i = getMaxMeta();
		}
		String s = "items/food/pack_" + names[i];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		int i = stack.getMetadata();
		if (i == 4)
			return 1600;
		else
			return 0;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new FluidPaperContDC(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		if (stack == null)
			return;

		String s = "";
		int i = stack.getItemDamage();
		if (i > 5) {
			i = 5;
		}

		Fluid f = FluidRegistry.getFluid(FLUIDS[i]);
		if (f != null) {
			tooltip.add(TextFormatting.YELLOW.toString() + "Fluid: " + f.getLocalizedName(new FluidStack(f, 200)));
			tooltip.add(TextFormatting.YELLOW.toString() + "Amount: " + 250);
		} else {
			tooltip.add(TextFormatting.YELLOW.toString() + "Fluid is empty: " + FLUIDS[i]);
		}
	}

	public static String getFluidName(int meta) {
		if (meta > 5) {
			meta = 5;
		}
		return FLUIDS[meta];
	}

	public static Fluid getFluid(int meta) {
		String name = getFluidName(meta);
		return FluidRegistry.getFluid(name);
	}

}
