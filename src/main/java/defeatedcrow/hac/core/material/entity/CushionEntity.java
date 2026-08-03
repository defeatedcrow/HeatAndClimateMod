package defeatedcrow.hac.core.material.entity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CushionEntity extends ObjectEntityBaseDC {

	public CushionEntity(EntityType<? extends CushionEntity> type, Level level) {
		super(type, level);
	}

	@Override
	public double getPassengersRidingOffset() {
		return this.getBbHeight() - 0.4D;
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (!this.isAlive()) {
			return super.interact(player, hand);
		} else {
			if (this.getPassengers()
			    .isEmpty()) {
				player.startRiding(this);
			} else {
				this.ejectPassengers();
			}
			return InteractionResult.SUCCESS;
		}
	}

}
