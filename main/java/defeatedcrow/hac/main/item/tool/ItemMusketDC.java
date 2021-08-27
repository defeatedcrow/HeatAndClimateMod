package defeatedcrow.hac.main.item.tool;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.entity.EntityBulletDC;
import defeatedcrow.hac.main.entity.EntityCrowBullet;
import defeatedcrow.hac.main.entity.EntityExtinctionBullet;
import defeatedcrow.hac.main.entity.EntityGhostBullet;
import defeatedcrow.hac.main.entity.EntityIronBullet;
import defeatedcrow.hac.main.entity.EntityLightBullet;
import defeatedcrow.hac.main.entity.EntityShotgunBullet;
import defeatedcrow.hac.main.entity.EntitySilverBullet;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemMusketDC extends ItemBow implements ITexturePath {

	public ItemMusketDC() {
		this.maxStackSize = 1;
		this.setMaxDamage(512);
		this.setFull3D();
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/musket";
	}

	public ItemStack findAmmo(EntityPlayer player) {
		if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
			return player.getHeldItem(EnumHand.OFF_HAND);
		else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
			return player.getHeldItem(EnumHand.MAIN_HAND);
		else {
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (this.isArrow(itemstack))
					return itemstack;
			}

			return ItemStack.EMPTY;
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			if (tag.hasKey("bulletType"))
				return EnumAction.BOW;
			else
				return EnumAction.NONE;
		}
		return EnumAction.NONE;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			if (tag.hasKey("bulletType")) {
				player.setActiveHand(hand);
				return new ActionResult(EnumActionResult.SUCCESS, stack);
			} else {
				if (MainCoreConfig.sound_gun > 0D)
					worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.NEUTRAL, (float) MainCoreConfig.sound_gun, 1.0F / (itemRand
							.nextFloat() * 0.4F + 1.2F) + 0.5F);
				ItemStack ammo = this.findAmmo(player);
				boolean flag = !DCUtil.isEmpty(ammo);
				if (EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0) {
					tag.setInteger("bulletType", 1);
					stack.setTagCompound(tag);
					return new ActionResult(EnumActionResult.SUCCESS, stack);
				} else if (flag) {
					int meta = ammo.getItemDamage();
					tag.setInteger("bulletType", meta);
					stack.setTagCompound(tag);
					if (!player.capabilities.isCreativeMode) {
						DCUtil.reduceStackSize(ammo, 1);
					}
					return new ActionResult(EnumActionResult.SUCCESS, stack);
				} else
					return new ActionResult(EnumActionResult.FAIL, stack);
			}
		}
		return new ActionResult(EnumActionResult.FAIL, stack);
	}

	@Override
	protected boolean isArrow(@Nullable ItemStack stack) {
		return !DCUtil.isEmpty(stack) && stack.getItem() == MainInit.cartridge && stack.getItemDamage() > 0;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase living, int timeLeft) {
		if (living instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) living;
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}

			int i = this.getMaxItemUseDuration(stack) - timeLeft;
			if (i < 0)
				return;

			if (tag.hasKey("bulletType")) {

				float f = getArrowVelocity(i);
				int type = tag.getInteger("bulletType");

				if (f >= 0.0D) {
					boolean flag1 = player.capabilities.isCreativeMode;

					EntityBulletDC entityarrow = new EntityIronBullet(world, player);
					float speed = 5.0F;

					switch (ItemBullets.getType(type)) {
					case BOLT:
						break;
					case GHOST:
						entityarrow = new EntityGhostBullet(world, player);
						break;
					case NORMAL:
						break;
					case SHOT:
						entityarrow = new EntityShotgunBullet(world, player);
						speed = 2.0F;
						break;
					case SILVER:
						entityarrow = new EntitySilverBullet(world, player);
						break;
					case LIGHT:
						entityarrow = new EntityLightBullet(world, player);
						break;
					case EXTINCTION:
						entityarrow = new EntityExtinctionBullet(world, player);
						break;
					case CROW:
						entityarrow = new EntityCrowBullet(world, player);
						break;
					default:
						break;

					}

					entityarrow.setAim(player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 0.1F);

					if (!world.isRemote) {
						int power = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
						int punch = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
						int flame = EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack);

						if (power > 0) {
							double pow = (power * 2) + 5.0D;
							pow *= 0.2D;
							double damage = entityarrow.getDamage() * pow;
							entityarrow.setDamage(damage);
						}

						if (punch > 0) {
							entityarrow.setKnockbackStrength(punch);
						}

						if (flame > 0) {
							entityarrow.setFire(100);
						}

						stack.damageItem(1, player);
						stack.getTagCompound().removeTag("bulletType");

						world.spawnEntity(entityarrow);
					}

					world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_FIREWORK_BLAST, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand
							.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

					player.addStat(StatList.getObjectUseStats(this));
				}
			}
		}
	}

}
