package defeatedcrow.hac.magic.event;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.ClimateCalculateEvent;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.WorldHeatTierEvent;
import defeatedcrow.hac.api.magic.IJewel;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.recipe.ICrusherRecipe;
import defeatedcrow.hac.api.recipe.IMillRecipe;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.magic.PictureList;
import defeatedcrow.hac.magic.entity.EntityBlackDog;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.item.tool.ItemScytheDC;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;

public class MagicCommonEvent {

	@SubscribeEvent
	public void onAttackEvent(LivingAttackEvent event) {
		EntityLivingBase target = event.getEntityLiving();
		DamageSource source = event.getSource();
		if (target != null && source != null) {
			if (source.getTrueSource() instanceof EntityTameable) {
				EntityTameable living = (EntityTameable) source.getTrueSource();

				boolean amu = DCUtil.hasCharmItem(living, new ItemStack(MagicInit.colorBadge, 1, 3));
				if (amu && living.getOwner() instanceof EntityPlayer) {
					target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) living.getOwner()), event
							.getAmount());
					event.setCanceled(true);
				} else if (DCUtil.hasCharmItem(living, new ItemStack(MagicInit.colorPendant, 1,
						4)) && !(target instanceof IMob)) {
					event.setCanceled(true);
					return;
				}
			} else if (source.getTrueSource() instanceof EntityLivingBase) {
				EntityLivingBase owner = (EntityLivingBase) source.getTrueSource();
				float eff = MainUtil.magicSuitEff(owner);
				if (owner != null) {
					if (DCUtil.hasCharmItem(owner, new ItemStack(MagicInit.colorBadge, 1, 3))) {
						// black badge
						target.attackEntityFrom(DamageSource.causeMobDamage(target), event.getAmount());
						event.setCanceled(true);
					} else if (DCUtil.hasCharmItem(owner, new ItemStack(MagicInit.colorPendant, 1,
							4)) && !(target instanceof IMob)) {
						// white pendant
						event.setCanceled(true);
						return;
					}
					if (getOffhandJewelColor(owner) == MagicColor.GREEN) {
						// green-white gauntlet
						float healamo = event.getAmount() * 0.25F * eff;
						owner.heal(healamo);
						event.getSource().setDamageBypassesArmor();
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onInteract(PlayerInteractEvent.EntityInteractSpecific event) {
		EntityPlayer player = event.getEntityPlayer();
		Entity target = event.getTarget();
		if (player != null && target != null) {
			if (player.isSneaking() && !player.world.isRemote && DCUtil.hasCharmItem(player, new ItemStack(
					MagicInit.colorRing2, 1, 0))) {
				if (target instanceof EntityLiving) {
					player.openGui(ClimateMain.instance, target.getEntityId(), player.world, target.getPosition()
							.getX(), target.getPosition().getY(), target.getPosition().getZ());
					event.setCancellationResult(EnumActionResult.SUCCESS);
				}
			} else if (getOffhandJewelColor(event.getEntityLiving()) == MagicColor.WHITE) {
				if (target instanceof EntityLiving) {
					if (target.getRidingEntity() != player) {
						target.startRiding(player, true);
					}
				}
			}
		}
	}

	private void playTameEffect(Entity animal) {
		EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;

		for (int i = 0; i < 7; ++i) {
			double d0 = animal.getEntityWorld().rand.nextGaussian() * 0.02D;
			double d1 = animal.getEntityWorld().rand.nextGaussian() * 0.02D;
			double d2 = animal.getEntityWorld().rand.nextGaussian() * 0.02D;
			animal.getEntityWorld().spawnParticle(enumparticletypes, animal.posX + animal.getEntityWorld().rand
					.nextFloat() * animal.width * 2.0F - animal.width, animal.posY + 0.5D + animal.getEntityWorld().rand
							.nextFloat() * animal.height, animal.posZ + animal.getEntityWorld().rand
									.nextFloat() * animal.width * 2.0F - animal.width, d0, d1, d2);
		}
	}

	@SubscribeEvent
	public void afterWarpDimEvent(PlayerChangedDimensionEvent event) {
		EntityPlayer player = event.player;
		if (player != null) {
			ItemStack charm = MainUtil.getCharmItem(player, new ItemStack(MagicInit.colorBadge, 1, 1));
			if (!DCUtil.isEmpty(charm)) {
				int dim = player.world.provider.getDimension();
				String dimName = player.world.provider.getDimensionType().getName();
				int x = MathHelper.floor(player.posX);
				int y = MathHelper.floor(player.posY);
				int z = MathHelper.floor(player.posZ);
				NBTTagCompound tag = charm.getTagCompound();
				if (tag == null) {
					tag = new NBTTagCompound();
				}
				tag.setString("dcs.portal.dimname", dimName);
				tag.setInteger("dcs.portal.dim", dim);
				tag.setInteger("dcs.portal.x", x);
				tag.setInteger("dcs.portal.y", y);
				tag.setInteger("dcs.portal.z", z);
				charm.setTagCompound(tag);
			}
		}
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		DamageSource source = event.getSource();
		float dam = event.getAmount();
		if (living == null)
			return;

		if (!(living instanceof EntityPlayer) && dam >= living.getHealth()) {
			if (DCUtil.hasCharmItem(living, new ItemStack(MagicInit.colorBadge, 1, 1))) {
				// DCLogger.debugInfoLog("on amulet process");
				living.fallDistance = 0.0F;
				living.setHealth(living.getMaxHealth() * 0.5F);
				living.world.playSound(null, living
						.getPosition(), SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.PLAYERS, 1.0F, 0.75F);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onDrop(LivingDropsEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		DamageSource source = event.getSource();
		int level = event.getLootingLevel();
		if (living == null)
			return;

		if (!living.world.isRemote) {
			int lu1 = 0;
			int lb2 = 0;
			boolean blue = false;
			if (source.getTrueSource() instanceof EntityLivingBase) {
				EntityLivingBase liv2 = (EntityLivingBase) source.getTrueSource();
				int eff = MathHelper.floor(MainUtil.magicSuitEff(liv2) * 2) - 1;
				lu1 = MainUtil.getCharmLevel(liv2, new ItemStack(MagicInit.colorRing, 1, 0)) * eff;
				lb2 = MainUtil.getCharmLevel(liv2, new ItemStack(MagicInit.colorRing2, 1, 3)) * eff;
				blue = getOffhandJewelColor(liv2) == MagicColor.BLUE;
			} else {
				return;
			}
			int m = 1 + (lu1 + level) * 5;
			if (living.world.rand.nextInt(100) < m) {
				int meta = getDropMeta(living);
				ItemStack item1 = new ItemStack(MagicInit.colorDrop, 1, meta);
				EntityItem drop = new EntityItem(living.world, living.posX, living.posY, living.posZ, item1);
				event.getDrops().add(drop);
			}
			int m2 = lb2 * 10;
			if (living.world.rand.nextInt(100) < m2) {
				if (!event.getDrops().isEmpty()) {
					int i = event.getDrops().size();
					int i2 = living.world.rand.nextInt(i);
					event.getDrops().get(i2).getItem().grow(1);
				}
			}
			if (blue && living.world.rand.nextInt(100) < 25) {
				ItemStack head = getDropSkull(living);
				if (!head.isEmpty()) {
					EntityItem drop2 = new EntityItem(living.world, living.posX, living.posY, living.posZ, head);
					event.getDrops().add(drop2);
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockDrop(BlockEvent.HarvestDropsEvent event) {
		if (event.getHarvester() != null) {
			/* 粉砕 */
			if (getOffhandJewelColor(event.getHarvester()) == MagicColor.BLACK) {
				ItemStack off = event.getHarvester().getHeldItem(EnumHand.OFF_HAND);
				float eff = MainUtil.magicSuitEff(event.getHarvester());
				List<ItemStack> nList = Lists.newArrayList();
				boolean flag = false;
				for (ItemStack i : event.getDrops()) {
					if (!DCUtil.isEmpty(i)) {
						ICrusherRecipe cr = null;
						if (eff > 1.0F && ModuleConfig.machine_advanced) {
							cr = RecipeAPI.registerCrushers.getRecipe(i, new ItemStack(MachineInit.rotaryBlade));
							ICrusherRecipe cr2 = RecipeAPI.registerCrushers.getRecipe(i, new ItemStack(
									MachineInit.rotaryBlade, 1, 1));
							if (eff >= 2.0F && cr2 != null) {
								cr = cr2;
							}
						}
						if (cr != null) {
							ItemStack o1 = cr.getOutput();
							if (!DCUtil.isEmpty(o1))
								nList.add(o1);
							ItemStack o2 = cr.getSecondary();
							if (!DCUtil.isEmpty(o2) && event.getWorld().rand.nextFloat() < cr
									.getSecondaryChance() * eff)
								nList.add(o2);
							ItemStack o3 = cr.getSecondary();
							if (!DCUtil.isEmpty(o3) && event.getWorld().rand.nextFloat() < cr.getTertialyChance() * eff)
								nList.add(o3);
							flag = true;
						} else {
							IMillRecipe recipe = RecipeAPI.registerMills.getRecipe(i);
							if (recipe != null) {
								ItemStack o1 = recipe.getOutput();
								if (!DCUtil.isEmpty(o1))
									nList.add(o1);
								ItemStack o2 = recipe.getSecondary();
								if (!DCUtil.isEmpty(o2) && event.getWorld().rand.nextFloat() < recipe
										.getSecondaryChance() * eff)
									nList.add(o2);
								flag = true;
							} else {
								nList.add(i.copy());
							}
						}
					}
				}
				event.getDrops().clear();
				event.getDrops().addAll(nList);
			}
			/* 精錬 */
			if (DCUtil.hasCharmItem(event.getHarvester(), new ItemStack(MagicInit.colorPendant2, 1, 2))) {
				List<ItemStack> nList = Lists.newArrayList();
				for (ItemStack i : event.getDrops()) {
					if (!DCUtil.isEmpty(i)) {
						ItemStack burnt = FurnaceRecipes.instance().getSmeltingResult(i);
						if (burnt.isEmpty()) {
							nList.add(i.copy());
						} else {
							int d = burnt.getCount() * i.getCount();
							burnt.setCount(d);
							nList.add(burnt.copy());
						}
					}
				}
				event.getDrops().clear();
				event.getDrops().addAll(nList);
			}

		}
	}

	static int getDropMeta(EntityLivingBase living) {
		if (living
				.getCreatureAttribute() == EnumCreatureAttribute.ILLAGER || living instanceof EntityEnderman || living instanceof EntityVillager) {
			return 4;
		} else if (living.isEntityUndead() || living.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
			return 3;
		} else if (living instanceof EntityCreeper || living.isImmuneToFire()) {
			return 2;
		} else if (living instanceof EntitySlime || living instanceof EntityGuardian || living instanceof EntityShulker) {
			return 0;
		} else {
			return 1;
		}
	}

	static ItemStack getDropSkull(EntityLivingBase living) {
		if (living instanceof EntityWitherSkeleton) {
			return new ItemStack(Items.SKULL, 1, 1);
		} else if (living instanceof EntitySkeleton) {
			return new ItemStack(Items.SKULL, 1, 0);
		} else if (living instanceof EntityZombie) {
			return new ItemStack(Items.SKULL, 1, 2);
		} else if (living instanceof EntityCreeper) {
			return new ItemStack(Items.SKULL, 1, 4);
		} else {
			return ItemStack.EMPTY;
		}
	}

	/* gauntlet */

	@SubscribeEvent
	public void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
		EntityPlayer player = event.getEntityPlayer();
		if (ClimateCore.isDebug && player != null && player.isSprinting()) {
			if (getOffhandJewelColor(event.getEntityLiving()) == MagicColor.BLUE && event.getItemStack()
					.getItem() instanceof ItemScytheDC) {
				double x = player.getForward().x * 0.5D + player.motionX;
				double y = 0.05D;
				double z = player.getForward().z * 0.5D + player.motionZ;
				player.motionX = x;
				player.motionY = y;
				player.motionZ = z;
			}
		}
	}

	// 確定Crit
	@SubscribeEvent
	public void onCrit(CriticalHitEvent event) {
		if (getOffhandJewelColor(event.getEntityPlayer()) == MagicColor.BLUE && event.getEntityPlayer()
				.getHeldItemMainhand().getItem() instanceof ItemScytheDC) {
			event.setDamageModifier(1.8F);
			event.setResult(Result.ALLOW);
		}
	}

	int count = 60;

	@SubscribeEvent
	public void onLiving(LivingEvent.LivingUpdateEvent event) {
		if (getOffhandJewelColor(event.getEntityLiving()) == MagicColor.RED) {
			if (event.getEntityLiving().collidedHorizontally) {
				event.getEntityLiving().motionY = 0.2D;
			} else if (isCollidedBlock(event.getEntityLiving())) {
				if (event.getEntityLiving().isSneaking()) {
					event.getEntityLiving().motionY = 0.0D;
					event.getEntityLiving().motionX *= 0.5D;
					event.getEntityLiving().motionZ *= 0.5D;
				}
			}
			event.getEntityLiving().fallDistance = 0F;
		} else if (getOffhandJewelColor(event.getEntityLiving()) == MagicColor.WHITE && event.getEntityLiving()
				.isSneaking()) {
			if (event.getEntityLiving().isBeingRidden()) {
				event.getEntityLiving().removePassengers();
			}
		}
		if (event.getEntityLiving() != null && !event.getEntityLiving().world.isRemote) {
			BlockPos pos = event.getEntityLiving().getPosition();
			int cx = pos.getX() >> 4;
			int cz = pos.getZ() >> 4;
			ChunkPos chunk = new ChunkPos(cx, cz);
			if (PictureList.INSTANCE.hasColor(chunk, MagicColor.GREEN) && event
					.getEntityLiving() instanceof EntityPlayer) {
				event.getEntityLiving().addPotionEffect(new PotionEffect(MainInit.bird, 205, 0));
			}
			if (PictureList.INSTANCE.hasColor(chunk, MagicColor.RED) && !(event.getEntityLiving() instanceof IMob)) {
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.HASTE, 205, 0));
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 205, 0));
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 205, 0));
			}

			if (event.getEntityLiving() instanceof IMob) {
				if (event.getEntityLiving().getTags().contains("blackdog")) {
					EntityBlackDog dog = new EntityBlackDog(event.getEntityLiving().getEntityWorld());
					dog.copyLocationAndAnglesFrom(event.getEntityLiving());
					event.getEntityLiving().getEntityWorld().spawnEntity(dog);
					event.getEntityLiving().setDead();
				}
			}

			// 定期的に絵画の存在チェック
			if (event.getEntityLiving() instanceof EntityPlayer) {
				if (count < 0) {
					PictureList.INSTANCE.checkList(event.getEntityLiving().getEntityWorld());
				}
				count = 60;
			} else {
				count--;
			}
		}
	}

	boolean isCollidedBlock(EntityLivingBase living) {// プレイヤー周囲ブロックへのコリジョンチェック
		World world = living.getEntityWorld();
		EnumFacing f = living.getHorizontalFacing();
		AxisAlignedBB bb = living.getEntityBoundingBox().grow(0.1D, 0, 0.1D);
		return !world.getCollisionBoxes(living, bb).isEmpty();
	}

	public static MagicColor getOffhandJewelColor(EntityLivingBase player) {
		if (player == null || DCUtil.isEmpty(player.getHeldItem(EnumHand.OFF_HAND)))
			return MagicColor.NONE;

		ItemStack held = player.getHeldItem(EnumHand.OFF_HAND);
		if (held.getItem() instanceof IJewel) {
			return ((IJewel) held.getItem()).getColor(held.getItemDamage());
		}

		return MagicColor.NONE;
	}

	/* pictures */

	@SubscribeEvent
	public void onSpawn(LivingSpawnEvent.CheckSpawn event) {
		EntityLivingBase living = event.getEntityLiving();
		if (living instanceof IMob) {
			int cx = (int) event.getX() >> 4;
			int cz = (int) event.getZ() >> 4;
			ChunkPos chunk = new ChunkPos(cx, cz);
			if (!event.getEntityLiving().getEntityWorld().isDaytime() && !event.getEntityLiving()
					.isInWater() && PictureList.INSTANCE.hasColor(chunk, MagicColor.BLACK)) {
				if (event.getWorld().rand.nextInt(100) < 50 || event.getEntityLiving() instanceof EntityCreeper) {
					event.getEntityLiving().addTag("blackdog");
				} else {
					event.setResult(Result.DENY);
				}
			} else if (PictureList.INSTANCE.hasColor(chunk, MagicColor.WHITE)) {
				event.setResult(Result.DENY);
			}
		}
	}

	@SubscribeEvent
	public void onCalcClimate(ClimateCalculateEvent event) {
		BlockPos pos = event.getPos();
		int cx = pos.getX() >> 4;
		int cz = pos.getZ() >> 4;
		ChunkPos chunk = new ChunkPos(cx, cz);
		if (PictureList.INSTANCE.hasColor(chunk, MagicColor.GREEN)) {
			IClimate old = event.currentClimate();
			if (old.getAirflow() != DCAirflow.TIGHT) {
				event.setNewClimate(old.addAirTier(1));
				event.setResult(Result.ALLOW);
			}
		}
	}

	@SubscribeEvent
	public void onBiomeTemp(WorldHeatTierEvent event) {
		BlockPos pos = event.getPos();
		int cx = pos.getX() >> 4;
		int cz = pos.getZ() >> 4;
		ChunkPos chunk = new ChunkPos(cx, cz);
		if (PictureList.INSTANCE.hasColor(chunk, MagicColor.BLUE)) {
			DCHeatTier old = event.currentClimate();
			event.setNewClimate(old.addTier(4));
			event.setResult(Result.ALLOW);
		}
	}
}
