package defeatedcrow.hac.magic.material.entity;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class PictureEntityRU extends MagicPictureEntity {

	public PictureEntityRU(EntityType<? extends OwnableMagicEntity> type, Level level) {
		super(type, level);
	}

	@Override
	public Item getItem() {
		return MagicInit.PICTURE_RU.get();
	}

	@Override
	public MagicColor getColor() {
		return MagicColor.RED_BLUE;
	}

}
