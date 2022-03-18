package defeatedcrow.hac.main.worldgen.vein;

import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.api.orevein.OreSet;
import defeatedcrow.hac.main.api.orevein.VeinTable;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

@Deprecated
public class WorldGenAltSkarn implements IWorldGenerator {

	private final boolean isForced;
	private int range = -1;
	private int forceX = 0;
	private int forceZ = 0;

	public WorldGenAltSkarn(boolean force) {
		super();
		isForced = force;
	}

	public void setRange(int i) {
		if (i > 10) {
			i = 10;
		}
		range = i;
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

		if (!canGenerate(chunkX, chunkZ, world, isForced))
			return;

		int posX = chunkX << 4;
		int posZ = chunkZ << 4;

		posX += world.rand.nextInt(8) + 4;
		posZ += world.rand.nextInt(8) + 4;
		if (isForced && forceX != 0 & forceZ != 0) {
			posX = forceX;
			posZ = forceZ;
		}
		BlockPos pos = new BlockPos(posX, 75, posZ);

		Biome biome = world.getBiome(pos);
		int f = world.rand.nextInt(15);
		if (range > 0) {
			f = (range / 2) + world.rand.nextInt(range);
		}
		int r = 2 + f;

		if (isForced || (!BiomeDictionary.hasType(biome, BiomeDictionary.Type.WET) && BiomeDictionary
				.hasType(biome, BiomeDictionary.Type.FOREST))) {
			for (int y = 1; y < 80; y++) {
				// world.setBlockState(pos.up(y), Blocks.STONE.getDefaultState(), 2);
				int r2 = y / 4;
				int r3 = y / 8;
				int r4 = (y - 40) / 4;
				int rr = r;
				if (y < 25) {
					rr = r + 3 - r2;
				} else if (y > 40) {
					rr = r - r3 + r4;
				} else {
					rr = r - r3;
				}

				if (rr > 0) {
					OreSet b1 = this.getBlockSet1(y, f, world.rand);
					BlockSet b2 = this.getBlockSet2(y, f, world.rand);
					// forに変更
					for (int x = posX - rr; x <= posX + rr; x++) {
						for (int z = posZ - rr; z <= posZ + rr; z++) {
							BlockPos p = new BlockPos(x, y, z);
							IBlockState block = world.getBlockState(p);
							if (block.getMaterial() == Material.GRASS && !world.isAirBlock(p.up())) {
								// 植物を破壊しないように
							} else if (this.isPlaceable(block, isForced)) {
								int x1 = p.getX() - posX;
								int z1 = p.getZ() - posZ;
								double dist = Math.sqrt(x1 * x1 + z1 * z1);
								if (rr > 2 && dist < rr - 2) {
									if (b1 != null) {
										if (b1.hasSecondOre() && world.rand.nextInt(100) < b1.getSecondChance()) {
											world.setBlockState(p, b1.getSecondOre().getState(), 2);
										} else {
											world.setBlockState(p, b1.getOre().getState(), 2);
										}
									}
								} else if (dist < rr) {
									if (b2 != null) {
										world.setBlockState(p, b2.getState(), 2);
									}
								}
							} else if (block.getMaterial() == Material.WOOD || block.getMaterial() == Material.LEAVES) {
								world.setBlockToAir(p);
							} else if (block.getMaterial() == Material.GRASS) {
								if (world.rand.nextBoolean() && world.isAirBlock(p.up())) {
									world.setBlockToAir(p);
								}
							}
						}
					}
				}
			}
		}
	}

	// 1/200
	public static boolean canGenerate(int chunkX, int chunkZ, World world, boolean f) {
		Random pRandom = new Random(world.getSeed() + chunkX + chunkZ * 31);
		if (f)
			return true;
		if (!SkarnGenPoint.hasPos(chunkX, chunkZ)) {
			int i = WorldGenConfig.skarnGen;
			pRandom.nextFloat();
			float r = pRandom.nextFloat() * 10000F;
			int ri = MathHelper.floor(r);
			if (ri > 0 && ri < i) {
				SkarnGenPoint.addPos(chunkX, chunkZ);
				return true;
			}
		}
		return false;
	}

	private OreSet getBlockSet1(int y, int f, Random rand) {
		int f2 = rand.nextInt(3);
		if (y < 12 + f2)
			// 最下層: 溶岩と花崗岩のレンズ状のかたまり
			return new OreSetDC(100, LAVA);
		else if (y < 14 + f2)
			// 最下層: 花崗岩
			return new OreSetDC(100, AIR);
		else if (y < 17 + f2)
			// 最下層: 花崗岩
			return new OreSetDC(100, STONE_1);
		else if (y > 50 + f2) {
			// 最上層: 石灰岩と大理石
			if (y > 58 + f2)
				return new OreSetDC(100, LIME, false);
			else
				return new OreSetDC(100, MARBLE, false);
		} else {
			VeinTable table = VeinTableRegister.INSTANCE.getTable(EnumVein.SKARN_IRON);
			VeinTable table2 = VeinTableRegister.INSTANCE.getTable(EnumVein.SKARN_COPPER);
			if (table == null || table2 == null) {
				return new OreSetDC(100, MARBLE);
			}
			// 中層: 下から、黒/白/RS、赤/青/緑/大理石
			if (y < 28) {
				List<OreSet> list = table2.getOreTable();
				int i1 = rand.nextInt(table2.tableCount);
				int i2 = 0;
				for (OreSet set : list) {
					i2 += set.getWeight();
					if (i2 >= i1) {
						return set;
					}
				}
				return new OreSetDC(100, STONE_2);
			} else {
				List<OreSet> list = table.getOreTable();
				int i1 = rand.nextInt(table.tableCount);
				int i2 = 0;
				for (OreSet set : list) {
					i2 += set.getWeight();
					if (i2 >= i1) {
						return set;
					}
				}
				return new OreSetDC(100, MARBLE, false);
			}
		}
	}

	private BlockSet getBlockSet2(int y, int f, Random rand) {
		int f2 = rand.nextInt(3);
		if (y < 12 + f2)
			// 最下層
			return STONE_1;
		else if (y < 20 + f2)
			// 最下層
			return STONE_1;
		else if (y > 48 + f2) {
			// 最上層
			if (y > 58 + f2)
				return LIME;
			else
				return MARBLE;
		} else {
			// 中層
			if (y < 30)
				return STONE_1;
			else
				return MARBLE;
		}
	}

	static boolean isPlaceable(IBlockState block, boolean b) {
		if (block.isNormalCube() && !(block.getBlock() instanceof BlockEmptyDrops))
			return block.getMaterial() == Material.ROCK || block.getMaterial() == Material.SAND || block
					.getMaterial() == Material.GROUND || block.getMaterial() == Material.GRASS;

		return b;
	}

	private static final BlockSet AIR = new BlockSet(Blocks.AIR, 0);

	private static final BlockSet LAVA = new BlockSet(Blocks.LAVA, 0);
	private static final BlockSet STONE_0 = new BlockSet(Blocks.STONE, 0);
	private static final BlockSet STONE_1 = new BlockSet(Blocks.STONE, 1);
	private static final BlockSet STONE_2 = new BlockSet(Blocks.STONE, 3);
	private static final BlockSet STONE_3 = new BlockSet(Blocks.STONE, 5);
	private static final BlockSet SAND = new BlockSet(Blocks.SANDSTONE, 0);

	private static final BlockSet LIME = new BlockSet(MainInit.layerNew, 1);
	private static final BlockSet MARBLE = new BlockSet(MainInit.gemBlock, 6);

}
