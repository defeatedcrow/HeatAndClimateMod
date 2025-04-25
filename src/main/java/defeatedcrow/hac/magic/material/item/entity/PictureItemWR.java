package defeatedcrow.hac.magic.material.item.entity;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PictureItemWR extends MagicPictureItem {

	public PictureItemWR(String s) {
		super(s, MagicColor.WHITE_RED);
	}

	@Override
	public EntityType<?> getType() {
		return MagicInit.MAGIC_PICTURE_WR.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return PICTURE_WR;
	}

	@Override
	public EntityRenderData getOuterRenderData(Item item) {
		return PICTURE_WR_OL;
	}

	public static final EntityRenderData PICTURE_WR = new EntityRenderData("magic/magic_picture_white_red", 1.0F, 0F);

	public static final EntityRenderData PICTURE_WR_OL = new EntityRenderData("magic/painting/magic_picture_white_red", 1.0F, 0F);

}
