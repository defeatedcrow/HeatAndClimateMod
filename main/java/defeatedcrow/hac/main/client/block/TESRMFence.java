package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.main.block.build.BlockMetalFenceBase;
import defeatedcrow.hac.main.block.build.TileMFence;
import defeatedcrow.hac.main.client.model.ModelMFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TESRMFence extends TileEntitySpecialRenderer<TileMFence> {

	private static final String TEX = "dcs_climate:textures/tiles/mfence_aluminum.png";
	private final ModelMFence model = new ModelMFence();

	@Override
	public void render(TileMFence te, double x, double y, double z, float partialTicks, int destroyStage, float a) {
		if (te != null && te.getWorld() != null) {
			IBlockState block = te.getWorld().getBlockState(te.getPos());
			if (block.getBlock() instanceof BlockMetalFenceBase) {
				IBlockState state = block.getActualState(te.getWorld(), te.getPos());

				this.bindTexture(new ResourceLocation(TEX));

				boolean under = DCState.getBool(state, BlockMetalFenceBase.UNDER);
				boolean upper = DCState.getBool(state, BlockMetalFenceBase.UPPER);
				boolean b_under = DCState.getBool(state, BlockMetalFenceBase.BACK_UNDER);
				boolean b_upper = DCState.getBool(state, BlockMetalFenceBase.BACK_UPPER);

				EnumFacing face = DCState.getFace(state, DCState.FACING);
				EnumFacing b_face = DCState.getFace(state, BlockMetalFenceBase.BACK_FACING);

				float f = 0.0F;
				if (face == EnumFacing.EAST) {
					f = -90F;
				}
				if (face == EnumFacing.WEST) {
					f = 90F;
				}
				if (face == EnumFacing.SOUTH) {
					f = 0F;
				}
				if (face == EnumFacing.NORTH) {
					f = 180F;
				}

				GlStateManager.pushMatrix();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.translate((float) x + 0.5F, (float) y + 0F, (float) z + 0.5F);
				GlStateManager.scale(1.0F, -1.0F, -1.0F);
				GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);

				model.render(0.0F, 0.0F, 0.0F);
				if (upper) {
					if (under) {
						model.renderMiddle();
					} else {
						model.renderUpper();
					}
				} else {
					if (under) {
						model.renderUnder();
					} else {
						model.renderNormal();
					}
				}

				GlStateManager.popMatrix();

				if (face != b_face && face != b_face.getOpposite()) {

					float bf = 0.0F;
					if (b_face == EnumFacing.EAST) {
						bf = -90F;
					}
					if (b_face == EnumFacing.WEST) {
						bf = 90F;
					}
					if (b_face == EnumFacing.SOUTH) {
						bf = 0F;
					}
					if (b_face == EnumFacing.NORTH) {
						bf = 180F;
					}

					GlStateManager.pushMatrix();
					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
					GlStateManager.translate((float) x + 0.5F, (float) y + 0F, (float) z + 0.5F);
					GlStateManager.scale(1.0F, -1.0F, -1.0F);
					GlStateManager.rotate(bf, 0.0F, 1.0F, 0.0F);

					model.render(0.0F, 0.0F, 0.0F);
					if (b_upper) {
						if (b_under) {
							model.renderMiddle();
						} else {
							model.renderUpper();
						}
					} else {
						if (b_under) {
							model.renderUnder();
						} else {
							model.renderNormal();
						}
					}

					GlStateManager.popMatrix();

				}
			}
		}
	}

}
