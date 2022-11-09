package defeatedcrow.hac.food.worldgen;

import java.util.List;

import org.apache.commons.compress.utils.Lists;

import com.mojang.serialization.Codec;

import defeatedcrow.hac.core.material.tag.TagUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.ClimateCropBaseBlock;
import defeatedcrow.hac.food.material.block.SaplingBeech;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
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

			// DCLogger.debugInfoLog("=== target: " + crop.getRegistryName() + " ===");
			// DCLogger.debugInfoLog("pos: " + chunk.x + ", " + chunk.z + ", " + height);

			// 設置
			for (int x = -4; x < 5; x++) {
				for (int z = -4; z < 5; z++) {
					for (int y = -1; y < 3; y++) {
						BlockPos p3 = new BlockPos(chunk.getMiddleBlockX() + x, height + y, chunk.getMiddleBlockZ() + z);
						BlockState soil = level.getBlockState(p3);
						BlockState air = level.getBlockState(p3.above());
						// 果樹だと少なくなる
						boolean f = crop instanceof SaplingBeech ? random.nextInt(8) == 0 : random.nextInt(3) == 0;

						if (p1.distSqr(p3) < 25D && f && crop.isSuitablePlace(level, p3, soil) && air.getMaterial().isReplaceable() && !air.getMaterial().isLiquid()) {
							level.setBlock(p3.above(), crop.defaultBlockState(), 2);
							continue;
						}
					}
				}
			}
		}

		return true;
	}

	static final List<ClimateCropBaseBlock> targetList = Lists.newArrayList();

	public static void init() {
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AL_WILD.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AM_GOOSEFOOT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_AP_CELERY.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BR_RAPESEED.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CA_CHILI.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_CR_OAT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_GN_COMMON.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_HB_MINT.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_KN_SORREL.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_ML_JUTE.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_MO_BINDWEED.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_PE_GREEN.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_COMMON.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RE_SORGHUM.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_RI_ZIZANIA.get());
		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_SL_NIGHTSHADE.get());

		targetList.add((ClimateCropBaseBlock) FoodInit.BLOCK_BH_COMMON.get());
	}

	private boolean matchBiome(Holder<Biome> biome, int height, ClimateCropBaseBlock block) {
		for (String s : block.getGeneratedBiomeTag(block.getTier())) {
			if (TagUtil.matchTag(s.toLowerCase(), biome).isPresent())
				return true;
			else if (s.contains("MOUNTAIN") && height > 120)
				return true;
		}
		return false;
	}

}
