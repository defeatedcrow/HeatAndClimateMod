package defeatedcrow.hac.magic.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.plugin.baubles.CharmItemBase;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.worldgen.OreGenPos;
import defeatedcrow.hac.main.worldgen.OreGenPos.OreVein;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorRing2 extends CharmItemBase {

	private final int maxMeta;

	/*
	 * U: インベントリ開示
	 * G: 鉱脈索敵
	 * R: 攻撃速度上昇
	 * B: ドロップ増加
	 * W: 悪性ポーション・炎上解除
	 */
	private static String[] names = { "u2", "g2", "r2", "b2", "w2" };

	public ItemColorRing2() {
		super();
		maxMeta = 4;
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
		String s = "items/magic/ring_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public CharmType getCharmType(int meta) {
		switch (meta) {
		case 0:
			return CharmType.SPECIAL;
		case 1:
			return CharmType.KEY;
		case 2:
			return CharmType.CONSTANT;
		case 3:
			return CharmType.ATTACK;
		case 4:
			return CharmType.CONSTANT;
		default:
			return CharmType.SPECIAL;
		}
	}

	@Override
	public MagicType getType(int meta) {
		return MagicType.RING;
	}

	@Override
	public MagicColor getColor(int meta) {
		switch (meta) {
		case 0:
			return MagicColor.BLUE;
		case 1:
			return MagicColor.GREEN;
		case 2:
			return MagicColor.RED;
		case 3:
			return MagicColor.BLACK;
		case 4:
			return MagicColor.WHITE;
		default:
			return MagicColor.NONE;
		}
	}

	@Override
	public float reduceDamage(DamageSource source, ItemStack charm) {
		return 0;
	}

	@Override
	public boolean onDiffence(DamageSource source, EntityLivingBase owner, float damage, ItemStack charm) {
		return false;
	}

	@Override
	public float increaceDamage(EntityLivingBase target, ItemStack charm) {
		return 1F;
	}

	@Override
	public boolean onAttacking(EntityLivingBase owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		if (getColor(charm.getItemDamage()) == MagicColor.BLACK && target != null && !owner.world.isRemote) {
			int r = itemRand.nextInt(3);
			if (r > 0) {
				EntityXPOrb orb = new EntityXPOrb(owner.world, target.posX, target.posY, target.posZ, r);
				owner.world.spawnEntity(orb);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean onPlayerAttacking(EntityPlayer owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		return onAttacking(owner, target, source, damage, charm);
	}

	@Override
	public boolean onToolUsing(EntityLivingBase owner, BlockPos pos, IBlockState state, ItemStack charm) {
		return false;
	}

	@Override
	public void constantEffect(EntityLivingBase owner, ItemStack charm) {
		int l = charm.getCount() - 1;
		if (l < 0) {
			l = 0;
		}
		if (getColor(charm.getItemDamage()) == MagicColor.RED) {
			owner.addPotionEffect(new PotionEffect(MainInit.nimble, 205, l));
		} else if (getColor(charm.getItemDamage()) == MagicColor.WHITE) {
			if (owner.isBurning()) {
				owner.extinguish();
			}
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
	}

	@Override
	public boolean onUsing(EntityPlayer owner, ItemStack charm) {
		if (getColor(charm.getItemDamage()) == MagicColor.GREEN) {
			int l = charm.getCount();
			int x = MathHelper.floor(owner.posX);
			int y = MathHelper.floor(owner.posY);
			int z = MathHelper.floor(owner.posZ);
			BlockPos p = new BlockPos(x, y, z);
			ChunkPos c = new ChunkPos(p);
			List<OreVein> list = Lists.newArrayList();
			List<ChunkPos> list2 = Lists.newArrayList();
			for (int i = -l; i < l + 1; i++) {
				for (int j = -l; j < l + 1; j++) {
					OreVein[] veins = OreGenPos.INSTANCE.getVeins(c.x + i, c.z + j, owner.world);
					if (veins != null) {
						for (OreVein v : veins) {
							if (v != null)
								list.add(v);
						}
					}
				}
			}

			if (!list.isEmpty()) {
				owner.sendMessage(new TextComponentString("== Ore vein detected! =="));
				for (OreVein v : list) {
					if (v.type != EnumVein.HIGH_RED)
						owner.sendMessage(new TextComponentString("* Type:" + v.type.name() + ", " + v.pos
								.getX() + ", " + v.pos.getY() + ", " + v.pos.getZ() + " *"));
				}
			} else {
				owner.sendMessage(new TextComponentString("== No ore vein detected in this chank =="));
			}
			return true;
		}
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
		if (meta == 0 || meta == 1) {
			tooltip.add(TextFormatting.BOLD.toString() + "PLAYER ONLY");
		}
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.color_ring2." + meta));
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.allcharm"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_ring2." + meta));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
	}

}
