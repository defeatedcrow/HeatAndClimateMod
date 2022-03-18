package defeatedcrow.hac.main.worldgen;

import java.util.Random;

import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class MazaiLakeGen implements IWorldGenerator {

	public MazaiLakeGen() {}

	public boolean generate(World world, Random rand, BlockPos pos) {

		while (pos.getY() > 10 && (world.isAirBlock(pos) || world.getBlockState(pos).getMaterial() == Material.LAVA)) {
			pos = pos.down();
		}

		if (pos.getY() <= 10) {
			return false;
		} else {
			// 底
			pos = pos.down(2);
			double range = rand.nextDouble() * 5.0D + 3.0D;
			for (int x = -7; x < 8; x++) {
				for (int z = -7; z < 8; z++) {
					double r = Math.sqrt(x * x + z * z);
					if (r < range) {
						if (r > range - 1.0D) {
							world.setBlockState(pos.add(x, 1, z), Blocks.AIR.getDefaultState(), 2);
							world.setBlockState(pos.add(x, 0, z), Blocks.OBSIDIAN.getDefaultState(), 2);
							if (rand.nextBoolean()) {
								world.setBlockState(pos.add(x, 2, z), Blocks.AIR.getDefaultState(), 2);
							}
						} else if (r > range - 2.0D) {
							world.setBlockState(pos.add(x, 1, z), Blocks.AIR.getDefaultState(), 2);
							world.setBlockState(pos.add(x, 2, z), Blocks.AIR.getDefaultState(), 2);
							world.setBlockState(pos.add(x, -1, z), Blocks.OBSIDIAN.getDefaultState(), 2);
							if (rand.nextBoolean()) {
								world.setBlockState(pos.add(x, 0, z), Blocks.OBSIDIAN.getDefaultState(), 2);
							} else {
								world.setBlockState(pos.add(x, 0, z), MainInit.mazaiBlock.getDefaultState(), 2);
								world.setBlockState(pos.add(x, -1, z), Blocks.OBSIDIAN.getDefaultState(), 2);
							}
						} else {
							world.setBlockState(pos.add(x, 1, z), Blocks.AIR.getDefaultState(), 2);
							world.setBlockState(pos.add(x, 2, z), Blocks.AIR.getDefaultState(), 2);
							world.setBlockState(pos.add(x, 0, z), MainInit.mazaiBlock.getDefaultState(), 2);
							if (rand.nextBoolean()) {
								world.setBlockState(pos.add(x, -1, z), Blocks.OBSIDIAN.getDefaultState(), 2);
								world.setBlockState(pos.add(x, 3, z), Blocks.AIR.getDefaultState(), 2);
							} else {
								world.setBlockState(pos.add(x, -1, z), MainInit.mazaiBlock.getDefaultState(), 2);
								world.setBlockState(pos.add(x, -2, z), Blocks.OBSIDIAN.getDefaultState(), 2);
							}
						}
					}
				}
			}
			return true;
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		random.nextInt(100);
		random.nextInt(100);
		if (random.nextInt(100) != 0) {
			return;
		}

		int genDim1 = world.provider.getDimension();
		if ((genDim1 != 1 && genDim1 != -1))
			return;

		int posX = chunkX << 4;
		int posZ = chunkZ << 4;

		posX += -8 + random.nextInt(8);
		posZ += -8 + random.nextInt(8);

		BlockPos pos = new BlockPos(posX, 64, posZ);

		this.generate(world, random, pos);
	}

}
