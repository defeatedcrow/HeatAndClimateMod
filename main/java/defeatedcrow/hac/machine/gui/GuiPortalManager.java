package defeatedcrow.hac.machine.gui;

import java.util.ArrayList;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.machine.block.TilePortalManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPortalManager extends GuiBaseDC {
	private static final ResourceLocation TEXTURE = new ResourceLocation("dcs_climate",
			"textures/gui/portal_manager_gui.png");
	private static final ResourceLocation iconTex = new ResourceLocation("dcs_climate", "textures/gui/gui_icons.png");
	private final TilePortalManager tile;
	private final InventoryPlayer playerInventory;

	public GuiPortalManager(TilePortalManager inv, EntityPlayer player) {
		super(new ContainerPortalManager(inv, player));
		this.tile = inv;
		this.playerInventory = player.inventory;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.translateToLocal(this.tile.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName()
				.getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		for (int k = 0; k < 6; k++) {
			if (tile.getField(k) == 1) {
				this.drawTexturedModalRect(i + 18 * k + 50, j + 38, 176, 0, 4, 12);
			} else if (tile.getField(k) == 2) {
				this.drawTexturedModalRect(i + 18 * k + 50, j + 38, 176, 12, 4, 12);
			}
		}

		this.mc.getTextureManager().bindTexture(iconTex);
		if (tile.isSuitableClimate()) {
			this.drawTexturedModalRect(i - 20, j + 4, 48, 0, 20, 20);
		} else {
			this.drawTexturedModalRect(i - 20, j + 4, 48, 20, 20, 20);
		}

		if (!tile.inputT.isEmpty()) {
			Fluid in = this.tile.inputT.getFluidType();
			int inAmo = 50 * this.tile.inputT.getFluidAmount() / 5000;
			renderFluid(in, inAmo, i + 18, j + 17, 12, 50);
		}
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		this.drawDefaultBackground();
		super.drawScreen(x, y, par3);

		ArrayList<String> list = new ArrayList<String>();
		// String hov = "point " + x + "," + y;
		// list.add(hov);

		if (isPointInRegion(18, 17, 12, 50, x, y)) {
			if (!tile.inputT.isEmpty()) {
				int inAmo = 5000 * this.tile.inputT.getFluidAmount() / 5000;
				Fluid fluid = this.tile.inputT.getFluidType();
				if (fluid != null && inAmo > 0) {
					String nameIn = fluid.getLocalizedName(new FluidStack(fluid, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			}
		}

		if (this.isPointInRegion(45, 20, 108, 18, x, y)) {
			if (tile != null) {
				list.add("Input Card");
			}
		}

		if (this.isPointInRegion(45, 50, 108, 18, x, y)) {
			if (tile != null) {
				list.add("Output Card");
			}
		}

		if (this.isPointInRegion(-20, 4, 20, 20, x, y)) {
			if (tile != null) {
				list.add(I18n.translateToLocal(tile.climateSuitableMassage()));
			}
		}

		this.drawHoveringText(list, x, y);
		this.renderHoveredToolTip(x, y);
	}
}
