package defeatedcrow.hac.machine.client.gui;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.DCTexturePath;
import defeatedcrow.hac.core.network.packet.message.MsgTileOwnerKeyToS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KichenBenchScreen extends AbstractContainerScreen<KichenBenchMenu> implements RecipeUpdateListener {
	private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("textures/gui/recipe_button.png");
	private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
	private boolean widthTooNarrow;

	public KichenBenchScreen(KichenBenchMenu menu, Inventory playerInv, Component comp) {
		super(menu, playerInv, comp);
		this.imageHeight = 230;
		this.titleLabelX = 8;
		this.titleLabelY = 11;
		this.inventoryLabelX = 8;
		this.inventoryLabelY = this.imageHeight - 92;
	}

	@Override
	protected void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
		this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
		this.addRenderableWidget(new ImageButton(this.leftPos + 8, this.height / 2 - 48, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, (button) -> {
			this.recipeBookComponent.toggleVisibility();
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
			((ImageButton) button).setPosition(this.leftPos + 8, this.height / 2 - 48);
		}));
		this.addWidget(this.recipeBookComponent);
		this.setInitialFocus(this.recipeBookComponent);
		this.titleLabelX = 8;
	}

	@Override
	public void containerTick() {
		super.containerTick();
		this.recipeBookComponent.tick();
	}

	@Override
	public void render(PoseStack pose, int mx, int my, float f) {
		this.renderBackground(pose);

		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBg(pose, f, mx, my);
			this.recipeBookComponent.render(pose, mx, my, f);
		} else {
			this.recipeBookComponent.render(pose, mx, my, f);
			super.render(pose, mx, my, f);
			this.recipeBookComponent.renderGhostRecipe(pose, this.leftPos, this.topPos, true, f);
		}

		List<Component> list = Lists.newArrayList();
		boolean lock = this.getMenu().getContainer().isLocked();
		if (this.isHovering(156, 3, 12, 20, mx, my)) {
			if (lock) {
				list.add(Component.translatable("dcs.tip.container.ownable_locked", this.getMenu().getContainer().getOwnerName()));
			} else {
				if (ClimateCore.proxy.keyShiftPushed())
					list.add(Component.translatable("dcs.tip.container.ownable"));
				else
					list.add(Component.translatable("dcs.tip.container.ownable_short"));
			}
		}

		this.renderTooltip(pose, mx, my);
		this.renderComponentTooltip(pose, list, mx, my);
		this.recipeBookComponent.renderTooltip(pose, this.leftPos, this.topPos, mx, my);
	}

	@Override
	protected void renderBg(PoseStack pose, float f, int mx, int my) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, DCTexturePath.GUI_KICHEN_BENCH.getLocation());
		int i = leftPos;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(pose, i, j, 0, 0, this.imageWidth, this.imageHeight);

		boolean lock = this.getMenu().getContainer().isLocked();
		if (lock) {
			this.blit(pose, i + 156, j + 3, 176, 21, 12, 21);
		} else {
			this.blit(pose, i + 156, j + 3, 176, 0, 12, 21);
		}
	}

	@Override
	protected boolean isHovering(int ix, int iy, int ix2, int iy2, double dx, double dy) {
		return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(ix, iy, ix2, iy2, dx, dy);
	}

	@Override
	public boolean mouseClicked(double x, double y, int i0) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;

		double dx = x - (i + 156);
		double dy = y - (j + 3);
		if (dx >= 0.0D && dy >= 0.0D && dx < 112.0D && dy < 21.0D) {
			if (this.getMenu().getContainer() != null && this.getMenu().isOwner) {
				boolean b = this.getMenu().getContainer().toggleLock();
				MsgTileOwnerKeyToS.sendToServer(this.minecraft.player, this.getMenu().getContainer().getBlockPos(), b);
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.IRON_DOOR_OPEN, 1.0F));
				return true;
			}
		}
		if (this.recipeBookComponent.mouseClicked(x, y, i0)) {
			this.setFocused(this.recipeBookComponent);
			return true;
		} else {
			return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? true : super.mouseClicked(x, y, i0);
		}
	}

	@Override
	protected boolean hasClickedOutside(double dx, double dy, int ix, int iy, int iz) {
		boolean flag = dx < ix || dy < iy || dx >= ix + this.imageWidth || dy >= iy + this.imageHeight;
		return this.recipeBookComponent.hasClickedOutside(dx, dy, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, iz) && flag;
	}

	@Override
	protected void slotClicked(Slot slotIn, int x, int py, ClickType click) {
		super.slotClicked(slotIn, x, py, click);
		this.recipeBookComponent.slotClicked(slotIn);
	}

	@Override
	public void recipesUpdated() {
		this.recipeBookComponent.recipesUpdated();
	}

	@Override
	public void removed() {
		this.recipeBookComponent.removed();
		super.removed();
	}

	@Override
	public RecipeBookComponent getRecipeBookComponent() {
		return this.recipeBookComponent;
	}

}
