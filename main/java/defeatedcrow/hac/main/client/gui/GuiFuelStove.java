package defeatedcrow.hac.main.client.gui;

import java.util.ArrayList;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.main.block.device.TileCookingStove;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiFuelStove extends GuiBaseDC {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate",
			"textures/gui/chamber_fluid_gui.png");

	private final InventoryPlayer playerInventory;
	private final TileCookingStove stove;

	public GuiFuelStove(TileCookingStove te, InventoryPlayer playerInv) {
		super(new ContainerFuelStove(te, playerInv));
		this.playerInventory = playerInv;
		this.stove = te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.translateToLocal(this.stove.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTex());
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (this.stove.isActive()) {
			this.drawTexturedModalRect(i + 75, j + 23, 176, 0, 26, 26);
		}

		int l = this.getCookProgressScaled(32);
		this.drawTexturedModalRect(i + 72, j + 51, 202, 0, 32 - l, 3);

		if (!stove.inputT.isEmpty()) {
			Fluid fluid = stove.inputT.getFluidType();
			int inAmo = 50 * stove.inputT.getFluidAmount() / 5000;
			renderFluid(fluid, inAmo, i + 125, j + 20, 12, 50);
		}
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		this.drawDefaultBackground();
		super.drawScreen(x, y, par3);

		ArrayList<String> list = new ArrayList<String>();
		// String hov = "point " + x + "," + y;
		// list.add(hov);

		if (isPointInRegion(125, 20, 12, 50, x, y)) {
			if (!stove.inputT.isEmpty()) {
				int inAmo = 5000 * stove.inputT.getFluidAmount() / 5000;
				Fluid fluid = stove.inputT.getFluidType();
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

	private int getCookProgressScaled(int pixels) {
		int i = this.stove.getField(0);
		int j = this.stove.getField(1);
		return j != 0 && i != 0 ? (j - i) * pixels / j : 0;
	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}
}
