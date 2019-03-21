package defeatedcrow.hac.magic.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.plugin.baubles.CharmItemBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * インベントリの最上段に入れていると効果のあるアクセサリー類。
 * ペンダントは常時系or防御系効果を持ち、耐久値の概念がない。
 */
@Deprecated
public class ItemMagicalPendant extends CharmItemBase implements IJewelCharm {

	private final int maxMeta;

	/*
	 * 0: 青カルセドニー
	 * 1: 赤カルセドニー
	 * 2: 白カルセドニー
	 * 3: 石英
	 * 4: サファイア
	 * 5: マラカイト
	 * 6: セレスタイト
	 * 7: ハマグリ
	 * 8: ラピス
	 * 9: ダイヤ
	 * 10: ショール
	 * 11: 蛇紋石
	 * 12: カンラン石
	 * 13: アルマンディン
	 * 14: エレスチャル
	 * 15: ルチル
	 * 16: ビスマス,
	 * 17: 翡翠
	 * 18: 月長石
	 * 19: リシア輝石
	 */
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
			"moonstone", /* 矢の弾速UP */
			"kunzite"
			/* 攻撃抑制 */ };

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
	public CharmType getCharmType(int meta) {
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
		case 19:
			return CharmType.ATTACK;
		default:
			return CharmType.CONSTANT;
		}
	}

	@Override
	public MagicType getType(int meta) {
		return MagicType.PENDANT;
	}

	@Override
	public MagicColor getColor(int meta) {
		return MagicColor.NONE;
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
	public boolean onAttacking(EntityLivingBase owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		int meta = charm.getMetadata();
		if (meta == 12 && target != null && !owner.world.isRemote) {
			int r = 2 + itemRand.nextInt(3);
			EntityXPOrb orb = new EntityXPOrb(owner.world, target.posX, target.posY, target.posZ, r);
			owner.world.spawnEntity(orb);
		}
		if (meta == 19) {
			damage = 0F;
		}
		return false;
	}

	@Override
	public boolean onPlayerAttacking(EntityPlayer owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		return this.onAttacking(owner, target, source, damage, charm);
	}

	@Override
	public boolean onDiffence(DamageSource source, EntityLivingBase target, float damage, ItemStack charm) {
		return false;
	}

	@Override
	public boolean onToolUsing(EntityLivingBase owner, BlockPos pos, IBlockState state, ItemStack charm) {
		int meta = charm.getMetadata();
		if (owner.isSneaking() && !owner.world.isRemote && state != null) {
			if (meta == 8) {
				AxisAlignedBB aabb = new AxisAlignedBB((double) pos.getX() - 5, (double) pos.getY() - 2, (double) pos
						.getZ() - 5, (double) pos.getX() + 5, (double) pos.getY() + 3, (double) pos.getZ() + 5);
				List<EntityItem> drops = owner.world.getEntitiesWithinAABB(EntityItem.class, aabb);
				for (EntityItem drop : drops) {
					drop.setPosition(owner.posX, owner.posY + 0.5D, owner.posZ);
				}
			}
		}
		return false;
	}

	@Override
	public void constantEffect(EntityLivingBase owner, ItemStack charm) {
		int meta = charm.getMetadata();

		if (meta == 3) {
			List<PotionEffect> removes = new ArrayList<PotionEffect>();
			Collection<PotionEffect> target = owner.getActivePotionEffects();
			for (PotionEffect check : target) {
				Potion p = check.getPotion();
				if (p != null && p.isBadEffect()) {
					removes.add(check);
				}
			}
			for (PotionEffect ret : removes) {
				owner.removePotionEffect(ret.getPotion());
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
			owner.addPotionEffect(eff);
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
				ItemStack off = owner.getHeldItemOffhand();
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
		tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.tip.pendant." + meta));
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.allcharm"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.pendant." + meta));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
	}
}
