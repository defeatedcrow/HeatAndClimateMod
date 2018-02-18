package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.CustomExplosion;
import defeatedcrow.hac.main.worldgen.OreGenPos;
import defeatedcrow.hac.main.worldgen.OreGenPos.OreVein;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMagicalBadge extends DCItem implements IJewelCharm {

	private final int maxMeta;

	private final float maxCount = 128;

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
			"chal_blue", /* 水属性追撃 */
			"chal_red", /* 火属性追撃 */
			"chal_white", /* ダメージ基礎値増加 */
			"crystal", /* バフ解除 */
			"sapphire", /* エンチャ本の追加ドロップ */
			"malachite", /* マーキング追撃 */
			"celestite", /* 天候制御 */
			"clam", /* ワープ */
			"lapis", /* アイテム収集範囲 */
			"diamond", /* 範囲採掘 */
			"schorl", /* サンボル */
			"serpentine", /* マークタワー設置 */
			"olivine", /* EXP結晶化 */
			"almandine", /* 敵の強制除去 */
			"elestial", /* 範囲攻撃 */
			"rutile", /* 光源設置 */
			"bismuth", /* Block遠隔使用 */
			"jadeite", /* 鉱脈索敵 */
			"moonstone", /* 攻撃対象の書き換え */
			"kunzite"
			/* 敵消去 */ };

	public ItemMagicalBadge(int max) {
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
		String s = "items/equip/badge_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public CharmType getType(int meta) {
		switch (meta) {
		case 6:
		case 7:
		case 11:
		case 12:
		case 15:
		case 16:
		case 17:
			return CharmType.KEY;
		case 8:
		case 9:
		case 19:
			return CharmType.TOOL;
		default:
			return CharmType.ATTACK;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		String s = "";
		int meta = stack.getMetadata();
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.translateToLocal("dcs.comment.badge." + meta));
		} else {
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.translateToLocal("dcs.tip.badge." + meta));
			tooltip.add(TextFormatting.RESET.toString() + I18n.translateToLocal("dcs.tip.shift"));
		}
		if (meta == 7 || meta == 16) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag != null && tag.hasKey("dcs.charm.dim")) {
				String warpDim = tag.getString("dcs.charm.dimname");
				int x = tag.getInteger("dcs.charm.x");
				int y = tag.getInteger("dcs.charm.y");
				int z = tag.getInteger("dcs.charm.z");
				tooltip.add(TextFormatting.RESET + warpDim + ", " + x + ", " + y + ", " + z);
			}
		}
	}

	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack stack = player.getHeldItem(hand);
			if (!world.isAirBlock(pos) && !DCUtil.isEmpty(stack) && stack.getItem() == this) {
				int meta = stack.getMetadata();
				if (!world.isRemote) {
					if (meta == 7 || meta == 16) {
						if (pos.getY() > 0 && pos.getY() < 254 && world.isAirBlock(pos.up())
								&& world.isAirBlock(pos.up(2))) {
							NBTTagCompound tag = stack.getTagCompound();
							if (tag == null) {
								tag = new NBTTagCompound();
							}
							String dimName = world.provider.getDimensionType().getName();
							int dim = world.provider.getDimension();
							int x = pos.getX();
							int y = meta == 7 ? pos.getY() + 1 : pos.getY();
							int z = pos.getZ();
							tag.setString("dcs.charm.dimname", dimName);
							tag.setInteger("dcs.charm.dim", dim);
							tag.setInteger("dcs.charm.x", x);
							tag.setInteger("dcs.charm.y", y);
							tag.setInteger("dcs.charm.z", z);
							stack.setTagCompound(tag);
							return EnumActionResult.SUCCESS;
						}
					}
				}
			}
		}
		return EnumActionResult.PASS;
	}

	/* item damage */

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return this.getNBTDamage(stack) > 0;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		int i = this.getNBTDamage(stack);
		return i / maxCount;
	}

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
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}

			int dam = 0;
			if (tag.hasKey("dcs.itemdam")) {
				dam = tag.getInteger("dcs.itemdam");
			}
			dam++;
			if (dam > maxCount)
				return ItemStack.EMPTY;
			else {
				tag.setInteger("dcs.itemdam", dam);
				stack.setTagCompound(tag);
				return stack;
			}
		}
		return stack;
	}

	@Override
	public void constantEffect(EntityPlayer player, ItemStack charm) {
		if (player != null && !DCUtil.isEmpty(charm) && !player.world.isRemote) {
			int meta = charm.getMetadata();
			if (meta == 6) {
				if (!player.onGround && player.isSneaking()) {
					if (player.motionY < 1.0F) {
						player.motionY += 1.0D;
					} else {
						player.motionY = 1.0D;
					}

					player.fallDistance = 0.0F;
				}
			}
		}
	}

	@Override
	public boolean onUsing(EntityPlayer player, ItemStack charm) {
		if (player != null && !DCUtil.isEmpty(charm) && !player.world.isRemote) {
			int meta = charm.getMetadata();
			if (meta == 6) {
				if (player.world.isRaining() || player.world.isThundering()) {
					WorldInfo worldinfo = player.world.getWorldInfo();
					worldinfo.setCleanWeatherTime(6000); // 5min
					worldinfo.setRainTime(0);
					worldinfo.setThunderTime(0);
					worldinfo.setRaining(false);
					worldinfo.setThundering(false);
					return true;
				}
			} else if (meta == 7 || meta == 16) {
				int dim = player.world.provider.getDimension();
				NBTTagCompound tag = charm.getTagCompound();
				if (tag != null && tag.hasKey("dcs.charm.dim")) {
					int warpDim = tag.getInteger("dcs.charm.dim");
					double x = tag.getInteger("dcs.charm.x");
					double y = tag.getInteger("dcs.charm.y");
					double z = tag.getInteger("dcs.charm.z");
					BlockPos pos = new BlockPos(x, y, z);

					if (warpDim == dim) {
						if (meta == 7) {
							player.setPositionAndUpdate(x + 0.5D, y, z + 0.5D);
							player.fallDistance = 0.0F;
							return true;

						} else {
							if (player.world.isBlockLoaded(pos)) {
								IBlockState state = player.world.getBlockState(pos);
								if (state != null && state.getMaterial() != Material.AIR) {
									state.getBlock().onBlockActivated(player.world, pos, state, player,
											EnumHand.MAIN_HAND, EnumFacing.NORTH, 0.5F, 0.5F, 0.5F);
									return true;
								}
							}
						}
					}
				}
			} else if (meta == 11) {
				int x = MathHelper.floor(player.posX);
				int y = MathHelper.floor(player.posY);
				int z = MathHelper.floor(player.posZ);
				BlockPos p = new BlockPos(x, y, z);
				IBlockState cur = player.world.getBlockState(p);
				IBlockState set = MainInit.markingPanel.getDefaultState();
				set = set.withProperty(DCState.FACING, player.getHorizontalFacing().getOpposite());
				if (cur.getMaterial().isReplaceable() && player.world.setBlockState(p, set))
					return true;
			} else if (meta == 12) {
				if (player.experienceLevel > 10) {
					ItemStack gem = new ItemStack(MagicInit.expGem);
					EntityItem drop = new EntityItem(player.world, player.posX, player.posY + 0.25D, player.posZ, gem);
					if (player.world.spawnEntity(drop)) {
						player.addExperienceLevel(-10);
						return true;
					}
				}
			} else if (meta == 15) {
				int x = MathHelper.floor(player.posX);
				int y = MathHelper.floor(player.posY);
				int z = MathHelper.floor(player.posZ);
				BlockPos p = new BlockPos(x, y + 1, z);
				IBlockState cur = player.world.getBlockState(p);
				IBlockState set = MainInit.lightOrb.getDefaultState();
				if (cur.getMaterial().isReplaceable() && player.world.setBlockState(p, set))
					return true;
			} else if (meta == 17) {
				int x = MathHelper.floor(player.posX);
				int y = MathHelper.floor(player.posY);
				int z = MathHelper.floor(player.posZ);
				BlockPos p = new BlockPos(x, y + 1, z);
				ChunkPos c = new ChunkPos(p);
				OreVein[] veins = OreGenPos.INSTANCE.getVeins(c.x, c.z, player.world);
				OreVein ret = null;
				int yd = 256;
				for (int i = 0; i < 3; i++) {
					if (veins[i] != null) {
						int dist = veins[i].pos.getY() - y;
						if (dist < 0) {
							dist *= -1;
						}
						if (dist < yd) {
							ret = veins[i];
							yd = dist;
						}
					}
				}
				if (ret != null) {
					player.sendMessage(new TextComponentString("== Nearest ore vein detected! =="));
					player.sendMessage(new TextComponentString("Type: " + ret.type.name()));
					player.sendMessage(new TextComponentString(
							"Pos: " + ret.pos.getX() + ", " + ret.pos.getY() + ", " + ret.pos.getZ()));
				} else {
					player.sendMessage(new TextComponentString("== No ore vein detected in this chank =="));
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isActive(ItemStack charm) {
		// NBTTagCompound tag = charm.getTagCompound();
		// if (tag != null && tag.hasKey("dcs.itemactive")) {
		// return tag.getBoolean("dcs.itemactive");
		// }
		return true;
	}

	@Override
	public boolean onDiffence(DamageSource source, EntityLivingBase target, float damage, ItemStack charm) {
		return false;
	}

	@Override
	public void setActive(ItemStack charm, boolean flag) {
		NBTTagCompound tag = charm.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		tag.setBoolean("dcs.itemactive", flag);
	}

	@Override
	public float reduceDamage(DamageSource source, ItemStack charm) {
		return 0F;
	}

	@Override
	public float increaceDamage(EntityLivingBase target, ItemStack charm) {
		int meta = charm.getMetadata();
		if (meta == 0) {
			if (target.isImmuneToFire() || target instanceof EntityEnderman)
				return 1.5F;
		}
		if (meta == 1) {
			if (target.isEntityUndead() || target instanceof EntityGuardian)
				return 1.5F;
		}
		if (meta == 2)
			return 1.1F;
		return 1.0F;
	}

	@Override
	public boolean onAttacking(EntityPlayer player, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		int meta = charm.getMetadata();
		if (player.isSneaking()) {
			if (meta == 3) {
				target.clearActivePotions();
				return true;
			}
			if (meta == 5) {
				target.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 2400, 0));
				return true;
			}
		}
		if (meta == 4 && this.itemRand.nextInt(20) == 0) {
			if (target instanceof IMob) {
				EnchantmentData data = this.getEnchantment(target);
				ItemStack ret = new ItemStack(Items.ENCHANTED_BOOK);
				ItemEnchantedBook.addEnchantment(ret, data);
				EntityItem drop = new EntityItem(player.world, target.posX, target.posY + 0.5D, target.posZ, ret);
				drop.setDefaultPickupDelay();
				player.world.spawnEntity(drop);
				return true;
			}
		}
		if (meta == 10) {
			if (player != null && target != null && !source.isExplosion() && source.isProjectile()) {
				CustomExplosion explosion = new CustomExplosion(player.world, player, player, target.posX,
						target.posY + 0.25D, target.posZ, 3F, CustomExplosion.Type.Silk, true);
				explosion.doExplosion();
				player.world.addWeatherEffect(new EntityLightningBolt(player.world, target.posX, target.posY - 0.25D,
						target.posZ, !player.isSneaking()));
				return true;
			}
		}
		if (meta == 13) {
			if (player != null && target != null && !source.isExplosion() && target instanceof IMob) {
				target.setDead();
				return true;
			}
		}
		if (meta == 14) {
			if (player != null && player.isSneaking() && target != null && !source.isExplosion()
					&& !source.isProjectile()) {
				AxisAlignedBB aabb = target.getEntityBoundingBox().grow(5.0D, 0D, 5.0D);
				List<EntityLiving> list = player.world.getEntitiesWithinAABB(EntityLiving.class, aabb);
				if (list.isEmpty())
					return false;
				else {
					for (EntityLiving liv : list) {
						liv.attackEntityFrom(source, damage);
					}
				}
				return true;
			}
		}
		if (meta == 18) {
			if (player != null && target instanceof EntityLiving) {
				AxisAlignedBB aabb = target.getEntityBoundingBox().grow(8.0D, 2.0D, 8.0D);
				List<EntityLiving> list = player.world.getEntitiesWithinAABB(EntityLiving.class, aabb);
				if (list.isEmpty())
					return false;
				else {
					double d = 256.0D;
					EntityLivingBase entity = null;
					for (EntityLiving liv : list) {
						double d2 = liv.getDistanceSq(target);
						if (d2 > 0.5D && d2 < d) {
							d = d2;
							entity = liv;
						}
					}
					if (entity != null) {
						((EntityLiving) target).setAttackTarget(entity);
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean onToolUsing(EntityPlayer player, BlockPos pos, IBlockState state, ItemStack charm) {
		int meta = charm.getMetadata();
		if (player.isSneaking() && !player.world.isRemote && state != null) {
			boolean silk = false;

			if (meta == 9) {
				silk = DCUtil.hasItemInTopSlots(player, new ItemStack(this, 1, 19));
				// 一括破壊
				ItemStack hold = player.getHeldItemMainhand();
				BlockPos min = pos.add(-5, -5, -5);
				BlockPos max = pos.add(5, 5, 5);
				Iterable<BlockPos> itr = pos.getAllInBox(min, max);
				boolean flag = false;
				for (BlockPos p : itr) {
					if (p.getY() < 0 || p.getY() > 255) {
						continue;
					}
					IBlockState target = player.world.getBlockState(p);
					if (target.equals(state)
							&& target.getBlock().getMetaFromState(target) == state.getBlock().getMetaFromState(state)
							&& !target.getBlock().hasTileEntity(target)) {
						// 同Block同Metadata
						if (silk && target.getBlock().canSilkHarvest(player.world, p, target, player)) {
							ItemStack item = new ItemStack(target.getBlock(), 1,
									target.getBlock().getMetaFromState(target));
							EntityItem drop = new EntityItem(player.world, p.getX() + 0.5D, p.getY() + 0.5D,
									p.getZ() + 0.5D, item);
							player.world.spawnEntity(drop);
						} else {
							target.getBlock().harvestBlock(player.world, player, p, target, null, hold);
						}
						player.world.setBlockToAir(p);
						flag = true;
					}
				}
				return flag;
			} else if (meta == 8) {
				silk = DCUtil.hasItemInTopSlots(player, new ItemStack(this, 1, 19));
				// 範囲破壊
				ItemStack hold = player.getHeldItemMainhand();
				String tool = state.getBlock().getHarvestTool(state);
				int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, hold);
				BlockPos min = new BlockPos(pos.add(-1, -1, -1));
				BlockPos max = new BlockPos(pos.add(1, 1, 1));
				Iterable<BlockPos> itr = pos.getAllInBox(min, max);
				boolean flag = false;
				for (BlockPos p : itr) {
					if (p.getY() < 0 || p.getY() > 255) {
						continue;
					}
					IBlockState block = player.world.getBlockState(p);
					if (!block.getBlock().hasTileEntity(block)) {
						if (silk && block.getBlock().canSilkHarvest(player.world, p, block, player)) {
							ItemStack item = new ItemStack(block.getBlock(), 1,
									block.getBlock().getMetaFromState(block));
							EntityItem drop = new EntityItem(player.world, p.getX() + 0.5D, p.getY() + 0.5D,
									p.getZ() + 0.5D, item);
							player.world.spawnEntity(drop);
						} else {
							block.getBlock().harvestBlock(player.world, player, p, block, null, hold);
						}
						player.world.setBlockToAir(p);
						flag = true;
					}
				}
				return flag;
			} else if (meta == 19 && state.getBlock().canSilkHarvest(player.world, pos, state, player)) {
				ItemStack item = new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
				EntityItem drop = new EntityItem(player.world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
						item);
				player.world.spawnEntity(drop);
				player.world.setBlockToAir(pos);
				return true;
			}
		}
		return false;
	}

	public static EnchantmentData getEnchantment(EntityLivingBase entity) {
		EnchantmentData data = new EnchantmentData(Enchantments.UNBREAKING, 1);

		if (entity instanceof EntityZombie) {
			data = new EnchantmentData(Enchantments.SHARPNESS, 1);
		}
		if (entity instanceof EntitySkeleton) {
			data = new EnchantmentData(Enchantments.POWER, 1);
		}
		if (entity instanceof EntityCreeper) {
			data = new EnchantmentData(Enchantments.EFFICIENCY, 1);
		}
		if (entity instanceof EntitySpider) {
			data = new EnchantmentData(Enchantments.FEATHER_FALLING, 1);
		}
		if (entity instanceof EntityEnderman) {
			data = new EnchantmentData(Enchantments.LOOTING, 1);
		}
		if (entity instanceof EntitySlime) {
			data = new EnchantmentData(Enchantments.PROTECTION, 1);
		}
		if (entity instanceof EntityWitch) {
			data = new EnchantmentData(Enchantments.PROJECTILE_PROTECTION, 1);
		}
		if (entity instanceof EntityBlaze) {
			data = new EnchantmentData(Enchantments.FLAME, 1);
		}
		if (entity instanceof EntityGhast) {
			data = new EnchantmentData(Enchantments.INFINITY, 1);
		}
		if (entity instanceof EntityGuardian) {
			data = new EnchantmentData(Enchantments.AQUA_AFFINITY, 1);
		}
		if (entity instanceof EntityPigZombie) {
			data = new EnchantmentData(Enchantments.FIRE_ASPECT, 1);
		}
		if (entity instanceof EntityMagmaCube) {
			data = new EnchantmentData(Enchantments.FIRE_PROTECTION, 1);
		}
		return data;
	}

}
