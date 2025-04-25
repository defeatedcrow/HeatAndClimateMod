package defeatedcrow.hac.magic.material.item.entity;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.material.EntityRenderData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PictureItemWU extends MagicPictureItem {

	public PictureItemWU(String s) {
		super(s, MagicColor.WHITE_BLUE);
	}

	@Override
	public EntityType<?> getType() {
		return null;
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return null;
	}

	@Override
	public EntityRenderData getOuterRenderData(Item item) {
		return null;
	}

}
