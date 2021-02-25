package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCatapult extends BlockTorqueBase {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1D, 0.5D, 1D);

	public BlockCatapult(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
		setHorizontal();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCatapult();
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			if (MainUtil.isHeldWrench(player, hand)) {
				TileEntity tile = world.getTileEntity(pos);

				if (tile != null && tile instanceof TileCatapult) {
					TileCatapult cat = (TileCatapult) tile;
					int r = cat.rad;
					if (player.isSneaking()) {
						r -= 10;
					} else {
						r += 10;
					}
					if (r > 90) {
						r = 0;
					}
					if (r < 0) {
						r = 0;
					}
					cat.rad = r;
					return true;
				}
			}
		}
		return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity != null) {
			// if (entity instanceof EntityPlayer || !world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile != null && tile instanceof TileCatapult) {
				TileCatapult cat = (TileCatapult) tile;
				float pow = cat.pow;
				double rad = cat.rad * Math.PI / 180.0D;
				EnumSide side = state.getValue(DCState.SIDE);
				float mX = pow * (float) Math.cos(rad) * side.getFacing().getFrontOffsetX();
				float mZ = pow * (float) Math.cos(rad) * side.getFacing().getFrontOffsetZ();
				float mY = pow * (float) Math.sin(rad) * 0.5F;

				// entity.setPosition(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
				entity.setRotationYawHead(side.getFacing().getHorizontalAngle());
				entity.motionX = mX;
				entity.motionY = mY;
				entity.motionZ = mZ;
				DCLogger.debugLog("catapult " + pow);
			}
			// }
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(I18n.format("dcs.tip.catapult"));
			tooltip.add(TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.catapult2"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
