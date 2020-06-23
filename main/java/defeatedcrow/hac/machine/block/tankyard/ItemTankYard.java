package defeatedcrow.hac.machine.block.tankyard;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTankYard extends DCItemBlock {

	public ItemTankYard(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(TextFormatting.BOLD.toString() + "Tier 3");
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null) {
			int w = tag.getInteger("width");
			int h = tag.getInteger("height");
			int l = tag.getInteger("limit");
			if (w > 0 && h > 0) {
				tooltip.add(TextFormatting.BOLD.toString() + "TANK SIZE");
				tooltip.add(w + "x" + w + "x" + h);
			}
			boolean b = false;
			if (tag.hasKey("Tank") && l > 0) {
				NBTTagList list = tag.getTagList("Tank", 10);
				NBTTagCompound nbt2 = list.getCompoundTagAt(0);
				if (!nbt2.hasKey("Empty")) {
					FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt2);
					if (fluid != null) {
						tooltip.add(TextFormatting.BOLD.toString() + "CONTAINED FLUID");
						tooltip.add("Fluid: " + fluid.getLocalizedName());
						tooltip.add("Amount: " + fluid.amount + "/" + l + " mB");
						b = true;
					}
				}
				if (!b) {
					tooltip.add(TextFormatting.BOLD.toString() + "CONTAINED FLUID");
					tooltip.add("Fluid: Empty");
					tooltip.add("Amount: 0" + "/" + l + " mB");
				}
			}
		}
	}

}
