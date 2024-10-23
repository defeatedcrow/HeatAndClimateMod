package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.entity.SilkySmallBombEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CardRedT1 extends MagicCardBase {

	public CardRedT1() {
		super(MagicColor.RED, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		SilkySmallBombEntity bomb = MagicInit.SILKY_BOMB_ENTITY.get().create(level);
		bomb.setPos(Vec3.atBottomCenterOf(pos.relative(dir)));
		bomb.setYRot(player.yRotO);
		bomb.setDeltaMovement(0D, 0D, 0D);
		bomb.setSize(Mth.floor(2F + f));
		if (player != null && player.getUUID() != null) {
			bomb.setOwnerUUID(player.getUUID());
		}
		level.addFreshEntity(bomb);
		return true;
	}

}
