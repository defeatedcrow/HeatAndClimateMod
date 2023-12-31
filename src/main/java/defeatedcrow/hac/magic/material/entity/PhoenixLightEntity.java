package defeatedcrow.hac.magic.material.entity;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PhoenixLightEntity extends OwnableMagicEntity {

	public PhoenixLightEntity(EntityType<? extends OwnableMagicEntity> type, Level level) {
		super(type, level);
	}

	@Override
	public ItemStack getDropItem() {
		return ItemStack.EMPTY;
	}

	@Override
	public MagicColor getColor() {
		return MagicColor.WHITE;
	}

	@Override
	public void tick() {
		if (this.level.isClientSide) {
			if (this.level.random.nextBoolean()) {
				double d0 = this.position().x - 0.05D + this.level.random.nextDouble() * 0.1D;
				double d1 = this.position().y + 0.4D;
				double d2 = this.position().z - 0.05D + this.level.random.nextDouble() * 0.1D;
				level.addParticle(CoreInit.LIGHT_ORB_WHITE.get(), d0, d1, d2, 0.0D, 0.05D, 0.0D);
			}
		}
	}

}
