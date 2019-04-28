package defeatedcrow.hac.main.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionHeavyBootsDC extends Potion {

	public PotionHeavyBootsDC() {
		super(false, 0xFFEE50);
		this.setPotionName("dcs.potion.heavyboots");
		this.setIconIndex(0, 1);
		this.registerPotionAttributeModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, DCAttributeUtil.NOCKBACK_MODIFIER
				.toString(), 0.5D, 0);
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
