package defeatedcrow.hac.main.block.build;

import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.BlockContainerDC;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider.WorldSleepResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBedDC extends BlockContainerDC {

	protected static final AxisAlignedBB AABB_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

	public BlockBedDC(String s) {
		super(Material.CLOTH, s);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT)
				.withProperty(BlockBed.OCCUPIED, Boolean.valueOf(false)));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBedDC();
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
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_HALF;
	}

	/* --- bed --- */

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			if (state.getValue(BlockBed.PART) != BlockBed.EnumPartType.HEAD) {
				pos = pos.offset(state.getValue(BlockBed.FACING));
				state = world.getBlockState(pos);

				if (state.getBlock() != this) {
					return true;
				}
			}

			// tile
			TileBedDC bed = null;
			TileBedDC bed2 = null;
			TileEntity t1;
			TileEntity t2;
			EnumFacing face = state.getValue(BlockBed.FACING);
			if (state.getValue(BlockBed.PART) == BlockBed.EnumPartType.HEAD) {
				t1 = world.getTileEntity(pos);
				t2 = world.getTileEntity(pos.offset(face.getOpposite()));
			} else {
				t1 = world.getTileEntity(pos.offset(face));
				t2 = world.getTileEntity(pos);
			}
			if (t1 instanceof TileBedDC) {
				bed = (TileBedDC) t1;
			} else {
				return true;
			}
			if (t2 instanceof TileBedDC) {
				bed2 = (TileBedDC) t2;
			} else {
				return true;
			}

			// HELLかどうか
			WorldSleepResult sleepResult = world.provider.canSleepAt(player, pos);
			if (sleepResult != WorldSleepResult.BED_EXPLODES && sleepResult != WorldSleepResult.DENY) {
				// 使用中
				if (state.getValue(BlockBed.OCCUPIED).booleanValue()) {
					EntityPlayer otherplayer = this.getPlayerInBed(world, pos);
					if (otherplayer != null) {
						player.sendStatusMessage(new TextComponentTranslation("tile.bed.occupied",
								new Object[0]), true);
						return true;
					}

					state = state.withProperty(BlockBed.OCCUPIED, Boolean.valueOf(false));
					world.setBlockState(pos, state, 4);
				}

				// Player側のSleep判定
				BlockPos pos2 = new BlockPos(player.getPosition());
				bed.setSleepPos(pos2);
				bed2.setSleepPos(pos2);
				// DCLogger.debugInfoLog("Sleep Pos: " + pos2.toString());

				EntityPlayer.SleepResult result = player.trySleep(pos);

				if (result == EntityPlayer.SleepResult.OK) {
					state = state.withProperty(BlockBed.OCCUPIED, Boolean.valueOf(true));
					world.setBlockState(pos, state, 4);
					return true;
				} else {
					if (result == EntityPlayer.SleepResult.NOT_POSSIBLE_NOW) {
						player.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]), true);
					} else if (result == EntityPlayer.SleepResult.NOT_SAFE) {
						player.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]), true);
					} else if (result == EntityPlayer.SleepResult.TOO_FAR_AWAY) {
						player.sendStatusMessage(new TextComponentTranslation("tile.bed.tooFarAway",
								new Object[0]), true);
					}

					return true;
				}
			} else {
				// 爆発はしない
				player.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]), true);
				return true;
			}
		}
	}

	@Override
	public boolean isBed(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable Entity player) {
		return true;
	}

	@Override
	@Nullable
	public BlockPos getBedSpawnPosition(IBlockState state, IBlockAccess world, BlockPos pos,
			@Nullable EntityPlayer player) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileBedDC) {
			BlockPos ret = ((TileBedDC) tile).getSleepPos();
			// DCLogger.debugInfoLog("Result pos: " + ret.toString());
			if (!ret.equals(BlockPos.ORIGIN)) {
				return ret.up();
			}
		}
		if (world instanceof World) {
			return BlockBed.getSafeExitLocation((World) world, pos, 0);
		}
		return null;
	}

	@Nullable
	private EntityPlayer getPlayerInBed(World worldIn, BlockPos pos) {
		for (EntityPlayer p : worldIn.playerEntities) {
			if (p.isPlayerSleeping() && p.bedLocation.equals(pos)) {
				return p;
			}
		}
		return null;
	}

	// ぼよんぼよんするやつ
	@Override
	public void onLanded(World world, Entity entity) {
		if (entity.isSneaking()) {
			super.onLanded(world, entity);
		} else if (entity.motionY < 0.0D) {
			entity.motionY = -entity.motionY * 0.65D;

			if (!(entity instanceof EntityLivingBase)) {
				entity.motionY *= 0.8D;
			}
		}
	}

	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float distance) {
		super.onFallenUpon(world, pos, entity, distance * 0.25F);
	}

	/* drop */

	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, @Nullable BlockPos from) {
		EnumFacing enumfacing = state.getValue(BlockBed.FACING);

		if (state.getValue(BlockBed.PART) == BlockBed.EnumPartType.FOOT) {
			if (world.getBlockState(pos.offset(enumfacing)).getBlock() != this) {
				world.setBlockToAir(pos);
			}
		} else if (world.getBlockState(pos.offset(enumfacing.getOpposite())).getBlock() != this) {
			if (!world.isRemote) {
				this.dropBlockAsItem(world, pos, state, 0);
			}
			world.setBlockToAir(pos);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(BlockBed.PART) == BlockBed.EnumPartType.FOOT ? Items.AIR : MainInit.itemBed;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, 0);
	}

	@Override
	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.BLOCK;
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (player.capabilities.isCreativeMode && state.getValue(BlockBed.PART) == BlockBed.EnumPartType.FOOT) {
			BlockPos blockpos = pos.offset(state.getValue(BlockBed.FACING));

			if (worldIn.getBlockState(blockpos).getBlock() == this) {
				worldIn.setBlockToAir(blockpos);
			}
		}
	}

	/* state */

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		if ((meta & 8) > 0) {
			return this.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD)
					.withProperty(BlockBed.FACING, facing).withProperty(BlockBed.OCCUPIED, Boolean
							.valueOf((meta & 4) > 0));
		} else {
			return this.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT)
					.withProperty(BlockBed.FACING, facing);
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | state.getValue(BlockBed.FACING).getHorizontalIndex();

		if (state.getValue(BlockBed.PART) == BlockBed.EnumPartType.HEAD) {
			i |= 8;
			if (state.getValue(BlockBed.OCCUPIED).booleanValue()) {
				i |= 4;
			}
		}
		return i;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if (state.getValue(BlockBed.PART) == BlockBed.EnumPartType.FOOT) {
			IBlockState s2 = worldIn.getBlockState(pos.offset(state.getValue(BlockBed.FACING)));

			if (s2.getBlock() == this) {
				state = state.withProperty(BlockBed.OCCUPIED, s2.getValue(BlockBed.OCCUPIED));
			}
		}

		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { BlockBed.FACING, BlockBed.PART, BlockBed.OCCUPIED });
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(BlockBed.FACING, rot.rotate(state.getValue(BlockBed.FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation(state.getValue(BlockBed.FACING)));
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	// 接してる面側が水だったら、その接してる水の側面を描画しない
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

}
