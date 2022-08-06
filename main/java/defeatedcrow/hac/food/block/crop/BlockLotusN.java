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
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IHumidityTile;
import defeatedcrow.hac.api.cultivate.GrowingStage;
import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.BlockContainerDC;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.core.base.INameSuffix;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLotusN extends BlockContainerDC implements INameSuffix, IClimateCrop, IRapidCollectables, IGrowable,
		IHumidityTile {
	public static final PropertyBool BLACK = PropertyBool.create("black");
	public static final AxisAlignedBB DOUBLE_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.125D, 1.0D);
	protected Random cropRand = new Random();

	public BlockLotusN(String s, int max) {
		super(Material.SAND, s);
		this.setSoundType(SoundType.GROUND);
		this.setHardness(0.2F);
		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.STAGE8, 0).withProperty(BLACK, false));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityLotus();
	}

	public boolean isInWater(IBlockAccess world, BlockPos pos) {
		IBlockState target = world.getBlockState(pos.up());
		if (target.getBlock() instanceof BlockIce) {
			return true;
		}
		if (target.getBlock() instanceof BlockLiquid && target.getMaterial() == Material.WATER) {
			return true;
		}
		return false;
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
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 5));
		return list;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return DOUBLE_BLOCK_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}
	/* Block動作 */

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			IBlockState crop = world.getBlockState(pos);
			int stage = state.getValue(DCState.STAGE8);
			ItemStack held = player.inventory.getCurrentItem();
			if (ClimateCore.isDebug && DCUtil.isHeldWrench(player, hand)) {
				IBlockState newS = state.withProperty(BLACK, true);
				world.setBlockState(pos, newS);
			}
			if (!DCUtil.isEmpty(held) && MainUtil.isBonemeal(held)) {
				ItemDye.applyBonemeal(held, world, pos, player, hand);
				return true;
			} else if (stage > 4) {
				player.playSound(SoundEvents.BLOCK_GRASS_PLACE, 0.75F, 1.0F);
				return this.harvest(world, pos, crop, player);
			}
		}
		return false;
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
		IClimate c = ClimateAPI.calculator.getClimate(world, pos, checkingRange());
		return c;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(Blocks.DIRT));
		if (cropRand.nextFloat() <= this.getSeedDropChance()) {
			ret.add(this.getSeedItem(state));
		}
		ret.addAll(this.getCropItems(state, fortune));
		return ret;
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.canBlockStay(worldIn, pos, state)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState(), 2);
		}
	}

	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		return this.isSuitablePlace(worldIn, pos, worldIn.getBlockState(pos));
	}

	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
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
	public IBlockState setGroundState(IBlockState state) {
		return state.withProperty(DCState.STAGE8, 0);
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
	public boolean isSuitablePlace(World world, BlockPos pos, IBlockState target) {
		if (target == null)
			return false;
		else {
			boolean water = this.isInWater(world, pos);
			boolean air = world.isAirBlock(pos.up(2));
			boolean soil = target.getBlock() instanceof BlockLotusN || target.getBlock() instanceof BlockDirt || target.getBlock() instanceof BlockGrass;
			return water && air && soil;
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
			boolean black = state.getValue(BLACK);
			boolean water = this.isInWater(world, pos);
			if (water) {
				EnumSeason season = DCTimeHelper.getSeasonEnum(world);
				int next = stage;
				if (ModuleConfig.crop) {
					switch (stage) {
					case 0:
					case 1:
					case 2:
						if (season.id == 3)
							next = 7;
						else
							next = stage + 1;
						break;
					case 3:
					case 4:
						if (season.id == 1 || season.id == 2)
							next = stage + 1;
						else if (season.id == 3)
							next = 7;
						break;
					case 5:
						if (season.id > 1)
							next = stage + 1;
						else if (season.id == 3)
							next = 7;
						break;
					case 6:
						if (season.id == 3)
							next = 7;
						break;
					case 7:
						if (season.id < 2)
							next = 0;
						break;
					}
				} else {
					next = stage + 1;
				}

				if (stage < 3) {
					if (world.rand.nextInt(50) == 0 && (!ModuleConfig.crop || season.id < 3)) {
						black = true;
					}
				} else {
					IBlockState target = world.getBlockState(pos.up());
					if (target.getBlock() instanceof BlockIce) {
						next = 7;
					}
				}
				if (next > stage) {
					IBlockState newstate = state.withProperty(DCState.STAGE8, next).withProperty(BLACK, black);
					if (next == 3 && !world.isRemote) {
						((TileEntityLotus) world.getTileEntity(pos)).setRandNum();
					}
					return world.setBlockState(pos, newstate, 2);
				}
			}
		}
		return false;
	}

	@Override
	public boolean harvest(World world, BlockPos pos, IBlockState thisState, EntityPlayer player) {
		if (thisState != null && thisState.getBlock() == this) {
			int stage = thisState.getValue(DCState.STAGE8);
			boolean black = thisState.getValue(BLACK);
			EnumSeason season = DCTimeHelper.getSeasonEnum(world);
			if (stage > 4) {
				int f = 0;
				if (player != null && !DCUtil.isEmpty(player.getActiveItemStack())) {
					f = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getActiveItemStack());
				}
				List<ItemStack> crops = this.getCropItems(thisState, f);
				boolean ret = false;
				for (ItemStack item : crops) {
					EntityItem drop = new EntityItem(world);
					drop.setItem(item);
					if (player != null) {
						drop.setPosition(player.posX, player.posY + 0.15D, player.posZ);
					} else {
						drop.setPosition(pos.getX(), pos.getY() + 0.5D, pos.getZ());
					}
					if (!world.isRemote) {
						world.spawnEntity(drop);
					}
					ret = true;
				}
				if (ret) {
					if (stage == 7) {
						world.setBlockState(pos, Blocks.DIRT.getDefaultState(), 2);
					} else {
						if (black && world.rand.nextInt(5) == 0) {
							thisState = thisState.withProperty(BLACK, false);
						}
						if (season == EnumSeason.WINTER) {
							IBlockState next = thisState.withProperty(DCState.STAGE8, 7);
							world.setBlockState(pos, next, 3);
						} else {
							IBlockState next = thisState.withProperty(DCState.STAGE8, 3);
							world.setBlockState(pos, next, 3);
						}
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
		ret.add(DCHeatTier.BOIL);
		return CoreConfigDC.harderCrop ? getHardmodeTemp(thisState) : ret;
	}

	@Override
	public List<DCHumidity> getSuitableHum(IBlockState thisState) {
		List<DCHumidity> ret = new ArrayList<DCHumidity>();
		ret.add(DCHumidity.WET);
		ret.add(DCHumidity.UNDERWATER);
		return CoreConfigDC.harderCrop ? getHardmodeHum(thisState) : ret;
	}

	@Override
	public List<DCAirflow> getSuitableAir(IBlockState thisState) {
		List<DCAirflow> ret = new ArrayList<DCAirflow>();
		ret.add(DCAirflow.TIGHT);
		ret.add(DCAirflow.NORMAL);
		ret.add(DCAirflow.FLOW);
		ret.add(DCAirflow.WIND);
		return CoreConfigDC.harderCrop ? getHardmodeAir(thisState) : ret;
	}

	public List<DCHeatTier> getHardmodeTemp(IBlockState thisState) {
		List<DCHeatTier> ret = new ArrayList<DCHeatTier>();
		ret.add(DCHeatTier.NORMAL);
		ret.add(DCHeatTier.WARM);
		return ret;
	}

	public List<DCHumidity> getHardmodeHum(IBlockState thisState) {
		List<DCHumidity> ret = new ArrayList<DCHumidity>();
		ret.add(DCHumidity.UNDERWATER);
		return ret;
	}

	public List<DCAirflow> getHardmodeAir(IBlockState thisState) {
		List<DCAirflow> ret = new ArrayList<DCAirflow>();
		ret.add(DCAirflow.TIGHT);
		ret.add(DCAirflow.NORMAL);
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
		IBlockState state = this.getDefaultState().withProperty(DCState.STAGE8, i).withProperty(BLACK, j);
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
				BLACK
		});
	}

	@Override
	public IProperty[] ignoreTarget() {
		return new IProperty[] {
				DCState.STAGE8,
				BLACK
		};
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.NORMAL;
	}

	// drop
	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.DIRT);
	}

	/* IGrowable */

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		int stage = DCState.getInt(state, DCState.STAGE8);
		return this.isSuitablePlace(world, pos, state) && stage < 7;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		int stage = DCState.getInt(state, DCState.STAGE8);
		return stage < 7;
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

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing side) {
		return true;
	}

	// 接してる面側が水だったら、その接してる水の側面を描画しない
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		if (face.getAxis().isHorizontal() && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

	@Override
	public DCHumidity getHumidity(World world, BlockPos targrt, BlockPos thisTile) {
		return DCHumidity.UNDERWATER;
	}
}
