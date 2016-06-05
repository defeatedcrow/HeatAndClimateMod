package defeatedcrow.hac.magic.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCPotion;

/**
 * インベントリの最上段に入れていると効果のあるアクセサリー類。
 * ペンダントは常時系or防御系効果を持ち、耐久値の概念がない。
 */
public class ItemMagicalPendant extends DCItem implements IJewelCharm {

	private final int maxMeta;

	private static String[] names = {
			"chal_blue", /* 水耐性 */
			"chal_red", /* 火耐性 */
			"chal_white", /* ダメージ軽減 */
			"crystal", /* 悪性ポーション */
			"sapphire", /* 辞書閲覧機能 */
			"malachite", /* 常時暗視 */
			"celestite", /* 落下耐性 */
			"clam", /* ワープ */
			"lapis",/* クラフト経験値 */
			"diamond"/* ブロックドロップ増加 */};

	public ItemMagicalPendant(int max) {
		super();
		maxMeta = max;
		this.setMaxStackSize(1);
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
		String s = "items/equip/pendant_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public CharmType getType(int meta) {
		switch (meta) {
		case 2:
			return CharmType.DEFFENCE;
		case 9:
			return CharmType.TOOL;
		case 7:
			return CharmType.SPECIAL;
		default:
			return CharmType.CONSTANT;
		}
	}

	@Override
	public void formLivingEffect(EntityPlayer player, ItemStack charm) {
		int meta = charm.getItemDamage();

		if (meta == 3) {
			List<PotionEffect> removes = new ArrayList<PotionEffect>();
			Collection<PotionEffect> target = player.getActivePotionEffects();
			for (PotionEffect check : target) {
				Potion p = check.getPotion();
				if (p != null && p.isBadEffect()) {
					removes.add(check);
				}
			}
			for (PotionEffect ret : removes) {
				player.removePotionEffect(ret.getPotion());
			}
		}

		PotionEffect eff = null;
		switch (meta) {
		case 0:
			eff = new PotionEffect(DCPotion.water_breath, 5, 0);
			break;
		case 1:
			eff = new PotionEffect(DCPotion.fire_reg, 5, 0);
			break;
		case 5:
			eff = new PotionEffect(DCPotion.night_vision, 605, 0);
			break;
		case 6:
			eff = new PotionEffect(DCPotion.jump, 5, 0);
			break;
		}

		if (eff != null) {
			player.addPotionEffect(eff);
		}
	}

	@Override
	public float reduceHeat(int meta) {
		return meta == 1 ? 2.0F : 0.0F;
	}

	@Override
	public float reduceCold(int meta) {
		return meta == 0 ? 2.0F : 0.0F;
	}

	@Override
	public float reduceDamage(DamageSource source, ItemStack charm) {
		if (charm.getItemDamage() == 2) {
			return 1.0F;
		}
		return 0.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		String s = "";
		int meta = stack.getItemDamage();
		tooltip.add(TextFormatting.YELLOW.toString() + I18n.translateToLocal("dcs.tip.pendant." + meta));
	}
}
