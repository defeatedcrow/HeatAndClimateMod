package defeatedcrow.hac.magic.item;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewelCharm;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCPotion;
import defeatedcrow.hac.main.util.CustomExplosion;

public class ItemMagicalBadge extends DCItem implements IJewelCharm {

	private final int maxMeta;

	private static String[] names = {
			"chal_blue", /* 水属性追撃 */
			"chal_red", /* 火属性追撃 */
			"chal_white", /* ダメージ基礎値増加 */
			"crystal", /* バフ解除 */
			"sapphire", /* エンチャ本の追加ドロップ */
			"malachite", /* マーキング追撃 */
			"celestite", /* 天候制御 */
			"clam", /* ワープ */
			"lapis",/* アイテム収集範囲 */
			"diamond",/* 範囲採掘 */
			"schorl" /* サンボル */};

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
			return CharmType.KEY;
		case 8:
		case 9:
			return CharmType.TOOL;
		default:
			return CharmType.ATTACK;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		String s = "";
		int meta = stack.getMetadata();
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.translateToLocal("dcs.comment.badge." + meta));
		} else {
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.translateToLocal("dcs.tip.badge." + meta));
			tooltip.add(TextFormatting.RESET.toString() + I18n.translateToLocal("dcs.tip.shift"));
		}
		if (meta == 7) {
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
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isAirBlock(pos) && stack != null && stack.getItem() == this) {
			int meta = stack.getMetadata();
			if (!world.isRemote) {
				if (meta == 7) {
					if (pos.getY() > 0 && pos.getY() < 254 && world.isAirBlock(pos.up()) && world.isAirBlock(pos.up(2))) {
						NBTTagCompound tag = stack.getTagCompound();
						if (tag == null) {
							tag = new NBTTagCompound();
						}
						String dimName = world.provider.getDimensionType().getName();
						int dim = world.provider.getDimension();
						int x = pos.getX();
						int y = pos.getY();
						int z = pos.getZ();
						tag.setString("dcs.charm.dimname", dimName);
						tag.setInteger("dcs.charm.dim", dim);
						tag.setInteger("dcs.charm.x", x);
						tag.setInteger("dcs.charm.y", y + 1);
						tag.setInteger("dcs.charm.z", z);
						stack.setTagCompound(tag);
						return EnumActionResult.SUCCESS;
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
		return i / 128.0D;
	}

	public int getNBTDamage(ItemStack stack) {
		if (stack != null) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null)
				tag = new NBTTagCompound();

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
		if (stack != null) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null)
				tag = new NBTTagCompound();

			int dam = 0;
			if (tag.hasKey("dcs.itemdam")) {
				dam = tag.getInteger("dcs.itemdam");
			}
			dam++;
			if (dam > 128) {
				return null;
			} else {
				tag.setInteger("dcs.itemdam", dam);
				stack.setTagCompound(tag);
				return stack;
			}
		}
		return stack;
	}

	@Override
	public void constantEffect(EntityPlayer player, ItemStack charm) {
		if (player != null && charm != null && !player.worldObj.isRemote) {
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
		if (player != null && charm != null && !player.worldObj.isRemote) {
			int meta = charm.getMetadata();
			if (meta == 6) {
				if (player.worldObj.isRaining() || player.worldObj.isThundering()) {
					WorldInfo worldinfo = player.worldObj.getWorldInfo();
					worldinfo.setCleanWeatherTime(6000); // 5min
					worldinfo.setRainTime(0);
					worldinfo.setThunderTime(0);
					worldinfo.setRaining(false);
					worldinfo.setThundering(false);
					return true;
				}
			}
			if (meta == 7) {
				int dim = player.worldObj.provider.getDimension();
				NBTTagCompound tag = charm.getTagCompound();
				if (tag != null && tag.hasKey("dcs.charm.dim")) {
					int warpDim = tag.getInteger("dcs.charm.dim");
					double x = tag.getInteger("dcs.charm.x") + 0.5D;
					double y = tag.getInteger("dcs.charm.y");
					double z = tag.getInteger("dcs.charm.z") + 0.5D;

					if (warpDim == dim) {
						player.setPositionAndUpdate(x, y, z);
						player.fallDistance = 0.0F;
						return true;
					}
				}
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
			if (target.isImmuneToFire() || target instanceof EntityEnderman) {
				return 1.5F;
			}
		}
		if (meta == 1) {
			if (target.isEntityUndead() || target instanceof EntityGuardian) {
				return 1.5F;
			}
		}
		if (meta == 2) {
			return 1.1F;
		}
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
				target.addPotionEffect(new PotionEffect(DCPotion.glowing, 2400, 0));
				return true;
			}
		}
		if (meta == 4 && this.itemRand.nextInt(20) == 0) {
			if (target instanceof IMob && target.getHealth() <= damage) {
				EnchantmentData data = this.getEnchantment(target);
				ItemStack ret = Items.ENCHANTED_BOOK.getEnchantedItemStack(data);
				EntityItem drop = new EntityItem(player.worldObj, target.posX, target.posY + 0.5D, target.posZ, ret);
				drop.setDefaultPickupDelay();
				player.worldObj.spawnEntityInWorld(drop);
				return true;
			}
		}
		if (meta == 10) {
			if (player != null && target != null && !source.isExplosion() && source.isProjectile()) {
				CustomExplosion explosion = new CustomExplosion(player.worldObj, player, player, target.posX,
						target.posY + 0.25D, target.posZ, 3F, CustomExplosion.Type.Silk, true);
				explosion.doExplosion();
				player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, target.posX,
						target.posY - 0.25D, target.posZ, !player.isSneaking()));
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onToolUsing(EntityPlayer player, BlockPos pos, IBlockState state, ItemStack charm) {
		int meta = charm.getMetadata();
		if (player.isSneaking() && !player.worldObj.isRemote && state != null) {
			if (meta == 9) {
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
					IBlockState target = player.worldObj.getBlockState(p);
					if (target.equals(state)
							&& target.getBlock().getMetaFromState(target) == state.getBlock().getMetaFromState(state)
							&& !target.getBlock().hasTileEntity(target)) {
						// 同Block同Metadata
						target.getBlock().harvestBlock(player.worldObj, player, p, target, null, hold);
						player.worldObj.setBlockToAir(p);
						flag = true;
					}
				}
				return flag;
			} else if (meta == 8) {
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
					IBlockState block = player.worldObj.getBlockState(p);
					if (block.getBlock().isToolEffective(tool, block) && !block.getBlock().hasTileEntity(block)) {
						block.getBlock().harvestBlock(player.worldObj, player, p, block, null, hold);
						player.worldObj.setBlockToAir(p);
						flag = true;
					}
				}
				return flag;
			}
		}
		return false;
	}

	public static EnchantmentData getEnchantment(EntityLivingBase entity) {
		EnchantmentData data = new EnchantmentData(Enchantments.UNBREAKING, 0);

		if (entity instanceof EntityZombie) {
			data = new EnchantmentData(Enchantments.SHARPNESS, 0);
		}
		if (entity instanceof EntitySkeleton) {
			data = new EnchantmentData(Enchantments.POWER, 0);
		}
		if (entity instanceof EntityCreeper) {
			data = new EnchantmentData(Enchantments.EFFICIENCY, 0);
		}
		if (entity instanceof EntitySpider) {
			data = new EnchantmentData(Enchantments.FEATHER_FALLING, 0);
		}
		if (entity instanceof EntityEnderman) {
			data = new EnchantmentData(Enchantments.LOOTING, 0);
		}
		if (entity instanceof EntitySlime) {
			data = new EnchantmentData(Enchantments.PROTECTION, 0);
		}
		if (entity instanceof EntityWitch) {
			data = new EnchantmentData(Enchantments.PROJECTILE_PROTECTION, 0);
		}
		if (entity instanceof EntityBlaze) {
			data = new EnchantmentData(Enchantments.FLAME, 0);
		}
		if (entity instanceof EntityGhast) {
			data = new EnchantmentData(Enchantments.INFINITY, 0);
		}
		if (entity instanceof EntityGuardian) {
			data = new EnchantmentData(Enchantments.AQUA_AFFINITY, 0);
		}
		if (entity instanceof EntityPigZombie) {
			data = new EnchantmentData(Enchantments.FIRE_ASPECT, 0);
		}
		if (entity instanceof EntityMagmaCube) {
			data = new EnchantmentData(Enchantments.FIRE_PROTECTION, 0);
		}
		return data;
	}

}
