package defeatedcrow.hac.magic.material.item.entity;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class PictureItemBR extends MagicPictureItem {

	public PictureItemBR(String s) {
		super(s, MagicColor.BLACK_RED);
	}

	@Override
	public EntityType<?> getType() {
		return MagicInit.MAGIC_PICTURE_BR.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		return PICTURE;
	}

	@Override
	public EntityRenderData getOuterRenderData(Item item) {
		return PICTURE_OL;
	}

	public static final EntityRenderData PICTURE = new EntityRenderData("magic/magic_picture_black_red", 1.0F, 0F);

	public static final EntityRenderData PICTURE_OL = new EntityRenderData("magic/painting/magic_picture_black_red", 1.0F, 0F);

}
