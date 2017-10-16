package defeatedcrow.hac.main.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenWindmill implements IWorldGenerator {

	private final boolean isForced;
	private int range = -1;
	private int forceX = 0;
	private int forceZ = 0;

	private static Random pRandom;

	public WorldGenWindmill(boolean force) {
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
		generateWindmill(random, chunkZ, chunkZ, world, chunkGenerator, chunkProvider);
	}

	public boolean generateWindmill(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		pRandom = new Random(world.getSeed() + chunkX + chunkZ * 31);

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

		int posX = chunkX << 4;
		int posZ = chunkZ << 4;

		posX += 6 + pRandom.nextInt(4);
		posZ += 6 + pRandom.nextInt(4);
		if (isForced && forceX != 0 & forceZ != 0) {
			posX = forceX;
			posZ = forceZ;
		}
		BlockPos pos = new BlockPos(posX, 60, posZ);

		Biome biome = world.getBiomeForCoordsBody(pos);

		if (world.villageCollection.getNearestVillage(pos, 6) != null)
			return false;

		if (isForced || BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS)
				|| BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY)) {
			// 高度選定
			int h = -1;
			for (int y = 2; y < 255; y++) {
				BlockPos p1 = new BlockPos(posX, y, posZ);
				if (!isReplaceable(world, p1) && isReplaceable(world, p1.up())
						&& (world.canSeeSky(p1.up()) || world.getBlockState(p1).getMaterial() == Material.GRASS)) {
					h = y;
					break;
				}
			}

			if (h > 2) {
				// 補正
				int i1 = 0; // 傾斜確認
				int adj = 0;
				for (int i = -2; i < 3; i++) {
					for (int j = -2; j < 3; j++) {
						BlockPos c1 = new BlockPos(posX + i, h, posZ + j);
						if (!isReplaceable(world, c1.up(2)) || isReplaceable(world, c1.down(2))) {
							i1 += 1;
						} else {
							if (isReplaceable(world, c1.down())) {
								adj += -1;
							} else if (!isReplaceable(world, c1) && !isReplaceable(world, c1.up())) {
								adj += 1;
							}
						}
					}
				}

				// 平地にしか生成しない
				if (i1 < 6) {
					if (adj < -13) {
						h -= 1;
					} else if (adj > 12) {
						h += 1;
					}

					DCLogger.debugLog("Windmill House target pos2: " + posX + ", " + h + "," + posZ);
					BlockPos main = new BlockPos(posX, h, posZ);
					generate(world, main);
					return true;
				}
			}
		}
		return false;
	}

	// 西向き固定
	public void generate(World world, BlockPos pos) {
		// クリア
		for (int i1 = -5; i1 < 6; i1++) {
			for (int j1 = -5; j1 < 6; j1++) {
				for (int k1 = 1; k1 < 15; k1++) {
					world.setBlockToAir(pos.add(i1, k1, j1));
				}
			}
		}

		// 土台から
		// brick
		for (int i1 = -3; i1 < 4; i1++) {
			for (int j1 = -3; j1 < 4; j1++) {
				world.setBlockState(pos.add(i1, 0, j1), Blocks.BRICK_BLOCK.getDefaultState(), 2);
			}
		}
		for (int k1 = 1; k1 < 3; k1++) {
			for (int j1 = -3; j1 < 4; j1++) {
				world.setBlockState(pos.add(-3, k1, j1), Blocks.BRICK_BLOCK.getDefaultState(), 2);
				world.setBlockState(pos.add(3, k1, j1), Blocks.BRICK_BLOCK.getDefaultState(), 2);
			}
			for (int i1 = -2; i1 < 3; i1++) {
				world.setBlockState(pos.add(i1, k1, -3), Blocks.BRICK_BLOCK.getDefaultState(), 2);
				world.setBlockState(pos.add(i1, k1, 3), Blocks.BRICK_BLOCK.getDefaultState(), 2);
			}
		}
		// plank
		for (int i2 = -5; i2 < 6; i2++) {
			for (int j2 = -5; j2 < 6; j2++) {
				world.setBlockState(pos.add(i2, 3, j2), Blocks.PLANKS.getDefaultState(), 2);
			}
		}
		for (int i2 = -5; i2 < 6; i2++) {
			world.setBlockState(pos.add(i2, 4, -5), Blocks.OAK_FENCE.getDefaultState(), 2);
			world.setBlockState(pos.add(i2, 4, 5), Blocks.OAK_FENCE.getDefaultState(), 2);
		}
		for (int i2 = -4; i2 < 5; i2++) {
			world.setBlockState(pos.add(5, 4, i2), Blocks.OAK_FENCE.getDefaultState(), 2);
		}
		world.setBlockState(pos.add(-5, 4, 4), Blocks.OAK_FENCE.getDefaultState(), 2);
		world.setBlockState(pos.add(-5, 4, -4), Blocks.OAK_FENCE.getDefaultState(), 2);

		// 柱と壁
		IBlockState strO = Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST);
		for (int k3 = 0; k3 < 3; k3++) {
			IBlockState logY = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
			world.setBlockState(pos.add(-2, 4 + k3, -2), logY, 2);
			world.setBlockState(pos.add(-2, 4 + k3, 2), logY, 2);
			world.setBlockState(pos.add(2, 4 + k3, -2), logY, 2);
			world.setBlockState(pos.add(2, 4 + k3, 2), logY, 2);
			for (int i3 = -1; i3 < 2; i3++) {
				world.setBlockState(pos.add(i3, 4 + k3, -2), Blocks.PLANKS.getDefaultState(), 2);
				world.setBlockState(pos.add(i3, 4 + k3, 2), Blocks.PLANKS.getDefaultState(), 2);
				world.setBlockState(pos.add(2, 4 + k3, i3), Blocks.PLANKS.getDefaultState(), 2);

				world.setBlockState(pos.add(-8 + k3, 1 + k3, i3), strO, 2);
			}
			world.setBlockState(pos.add(-2, 4 + k3, -1), Blocks.PLANKS.getDefaultState(), 2);
			world.setBlockState(pos.add(-2, 4 + k3, 1), Blocks.PLANKS.getDefaultState(), 2);
		}
		world.setBlockState(pos.add(-2, 4, 0),
				Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.EAST)
						.withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT),
				2);
		world.setBlockState(pos.add(-2, 5, 0),
				Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.FACING, EnumFacing.EAST)
						.withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT)
						.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER),
				2);
		world.setBlockState(pos.add(-2, 6, 0), Blocks.PLANKS.getDefaultState(), 2);
		world.setBlockState(pos.add(2, 7, -1), Blocks.PLANKS.getDefaultState(), 2);
		world.setBlockState(pos.add(2, 7, 1), Blocks.PLANKS.getDefaultState(), 2);
		world.setBlockState(pos.add(2, 8, 0), Blocks.PLANKS.getDefaultState(), 2);
		world.setBlockState(pos.add(-2, 7, -1), Blocks.PLANKS.getDefaultState(), 2);
		world.setBlockState(pos.add(-2, 7, 0), Blocks.PLANKS.getDefaultState(), 2);
		world.setBlockState(pos.add(-2, 7, 1), Blocks.PLANKS.getDefaultState(), 2);
		world.setBlockState(pos.add(-2, 8, 0), Blocks.PLANKS.getDefaultState(), 2);

		// 屋根
		IBlockState slab = Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT,
				BlockStoneSlab.EnumType.BRICK);
		world.setBlockState(pos.add(-3, 6, -1), slab, 2);
		world.setBlockState(pos.add(-3, 6, 1), slab, 2);
		world.setBlockState(pos.add(-3, 6, 0), slab, 2);

		IBlockState strL = Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
		IBlockState strR = Blocks.BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
		for (int j3 = -2; j3 < 3; j3++) {
			world.setBlockState(pos.add(j3, 6, -3), strL, 2);
			world.setBlockState(pos.add(j3, 7, -2), strL, 2);
			world.setBlockState(pos.add(j3, 8, -1), strL, 2);
			world.setBlockState(pos.add(j3, 6, 3), strR, 2);
			world.setBlockState(pos.add(j3, 7, 2), strR, 2);
			world.setBlockState(pos.add(j3, 8, 1), strR, 2);
			world.setBlockState(pos.add(j3, 9, 0), slab, 2);
		}
		world.setBlockState(pos.add(0, 9, 0), Blocks.BRICK_BLOCK.getDefaultState(), 2);
		world.setBlockState(pos.add(0, 10, 0), MainInit.windvane.getDefaultState(), 2);

		// 内装
		for (int i4 = 0; i4 < 3; i4++) {
			world.setBlockState(pos.add(-1, 1 + i4, -2), Blocks.PLANKS.getDefaultState(), 2);
			world.setBlockState(pos.add(-1, 1 + i4, -1),
					Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH), 2);
		}

		world.setBlockState(pos.add(-1, 6, 0), MainInit.wallLamp.getDefaultState()
				.withProperty(DCState.FACING, EnumFacing.EAST).withProperty(DCState.TYPE4, 3), 2);
		world.setBlockState(pos.add(-2, 2, 0), MainInit.wallLamp.getDefaultState()
				.withProperty(DCState.FACING, EnumFacing.EAST).withProperty(DCState.TYPE4, 3), 2);

		// マシン
		world.setBlockState(pos.add(1, 4, 0), MachineInit.stonemill.getDefaultState(), 2);
		if (pRandom.nextInt(8) > 0) {
			world.setBlockState(pos.add(1, 5, 0),
					MachineInit.shaft_s.getDefaultState().withProperty(DCState.SIDE, EnumSide.UP), 2);
		}
		if (pRandom.nextInt(8) > 0) {
			world.setBlockState(pos.add(1, 6, 0),
					MachineInit.shaft_s.getDefaultState().withProperty(DCState.SIDE, EnumSide.UP), 2);
		}
		world.setBlockState(pos.add(1, 7, 0),
				MachineInit.shaft_l.getDefaultState().withProperty(DCState.SIDE, EnumSide.EAST), 2);
		TileEntity shaft = world.getTileEntity(pos.add(1, 7, 0));
		if (shaft != null) {
			((TileTorqueBase) shaft).setFaceSide(EnumFacing.SOUTH);
		}
		world.setBlockState(pos.add(2, 7, 0),
				MachineInit.gearbox.getDefaultState().withProperty(DCState.SIDE, EnumSide.EAST), 2);
		world.setBlockState(pos.add(3, 7, 0),
				MachineInit.windmill_l.getDefaultState().withProperty(DCState.SIDE, EnumSide.WEST), 2);

		// 宝箱
		world.setBlockState(pos.add(2, 1, -2),
				Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.WEST), 2);
		TileEntity chest = world.getTileEntity(pos.add(2, 1, -2));
		if (chest != null && chest instanceof TileEntityChest) {
			for (int l = 0; l < 27; l++) {
				int r = pRandom.nextInt(80);
				if (r < loot.size()) {
					ItemStack ret = loot.get(r);
					((TileEntityChest) chest).setInventorySlotContents(l, ret);
				}
			}
		}
	}

	// 1/200
	private boolean canGenerate(int chunkX, int chunkZ, World world) {
		if (isForced)
			return true;
		int i = WorldGenConfig.windmillGen;
		pRandom.nextFloat();
		float r = pRandom.nextFloat() * 1000;
		if (r > 0 && r < i)
			// SkarnGenPoint.addPos(chunkX, chunkZ);
			return true;
		return false;
	}

	public boolean isReplaceable(World world, BlockPos pos) {
		net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
		return state.getBlock().isAir(state, world, pos) || state.getMaterial().isReplaceable();
	}

	public static List<ItemStack> loot = new ArrayList<ItemStack>();

	public static void initLoot() {
		loot.add(new ItemStack(MainInit.oreIngot, 2, 4));
		loot.add(new ItemStack(MainInit.oreIngot, 3, 4));
		loot.add(new ItemStack(MainInit.oreIngot, 3, 4));
		loot.add(new ItemStack(MainInit.oreIngot, 3, 4));
		loot.add(new ItemStack(MainInit.oreIngot, 4, 4));
		loot.add(new ItemStack(MainInit.oreIngot, 4, 4));
		loot.add(new ItemStack(MainInit.oreIngot, 5, 4));
		loot.add(new ItemStack(MainInit.oreIngot, 3, 3));
		loot.add(new ItemStack(MainInit.oreIngot, 5, 3));
		loot.add(new ItemStack(MainInit.oreIngot, 5, 6));
		loot.add(new ItemStack(MainInit.oreDust, 6, 0));
		loot.add(new ItemStack(MainInit.oreDust, 12, 0));
		loot.add(new ItemStack(MainInit.oreDust, 6, 1));
		loot.add(new ItemStack(MainInit.oreDust, 12, 1));
		loot.add(new ItemStack(MainInit.gems, 3, 0));
		loot.add(new ItemStack(MainInit.gems, 3, 1));
		loot.add(new ItemStack(MainInit.gems, 3, 2));
		loot.add(new ItemStack(MainInit.gems, 4, 3));
		loot.add(new ItemStack(MainInit.gems, 8, 3));
		loot.add(new ItemStack(MainInit.wrench, 1, 0));
		loot.add(new ItemStack(MainInit.wrench, 1, 0));
		loot.add(new ItemStack(MainInit.materials, 2, 2));
		loot.add(new ItemStack(MainInit.materials, 2, 3));
		loot.add(new ItemStack(MainInit.materials, 1, 4));
		loot.add(new ItemStack(MainInit.materials, 1, 4));
		loot.add(new ItemStack(MainInit.materials, 1, 5));
		loot.add(new ItemStack(MainInit.materials, 1, 5));
		loot.add(new ItemStack(MainInit.bakedApple, 3, 2));
		loot.add(new ItemStack(MainInit.bakedApple, 3, 3));
		loot.add(new ItemStack(MachineInit.shaft_s, 1, 0));
		loot.add(new ItemStack(MachineInit.shaft_s, 1, 0));
		loot.add(new ItemStack(MachineInit.shaft_s, 1, 0));
		loot.add(new ItemStack(MachineInit.shaft_l, 1, 0));
	}

}
