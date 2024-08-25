package defeatedcrow.hac.core.worldgen;

import java.util.Random;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;

public class SpringFeature extends Feature<NoneFeatureConfiguration> {

	private boolean isForced = false;

	public SpringFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
		isForced = false;
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		if (!ConfigCommonBuilder.INSTANCE.enCustomSpring.get())
			return false;

		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		// Contextの座標は使用しない
		ChunkPos chunk = level.getChunk(pos).getPos();
		long seed = level.getSeed() + chunk.x + chunk.z * 31;
		Random random = new Random(seed);

		random.nextInt(1000);
		int rate = ConfigCommonBuilder.INSTANCE.vSpringFeature.get() * 10;
		if (random.nextInt(1000) < rate) {
			return false;
		}

		int surface = level.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getMiddleBlockX(), chunk.getMiddleBlockZ());
		BlockPos p = new BlockPos(chunk.getMiddleBlockX(), 65, chunk.getMiddleBlockZ());
		Holder<Biome> biome = level.getBiome(p);

		SpringTable table = BLACK;
		if (biome.containsTag(TagDC.BiomeTag.BLUE_BIOME)) {
			table = BLUE;
		} else if (biome.containsTag(TagDC.BiomeTag.RED_BIOME) && !biome.containsTag(Tags.Biomes.IS_COLD)) {
			table = RED;
		} else if (biome.containsTag(TagDC.BiomeTag.GREEN_BIOME)) {
			table = GREEN;
		} else if (biome.containsTag(Tags.Biomes.IS_DEAD) || biome.containsTag(BiomeTags.IS_OCEAN) || biome.is(Tags.Biomes.IS_UNDERGROUND)) {
			table = BLACK;
		}

		BlockPos.MutableBlockPos mp = new BlockPos.MutableBlockPos();
		// 高度選定
		int height = 0;
		if (table == BLACK) {
			for (int j = surface; j > surface - 30; j--) {

				mp.set(chunk.getMiddleBlockX(), j, chunk.getMiddleBlockZ());
				BlockState check = level.getBlockState(mp);
				BlockState under = level.getBlockState(mp.below());
				if (!under.isAir() && isPlaceable(under) && check.getMaterial().isLiquid()) {
					height = j;
					break;
				}

				if (!check.isAir() && isPlaceable(check) && (under.getMaterial().isLiquid() || under.isAir())) {
					height = j;
					break;
				}
			}
			if (height > surface) {
				return false;
			}
		} else {
			for (int j = surface + 100; j > surface - 10; j--) {

				mp.set(chunk.getMiddleBlockX(), j, chunk.getMiddleBlockZ());
				BlockState check = level.getBlockState(mp);
				if (!check.isAir() && isPlaceable(check)) {
					height = j;
					break;
				}
				if (check.getMaterial().isLiquid()) {
					height = 0;
					break;
				}
			}
			if (height < 45) {
				return false;
			}
		}

		// 中心点
		p = new BlockPos(chunk.getMiddleBlockX(), height - 1, chunk.getMiddleBlockZ());
		int r = 5 + random.nextInt(3);
		double r1 = r - 1D;
		double r2 = r - 3D;

		if (table == BLACK) {
			for (int x = -r; x <= r; x++) {
				for (int z = -r; z <= r; z++) {
					for (int y = -4; y <= 2; y++) {
						mp.set(p.getX() + x, p.getY() + y, p.getZ() + z);
						BlockState st = level.getBlockState(mp);
						if (st.isAir() || st.getMaterial().isReplaceable()) {
							continue;
						}
						double d = Math.sqrt(x * x + y * y + z * z);
						double d2 = random.nextDouble() * 0.5D;
						if (d + d2 < r2) {
							if (isGroundBlock(st))
								level.setBlock(mp, table.topStone().get().defaultBlockState(), 2);
						} else if (d + d2 < r) {
							if (isGroundBlock(st) && random.nextBoolean())
								level.setBlock(mp, table.topStone().get().defaultBlockState(), 2);
						}
					}
				}
			}
		} else {
			for (int x = -r; x <= r; x++) {
				for (int z = -r; z <= r; z++) {
					for (int y = -4; y <= 2; y++) {
						mp.set(p.getX() + x, p.getY() + y, p.getZ() + z);
						BlockState st = level.getBlockState(mp);
						if (st.isAir() || st.getMaterial().isReplaceable()) {
							continue;
						}
						double d = Math.sqrt(x * x + y * y + z * z);
						double d2 = random.nextDouble() * 0.5D;
						if (d < r && y >= 0 && st.getMaterial().isReplaceable()) {
							level.setBlock(mp, Blocks.AIR.defaultBlockState(), 2);
							continue;
						}
						if (d + d2 < r2) {
							// 液体生成範囲
							if (y < 0) {
								if (y == -3) {
									// Top
									if (random.nextBoolean()) {
										level.setBlock(mp, table.layerStone().get().defaultBlockState(), 2);
									} else {
										level.setBlock(mp, table.topStone().get().defaultBlockState(), 2);
									}
								} else if (y == -2) {
									// Fluid or Top
									boolean a1 = true;
									for (Direction dir : Direction.values()) {
										BlockState check = level.getBlockState(mp.relative(dir));
										if (dir != Direction.UP && !check.getMaterial().isSolid() && !check.getMaterial().isLiquid()) {
											a1 = false;
										}
									}
									if (a1 && (random.nextInt(3) == 0 || d < 2D)) {
										level.setBlock(mp, table.liquid().get().defaultBlockState(), 2);
									} else {
										level.setBlock(mp, table.topStone().get().defaultBlockState(), 2);
									}
								} else if (y == -1) {
									boolean a1 = true;
									for (Direction dir : Direction.values()) {
										BlockState check = level.getBlockState(mp.relative(dir));
										if (dir != Direction.UP && !check.getMaterial().isSolid() && !check.getMaterial().isLiquid()) {
											a1 = false;
										}
									}
									if (a1) {
										level.setBlock(mp, table.liquid().get().defaultBlockState(), 2);
									} else {
										level.setBlock(mp, table.topStone().get().defaultBlockState(), 2);
									}
								} else {
									level.setBlock(mp, table.topStone().get().defaultBlockState(), 2);
								}
							} else {
								// 上
								level.setBlock(mp, Blocks.AIR.defaultBlockState(), 2);
							}
						} else if (d + d2 < r1) {
							// 縁部分
							if (y <= -3) {
								// Layer or Top
								if (random.nextBoolean())
									level.setBlock(mp, table.topStone().get().defaultBlockState(), 2);
								else
									level.setBlock(mp, table.layerStone().get().defaultBlockState(), 2);
							} else if (y <= 0) {
								// Top
								level.setBlock(mp, table.topStone().get().defaultBlockState(), 2);
							} else if (random.nextBoolean()) {
								level.setBlock(mp, Blocks.AIR.defaultBlockState(), 2);
							}
						} else if (d + d2 < r) {
							// 縁部分2
							if (random.nextBoolean()) {
								if (y <= -1) {
									level.setBlock(mp, table.layerStone().get().defaultBlockState(), 2);
								} else if (y <= 0) {
									level.setBlock(mp, table.topStone().get().defaultBlockState(), 2);
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	static boolean isPlaceable(BlockState block) {
		if (!block.hasBlockEntity() && !block.is(BlockTags.FEATURES_CANNOT_REPLACE))
			if (block.getMaterial() == Material.STONE || block.getMaterial() == Material.SAND || block.getMaterial() == Material.CLAY || block.getMaterial() == Material.ICE || block
					.getMaterial() == Material.DIRT || block.getMaterial() == Material.GRASS || block.getMaterial() == Material.SNOW || block.getMaterial() == Material.POWDER_SNOW) {
				if (TagUtil.matchTag("ores", block.getBlock().asItem()).isEmpty() && block.getBlock() != Blocks.POINTED_DRIPSTONE) {
					return true;
				}
			}
		return false;
	}

	static boolean isGroundBlock(BlockState state) {
		return state.is(BlockTags.BASE_STONE_OVERWORLD) || state.is(Tags.Blocks.SAND) || state.is(Tags.Blocks.GRAVEL);
	}

	private static final SpringTable BLUE = new SpringTable(MagicColor.BLUE, 60, CoreInit.STONE_GYPSUM, CoreInit.STONE_TRAVERTINE, () -> CoreInit.HOTSPRING.getStillBlock().get());
	private static final SpringTable BLACK = new SpringTable(MagicColor.BLACK, 40, CoreInit.STONE_GUANO, CoreInit.STONE_GUANO, () -> (LiquidBlock) Blocks.WATER);
	private static final SpringTable RED = new SpringTable(MagicColor.RED, 60, CoreInit.STONE_NATRON, CoreInit.STONE_SALT, () -> CoreInit.BRINE.getStillBlock().get());
	private static final SpringTable GREEN = new SpringTable(MagicColor.GREEN, 60, () -> Blocks.COBBLESTONE, () -> Blocks.MOSSY_COBBLESTONE, () -> CoreInit.SPARKLING.getStillBlock().get());

	private record SpringTable(
			MagicColor color,
			int altitude,
			Supplier<Block> layerStone,
			Supplier<Block> topStone,
			Supplier<LiquidBlock> liquid) {}

}
