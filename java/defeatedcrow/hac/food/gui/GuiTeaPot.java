package defeatedcrow.hac.food.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import defeatedcrow.hac.food.block.TileTeaPot;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTeaPot extends GuiContainer {
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
		String s = I18n.translateToLocal(this.pot.getName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 2, 4210752);

		String im = "" + pot.getField(9);
		int colorm = pot.getField(9) == 0 ? 0xCCCCCC : 0x00FFFF;
		String is = "" + pot.getField(10);
		int colors = pot.getField(10) == 0 ? 0xCCCCCC : 0x00FFFF;

		this.fontRendererObj.drawString(im, 116 - this.fontRendererObj.getStringWidth(im), 24, colorm, true);
		this.fontRendererObj.drawString(is, 116 - this.fontRendererObj.getStringWidth(is), 44, colors, true);

		if (this.isPointInRegion(-20, 4, 20, 20, mouseX, mouseY)) {
			List<String> list = new ArrayList<String>();
			if (pot != null) {
				list.add(I18n.translateToLocal(pot.climateSuitableMassage()));
			}
			if (!list.isEmpty()) {
				this.drawHoveringText(list, mouseX - this.guiLeft, mouseY - this.guiTop);
			}
		}

		if (this.isPointInRegion(102, 18, 16, 16, mouseX, mouseY)) {
			List<String> list = new ArrayList<String>();
			if (pot != null) {
				DrinkMilk milk = DrinkMilk.getFromId(pot.getField(7));
				list.add("Milk: " + milk.toString());
				if (ClimateCore.proxy.isShiftKeyDown()) {
					list.add(I18n.translateToLocal("dcs.tip.teapot"));
					list.add(I18n.translateToLocal("dcs.tip.teapot.milk"));
				} else {
					list.add(I18n.translateToLocal("dcs.tip.shift"));
				}
			}
			if (!list.isEmpty()) {
				this.drawHoveringText(list, mouseX - this.guiLeft - 80, mouseY - this.guiTop - 10);
			}
		}

		if (this.isPointInRegion(102, 36, 16, 16, mouseX, mouseY)) {
			List<String> list = new ArrayList<String>();
			if (pot != null) {
				DrinkSugar sugar = DrinkSugar.getFromId(pot.getField(8));
				list.add("Sugar: " + sugar.toString());
				if (ClimateCore.proxy.isShiftKeyDown()) {
					list.add(I18n.translateToLocal("dcs.tip.teapot"));
					list.add(I18n.translateToLocal("dcs.tip.teapot.sugar"));
				} else {
					list.add(I18n.translateToLocal("dcs.tip.shift"));
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
				int in = this.pot.getField(3);
				int inAmo = 5000 * this.pot.getField(5) / 5000;
				Fluid fluid = FluidIDRegisterDC.getFluid(in);
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}

		if (isPointInRegion(125, 20, 12, 50, x, y)) {
			if (!pot.outputT.isEmpty()) {
				int out = this.pot.getField(4);
				int outAmo = 5000 * this.pot.getField(6) / 5000;
				Fluid fluid = FluidIDRegisterDC.getFluid(out);
				if (fluid != null && outAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(outAmo + " mB");
				}
			}
		}

		this.drawHoveringText(list, x, y);
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
			int in = this.pot.getField(3);
			int inAmo = 50 * this.pot.getField(5) / 5000;
			renderFluid(in, inAmo, i + 38, j + 20, 12, 50);
		}

		if (!pot.outputT.isEmpty()) {
			int out = this.pot.getField(4);
			int outAmo = 50 * this.pot.getField(6) / 5000;
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

	protected void renderFluid(int id, int amo, int x, int y, int width, int height) {
		Fluid fluid = FluidIDRegisterDC.getFluid(id);
		if (fluid != null) {
			TextureMap textureMapBlocks = mc.getTextureMapBlocks();
			ResourceLocation res = fluid.getStill();
			TextureAtlasSprite spr = null;
			if (res != null) {
				spr = textureMapBlocks.getTextureExtry(res.toString());
			}
			if (spr == null) {
				spr = textureMapBlocks.getMissingSprite();
			}
			mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			setGLColorFromInt(fluid.getColor());

			int widR = width;
			int heiR = amo;
			int yR = y + height;

			int widL = 0;
			int heiL = 0;

			for (int i = 0; i < widR; i += 16) {
				for (int j = 0; j < heiR; j += 16) {
					widL = Math.min(widR - i, 16);
					heiL = Math.min(heiR - j, 16);
					if (widL > 0 && heiL > 0) {
						drawFluidTexture(x + i, yR - j, spr, widL, heiL, 100);
					}
				}
			}
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
		}
	}

	public static void setGLColorFromInt(int color) {
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}

	private static void drawFluidTexture(double x, double y, TextureAtlasSprite spr, int widL, int heiL,
			double zLevel) {
		double uMin = spr.getMinU();
		double uMax = spr.getMaxU();
		double vMin = spr.getMinV();
		double vMax = spr.getMaxV();
		double l = heiL / 16.0D;
		vMax = vMin + ((vMax - vMin) * l);

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexBuffer = tessellator.getBuffer();
		vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexBuffer.pos(x, y, zLevel).tex(uMin, vMax).endVertex();
		vertexBuffer.pos(x + widL, y, zLevel).tex(uMax, vMax).endVertex();
		vertexBuffer.pos(x + widL, y - heiL, zLevel).tex(uMax, vMin).endVertex();
		vertexBuffer.pos(x, y - heiL, zLevel).tex(uMin, vMin).endVertex();
		tessellator.draw();
	}

}
