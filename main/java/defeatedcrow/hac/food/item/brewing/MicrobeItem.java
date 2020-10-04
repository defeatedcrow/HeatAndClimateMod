package defeatedcrow.hac.food.item.brewing;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class MicrobeItem extends DCItem implements IMicrobe {

	public MicrobeItem() {
		super();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			int i = stack.getItemDamage();
			if (i == 2)
				return EnumRarity.RARE;
			if (i == 1)
				return EnumRarity.UNCOMMON;
		}
		return EnumRarity.COMMON;
	}

	@Override
	public int getMaxMeta() {
		return 2;
	}

	public String getAbilityName(ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			int i = stack.getItemDamage();
			return "dcs.tip.microbe." + ((IMicrobe) stack.getItem()).getName() + "_" + i;
		}
		return "dcs.tip.microbe." + ((IMicrobe) stack.getItem()).getName() + "_" + 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		tooltip.add(TextFormatting.BOLD.toString() + getRarity(stack));
		tooltip.add(I18n.format(getAbilityName(stack)));
	}

}
