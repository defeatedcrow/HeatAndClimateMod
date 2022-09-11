package defeatedcrow.hac.api.magic;

import net.minecraft.world.item.Item;

public abstract interface IJewel {

	Item getItem();

	CharmType getCharmType();

	MagicType getType();

	MagicColor getColor();

}