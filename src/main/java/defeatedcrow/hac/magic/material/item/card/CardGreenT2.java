package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class CardGreenT2 extends MagicCardBase {

	public CardGreenT2() {
		super(MagicColor.GREEN, Rarity.UNCOMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		if (level instanceof ServerLevel server) {
			if (server.getLevelData().isRaining()) {
				server.setWeatherParameters(Mth.floor(24000 * f), 0, false, false);
			} else {
				server.setWeatherParameters(0, Mth.floor(24000 * f), true, level.getRandom().nextBoolean());
			}
		}
		return true;
	}

}
