package defeatedcrow.hac.api.magic;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public abstract interface IJewel extends IColorDC {

	Item getItem();

	CharmType getCharmType();

	MagicType getType();

	@Override
	MagicColor getColor();

	Rarity getTier();

}