package defeatedcrow.hac.machine.item;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.IPressMold;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemAluminiumMold extends DCItem implements IPressMold {

	private final int maxMeta;

	public ItemAluminiumMold() {
		super();
		maxMeta = 3;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/mold_aluminium";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (stack != null) {
			ItemStack output = this.getOutput(stack);
			if (output != null) {
				tooltip.add(TextFormatting.BOLD.toString() + "Output: " + output.getDisplayName());
			} else {
				tooltip.add("Please register an item on the anvil.");
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return stack != null && stack.getItemDamage() > 0;
	}

	@Override
	public ItemStack setOutput(ItemStack mold, ItemStack output, int num) {
		int meta = -1;
		if (DCUtil.isSameItem(output, new ItemStack(MachineInit.synthetic, 1, 0), false)
				|| matchOreName(output, "string")) {
			meta = 1;
		} else if (DCUtil.isSameItem(output, new ItemStack(MachineInit.synthetic, 1, 2), false)
				|| DCUtil.isSameItem(output, new ItemStack(Blocks.GLASS_PANE), false)) {
			meta = 2;
		} else if (DCUtil.isSameItem(output, new ItemStack(MainInit.syntheticBlock, 1, 8), false)
				|| DCUtil.isSameItem(output, new ItemStack(Blocks.GLASS), false)) {
			meta = 3;
		}
		if (meta > 0) {
			ItemStack next = new ItemStack(mold.getItem(), mold.stackSize, meta);
			return next;
		} else
			return null;
	}

	@Override
	public ItemStack getOutput(ItemStack mold) {
		if (mold != null) {
			int m = mold.getItemDamage();
			if (m == 1)
				return new ItemStack(MachineInit.synthetic, 2, 0);
			else if (m == 2)
				return new ItemStack(MachineInit.synthetic, 4, 2);
			else if (m == 3)
				return new ItemStack(MainInit.syntheticBlock, 1, 0);
		}
		return null;
	}

	@Override
	public int getRecipeNumber(ItemStack mold) {
		return 0;
	}

	@Override
	public List<ItemStack> getInputs(ItemStack mold) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(MachineInit.reagent, 1, 2));
		return list;
	}

	private boolean matchOreName(ItemStack item, String name) {
		List<ItemStack> list = OreDictionary.getOres(name);
		for (ItemStack check : list) {
			if (OreDictionary.itemMatches(item, check, false))
				return true;
		}
		return false;
	}

}
