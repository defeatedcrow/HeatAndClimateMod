package defeatedcrow.hac.api.material;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class EntityRenderData {

	final ResourceLocation texPath;
	final ModelLayerLocation location;
	final float scale;
	final float adjustY;

	public EntityRenderData(String name, float s, float a) {
		texPath = new ResourceLocation(ClimateCore.MOD_ID, "textures/entity/" + name + ".png");
		location = new ModelLayerLocation(new ResourceLocation(ClimateCore.MOD_ID, name), "main");
		scale = s;
		adjustY = a;
	}

	public ResourceLocation getTextureLocation() {
		return texPath;
	}

	public ModelLayerLocation getLayerLocation() {
		return location;
	}

	public float getModelScale() {
		return scale;
	}

	public float getAdjustY() {
		return adjustY;
	}

}
