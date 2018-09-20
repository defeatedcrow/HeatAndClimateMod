package defeatedcrow.hac.machine.gui;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.fluid.FluidIDRegisterDC;
import defeatedcrow.hac.machine.block.TileReactor;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageReactorButton;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiReactor extends GuiContainer {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate", "textures/gui/reactor_gui.png");
	private static final ResourceLocation iconTex = new ResourceLocation("dcs_climate", "textures/gui/gui_icons.png");
	/** The player inventory bound to this GUI. */
	private final InventoryPlayer playerInventory;
	private final TileReactor machine;

	public GuiReactor(TileReactor te, InventoryPlayer playerInv) {
		super(new ContainerReactor(te, playerInv));
		this.playerInventory = playerInv;
		this.machine = te;
		this.ySize = 200;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 92,
				4210752);

		String s1 = machine.getField(11) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(11)).name();
		String s2 = machine.getField(12) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(12)).name();
		String s3 = machine.getField(13) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(13)).name();
		String s4 = machine.getField(14) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(14)).name();
		this.fontRenderer.drawString(s1, 26, 9, 0xFFFFFF);
		this.fontRenderer.drawString(s2, 66, 9, 0xFFFFFF);
		this.fontRenderer.drawString(s3, 108, 39, 0xFFFFFF);
		this.fontRenderer.drawString(s4, 148, 39, 0xFFFFFF);
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
		if (isPointInRegion(11, 20, 12, 40, x, y)) {
			if (this.machine.getField(3) > -1) {
				int in = this.machine.getField(3);
				int inAmo = this.machine.getField(7);
				Fluid fluid = FluidIDRegisterDC.getFluid(in);
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}
		if (isPointInRegion(51, 20, 12, 40, x, y)) {
			if (this.machine.getField(4) > -1) {
				int in = this.machine.getField(4);
				int inAmo = this.machine.getField(8);
				Fluid fluid = FluidIDRegisterDC.getFluid(in);
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}
		if (isPointInRegion(93, 50, 12, 40, x, y)) {
			if (this.machine.getField(5) > -1) {
				int in = this.machine.getField(5);
				int inAmo = this.machine.getField(9);
				Fluid fluid = FluidIDRegisterDC.getFluid(in);
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}
		if (isPointInRegion(133, 50, 12, 40, x, y)) {
			if (this.machine.getField(6) > -1) {
				int in = this.machine.getField(6);
				int inAmo = this.machine.getField(10);
				Fluid fluid = FluidIDRegisterDC.getFluid(in);
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}

		String s1 = machine.getField(11) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(11)).name();
		String s2 = machine.getField(12) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(12)).name();
		String s3 = machine.getField(13) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(13)).name();
		String s4 = machine.getField(14) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(14)).name();
		if (isPointInRegion(28, 9, 12, 6, x, y)) {
			list.add(s1);
		}
		if (isPointInRegion(69, 9, 12, 6, x, y)) {
			list.add(s2);
		}
		if (isPointInRegion(110, 39, 12, 6, x, y)) {
			list.add(s3);
		}
		if (isPointInRegion(150, 39, 12, 6, x, y)) {
			list.add(s4);
		}

		if (isPointInRegion(12, 90, 40, 10, x, y)) {
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

		int l = this.getCookProgressScaled(52);
		if (l < 36) {
			this.drawTexturedModalRect(i + 89, j + 10, 176, 0, l, 21);
		} else {
			this.drawTexturedModalRect(i + 89, j + 10, 176, 0, 36, 21);
			this.drawTexturedModalRect(i + 121, j + 19, 176, 25, 13, l - 36);
		}

		if (this.machine.getField(3) > -1) {
			int in = this.machine.getField(3);
			int inAmo = 40 * this.machine.getField(7) / 4000;
			renderFluid(in, inAmo, i + 11, j + 20, 12, 40);
		}

		if (this.machine.getField(4) > -1) {
			int in = this.machine.getField(4);
			int inAmo = 40 * this.machine.getField(8) / 4000;
			renderFluid(in, inAmo, i + 51, j + 20, 12, 40);
		}

		if (this.machine.getField(5) > -1) {
			int in = this.machine.getField(5);
			int inAmo = 40 * this.machine.getField(9) / 4000;
			renderFluid(in, inAmo, i + 93, j + 50, 12, 40);
		}

		if (this.machine.getField(6) > -1) {
			int in = this.machine.getField(6);
			int inAmo = 40 * this.machine.getField(10) / 4000;
			renderFluid(in, inAmo, i + 133, j + 50, 12, 40);
		}

		this.mc.getTextureManager().bindTexture(iconTex);
		this.drawTexturedModalRect(i + 12, j + 92, 0, 16, 46, 7);
		IClimate clm = ClimateAPI.register.getClimateFromInt(machine.getField(2));
		if (clm != null) {
			int cl = clm.getHeat().getID() * 3;
			this.drawTexturedModalRect(i + 13 + cl, j + 89, 1, 25, 5, 10);
		}
	}

	private int getCookProgressScaled(int pixels) {
		int i = this.machine.getField(0);
		int j = this.machine.getMaxBurnTime();
		return i != 0 ? i * pixels / j : 0;
	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton) throws IOException {
		super.mouseClicked(x, y, mouseButton);
		boolean flag = this.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(mouseButton - 100);
		if (mouseButton == 0 || mouseButton == 1 || flag) {
			int i = x - this.guiLeft;
			int j = y - this.guiTop;
			int n = 0;
			if (isPointInRegion(28, 9, 12, 6, x, y)) {
				if (machine.getField(11) > 4) {
					n = 0;
				} else {
					n = machine.getField(11) + 1;
				}
				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK,
						1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageReactorButton(machine.getPos(), (byte) 0, (byte) n));
			}
			if (isPointInRegion(69, 9, 12, 6, x, y)) {
				if (machine.getField(12) > 4) {
					n = 0;
				} else {
					n = machine.getField(12) + 1;
				}
				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK,
						1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageReactorButton(machine.getPos(), (byte) 1, (byte) n));
			}
			if (isPointInRegion(110, 39, 12, 6, x, y)) {
				if (machine.getField(13) > 4) {
					n = 0;
				} else {
					n = machine.getField(13) + 1;
				}
				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK,
						1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageReactorButton(machine.getPos(), (byte) 2, (byte) n));
			}
			if (isPointInRegion(150, 39, 12, 6, x, y)) {
				if (machine.getField(14) > 4) {
					n = 0;
				} else {
					n = machine.getField(14) + 1;
				}
				mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK,
						1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageReactorButton(machine.getPos(), (byte) 3, (byte) n));
			}
		}
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
		BufferBuilder vertexBuffer = tessellator.getBuffer();
		vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexBuffer.pos(x, y, zLevel).tex(uMin, vMax).endVertex();
		vertexBuffer.pos(x + widL, y, zLevel).tex(uMax, vMax).endVertex();
		vertexBuffer.pos(x + widL, y - heiL, zLevel).tex(uMax, vMin).endVertex();
		vertexBuffer.pos(x, y - heiL, zLevel).tex(uMin, vMin).endVertex();
		tessellator.draw();
	}
}
