package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class CardGreenT1 extends MagicCardBase {

	public CardGreenT1() {
		super(MagicColor.GREEN, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, ItemStack card) {
		BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
		for (int x = -8; x < 8; x++) {
			for (int z = -8; z < 8; z++) {
				for (int y = -2; y < 3; y++) {
					mpos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (mpos.getY() > level.getMinBuildHeight() && mpos.getY() < level.getMaxBuildHeight()) {
						BlockState crop = level.getBlockState(mpos);
						ItemStack f = new ItemStack(Items.BONE_MEAL);
						int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, level, mpos, crop, f);
						if (hook != 0)
							return hook > 0;
						if (crop.getBlock() instanceof BonemealableBlock) {
							if (crop.getMaterial() == Material.GRASS || crop.getBlock() == Blocks.TALL_GRASS || crop.getBlock() == Blocks.GRASS_BLOCK)
								continue;
							BonemealableBlock target = (BonemealableBlock) crop.getBlock();
							if (target.isValidBonemealTarget(level, mpos, crop, level.isClientSide)) {
								if (level instanceof ServerLevel) {
									if (target.isBonemealSuccess(level, level.random, mpos, crop)) {
										target.performBonemeal((ServerLevel) level, level.random, mpos, crop);
										if (!level.isClientSide) {
											level.levelEvent(1505, mpos, 0);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

}
