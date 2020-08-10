package defeatedcrow.hac.machine.gui;

import java.util.ArrayList;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.machine.block.TileHopperFluid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiHopperFluid extends GuiBaseDC {
	private static final ResourceLocation TEXTURE = new ResourceLocation("dcs_climate",
			"textures/gui/hopperfluid_gui.png");
	private final TileHopperFluid processor;
	private final InventoryPlayer playerInventory;

	public GuiHopperFluid(TileHopperFluid inv, EntityPlayer player) {
		super(new ContainerHopperFluid(inv, player));
		this.processor = inv;
		this.playerInventory = player.inventory;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format(this.processor.getName());
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

		if (!processor.inputT.isEmpty()) {
			Fluid in = this.processor.inputT.getFluidType();
			int inAmo = 50 * this.processor.inputT.getFluidAmount() / 5000;
			renderFluid(in, inAmo, i + 92, j + 20, 12, 50);
		}
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		this.drawDefaultBackground();
		super.drawScreen(x, y, par3);

		ArrayList<String> list = new ArrayList<String>();
		// String hov = "point " + x + "," + y;
		// list.add(hov);

		if (isPointInRegion(92, 20, 12, 50, x, y)) {
			if (!processor.inputT.isEmpty()) {
				int inAmo = 5000 * this.processor.inputT.getFluidAmount() / 5000;
				Fluid fluid = this.processor.inputT.getFluidType();
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

}
