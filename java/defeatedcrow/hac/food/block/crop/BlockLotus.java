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
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.base.INameSuffix;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
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

public class BlockLotus extends Block implements INameSuffix, IClimateCrop, IRapidCollectables, IGrowable {

	public static final PropertyBool BLACK = PropertyBool.create("black");

	protected Random cropRand = new Random();

	public BlockLotus(String s, int max) {
		super(Material.WATER);
		this.setUnlocalizedName(s);
		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.STAGE8, 0).withProperty(BLACK, false)
				.withProperty(BlockFluidBase.LEVEL, Integer.valueOf(0)));
	}

	public boolean isInWater(IBlockAccess world, BlockPos pos) {
		boolean ret = true;
		for (EnumFacing face : EnumFacing.HORIZONTALS) {
			IBlockState target = world.getBlockState(pos.offset(face));
			if (target.getBlock() instanceof BlockLiquid && target.getMaterial() == Material.WATER) {

			} else if (target.getBlock() != this
					&& !target.getBlock().isSideSolid(target, world, pos.offset(face), face.getOpposite())) {
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
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(this, 1, 3));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* Block動作 */

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			IBlockState crop = world.getBlockState(pos);
			int stage = state.getValue(DCState.STAGE8);
			ItemStack held2 = player.inventory.getCurrentItem();
			if (held2 != null && held2.getItem() == Items.DYE && held2.getItemDamage() == 15 && held2.stackSize > 1) {
				ItemDye.applyBonemeal(held2, world, pos, player);
				return true;
			} else if (stage > 3) {
				player.playSound(SoundEvents.BLOCK_GRASS_PLACE, 1.0F, 1.0F);
				return this.harvest(world, pos, crop, player);
			}
		}
		return false;
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		super.onBlockClicked(world, pos, player);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (!world.isRemote && state != null && state.getBlock() == this) {
			IClimate clm = this.getClimate(world, pos, state);
			int stage = state.getValue(DCState.STAGE8);
			int chance = this.isSuitableClimate(clm, state) ? 12 : 30;
			if (rand.nextInt(chance) == 0) {
				this.grow(world, pos, state);
			} else {
				this.checkAndDropBlock(world, pos, state);
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
		if (cropRand.nextFloat() <= this.getSeedDropChance()) {
			ret.add(this.getSeedItem(state));
		}
		ret.addAll(this.getCropItems(state, fortune));
		return ret;
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.canBlockStay(worldIn, pos, state)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.WATER.getDefaultState(), 2);
		}
	}

	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		return this.isSuitablePlace(worldIn, pos.down(), worldIn.getBlockState(pos.down()));
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block) {
		world.scheduleBlockUpdate(pos, block, 10, 0);
	}

	protected float getSeedDropChance() {
		return 1.0F;
	}

	/* IClimateCrop */

	@Override
	public ItemStack getSeedItem(IBlockState thisState) {
		ItemStack item = new ItemStack(FoodInit.seeds, 1, 6);
		return item;
	}

	@Override
	public List<ItemStack> getCropItems(IBlockState thisState, int fortune) {
		List<ItemStack> ret = Lists.newArrayList();
		int stage = thisState.getValue(DCState.STAGE8);
		boolean black = thisState.getValue(BLACK);
		int count = 1;
		if (fortune > 0) {
			count += cropRand.nextInt(fortune);
		}
		if (stage == 7) {
			ret.add(new ItemStack(FoodInit.seeds, count + 2, 6));
		} else if (stage == 6) {
			if (black) {
				ret.add(new ItemStack(FoodInit.petals, 1, 1));
				ret.add(new ItemStack(FoodInit.crops, count, 10));
			} else {
				ret.add(new ItemStack(FoodInit.petals, 1, 0));
				ret.add(new ItemStack(FoodInit.crops, count, 10));
			}
		} else if (stage == 5 || stage == 4) {
			if (black) {
				ret.add(new ItemStack(FoodInit.petals, count, 1));
			} else {
				ret.add(new ItemStack(FoodInit.petals, count, 0));
			}
		}
		return ret;
	}

	@Override
	public IBlockState getGrownState() {
		return this.getDefaultState().withProperty(DCState.STAGE8, 6);
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

	@Override
	public boolean isSuitablePlace(World world, BlockPos pos, IBlockState targetState) {
		if (targetState == null)
			return false;
		else {
			boolean water = this.isInWater(world, pos.up());
			boolean soil = targetState.getMaterial() == Material.GROUND || targetState.getMaterial() == Material.GRASS;
			boolean air = world.isAirBlock(pos.up(2));
			// DCLogger.debugLog("water " + water + ", soil " + soil + ", air " + air);
			return water && soil && air;
		}
	}

	@Override
	public GrowingStage getCurrentStage(IBlockState thisState) {
		if (thisState == null)
			return GrowingStage.DEAD;
		else {
			int i = DCState.getInt(thisState, DCState.STAGE8);
			if (i == 7)
				return GrowingStage.DEAD;
			else if (i == 6)
				return GrowingStage.GROWN;
			else if (i > 3)
				return GrowingStage.FLOWER;
			else
				return GrowingStage.YOUNG;
		}
	}

	@Override
	public boolean grow(World world, BlockPos pos, IBlockState state) {
		if (state != null && state.getBlock() == this) {
			int stage = state.getValue(DCState.STAGE8);
			boolean water = this.isInWater(world, pos);
			if (water) {
				if (stage < 7) {
					int season = DCTimeHelper.getSeason(world);
					int next = stage;
					if (stage == 6 && season > 2) {
						// winter
						next = stage + 1;
					} else if (stage == 5 && season > 1) {
						// autumn
						next = stage + 1;
					} else if ((stage == 4 || stage == 3) && (season == 1 || season == 2)) {
						// summer
						next = stage + 1;
					} else if (stage < 3 && season < 3) {
						if (stage == 0 && world.rand.nextInt(30) == 0) {
							state.withProperty(BLACK, true);
						}
						next = stage + 1;
					} else if (stage > 1 && season == 3) {
						next = 7;
					}

					if (next > stage) {
						IBlockState newstate = state.withProperty(DCState.STAGE8, next);
						return world.setBlockState(pos, newstate, 2);
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean harvest(World world, BlockPos pos, IBlockState thisState, EntityPlayer player) {
		if (thisState != null && thisState.getBlock() == this) {
			int stage = thisState.getValue(DCState.STAGE8);
			if (stage > 3) {
				int f = 0;
				if (player != null && !DCUtil.isEmpty(player.getActiveItemStack())) {
					f = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getActiveItemStack());
				}
				List<ItemStack> crops = this.getCropItems(thisState, f);
				boolean ret = false;
				for (ItemStack item : crops) {
					EntityItem drop = new EntityItem(world);
					drop.setEntityItemStack(item);
					if (player != null) {
						drop.setPosition(player.posX, player.posY + 0.15D, player.posZ);
					} else {
						drop.setPosition(pos.getX(), pos.getY() + 0.5D, pos.getZ());
					}
					if (!world.isRemote) {
						world.spawnEntityInWorld(drop);
					}
					ret = true;
				}
				if (ret) {
					if (stage == 7) {
						boolean air = !this.isInWater(world, pos);
						world.setBlockState(pos, Blocks.WATER.getDefaultState(), 2);
					} else {
						IBlockState next = thisState.withProperty(DCState.STAGE8, 2);
						world.setBlockState(pos, next, 2);
					}
				}
				return ret;
			}
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
		ret.add(DCHeatTier.NORMAL);
		ret.add(DCHeatTier.WARM);
		ret.add(DCHeatTier.HOT);
		return ret;
	}

	@Override
	public List<DCHumidity> getSuitableHum(IBlockState thisState) {
		List<DCHumidity> ret = new ArrayList<DCHumidity>();
		ret.add(DCHumidity.WET);
		ret.add(DCHumidity.UNDERWATER);
		return ret;
	}

	@Override
	public List<DCAirflow> getSuitableAir(IBlockState thisState) {
		List<DCAirflow> ret = new ArrayList<DCAirflow>();
		ret.add(DCAirflow.TIGHT);
		ret.add(DCAirflow.NORMAL);
		ret.add(DCAirflow.FLOW);
		ret.add(DCAirflow.WIND);
		return ret;
	}

	/* IRapidCollectables */

	@Override
	public boolean isCollectable(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return false;
		// デフォルトではハサミ
		return item.getItem() instanceof ItemShears;
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
		int i = meta & 7;
		boolean j = meta > 7;
		IBlockState state = this.getDefaultState().withProperty(DCState.STAGE8, i);
		state.withProperty(BLACK, j);
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		int j = 0;

		i = state.getValue(DCState.STAGE8);
		j = state.getValue(BLACK) ? 8 : 0;
		return i + j;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.STAGE8,
				BLACK,
				BlockFluidBase.LEVEL
		});
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
		if (this.getSeedItem(state) != null)
			return this.getSeedItem(state).getItem();
		else
			return null;
	}

	/* IGrowable */

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		int stage = state.getValue(DCState.STAGE8);
		// DCLogger.debugLog("bonemeal? 1 " + stage);
		IClimate clm = this.getClimate(world, pos, state);
		// DCLogger.debugLog("suitable? " + this.isSuitableClimate(clm, state));
		return this.isSuitableClimate(clm, state) && stage > -1 && stage < 7;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		int stage = DCState.getInt(state, DCState.STAGE8);
		// DCLogger.debugLog("bonemeal? 2 " + stage);
		return stage > -1 && stage < 7;
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
		int meta = this.getMetaFromState(state);
		if (meta >= 0)
			return new ItemStack(this, 1, meta);
		return getItem(world, pos, state);
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

}
