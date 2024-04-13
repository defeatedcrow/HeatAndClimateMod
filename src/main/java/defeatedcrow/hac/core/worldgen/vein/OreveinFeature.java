package defeatedcrow.hac.core.worldgen.vein;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;

import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.core.worldgen.vein.OreGenPos.OreVein;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Material;

public class OreveinFeature extends Feature<NoneFeatureConfiguration> {

	private boolean isForced = false;

	public OreveinFeature(Codec<NoneFeatureConfiguration> codec) {
		super(codec);
		isForced = false;
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		BlockPos pos = context.origin();
		RandomSource random = context.random();

		// Contextの座標は使用しない
		ChunkPos chunk = level.getChunk(pos).getPos();
		boolean flag = false;

		// 周辺チャンクの分も生成する
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				if (level.hasChunk(chunk.x + x, chunk.z + z)) {
					ChunkPos c = new ChunkPos(chunk.x + x, chunk.z + z);
					OreVein[] veins = OreGenPos.INSTANCE.getVeins(c, level);
					if (veins != null) {
						for (int i = 0; i < veins.length; i++) {
							OreVein vein = veins[i];
							if (vein != null) {
								generateVein(level, chunk, vein);
								flag = true;
								// DCLogger.debugInfoLog("check_" + i + ": pos " + vein.pos.toShortString());
							}
						}
					}
				}
			}
		}

		return flag;
	}

	static boolean isPlaceable(BlockState block) {
		if (!block.hasBlockEntity() && !block.is(BlockTags.FEATURES_CANNOT_REPLACE))
			if (block.getMaterial() == Material.STONE || block.getMaterial() == Material.SAND || block.getMaterial() == Material.CLAY || block
				.getMaterial() == Material.DIRT) {
				if (TagUtil.matchTag("ores", block.getBlock().asItem()).isEmpty() && block.getBlock() != Blocks.POINTED_DRIPSTONE) {
					return true;
				}
			}
		return false;
	}

	static boolean avobeCheck(BlockState block) {
		if (block.getMaterial() == Material.PLANT || block.getMaterial() == Material.WATER_PLANT || block.getMaterial() == Material.BAMBOO || block
			.getMaterial() == Material.BAMBOO_SAPLING) {
			return true;
		}
		return false;
	}

	public void generateVein(WorldGenLevel level, ChunkPos chunk, OreVein vein) {
		if (vein.table == null) {
			return;
		}
		BlockPos center = vein.pos;
		int r = vein.round;
		int h = vein.round;
		int[] rands = vein.rands;

		// 鉱脈の構成ブロック
		OreSet[] gen = new OreSet[h];
		for (int i = 0; i < h; i++) {
			List<OreSet> list = Lists.newArrayList();
			int i1 = 0;
			list.addAll(vein.table.getOreSet());
			if (i < rands.length)
				i1 = vein.rands[i];

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

		// 鉱脈の傾き
		float sx = 0;
		float sz = 0;
		if (rands.length > 0) {
			int i3 = rands[0] & 15;
			if (i3 < 8) {
				sx = i3 / 8F;
				if ((i3 & 1) == 1) {
					sx *= -1F;
				}
			} else {
				i3 -= 8F;
				sz = i3 / 8F;
				if ((i3 & 1) == 1) {
					sz *= -1F;
				}
			}
		}

		// 生成
		for (int x = -r; x < r; x++) {
			for (int z = -r; z < r; z++) {
				for (int y = 0; y < h + 1; y++) {

					int offY = Mth.floor(sx * x) + Mth.floor(sz * z);
					int h2 = y;
					BlockPos p = new BlockPos(center.getX() + x, center.getY() + y + offY, center.getZ() + z);

					if (p.getX() >= chunk.getMinBlockX() && p.getX() <= chunk.getMaxBlockX() && p.getZ() >= chunk.getMinBlockZ() && p.getZ() <= chunk
						.getMaxBlockZ()) {

						BlockState block = level.getBlockState(p);
						double d1 = Math.sqrt(center.distSqr(p));
						if (p.getY() > level.getMinBuildHeight() && p.getY() < level.getMaxBuildHeight() && d1 < r && !avobeCheck(level.getBlockState(p.above()))) {
							if (y == h) {
								if (isPlaceable(block) || isForced) {
									level.setBlock(p, vein.table.getLayer().defaultBlockState(), isForced ? 3 : 2);
								}
							} else {
								OreSet add = gen[h2];
								if (isPlaceable(block) || isForced) {
									int j = level.getRandom().nextInt(100);
									if (add.hasTertOre() && j < add.getTertiaryChance()) {
										level.setBlock(p, add.getTertOre().defaultBlockState(), isForced ? 3 : 2);
									} else if (add.hasSecondOre() && j < add.getSecondChance()) {
										level.setBlock(p, add.getSecondOre().defaultBlockState(), isForced ? 3 : 2);
									} else if (add.getOre() != Blocks.AIR) {
										level.setBlock(p, add.getOre().defaultBlockState(), isForced ? 3 : 2);
									} else {
										level.setBlock(p, vein.table.getLayer().defaultBlockState(), isForced ? 3 : 2);
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
