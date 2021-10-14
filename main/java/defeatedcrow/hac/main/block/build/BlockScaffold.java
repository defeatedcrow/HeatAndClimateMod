package defeatedcrow.hac.main.block.build;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockScaffold extends DCSimpleBlock implements ITexturePath, IRapidCollectables {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.01D, 0.0D, 0.01D, 0.99D, 1.0D, 0.99D);

	public BlockScaffold(Material m, String s) {
		super(m, s, 0, false);
		this.setTickRandomly(false);
		this.setHardness(0.1F);
		this.setResistance(5.0F);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	private static String[] names = {
			"wood"
	};

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta > getMaxMeta()) {
			meta = getMaxMeta();
		}
		String s = "blocks/build/build_plank";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.scaffold"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Press shift key: Tooltip expands ===");
		}
	}

	/* 機能部分 */

	@Override
	public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
		return true;
	}

	// 伸張
	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null && !world.isRemote) {
			ItemStack held = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(held) && held.getItem() instanceof ItemBlock) {

				ItemBlock tI = (ItemBlock) held.getItem();
				Block tB = tI.getBlock();

				if (tB == this || tB instanceof BlockContainer)
					return false;

				// 設置State
				IBlockState tS = tB.getStateForPlacement(world, pos, side, hitX, hitY, hitZ, held
						.getItemDamage(), player, hand);

				if (tS == null)
					return true;

				Set<BlockPos> set = new LinkedHashSet<>();
				set = MainUtil.getLumberTargetList(world, pos, this, 100);

				int count = 0;
				for (BlockPos p2 : set) {
					world.setBlockState(p2, tS);
					count++;
					if (count >= held.getCount())
						break;
				}

				if (count > 0) {
					DCUtil.reduceStackSize(held, count);
					ItemStack drop = new ItemStack(this, count);
					EntityItem dropE = new EntityItem(world, player.posX, player.posY + 0.5D, player.posZ, drop);
					world.spawnEntity(dropE);
				}

				return true;
			}
		}
		return true;
	}

	private double dist(Vec3i v1, Vec3i v2) {
		double d0 = (double) v1.getX() - (double) v2.getX();
		double d1 = (double) v1.getY() - (double) v2.getY();
		double d2 = (double) v1.getZ() - (double) v2.getZ();
		return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
	}

	/* IRapidCollectables */

	@Override
	public String getCollectableTool() {
		return "axe";
	}

	@Override
	public boolean isCollectable(ItemStack item) {
		return !DCUtil.isEmpty(item) && item.getItem() instanceof ItemAxe;
	}

	@Override
	public int getCollectArea(ItemStack item) {
		return 3;
	}

	@Override
	public boolean doCollect(World world, BlockPos pos, IBlockState state, EntityPlayer player, ItemStack tool) {
		NonNullList<ItemStack> list = NonNullList.create();
		this.getDrops(list, world, pos, state, 0);
		for (ItemStack item : list) {
			double x = player.posX;
			double y = player.posY + 0.25D;
			double z = player.posZ;
			EntityItem drop = new EntityItem(world, x, y, z, item);
			world.spawnEntity(drop);
		}
		world.setBlockToAir(pos);
		return true;
	}
}
