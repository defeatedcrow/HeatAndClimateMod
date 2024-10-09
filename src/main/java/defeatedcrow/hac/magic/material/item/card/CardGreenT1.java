package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.network.packet.message.MsgEffectToC;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class CardGreenT1 extends MagicCardBase {

	public CardGreenT1() {
		super(MagicColor.GREEN, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float boost) {
		BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
		int range = 8 + Mth.floor(boost);
		for (int x = -range; x < range; x++) {
			for (int z = -range; z < range; z++) {
				for (int y = -2; y < 3; y++) {
					mpos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (mpos.getY() > level.getMinBuildHeight() && mpos.getY() < level.getMaxBuildHeight()) {
						BlockState crop = level.getBlockState(mpos);
						ItemStack f = new ItemStack(Items.BONE_MEAL);
						int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, level, mpos, crop, f);
						if (hook != 0)
							continue;
						if (crop.getBlock() instanceof BonemealableBlock) {
							if (crop.getMaterial() == Material.DIRT || crop.getMaterial() == Material.GRASS || crop.is(TagDC.BlockTag.WEED))
								continue;
							BonemealableBlock target = (BonemealableBlock) crop.getBlock();
							if (target.isValidBonemealTarget(level, mpos, crop, false)) {
								if (level instanceof ServerLevel) {
									if (target.isBonemealSuccess(level, level.random, mpos, crop)) {
										target.performBonemeal((ServerLevel) level, level.random, mpos, crop);
										MsgEffectToC.sendToClient((ServerLevel) level, player.position().add(0D, 1.0D, 0D), 1);
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
