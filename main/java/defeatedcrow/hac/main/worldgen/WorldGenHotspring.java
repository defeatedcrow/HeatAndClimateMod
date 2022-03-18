package defeatedcrow.hac.main.worldgen;

import java.util.Random;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenHotspring implements IWorldGenerator {

	public WorldGenHotspring() {}

	public boolean generate(World world, Random rand, BlockPos pos) {

		// 山に埋まった生成はしない
		if (!world.isAirBlock(pos)) {
			return false;
		}

		while (pos.getY() > 55) {
			pos = pos.down();
			if (!world.isAirBlock(pos) && (world.getBlockState(pos).getMaterial() == Material.ROCK || world
					.getBlockState(pos).getMaterial() == Material.GROUND))
				break;
		}

		if (pos.getY() < 56) {
			// 地表にのみ生成
			return false;
		} else {
			Biome b = world.getBiome(pos);
			pos.down(2);
			// DCLogger.debugInfoLog("hotspring: " + pos.toString());
			int r = 5 + rand.nextInt(4);
			double r1 = r - 1D;
			double r2 = r - 3D;
			for (BlockPos p : BlockPos.getAllInBox(pos.add(-r, -4, -r), pos.add(r, 2, r))) {
				if (!world.isBlockLoaded(p))
					continue;
				if (world.isAirBlock(p))
					continue;
				double d0 = p.getX() - pos.getX();
				double d1 = p.getY() - pos.getY();
				double d2 = p.getZ() - pos.getZ();
				double d = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
				double d3 = rand.nextDouble() * 0.5D;
				if (d < r && world.getBlockState(p).getMaterial().isReplaceable()) {
					if (p.getY() >= pos.getY()) {
						world.setBlockState(p, AIR.getState(), 2);
					}
					continue;
				}
				if (d + d3 < r2) {
					// 液体生成範囲
					if (p.getY() < pos.getY()) {
						if (p.getY() == pos.getY() - 3) {
							// 湯の華
							world.setBlockState(p, TRA.getState(), 2);
						} else if (p.getY() == pos.getY() - 2) {
							// 湯
							boolean a1 = true;
							for (EnumFacing f : EnumFacing.VALUES) {
								if (f != EnumFacing.UP && !world.isSideSolid(p.offset(f), f
										.getOpposite(), false) && !world.getBlockState(p.offset(f)).getMaterial()
												.isLiquid()) {
									a1 = false;
								}
							}
							if (a1 && (rand.nextInt(3) == 0 || d < 2D)) {
								world.setBlockState(p, HOT.getState(), 2);
							} else {
								world.setBlockState(p, TRA.getState(), 2);
							}
						} else if (p.getY() == pos.getY() - 1) {
							boolean a1 = true;
							for (EnumFacing f : EnumFacing.VALUES) {
								if (f != EnumFacing.UP && !world.isSideSolid(p.offset(f), f
										.getOpposite(), false) && !world.getBlockState(p.offset(f)).getMaterial()
												.isLiquid()) {
									a1 = false;
								}
							}
							if (a1) {
								world.setBlockState(p, HOT.getState(), 2);
							} else {
								world.setBlockState(p, TRA.getState(), 2);
							}
						} else {
							world.setBlockState(p, TRA.getState(), 2);
						}
					} else {
						// 上
						world.setBlockState(p, AIR.getState(), 2);
					}
				} else if (d + d3 < r1) {
					// 縁部分
					if (p.getY() <= pos.getY() - 3) {
						// 石膏
						if (rand.nextBoolean())
							world.setBlockState(p, TRA.getState(), 2);
						else
							world.setBlockState(p, ALA.getState(), 2);
					} else if (p.getY() <= pos.getY()) {
						// 湯の華
						if (rand.nextBoolean())
							world.setBlockState(p, TRA.getState(), 2);
						else
							world.setBlockState(p, STN.getState(), 2);
					} else if (rand.nextBoolean()) {
						world.setBlockState(p, AIR.getState(), 2);
					}
				} else if (d + d3 < r) {
					// 縁部分2
					if (rand.nextBoolean()) {
						if (p.getY() < pos.getY()) {
							// 石膏
							world.setBlockState(p, ALA.getState(), 2);
						} else if (p.getY() == pos.getY()) {
							// 間欠泉
							if (rand.nextInt(100) < 5 && !world.isBlockNormalCube(p.up(), false)) {
								world.setBlockState(p, GYS.getState(), 2);
							} else {
								world.setBlockState(p, STN.getState(), 2);
							}
						}
					}
				}
			}
			return true;
		}
	}

	@Override
	public void generate(Random randomIn, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		long seed = world.getSeed() + chunkX + chunkZ * 31;
		Random random = new Random(seed);
		random.nextInt(10000);
		random.nextInt(10000);

		int genDim1 = world.provider.getDimension();
		if (genDim1 != 0)
			return;

		int posX = chunkX << 4;
		int posZ = chunkZ << 4;

		posX -= 8;
		posZ -= 8;

		BlockPos pos = new BlockPos(posX, 150, posZ);

		Biome b = world.getBiome(pos);
		if (b.getDefaultTemperature() > 0.2F) {
			return;
		}
		if (!BiomeDictionary.hasType(b, BiomeDictionary.Type.MOUNTAIN) && !BiomeDictionary
				.hasType(b, BiomeDictionary.Type.HILLS) && b.getDefaultTemperature() > 0F) {
			return;
		}

		double d = 0.2D - b.getDefaultTemperature();
		d += 1D; // 1D ~ 1.7D;
		if (random.nextInt(10000) > WorldGenConfig.hotspringGen * d) {
			return;
		}

		this.generate(world, random, pos);
	}

	private static final BlockSet AIR = new BlockSet(Blocks.AIR, 0);
	private static final BlockSet STN = new BlockSet(Blocks.STONE, 0);
	private static final BlockSet HOT = new BlockSet(MainInit.hotSpringBlock, 0);
	private static final BlockSet TRA = new BlockSet(MainInit.layerNew, 7);
	private static final BlockSet ALA = new BlockSet(MainInit.layerNew, 0);
	private static final BlockSet GYS = new BlockSet(MainInit.geyser, 1);

}
