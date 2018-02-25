package defeatedcrow.hac.food.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import defeatedcrow.hac.food.block.TileFluidProcessorBase;
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
public class GuiFluidProcessor extends GuiContainer {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate",
			"textures/gui/fluidprocessor_gui.png");

	private final InventoryPlayer playerInventory;
	private final TileFluidProcessorBase processor;

	public GuiFluidProcessor(TileFluidProcessorBase te, InventoryPlayer playerInv) {
		super(new ContainerFluidProcessor(te, playerInv));
		this.playerInventory = playerInv;
		this.processor = te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.translateToLocal(this.processor.getName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 2, 4210752);

		if (this.isPointInRegion(-20, 4, 20, 20, mouseX, mouseY)) {
			List<String> list = new ArrayList<String>();
			if (processor != null) {
				list.add(I18n.translateToLocal(processor.climateSuitableMassage()));
			}
			if (!list.isEmpty()) {
				this.drawHoveringText(list, mouseX - this.guiLeft, mouseY - this.guiTop);
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
			if (!processor.inputT.isEmpty()) {
				int in = this.processor.getField(3);
				int inAmo = 5000 * this.processor.getField(5) / 5000;
				Fluid fluid = FluidIDRegisterDC.getFluid(in);
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}

		if (isPointInRegion(125, 20, 12, 50, x, y)) {
			if (!processor.outputT.isEmpty()) {
				int out = this.processor.getField(4);
				int outAmo = 5000 * this.processor.getField(6) / 5000;
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

		if (processor.isSuitableClimate()) {
			this.drawTexturedModalRect(i - 20, j + 4, 176, 29, 20, 20);
		} else {
			this.drawTexturedModalRect(i - 20, j + 4, 176, 49, 20, 20);
		}

		if (!processor.inputT.isEmpty()) {
			int in = this.processor.getField(3);
			int inAmo = 50 * this.processor.getField(5) / 5000;
			renderFluid(in, inAmo, i + 38, j + 20, 12, 50);
		}

		if (!processor.outputT.isEmpty()) {
			int out = this.processor.getField(4);
			int outAmo = 50 * this.processor.getField(6) / 5000;
			renderFluid(out, outAmo, i + 125, j + 20, 12, 50);
		}
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.processor.getField(0);
		int j = this.processor.getField(1);
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
