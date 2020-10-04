package defeatedcrow.hac.main.item.tool;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.api.IWideMining;
import defeatedcrow.hac.main.util.DCToolMaterial;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAxeDC extends ItemAxe implements ITexturePath, IWideMining {

	private final String tex;
	private boolean isToolsteel;
	private boolean isMangalloy;

	public ItemAxeDC(ToolMaterial m, String t) {
		super(m, m.getAttackDamage() + 2.0F, -3.0F);
		if (m == DCToolMaterial.DC_TOOLMETAL) {
			isToolsteel = true;
		}
		if (m == DCToolMaterial.DC_MANGALLOY) {
			isMangalloy = true;
		}
		tex = t;
	}

	public ItemAxeDC setToolsteel() {
		isToolsteel = true;
		return this;
	}

	public ItemAxeDC setMangalloy() {
		isMangalloy = true;
		return this;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/axe_" + tex;
	}

	@Override
	public int getMiningRange(EntityPlayer player, ItemStack stack, int level) {
		return isToolsteel ? 1 : 0;
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if (isMangalloy) {
			Map<Enchantment, Integer> map = Collections.singletonMap(Enchantments.SILK_TOUCH, 1);
			EnchantmentHelper.setEnchantments(map, stack);
		} else {
			super.onCreated(stack, world, player);
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (isMangalloy && this.isInCreativeTab(tab)) {
			ItemStack ret = new ItemStack(this);
			Map<Enchantment, Integer> map = Collections.singletonMap(Enchantments.SILK_TOUCH, 1);
			EnchantmentHelper.setEnchantments(map, ret);
			items.add(ret);
		} else {
			super.getSubItems(tab, items);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (isToolsteel) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.toolsteel.tools"));
		}
	}

}
