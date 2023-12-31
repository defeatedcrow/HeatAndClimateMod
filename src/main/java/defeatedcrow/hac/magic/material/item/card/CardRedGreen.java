package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.entity.SilkyFairyEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CardRedGreen extends MagicCardBase {

	public CardRedGreen() {
		super(MagicColor.RED_GREEN, Rarity.UNCOMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		if (level instanceof ServerLevel sl) {
			SectionPos sp = SectionPos.of(pos);
			SilkyFairyEntity fairy = MagicInit.SILKY_FAIRY_ENTITY.get().create(level);
			fairy.setPos(Vec3.atBottomCenterOf(sp.center()));
			fairy.setYRot(0.0F);
			fairy.setDeltaMovement(0D, 0D, 0D);
			if (player != null && player.getUUID() != null) {
				fairy.setOwnerUUID(player.getUUID());
			}
			level.addFreshEntity(fairy);
		}
		return true;
	}
}
