package defeatedcrow.hac.main.potion;

import defeatedcrow.hac.main.api.IWideMining;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionWideMiningDC extends Potion implements IWideMining {

	public PotionWideMiningDC() {
		super(false, 0xFF2020);
		this.setPotionName("dcs.potion.wide_mining");
		this.setIconIndex(5, 1);
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

	@Override
	public int getMiningRange(EntityPlayer player, ItemStack stack, int level) {
		return level + 1;
	}

}
