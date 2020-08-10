package defeatedcrow.hac.machine.gui;

import java.io.IOException;
import java.util.ArrayList;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.machine.block.TileReactor;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageReactorButton;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiReactor extends GuiBaseDC {
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
		this.fontRenderer.drawString(this.playerInventory.getDisplayName()
				.getUnformattedText(), 8, this.ySize - 92, 4210752);

		String s1 = machine.getField(3) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(3)).name();
		String s2 = machine.getField(4) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(4)).name();
		String s3 = machine.getField(5) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(5)).name();
		String s4 = machine.getField(6) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(6)).name();
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
		if (isPointInRegion(51, 20, 12, 40, x, y)) {
			if (this.machine.inputT2.getFluidAmount() > 0) {
				int inAmo = this.machine.inputT2.getFluidAmount();
				Fluid fluid = this.machine.inputT2.getFluidType();
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}
		if (isPointInRegion(93, 50, 12, 40, x, y)) {
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
		if (isPointInRegion(133, 50, 12, 40, x, y)) {
			if (this.machine.outputT2.getFluidAmount() > 0) {
				int inAmo = this.machine.outputT2.getFluidAmount();
				Fluid fluid = this.machine.outputT2.getFluidType();
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}

		String s1 = machine.getField(11) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(3)).name();
		String s2 = machine.getField(12) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(4)).name();
		String s3 = machine.getField(13) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(5)).name();
		String s4 = machine.getField(14) > 5 ? "ANY" : EnumSide.fromIndex(machine.getField(7)).name();
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

		if (this.machine.inputT1.getFluidAmount() > 0) {
			Fluid in = this.machine.inputT1.getFluidType();
			int inAmo = 40 * this.machine.inputT1.getFluidAmount() / 4000;
			renderFluid(in, inAmo, i + 11, j + 20, 12, 40);
		}

		if (this.machine.inputT2.getFluidAmount() > 0) {
			Fluid in = this.machine.inputT2.getFluidType();
			int inAmo = 40 * this.machine.inputT2.getFluidAmount() / 4000;
			renderFluid(in, inAmo, i + 51, j + 20, 12, 40);
		}

		if (this.machine.outputT1.getFluidAmount() > 0) {
			Fluid in = this.machine.outputT1.getFluidType();
			int inAmo = 40 * this.machine.outputT1.getFluidAmount() / 4000;
			renderFluid(in, inAmo, i + 93, j + 50, 12, 40);
		}

		if (this.machine.outputT2.getFluidAmount() > 0) {
			Fluid in = this.machine.outputT2.getFluidType();
			int inAmo = 40 * this.machine.outputT2.getFluidAmount() / 4000;
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
		return i != 0 && j != 0 ? i * pixels / j : 0;
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
				if (machine.getField(3) > 4) {
					n = 0;
				} else {
					n = machine.getField(3) + 1;
				}
				mc.getSoundHandler().playSound(PositionedSoundRecord
						.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageReactorButton(machine.getPos(), (byte) 0, (byte) n));
			}
			if (isPointInRegion(69, 9, 12, 6, x, y)) {
				if (machine.getField(4) > 4) {
					n = 0;
				} else {
					n = machine.getField(4) + 1;
				}
				mc.getSoundHandler().playSound(PositionedSoundRecord
						.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageReactorButton(machine.getPos(), (byte) 1, (byte) n));
			}
			if (isPointInRegion(110, 39, 12, 6, x, y)) {
				if (machine.getField(5) > 4) {
					n = 0;
				} else {
					n = machine.getField(5) + 1;
				}
				mc.getSoundHandler().playSound(PositionedSoundRecord
						.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageReactorButton(machine.getPos(), (byte) 2, (byte) n));
			}
			if (isPointInRegion(150, 39, 12, 6, x, y)) {
				if (machine.getField(6) > 4) {
					n = 0;
				} else {
					n = machine.getField(6) + 1;
				}
				mc.getSoundHandler().playSound(PositionedSoundRecord
						.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				DCMainPacket.INSTANCE.sendToServer(new MessageReactorButton(machine.getPos(), (byte) 3, (byte) n));
			}
		}
	}

}
