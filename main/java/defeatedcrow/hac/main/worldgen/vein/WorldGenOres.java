package defeatedcrow.hac.main.worldgen.vein;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.api.orevein.OreSet;
import defeatedcrow.hac.main.api.orevein.VeinTable;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.config.WorldGenConfig;
import defeatedcrow.hac.main.worldgen.vein.OreGenPos.OreVein;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

// 新型
public class WorldGenOres implements IWorldGenerator {

	private static final boolean debug = false;
	private final boolean isForced;

	public WorldGenOres(boolean force) {
		super();
		isForced = force;
	}

	public WorldGenOres() {
		super();
		isForced = false;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		int genDim1 = world.provider.getDimension();
		if (Math.abs(chunkX) > 1000 || Math.abs(chunkZ) > 1000)
			// あまり遠いと生成しない
			return;
		if (!world.getChunkFromChunkCoords(chunkX, chunkZ).isLoaded())
			return;

		int posX1 = chunkX << 4;
		int posZ1 = chunkZ << 4;
		int count = 4;

		// 隣接チャンク分も生成する
		if (genDim1 == -1) {
			OreVein vein = OreGenPos.INSTANCE.getNetherVeins(chunkX, chunkZ, world);
			if (vein != null) {
				generateNetherVein(world, vein, posX1, posZ1);
			}
		} else if ((genDim1 != 1 && genDim1 != -1)) {
			OreVein[] veins = OreGenPos.INSTANCE.getVeins(chunkX, chunkZ, world);
			if (veins != null) {
				for (int i = 0; i < veins.length; i++) {
					OreVein vein = veins[i];
					if (vein != null && (!isForced || vein.pos.getY() < 120)) {
						if (vein.type == EnumVein.GUANO) {
							generateGuano(world, vein, posX1, posZ1);
						} else {
							generateVein(world, vein, posX1, posZ1);
						}
					}
				}
			}
		}

	}

	static boolean isPlaceable(IBlockState block) {
		if (block.isNormalCube() && !(block.getBlock() instanceof BlockEmptyDrops) && !(block
				.getBlock() instanceof BlockContainer))
			if (block.getMaterial() == Material.ROCK || block.getMaterial() == Material.SAND || block
					.getMaterial() == Material.GROUND || block.getMaterial() == Material.GRASS) {
				if (WorldGenConfig.disables.contains(new BlockSet(block.getBlock(), block.getBlock()
						.getMetaFromState(block)))) {
					return false;
				}
				return true;
			}

		return debug;
	}

	static boolean isPlaceable3(IBlockState block) {
		if (block.isNormalCube() && !(block.getBlock() instanceof BlockEmptyDrops)) {
			if (block.getMaterial() == Material.GROUND || block.getBlock() == Blocks.NETHERRACK || block
					.getBlock() == Blocks.SOUL_SAND) {
				if (WorldGenConfig.disables.contains(new BlockSet(block.getBlock(), block.getBlock()
						.getMetaFromState(block)))) {
					return false;
				}
				return true;
			}

		}
		return debug;
	}

	/**
	 * since 2019.2.26
	 * version3
	 * 円筒形の汎用鉱脈生成
	 */
	public void generateVein(World world, OreVein vein, int limX, int limZ) {
		VeinTable table = VeinTableRegister.INSTANCE.getTable(vein.type);
		if (table == null) {
			return;
		}
		BlockPos pos = vein.pos;
		int r = vein.round;
		int h = r - 1;
		int[] rands = vein.rands;
		OreSet[] gen = new OreSet[h];
		for (int i = 0; i < h; i++) {
			List<OreSet> list = new ArrayList<OreSet>();
			int i1 = 0;
			list = table.getOreTable();
			i1 = world.rand.nextInt(table.tableCount + 1);

			int i2 = 0;
			for (OreSet set : list) {
				i2 += set.getWeight();
				if (i2 >= i1) {
					gen[i] = set;
					break;
				}
				if (gen[i] == null) {
					gen[i] = list.get(0);
				}
			}
		}

		float sx = 0;
		float sz = 0;
		if (rands.length > 0) {
			int i3 = rands[0];
			if (i3 < 11) {
				sx = i3 / 10F;
				if ((i3 & 1) == 1) {
					sx *= -1F;
				}
			} else {
				i3 -= 10;
				sz = i3 / 10F;
				if ((i3 & 1) == 1) {
					sz *= -1F;
				}
			}
		}

		for (int x = -r; x < +r; x++) {
			for (int z = -r; z < +r; z++) {
				for (int y = 0; y < h; y++) {
					if (pos.getX() + x > limX && pos.getZ() + z > limZ) {

						int offY = MathHelper.floor(sx * x) + MathHelper.floor(sz * z);
						int h2 = y;

						BlockPos p = new BlockPos(pos.getX() + x, pos.getY() + y + offY, pos.getZ() + z);
						IBlockState block = world.getBlockState(p);
						double d1 = Math.sqrt(pos.distanceSq(p.getX(), pos.getY(), p.getZ()));
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && d1 < r) {
							OreSet add = gen[h2];
							if (block.getMaterial() == Material.GRASS && !world.isAirBlock(p.up())) {
								// 植物を破壊しないように
							} else if (isPlaceable(block) || isForced) {
								int j = world.rand.nextInt(100);
								if (add.hasSecondOre() && j < add.getSecondChance()) {
									world.setBlockState(p, add.getSecondOre().getState(), isForced ? 3 : 2);
								} else {
									world.setBlockState(p, add.getOre().getState(), isForced ? 3 : 2);
								}
							}
						}
					}
				}
			}
		}
	}

	// ネザー
	public void generateNetherVein(World world, OreVein vein, int limX, int limZ) {
		VeinTable table = VeinTableRegister.INSTANCE.getTable(vein.type);
		if (table == null) {
			return;
		}
		BlockPos pos = vein.pos;
		int r = vein.round;
		int h = r - 1;
		int[] rands = vein.rands;
		OreSet[] gen = new OreSet[h];
		for (int i = 0; i < h; i++) {
			List<OreSet> list = new ArrayList<OreSet>();
			int i1 = 0;
			list = table.getOreTable();
			i1 = world.rand.nextInt(table.tableCount);

			int i2 = 0;
			for (OreSet set : list) {
				i2 += set.getWeight();
				if (i2 >= i1) {
					gen[i] = set;
					break;
				}
				if (gen[i] == null) {
					gen[i] = list.get(0);
				}
			}
		}

		float sx = 0;
		float sz = 0;
		if (rands.length > 0) {
			int i3 = rands[0];
			if (i3 < 11) {
				sx = i3 / 10F;
				if ((i3 & 1) == 1) {
					sx *= -1F;
				}
			} else {
				i3 -= 10;
				sz = i3 / 10F;
				if ((i3 & 1) == 1) {
					sz *= -1F;
				}
			}
		}

		for (int x = -r; x < +r; x++) {
			for (int z = -r; z < +r; z++) {
				for (int y = 0; y < h; y++) {
					if (pos.getX() + x > limX && pos.getZ() + z > limZ) {

						int offY = MathHelper.floor(sx * x) + MathHelper.floor(sz * z);
						int h2 = y;

						BlockPos p = new BlockPos(pos.getX() + x, pos.getY() + y + offY, pos.getZ() + z);
						IBlockState block = world.getBlockState(p);
						double d1 = Math.sqrt(pos.distanceSq(p.getX(), pos.getY(), p.getZ()));
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && d1 < r) {
							OreSet add = gen[h2];
							if (isPlaceable3(block) || isForced) {
								int j = world.rand.nextInt(100);
								if (add.hasSecondOre() && j < add.getSecondChance()) {
									world.setBlockState(p, add.getSecondOre().getState(), isForced ? 3 : 4);
								} else {
									world.setBlockState(p, add.getOre().getState(), isForced ? 3 : 4);
								}
							}
						}
					}
				}
			}
		}
	}

	/*
	 * 燐グアノ。浅い海洋にのみ生成。
	 */
	public void generateGuano(World world, OreVein vein, int limX, int limZ) {
		VeinTable table = VeinTableRegister.INSTANCE.getTable(vein.type);
		if (table == null) {
			return;
		}
		BlockPos pos = vein.pos;
		int r = vein.round - 1;
		int h = r / 2 + 1;
		int[] rands = vein.rands;
		OreSet[] gen = new OreSet[h];
		for (int i = 0; i < h; i++) {
			if (i == 0) {
				gen[i] = table.layerStone;
			} else {
				List<OreSet> list = table.getOreTable();
				int i1 = world.rand.nextInt(table.tableCount);
				int i2 = 0;
				for (OreSet set : list) {
					i2 += set.getWeight();
					if (i2 >= i1) {
						gen[i] = set;
						break;
					}
					if (gen[i] == null) {
						gen[i] = list.get(0);
					}
				}
			}
		}

		for (int x = pos.getX() - r; x < pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z < pos.getZ() + r; z++) {
				for (int y = pos.getY(); y < pos.getY() + h; y++) {
					if (x > limX && z > limZ) {
						BlockPos p = new BlockPos(x, y, z);
						IBlockState block = world.getBlockState(p);
						double d1 = Math.sqrt(pos.distanceSq(p.getX(), pos.getY(), p.getZ()));
						boolean b1 = world.getBlockState(p.up(1)).getMaterial() == Material.WATER;
						boolean b2 = world.getBlockState(p.up(2)).getMaterial() == Material.WATER;
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && d1 < r) {
							if (b1 || b2) {
								int height = p.getY() - pos.getY();
								OreSet add = gen[height];
								if (isPlaceable(block) || isForced) {
									int j = world.rand.nextInt(100);
									if (add.hasSecondOre() && j < add.getSecondChance()) {
										world.setBlockState(p, add.getSecondOre().getState(), isForced ? 3 : 4);
										if (b1 && ModuleConfig.food) {
											world.setBlockState(p.up(), FoodInit.cropSeaweed.getDefaultState());
										}
									} else {
										world.setBlockState(p, add.getOre().getState(), isForced ? 3 : 4);
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
