package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		if (player instanceof ServerPlayer) {
			ServerPlayer p2 = (ServerPlayer) player;
			BlockPos p1 = player.blockPosition().relative(dir);
			p2.setRespawnPosition(level.dimension(), p1, 0F, true, true);
			level.levelEvent(1505, pos, 0);

			MutableComponent mes = Component.translatable("dcs.tip.coodinate");
			MutableComponent mes2 = Component.literal(" X:" + p1.getX());
			mes.append(Component.literal(" Y:" + p1.getY()));
			mes.append(Component.literal(" Z:" + p1.getZ()));
			player.displayClientMessage(mes, true);
			player.displayClientMessage(mes2, true);
		}
		return true;
	}

}
