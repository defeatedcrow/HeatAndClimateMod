package defeatedcrow.hac.magic.material.item.card;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.entity.CrowTurretEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Rotations;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CardBlackRed extends MagicCardBase {

	public CardBlackRed() {
		super(MagicColor.BLACK_RED, Rarity.UNCOMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		CrowTurretEntity doll = MagicInit.CROW_TURRET.get().create(level);
		Direction playerDir = player.getDirection();
		Rotations rot = new Rotations(0F, playerDir.getOpposite().toYRot(), 0F);
		doll.setPos(Vec3.atBottomCenterOf(pos.relative(dir)));
		doll.setDeltaMovement(0D, 0D, 0D);
		doll.setBodyPose(rot);
		doll.setRange(4.0F + f);
		level.addFreshEntity(doll);
		return true;
	}

}
