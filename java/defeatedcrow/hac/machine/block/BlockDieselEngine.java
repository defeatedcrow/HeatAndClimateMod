package defeatedcrow.hac.machine.block;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.base.ITagGetter;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.fluid.DCFluidUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDieselEngine extends BlockTorqueBase {

	public BlockDieselEngine(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileDieselEngine();
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			TileEntity tile = world.getTileEntity(pos);
			ItemStack held = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(held)) {
				boolean flag = false;

				if (held.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side)) {
					IFluidHandler cont = held.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
					if (cont != null && cont.drain(1000, false) != null) {
						FluidStack f = cont.drain(1000, false);
						if (MainAPIManager.fuelRegister.isRegistered(f.getFluid())) {
							if (!world.isRemote
									&& DCFluidUtil.onActivateDCTank(tile, held, world, state, side, player)) {
								world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F,
										2.0F);
							}
							return true;
						}
					}
				} else if (held.getItem() instanceof IWrenchDC)
					return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
				else {
					if (!world.isRemote) {
						player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
					}
					return true;
				}
			} else {
				if (!world.isRemote) {
					player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
				}
				return true;
			}
		}
		return true;
	}

	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		if (!world.isRemote) {
			boolean flag = world.isBlockPowered(pos);

			if (flag || block.getDefaultState().canProvidePower()) {
				if (flag != state.getValue(DCState.POWERED).booleanValue()) {
					world.setBlockState(pos, state.withProperty(DCState.POWERED, Boolean.valueOf(flag)), 2);
					float f = state.getValue(DCState.POWERED).booleanValue() ? 0.6F : 0.5F;
					world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F,
							f);
				}
			}
		}
	}

	// 設置時にはプレイヤーの方を向いている方が自然なので
	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		// achievement
		if (placer != null && !placer.worldObj.isRemote && placer instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) placer;
		}
		if (placer != null) {
			EnumFacing face = placer.getHorizontalFacing();
			if (placer.rotationPitch < -75.0F) {
				face = EnumFacing.UP;
			} else if (placer.rotationPitch > 75.0F) {
				face = EnumFacing.DOWN;
			}
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(face.getOpposite()));
		} else {
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing.getOpposite()));
		}
		return state;
	}

	// drop
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && tile instanceof ITagGetter) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag != null) {
				((ITagGetter) tile).setNBT(tag);
			}
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);
		int i = this.damageDropped(state);
		ItemStack drop = new ItemStack(this, 1, i);

		if (tile != null && tile instanceof ITagGetter) {
			NBTTagCompound tag = new NBTTagCompound();
			tag = ((ITagGetter) tile).getNBT(tag);
			if (tag != null) {
				drop.setTagCompound(tag);
			}
		}

		if (!world.isRemote) {
			EntityItem entityitem = new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
					drop);
			float f3 = 0.05F;
			entityitem.motionX = (float) world.rand.nextGaussian() * f3;
			entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 0.25F;
			entityitem.motionZ = (float) world.rand.nextGaussian() * f3;
			world.spawnEntityInWorld(entityitem);
		}
		world.updateComparatorOutputLevel(pos, state.getBlock());
		super.breakBlock(world, pos, state);

	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && !DCState.getBool(state, DCState.POWERED)) {
			for (int i = 0; i < 5; i++) {
				EnumFacing face = DCState.getSide(state, DCState.SIDE).getFacing();
				double x = pos.getX() + 0.5D - face.getFrontOffsetX() * 0.4D + face.getFrontOffsetZ() * 0.4D;
				double y = pos.getY() + 1.0D;
				double z = pos.getZ() + 0.5D - face.getFrontOffsetZ() * 0.4D + face.getFrontOffsetX() * 0.4D;
				double dx = 0.0D;
				double dy = 0.05D;
				double dz = 0.0D;

				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, dy, 0.0D, new int[0]);
			}
		}
	}

}
