package defeatedcrow.hac.magic.client;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderColorArrow extends ArrowRenderer<AbstractArrow> {
	public static final ResourceLocation WHITE = new ResourceLocation(ClimateCore.MOD_ID, "textures/entity/white_arrow.png");
	public static final ResourceLocation BLUE = new ResourceLocation(ClimateCore.MOD_ID, "textures/entity/blue_arrow.png");
	public static final ResourceLocation BLACK = new ResourceLocation(ClimateCore.MOD_ID, "textures/entity/black_arrow.png");
	public static final ResourceLocation RED = new ResourceLocation(ClimateCore.MOD_ID, "textures/entity/red_arrow.png");
	public static final ResourceLocation GREEN = new ResourceLocation(ClimateCore.MOD_ID, "textures/entity/green_arrow.png");

	public RenderColorArrow(EntityRendererProvider.Context cont) {
		super(cont);
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractArrow arrow) {
		if (arrow.getType() == MagicInit.ARROW_WHITE_ENTITY.get()) {
			return WHITE;
		}
		if (arrow.getType() == MagicInit.ARROW_BLUE_ENTITY.get()) {
			return BLUE;
		}
		if (arrow.getType() == MagicInit.ARROW_BLACK_ENTITY.get()) {
			return BLACK;
		}
		if (arrow.getType() == MagicInit.ARROW_RED_ENTITY.get()) {
			return RED;
		}
		if (arrow.getType() == MagicInit.ARROW_GREEN_ENTITY.get()) {
			return GREEN;
		}
		return WHITE;
	}
}
