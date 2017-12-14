package defeatedcrow.hac.magic.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.api.magic.IJewelAmulet;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAmulet extends DCItem implements IJewelAmulet {

	private final int maxMeta;

	private static String[] names = {
			"crystal", /* 状態異常耐性 */
			"rose", /* 気候・火炎耐性 */
			"black", /* 反撃 */
			"star",
			/* 復活 */ };

	public ItemAmulet(int max) {
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
		String s = "items/equip/amulet_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		String s = "";
		int meta = stack.getMetadata();
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.translateToLocal("dcs.comment.amulet." + meta));
		} else {
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.translateToLocal("dcs.tip.amulet." + meta));
			tooltip.add(TextFormatting.RESET.toString() + I18n.translateToLocal("dcs.tip.shift"));
		}
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		if (target == null || DCUtil.isEmpty(stack))
			return false;

		if (!(target instanceof EntityPlayer)) {
			ItemStack off = target.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
			ItemStack ret = stack.copy();
			if (DCUtil.isEmpty(off)) {
				target.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ret);
				DCUtil.reduceAndDeleteStack(stack, 1);
				return true;
			} else {
				if (!target.worldObj.isRemote) {
					target.entityDropItem(off, 0.25F);
				}
				target.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ret);
				DCUtil.reduceAndDeleteStack(stack, 1);
				return true;
			}
		}
		return false;
	}

	/* item damage */

	public int getNBTDamage(ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}

			if (tag.hasKey("dcs.itemdam")) {
				int d = tag.getInteger("dcs.itemdam");
				return d;
			}
		}
		return 0;
	}

	/* 効果 */

	@Override
	public ItemStack consumeCharmItem(ItemStack stack) {
		return stack;
	}

	@Override
	public void constantEffect(EntityLivingBase owner, ItemStack charm) {
		if (owner != null && !DCUtil.isEmpty(charm) && !owner.worldObj.isRemote) {
			int meta = charm.getMetadata();
			if (meta == 0) {
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
			if (meta == 1) {
				if (owner.isBurning()) {
					owner.extinguish();
				}
				if (owner.getAir() < 300) {
					owner.setAir(300);
				}
				owner.fallDistance = 0.0F;
			}
		}
	}

	@Override
	public void setActive(ItemStack charm, boolean flag) {}

	@Override
	public boolean isActive(ItemStack charm) {
		return true;
	}

	@Override
	public float reduceDamage(DamageSource source, ItemStack charm) {
		int meta = charm.getItemDamage();
		if (meta == 1) {
			if (source instanceof DamageSourceClimate || source.isFireDamage() || source == DamageSource.cactus)
				return 2.0F;
		} else if (meta == 2) {
			if (source instanceof EntityDamageSource)
				return 2.0F;
			else if (source.isExplosion())
				return 10.0F;
		}
		return 0F;
	}

	@Override
	public boolean onDiffence(DamageSource source, EntityLivingBase target, float damage, ItemStack charm) {
		int meta = charm.getItemDamage();
		if (meta == 2 && damage >= 1.0F) {
			if (source instanceof EntityDamageSource && source.getEntity() instanceof EntityLiving) {
				EntityLiving entity = (EntityLiving) source.getEntity();
				entity.attackEntityFrom(DamageSource.magic, damage * 0.5F);
				return true;
			}
		}
		return false;
	}

	@Override
	public float increaceDamage(EntityLivingBase target, ItemStack charm) {
		int meta = charm.getMetadata();
		if (meta == 2) {
			if (target.isEntityUndead() || target instanceof EntityEnderman)
				return 2.0F;
		}
		return 0.0F;
	}

	@Override
	public boolean onAttacking(EntityLivingBase owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		int meta = charm.getItemDamage();
		return false;
	}

}
