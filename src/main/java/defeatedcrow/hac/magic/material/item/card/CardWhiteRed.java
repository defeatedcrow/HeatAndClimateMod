package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.entity.PhoenixLightEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CardWhiteRed extends MagicCardBase {

	public CardWhiteRed() {
		super(MagicColor.WHITE_RED, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		PhoenixLightEntity light = MagicInit.PHOENIX_LIGHT_ENTITY.get().create(level);
		light.setPos(Vec3.atBottomCenterOf(pos.relative(dir)));
		light.setYRot(0.0F);
		light.setDeltaMovement(0D, 0D, 0D);
		if (player != null && player.getUUID() != null) {
			light.setOwnerUUID(player.getUUID());
		}
		level.addFreshEntity(light);
		return true;
	}

}
