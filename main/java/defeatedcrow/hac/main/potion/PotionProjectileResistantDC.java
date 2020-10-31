package defeatedcrow.hac.main.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionProjectileResistantDC extends Potion {

	public PotionProjectileResistantDC() {
		super(false, 0xC0FF30);
		this.setPotionName("dcs.potion.projectile_resistant");
		this.setIconIndex(3, 0);
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
