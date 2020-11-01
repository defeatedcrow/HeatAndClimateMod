package defeatedcrow.hac.food.gui;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.client.base.GuiBaseDC;
import defeatedcrow.hac.food.block.TileAgingBarrel;
import defeatedcrow.hac.main.config.MainCoreConfig;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAgingBarrel extends GuiBaseDC {
	private static final ResourceLocation guiTex = new ResourceLocation("dcs_climate",
			"textures/gui/agingbarrel_gui.png");

	private final InventoryPlayer playerInventory;
	private final TileAgingBarrel processor;

	public GuiAgingBarrel(TileAgingBarrel te, InventoryPlayer playerInv) {
		super(new ContainerAgingBarrel(te, playerInv));
		this.playerInventory = playerInv;
		this.processor = te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format(this.processor.getName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 2, 4210752);

		if (this.isPointInRegion(-20, 4, 20, 20, mouseX, mouseY)) {
			List<String> list = new ArrayList<String>();
			if (processor != null) {
				list.add(I18n.format(processor.climateSuitableMassage()));
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

		if (isPointInRegion(56, 20, 12, 50, x, y)) {
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

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTex());
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (processor.isSuitableClimate()) {
			this.drawTexturedModalRect(i - 20, j + 4, 176, 29, 20, 20);
		} else {
			this.drawTexturedModalRect(i - 20, j + 4, 176, 49, 20, 20);
		}

		if (!processor.inputT.isEmpty()) {
			Fluid in = this.processor.inputT.getFluidType();
			int inAmo = 50 * this.processor.inputT.getFluidAmount() / 5000;
			renderFluid(in, inAmo, i + 56, j + 20, 12, 50);
		}

		if (getAge() > 0) {
			String s = getAge() + " Day";
			int color = getAge() >= MainCoreConfig.aging_day ? 0xFF8080 : 0xFFFFFF;
			this.fontRenderer.drawString(s, i + 125 - this.fontRenderer.getStringWidth(s) / 2, j + 53, color, true);
		}
	}

	private int getAge() {
		int i = this.processor.getField(0);
		return i;
	}

	protected static ResourceLocation guiTex() {
		return guiTex;
	}

}
