package defeatedcrow.hac.main.client.block;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.block.build.BlockDisplayShopCase;
import defeatedcrow.hac.main.block.build.TileDisplayShopCase;
import defeatedcrow.hac.main.client.model.ModelDisplayCase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TESRDisplayShopCase extends TileEntitySpecialRenderer<TileDisplayShopCase> {

	private static final String TEX = "dcs_climate:textures/tiles/showcase_steel.png";
	private final ModelDisplayCase model = new ModelDisplayCase();

	@Override
	public void render(TileDisplayShopCase te, double x, double y, double z, float partialTicks, int destroyStage,
			float a) {
		if (te != null) {

			int type = 0;
			int face = 0;
			float f = 0.0F;

			boolean sideA = false;
			boolean sideB = false;

			if (te.hasWorld()) {
				int meta = te.getBlockMetadata();
				IBlockState block = te.getWorld().getBlockState(te.getPos());
				if (block.getBlock() instanceof BlockDisplayShopCase) {
					IBlockState state = block.getActualState(te.getWorld(), te.getPos());
					sideA = getSideA(state);
					sideB = getSideB(state);
				}

				type = meta & 3;
				face = 5 - (meta >> 2);
				if (face == 2) {
					f = 180F;
				}
				if (face == 3) {
					f = 0F;
				}
				if (face == 4) {
					f = 90F;
				}
				if (face == 5) {
					f = -90F;
				}
			}

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);

			this.bindTexture(new ResourceLocation(TEX));

			model.render(0.0F, 0.0F, 0.0F);
			if (!sideA) {
				model.renderA();
			}
			if (!sideB) {
				model.renderB();
			}

			if (!DCUtil.isEmpty(te.getStackInSlot(0))) {
				renderItem(te.getStackInSlot(0).copy(), -0.25F, 0.2F, -0.25F, te);
				int count = te.getStackInSlot(0).copy().getCount();
				if (count > 1) {
					renderItem(te.getStackInSlot(0).copy(), -0.25F, 0.2F, 0F, te);
				}
				if (count > 2) {
					renderItem(te.getStackInSlot(0).copy(), -0.25F, 0.2F, 0.25F, te);
				}
			}

			if (!DCUtil.isEmpty(te.getStackInSlot(1))) {
				renderItem(te.getStackInSlot(1).copy(), 0.25F, 0.2F, -0.25F, te);
				int count = te.getStackInSlot(1).copy().getCount();
				if (count > 1) {
					renderItem(te.getStackInSlot(1).copy(), 0.25F, 0.2F, 0F, te);
				}
				if (count > 2) {
					renderItem(te.getStackInSlot(1).copy(), 0.25F, 0.2F, 0.25F, te);
				}
			}

			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();

		}
	}

	protected void renderItem(ItemStack stack, float x, float y, float z, TileEntity te) {
		RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();

		if (!DCUtil.isEmpty(stack)) {
			stack.setCount(1);
			EntityItem drop = new EntityItem(te.getWorld(), 0.0D, 0.0D, 0.0D, stack);
			Item item = stack.getItem();

			drop.hoverStart = 0.0F;
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			if (item instanceof ItemBlock) {
				GlStateManager.translate(x, y, z);
			} else {
				GlStateManager.translate(x, y - 0.0675F, z);
			}
			GlStateManager.scale(0.4F, 0.4F, 0.4F);

			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(15.0F, 1.0F, 0.0F, 0.0F);

			GlStateManager.pushAttrib();
			RenderHelper.enableStandardItemLighting();
			itemRenderer.renderItem(drop.getItem(), ItemCameraTransforms.TransformType.FIXED);
			RenderHelper.disableStandardItemLighting();
			GlStateManager.popAttrib();

			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}

	private boolean getSideA(IBlockState state) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		switch (face) {
		case EAST:
			return DCState.getBool(state, BlockDisplayShopCase.SOUTH);
		case NORTH:
			return DCState.getBool(state, BlockDisplayShopCase.EAST);
		case SOUTH:
			return DCState.getBool(state, BlockDisplayShopCase.WEST);
		case WEST:
			return DCState.getBool(state, BlockDisplayShopCase.NORTH);
		default:
			return false;
		}
	}

	private boolean getSideB(IBlockState state) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		switch (face) {
		case EAST:
			return DCState.getBool(state, BlockDisplayShopCase.NORTH);
		case NORTH:
			return DCState.getBool(state, BlockDisplayShopCase.WEST);
		case SOUTH:
			return DCState.getBool(state, BlockDisplayShopCase.EAST);
		case WEST:
			return DCState.getBool(state, BlockDisplayShopCase.SOUTH);
		default:
			return false;
		}
	}
}
