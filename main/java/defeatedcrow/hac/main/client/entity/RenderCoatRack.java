package defeatedcrow.hac.main.client.entity;

import defeatedcrow.hac.main.client.model.ModelCoatRack;
import net.minecraft.client.model.ModelArmorStandArmor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCoatRack extends RenderLivingBase<EntityArmorStand> {
	private static final ResourceLocation TEX = new ResourceLocation("dcs_climate",
			"textures/entity/coat_rack.png");

	public RenderCoatRack(RenderManager manager) {
		super(manager, new ModelCoatRack(), 0.0F);
		LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
			protected void initArmor() {
				this.modelLeggings = new ModelCoat(0F);
				this.modelArmor = new ModelCoat(0.5F);
			}
		};
		this.addLayer(layerbipedarmor);
	}

	protected ResourceLocation getEntityTexture(EntityArmorStand entity) {
		return TEX;
	}

	public ModelCoatRack getMainModel() {
		return (ModelCoatRack) super.getMainModel();
	}

	protected void applyRotations(EntityArmorStand entityLiving, float p_77043_2_, float rotationYaw,
			float partialTicks) {
		GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
		float f = (float) (entityLiving.world.getTotalWorldTime() - entityLiving.punchCooldown) + partialTicks;

		if (f < 5.0F) {
			GlStateManager.rotate(MathHelper.sin(f / 1.5F * (float) Math.PI) * 3.0F, 0.0F, 1.0F, 0.0F);
		}
	}

	protected boolean canRenderName(EntityArmorStand entity) {
		return entity.getAlwaysRenderNameTag();
	}

	public void doRender(EntityArmorStand entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (entity.hasMarker()) {
			this.renderMarker = true;
		}

		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		if (entity.hasMarker()) {
			this.renderMarker = false;
		}
	}

	public class ModelCoat extends ModelArmorStandArmor {
		public ModelCoat() {
			super(0.0F);
		}

		public ModelCoat(float modelSize) {
			super(modelSize);
		}

		public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
				float headPitch, float scaleFactor, Entity entityIn) {
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
			if (entityIn instanceof EntityArmorStand) {
				this.bipedHead.setRotationPoint(0.0F, -2.0F, 0.0F);
			}
		}
	}
}
