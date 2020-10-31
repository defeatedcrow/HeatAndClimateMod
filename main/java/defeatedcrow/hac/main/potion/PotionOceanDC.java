package defeatedcrow.hac.main.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionOceanDC extends Potion {

	public PotionOceanDC() {
		super(false, 0x5050FF);
		this.setPotionName("dcs.potion.ocean");
		this.setIconIndex(2, 0);
		this.registerPotionAttributeModifier(EntityLivingBase.SWIM_SPEED, DCAttributeUtil.SWIM_SPEED_MODIFIER
				.toString(), 2.0D, 0);
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return false;
	}

	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(
				"dcs_climate:textures/gui/icons_potion_main.png"));
		return super.getStatusIconIndex();
	}

	@Override
	public boolean hasStatusIcon() {
		return true;
	}

}
