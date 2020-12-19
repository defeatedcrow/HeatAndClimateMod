package defeatedcrow.hac.machine.gui;

import java.util.ArrayList;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.machine.entity.EntityScooter;
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
public class GuiEntityScooter extends GuiBaseDC {
	private static final ResourceLocation TEXTURE = new ResourceLocation("dcs_climate",
			"textures/gui/entityscooter_gui.png");
	private final EntityScooter processor;
	private final InventoryPlayer playerInventory;

	public GuiEntityScooter(EntityScooter inv, EntityPlayer player) {
		super(new ContainerEntityScooter(inv, player));
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

		if (!processor.tank.isEmpty()) {
			Fluid in = processor.tank.getFluidType();
			int inAmo = 50 * processor.getField(3) / 5000;
			renderFluid(in, inAmo, i + 38, j + 18, 12, 50);
		}
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		this.drawDefaultBackground();
		super.drawScreen(x, y, par3);

		ArrayList<String> list = new ArrayList<String>();
		// String hov = "point " + x + "," + y;
		// list.add(hov);

		if (isPointInRegion(38, 18, 12, 50, x, y)) {
			FluidStack f = processor.tank.getFluid();
			if (f != null) {
				Fluid in = f.getFluid();
				int inAmo = 5000 * f.amount / 5000;
				if (in != null && inAmo > 0) {
					String nameIn = in.getLocalizedName(new FluidStack(in, 1000));
					list.add(nameIn);
					list.add(inAmo + " mB");
				}
			} else {
				list.add("Empty");
			}
		}

		this.drawHoveringText(list, x, y);
		this.renderHoveredToolTip(x, y);
	}

}
