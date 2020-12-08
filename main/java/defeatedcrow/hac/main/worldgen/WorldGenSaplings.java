package defeatedcrow.hac.main.worldgen;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.util.BiomeCatchDC;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenSaplings implements IWorldGenerator {

	private final boolean isForced;
	private int range = -1;
	private int forceX = 0;
	private int forceZ = 0;

	private static Random pRandom;

	public WorldGenSaplings(boolean force) {
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
		generateSapling(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
	}

	public boolean generateSapling(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		pRandom = new Random(world.getSeed() + chunkX + chunkZ * 31);
		pRandom.nextInt(8);
		pRandom.nextInt(8);
		pRandom.nextInt(8);

		int genDim1 = world.provider.getDimension();
		if ((genDim1 == 1 || genDim1 == -1))
			return false;

		if (chunkX > 3000 || chunkZ > 3000)
			// あまり遠いと生成しない
			return false;
		if (chunkX < -3000 || chunkZ < -3000)
			// あまり遠いと生成しない
			return false;

		if (!canGenerate(chunkX, chunkZ, world))
			return false;

		int X = chunkX << 4;
		int Z = chunkZ << 4;
		int posX = X + 8 + pRandom.nextInt(8);
		int posZ = Z + 8 + pRandom.nextInt(8);
		if (isForced && forceX != 0 & forceZ != 0) {
			posX = forceX;
			posZ = forceZ;
		}
		BlockPos pos = new BlockPos(posX, 60, posZ);

		Biome biome = BiomeCatchDC.getBiome(pos, world);
		float temp = biome.getDefaultTemperature();

		TreeType type = null;

		if (temp > 0.9F || BiomeDictionary.hasType(biome, BiomeDictionary.Type.HOT)) {
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.DRY) || BiomeDictionary
					.hasType(biome, BiomeDictionary.Type.SANDY)) {
				type = TreeType.DATE;
			} else if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.WET) || BiomeDictionary
					.hasType(biome, BiomeDictionary.Type.JUNGLE)) {
				type = TreeType.MORUS;
			} else if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN) || BiomeDictionary
					.hasType(biome, BiomeDictionary.Type.HILLS)) {
				type = TreeType.TEA;
			}
		} else if (temp > 0.0F) {
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN) || BiomeDictionary
					.hasType(biome, BiomeDictionary.Type.HILLS)) {
				type = TreeType.TEA;
			} else if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.CONIFEROUS)) {
					type = TreeType.WALNUT;
				} else if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.WET)) {
					type = TreeType.WISTERIA;
				} else {
					type = TreeType.LEMON;
				}
			} else if (temp > 0.5F) {
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.WET)) {
					type = TreeType.MORUS;
				} else {
					type = TreeType.OLIVE;
				}
			}
		}

		if (type != null)

		{
			// 高度選定
			int h = -1;
			for (int y = 200; y > 1; y--) {
				BlockPos p1 = new BlockPos(posX, y, posZ);
				if (isSoil(world, p1) && isReplaceable(world, p1.up()) && (world.canSeeSky(p1.up()) || world
						.getBlockState(p1).getMaterial() == Material.GRASS)) {
					h = y;
					break;
				}
			}

			if (h > 2) {
				// DCLogger.debugInfoLog("Wild sapling target pos: " + posX + ", " + h + ", " + posZ + ", " + type);
				BlockPos main = new BlockPos(posX, h, posZ);
				generate(world, main, type);
				return true;
			}
		}
		return false;
	}

	private boolean checkLight(World world, BlockPos pos) {
		if (DCTimeHelper.isDayTime(world)) {
			return world.getLightFromNeighbors(pos) > 9;
		} else {
			return world.getLightFromNeighbors(pos) > 6;
		}
	}

	public void generate(World world, BlockPos pos, TreeType type) {
		IBlockState sap = FoodInit.saplings.getDefaultState();
		switch (type) {
		case DATE:
			sap = FoodInit.saplings2.getDefaultState().withProperty(DCState.STAGE4, 1);
			break;
		case LEMON:
			sap = FoodInit.saplings.getDefaultState();
			break;
		case MORUS:
			sap = FoodInit.saplings.getDefaultState().withProperty(DCState.STAGE4, 3);
			break;
		case OLIVE:
			sap = FoodInit.saplings.getDefaultState().withProperty(DCState.STAGE4, 1);
			break;
		case TEA:
			sap = FoodInit.saplings.getDefaultState().withProperty(DCState.STAGE4, 2);
			break;
		case WALNUT:
			sap = FoodInit.saplings2.getDefaultState();
			break;
		case WISTERIA:
			sap = FoodInit.cropWisteria.getDefaultState();
			break;
		default:
			break;
		}
		world.setBlockState(pos.up(), sap, 2);
		IGrowable crop = (IGrowable) sap.getBlock();
		crop.grow(world, world.rand, pos.up(), sap);
	}

	private boolean canGenerate(int chunkX, int chunkZ, World world) {
		if (isForced)
			return true;
		int i = WorldGenConfig.saplingGen;
		pRandom.nextFloat();
		pRandom.nextFloat();
		float r = pRandom.nextFloat() * 10000F;
		int ri = MathHelper.floor(r);
		if (ri > 0 && ri < i)
			return true;
		return false;
	}

	public boolean isSoil(World world, BlockPos pos) {
		net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
		return state.getMaterial() == Material.GROUND || state.getMaterial() == Material.GRASS || state
				.getMaterial() == Material.SAND;
	}

	public boolean isReplaceable(World world, BlockPos pos) {
		net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
		if (state.getMaterial().isLiquid())
			return false;
		return state.getBlock().isAir(state, world, pos) || state.getMaterial().isReplaceable();
	}

	private enum TreeType {
		LEMON,
		OLIVE,
		TEA,
		MORUS,
		WALNUT,
		DATE,
		WISTERIA;
	}

}
