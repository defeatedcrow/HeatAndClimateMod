package defeatedcrow.hac.main.item.tool;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.util.LootTablesDC;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.IPlantable;

public class ItemHandNet extends ItemTool implements ITexturePath {

	public static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.WEB);

	public ItemHandNet() {
		super(ToolMaterial.STONE, EFFECTIVE_ON);
		this.setMaxDamage(255);
		this.setMaxStackSize(1);
		this.setHasSubtypes(false);
		this.setFull3D();
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/tool/hand_net";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getItemEnchantability() {
		return 5;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (player != null) {
			ItemStack item = player.getHeldItem(hand);
			/* 水の場合のみ */
			if (!DCUtil.isEmpty(item)) {
				RayTraceResult res = this.rayTrace(world, player, true);
				if (res != null && res.typeOfHit == RayTraceResult.Type.BLOCK) {
					BlockPos pos = res.getBlockPos();
					if (world.isBlockModifiable(player, pos) && player.canPlayerEdit(pos
							.offset(res.sideHit), res.sideHit, item)) {
						IBlockState state = world.getBlockState(pos);
						if (state.getBlock() == Blocks.WATER || state.getBlock() == Blocks.FLOWING_WATER) {

							if (!world.isRemote) {
								// ガサガサ
								Biome biome = world.getBiome(player.getPosition());
								FishType type = FishType.POND;
								if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) || BiomeDictionary
										.hasType(biome, BiomeDictionary.Type.BEACH)) {
									type = FishType.OCEAN;
								} else if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)) {
									type = FishType.RIVER;
								}

								List<ItemStack> fishes = this.getFishItem(world, player, type, item, pos);

								if (!fishes.isEmpty() && !fishes.get(0).isEmpty()) {
									EntityItem drop = new EntityItem(world, pos.getX() + 0.5D, pos.getY(), pos
											.getZ() + 0.5D, fishes.get(0));
									drop.motionX = world.rand.nextDouble() * 0.1D;
									drop.motionY = world.rand.nextDouble() * 0.1D + 0.15D;
									drop.motionZ = world.rand.nextDouble() * 0.1D;
									drop.setPickupDelay(5);
									world.spawnEntity(drop);
								}
							}

							for (int i = 0; i < 5; i++) {
								float f6 = MathHelper.nextFloat(world.rand, 0.0F, 360.0F) * 0.017453292F;
								float f7 = MathHelper.nextFloat(world.rand, 25.0F, 60.0F);
								double d4 = MathHelper.sin(f6) * f7 * 0.1F;
								double d5 = pos.getY() + 1.125D;
								double d6 = MathHelper.cos(f6) * f7 * 0.1F;
								world.spawnParticle(EnumParticleTypes.WATER_SPLASH, pos.getX() + 0.5D, pos
										.getY() + 1D, pos.getZ() + 0.5D, d4, 0.2D, d6);
							}

							world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand
									.nextFloat() * 0.4F + 0.8F));
							player.swingArm(hand);
							item.damageItem(1, player);

							return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
						}
					}
				}
			} else {
				ActionResult<ItemStack> ret = super.onItemRightClick(world, player, hand);
				if (ret.getType() == EnumActionResult.PASS) {
					player.setActiveHand(hand);
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
				}
			}
		}
		return super.onItemRightClick(world, player, hand);
	}

	public List<ItemStack> getFishItem(World world, EntityPlayer player, FishType type, ItemStack tool, BlockPos pos) {

		List<ItemStack> drops = Lists.newArrayList();

		int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, tool);
		int silk = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, tool);

		ResourceLocation loot = LootTableList.GAMEPLAY_FISHING_FISH;
		int r1 = getEnvironmentCount(world, pos);
		if (r1 < 20) {
			return drops;
		}
		int r = (fortune * 10) + world.rand.nextInt(r1);

		if (silk > 0) {

		} else {
			if (r > 60) {
				if (world.rand.nextBoolean())
					loot = LootTablesDC.TREASURE_LOOT;
			} else {
				if (type == FishType.OCEAN) {
					loot = LootTablesDC.OCEAN_LOOT;
				} else if (type == FishType.RIVER) {
					loot = LootTablesDC.RIVER_LOOT;
				} else {
					loot = LootTablesDC.POND_LOOT;
				}
			}
		}

		LootContext.Builder builder = new LootContext.Builder((WorldServer) world);
		builder.withLuck(player.getLuck()).withPlayer(player);
		LootTable loottable = world.getLootTableManager().getLootTableFromLocation(loot);
		List<ItemStack> result = loottable.generateLootForPools(world.rand, builder.build());
		if (result != null && !result.isEmpty()) {
			drops.addAll(result);

			return drops;
		}

		return drops;
	}

	BlockPos last = null;
	int def = 0;

	private int getEnvironmentCount(World world, BlockPos pos) {

		if (last != null && pos.distanceSq(last) < 2.0D) {
			def += 10;
		} else {
			def = 0;
		}
		last = pos;

		int ret = 50 - def;

		if (world.isRaining()) {
			ret += 20;
		}

		Iterable<BlockPos> itr0 = pos.getAllInBox(pos.add(-1, 1, -1), pos.add(1, 1, 1));
		for (BlockPos p0 : itr0) {
			IBlockState st = world.getBlockState(p0);
			if (st != null) {
				if (st.getMaterial() == Material.WOOD || st.getMaterial() == Material.PLANTS) {
					ret += 5;
				} else if (st.getBlock() instanceof IPlantable) {
					ret += 5;
				}
			}
		}

		Iterable<BlockPos> itr1 = pos.getAllInBox(pos.add(-1, 0, -1), pos.add(1, 0, 1));
		int w1 = 0;
		for (BlockPos p1 : itr1) {
			IBlockState st = world.getBlockState(p1);
			if (st != null) {
				if (st.getMaterial() == Material.WATER) {
					w1++;
				} else if (st.getMaterial() == Material.WOOD || st.getMaterial() == Material.PLANTS) {
					ret += 5;
				} else if (st.getBlock() instanceof IPlantable) {
					ret += 5;
				}
			}
		}

		if (w1 < 2 || w1 > 7) {
			ret -= 20;
		}

		if (w1 == 4 || w1 == 5) {
			ret += 20;
		}

		Iterable<BlockPos> itr2 = pos.getAllInBox(pos.add(-1, -1, -1), pos.add(1, -1, 1));
		int w2 = 0;
		for (BlockPos p2 : itr2) {
			IBlockState st = world.getBlockState(p2);
			if (st != null) {
				if (st.getMaterial() == Material.WATER) {
					w2++;
				} else if (st.getMaterial() == Material.WOOD || st.getMaterial() == Material.PLANTS) {
					ret += 5;
				} else if (st.getBlock() instanceof IPlantable) {
					ret += 5;
				} else if (st.getMaterial() == Material.GRASS || st.getMaterial() == Material.SAND) {
					ret += 5;
				}
			}
		}

		if (w2 > 6) {
			ret -= 20;
		}

		if (w2 < 4) {
			ret += 20;
		}

		return ret;
	}

	public enum FishType {
		OCEAN,
		RIVER,
		POND;
	}
}
