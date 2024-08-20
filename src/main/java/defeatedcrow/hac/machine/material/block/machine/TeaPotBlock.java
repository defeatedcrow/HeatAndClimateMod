package defeatedcrow.hac.machine.material.block.machine;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fluids.FluidUtil;

public class TeaPotBlock extends ProcessTileBlock {

	protected static final VoxelShape AABB = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	final String name;

	public TeaPotBlock(String s) {
		super(getProp());
		name = s;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		return AABB;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(3.0F, 540.0F).noOcclusion();
	}

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TeaPotTile(pos, state);
	}

	@Override
	public String getRegistryName() {
		return "machine/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("dcs_climate:block/dcs_dummy", ImmutableMap.of("all", "dcs_climate:block/machine/" + name + "_item")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getStateNameSuffix() {
		return ImmutableList.of("");
	}

	@Override
	public boolean requireStateJson() {
		return false;
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:block/machine/" + name + "_item"));
	}

	public static void changeLisState(Level level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		if (state.getBlock() instanceof TeaPotBlock) {
			boolean l = !DCState.getBool(state, DCState.LIT);
			level.setBlock(pos, state.setValue(DCState.LIT, Boolean.valueOf(l)), 3);
		}
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return !level.isClientSide ? createTickerHelper(type, MachineInit.TEA_POT_TILE.get(), ProcessTileBaseDC::serverTick) : null;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		BlockEntity tile = level.getBlockEntity(pos);
		if (tile instanceof TeaPotTile tank) {
			if (ClimateCore.isDebug && player.isCrouching()) {
				DCLogger.debugInfoLog("### Fluid: " + tank.inputTank.getFluid().getDisplayName().getString() + "/" + tank.inputTank.getFluid().getAmount() + " ###");
			}
			ItemStack held = player.getItemInHand(hand);
			if (level.isClientSide) {
				return InteractionResult.SUCCESS;
			} else {
				if (!DCUtil.isEmpty(held) && FluidUtil.getFluidHandler(held.copy()).isPresent()) {
					if (DCFluidUtil.exchangeSidedFluid(level, player.position(), tank.inputTank, tank.outputTank, held)) {
						tile.setChanged();
						player.getInventory().setChanged();
						level.playSound(player, pos, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1.0F, 1.2F);
					}
					return InteractionResult.CONSUME;
				} else if (DCUtil.isEmpty(held) && player.isCrouching()) {
					TeaPotBlock.changeLisState(level, pos);
				} else {
					super.use(state, level, pos, player, hand, hitRes);
				}
			}
		}
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		BlockEntity tile = level.getBlockEntity(pos);
		if (tile instanceof TeaPotTile pot && rand.nextInt(3) == 0) {
			ItemStack output = pot.getDisplay(0);
			if (!output.isEmpty() && output.is(TagDC.ItemTag.HAC_DRINK_HOT)) {
				double d0 = pos.getX() + 0.1D + rand.nextDouble() * 0.8D;
				double d1 = pos.getY() + 0.5D;
				double d2 = pos.getZ() + 0.1D + rand.nextDouble() * 0.8D;
				level.addParticle(CoreInit.SMOKE.get(), d0, d1, d2, 0.0D, 0.005D, 0.0D);
			}
		}
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable Level level, List<Component> list) {
		MutableComponent tex1 = Component.translatable("dcs.tip.cooking_pot");
		list.add(tex1);
	}

}
