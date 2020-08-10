package defeatedcrow.hac.machine.gui;

import java.util.ArrayList;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.machine.block.TileDieselEngine;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDieselEngine extends GuiBaseDC {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate",
			"textures/gui/chamber_fluid_gui.png");
	private static final ResourceLocation iconTex = new ResourceLocation("dcs_climate", "textures/gui/gui_icons.png");

	private final InventoryPlayer playerInventory;
	private final TileDieselEngine machine;

	public GuiDieselEngine(TileDieselEngine te, InventoryPlayer playerInv) {
		super(new ContainerDieselEngine(te, playerInv));
		this.playerInventory = playerInv;
		this.machine = te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.translateToLocal(this.machine.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTex());
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (this.machine.isActive()) {
			this.drawTexturedModalRect(i + 75, j + 23, 176, 0, 26, 26);
		}

		int l = this.getCookProgressScaled(32);
		this.drawTexturedModalRect(i + 72, j + 51, 202, 0, 32 - l, 3);

		if (!machine.inputT.isEmpty()) {
			Fluid in = this.machine.inputT.getFluidType();
			int inAmo = 50 * this.machine.inputT.getFluidAmount() / 5000;
			renderFluid(in, inAmo, i + 125, j + 20, 12, 50);
		}

		// airflow data
		this.mc.getTextureManager().bindTexture(iconTex);
		if (machine.isSuitableClimate()) {
			this.drawTexturedModalRect(i - 20, j + 4, 48, 0, 20, 20);
		} else {
			this.drawTexturedModalRect(i - 20, j + 4, 48, 20, 20, 20);
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
			if (!machine.inputT.isEmpty()) {
				int inAmo = 5000 * this.machine.inputT.getFluidAmount() / 5000;
				Fluid fluid = this.machine.inputT.getFluidType();
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}

		if (this.isPointInRegion(-20, 4, 20, 20, x, y)) {
			if (machine != null) {
				list.addAll(machine.climateSuitableMassage());
			}
		}

		this.drawHoveringText(list, x, y);
		this.renderHoveredToolTip(x, y);
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.machine.getField(0);
		int j = this.machine.getField(1);
		return j != 0 && i != 0 ? (j - i) * pixels / j : 0;
	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}

}
