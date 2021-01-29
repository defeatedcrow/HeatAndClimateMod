package defeatedcrow.hac.food.item.brewing;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityZombieHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAntibiotic extends DCItem {

	private final int maxMeta;

	private static String[] names = {
		"pcn",
		"cp",
		"anti_zombie",
		"poison",
		"zombie",
		"pigman",
		"mushroom",
		"mana",
		"anti_heal" };

	public ItemAntibiotic() {
		super();
		maxMeta = 8;
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
		String s = "items/food/brewing/antibiotic_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		if (!DCUtil.isEmpty(stack)) {
			boolean b = false;
			if (stack.getItemDamage() < 2) {
				if (!player.world.isRemote) {
					target.clearActivePotions();
					player.world.playEvent(null, 1027, new BlockPos(target), 0);
				}
				b = true;
			} else if (target instanceof EntityZombieVillager && stack.getItemDamage() == 2) {
				if (!player.world.isRemote) {
					EntityVillager villager = new EntityVillager(player.world);
					villager = (EntityVillager) initialize(villager, target);

					if (target.isChild()) {
						villager.setGrowingAge(-24000);
					}
					villager.setProfession(((EntityZombieVillager) target).getForgeProfession());
					villager.finalizeMobSpawn(player.world.getDifficultyForLocation(new BlockPos(
							villager)), (IEntityLivingData) null, false);
					villager.setLookingForHome();

					player.world.removeEntity(target);
					player.world.spawnEntity(villager);
					player.world.playEvent(null, 1027, new BlockPos(villager), 0);
				}
				b = true;
			} else if ((target instanceof EntityZombieHorse || target instanceof EntitySkeletonHorse) && stack
					.getItemDamage() == 2) {
				if (!player.world.isRemote) {
					EntityHorse horse = new EntityHorse(player.world);
					horse = (EntityHorse) initialize(horse, target);

					horse.onInitialSpawn(player.world.getDifficultyForLocation(new BlockPos(
							horse)), (IEntityLivingData) null);
					if (((AbstractHorse) target).isTame()) {
						horse.setOwnerUniqueId(((AbstractHorse) target).getOwnerUniqueId());
						horse.setHorseTamed(true);
					} else {
						horse.setTamedBy(player);
					}

					player.world.removeEntity(target);
					player.world.spawnEntity(horse);
					horse.playLivingSound();
					player.world.playEvent(null, 1026, new BlockPos(horse), 0);
				}
				b = true;
			} else if (target instanceof EntityPigZombie && stack.getItemDamage() == 2) {
				if (!player.world.isRemote) {
					EntityPig pig = new EntityPig(player.world);
					pig = (EntityPig) initialize(pig, target);

					player.world.removeEntity(target);
					player.world.spawnEntity(pig);
					pig.playLivingSound();
					player.world.playEvent(null, 1027, new BlockPos(pig), 0);
				}
				b = true;
			} else if (stack.getItemDamage() == 3) {
				if (!player.world.isRemote) {
					target.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 0));
					target.playSound(SoundEvents.ENTITY_PLAYER_HURT_DROWN, 1.0F, 1.0F);
				}
				b = true;
			} else if (target instanceof EntityVillager && stack.getItemDamage() == 4) {
				if (!player.world.isRemote) {
					EntityZombieVillager zombie = new EntityZombieVillager(player.world);
					zombie = (EntityZombieVillager) initialize(zombie, target);

					zombie.setChild(target.isChild());
					zombie.setForgeProfession(((EntityVillager) target).getProfessionForge());

					player.world.removeEntity(target);
					player.world.spawnEntity(zombie);
					zombie.playLivingSound();
					player.world.playEvent(null, 1026, new BlockPos(zombie), 0);
				}
				b = true;
			} else if (target instanceof EntityHorse && stack.getItemDamage() == 4) {
				if (!player.world.isRemote) {
					EntityZombieHorse zombie = new EntityZombieHorse(player.world);
					zombie = (EntityZombieHorse) initialize(zombie, target);

					if (((AbstractHorse) target).isTame()) {
						zombie.setOwnerUniqueId(((AbstractHorse) target).getOwnerUniqueId());
						zombie.setHorseTamed(true);
					} else {
						zombie.setTamedBy(player);
					}

					player.world.removeEntity(target);
					player.world.spawnEntity(zombie);
					zombie.playLivingSound();
					player.world.playEvent(null, 1026, new BlockPos(zombie), 0);
				}
				b = true;
			} else if (target instanceof EntityPig && stack.getItemDamage() == 5) {
				if (!player.world.isRemote) {
					EntityPigZombie zombie = new EntityPigZombie(player.world);
					zombie = (EntityPigZombie) initialize(zombie, target);

					zombie.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));

					player.world.removeEntity(target);
					player.world.spawnEntity(zombie);
					player.world.playEvent(null, 1026, new BlockPos(zombie), 0);
				}
				b = true;
			} else if (target instanceof EntityCow && stack.getItemDamage() == 6) {
				if (!player.world.isRemote) {
					EntityMooshroom cow = new EntityMooshroom(player.world);
					cow = (EntityMooshroom) initialize(cow, target);

					player.world.removeEntity(target);
					player.world.spawnEntity(cow);
					cow.playLivingSound();
					player.world.playEvent(null, 1027, new BlockPos(cow), 0);
				}
				b = true;
			} else if (stack.getItemDamage() == 8) {
				if (!player.world.isRemote) {
					target.addPotionEffect(new PotionEffect(MainInit.unrepair, 2400, 0));
					target.playSound(SoundEvents.ENTITY_PLAYER_HURT_DROWN, 1.0F, 1.0F);
				}
				b = true;
			}

			if (b && !player.capabilities.isCreativeMode) {
				DCUtil.reduceStackSize(stack, 1);
			}
			return true;
		}
		return super.itemInteractionForEntity(stack, player, target, hand);
	}

	public EntityLivingBase initialize(EntityLivingBase living, EntityLivingBase target) {
		living.copyLocationAndAnglesFrom(target);
		if (target.hasCustomName()) {
			living.setCustomNameTag(target.getCustomNameTag());
			living.setAlwaysRenderNameTag(target.getAlwaysRenderNameTag());
		}
		if (living instanceof EntityLiving)
			((EntityLiving) living).setNoAI(false);

		return living;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		if (!DCUtil.isEmpty(stack)) {
			int m = stack.getItemDamage();
			if (m != 0 && m != 1 && m != 7) {
				tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
				tooltip.add(I18n.format("dcs.tip.antibiotic_" + m));
			}
		}
	}

}
