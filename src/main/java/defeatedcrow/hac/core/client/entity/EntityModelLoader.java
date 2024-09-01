package defeatedcrow.hac.core.client.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.core.client.entity.model.ModelMagicFin;
import defeatedcrow.hac.core.client.entity.model.ModelMagicWing;
import defeatedcrow.hac.core.client.entity.model.ModelThinArmor;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

public class EntityModelLoader implements ResourceManagerReloadListener {

	public static EntityModelLoader INSTANCE = new EntityModelLoader();

	public static ModelThinArmor MODEL_BOOTS;
	public static ModelThinArmor MODEL_SKIRT;
	public static ModelThinArmor MODEL_LEGGINS;
	public static ModelThinArmor MODEL_SHIRT;
	public static ModelThinArmor MODEL_JACKET;
	public static ModelThinArmor MODEL_OVERSUITS;
	public static ModelThinArmor MODEL_SUITS;
	public static ModelThinArmor MODEL_HAT;
	public static ModelThinArmor MODEL_DRESS;

	public static ModelMagicWing MODEL_WING;
	public static ModelMagicFin MODEL_FIN;

	@Override
	public void onResourceManagerReload(ResourceManager res) {
		MODEL_BOOTS = new ModelThinArmor(Minecraft.getInstance().getEntityModels().bakeLayer(BOOTS.getLayerLocation()));
		MODEL_SKIRT = new ModelThinArmor(Minecraft.getInstance().getEntityModels().bakeLayer(SKIRT.getLayerLocation()));
		MODEL_LEGGINS = new ModelThinArmor(Minecraft.getInstance().getEntityModels().bakeLayer(LEGGINS.getLayerLocation()));
		MODEL_SHIRT = new ModelThinArmor(Minecraft.getInstance().getEntityModels().bakeLayer(SHIRT.getLayerLocation()));
		MODEL_JACKET = new ModelThinArmor(Minecraft.getInstance().getEntityModels().bakeLayer(JACKET.getLayerLocation()));
		MODEL_OVERSUITS = new ModelThinArmor(Minecraft.getInstance().getEntityModels().bakeLayer(OVERSUITS.getLayerLocation()));
		MODEL_SUITS = new ModelThinArmor(Minecraft.getInstance().getEntityModels().bakeLayer(SUITS.getLayerLocation()));
		MODEL_HAT = new ModelThinArmor(Minecraft.getInstance().getEntityModels().bakeLayer(HAT.getLayerLocation()));
		MODEL_DRESS = new ModelThinArmor(Minecraft.getInstance().getEntityModels().bakeLayer(LONG.getLayerLocation()));
		MODEL_WING = new ModelMagicWing(Minecraft.getInstance().getEntityModels().bakeLayer(WING.getLayerLocation()));
		MODEL_FIN = new ModelMagicFin(Minecraft.getInstance().getEntityModels().bakeLayer(FIN.getLayerLocation()));
	}

	public static final EntityRenderData BOOTS = new EntityRenderData("armor/boots_linen", 1F, 0F);
	public static final EntityRenderData SKIRT = new EntityRenderData("armor/skirt_linen", 1F, 0F);
	public static final EntityRenderData LEGGINS = new EntityRenderData("armor/leggins_linen", 1F, 0F);
	public static final EntityRenderData SHIRT = new EntityRenderData("armor/shirt_linen", 1F, 0F);
	public static final EntityRenderData JACKET = new EntityRenderData("armor/jacket_linen", 1F, 0F);
	public static final EntityRenderData OVERSUITS = new EntityRenderData("armor/suits_cloth", 1F, 0F);
	public static final EntityRenderData SUITS = new EntityRenderData("armor/suits_linen", 1F, 0F);
	public static final EntityRenderData HAT = new EntityRenderData("armor/hat_linen", 1F, 0F);
	public static final EntityRenderData LONG = new EntityRenderData("armor/long_linen", 1F, 0F);

	public static final EntityRenderData WING = new EntityRenderData("magic/magic_wing", 1F, 0F);
	public static final EntityRenderData FIN = new EntityRenderData("magic/magic_fin", 1F, 0F);

}
