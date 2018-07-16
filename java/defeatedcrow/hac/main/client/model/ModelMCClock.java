package defeatedcrow.hac.main.client.model;

import defeatedcrow.hac.core.util.DCTimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMCClock extends ModelLargeClock {

	@Override
	public void setRotationAngles(float f, float speed, float tick) {
		setRotationAngles(f);
		long time = DCTimeHelper.time(Minecraft.getMinecraft().world) + 6000;
		float hour = (time / 1000) * 360.0F / 12.0F;
		float f1 = (float) (hour * Math.PI / 180F);
		long m = (time % 1000) * 60 / 1000;
		float min = m * 360.0F / 60.0F;
		float f2 = (float) (min * Math.PI / 180F);

		hand_L.rotateAngleX = f2 - (float) Math.PI;
		hand_S.rotateAngleX = f1 - (float) Math.PI;
	}

}