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
import defeatedcrow.hac.api.cultivate.GrowingStage;
import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.core.base.INameSuffix;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
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
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGrape extends BlockDC implements INameSuffix, IClimateCrop, IRapidCollectables, IGrowable,
		IPlantable {
	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D);
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
	public static final PropertyBool GROUND = PropertyBool.create("ground");
	public static final PropertyBool TOP = PropertyBool.create("top");
	public static final PropertyDirection FACING2 = PropertyDirection.create("facing");
	protected Random cropRand = new Random();

	public BlockGrape(String s) {
		super(Material.PLANTS, s);
		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.STAGE4, 0).withProperty(FACING2, EnumFacing.DOWN).withProperty(GROUND, false)
				.withProperty(TOP, false));
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
		return 150;
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
		list.add(new ItemStack(this, 1, 3));
		return list;
	}
	/* Block動作 */

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			IBlockState crop = world.getBlockState(pos);
			ItemStack held = player.inventory.getCurrentItem();
			if (!DCUtil.isEmpty(held) && MainUtil.isBonemeal(held)) {
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
		if (state.getBlock() == this) {
			IClimate clm = this.getClimate(world, pos, state);
			int chance = this.isSuitableClimate(clm, state) ? 8 : 32;
			if (rand.nextInt(chance) == 0) {
				this.grow(world, pos, state);
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
		GrowingStage stage = this.getCurrentStage(state);
		ret.add(this.getSeedItem(state));
		if (stage == GrowingStage.GROWN) {
			ret.addAll(this.getCropItems(state, fortune));
		}
		return ret;
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.canBlockStay(worldIn, pos, state)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}

	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		return this.isSuitablePlace(worldIn, pos, worldIn.getBlockState(pos.down()));
	}

	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		if (!this.canBlockStay(world, pos, state)) {
			world.destroyBlock(pos, true);
		}
	}

	protected float getSeedDropChance() {
		return 1.0F;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		int s = DCState.getInt(state, DCState.STAGE4);
		if (s < 0)
			s = 0;
		return new ItemStack(this, 1, s);
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = this.getActualState(state, source, pos);
		if (DCState.getBool(state, TOP)) {
			return UP_AABB;
		}
		EnumFacing face = state.getValue(FACING2);
		switch (face) {
		case NORTH:
			return NORTH_AABB;
		case SOUTH:
			return SOUTH_AABB;
		case WEST:
			return WEST_AABB;
		case EAST:
			return EAST_AABB;
		default:
			return Block.FULL_BLOCK_AABB;
		}
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	/* IClimateCrop */

	@Override
	public ItemStack getSeedItem(IBlockState state) {
		ItemStack item = new ItemStack(FoodInit.seeds, 1, 16);
		return item;
	}

	@Override
	public List<ItemStack> getCropItems(IBlockState state, int fortune) {
		List<ItemStack> ret = Lists.newArrayList();
		int i = 1 + MathHelper.floor(this.cropRand.nextInt(1 + fortune) / 2F);
		ret.add(new ItemStack(FoodInit.crops, i, 20));
		return ret;
	}

	@Override
	public IBlockState getGrownState() {
		return this.getDefaultState().withProperty(DCState.STAGE4, 3);
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
		return state.getBlock() == this || isSuitableMaterial(state.getMaterial()) || world.getBlockState(pos).getBlock()
				.canSustainPlant(state, world, pos, EnumFacing.UP, this);
	}

	protected boolean isSuitableMaterial(Material mat) {
		return mat == Material.GRASS || mat == Material.GROUND || mat == Material.SAND;
	}

	@Override
	public boolean isSuitablePlace(World world, BlockPos pos, IBlockState targetState) {
		for (EnumFacing face : EnumFacing.VALUES) {
			if (face != EnumFacing.DOWN) {
				if (!world.isAirBlock(pos.offset(face))) {
					IBlockState check = world.getBlockState(pos.offset(face));
					if (check.getMaterial() != Material.PLANTS && !check.getMaterial().isReplaceable())
						return true;
				}
			}
		}
		return isSoil(world, pos.down());
	}

	@Override
	public GrowingStage getCurrentStage(IBlockState thisState) {
		if (thisState == null)
			return GrowingStage.DEAD;
		else {
			int i = DCState.getInt(thisState, DCState.STAGE4);
			if (i == 3)
				return GrowingStage.GROWN;
			else if (i == 2)
				return GrowingStage.FLOWER;
			else if (i == 1)
				return GrowingStage.YOUNG;
			else
				return GrowingStage.GROUND;
		}
	}

	@Override
	public boolean grow(World world, BlockPos pos, IBlockState state) {
		if (state != null && state.getBlock() == this && world.getLight(pos) > 7) {
			// grow
			EnumSeason season = DCTimeHelper.getSeasonEnum(world);
			if (!DCState.getBool(state, GROUND)) {
				int stage = DCState.getInt(state, DCState.STAGE4);
				if (ModuleConfig.crop) {
					if (stage == 3 || season == EnumSeason.WINTER) {
						return false;
					} else if (stage > 0 && season != EnumSeason.SPRING) {
						IBlockState newstate = state.withProperty(DCState.STAGE4, stage + 1);
						world.setBlockState(pos, newstate, 2);
					} else {
						IBlockState newstate = state.withProperty(DCState.STAGE4, stage + 1);
						world.setBlockState(pos, newstate, 2);
					}
				} else {
					IBlockState newstate = state.withProperty(DCState.STAGE4, stage + 1);
					world.setBlockState(pos, newstate, 2);
				}
			}
			if (!ModuleConfig.crop || season != EnumSeason.WINTER) {
				EnumFacing f = EnumFacing.UP;
				if (!DCState.getBool(state, GROUND)) {
					int i = cropRand.nextInt(6);
					f = EnumFacing.VALUES[i];
				}
				IBlockState state2 = this.getActualState(state, world, pos);
				if (isGrowableHeight(world, pos, state2)) {
					if (f == EnumFacing.DOWN) {
						EnumFacing side = state2.getValue(FACING2);
						if (side.getAxis().isHorizontal() && world.isAirBlock(pos.up().offset(side)) && this.isGrowablePlace(world, pos.up().offset(side))) {
							IBlockState newstate = this.getDefaultState();
							world.setBlockState(pos.up().offset(side), newstate, 2);
						}
					} else if (world.isAirBlock(pos.offset(f)) && this.isGrowablePlace(world, pos.offset(f))) {
						IBlockState newstate = this.getDefaultState();
						world.setBlockState(pos.offset(f), newstate, 2);
					}
				}
			}
		}
		return false;
	}

	public boolean isGrowableHeight(World world, BlockPos pos, IBlockState state) {
		if (DCState.getBool(state, GROUND)) {
			return true;
		} else {
			int h = 1;
			while (h < 4) {
				for (BlockPos p : BlockPos.getAllInBoxMutable(pos.add(-1, -h, -1), pos.add(1, -h, 1))) {
					if (isSuitableMaterial(world.getBlockState(p).getMaterial())) {
						return true;
					}
				}
				h++;
			}
		}
		return false;
	}

	public boolean isGrowablePlace(World world, BlockPos pos) {
		for (EnumFacing face : EnumFacing.VALUES) {
			if (face != EnumFacing.DOWN) {
				if (!world.isAirBlock(pos.offset(face))) {
					IBlockState check = world.getBlockState(pos.offset(face));
					if (check.getMaterial() != Material.PLANTS && !check.getMaterial().isReplaceable())
						return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean harvest(World world, BlockPos pos, IBlockState thisState, EntityPlayer player) {
		if (thisState != null && thisState.getBlock() == this) {
			GrowingStage stage = this.getCurrentStage(thisState);
			if (stage == GrowingStage.GROWN) {
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
					if (!world.isRemote)
						world.spawnEntity(drop);
					ret = true;
				}
				if (ret) {
					IBlockState next = thisState.withProperty(DCState.STAGE4, 0);
					world.setBlockState(pos, next, 2);
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
		ret.add(DCHeatTier.COLD);
		ret.add(DCHeatTier.COOL);
		ret.add(DCHeatTier.NORMAL);
		ret.add(DCHeatTier.WARM);
		return CoreConfigDC.harderCrop ? getHardmodeTemp(thisState) : ret;
	}

	@Override
	public List<DCHumidity> getSuitableHum(IBlockState thisState) {
		List<DCHumidity> ret = new ArrayList<DCHumidity>();
		ret.add(DCHumidity.DRY);
		ret.add(DCHumidity.NORMAL);
		return CoreConfigDC.harderCrop ? getHardmodeHum(thisState) : ret;
	}

	@Override
	public List<DCAirflow> getSuitableAir(IBlockState thisState) {
		List<DCAirflow> ret = new ArrayList<DCAirflow>();
		ret.add(DCAirflow.NORMAL);
		ret.add(DCAirflow.FLOW);
		ret.add(DCAirflow.WIND);
		return CoreConfigDC.harderCrop ? getHardmodeAir(thisState) : ret;
	}

	public List<DCHeatTier> getHardmodeTemp(IBlockState thisState) {
		List<DCHeatTier> ret = new ArrayList<DCHeatTier>();
		ret.add(DCHeatTier.NORMAL);
		return ret;
	}

	public List<DCHumidity> getHardmodeHum(IBlockState thisState) {
		List<DCHumidity> ret = new ArrayList<DCHumidity>();
		ret.add(DCHumidity.NORMAL);
		return ret;
	}

	public List<DCAirflow> getHardmodeAir(IBlockState thisState) {
		List<DCAirflow> ret = new ArrayList<DCAirflow>();
		ret.add(DCAirflow.NORMAL);
		ret.add(DCAirflow.FLOW);
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
	/* Json登録用 */

	@Override
	public String[] getNameSuffix() {
		return null;
	};
	/* drop */

	@Override
	public int damageDropped(IBlockState state) {
		return this.getSeedItem(state).getItemDamage();
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this.getSeedItem(state).getItem();
	}
	/* state */

	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 3;
		if (i > 3) {
			i = 3;
		}
		IBlockState state = this.getDefaultState().withProperty(DCState.STAGE4, i);
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = state.getValue(DCState.STAGE4);
		if (i > 3) {
			i = 3;
		}
		return i;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean up = !world.isAirBlock(pos.up()) && world.getBlockState(pos.up()).getMaterial() != Material.PLANTS;
		boolean down = isSuitableMaterial(world.getBlockState(pos.down()).getMaterial());
		EnumFacing f = EnumFacing.DOWN;
		if (up) {
			f = EnumFacing.UP;
		}
		for (EnumFacing f2 : EnumFacing.HORIZONTALS) {
			if (!world.isAirBlock(pos.offset(f2)) && world.getBlockState(pos.offset(f2)).getMaterial() != Material.PLANTS && !world.getBlockState(pos
					.offset(f2)).getMaterial().isReplaceable()) {
				f = f2;
				break;
			}
		}
		return state.withProperty(FACING2, f).withProperty(GROUND, down).withProperty(TOP, up);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				GROUND,
				FACING2,
				DCState.STAGE4,
				TOP
		});
	}

	@Override
	public IProperty[] ignoreTarget() {
		return null;
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.CUSTOM;
	}
	/* IGrowable */

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
		if (state != null && state.getBlock() == this) {
			GrowingStage stage = this.getCurrentStage(state);
			IClimate clm = this.getClimate(world, pos, state);
			return this.isSuitableClimate(clm, state) && stage.canUseBonemeal();
		}
		return false;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
		if (state != null && state.getBlock() == this) {
			GrowingStage stage = this.getCurrentStage(state);
			return stage.canUseBonemeal();
		}
		return false;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		this.grow(world, pos, state);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return world.getBlockState(pos);
	}
}
