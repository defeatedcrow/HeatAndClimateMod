package defeatedcrow.hac.food.worldgen;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

import com.mojang.serialization.Codec;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.ClimateCropBaseBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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

		if (random.nextInt(100) > 10)
			return false;

		// Contextの座標は使用しない
		ChunkPos chunk = level.getChunk(pos).getPos();

		// 高度選定
		int height = 0;
		for (int j = 50; j < 200; j++) {

			BlockPos p2 = new BlockPos(chunk.getMiddleBlockX(), j, chunk.getMiddleBlockZ());
			BlockState soil = level.getBlockState(p2);
			BlockState air = level.getBlockState(p2.above());
			if (isSoil(soil) && isSurface(air)) {
				height = j;
				break;
			}
		}
		if (height == 0)
			return false;

		BlockPos p1 = new BlockPos(chunk.getMiddleBlockX(), height, chunk.getMiddleBlockZ());

		// サークル状に生成
		Holder<Biome> biome = level.getBiome(p1);
		List<ClimateCropBaseBlock> targets = targetList.stream().filter((b) -> matchBiome(biome, p1.getY(), b)).toList();
		if (!targets.isEmpty() && targets.size() > 0) {
			int i = targets.size();
			ClimateCropBaseBlock crop = i < 2 ? targets.get(0) : targets.get(random.nextInt(i));
			boolean tree = crop.getCurrentStage(crop.defaultBlockState()) == CropStage.SAPLING;
			boolean wild = crop.getTier() == CropTier.WILD;

			// DCLogger.debugInfoLog("=== target: " + crop.getRegistryName() + " ===");
			// DCLogger.debugInfoLog("pos: " + chunk.x + ", " + chunk.z + ", " + height);

			// 設置
			int c = 0;
			BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
			if (tree) {
				// 5回チャレンジ
				for (int k = 0; k < 6; k++) {
					if (c > 2)
						break;
					for (int y = -1; y < 3; y++) {
						mpos.set(chunk.getMiddleBlockX() + random.nextInt(14) - 7, height + y, chunk.getMiddleBlockZ() + random.nextInt(14) - 7);
						BlockState soil = level.getBlockState(mpos);
						BlockState air = level.getBlockState(mpos.above());
						if (suitableSoil(crop, level, mpos, soil) && canReplaceBlock(air)) {
							BlockState nextState = crop.getFeatureState();

							if (soil.getMaterial() == Material.SNOW || soil.getMaterial() == Material.POWDER_SNOW)
								level.setBlock(mpos, Blocks.DIRT.defaultBlockState(), 2);

							level.setBlock(mpos.above(), nextState, 2);
							c++;
							continue;
						}
					}
				}
			} else {
				for (int x = -4; x < 5; x++) {
					for (int z = -4; z < 5; z++) {
						for (int y = -1; y < 3; y++) {
							mpos.set(chunk.getMiddleBlockX() + x, height + y, chunk.getMiddleBlockZ() + z);
							BlockState soil = level.getBlockState(mpos);
							BlockState air = level.getBlockState(mpos.above());
							double dist = p1.distSqr(mpos);
							int d = 3 + Mth.absFloor(dist);
							boolean f = random.nextInt(d) == 0;

							if (f && suitableSoil(crop, level, mpos, soil) && canReplaceBlock(air)) {
								BlockState nextState = crop.getFeatureState();

								if (soil.getMaterial() == Material.SNOW || soil.getMaterial() == Material.POWDER_SNOW)
									level.setBlock(mpos, Blocks.DIRT.defaultBlockState(), 2);

								level.setBlock(mpos.above(), nextState, 2);
								c++;

								// 二段作物は二段の状態で生えてくる
								if (nextState.hasProperty(DCState.DOUBLE)) {
									BlockState upper = crop.getFeatureState().setValue(DCState.DOUBLE, true);
									level.setBlock(mpos.above(2), upper, 2);
								}
								continue;
							}
						}
					}
				}
			}
		}

		return true;
	}

	private boolean isSurface(BlockState state) {
		return !state.is(BlockTags.FEATURES_CANNOT_REPLACE) && !state.getMaterial().isLiquid() && (state.getMaterial().isReplaceable() || state.getMaterial() == Material.LEAVES || state.getMaterial() == Material.WOOD);
	}

	private boolean isSoil(BlockState soil) {
		return soil.getMaterial() == Material.DIRT || soil.getMaterial() == Material.SAND || soil.getMaterial() == Material.GRASS || soil.getMaterial() == Material.WATER;
	}

	private boolean suitableSoil(ClimateCropBaseBlock crop, WorldGenLevel level, BlockPos p, BlockState soil) {
		return crop.isSuitablePlace(level, p, soil) || soil.getMaterial() == Material.SNOW || soil.getMaterial() == Material.POWDER_SNOW;
	}

	private boolean canReplaceBlock(BlockState state) {
		return !state.is(BlockTags.FEATURES_CANNOT_REPLACE) && state.getMaterial().isReplaceable() && !state.getMaterial().isLiquid();
	}

	static final List<ClimateCropBaseBlock> targetList = Lists.newArrayList();

	public static void init() {
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AL_WILD.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AM_GOOSEFOOT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_CELERY.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AS_ARTEMISIA.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_RAPESEED.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CA_CHILI.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_OAT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GN_COMMON.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_MINT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_SORREL.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ML_JUTE.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_BINDWEED.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PD_ROGERIA.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_GREEN.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_COMMON.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RI_ZIZANIA.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_NIGHTSHADE.get());

		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BH_COMMON.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CH_WILD.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CM_OIL.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CN_CAMPHOR.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CT_POMELO.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ER_HEATH.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MR_MULBERRY.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_OL_ASH.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PL_COCONUT.get());
	}

	private boolean matchBiome(Holder<Biome> biome, int height, ClimateCropBaseBlock block) {
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
			}
		}
		return b1 && !b2;
	}

}
