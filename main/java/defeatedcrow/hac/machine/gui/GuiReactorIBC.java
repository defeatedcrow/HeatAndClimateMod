package defeatedcrow.hac.machine.gui;

import java.util.ArrayList;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.machine.block.TileReactorIBC;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiReactorIBC extends GuiBaseDC {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate",
			"textures/gui/reactor_ibc_gui.png");
	private static final ResourceLocation iconTex = new ResourceLocation("dcs_climate", "textures/gui/gui_icons.png");
	/** The player inventory bound to this GUI. */
	private final InventoryPlayer playerInventory;
	private final TileReactorIBC machine;

	public GuiReactorIBC(TileReactorIBC te, InventoryPlayer playerInv) {
		super(new ContainerReactorIBC(te, playerInv));
		this.playerInventory = playerInv;
		this.machine = te;
		this.ySize = 200;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format(machine.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
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
		if (isPointInRegion(30, 30, 12, 40, x, y)) {
			if (this.machine.inputT1.getFluidAmount() > 0) {
				int inAmo = this.machine.inputT1.getFluidAmount();
				Fluid fluid = this.machine.inputT1.getFluidType();
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}
		if (isPointInRegion(113, 30, 12, 40, x, y)) {
			if (this.machine.outputT1.getFluidAmount() > 0) {
				int inAmo = this.machine.outputT1.getFluidAmount();
				Fluid fluid = this.machine.outputT1.getFluidType();
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}

		if (isPointInRegion(105, 100, 40, 10, x, y)) {
			IClimate clm = ClimateAPI.register.getClimateFromInt(machine.getField(2));
			if (clm != null) {
				DCHeatTier h = clm.getHeat();
				list.add(h.toString());
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

		int l = this.getCookProgressScaled(39);
		this.drawTexturedModalRect(i + 68, j + 36, 176, 0, l, 21);

		if (this.machine.inputT1.getFluidAmount() > 0) {
			Fluid in = this.machine.inputT1.getFluidType();
			int inAmo = 40 * this.machine.inputT1.getFluidAmount() / 4000;
			renderFluid(in, inAmo, i + 30, j + 32, 12, 40);
		}

		if (this.machine.outputT1.getFluidAmount() > 0) {
			Fluid in = this.machine.outputT1.getFluidType();
			int inAmo = 40 * this.machine.outputT1.getFluidAmount() / 4000;
			renderFluid(in, inAmo, i + 113, j + 32, 12, 40);
		}

		this.mc.getTextureManager().bindTexture(iconTex);
		this.drawTexturedModalRect(i + 105, j + 102, 0, 16, 46, 7);
		IClimate clm = ClimateAPI.register.getClimateFromInt(machine.getField(2));
		if (clm != null) {
			int cl = clm.getHeat().getID() * 3;
			this.drawTexturedModalRect(i + 106 + cl, j + 99, 1, 25, 5, 10);
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
