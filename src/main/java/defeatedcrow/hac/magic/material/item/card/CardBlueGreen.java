package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CardBlueGreen extends MagicCardBase {

	public CardBlueGreen() {
		super(MagicColor.BLUE_GREEN, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		BlockState state = level.getBlockState(pos);
		if (state != null && state.getBlock() instanceof IClimateCrop crop) {
			if (crop.onMutation(level, pos, state, level.random, 5 + Mth.floor(f))) {
				level.levelEvent(1505, pos, 0);
				return true;
			}
		}
		return false;
	}
}
