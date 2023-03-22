package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.entity.ArrowRed;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class CardRedT1 extends MagicCardBase {

	public CardRedT1() {
		super(MagicColor.RED, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, ItemStack card) {
		if (!level.isClientSide()) {
			ArrowItem arrowitem = (ArrowItem) (MagicInit.ARROW_RED.get());
			ArrowRed red = (ArrowRed) arrowitem.createArrow(level, new ItemStack(arrowitem), player);
			red.setRange(3.0F);
			red.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
			red.setCritArrow(true);
			red.setBaseDamage(red.getBaseDamage() * 2D);
			red.setRange(5.0F);
			red.pickup = AbstractArrow.Pickup.DISALLOWED;
			level.addFreshEntity(red);
		}
		return true;
	}

}
