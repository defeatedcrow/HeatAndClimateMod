package defeatedcrow.hac.main.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionNimbleDC extends Potion {

	public PotionNimbleDC() {
		super(false, 0xFF5050);
		this.setPotionName("dcs.potion.nimble");
		this.setIconIndex(2, 1);
		this.registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED, DCAttributeUtil.ATTACK_SPEED_MODIFIER
				.toString(), 1.0D, 0);
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
				"dcs_climate:textures/gui/icons_potion.png"));
		return super.getStatusIconIndex();
	}

	@Override
	public boolean hasStatusIcon() {
		return true;
	}

}
