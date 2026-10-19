package defeatedcrow.hac.food.worldgen;

import java.util.List;

import com.mojang.serialization.Codec;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.ICropData.AquaticType;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.food.material.block.crops.ClimateCropBaseBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class WildCropFeature extends Feature<NoneFeatureConfiguration> {

	public WildCropFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
		boolean isForced = false;
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
		// より条件が一致する作物を優先する
		Holder<Biome> biome = level.getBiome(pos);
		List<ClimateCropBaseBlock> targetFirst = TargetCropList.targetList.stream().filter(b -> matchBiome(biome, pos.getY(), b) > 1).toList();
		List<ClimateCropBaseBlock> targetSecond = TargetCropList.targetList.stream().filter(b -> matchBiome(biome, pos.getY(), b) > 0).toList();
		boolean tree = random.nextInt(100) < 30;
		if (ConfigCommonBuilder.INSTANCE.enCommonCrop.get() && random.nextInt(100) < 20) {
			// 20%の確率
			if (tree) {
				// 村には生成しないように
				if (level.getLevel().isCloseToVillage(pos, 8)) {
					return false;
				} else {
					targetFirst = TargetCropList.commonTreeList.stream().filter(b -> matchBiome(biome, pos.getY(), b) > 1).toList();
					targetSecond = TargetCropList.commonTreeList.stream().filter(b -> matchBiome(biome, pos.getY(), b) > 0).toList();
				}
			} else {
				targetFirst = TargetCropList.commonList.stream().filter(b -> matchBiome(biome, pos.getY(), b) > 1).toList();
				targetSecond = TargetCropList.commonList.stream().filter(b -> matchBiome(biome, pos.getY(), b) > 0).toList();
			}
		} else if (tree) {
			// 村には生成しないように
			if (level.getLevel().isCloseToVillage(pos, 8)) {
				return false;
			} else {
				targetFirst = TargetCropList.targetTreeList.stream().filter(b -> matchBiome(biome, pos.getY(), b) > 1).toList();
				targetSecond = TargetCropList.targetTreeList.stream().filter(b -> matchBiome(biome, pos.getY(), b) > 0).toList();
			}
		}

		// DCLogger.debugInfoLog("=== target size: " + targets.size() + " " + tree + " ===");
		// DCLogger.debugInfoLog("pos: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
		ClimateCropBaseBlock crop = null;
		boolean first = random.nextInt(100) < 50 && !targetFirst.isEmpty() && targetFirst.size() > 0;
		if (first) {
			int i = targetFirst.size();
			crop = i < 2 ? targetFirst.get(0) : targetFirst.get(random.nextInt(i));
		} else if (!targetSecond.isEmpty() && targetSecond.size() > 0) {
			int i = targetSecond.size();
			crop = i < 2 ? targetSecond.get(0) : targetSecond.get(random.nextInt(i));
		}
		if (crop != null) {
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
						BlockState air2 = level.getBlockState(p1.above(2));
						if (suitableSoil(crop, level, p1, soil) && canReplaceBlock(crop, air, air2)) {
							BlockState nextState = crop.getFeatureState();

							if (soil.is(Blocks.SNOW_BLOCK) || soil.is(Blocks.POWDER_SNOW))
								level.setBlock(p1, Blocks.DIRT.defaultBlockState(), 2);

							level.setBlock(p1.above(), nextState, 2);
							break;
						}
					}
				}
			} else {
				for (int x = -4; x < 5; x++) {
					for (int z = -4; z < 5; z++) {
						for (int y = -4; y < 3; y++) {
							mpos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
							BlockState soil = level.getBlockState(mpos);
							BlockState air = level.getBlockState(mpos.above());
							BlockState air2 = level.getBlockState(mpos.above(2));
							double dist = pos.distSqr(mpos);
							int d = 3 + Mth.floor(Math.abs(dist));
							boolean f = random.nextInt(d) == 0;

							if (f && suitableSoil(crop, level, mpos, soil) && canReplaceBlock(crop, air, air2)) {
								BlockState nextState = crop.getFeatureState();

								boolean w = true;
								if (crop.isAquaticPlant(crop.getTier()) == AquaticType.FORCED_SUBMERGED && !isWater(air)) {
									// 水辺にしか生えない
									w = false;
									for (Direction side : Direction.Plane.HORIZONTAL) {
										if (isWater(level.getBlockState(mpos.relative(side)))) {
											w = true;
										}
									}
								}

								if (w) {
									if (soil.is(Blocks.SNOW_BLOCK) || soil.is(Blocks.POWDER_SNOW))
										level.setBlock(mpos, Blocks.DIRT.defaultBlockState(), 2);

									if (crop.getGrowType(crop.getTier()) == CropGrowType.VINE || crop.getGrowType(crop.getTier()) == CropGrowType.EPIPHYTE) {
										nextState = crop.updateShape(nextState, Direction.DOWN, nextState, level, mpos.above(), mpos);
									}
									if (isWater(air) && nextState.hasProperty(BlockStateProperties.WATERLOGGED)) {
										nextState = nextState.setValue(BlockStateProperties.WATERLOGGED, true);
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
		}

		return true;
	}

	private static boolean isSurface(BlockState state) {
		if (!state.getFluidState().isEmpty() && state.is(BlockTags.FEATURES_CANNOT_REPLACE) && state.liquid())
			return false;
		return state.canBeReplaced() || state.is(BlockTags.LEAVES) || state.is(TagDC.BlockTag.WEED) || state.is(BlockTags.SAPLINGS) || state.is(BlockTags.FLOWERS);
	}

	private static boolean isSoil(BlockState soil) {
		return soil.is(BlockTags.DIRT) || soil.is(BlockTags.SAND) || soil.is(Blocks.FARMLAND) || soil.is(Blocks.GRASS_BLOCK) || soil.getFluidState().is(FluidTags.WATER);
	}

	private static boolean suitableSoil(ClimateCropBaseBlock crop, WorldGenLevel level, BlockPos p, BlockState soil) {
		return crop.isSuitablePlace(level, p, soil) || soil.is(Blocks.SNOW_BLOCK) || soil.is(Blocks.POWDER_SNOW);
	}

	private static boolean canReplaceBlock(ClimateCropBaseBlock crop, BlockState avobe, BlockState air) {
		AquaticType aquatic = crop.isAquaticPlant(crop.getTier());
		boolean air2 = isSurface(avobe) && !(avobe.getBlock() instanceof DoublePlantBlock);
		if (isWater(air)) {
			air2 = aquatic == AquaticType.SUBMERGED || aquatic == AquaticType.FORCED_SUBMERGED;
		}
		boolean air1 = isSurface(avobe);
		if (aquatic == AquaticType.FORCED_EMERGED || aquatic == AquaticType.FORCED_SUBMERGED) {
			air1 = isWater(avobe);
		} else if (aquatic == AquaticType.EMERGED || aquatic == AquaticType.SUBMERGED) {
			if (isWater(avobe))
				air1 = true;
		}
		return air1 && air2;
	}

	private static boolean isWater(BlockState state) {
		return state.getFluidState().is(FluidTags.WATER) && !state.getFluidState().isEmpty() && state.getFluidState().isSource();
	}

	private static int matchBiome(Holder<Biome> biome, int height, ClimateCropBaseBlock block) {
		int weight = 0;
		boolean b2 = false;
		float humid = biome.get().getModifiedClimateSettings().downfall();
		if (!block.getSuitableTemp(block.getTier()).contains(DCHeatTier.getTypeByBiomeTemp(biome.get().getBaseTemperature()))) {
			// 温度条件の一致
			return -1;
		}
		for (String s2 : block.getAvoidBiomeTag(block.getTier())) {
			if (TagUtil.matchTag(s2.toLowerCase(), biome).isPresent()) {
				b2 = true;
				break;
			} else if (s2.contains("MOUNTAIN") && height > 130) {
				b2 = true;
				break;
			} else if (s2.contains("LOWLAND") && height < 90) {
				b2 = true;
				break;
			} else if (s2.contains("DRY") && humid < 0.35F) {
				b2 = true;
				break;
			} else if ((s2.contains("WET") || s2.contains("WATER")) && humid > 0.8F) {
				b2 = true;
				break;
			}
		}
		if (b2)
			return -1;

		for (String s : block.getGeneratedBiomeTag(block.getTier())) {
			if (TagUtil.matchTag(s.toLowerCase(), biome).isPresent()) {
				weight++;
			} else if (s.contains("MOUNTAIN") && height > 120) {
				weight++;
				if (height > 180) {
					weight++;
				}
			} else if (s.contains("LOWLAND") && height < 90) {
				weight++;
				if (height < 70) {
					weight++;
				}
				break;
			} else if (s.contains("DRY") && humid < 0.35F) {
				weight++;
			} else if ((s.contains("WET") || s.contains("WATER")) && humid > 0.8F) {
				weight++;
			} else if (biome.unwrapKey().isPresent()) {
				if (biome.unwrapKey().get().location().getPath().contains(s)) {
					weight++;
				}
			}
		}

		return weight;
	}

}
