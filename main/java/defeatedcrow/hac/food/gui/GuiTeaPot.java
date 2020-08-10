package defeatedcrow.hac.food.gui;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.food.block.TileTeaPot;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTeaPot extends GuiBaseDC {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate", "textures/gui/teapot_gui.png");

	private final InventoryPlayer playerInventory;
	private final TileTeaPot pot;

	public GuiTeaPot(TileTeaPot te, InventoryPlayer playerInv) {
		super(new ContainerTeaPot(te, playerInv));
		this.playerInventory = playerInv;
		this.pot = te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format(this.pot.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 2, 4210752);

		String im = "" + pot.getField(5);
		int colorm = pot.getField(5) == 0 ? 0xCCCCCC : 0x00FFFF;
		String is = "" + pot.getField(6);
		int colors = pot.getField(6) == 0 ? 0xCCCCCC : 0x00FFFF;

		this.fontRenderer.drawString(im, 116 - this.fontRenderer.getStringWidth(im), 24, colorm, true);
		this.fontRenderer.drawString(is, 116 - this.fontRenderer.getStringWidth(is), 44, colors, true);

		if (this.isPointInRegion(-20, 4, 20, 20, mouseX, mouseY)) {
			List<String> list = new ArrayList<String>();
			if (pot != null) {
				list.add(I18n.format(pot.climateSuitableMassage()));
			}
			if (!list.isEmpty()) {
				this.drawHoveringText(list, mouseX - this.guiLeft, mouseY - this.guiTop);
			}
		}

		if (this.isPointInRegion(102, 18, 16, 16, mouseX, mouseY)) {
			List<String> list = new ArrayList<String>();
			if (pot != null) {
				DrinkMilk milk = DrinkMilk.getFromId(pot.getField(3));
				list.add("Milk: " + milk.toString());
				if (ClimateCore.proxy.isShiftKeyDown()) {
					list.add(I18n.format("dcs.tip.teapot"));
					list.add(I18n.format("dcs.tip.teapot.milk"));
				} else {
					list.add(I18n.format("dcs.tip.shift"));
				}
			}
			if (!list.isEmpty()) {
				this.drawHoveringText(list, mouseX - this.guiLeft - 80, mouseY - this.guiTop - 10);
			}
		}

		if (this.isPointInRegion(102, 36, 16, 16, mouseX, mouseY)) {
			List<String> list = new ArrayList<String>();
			if (pot != null) {
				DrinkSugar sugar = DrinkSugar.getFromId(pot.getField(4));
				list.add("Sugar: " + sugar.toString());
				if (ClimateCore.proxy.isShiftKeyDown()) {
					list.add(I18n.format("dcs.tip.teapot"));
					list.add(I18n.format("dcs.tip.teapot.sugar"));
				} else {
					list.add(I18n.format("dcs.tip.shift"));
				}
			}
			if (!list.isEmpty()) {
				this.drawHoveringText(list, mouseX - this.guiLeft - 80, mouseY - this.guiTop - 10);
			}
		}
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		this.drawDefaultBackground();
		super.drawScreen(x, y, par3);

		ArrayList<String> list = new ArrayList<String>();
		// String hov = "point " + x + "," + y;
		// list.add(hov);

		if (isPointInRegion(38, 20, 12, 50, x, y)) {
			if (!pot.inputT.isEmpty()) {
				int inAmo = 5000 * this.pot.inputT.getFluidAmount() / 5000;
				Fluid fluid = this.pot.inputT.getFluidType();
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}

		if (isPointInRegion(125, 20, 12, 50, x, y)) {
			if (!pot.outputT.isEmpty()) {
				int outAmo = 5000 * this.pot.outputT.getFluidAmount() / 5000;
				Fluid fluid = this.pot.outputT.getFluidType();
				if (fluid != null && outAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(outAmo + " mB");
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

		int l = this.getCookProgressScaled(20);
		this.drawTexturedModalRect(i + 78, j + 38, 176, 0, l, 11);

		if (pot.isSuitableClimate()) {
			this.drawTexturedModalRect(i - 20, j + 4, 176, 29, 20, 20);
		} else {
			this.drawTexturedModalRect(i - 20, j + 4, 176, 49, 20, 20);
		}

		if (!pot.inputT.isEmpty()) {
			Fluid in = this.pot.inputT.getFluidType();
			int inAmo = 50 * this.pot.inputT.getFluidAmount() / 5000;
			renderFluid(in, inAmo, i + 38, j + 20, 12, 50);
		}

		if (!pot.outputT.isEmpty()) {
			Fluid out = this.pot.outputT.getFluidType();
			int outAmo = 50 * this.pot.outputT.getFluidAmount() / 5000;
			renderFluid(out, outAmo, i + 125, j + 20, 12, 50);
		}
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.pot.getField(0);
		int j = this.pot.getField(1);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}

}
