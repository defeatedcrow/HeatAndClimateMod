package defeatedcrow.hac.api.magic;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public abstract interface IJewel extends IColorDC {

	Item getItem();

	CharmType getCharmType();

	@Override
	MagicType getMagicType();

	@Override
	MagicColor getColor();

	Rarity getTier();

}