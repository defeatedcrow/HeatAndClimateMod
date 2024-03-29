package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.entity.ArrowBindPlant;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class CardGreenT2 extends MagicCardBase {

	public CardGreenT2() {
		super(MagicColor.GREEN, Rarity.UNCOMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		ArrowBindPlant arrow = new ArrowBindPlant(level, player);
		arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
		arrow.setCritArrow(true);
		arrow.setMaxAge(1200 * (1 + Mth.floor(f)));
		arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
		level.addFreshEntity(arrow);
		return true;
	}

}
