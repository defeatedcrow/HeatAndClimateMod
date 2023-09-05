package defeatedcrow.hac.core.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.core.client.DCTexturePath;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LockButton extends Button {
	private final boolean isForward;

	public LockButton(int x, int y, boolean b, Button.OnPress press) {
		super(x, y, 12, 20, CommonComponents.EMPTY, press);
		this.isForward = b;
	}

	@Override
	public void renderButton(PoseStack pose, int i1, int i2, float i3) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_INV_SINGLE.getLocation());
		int i = 177;
		int j = 0;

		if (!this.isForward) {
			j += 20;
		}

		this.blit(pose, this.x, this.y, i, j, 12, 20);
	}

	@Override
	public void playDownSound(SoundManager soundManager) {
		soundManager.play(SimpleSoundInstance.forUI(SoundEvents.IRON_DOOR_OPEN, 1.0F));
	}
}
