package defeatedcrow.hac.machine.block.tankyard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCTileBlockFaced;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.core.fluid.DCFluidUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.MachineInit;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockYardPart extends DCTileBlockFaced {
	public BlockYardPart(String s) {
		super(Material.CLAY, s, 3);
		this.setResistance(120.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileYardPart();
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (DCState.getInt(state, DCState.TYPE4) > 1)
			return false;
		else
			return true;
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (hand == EnumHand.MAIN_HAND) {
				TileEntity tile = world.getTileEntity(pos);
				if (!world.isRemote && tile instanceof TileYardPart) {
					TileYardPart part = (TileYardPart) tile;
					if (heldItem.getItem() instanceof ItemBlock) {
						return false;
					} else if (heldItem.getItem() instanceof IWrenchDC) {
						BlockPos core = getTankCore(world, pos, 128);
						if (core != null) {
							TileEntity tile2 = world.getTileEntity(core);
							if (tile2 instanceof TileTankYard) {
								((TileTankYard) tile2).setRequest(true);
							}
						}
					} else if (part.parent != null && world.isBlockLoaded(part.parent)) {
						TileEntity tile2 = world.getTileEntity(part.parent);
						if (tile2 instanceof TileTankYard) {
							TileTankYard parent = (TileTankYard) tile2;
							if (!DCUtil.isEmpty(heldItem)) {
								if (DCFluidUtil.onActivateDCTank(parent, heldItem, world, state, side, player)) {
									world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
								}
							} else {
								FluidStack f = parent.getTank().getFluid();
								int lim = parent.getLimit();
								if (f != null) {
									String name = f.getLocalizedName();
									int i = f.amount;
									String mes1 = "Stored Fluid: " + name + " " + i + "/" + lim + "mB";
									player.sendMessage(new TextComponentString(mes1));
								} else {
									String mes1 = "Stored Fluid: EMPTY 0/" + lim + "mB";
									player.sendMessage(new TextComponentString(mes1));
								}
							}
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	public BlockPos getTankCore(World world, BlockPos pos, int limit) {
		while (!world.isOutsideBuildHeight(pos.down())) {
			if (world.getBlockState(pos.down()).getBlock() == MachineInit.tankYard) {
				return pos.down();
			} else if (world.getBlockState(pos.down()).getBlock() == MachineInit.tankYardPart) {
				pos = pos.down();
			} else {
				break;
			}
		}
		List<BlockPos> nextTargets = new ArrayList<>();
		BlockPos core = null;
		nextTargets.add(pos);
		Set<BlockPos> founds = new LinkedHashSet<>();
		while (founds.size() <= limit && core == null) {
			List<BlockPos> facing = new ArrayList<>();
			Iterator<BlockPos> itr = nextTargets.iterator();
			while (itr.hasNext()) {
				BlockPos b1 = itr.next();
				for (EnumFacing f : EnumFacing.HORIZONTALS) {
					BlockPos b = b1.offset(f);
					if (world.isBlockLoaded(b)) {
						if (world.getBlockState(b).getBlock() == MachineInit.tankYard) {
							core = b;
						} else if (world.getBlockState(b).getBlock() == MachineInit.tankYardPart && founds.add(b)) {
							facing.add(b);
						}
					}
				}
				itr.remove();
			}
			nextTargets.addAll(facing);
			facing.clear();
			if (nextTargets.isEmpty())
				break;
		}
		return core;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);
		if (!world.isRemote && tile instanceof TileYardPart) {
			if (((TileYardPart) tile).parent != null) {
				TileEntity tile2 = world.getTileEntity(((TileYardPart) tile).parent);
				if (tile2 instanceof TileTankYard) {
					TileTankYard parent = (TileTankYard) tile2;
					if (parent.multi) {
						parent.multi = false;
						parent.setRequest(false);
					}
				}
			}
		}
		world.removeTileEntity(pos);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		// block側の気候処理は無し
	}

	@Override
	public boolean onClimateChange(World world, BlockPos pos, IBlockState state, IClimate clm) {
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public IProperty[] ignoreTarget() {
		return null;
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.CUSTOM;
	}

	@Override
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = state.getBlock().getMetaFromState(state);
		return meta > 1 ? 0 : 255;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		BlockPos check = pos.offset(side);
		IBlockState state2 = world.getBlockState(check);
		if (state2 != null && state2.getBlock() == this) {
			if (DCState.getInt(state, DCState.TYPE4) == DCState.getInt(state2, DCState.TYPE4))
				return false;
		}
		return super.shouldSideBeRendered(state, world, pos, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.tankyard1"));
			tooltip.add(I18n.format("dcs.tip.tankyard2"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Press shift key: Tooltip expands ===");
		}
	}
}
