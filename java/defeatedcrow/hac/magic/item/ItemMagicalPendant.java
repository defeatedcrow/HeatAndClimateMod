package defeatedcrow.hac.magic.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
			"clam", /* 死亡時ワープ */
			"lapis", /* 範囲回収 */
			"diamond", /* 採掘速度増加 */
			"schorl", /* 加速 */
			"serpentine", /* 透明化 */
			"olivine", /* EXP増加 */
			"almandine", /* ノックバック防止 */
			"elestial", /* カルセドニー投げ */
			"rutile", /* 爆発耐性 */
			"bismuth", /* ツールがゆっくり回復 */
			"jadeite", /* 動物と仲良く */
			"moonstone"
			/* 矢の弾速UP */ };

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
		case 0:
		case 1:
		case 2:
		case 15:
			return CharmType.DEFFENCE;
		case 7:
		case 9:
		case 17:
		case 18:
			return CharmType.SPECIAL;
		case 8:
			return CharmType.TOOL;
		case 12:
			return CharmType.ATTACK;
		default:
			return CharmType.CONSTANT;
		}
	}

	@Override
	public float reduceDamage(DamageSource source, ItemStack charm) {
		int meta = charm.getMetadata();
		switch (meta) {
		case 0:
			return source == DamageSourceClimate.climateColdDamage ? 2.0F : 0F;
		case 1:
			return source == DamageSourceClimate.climateHeatDamage ? 2.0F : 0F;
		case 2:
			return 1.0F;
		case 10:
			return source == DamageSource.LIGHTNING_BOLT ? 5.0F : 0F;
		case 15:
			return source.isExplosion() ? 20.0F : 0F;
		default:
			return 0F;
		}
	}

	@Override
	public float increaceDamage(EntityLivingBase target, ItemStack charm) {
		return 1.0F;
	}

	@Override
	public boolean onAttacking(EntityPlayer player, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		int meta = charm.getMetadata();
		if (meta == 12 && target != null && !player.world.isRemote) {
			int r = 2 + itemRand.nextInt(3);
			EntityXPOrb orb = new EntityXPOrb(player.world, target.posX, target.posY, target.posZ, r);
			player.world.spawnEntity(orb);
		}
		return false;
	}

	@Override
	public boolean onDiffence(DamageSource source, EntityLivingBase target, float damage, ItemStack charm) {
		return false;
	}

	@Override
	public boolean onToolUsing(EntityPlayer player, BlockPos pos, IBlockState state, ItemStack charm) {
		int meta = charm.getMetadata();
		if (player.isSneaking() && !player.world.isRemote && state != null) {
			if (meta == 8) {
				AxisAlignedBB aabb = new AxisAlignedBB((double) pos.getX() - 5, (double) pos.getY() - 2,
						(double) pos.getZ() - 5, (double) pos.getX() + 5, (double) pos.getY() + 3,
						(double) pos.getZ() + 5);
				List<EntityItem> drops = player.world.getEntitiesWithinAABB(EntityItem.class, aabb);
				for (EntityItem drop : drops) {
					drop.setPosition(player.posX, player.posY + 0.5D, player.posZ);
				}
			}
		}
		return false;
	}

	@Override
	public void constantEffect(EntityPlayer player, ItemStack charm) {
		int meta = charm.getMetadata();

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
			eff = new PotionEffect(MobEffects.WATER_BREATHING, 205, 0);
			break;
		case 1:
			eff = new PotionEffect(MobEffects.FIRE_RESISTANCE, 205, 0);
			break;
		case 5:
			eff = new PotionEffect(MobEffects.NIGHT_VISION, 605, 0);
			break;
		case 6:
			eff = new PotionEffect(MobEffects.JUMP_BOOST, 205, 0);
			break;
		case 10:
			eff = new PotionEffect(MobEffects.SPEED, 205, 0);
			break;
		case 11:
			eff = new PotionEffect(MobEffects.INVISIBILITY, 205, 0);
			break;
		case 13:
			eff = new PotionEffect(MainInit.heavyboots, 205, 1);
		}

		if (eff != null) {
			player.addPotionEffect(eff);
		}

		if (meta == 16) {
			int cool = 40;
			NBTTagCompound tag = charm.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			if (tag.hasKey("CharmCooldown")) {
				cool = tag.getInteger("CharmCooldown");
			}
			if (cool < 0) {
				ItemStack off = player.getHeldItemOffhand();
				if (!DCUtil.isEmpty(off) && off.getItem().isDamageable()) {
					int dam = off.getItemDamage();
					if (dam > 0) {
						dam--;
						off.setItemDamage(dam);
					}
				}
				tag.setInteger("CharmCooldown", 40);
			} else {
				cool--;
				tag.setInteger("CharmCooldown", cool);
			}
			charm.setTagCompound(tag);

		}
	}

	@Override
	public boolean onUsing(EntityPlayer player, ItemStack charm) {
		return false;
	}

	@Override
	public boolean isActive(ItemStack charm) {
		return true;
	}

	@Override
	public void setActive(ItemStack charm, boolean flag) {}

	@Override
	public ItemStack consumeCharmItem(ItemStack stack) {
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		String s = "";
		int meta = stack.getMetadata();
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.translateToLocal("dcs.comment.pendant." + meta));
		} else {
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.translateToLocal("dcs.tip.pendant." + meta));
			tooltip.add(TextFormatting.RESET.toString() + I18n.translateToLocal("dcs.tip.shift"));
		}
	}
}
