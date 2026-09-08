package defeatedcrow.hac.core.client.effect;

import com.mojang.blaze3d.systems.RenderSystem;

import defeatedcrow.hac.core.client.DCTexturePath;
import defeatedcrow.hac.core.client.WRRHelper;
import defeatedcrow.hac.core.material.effects.MobEffectDC;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

public class DCEffectRenderer implements IClientMobEffectExtensions {

	@Override
	public boolean renderInventoryIcon(MobEffectInstance instance, EffectRenderingInventoryScreen<?> screen, GuiGraphics poseStack, int x, int y, int blit) {
		MobEffect effect = instance.getEffect();
		if (effect instanceof MobEffectDC dc) {
			RenderSystem.setShaderTexture(0, DCTexturePath.POTION.getLocation());
			WRRHelper.blitSprite(poseStack, DCTexturePath.POTION.getLocation(), x, y + 8, blit, 18, 18, dc.getIconX(), dc.getIconY());
			return true;
		}

		return false;
	}

	@Override
	public boolean renderInventoryText(MobEffectInstance instance, EffectRenderingInventoryScreen<?> screen, GuiGraphics poseStack, int x, int y, int blit) {
		// TODO +10レベル以上の場合
		return false;
	}

	@Override
	public boolean renderGuiIcon(MobEffectInstance instance, Gui gui, GuiGraphics poseStack, int x, int y, float z, float alpha) {
		MobEffect effect = instance.getEffect();
		if (effect instanceof MobEffectDC dc) {
			WRRHelper.blitSprite(poseStack, DCTexturePath.POTION.getLocation(), x + 3, y + 3, Mth.floor(z), 18, 18, dc.getIconX(), dc.getIconY());
			return true;
		}

		return false;
	}

}
