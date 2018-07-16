package defeatedcrow.hac.main.client.model;

import java.util.Calendar;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelLargeClock extends DCTileModelBase {
	// fields
	ModelRenderer boad;
	ModelRenderer hand_L;
	ModelRenderer hand_S;
	ModelRenderer boad2;

	public ModelLargeClock() {
		textureWidth = 64;
		textureHeight = 32;

		boad = new ModelRenderer(this, 0, 0);
		boad.addBox(-1F, -2F, -2F, 2, 4, 4);
		boad.setRotationPoint(0F, 0F, 0F);
		boad.setTextureSize(64, 32);
		boad.mirror = true;
		setRotation(boad, 0F, 0F, 0F);
		hand_L = new ModelRenderer(this, 16, 0);
		hand_L.addBox(-2.5F, -6F, -4F, 0, 24, 8);
		hand_L.setRotationPoint(0F, 0F, 0F);
		hand_L.setTextureSize(64, 32);
		hand_L.mirror = true;
		setRotation(hand_L, 0F, 0F, 0F);
		hand_S = new ModelRenderer(this, 32, 0);
		hand_S.addBox(-2.3F, -4F, -4F, 0, 16, 8);
		hand_S.setRotationPoint(0F, 0F, 0F);
		hand_S.setTextureSize(64, 32);
		hand_S.mirror = true;
		setRotation(hand_S, 0F, 0F, 0F);
		boad2 = new ModelRenderer(this, 0, 8);
		boad2.addBox(-2F, -1F, -1F, 1, 2, 2);
		boad2.setRotationPoint(0F, 0F, 0F);
		boad2.setTextureSize(64, 32);
		boad2.mirror = true;
		setRotation(boad2, 0F, 0F, 0F);
	}

	@Override
	public void render(float f, float speed, float tick) {
		setRotationAngles(f, speed, tick);
		boad.render(0.0625F);
		boad2.render(0.0625F);
		hand_L.render(0.0625F);
		hand_S.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		Calendar cal = Calendar.getInstance();
		float hour = cal.get(11) * 360.0F / 12.0F;
		hour += cal.get(12) * 360.0F / 720.0F;
		float f1 = (float) (hour * Math.PI / 180F);
		float min = cal.get(12) * 360.0F / 60.0F;
		float f2 = (float) (min * Math.PI / 180F);

		hand_L.rotateAngleX = f2 - (float) Math.PI;
		hand_S.rotateAngleX = f1 - (float) Math.PI;
	}

}