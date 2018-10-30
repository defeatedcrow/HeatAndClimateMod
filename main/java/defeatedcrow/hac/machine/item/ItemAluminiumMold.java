package defeatedcrow.hac.machine.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.IPressMold;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemAluminiumMold extends DCItem implements IPressMold {

	private final int maxMeta;

	public ItemAluminiumMold() {
		super();
		maxMeta = 5;
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
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		if (!DCUtil.isEmpty(stack)) {
			ItemStack output = this.getOutput(stack);
			if (!DCUtil.isEmpty(output)) {
				tooltip.add(TextFormatting.BOLD.toString() + "Output: " + output.getDisplayName());
			} else {
				tooltip.add(I18n.format("dcs.tip.mold1"));
			}
		}
		tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
		tooltip.add(I18n.format("dcs.tip.mold2"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return !DCUtil.isEmpty(stack) && stack.getItemDamage() > 0;
	}

	@Override
	public ItemStack setOutput(ItemStack mold, ItemStack output, int num) {
		int meta = -1;
		if (DCUtil.isSameItem(output, new ItemStack(MachineInit.synthetic, 1,
				0), false) || matchOreName(output, "string")) {
			meta = 1;
		} else if (DCUtil.isSameItem(output, new ItemStack(MachineInit.synthetic, 1, 2), false) || DCUtil
				.isSameItem(output, new ItemStack(Blocks.GLASS_PANE), false)) {
			meta = 2;
		} else if (DCUtil.isSameItem(output, new ItemStack(MainInit.syntheticBlock, 1, 8), false) || DCUtil
				.isSameItem(output, new ItemStack(Blocks.GLASS), false)) {
			meta = 3;
		} else if (DCUtil.isSameItem(output, new ItemStack(MachineInit.synthetic, 1,
				2), false) || matchOreName(output, "leather")) {
			meta = 4;
		} else if (DCUtil.isSameItem(output, new ItemStack(MachineInit.synthetic, 1,
				2), false) || matchOreName(output, "rabbithide")) {
			meta = 5;
		}
		if (meta > 0) {
			ItemStack next = new ItemStack(mold.getItem(), mold.getCount(), meta);
			return next;
		} else
			return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getOutput(ItemStack mold) {
		if (!DCUtil.isEmpty(mold)) {
			int m = mold.getItemDamage();
			if (m == 1)
				return new ItemStack(MachineInit.synthetic, 2, 0);
			else if (m == 2)
				return new ItemStack(MachineInit.synthetic, 4, 2);
			else if (m == 3)
				return new ItemStack(MainInit.syntheticBlock, 1, 0);
			else if (m == 4)
				return new ItemStack(MachineInit.synthetic, 1, 3);
			else if (m == 5)
				return new ItemStack(MachineInit.synthetic, 1, 4);
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
		if (mold.getItemDamage() == 4) {
			list.add(new ItemStack(MainInit.clothes, 1, 2));
		}
		if (mold.getItemDamage() == 5) {
			list.add(new ItemStack(FoodInit.crops, 1, 5));
		}
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
