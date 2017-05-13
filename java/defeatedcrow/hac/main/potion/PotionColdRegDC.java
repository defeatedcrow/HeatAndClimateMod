package defeatedcrow.hac.main.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionColdRegDC extends Potion {

	public PotionColdRegDC() {
		super(false, 0x50FFFF);
		this.setPotionName("dcs.potion.cold_registance");
		this.setIconIndex(1, 1);
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
		Minecraft.getMinecraft().getTextureManager()
				.bindTexture(new ResourceLocation("dcs_climate:textures/gui/icons_potion.png"));
		return super.getStatusIconIndex();
	}

	@Override
	public boolean hasStatusIcon() {
		return true;
	}

}
