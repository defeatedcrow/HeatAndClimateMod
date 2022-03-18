package defeatedcrow.hac.main.worldgen.vein;

import java.util.Random;

import defeatedcrow.hac.core.DCLogger;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenPosTest implements IWorldGenerator {

	private int range = -1;
	private int forceX = 0;
	private int forceZ = 0;

	private static Random pRandom;

	public WorldGenPosTest() {
		super();
	}

	public void setForcePos(int x, int z) {
		forceX = x;
		forceZ = z;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		generatePos(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
	}

	public boolean generatePos(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		pRandom = new Random(world.getSeed() + chunkX + chunkZ * 31);
		pRandom.nextInt(8);
		pRandom.nextInt(8);

		int genDim1 = world.provider.getDimension();
		if ((genDim1 == 1 || genDim1 == -1))
			return false;

		int X = chunkX << 4;
		int Z = chunkZ << 4;
		int posX = X + 8 + pRandom.nextInt(8);
		int posZ = Z + 8 + pRandom.nextInt(8);

		BlockPos pos = new BlockPos(posX, 60, posZ);

		Biome biome = world.getBiomeForCoordsBody(pos);
		float temp = biome.getDefaultTemperature();

		pRandom.nextFloat();
		float f = pRandom.nextFloat();
		if (f > 0.8F) {

			DCLogger.debugInfoLog("==== WorldGen Test ====");
			DCLogger.debugInfoLog("*Chunk: " + chunkX + ", " + chunkZ);
			DCLogger.debugInfoLog("*Base Pos: " + X + ", " + Z);
			DCLogger.debugInfoLog("*Pos: " + posX + ", " + posZ);
			DCLogger.debugInfoLog("*Biome: " + biome.getBiomeName());
			DCLogger.debugInfoLog("*Float: " + String.format("%.3f", f));

		}

		return true;
	}

}
