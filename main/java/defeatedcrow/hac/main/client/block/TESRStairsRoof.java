package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.block.build.TileStairsRoof;
import defeatedcrow.hac.main.client.model.ModelStairsRoof;
import defeatedcrow.hac.main.client.model.ModelStairsRoof_Inner;
import defeatedcrow.hac.main.client.model.ModelStairsRoof_Outer;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs.EnumShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class TESRStairsRoof extends TileEntitySpecialRenderer<TileStairsRoof> {

	private static final String TEX_BLACK = "dcs_climate:textures/tiles/slate_roof_black.png";
	private static final String TEX_BROWN = "dcs_climate:textures/tiles/slate_roof_brown.png";
	private static final String TEX_GREEN = "dcs_climate:textures/tiles/slate_roof_green.png";
	private static final String TEX_RED = "dcs_climate:textures/tiles/slate_roof_red.png";
	private final ModelStairsRoof model_st_top = new ModelStairsRoof(true);
	private final ModelStairsRoof model_st = new ModelStairsRoof(false);
	private final ModelStairsRoof_Outer model_outer = new ModelStairsRoof_Outer();
	private final ModelStairsRoof_Inner model_inner_top = new ModelStairsRoof_Inner(true);
	private final ModelStairsRoof_Inner model_inner = new ModelStairsRoof_Inner(false);

	@Override
	public void render(TileStairsRoof te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null && te.hasWorld()) {
			int meta = te.getBlockMetadata();
			IBlockState stair = te.getWorld().getBlockState(te.getPos());
			stair = stair.getActualState(te.getWorld(), te.getPos());
			EnumFacing face = EnumFacing.getFront(5 - (meta & 3));
			boolean top = (meta & 4) > 0;
			float rot = 0F;
			float rev = top ? 180F : 0F;
			EnumShape shape = stair.getValue(BlockStairs.SHAPE);

			switch (face) {
			case NORTH:
				rot = 0F;
				break;
			case SOUTH:
				rot = 180F;
				break;
			case EAST:
				rot = 90F;
				break;
			case WEST:
				rot = -90F;
				break;
			default:
				break;
			}

			if (top && (face == EnumFacing.NORTH || face == EnumFacing.SOUTH)) {
				rot += 180F;
			}

			if (shape == EnumShape.OUTER_RIGHT && !top) {
				rot += 90F;
			}

			if (shape == EnumShape.OUTER_LEFT && top) {
				rot += 90F;
			}

			if (shape == EnumShape.INNER_RIGHT && !top) {
				rot += 90F;
			}

			if (shape == EnumShape.INNER_LEFT && top) {
				rot += 90F;
			}

			BlockPos check = te.getPos().offset(face).up();
			boolean isTop = stair.getBlock() == te.getWorld().getBlockState(check).getBlock();

			this.bindTexture(new ResourceLocation(TEX_BLACK));
			if (stair.getBlock() == MainInit.roofSlateBrown) {
				this.bindTexture(new ResourceLocation(TEX_BROWN));
			} else if (stair.getBlock() == MainInit.roofSlateGreen) {
				this.bindTexture(new ResourceLocation(TEX_GREEN));
			} else if (stair.getBlock() == MainInit.roofSlateRed) {
				this.bindTexture(new ResourceLocation(TEX_RED));
			}

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(rev, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(rot, 0.0F, 1.0F, 0.0F);

			if (shape == EnumShape.STRAIGHT) {
				if (isTop & !top) {
					model_st.render(0.0625F);
				} else {
					model_st_top.render(0.0625F);
				}
			} else if (shape == EnumShape.INNER_LEFT || shape == EnumShape.INNER_RIGHT) {
				if (isTop & !top) {
					model_inner.render(0.0625F);
				} else {
					model_inner_top.render(0.0625F);
				}
			} else if (shape == EnumShape.OUTER_LEFT || shape == EnumShape.OUTER_RIGHT) {
				model_outer.render(0.0625F);
			}

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

		}
	}

}
