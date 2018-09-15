package defeatedcrow.hac.machine.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.IPressMold;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAlloyMold extends DCItem implements IPressMold {

	private final int maxMeta;

	public ItemAlloyMold() {
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
		String s = "items/misc/mold_alloy";
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
				if (ClimateCore.isDebug) {
					tooltip.add(TextFormatting.BOLD.toString() + "Recipe ID: " + this.getRecipeNumber(stack));
				}
			} else {
				tooltip.add(I18n.format("dcs.tip.mold1"));
			}
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.mold2"));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack setOutput(ItemStack mold, ItemStack output, int num) {
		return null;
	}

	@Override
	public ItemStack getOutput(ItemStack mold) {
		if (!DCUtil.isEmpty(mold)) {
			int m = mold.getItemDamage();
			if (m == 0)
				return new ItemStack(MachineInit.catalyst, 1, 0);
			else if (m == 1)
				return new ItemStack(MachineInit.catalyst, 1, 1);
			else if (m == 2)
				return new ItemStack(MachineInit.catalyst, 1, 2);
			else if (m == 3)
				return new ItemStack(MachineInit.catalyst, 1, 3);
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
		if (!DCUtil.isEmpty(mold)) {
			if (mold.getItemDamage() == 0) {
				list.add(new ItemStack(MainInit.oreDust, 2, 2));
				list.add(new ItemStack(MainInit.oreDust, 1, 11));
				list.add(new ItemStack(MainInit.miscDust, 1, 1));
			} else if (mold.getItemDamage() == 1) {
				list.add(new ItemStack(MainInit.oreDust, 2, 10));
				list.add(new ItemStack(MainInit.oreDust, 1, 11));
				list.add(new ItemStack(MainInit.miscDust, 1, 1));
			} else if (mold.getItemDamage() == 2) {
				list.add(new ItemStack(MainInit.oreDust, 2, 12));
				list.add(new ItemStack(MainInit.oreDust, 1, 11));
				list.add(new ItemStack(MainInit.miscDust, 1, 1));
			} else if (mold.getItemDamage() == 3) {
				list.add(new ItemStack(Items.BLAZE_POWDER, 1, 0));
				list.add(new ItemStack(MainInit.oreDust, 1, 10));
				list.add(new ItemStack(MainInit.oreDust, 1, 11));
				list.add(new ItemStack(MainInit.miscDust, 1, 1));
			}
		}
		return list;
	}

}
