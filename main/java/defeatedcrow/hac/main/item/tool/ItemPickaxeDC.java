package defeatedcrow.hac.main.item.tool;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.util.DCToolMaterial;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPickaxeDC extends ItemPickaxe implements ITexturePath {

	private final String tex;
	private boolean isToolsteel;

	public ItemPickaxeDC(ToolMaterial m, String t) {
		super(m);
		if (m == DCToolMaterial.DC_TOOLMETAL) {
			isToolsteel = true;
		}
		tex = t;
	}

	public ItemPickaxeDC setToolsteel() {
		isToolsteel = true;
		return this;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/tool/pickaxe_" + tex;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase living) {
		if (this.toolMaterial == DCToolMaterial.DC_TOOLMETAL && living instanceof EntityPlayer && !world.isRemote) {
			EnumFacing face;
			EntityPlayer player = (EntityPlayer) living;
			RayTraceResult ret = this.rayTrace(world, player, false);
			if (ret != null && ret.typeOfHit == RayTraceResult.Type.BLOCK) {
				face = ret.sideHit;
				List<BlockPos> list = getTargetPos(pos, face);
				for (BlockPos p : list) {
					if (player.canPlayerEdit(p, face, stack)) {
						IBlockState block = player.world.getBlockState(p);
						if (block.getBlock().canHarvestBlock(player.world, p, player) && !block.getBlock()
								.hasTileEntity(block) && block.getBlockHardness(world, p) >= 0) {
							block.getBlock().harvestBlock(world, player, p, block, null, stack);
							world.setBlockToAir(p);
						}
					}
				}
			}
			if (!player.capabilities.isCreativeMode && (player.world
					.getDifficulty() != EnumDifficulty.PEACEFUL || CoreConfigDC.peacefulDam)) {
				player.addExhaustion(0.1F);
			}

		}
		return super.onBlockDestroyed(stack, world, state, pos, living);
	}

	List<BlockPos> getTargetPos(BlockPos pos, EnumFacing face) {
		List<BlockPos> ret = Lists.newArrayList();
		if (face.getAxis() == EnumFacing.Axis.X) {
			for (int z = -1; z <= 1; z++) {
				for (int y = -1; y <= 1; y++) {
					if (z == 0 && y == 0)
						continue;
					BlockPos p = pos.add(0, y, z);
					ret.add(p);
				}
			}
			return ret;
		} else if (face.getAxis() == EnumFacing.Axis.Z) {
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					if (x == 0 && y == 0)
						continue;
					BlockPos p = pos.add(x, y, 0);
					ret.add(p);
				}
			}
			return ret;
		} else {
			for (int z = -1; z <= 1; z++) {
				for (int x = -1; x <= 1; x++) {
					if (z == 0 && x == 0)
						continue;
					BlockPos p = pos.add(x, 0, z);
					ret.add(p);
				}
			}
			return ret;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (isToolsteel) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.toolsteel.tools"));
		}
	}

}
