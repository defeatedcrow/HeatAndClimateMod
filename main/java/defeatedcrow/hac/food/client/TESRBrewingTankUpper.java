package defeatedcrow.hac.food.client;

import defeatedcrow.hac.core.base.DCLockableTE;
import defeatedcrow.hac.core.client.base.DCLockableTESRBase;
import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.food.client.model.ModelBlockBrewing;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRBrewingTankUpper extends DCLockableTESRBase {

	private static final String TEX = "dcs_climate:textures/tiles/brewing_tank_sus.png";
	private static final ModelBlockBrewing MODEL = new ModelBlockBrewing();

	@Override
	public void render(DCLockableTE te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		if (te != null && te.getWorld() != null) {
			TileEntity tile = te.getWorld().getTileEntity(te.getPos().up());

			if (tile != null && tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, EnumFacing.DOWN)) {

				DCTileModelBase model = this.getModel(0);
				this.bindTexture(new ResourceLocation(getTexPass(0)));

				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);

				GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
				MODEL.renderPipe(0.0F);
				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();

			}
		}
	}

	@Override
	protected String getTexPass(int i) {
		return TEX;
	}

	@Override
	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
