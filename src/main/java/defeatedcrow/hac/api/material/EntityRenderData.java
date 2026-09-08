package defeatedcrow.hac.api.material;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityRenderData {

	final ResourceLocation texPath;
	final String location;
	final String outer;

	final float scale;
	final float adjustY;
	boolean isOuter;

	public EntityRenderData(String name, float s, float a) {
		texPath = new ResourceLocation(ClimateCore.MOD_ID, "textures/entity/" + name + ".png");
		location = name;
		outer = "main";
		scale = s;
		adjustY = a;
		isOuter = false;
	}

	public EntityRenderData(String name, float s, float a, String outerName) {
		texPath = new ResourceLocation(ClimateCore.MOD_ID, "textures/entity/" + name + ".png");
		location = name;
		outer = outerName;
		scale = s;
		adjustY = a;
		isOuter = true;
	}

	public ResourceLocation getTextureLocation() {
		return texPath;
	}

	@OnlyIn(Dist.CLIENT)
	public ModelLayerLocation getLayerLocation() {
		return new ModelLayerLocation(new ResourceLocation(ClimateCore.MOD_ID, location), "main");
	}

	@OnlyIn(Dist.CLIENT)
	public ModelLayerLocation getOuterLocation() {
		return new ModelLayerLocation(new ResourceLocation(ClimateCore.MOD_ID, location), outer);
	}

	public boolean hasOuter() {
		return isOuter;
	}

	public float getModelScale() {
		return scale;
	}

	public float getAdjustY() {
		return adjustY;
	}

}
