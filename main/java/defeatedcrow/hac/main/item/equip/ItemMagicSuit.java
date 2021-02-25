package defeatedcrow.hac.main.item.equip;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IMagicClothing;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMagicSuit extends ItemArmorDC implements IMagicClothing {

	public ItemMagicSuit(ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
		super(m, mat, slot, t);
	}

	@Override
	public float getEffect() {
		return 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, world, tooltip, flagIn);
		if (!DCUtil.isEmpty(stack)) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.cloth.magic"));
		}
	}

}
