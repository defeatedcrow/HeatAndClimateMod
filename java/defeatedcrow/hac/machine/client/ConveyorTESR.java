package defeatedcrow.hac.machine.client;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.core.client.base.DCTorqueTESRBase;
import defeatedcrow.hac.core.energy.TileTorqueBase;
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
	public void renderTileEntityAt(TileTorqueBase te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		if (te == null) {
			return;
		}
		ItemStack in = null;
		ItemStack out = null;
		float moveF = 0F;
		int fF = 0;
		int preF = 0;
		float moveB = 0F;
		int fB = 0;
		int preB = 0;
		EnumFacing face = EnumFacing.SOUTH;

		if (te != null && te instanceof TileConveyor) {
			TileConveyor tile = (TileConveyor) te;
			in = tile.getStackInSlot(0);
			out = tile.getStackInSlot(1);
			fF = tile.moveF;
			preF = tile.prevMoveF;
			fB = tile.moveB;
			preB = tile.prevMoveB;
			face = tile.getBaseSide();
		}
		moveF = fF;
		moveF /= 8.0F;
		moveB = fB;
		moveB /= 8.0F;
		float rot = 0F;
		if (face == EnumFacing.SOUTH || face == EnumFacing.NORTH) {
			rot = 90F;
		}

		if (in != null) {

			float offX = face.getFrontOffsetX() * (moveF - 1.0F) * 0.5F;
			float offZ = face.getFrontOffsetZ() * (moveF - 1.0F) * 0.5F;

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F + offX, (float) y + 0.45F, (float) z + 0.5F + offZ);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(rot, 0F, 1F, 0F);
			GlStateManager.rotate(180F, 0F, 0F, 1F);
			GlStateManager.scale(0.75F, 0.75F, 0.75F);

			Minecraft.getMinecraft().getRenderItem().renderItem(in, TransformType.GROUND);

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
		}

		if (out != null) {

			float offX = face.getFrontOffsetX() * moveB * 0.5F;
			float offZ = face.getFrontOffsetZ() * moveB * 0.5F;

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F + offX, (float) y + 0.45F, (float) z + 0.5F + offZ);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(rot, 0F, 1F, 0F);
			GlStateManager.rotate(180F, 0F, 0F, 1F);
			GlStateManager.scale(0.75F, 0.75F, 0.75F);

			Minecraft.getMinecraft().getRenderItem().renderItem(out, TransformType.GROUND);

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
		}

	}

}
