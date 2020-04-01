package defeatedcrow.hac.main.item.tool;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.config.MainCoreConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBullets extends DCItem {

	private final int maxMeta;

	private static String[] names = { "bolt", "normal", "shot", "silver", "ghost", "light", "extinction", "crow" };

	public ItemBullets(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/cartridge_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	public static Type getType(int meta) {
		meta = MathHelper.clamp(meta, 0, 7);
		return values[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (!DCUtil.isEmpty(stack)) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			int m = stack.getItemDamage();
			switch (getType(m)) {
			case BOLT:
				tooltip.add("Damage: " + MainCoreConfig.gun_damage * 0.5F);
				break;
			case CROW:
				tooltip.add("No Damage");
				tooltip.add(I18n.format("dcs.tip.balloon_bullet"));
				break;
			case EXTINCTION:
				tooltip.add("No Damage");
				tooltip.add(I18n.format("dcs.tip.extinction_bullet"));
				break;
			case GHOST:
				tooltip.add("Damage: " + MainCoreConfig.gun_damage);
				tooltip.add(I18n.format("dcs.tip.penetrate_block"));
				break;
			case LIGHT:
				tooltip.add("No Damage");
				tooltip.add(I18n.format("dcs.tip.light_bullet"));
				break;
			case NORMAL:
				tooltip.add("Damage: " + MainCoreConfig.gun_damage);
				break;
			case SHOT:
				tooltip.add("Damage: " + MainCoreConfig.gun_damage);
				tooltip.add(I18n.format("dcs.tip.penetrate_block"));
				tooltip.add(I18n.format("dcs.tip.ranged_attack"));
				break;
			case SILVER:
				tooltip.add("Damage: " + MainCoreConfig.gun_damage);
				tooltip.add(I18n.format("dcs.tip.silver_bullet"));
				break;
			default:
				break;
			}
		}
	}

	public static Type[] values = {
		Type.BOLT,
		Type.NORMAL,
		Type.SHOT,
		Type.SILVER,
		Type.GHOST,
		Type.LIGHT,
		Type.EXTINCTION,
		Type.CROW };

	public enum Type {
		BOLT,
		NORMAL,
		SILVER,
		SHOT,
		GHOST,
		LIGHT,
		EXTINCTION,
		CROW;
	}

}
