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
			if ((soil.getMaterial() == Material.DIRT || soil.getMaterial() == Material.SAND || soil.getMaterial() == Material.WATER || soil.getMaterial() == Material.GRASS) && air.getMaterial()
				.isReplaceable() && !air.getMaterial().isLiquid()) {
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
		if (!targets.isEmpty()) {

			int i = targets.size();
			random.nextInt(i);
			ClimateCropBaseBlock crop = i < 1 ? targets.get(0) : targets.get(random.nextInt(i));
			boolean tree = crop.getCurrentStage(crop.defaultBlockState()) == CropStage.SAPLING;
			boolean wild = crop.getTier() == CropTier.WILD;

			// DCLogger.debugInfoLog("=== target: " + crop.getRegistryName() + " ===");
			// DCLogger.debugInfoLog("pos: " + chunk.x + ", " + chunk.z + ", " + height);

			// 設置
			int c = 0;
			for (int x = -4; x < 5; x++) {
				for (int z = -4; z < 5; z++) {
					if (c > 3 && tree) {
						break;
					}

					double r = 25D;
					int x1 = x;
					int z1 = z;
					if (tree) {
						// 強制的に間隔を取る
						x1 *= 3;
						z1 *= 3;
						r = 250D;
					}

					if (x < -8 || x > 7 || z < -8 || z > 7)
						continue;

					for (int y = -1; y < 3; y++) {
						BlockPos p3 = new BlockPos(chunk.getMiddleBlockX() + x1, height + y, chunk.getMiddleBlockZ() + z1);
						BlockState soil = level.getBlockState(p3);
						BlockState air = level.getBlockState(p3.above());
						// 果樹やアンコモン種だと少なくなる
						boolean f = !tree && wild ? random.nextInt(3) == 0 : random.nextInt(8) == 0;

						if (p1.distSqr(p3) < r && f && suitableSoil(crop, level, p3, soil) && air.getMaterial().isReplaceable() && !air.getMaterial().isLiquid()) {
							BlockState nextState = crop.getFeatureState();

							if (soil.getMaterial() == Material.SNOW || soil.getMaterial() == Material.POWDER_SNOW)
								level.setBlock(p3, Blocks.DIRT.defaultBlockState(), 2);

							level.setBlock(p3.above(), nextState, 2);
							c++;

							// 二段作物は二段の状態で生えてくる
							if (nextState.hasProperty(DCState.DOUBLE)) {
								BlockState upper = crop.getFeatureState().setValue(DCState.DOUBLE, true);
								level.setBlock(p3.above(2), upper, 2);
							}
							continue;
						}
					}
				}
			}
		}

		return true;
	}

	private boolean suitableSoil(ClimateCropBaseBlock crop, WorldGenLevel level, BlockPos p, BlockState soil) {
		return crop.isSuitablePlace(level, p, soil) || soil.getMaterial() == Material.SNOW || soil.getMaterial() == Material.POWDER_SNOW;
	}

	static final List<ClimateCropBaseBlock> targetList = Lists.newArrayList();

	public static void init() {
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AL_WILD.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AM_GOOSEFOOT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_CELERY.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_FENNEL.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_RAPESEED.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CA_CHILI.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_OAT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_RYE.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GN_COMMON.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_MINT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_BASIL.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_SORREL.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_BUCKWHEAT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ML_JUTE.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_BINDWEED.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_GREEN.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_GARBANZO.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_COMMON.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_SORGHUM.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RI_ZIZANIA.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_NIGHTSHADE.get());

		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BH_COMMON.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CH_WILD.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CN_CAMPHOR.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CT_POMELO.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_OL_ASH.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PL_COCONUT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PL_DATE.get());
	}

	private boolean matchBiome(Holder<Biome> biome, int height, ClimateCropBaseBlock block) {
		if (!block.getSuitableTemp(block.getTier()).contains(DCHeatTier.getTypeByBiomeTemp(biome.get().getBaseTemperature()))) {
			// 温度条件の一致
			return false;
		}
		for (String s : block.getGeneratedBiomeTag(block.getTier())) {
			if (TagUtil.matchTag(s.toLowerCase(), biome).isPresent())
				return true;
			else if (s.contains("MOUNTAIN") && height > 130)
				return true;
		}
		return false;
	}

}
