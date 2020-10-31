package defeatedcrow.hac.main.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionDigestiveDC extends Potion {

	public PotionDigestiveDC() {
		super(false, 0xFFC030);
		this.setPotionName("dcs.potion.digestive");
		this.setIconIndex(4, 1);
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
