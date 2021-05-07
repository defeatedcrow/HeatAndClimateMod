package defeatedcrow.hac.main.block;

import java.util.List;

import javax.annotation.Nullable;

import com.mojang.authlib.GameProfile;

import defeatedcrow.hac.core.base.DCTileBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockExclusiveDC extends DCTileBlock {

	public BlockExclusiveDC(Material m, String s, int max) {
		super(m, s, max);
		this.setResistance(6000001.0F);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && tile instanceof DCExclusiveTE && placer instanceof EntityPlayer && placer.isSneaking()) {
			DCExclusiveTE exclusive = (DCExclusiveTE) tile;
			EntityPlayer player = (EntityPlayer) placer;
			GameProfile profile = player.getGameProfile();
			exclusive.setOwner(profile.getId());
			exclusive.setOwnerName(player.getDisplayNameString());
		}
		super.onBlockPlacedBy(world, pos, state, placer, stack);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile != null && tile instanceof DCExclusiveTE) {
				DCExclusiveTE exclusive = (DCExclusiveTE) tile;
				if (exclusive.isOwnerOrOP(player)) {
					return onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
				} else {
					return onRightClickByOthers(world, pos, state, player, hand, side, hitX, hitY, hitZ);
				}
			}
		}
		return true;
	}

	public boolean onRightClickByOthers(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		return true;
	}

	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
		tooltip.add(I18n.format("dcs.tip.exclusive"));
	}

}
