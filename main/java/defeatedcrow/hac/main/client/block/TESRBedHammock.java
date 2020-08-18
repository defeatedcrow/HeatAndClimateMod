package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.core.client.base.DCTileModelBase;
import defeatedcrow.hac.main.block.build.TileBedDC;
import defeatedcrow.hac.main.client.model.ModelBedHammock;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRBedHammock extends TileEntitySpecialRenderer<TileBedDC> {

	private static final ModelBedHammock MODEL = new ModelBedHammock();

	@Override
	public void render(TileBedDC te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

		float face = 0.0F;
		if (te.hasWorld()) {
			int meta = te.getBlockMetadata();
			if (BlockBed.isHeadPiece(meta)) {
				boolean head1 = false;
				boolean head2 = false;
				boolean head3 = false;
				boolean bottom1 = false;
				boolean bottom2 = false;
				boolean bottom3 = false;

				EnumFacing facing = EnumFacing.getHorizontal(meta);
				face = facing.getOpposite().getHorizontalAngle();

				if (te.getWorld().isAirBlock(te.getPos().offset(facing))) {
					IBlockState state = te.getWorld().getBlockState(te.getPos().offset(facing, 2));
					if (state.isSideSolid(te.getWorld(), te.getPos().offset(facing, 2), facing.getOpposite())) {
						head1 = true;
						head2 = true;
					} else {
						head1 = true;
						head2 = true;
						head3 = true;
					}
				} else {
					IBlockState state = te.getWorld().getBlockState(te.getPos().offset(facing));
					if (!state.isSideSolid(te.getWorld(), te.getPos().offset(facing), facing.getOpposite())) {
						head1 = true;
					}
				}

				if (te.getWorld().isAirBlock(te.getPos().offset(facing.getOpposite(), 2))) {
					IBlockState state = te.getWorld().getBlockState(te.getPos().offset(facing.getOpposite(), 3));
					if (state.isSideSolid(te.getWorld(), te.getPos().offset(facing.getOpposite(), 3), facing)) {
						bottom1 = true;
						bottom2 = true;
					} else {
						bottom1 = true;
						bottom2 = true;
						bottom3 = true;
					}
				} else {
					IBlockState state = te.getWorld().getBlockState(te.getPos().offset(facing.getOpposite(), 2));
					if (!state.isSideSolid(te.getWorld(), te.getPos().offset(facing.getOpposite(), 2), facing)) {
						bottom1 = true;
					}
				}

				this.bindTexture(new ResourceLocation(getTexPass(0)));

				GlStateManager.pushMatrix();
				GlStateManager.enableRescaleNormal();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
				GlStateManager.scale(1, -1, -1);

				GlStateManager.rotate(face, 0.0F, 1.0F, 0.0F);
				MODEL.render(partialTicks, 0, 0);
				if (head1)
					MODEL.renderLeg1();
				if (head2)
					MODEL.renderLeg2();
				if (head3)
					MODEL.renderLeg3();

				GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
				GlStateManager.translate(0F, 0F, 1F);

				if (bottom1)
					MODEL.renderLeg1();
				if (bottom2)
					MODEL.renderLeg2();
				if (bottom3)
					MODEL.renderLeg3();

				GlStateManager.disableRescaleNormal();
				GlStateManager.popMatrix();
			}

		}
	}

	protected String getTexPass(int i) {
		return "dcs_climate:textures/tiles/bed_hammock.png";
	}

	protected DCTileModelBase getModel(int i) {
		return MODEL;
	}
}
