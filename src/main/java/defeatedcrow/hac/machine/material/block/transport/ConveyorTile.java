package defeatedcrow.hac.machine.material.block.transport;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.material.IDisplayTile;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.network.packet.message.MsgTileDisplayItemToC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.Hopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class ConveyorTile extends BlockEntity implements WorldlyContainer, IDisplayTile {

	private final RecipeManager.CachedCheck<Container, SmeltingRecipe> quickCheck;

	public ConveyorTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
		this.quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
	}

	public ConveyorTile(BlockPos pos, BlockState state) {
		this(MachineInit.CONVEYOR_TILE.get(), pos, state);
	}

	// tick
	public int move = 0;
	public int prevMove = 0;
	public ItemStack lastDisp = ItemStack.EMPTY;
	public ItemStack disp = ItemStack.EMPTY;
	public static final int MAX_MOVE = 8;

	@Override
	public ItemStack getDisplay(int s) {
		return disp;
	}

	@Override
	public void setDisplay(int s, ItemStack item) {
		lastDisp = disp.copy();
		disp = item;
		prevMove = move;
		move = s;
	}

	public static void clientTick(Level level, BlockPos pos, BlockState state, ConveyorTile tile) {
		tile.lastDisp = tile.disp.copy();
		tile.prevMove = tile.move;
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, ConveyorTile tile) {
		tile.onTickProcess(level, pos, state);
	}

	public boolean isActive() {
		return !DCState.getBool(this.getBlockState(), DCState.POWERED);
	}

	public Direction getBlockDir() {
		return DCState.getFace(this.getBlockState(), DCState.FACING);
	}

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (isActive()) {
			boolean f = false;
			prevMove = move;

			if (DCUtil.isEmpty(this.getItem(0))) {
				move = 0;
				if (prevMove == 0) {
					if (insertItem()) {
						f = true;
					}
				}
			} else {
				if (move < MAX_MOVE) {
					if (move == 4) {
						if (onMiddlePosition()) {
							move++;
						}
						f = true;
					} else {
						move++;
					}
				} else {
					// 送り出し
					if (releaseItem()) {
						f = true;
						move = 0;
					}
				}
			}
			if (f) {
				this.setChanged();
			}
		}

		disp = getItem(0).copy();
		if (move != prevMove) {
			MsgTileDisplayItemToC.sendToClient((ServerLevel) level, pos, disp, move);
		}
		return false;
	}

	protected boolean insertItem() {
		if (!DCUtil.isEmpty(this.getItem(0)))
			return false;

		Direction side = getBlockDir().getOpposite();
		BlockEntity input = getLevel().getBlockEntity(getBlockPos().relative(side));

		// DOWNからの搬出を偽装
		if (input != null && !(input instanceof ConveyorTile) && !(input instanceof Hopper)) {
			return input.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.DOWN)
					.map(handler -> {
						int slot = -1;
						for (int j = 0; j < handler.getSlots(); j++) {
							if (!handler.extractItem(j, 1, true).isEmpty()) {
								slot = j;
								break;
							}
						}
						if (slot >= 0) {
							ItemStack take = handler.extractItem(slot, 1, true).copy();
							int i = this.getInventory().canIncrSlot(0, take);
							if (i > 0) {
								this.getInventory().incrStackInSlot(0, take);
								handler.extractItem(slot, 1, false);
								this.setChanged();
								input.setChanged();
								return true;
							}
						}
						return false;
					}).orElse(false);
		}
		return false;
	}

	// protected boolean insertDrop() {
	// if (!DCUtil.isEmpty(this.getItem(0)))
	// return false;
	//
	// VoxelShape box = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	// AABB aabb = box.bounds().move(getBlockPos()).inflate(0.5D);
	// List<ItemEntity> drops = getLevel().getEntitiesOfClass(ItemEntity.class, aabb, EntitySelector.ENTITY_STILL_ALIVE);
	// for (ItemEntity drop : drops) {
	// ItemStack take = drop.getItem().copy();
	// take.setCount(1);
	// int i = this.getInventory().canIncrSlot(0, take);
	// if (i > 0) {
	// this.getInventory().incrStackInSlot(0, take);
	// drop.getItem().split(i);
	// if (drop.getItem().isEmpty())
	// drop.discard();
	// this.setChanged();
	// }
	// }
	// return false;
	// }

	protected BlockPos getForwardPos() {
		Direction side = getBlockDir();
		return getBlockPos().relative(side);
	}

	protected boolean releaseItem() {
		if (DCUtil.isEmpty(this.getItem(0)))
			return false;

		BlockPos next = getForwardPos();
		BlockEntity outlet = getLevel().getBlockEntity(next);
		boolean flag = false;
		if (outlet != null) {
			flag = outlet.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP).map(handler -> {
				ItemStack take = this.getItem(0).copy();
				take.setCount(1);
				for (int j = 0; j < handler.getSlots(); j++) {
					ItemStack ret = handler.insertItem(j, take, true);
					if (DCUtil.isEmpty(ret)) {
						handler.insertItem(j, take, false);
						this.getInventory().removeItem(0, 1);
						this.setChanged();
						outlet.setChanged();
						break;
					}
				}
				return DCUtil.isEmpty(this.getItem(0));
			}).orElse(false);
		}
		if (!flag && !DCUtil.isEmpty(this.getItem(0)) && getLevel().getBlockState(next).isAir()) {
			ItemEntity drop = new ItemEntity(getLevel(), next.getX() + 0.5D, next.getY() + 0.5D, next.getZ() + 0.5D, this.getItem(0).copy());
			if (getLevel().addFreshEntity(drop)) {
				this.getInventory().setItem(0, ItemStack.EMPTY);
				this.setChanged();
				flag = true;
			}
		}
		return flag;
	}

	protected boolean onMiddlePosition() {
		if (!DCUtil.isEmpty(this.getItem(0))) {
			ItemStack target = this.getItem(0).copy();
			ClimateSupplier sup = new ClimateSupplier(getLevel(), getBlockPos());
			boolean hasRecipe = DCRecipes.getSmeltingRecipe(sup, target)
					.filter(recipe -> recipe.additionalRequire(getLevel(), getBlockPos())).map(recipe -> {
						ItemStack output = recipe.getOutput();
						getLevel().playSound(null, getBlockPos(), getSE(sup.get()), SoundSource.BLOCKS, 0.8F, 1.5F);
						this.getInventory().setItem(0, output);
						this.setChanged();
						return true;
					}).orElse(false);
			if (!hasRecipe) {
				hasRecipe = DCRecipes.getHeatTreatmentRecipe(sup, target)
						.filter(recipe -> {
							ItemStack check = recipe.getCurrentOutput(target, sup.get());
							return !DCUtil.isEmpty(check) && !check.is(recipe.getFail().asItem());
						}).map(recipe -> {
							ItemStack output = recipe.getCurrentOutput(target, sup.get()).copy();
							getLevel().playSound(null, getBlockPos(), getSE(sup.get()), SoundSource.BLOCKS, 0.8F, 1.5F);
							this.getInventory().setItem(0, output);
							this.setChanged();
							return true;
						}).orElse(false);
			}
			if (!hasRecipe && !target.is(CoreInit.DUSTBLOCK_STEEL.get().asItem())) {
				SmeltingRecipe recipe = quickCheck.getRecipeFor(getInventory(), getLevel()).orElse(null);
				if (recipe != null && DCHeatTier.smeltingTemp().contains(sup.get().getHeat()) && DCHumidity.notWet().contains(sup.get().getHumidity())
						&& DCAirflow.underRoofs().contains(sup.get().getAirflow())) {
					ItemStack output = recipe.assemble(getInventory());
					if (!DCUtil.isEmpty(output)) {
						getLevel().playSound(null, getBlockPos(), SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.8F, 1.5F);
						this.getInventory().setItem(0, output);
						this.setChanged();
					}
				}
			}
		}
		return true;
	}

	public SoundEvent getSE(IClimate climate) {
		if (climate.getHumidity() == DCHumidity.UNDERWATER)
			return SoundEvents.BUCKET_FILL;
		return climate.getHeat().isCold() ? SoundEvents.GLASS_BREAK : SoundEvents.LAVA_EXTINGUISH;
	}

	/* Inventory */

	protected InventoryDC inv = new InventoryDC(1, this);

	public InventoryDC getInventory() {
		return inv;
	}

	@Override
	public int getContainerSize() {
		return 1;
	}

	protected Component getDefaultName() {
		return Component.translatable("dcs.container.device");
	}

	@Override
	public int[] getSlotsForFace(Direction dir) {
		if (dir == Direction.DOWN || dir == Direction.UP) {
			return new int[] { 0 };
		} else {
			return new int[] {};
		}
	}

	@Override
	public boolean isEmpty() {
		return getInventory().isEmpty();
	}

	@Override
	public ItemStack getItem(int s) {
		return getInventory().getItem(s);
	}

	@Override
	public ItemStack removeItem(int s, int count) {
		return getInventory().removeItem(s, count);
	}

	@Override
	public ItemStack removeItemNoUpdate(int s) {
		return getInventory().removeItemNoUpdate(s);
	}

	@Override
	public void setItem(int s, ItemStack item) {
		getInventory().setItem(s, item);
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void clearContent() {
		getInventory().clearContent();
	}

	@Override
	public boolean canPlaceItem(int s, ItemStack stack) {
		return DCUtil.isEmpty(this.getItem(0));
	}

	@Override
	public boolean canPlaceItemThroughFace(int s, ItemStack stack, @Nullable Direction dir) {
		return dir == Direction.UP && canPlaceItem(s, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int s, ItemStack stack, Direction dir) {
		return move > 4 && dir == Direction.DOWN && !DCUtil.isEmpty(this.getItem(0));
	}

	LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER) {
			if (facing == Direction.UP)
				return handlers[0].cast();
			else if (facing == Direction.DOWN)
				return handlers[1].cast();
			else
				return handlers[2].cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		for (int x = 0; x < handlers.length; x++)
			handlers[x].invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		loadTag(tag);
	}

	public void loadTag(CompoundTag tag) {
		getInventory().readFromNBT(tag);
		move = tag.getInt(TagKeyDC.CURRENT_PROGRESS);
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		writeTag(tag);
		tag.putInt(TagKeyDC.CURRENT_PROGRESS, move);
	}

	public void writeTag(CompoundTag tag) {
		getInventory().writeToNBT(tag);
	}

}
