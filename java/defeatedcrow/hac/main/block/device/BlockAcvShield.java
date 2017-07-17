package defeatedcrow.hac.main.block.device;

import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.main.achievement.AcvHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BlockAcvShield extends DCTileBlock {

	public BlockAcvShield(Material m, String s, int max) {
		super(m, s, max);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileAcvShield();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile != null && tile instanceof TileAcvShield) {
				TileAcvShield shield = (TileAcvShield) tile;
				String owner = shield.getOwnerName();
				String playerName = player.getDisplayNameString();
				int type = DCState.getInt(state, DCState.TYPE4);
				if (owner != null) {
					if (owner.equals(playerName) || player.capabilities.isCreativeMode) {
						if (type == 0) {
							AcvHelper.forceAllClimate(player);
						}
						if (type == 1) {
							AcvHelper.forceAllMachine(player);
						}
						if (type == 2) {
							AcvHelper.forceAllMagic(player);
						}
					}
					player.addChatMessage(new TextComponentString("Owner: " + owner));
				} else {
					player.addChatMessage(new TextComponentString("Owner: NO DATA"));
				}
				return true;
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);
		int i = this.damageDropped(state);
		ItemStack drop = new ItemStack(this, 1, i);

		if (tile != null && tile instanceof TileAcvShield) {
			TileAcvShield shield = (TileAcvShield) tile;
			NBTTagCompound tag = new NBTTagCompound();
			if (shield.getOwnerName() != null) {
				tag.setString("owner", shield.getOwnerName());
			}
			drop.setTagCompound(tag);
		}

		if (!world.isRemote) {
			EntityItem entityitem = new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
					drop);
			float f3 = 0.05F;
			entityitem.motionX = (float) this.rand.nextGaussian() * f3;
			entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.25F;
			entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
			world.spawnEntityInWorld(entityitem);
		}
		world.updateComparatorOutputLevel(pos, state.getBlock());
		world.removeTileEntity(pos);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && tile instanceof TileAcvShield) {
			TileAcvShield shield = (TileAcvShield) tile;
			NBTTagCompound tag = stack.getTagCompound();
			if (tag != null) {
				DCLogger.debugLog("tag");
				DCLogger.debugLog("name: " + tag.getString("owner"));
				shield.setOwner(tag.getString("owner"));
			} else if (placer != null && placer instanceof EntityPlayer) {
				shield.setOwner(((EntityPlayer) placer).getDisplayNameString());
			}
		}
	}

}
