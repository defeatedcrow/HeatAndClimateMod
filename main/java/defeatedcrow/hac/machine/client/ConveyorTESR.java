package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.block.TileConveyor;
import defeatedcrow.hac.machine.client.model.ModelConveyor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ConveyorTESR extends DCTorqueTESRBase {

	@Override
	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/convayor_steel.png";
	}

	private static final DCTileModelBase MODEL = new ModelConveyor();

	@Override
	protected DCTileModelBase getModel(TileTorqueBase te) {
		return MODEL;
	}

	@Override
	public void render(TileTorqueBase te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		if (te == null) {
			return;
		}
		ItemStack in = ItemStack.EMPTY;
		float move = 0F;
		int fF = 0;
		EnumFacing face = EnumFacing.SOUTH;

		if (te != null && te instanceof TileConveyor) {
			TileConveyor tile = (TileConveyor) te;
			in = tile.disp;
			fF = tile.move;
			face = tile.getBaseSide();
		}
		move = fF;

		float rot = 0F;
		if (face == EnumFacing.SOUTH) {
			rot = -90F;
		} else if (face == EnumFacing.NORTH) {
			rot = 90F;
		} else if (face == EnumFacing.EAST) {
			rot = 180F;
		}

		if (!DCUtil.isEmpty(in)) {

			float off = move * 0.0625F;

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(rot, 0F, 1F, 0F);
			GlStateManager.rotate(180F, 0F, 0F, 1F);
			GlStateManager.translate(-0.5F + off, -0.05F, 0F);
			GlStateManager.scale(0.75F, 0.75F, 0.75F);

			Minecraft.getMinecraft().getRenderItem().renderItem(in, TransformType.GROUND);

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
		}

	}

}
