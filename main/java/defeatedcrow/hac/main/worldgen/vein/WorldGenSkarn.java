package defeatedcrow.hac.main.worldgen.vein;

import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.BiomeCatchDC;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.api.orevein.OreSet;
import defeatedcrow.hac.main.api.orevein.VeinTable;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenSkarn implements IWorldGenerator {

	private final boolean isForced;
	private int forceX = 0;
	private int forceZ = 0;

	public WorldGenSkarn(boolean force) {
		super();
		isForced = force;
	}

	public void setForcePos(int x, int z) {
		forceX = x;
		forceZ = z;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (!ModuleConfig.world)
			return;

		int genDim1 = world.provider.getDimension();
		if ((genDim1 == 1 || genDim1 == -1))
			return;

		if (!world.getChunkFromChunkCoords(chunkX, chunkZ).isLoaded())
			return;

		BlockPos center = SkarnGenPos.getNearestPoint(chunkX, chunkZ, world, 2);
		if (center == null)
			return;
		if (SkarnGenPos.isDupe(center, world)) {
			return;
		}

		int cx2 = center.getX() >> 4;
		int cz2 = center.getZ() >> 4;
		if (chunkX == cx2 && chunkZ == cz2) {
			DCLogger.debugInfoLog("Generation skarn : " + chunkX + "," + chunkZ);
		}

		int posX = chunkX << 4;
		int posZ = chunkZ << 4;

		posX += 4;
		posZ += 4;
		if (isForced && forceX != 0 & forceZ != 0) {
			posX = forceX;
			posZ = forceZ;
		}
		BlockPos pos = new BlockPos(posX, 40, posZ);

		Random rand2 = new Random(world.getSeed() + cx2 + cz2 * 31);
		OreSet[] ores = this.getBlockSet2(rand2, BiomeCatchDC.getBiome(pos, world));

		for (int x = posX; x < posX + 16; x++) {
			for (int z = posZ; z < posZ + 16; z++) {
				BlockPos p1 = new BlockPos(x, 40, z);
				Biome biome = BiomeCatchDC.getBiome(p1, world);
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN)) {
					center = new BlockPos(center.getX(), 30, center.getZ());
				}
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN) || BiomeDictionary
						.hasType(biome, BiomeDictionary.Type.HILLS)) {
					center = new BlockPos(center.getX(), 50, center.getZ());
				}

				int height = SkarnGenPos.getGenHeight(world, p1, center);
				for (int y = height * 2; y > 2; y--) {
					BlockPos p = new BlockPos(x, y, z);
					IBlockState block = world.getBlockState(p);
					OreSet set = this.getBlockSet1(y, height, rand2, biome);
					if (this.isPlaceable(block, isForced)) {
						if (y == height && y > 5) {
							set = new OreSetDC(100, QUARTZ);
						} else if (y > height * 0.8D && y < height * 1.2D && y > 5) {
							int h = y - (int) (height * 0.8D);
							if (h < 0)
								h = -h;
							if (h > 19)
								h = 0;
							if (ores[h] != null) {
								set = ores[h];
							}
						}

						if (set != null) {
							if (set.hasSecondOre() && world.rand.nextInt(100) < set.getSecondChance()) {
								world.setBlockState(p, set.getSecondOre().getState(), 2);
							} else {
								world.setBlockState(p, set.getOre().getState(), 2);
							}
						}
					} else if (block != null && block.getMaterial() == Material.GRASS) {
						if (world.isAirBlock(p.up()) && world.rand.nextBoolean())
							world.setBlockState(p, AIR.getState(), 2);
					}
				}
			}
		}
	}

	private OreSet getBlockSet1(int y, int height, Random rand, Biome biome) {
		if (y == height) {
			return new OreSetDC(100, QUARTZ);
		} else {
			BlockSet[] blocks = STONE;
			if (biome != null) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) || BiomeDictionary
						.hasType(biome, BiomeDictionary.Type.SWAMP)) {
					blocks = SAND;
				} else if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY) || BiomeDictionary
						.hasType(biome, BiomeDictionary.Type.MESA) || BiomeDictionary
								.hasType(biome, BiomeDictionary.Type.PLAINS)) {
					blocks = LIME;
				}
			}

			if (y < (height / 2)) {
				if (y < 11)
					return new OreSetDC(100, LAVA);
				if (y < 14)
					return new OreSetDC(100, AIR);
				else
					return new OreSetDC(100, blocks[3]);
			} else if (y < height) {
				if (rand.nextInt(100) == 1) {
					return GREISEN_ORE;
				}
				return new OreSetDC(100, blocks[2]);
			} else if (y < (height * 1.5D)) {
				if (rand.nextInt(100) == 1) {
					if (blocks == LIME) {
						return LIME_ORE;
					}
					if (blocks == SAND) {
						return HORNFELS_ORE;
					}
					if (blocks == STONE) {
						return SKARN_ORE;
					}
				}
				return new OreSetDC(100, blocks[1]);
			} else {
				return new OreSetDC(100, blocks[0]);
			}
		}
	}

	private OreSet[] getBlockSet2(Random rand, Biome biome) {
		VeinTable table = VeinTableRegister.INSTANCE.getTable(EnumVein.SKARN_IRON);
		if (biome != null) {
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN) || BiomeDictionary
					.hasType(biome, BiomeDictionary.Type.SWAMP)) {
				table = VeinTableRegister.INSTANCE.getTable(EnumVein.SKARN_COPPER);
			}
		}
		OreSet[] ret = new OreSet[20];
		if (table != null) {
			List<OreSet> list = table.getOreTable();
			for (int i = 0; i < 20; i++) {
				int i1 = rand.nextInt(table.tableCount);
				int i2 = 0;
				for (OreSet set : list) {
					i2 += set.getWeight();
					if (i2 >= i1) {
						ret[i] = set;
						break;
					}
				}
			}
		}
		return ret;
	}

	static boolean isPlaceable(IBlockState block, boolean b) {
		if (block.isNormalCube() && !(block.getBlock() instanceof BlockEmptyDrops) && !block.getBlock()
				.getUnlocalizedName().contains("ore"))
			return block.getMaterial() == Material.ROCK || block.getMaterial() == Material.SAND || block.getBlock()
					.getUnlocalizedName().contains("gravel") || block.getBlock().getUnlocalizedName().contains("dirt");

		return b;
	}

	static boolean isPlaceable2(IBlockState block, boolean b) {
		if (block.isNormalCube() && !(block.getBlock() instanceof BlockEmptyDrops) && !block.getBlock()
				.getUnlocalizedName().contains("ore"))
			return block.getMaterial() == Material.ROCK || block.getMaterial() == Material.SAND || block.getBlock()
					.getUnlocalizedName().contains("gravel") || block.getBlock().getUnlocalizedName().contains("dirt");

		return b;
	}

	private static final BlockSet AIR = new BlockSet(Blocks.AIR, 0);
	private static final BlockSet LAVA = new BlockSet(Blocks.LAVA, 0);

	private static final BlockSet GRANITE = new BlockSet(Blocks.STONE, 1);
	private static final BlockSet QUARTZ = new BlockSet(MainInit.skarnOre, 8);
	private static final BlockSet IRON = new BlockSet(MainInit.skarnOre, 8);
	private static final BlockSet COPPER = new BlockSet(MainInit.skarnOre, 8);

	private static final BlockSet[] LIME = new BlockSet[] {
			new BlockSet(MainInit.layerNew, 1),
			new BlockSet(MainInit.gemBlock, 6),
			new BlockSet(MainInit.skarnBlock, 2),
			new BlockSet(Blocks.STONE, 1),
			new BlockSet(MainInit.oreNew, 4)
	};

	private static final BlockSet[] STONE = new BlockSet[] {
			new BlockSet(Blocks.STONE, 0),
			new BlockSet(MainInit.skarnBlock, 0),
			new BlockSet(MainInit.skarnBlock, 2),
			new BlockSet(Blocks.STONE, 1),
			new BlockSet(MainInit.oreNew, 4)
	};

	private static final BlockSet[] SAND = new BlockSet[] {
			new BlockSet(Blocks.SANDSTONE, 0),
			new BlockSet(MainInit.skarnBlock, 1),
			new BlockSet(MainInit.skarnBlock, 2),
			new BlockSet(Blocks.STONE, 1),
			new BlockSet(MainInit.oreNew, 0)
	};

	private static final OreSet LIME_ORE = new OreSetDC(100, new BlockSet(MainInit.skarnOre, 0), new BlockSet(
			MainInit.skarnOre, 1), 20);

	private static final OreSet HORNFELS_ORE = new OreSetDC(100, new BlockSet(MainInit.skarnOre, 2), new BlockSet(
			MainInit.skarnOre, 3), 20);

	private static final OreSet GREISEN_ORE = new OreSetDC(100, new BlockSet(MainInit.skarnOre, 5), new BlockSet(
			MainInit.skarnOre, 4), 20);

	private static final OreSet SKARN_ORE = new OreSetDC(100, new BlockSet(MainInit.skarnOre, 6), new BlockSet(
			MainInit.skarnOre, 7), 20);

}
