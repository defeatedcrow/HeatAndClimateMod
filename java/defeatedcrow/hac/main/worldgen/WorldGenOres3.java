package defeatedcrow.hac.main.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.orevein.OreSet;
import defeatedcrow.hac.main.worldgen.OreGenPos.OreVein;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

// 新型
public class WorldGenOres3 implements IWorldGenerator {

	private static final boolean debug = false;

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
		if ((genDim1 != 1 && genDim1 != -1)) {
			OreVein[] veins = OreGenPos.INSTANCE.getVeins(chunkX, chunkZ, world);
			if (veins != null) {
				for (int i = 0; i < veins.length; i++) {
					OreVein vein = veins[i];
					if (vein != null) {
						switch (vein.type) {
						case BAUXITE:
							generateBauxite(world, vein, posX1, posZ1);
							break;
						case GEODE:
							generateVugs(world, vein, posX1, posZ1);
							break;
						case HIGH_SEDIMENT:
							generateSediment(world, vein, posX1, posZ1);
							break;
						case KIESLAGER:
							generateKieslager(world, vein, posX1, posZ1);
							break;
						case QUARTZ:
							generateQuartzVein(world, vein, posX1, posZ1);
							break;
						case SAND_SEDIMENT:
							generateSandSediment(world, vein, posX1, posZ1);
							break;
						case SEDIMENT:
							generateSediment(world, vein, posX1, posZ1);
							break;
						case UNDERLAVA:
							generateUnderlava(world, vein, posX1, posZ1);
							break;
						case GUANO:
							generateGuano(world, vein, posX1, posZ1);
							break;
						default:
							break;
						}
					}
				}
			}
		}

	}

	static boolean isPlaceable(Block block) {
		if (block == Blocks.STONE)
			return true;
		if (block == Blocks.GRAVEL)
			return true;
		if (block == Blocks.DIRT)
			return true;
		if (block == Blocks.SANDSTONE)
			return true;
		if (block == Blocks.SAND)
			return true;
		if (block == Blocks.GRASS)
			return true;

		return debug;
	}

	static boolean isPlaceable2(Block block) {
		if (block == Blocks.STAINED_HARDENED_CLAY)
			return true;
		if (block == Blocks.HARDENED_CLAY)
			return true;

		return debug;
	}

	static boolean isPlaceable3(Block block) {
		if (block == Blocks.SAND)
			return true;
		if (block == Blocks.SANDSTONE)
			return true;
		if (block == Blocks.DIRT)
			return true;

		return debug;
	}

	/*
	 * 堆積岩。山岳の高高度に生成する。
	 * これを集めるだけでも生きてはいけるが、銅や亜鉛はここにはない。
	 */
	public void generateSediment(World world, OreVein vein, int limX, int limZ) {
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
			if (i > h / 2) {
				list = table.getOreTable1();
				world.rand.nextInt(table.tableCount1);
			} else {
				list = table.getOreTable2();
				i1 = world.rand.nextInt(table.tableCount2);
			}

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

		for (int x = pos.getX() - r; x < pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z < pos.getZ() + r; z++) {
				for (int y = pos.getY(); y < pos.getY() + h; y++) {
					if (x > limX && z > limZ) {
						BlockPos p = new BlockPos(x, y, z);
						Block block = world.getBlockState(p).getBlock();
						double d1 = Math.sqrt(pos.distanceSq(p.getX(), pos.getY(), p.getZ()));
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && d1 < r) {
							int height = p.getY() - pos.getY();
							OreSet add = gen[height];
							if (isPlaceable(block)) {
								int j = world.rand.nextInt(100);
								if (add.hasSecondOre() && j < add.getSecondChance()) {
									world.setBlockState(p, add.getSecondOre().getState(), 4);
								} else {
									if (add.getOre().equals(CHAL_B) && j > 90) {
										world.setBlockState(p, SAPPHIRE.getState(), 4);
									} else {
										world.setBlockState(p, add.getOre().getState(), 4);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/*
	 * 堆積岩。砂漠かメサにのみ生成。
	 * 料理に必要な岩塩などがある。
	 */
	public void generateSandSediment(World world, OreVein vein, int limX, int limZ) {
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
			if (i == 0) {
				gen[i] = table.layerStone1;
			} else {
				List<OreSet> list = new ArrayList<OreSet>();
				int i1 = 0;
				if (i > h / 2) {
					list = table.getOreTable1();
					world.rand.nextInt(table.tableCount1);
				} else {
					list = table.getOreTable2();
					i1 = world.rand.nextInt(table.tableCount2);
				}
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
				for (int y = pos.getY() - h + 2; y <= pos.getY() + 1; y++) {
					if (x > limX && z > limZ) {
						BlockPos p = new BlockPos(x, y, z);
						Block block = world.getBlockState(p).getBlock();
						double d1 = Math.sqrt(pos.distanceSq(p.getX(), pos.getY(), p.getZ()));
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && d1 < r) {
							int height = pos.getY() + 1 - p.getY();
							OreSet add = gen[height];
							if (isPlaceable(block) || isPlaceable2(block)) {
								int j = world.rand.nextInt(100);
								if (add.hasSecondOre() && j < add.getSecondChance()) {
									world.setBlockState(p, add.getSecondOre().getState(), 4);
								} else {
									world.setBlockState(p, add.getOre().getState(), 4);
								}
							}
						}
					}
				}
			}
		}
	}

	/*
	 * ボーキサイト。ジャングルかサバンナにのみ生成。
	 */
	public void generateBauxite(World world, OreVein vein, int limX, int limZ) {
		VeinTable table = VeinTableRegister.INSTANCE.getTable(vein.type);
		if (table == null) {
			return;
		}
		BlockPos pos = vein.pos;
		int r = vein.round;
		int h = r / 2;
		int[] rands = vein.rands;
		OreSet[] gen = new OreSet[h];
		for (int i = 0; i < h; i++) {
			if (i == 0) {
				gen[i] = table.layerStone1;
			} else {
				List<OreSet> list = table.getOreTable1();
				int i1 = world.rand.nextInt(table.tableCount1);
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
						Block block = world.getBlockState(p).getBlock();
						double d1 = Math.sqrt(pos.distanceSq(p.getX(), pos.getY(), p.getZ()));
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && d1 < r) {
							int height = p.getY() - pos.getY();
							OreSet add = gen[height];
							if (isPlaceable(block)) {
								int j = world.rand.nextInt(100);
								if (add.hasSecondOre() && j < add.getSecondChance()) {
									world.setBlockState(p, add.getSecondOre().getState(), 4);
								} else {
									world.setBlockState(p, add.getOre().getState(), 4);
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
		int h = r / 2;
		int[] rands = vein.rands;
		OreSet[] gen = new OreSet[h];
		for (int i = 0; i < h; i++) {
			if (i == 0) {
				gen[i] = table.layerStone1;
			} else {
				List<OreSet> list = table.getOreTable1();
				int i1 = world.rand.nextInt(table.tableCount1);
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
						Block block = world.getBlockState(p).getBlock();
						double d1 = Math.sqrt(pos.distanceSq(p.getX(), pos.getY(), p.getZ()));
						boolean b1 = world.getBlockState(p.up(1)).getMaterial() == Material.WATER;
						boolean b2 = world.getBlockState(p.up(2)).getMaterial() == Material.WATER;
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && d1 < r) {
							if (b1 || b2) {
								int height = p.getY() - pos.getY();
								OreSet add = gen[height];
								if (isPlaceable(block)) {
									int j = world.rand.nextInt(100);
									if (add.hasSecondOre() && j < add.getSecondChance()) {
										world.setBlockState(p, add.getSecondOre().getState(), 4);
										if (b1) {
											world.setBlockState(p.up(), FoodInit.cropSeaweed.getDefaultState());
										}
									} else {
										world.setBlockState(p, add.getOre().getState(), 4);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/*
	 * キースラガー。山岳と海底に生成。
	 * 本来は玄武岩など塩基性寄りのはずなんだが...
	 */
	public void generateKieslager(World world, OreVein vein, int limX, int limZ) {
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
			if (i == 0) {
				gen[i] = table.layerStone1;
			} else {
				List<OreSet> list = new ArrayList<OreSet>();
				int i1 = 0;
				if (i > h / 2) {
					list = table.getOreTable1();
					world.rand.nextInt(table.tableCount1);
				} else {
					list = table.getOreTable2();
					i1 = world.rand.nextInt(table.tableCount2);
				}
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

		// 柱状に生成する
		for (int x = pos.getX() - r; x < pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z < pos.getZ() + r; z++) {
				for (int y = pos.getY() - h + 2; y <= pos.getY() + 1; y++) {
					if (x > limX && z > limZ) {
						BlockPos p = new BlockPos(x, y, z);
						Block block = world.getBlockState(p).getBlock();
						double d1 = Math.sqrt(pos.distanceSq(p.getX(), pos.getY(), p.getZ()));
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && isPlaceable(block) && d1 < r) {
							int height = pos.getY() + 1 - p.getY();
							OreSet add = gen[height];
							if (isPlaceable(block)) {
								int j = world.rand.nextInt(100);
								if (add.hasSecondOre() && j < add.getSecondChance()) {
									world.setBlockState(p, add.getSecondOre().getState(), 4);
								} else {
									world.setBlockState(p, add.getOre().getState(), 4);
								}
							}
						}
					}
				}
			}
		}
	}

	/*
	 * 石英脈。キースラガーが生成しないバイオームに出現。
	 * サンドイッチ状の層構造とライン状の鉱脈を伴う。
	 * 出現率は低く、生成も固有鉱石はない。山がないときの救済用。
	 */
	public void generateQuartzVein(World world, OreVein vein, int limX, int limZ) {
		VeinTable table = VeinTableRegister.INSTANCE.getTable(vein.type);
		if (table == null) {
			return;
		}
		BlockPos pos = vein.pos;
		int r = vein.round;
		int h = r;
		int[] rands = vein.rands;
		OreSet[] gen = new OreSet[h];
		for (int i = 0; i < h; i++) {
			if (i == 0 || i == h - 1) {
				gen[i] = table.layerStone1; // granite
			} else if (i == 1 || i == h - 2) {
				gen[i] = table.layerStone2;
			} else {
				List<OreSet> list = new ArrayList<OreSet>();
				int i1 = 0;
				if (i > h / 2) {
					list = table.getOreTable1();
					world.rand.nextInt(table.tableCount1);
				} else {
					list = table.getOreTable2();
					i1 = world.rand.nextInt(table.tableCount2);
				}
				int i2 = 0;
				for (OreSet set : list) {
					i2 += set.getWeight();
					if (i2 >= i1) {
						gen[i] = set;
						break;
					}
				}
				if (gen[i] == null) {
					gen[i] = list.get(0);
				}
			}
		}

		// 円柱状に生成
		for (int x = pos.getX() - r; x < pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z < pos.getZ() + r; z++) {
				for (int y = pos.getY() - h + 2; y <= pos.getY() + 1; y++) {
					if (x > limX && z > limZ) {
						BlockPos p = new BlockPos(x, y, z);
						double d1 = Math.sqrt(pos.distanceSq(p.getX(), pos.getY(), p.getZ()));
						Block block = world.getBlockState(p).getBlock();
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && isPlaceable(block) && d1 < r) {
							int height = pos.getY() + 1 - p.getY();
							OreSet add = gen[height];
							if (isPlaceable(block)) {
								int j = world.rand.nextInt(100);
								if (add.hasSecondOre() && j < add.getSecondChance()) {
									world.setBlockState(p, add.getSecondOre().getState(), 4);
								} else {
									world.setBlockState(p, add.getOre().getState(), 4);
								}
							}
						}
					}
				}
			}
		}
	}

	/*
	 * マグマ底床。マグマ帯の周囲に生成する。
	 * 非常に探しづらいが、ニッケルの入手手段のひとつになる。
	 */
	public void generateUnderlava(World world, OreVein vein, int limX, int limZ) {
		VeinTable table = VeinTableRegister.INSTANCE.getTable(vein.type);
		if (table == null) {
			return;
		}
		BlockPos pos = vein.pos;
		int r = vein.round;
		int h = r - 1;
		int[] rands = vein.rands;

		// 球状に生成
		for (int x = pos.getX() - r; x < pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z < pos.getZ() + r; z++) {
				for (int y = pos.getY() - r; y <= pos.getY() + r; y++) {
					if (x > limX && z > limZ) {
						BlockPos p = new BlockPos(x, y, z);
						double d1 = Math.sqrt(p.distanceSq(pos));
						if (d1 >= r) {
							continue;
						}
						Block block = world.getBlockState(p).getBlock();
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && isPlaceable(block)) {
							int height = pos.getY() + 1 - p.getY();
							OreSet add = null;
							int j = world.rand.nextInt(table.tableCount1);
							if (isPlaceable(block)) {
								List<OreSet> list = new ArrayList<OreSet>();
								if (p.getY() <= pos.getY() || p.getY() < 5) {
									list = table.getOreTable2();
								} else {
									list = table.getOreTable1();
									j = world.rand.nextInt(table.tableCount2);
								}
								int i2 = 0;
								for (OreSet set : list) {
									i2 += set.getWeight();
									if (i2 >= j) {
										add = set;
										break;
									}
								}
								if (add == null) {
									add = list.get(0);
								}
								if (add.hasSecondOre() && world.rand.nextInt(100) < add.getSecondChance()) {
									world.setBlockState(p, add.getSecondOre().getState(), 4);
								} else {
									world.setBlockState(p, add.getOre().getState(), 4);
								}
							}
						}
					}
				}
			}
		}

		// DCLogger.debugLog("Ore gen! Underlava:" + pos.getX() + "," + pos.getY() + "," +
		// pos.getZ() + ", size: " + h);
	}

	/*
	 * 晶洞。マグマの上に生成する。
	 * 球状に生成し、中央に空洞を持つ。
	 * なかなか探しにくいが、引き当てるとバニラ宝石類が大量に手に入る。
	 */
	public void generateVugs(World world, OreVein vein, int limX, int limZ) {
		VeinTable table = VeinTableRegister.INSTANCE.getTable(vein.type);
		if (table == null) {
			return;
		}
		BlockPos pos = vein.pos;
		int h = vein.round;
		// 球状に生成
		for (int x = pos.getX() - h; x <= pos.getX() + h; x++) {
			for (int z = pos.getZ() - h; z <= pos.getZ() + h; z++) {
				for (int y = pos.getY() - h; y <= pos.getY() + h; y++) {
					if (x > limX && z > limZ) {
						BlockPos p = new BlockPos(x, y, z);
						double d1 = Math.sqrt(p.distanceSq(pos));
						int r = h + 1 - MathHelper.floor(d1);
						if (r < -0.0D) {
							continue;
						}
						Block block = world.getBlockState(p).getBlock();
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && isPlaceable(block)) {
							if (r < 2.0D) {
								world.setBlockState(p, table.layerStone1.getOre().getState(), 4);
							} else if (r < 4.0D) {
								world.setBlockState(p, table.layerStone2.getOre().getState(), 4);
							} else if (r < 5.0D) {
								List<OreSet> list = table.getOreTable1();
								int i1 = world.rand.nextInt(table.tableCount1);
								int i2 = 0;
								OreSet add = null;
								for (OreSet set : list) {
									i2 += set.getWeight();
									if (i2 >= i1) {
										add = set;
										break;
									}
									if (add == null) {
										add = list.get(0);
									}
								}

								if (add.hasSecondOre() && world.rand.nextInt(100) < add.getSecondChance()) {
									world.setBlockState(p, add.getSecondOre().getState(), 4);
								} else {
									world.setBlockState(p, add.getOre().getState(), 4);
								}
							} else {
								world.setBlockToAir(p);
							}
						}
					}
				}
			}
		}
	}

	// 以下はバニラ改変
	public void generateStoneLayer(World world, Random rand, int chunkX, int chunkZ) {
		// 花崗岩帯をY40位に追加
		DCLogger.debugLog("stone");
		int h2 = 40 + rand.nextInt(5);
		int r2 = 3 + rand.nextInt(2);
		BlockPos min2 = new BlockPos(new BlockPos(chunkX, h2, chunkZ));
		BlockPos max2 = new BlockPos(new BlockPos(chunkX + 15, h2 + r2, chunkZ + 15));
		Iterable<BlockPos> itr2 = BlockPos.getAllInBox(min2, max2);
		for (BlockPos p1 : itr2) {
			Block block = world.getBlockState(p1).getBlock();
			if (p1.getY() > 1 && p1.getY() < world.getActualHeight() && isPlaceable(block)) {
				BlockSet add = new BlockSet(Blocks.STONE, 1);
				world.setBlockState(p1, add.getState(), 4);
			}
		}
	}

	private static final BlockSet CHAL_B = new BlockSet(MainInit.ores, 2);
	private static final BlockSet SAPPHIRE = new BlockSet(MainInit.ores, 3);
}
