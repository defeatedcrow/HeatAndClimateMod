package defeatedcrow.hac.food.block.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.cultivate.GrowingStage;
import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.api.placeable.ISidedTexture;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.base.INameSuffix;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSeaweed extends BlockDC implements ISidedTexture, INameSuffix, IClimateCrop, IGrowable,
		IRapidCollectables {

	protected Random cropRand = new Random();

	public BlockSeaweed(String s, int max) {
		super(Material.WATER, s);
		this.setSoundType(SoundType.PLANT);
		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.STAGE4, 0)
				.withProperty(BlockFluidBase.LEVEL, Integer.valueOf(0)));
	}

	public boolean isInWater(IBlockAccess world, BlockPos pos) {
		boolean ret = true;
		if (world.getBlockState(pos.up()).getMaterial() != Material.WATER) {
			return false;
		}
		for (EnumFacing face : EnumFacing.HORIZONTALS) {
			IBlockState target = world.getBlockState(pos.offset(face));
			if (target.getMaterial() != Material.WATER && !target.getBlock().isSideSolid(target, world, pos
					.offset(face), face.getOpposite())) {
				ret = false;
			}
		}
		return ret;
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
	public int tickRate(World world) {
		return 80;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = super.getSubItemList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* Block動作 */

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			IBlockState crop = world.getBlockState(pos);
			ItemStack held = player.inventory.getCurrentItem();
			if (!DCUtil.isEmpty(held) && held.getItem() == Items.DYE && held.getItemDamage() == 15) {
				ItemDye.applyBonemeal(held, world, pos, player, hand);
				return true;
			} else if (this.harvest(world, pos, crop, player)) {
				player.playSound(SoundEvents.BLOCK_GRASS_PLACE, 1.0F, 1.0F);
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (state.getBlock() == this && checkAndDropBlock(world, pos, state)) {
			IClimate clm = this.getClimate(world, pos, state);
			int chance = this.isSuitableClimate(clm, state) ? 8 : 30;
			if (rand.nextInt(chance) == 0) {
				this.grow(world, pos, state);
			}
		}
	}

	protected IClimate getClimate(World world, BlockPos pos, IBlockState state) {
		DCHeatTier heat = ClimateAPI.calculator.getAverageTemp(world, pos, checkingRange()[0], false);
		DCHumidity hum = ClimateAPI.calculator.getHumidity(world, pos.down(), checkingRange()[1], false);
		DCAirflow air = ClimateAPI.calculator.getAirflow(world, pos, checkingRange()[2], false);
		IClimate c = ClimateAPI.register.getClimateFromParam(heat, hum, air);
		return c;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new ArrayList<ItemStack>();
		ret.addAll(this.getCropItems(state, fortune));
		return ret;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return this.isSuitablePlace(worldIn, pos, worldIn.getBlockState(pos));
	}

	protected boolean checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (this.canPlaceBlockAt(worldIn, pos)) {
			return true;
		} else {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.WATER.getDefaultState(), 3);
			worldIn.neighborChanged(pos.up(), Blocks.WATER, pos);
			return false;
		}
	}

	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		this.checkAndDropBlock(world, pos, state);
	}

	protected float getSeedDropChance() {
		return 1.0F;
	}

	/* IClimateCrop */

	@Override
	public ItemStack getSeedItem(IBlockState thisState) {
		ItemStack item = new ItemStack(FoodInit.seeds, 1, 8);
		return item;
	}

	@Override
	public List<ItemStack> getCropItems(IBlockState thisState, int fortune) {
		List<ItemStack> ret = Lists.newArrayList();
		ret.add(new ItemStack(FoodInit.seeds, 1, 8));
		return ret;
	}

	@Override
	public IBlockState getGrownState() {
		return this.getDefaultState().withProperty(DCState.STAGE4, 2);
	}

	@Override
	public IBlockState setGroundState(IBlockState state) {
		return state.withProperty(DCState.STAGE4, 0);
	}

	@Override
	public boolean isSuitableClimate(IClimate climate, IBlockState thisState) {
		if (climate == null || thisState == null || thisState.getBlock() != this)
			return false;
		boolean temp = this.getSuitableTemp(thisState).contains(climate.getHeat());
		boolean hum = this.getSuitableHum(thisState).contains(climate.getHumidity());
		boolean air = this.getSuitableAir(thisState).contains(climate.getAirflow());
		return temp && hum && air;
	}

	protected boolean isSoil(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		boolean soil = state.isSideSolid(world, pos, EnumFacing.UP) || state.getBlock() == this;
		return soil;
	}

	@Override
	public boolean isSuitablePlace(World world, BlockPos pos, IBlockState targetState) {
		boolean water = this.isInWater(world, pos);
		boolean soil = isSoil(world, pos.down());
		return water && soil;
	}

	@Override
	public GrowingStage getCurrentStage(IBlockState thisState) {
		if (thisState == null)
			return GrowingStage.DEAD;
		else {
			int i = DCState.getInt(thisState, DCState.STAGE4);
			if (i == 0)
				return GrowingStage.GROUND;
			else
				return GrowingStage.GROWN;
		}
	}

	@Override
	public boolean grow(World world, BlockPos pos, IBlockState state) {
		if (state != null && state.getBlock() == this) {
			int l = 1;
			for (int i = 1; pos.getY() - i < 1; i++) {
				BlockPos check = pos.up(i);
				if (world.getBlockState(check).getBlock() != this) {
					l = i - 1;
					break;
				}
			}
			if (l > 63) {
				return false;
			}
			boolean water = this.isInWater(world, pos.up());
			boolean gl = world.getBlockState(pos.up()).getBlock() == Blocks.WATER;
			if (gl && water) {
				IBlockState newstate = state.withProperty(DCState.STAGE4, 3);
				world.setBlockState(pos.up(), newstate, 2);
				world.neighborChanged(pos.down(), Blocks.WATER, pos);
			}
		}
		return false;
	}

	@Override
	public boolean harvest(World world, BlockPos pos, IBlockState thisState, EntityPlayer player) {
		if (thisState != null && thisState.getBlock() == this && !isSoil(world, pos.down())) {

			ItemStack item = new ItemStack(FoodInit.seeds, 1, 8);
			EntityItem drop = new EntityItem(world);
			drop.setItem(item);
			if (player != null) {
				drop.setPosition(player.posX, player.posY + 0.15D, player.posZ);
			} else {
				drop.setPosition(pos.getX(), pos.getY() + 0.5D, pos.getZ());
			}
			if (!world.isRemote)
				world.spawnEntity(drop);
			world.setBlockState(pos, Blocks.WATER.getDefaultState(), 3);
			world.neighborChanged(pos.up(), Blocks.WATER, pos);
			return true;
		}
		return false;
	}

	public boolean canStayOnHarvest() {
		return true;
	}

	@Override
	public int[] checkingRange() {
		return CoreConfigDC.ranges;
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(IBlockState thisState) {
		List<DCHeatTier> ret = new ArrayList<DCHeatTier>();
		ret.add(DCHeatTier.COLD);
		ret.add(DCHeatTier.COOL);
		ret.add(DCHeatTier.NORMAL);
		ret.add(DCHeatTier.WARM);
		return ret;
	}

	@Override
	public List<DCHumidity> getSuitableHum(IBlockState thisState) {
		List<DCHumidity> ret = new ArrayList<DCHumidity>();
		ret.add(DCHumidity.UNDERWATER);
		return ret;
	}

	@Override
	public List<DCAirflow> getSuitableAir(IBlockState thisState) {
		List<DCAirflow> ret = new ArrayList<DCAirflow>();
		ret.add(DCAirflow.TIGHT);
		return ret;
	}

	/* IRapidCollectables */

	@Override
	public String getCollectableTool() {
		return "shears";
	}

	@Override
	public boolean isCollectable(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return false;
		// デフォルトではハサミ
		return item.getItem() instanceof ItemShears || item.getItem() instanceof ItemSword;
	}

	@Override
	public int getCollectArea(ItemStack item) {
		return 3;
	}

	@Override
	public boolean doCollect(World world, BlockPos pos, IBlockState state, EntityPlayer player, ItemStack tool) {
		return this.harvest(world, pos, state, player);
	}

	/* state関連 */

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean up = world.getBlockState(pos.up()).getBlock() == this;
		boolean down = world.getBlockState(pos.down()).getBlock() == this;
		int i = 0;
		if (up) {
			i = down ? 2 : 1;
		} else {
			i = down ? 3 : 0;
		}
		return state.withProperty(DCState.STAGE4, i);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DCState.STAGE4, BlockFluidBase.LEVEL });
	}

	// drop
	@Override
	public int damageDropped(IBlockState state) {
		return this.getMetaFromState(state);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	/* IGrowable */

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		return this.isSuitablePlace(world, pos, state);
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		this.grow(world, pos, state);
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(this, 1, 0);
	}

	/**
	 * 側面描画用
	 * flammpfeil氏より
	 */

	// 接してる面側が水だったら、その接してる水の側面を描画しない
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		if (world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;// super.doesSideBlockRendering(state, world, pos, face);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return true;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 3;
		String b = "dcs_climate:blocks/crop/";
		return b + "seaweed_" + m;
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player,
			boolean willHarvest) {
		this.onBlockHarvested(world, pos, state, player);
		return world.setBlockState(pos, Blocks.WATER.getDefaultState(), world.isRemote ? 11 : 3);
	}

}
