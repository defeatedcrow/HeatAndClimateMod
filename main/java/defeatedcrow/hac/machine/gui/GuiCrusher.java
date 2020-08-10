package defeatedcrow.hac.machine.gui;

import java.util.ArrayList;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.machine.block.TileRollerCrusher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCrusher extends GuiBaseDC {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate", "textures/gui/crusher_gui.png");
	/** The player inventory bound to this GUI. */
	private final InventoryPlayer playerInventory;
	private final TileRollerCrusher machine;

	public GuiCrusher(TileRollerCrusher te, InventoryPlayer playerInv) {
		super(new ContainerCrusher(te, playerInv));
		this.playerInventory = playerInv;
		this.machine = te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.translateToLocal(this.machine.getName());
		this.fontRenderer.drawString(this.playerInventory.getDisplayName()
				.getUnformattedText(), 8, this.ySize - 92, 4210752);
	}

	@Override
	public void drawScreen(int x, int y, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(x, y, partialTicks);
		ArrayList<String> list = new ArrayList<String>();
		// if (ClimateCore.isDebug) {
		// int i = x - this.guiLeft;
		// int j = y - this.guiTop;
		// list.add("Point:" + i + ", " + j);
		// }
		if (isPointInRegion(121, 16, 12, 52, x, y)) {
			if (!this.machine.outputT1.isEmpty()) {
				Fluid fluid = this.machine.outputT1.getFluidType();
				int inAmo = this.machine.outputT1.getFluidAmount();
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}

		this.drawHoveringText(list, x, y);
		this.renderHoveredToolTip(x, y);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTex());
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		int l = this.getCookProgressScaled(42);
		this.drawTexturedModalRect(i + 51, j + 22, 176, 0, l, 23);

		if (!this.machine.outputT1.isEmpty()) {
			Fluid in = this.machine.outputT1.getFluidType();
			int inAmo = 40 * this.machine.outputT1.getFluidAmount() / 5000;
			renderFluid(in, inAmo, i + 121, j + 18, 12, 50);
		}
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.machine.getField(0);
		int j = this.machine.getMaxBurnTime();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}
}
