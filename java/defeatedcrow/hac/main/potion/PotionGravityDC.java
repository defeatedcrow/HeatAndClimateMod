package defeatedcrow.hac.main.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionGravityDC extends Potion {

	public PotionGravityDC() {
		super(true, 0x50FF50);
		this.setPotionName("dcs.potion.gravity");
		this.setIconIndex(1, 1);
	}

	@Override
	public boolean isInstant() {
		return true;
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}

	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().getTextureManager()
				.bindTexture(new ResourceLocation("dcs_climate:textures/gui/icons_potion.png"));
		return super.getStatusIconIndex();
	}

	@Override
	public boolean hasStatusIcon() {
		return true;
	}

}
