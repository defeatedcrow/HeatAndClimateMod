package defeatedcrow.hac.main.worldgen;

import java.util.Random;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.worldgen.OreGenPos.OreVein;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

// 新型
public class WorldGenAltOres2 implements IWorldGenerator {

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
		BlockPos pos = vein.pos;
		int r = vein.round;
		int h = r - 1;
		int[] rands = vein.rands;
		BlockSet[] gen = new BlockSet[h];
		for (int i = 0; i < h; i++) {
			if (i == 0) {
				gen[i] = GYPSUM;
			} else if (i == h - 1) {
				gen[i] = rands[i] > 9 ? IRON_0 : COAL;
			} else {
				if (i <= h / 2) {
					switch (rands[i] & 7) {
					case 0:
					case 1:
						gen[i] = GYPSUM;
						break;
					case 2:
						gen[i] = STONE_2;
						break;
					case 4:
					case 5:
						gen[i] = IRON_0;
						break;
					default:
						gen[i] = COAL;
					}

				} else {
					gen[i] = rands[i] > 9 ? IRON_0 : STONE_2;
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
							BlockSet add = gen[height];
							if (isPlaceable(block)) {
								if (add.equals(STONE_2)) {
									int j = world.rand.nextInt(50);
									if (j == 0) {
										world.setBlockState(p, SAPPHIRE.getState(), 4);
									} else if (j < 10) {
										world.setBlockState(p, CALC_B.getState(), 4);
									} else {
										world.setBlockState(p, add.getState(), 4);
									}
								} else {
									world.setBlockState(p, add.getState(), 4);
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
		BlockPos pos = vein.pos;
		int r = vein.round;
		int h = r - 1;
		int[] rands = vein.rands;
		BlockSet[] gen = new BlockSet[h];
		for (int i = 0; i < h; i++) {
			if (i == 0) {
				gen[i] = LIME;
			} else {
				if (i <= h / 2) {
					switch (vein.rands[i] & 7) {
					case 0:
					case 1:
					case 2:
						gen[i] = BAUXITE;
						break;
					case 3:
					case 4:
						gen[i] = SALT;
						break;
					default:
						gen[i] = LIME;
					}
				} else {
					gen[i] = vein.rands[i] > 9 ? SALT : NITER;
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
							BlockSet add = gen[height];
							if (isPlaceable(block) || isPlaceable2(block)) {
								world.setBlockState(p, add.getState(), 4);
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
		BlockPos pos = vein.pos;
		int r = vein.round;
		int h = r / 2;
		int[] rands = vein.rands;
		BlockSet[] gen = new BlockSet[h];
		for (int i = 0; i < h; i++) {
			if (i == 0) {
				gen[i] = BAUXITE;
			} else {
				gen[i] = rands[i] > 10 ? STONE_1 : BAUXITE;
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
							BlockSet add = gen[height];
							if (isPlaceable(block)) {
								world.setBlockState(p, add.getState(), 4);
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
		BlockPos pos = vein.pos;
		int r = vein.round - 1;
		int h = r / 2;
		int[] rands = vein.rands;
		BlockSet[] gen = new BlockSet[h];
		for (int i = 0; i < h; i++) {
			gen[i] = GUANO;
		}

		for (int x = pos.getX() - r; x < pos.getX() + r; x++) {
			for (int z = pos.getZ() - r; z < pos.getZ() + r; z++) {
				for (int y = pos.getY(); y < pos.getY() + h; y++) {
					if (x > limX && z > limZ) {
						BlockPos p = new BlockPos(x, y, z);
						Block block = world.getBlockState(p).getBlock();
						double d1 = Math.sqrt(pos.distanceSq(p.getX(), pos.getY(), p.getZ()));
						boolean b = world.getBlockState(p.up(1)).getMaterial() == Material.WATER
								|| world.getBlockState(p.up(2)).getMaterial() == Material.WATER;
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && d1 < r && b) {
							int height = p.getY() - pos.getY();
							BlockSet add = gen[height];
							if (isPlaceable3(block)) {
								world.setBlockState(p, add.getState(), 4);
							} else if (block == Blocks.GRAVEL) {
								int j = world.rand.nextInt(3);
								if (j > 0) {
									world.setBlockState(p, add.getState(), 4);
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
		BlockPos pos = vein.pos;
		int r = vein.round;
		int h = r - 1;
		int[] rands = vein.rands;
		BlockSet[] gen = new BlockSet[h];
		for (int i = 0; i < h; i++) {
			if (i == 0 || i == h - 1) {
				gen[i] = STONE_3; // andesite
			} else {
				// 亜鉛 - 銅 - 鉄
				if (i >= h / 2) {
					gen[i] = rands[i] > 9 ? COPPER : IRON_1;
				} else {
					gen[i] = rands[i] > 12 ? ZINC : COPPER;
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
							BlockSet add = gen[height];
							int j = world.rand.nextInt(30);
							// ニッケル、錫の生成チャンス
							if (j == 0) {
								world.setBlockState(p, NICKEL_1.getState(), 4);
							} else if (j == 1) {
								world.setBlockState(p, TIN.getState(), 4);
							} else if (j > 25) {
								world.setBlockState(p, STONE_3.getState(), 4);
							} else {
								world.setBlockState(p, add.getState(), 4);
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
		BlockPos pos = vein.pos;
		int r = vein.round;
		int h = r;
		int[] rands = vein.rands;
		BlockSet[] gen = new BlockSet[h];
		for (int i = 0; i < h; i++) {
			if (i == 0 || i == h - 1) {
				gen[i] = STONE_1; // granite
			} else if (i == 1 || i == h - 2) {
				gen[i] = CALC_W;
			} else {
				// 優先度は 亜鉛>鉄>銀>金
				int j = rands[i] & 7;
				switch (j) {
				case 0:
					gen[i] = CRYSTAL;
					break;
				case 1:
					gen[i] = CALC_SILV;
					break;
				case 2:
					gen[i] = CALC_GOLD;
					break;
				case 3:
				case 4:
				case 5:
					gen[i] = ZINC;
					break;
				default:
					gen[i] = IRON_3;
					break;
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
							BlockSet add = gen[height];
							if (add.equals(ZINC) && world.rand.nextInt(20) == 0) {
								world.setBlockState(p, BISMUTH.getState(), 4);
							} else {
								world.setBlockState(p, add.getState(), 4);
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
		BlockPos pos = vein.pos;
		int r = vein.round;

		// 球状に生成
		BlockPos min = new BlockPos(pos.add(-r, -r, -r));
		BlockPos max = new BlockPos(pos.add(r, r, r));
		Iterable<BlockPos> itr = pos.getAllInBox(min, max);
		int h2 = pos.getY() - r; // 高さの半分
		for (BlockPos p1 : itr) {
			if (p1.getX() > limX && p1.getZ() > limZ) {
				if (p1.getY() <= 1) {
					continue;
				}
				double d1 = Math.sqrt(p1.distanceSq(pos));
				if (d1 > r) {
					continue;
				}
				Block block = world.getBlockState(p1).getBlock();
				if (isPlaceable(block)) {
					int j = world.rand.nextInt(7);
					if (p1.getY() <= pos.getY() || p1.getY() < 5) {
						switch (j) {
						case 0:
							world.setBlockState(p1, SULFUR.getState(), 4);
							break;
						case 1:
						case 2:
							world.setBlockState(p1, SERPENTINE.getState(), 4);
							break;
						case 3:
							world.setBlockState(p1, ALMANDINE.getState(), 4);
							break;
						default:
							world.setBlockState(p1, IRON_2.getState(), 4);
						}
					} else {
						switch (j) {
						case 0:
							world.setBlockState(p1, NICKEL_2.getState(), 4);
							break;
						case 1:
						case 2:
							world.setBlockState(p1, IRON_2.getState(), 4);
							break;
						default:
							world.setBlockState(p1, SERPENTINE.getState(), 4);
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
		BlockPos pos = vein.pos;
		int h = vein.round;
		// 球状に生成
		for (int x = pos.getX() - h; x <= pos.getX() + h; x++) {
			for (int z = pos.getZ() - h; z <= pos.getZ() + h; z++) {
				for (int y = pos.getY() - h; y <= pos.getY() + h; y++) {
					if (x > limX && z > limZ) {
						BlockPos p = new BlockPos(x, y, z);
						double d1 = Math.sqrt(p.distanceSq(pos));
						int r = h + 1 - MathHelper.floor_double(d1);
						if (r < -0.0D) {
							continue;
						}
						Block block = world.getBlockState(p).getBlock();
						if (p.getY() > 1 && p.getY() < world.getActualHeight() && isPlaceable(block)) {
							if (r < 2.0D) {
								world.setBlockState(p, STONE_1.getState(), 4);
							} else if (r < 4.0D) {
								world.setBlockState(p, CALC_W.getState(), 4);
							} else if (r < 5.0D) {
								int j = world.rand.nextInt(10);
								switch (j) {
								case 0:
									world.setBlockState(p, CRYSTAL.getState(), 4);
									break;
								case 1:
									world.setBlockState(p, CALC_DIA.getState(), 4);
									break;
								case 2:
									world.setBlockState(p, CALC_EME.getState(), 4);
									break;
								case 3:
									world.setBlockState(p, SCHORL.getState(), 4);
									break;
								case 4:
								case 5:
									world.setBlockState(p, RUTILE.getState(), 4);
									break;
								case 6:
								case 7:
									world.setBlockToAir(p);
									break;
								case 8:
									world.setBlockState(p, CALC_SILV.getState(), 2);
									break;
								default:
									world.setBlockState(p, CALC_W.getState(), 2);
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

	private static final BlockSet AIR = new BlockSet(Blocks.AIR, 0);

	private static final BlockSet LAVA = new BlockSet(Blocks.LAVA, 0);
	private static final BlockSet STONE_0 = new BlockSet(Blocks.STONE, 0);
	private static final BlockSet STONE_1 = new BlockSet(Blocks.STONE, 1);
	private static final BlockSet STONE_2 = new BlockSet(Blocks.STONE, 3);
	private static final BlockSet STONE_3 = new BlockSet(Blocks.STONE, 5);
	private static final BlockSet GRAVEL = new BlockSet(Blocks.GRAVEL, 0);

	private static final BlockSet LIME = new BlockSet(MainInit.ores_2, 0);
	private static final BlockSet MARBLE = new BlockSet(MainInit.gemBlock, 6);
	private static final BlockSet GYPSUM = new BlockSet(MainInit.ores, 0);
	private static final BlockSet CALC_B = new BlockSet(MainInit.ores, 2);
	private static final BlockSet CALC_W = new BlockSet(MainInit.ores, 9);

	private static final BlockSet IRON_0 = new BlockSet(MainInit.ores, 1);
	private static final BlockSet IRON_1 = new BlockSet(MainInit.ores, 4);
	private static final BlockSet IRON_2 = new BlockSet(MainInit.ores, 5);
	private static final BlockSet IRON_3 = new BlockSet(MainInit.ores, 15);

	private static final BlockSet GOLD = new BlockSet(Blocks.GOLD_ORE, 0);
	private static final BlockSet COAL = new BlockSet(Blocks.COAL_ORE, 0);

	private static final BlockSet COPPER = new BlockSet(MainInit.ores, 6);
	private static final BlockSet ZINC = new BlockSet(MainInit.ores, 8);
	private static final BlockSet TIN = new BlockSet(MainInit.ores_2, 4);
	private static final BlockSet BISMUTH = new BlockSet(MainInit.ores_2, 9);
	private static final BlockSet NICKEL_1 = new BlockSet(MainInit.ores, 7);
	private static final BlockSet NICKEL_2 = new BlockSet(MainInit.ores_2, 8);

	private static final BlockSet SAPPHIRE = new BlockSet(MainInit.ores, 3);
	private static final BlockSet CRYSTAL = new BlockSet(MainInit.ores, 10);
	private static final BlockSet SALT = new BlockSet(MainInit.ores_2, 1);
	private static final BlockSet NITER = new BlockSet(MainInit.ores_2, 2);
	private static final BlockSet SULFUR = new BlockSet(MainInit.ores_2, 3);
	private static final BlockSet SCHORL = new BlockSet(MainInit.ores_2, 5);
	private static final BlockSet SERPENTINE = new BlockSet(MainInit.ores_2, 6);
	private static final BlockSet ALMANDINE = new BlockSet(MainInit.ores_2, 7);
	private static final BlockSet BAUXITE = new BlockSet(MainInit.ores_2, 10);
	private static final BlockSet RUTILE = new BlockSet(MainInit.ores_2, 11);
	private static final BlockSet CALC_GOLD = new BlockSet(MainInit.ores, 11);
	private static final BlockSet CALC_SILV = new BlockSet(MainInit.ores, 12);
	private static final BlockSet CALC_DIA = new BlockSet(MainInit.ores, 13);
	private static final BlockSet CALC_EME = new BlockSet(MainInit.ores, 14);
	private static final BlockSet GUANO = new BlockSet(MainInit.ores_2, 12);

}
