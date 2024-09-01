package defeatedcrow.hac.food.worldgen;

import java.util.List;

import com.mojang.serialization.Codec;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.food.material.block.crops.ClimateCropBaseBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Material;

public class WildCropFeature extends Feature<NoneFeatureConfiguration> {

	private boolean isForced = false;

	public WildCropFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
		isForced = false;
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		RandomSource random = context.random();
		random.nextInt(100);
		int rate = ConfigCommonBuilder.INSTANCE.vCropFeature.get();

		if (rate == 0 || random.nextInt(100) > rate)
			return false;

		// Contextの座標は使用しない
		ChunkPos chunk = level.getChunk(pos).getPos();

		// 高度選定
		int surface = level.getHeight(Heightmap.Types.WORLD_SURFACE, chunk.getMiddleBlockX(), chunk.getMiddleBlockZ());
		int height = level.getMaxBuildHeight();
		for (int j = surface - 20; j < level.getMaxBuildHeight() - 5; j++) {

			BlockPos p2 = new BlockPos(chunk.getMiddleBlockX(), j, chunk.getMiddleBlockZ());
			BlockState soil = level.getBlockState(p2);
			BlockState air = level.getBlockState(p2.above());
			if (isSoil(soil) && isSurface(air)) {
				height = j;
				break;
			}
		}
		if (height == level.getMaxBuildHeight())
			return false;

		BlockPos p1 = new BlockPos(chunk.getMiddleBlockX(), height + 1, chunk.getMiddleBlockZ());
		// BlockState nextState = FoodInit.DUMMY_DIANTHUS.get().defaultBlockState();
		// level.setBlock(p1, nextState, 2);

		WildCropFeature.updateFlower(level, p1, random);

		return true;
	}

	public static boolean updateFlower(WorldGenLevel level, BlockPos pos, RandomSource random) {
		random.nextInt(100);
		// サークル状に生成
		Holder<Biome> biome = level.getBiome(pos);
		List<ClimateCropBaseBlock> targets = TargetCropList.INSTANCE.targetList.stream().filter((b) -> matchBiome(biome, pos.getY(), b)).toList();
		boolean tree = random.nextInt(100) < 30;
		if (tree) {
			// 村には生成しないように
			if (level.getLevel().isCloseToVillage(pos, 3)) {
				return false;
			} else {
				targets = TargetCropList.INSTANCE.targetTreeList.stream().filter((b) -> matchBiome(biome, pos.getY(), b)).toList();
			}
		}
		if (ConfigCommonBuilder.INSTANCE.enCommonCrop.get() && random.nextInt(100) < 20) {
			// 20%の確率
			targets = tree ? TargetCropList.INSTANCE.commonTreeList.stream().filter((b) -> matchBiome(biome, pos.getY(), b)).toList() : TargetCropList.INSTANCE.commonList.stream().filter((
					b) -> matchBiome(biome, pos.getY(), b)).toList();
		}

		// DCLogger.debugInfoLog("=== target size: " + targets.size() + " " + tree + " ===");
		// DCLogger.debugInfoLog("pos: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());

		if (!targets.isEmpty() && targets.size() > 0) {
			int i = targets.size();
			ClimateCropBaseBlock crop = i < 2 ? targets.get(0) : targets.get(random.nextInt(i));
			boolean wild = crop.getTier() == CropTier.WILD;

			// DCLogger.debugInfoLog("crop: " + crop.getRegistryName());

			// 設置
			BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
			if (tree) {
				// 4回チャレンジ
				for (Direction dir : Direction.values()) {
					if (dir.getAxis().isVertical())
						continue;
					int k = random.nextInt(7);
					for (int y = -3; y < 5; y++) {
						BlockPos p1 = pos.relative(dir, 2 + k).above(y);
						BlockState soil = level.getBlockState(p1);
						BlockState air = level.getBlockState(p1.above());
						if (suitableSoil(crop, level, p1, soil) && canReplaceBlock(air)) {
							BlockState nextState = crop.getFeatureState();

							if (soil.getMaterial() == Material.SNOW || soil.getMaterial() == Material.POWDER_SNOW)
								level.setBlock(p1, Blocks.DIRT.defaultBlockState(), 2);

							level.setBlock(p1.above(), nextState, 2);
							break;
						}
					}
				}
			} else {
				for (int x = -4; x < 5; x++) {
					for (int z = -4; z < 5; z++) {
						for (int y = -3; y < 3; y++) {
							mpos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
							BlockState soil = level.getBlockState(mpos);
							BlockState air = level.getBlockState(mpos.above());
							double dist = pos.distSqr(mpos);
							int d = 3 + Mth.absFloor(dist);
							boolean f = random.nextInt(d) == 0;

							if (f && suitableSoil(crop, level, mpos, soil) && canReplaceBlock(air)) {
								BlockState nextState = crop.getFeatureState();

								if (soil.getMaterial() == Material.SNOW || soil.getMaterial() == Material.POWDER_SNOW)
									level.setBlock(mpos, Blocks.DIRT.defaultBlockState(), 2);

								if (crop.getGrowType(crop.getTier()) == CropGrowType.VINE || crop.getGrowType(crop.getTier()) == CropGrowType.EPIPHYTE) {
									nextState = crop.updateShape(nextState, Direction.DOWN, nextState, level, mpos.above(), mpos);
								}
								level.setBlock(mpos.above(), nextState, 2);
								// 二段作物は二段の状態で生えてくる
								if (nextState.hasProperty(DCState.DOUBLE)) {
									BlockState upper = crop.getFeatureState().setValue(DCState.DOUBLE, true);
									level.setBlock(mpos.above(2), upper, 2);
								}
								break;
							}
						}
					}
				}
			}
		}

		return true;
	}

	private static boolean isSurface(BlockState state) {
		return !state.getFluidState().isSource() && !state.is(BlockTags.FEATURES_CANNOT_REPLACE) && !state.getMaterial().isLiquid() && (state.getMaterial().isReplaceable() || state
				.getMaterial() == Material.LEAVES || state.getMaterial() == Material.PLANT);
	}

	private static boolean isSoil(BlockState soil) {
		return soil.getMaterial() == Material.DIRT || soil.getMaterial() == Material.SAND || soil.getMaterial() == Material.GRASS;
	}

	private static boolean suitableSoil(ClimateCropBaseBlock crop, WorldGenLevel level, BlockPos p, BlockState soil) {
		return crop.isSuitablePlace(level, p, soil) || soil.getMaterial() == Material.SNOW || soil.getMaterial() == Material.POWDER_SNOW;
	}

	private static boolean canReplaceBlock(BlockState state) {
		return !state.is(BlockTags.FEATURES_CANNOT_REPLACE) && (state.getMaterial().isReplaceable() || state.getMaterial() == Material.TOP_SNOW) && !state.getMaterial().isLiquid() && !(state
				.getBlock() instanceof LiquidBlock);
	}

	private static boolean matchBiome(Holder<Biome> biome, int height, ClimateCropBaseBlock block) {
		if (!block.getSuitableTemp(block.getTier()).contains(DCHeatTier.getTypeByBiomeTemp(biome.get().getBaseTemperature()))) {
			// 温度条件の一致
			return false;
		}
		boolean b1 = false;
		boolean b2 = false;
		for (String s : block.getGeneratedBiomeTag(block.getTier())) {
			if (TagUtil.matchTag(s.toLowerCase(), biome).isPresent()) {
				b1 = true;
				break;
			} else if (s.contains("MOUNTAIN") && height > 130) {
				b1 = true;
				break;
			}
		}
		for (String s : block.getAvoidBiomeTag(block.getTier())) {
			if (TagUtil.matchTag(s.toLowerCase(), biome).isPresent()) {
				b2 = true;
				break;
			} else if (s.contains("MOUNTAIN") && height > 130) {
				b2 = true;
				break;
			} else if (s.contains("LOWLAND") && height < 90) {
				b2 = true;
				break;
			}
		}
		return b1 && !b2;
	}

}
