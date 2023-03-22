package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class CardBlueT1 extends MagicCardBase {

	public CardBlueT1() {
		super(MagicColor.BLUE, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, ItemStack card) {
		if (player instanceof ServerPlayer) {
			ServerPlayer p2 = (ServerPlayer) player;
			p2.setRespawnPosition(level.dimension(), player.blockPosition().above(), 0F, true, true);
			level.levelEvent(1505, pos, 0);
		}
		return true;
	}

}
