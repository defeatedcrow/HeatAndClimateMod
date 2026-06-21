package defeatedcrow.hac.machine.material.block.machine;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.network.packet.message.IIntReceiver;
import defeatedcrow.hac.core.network.packet.message.MsgTileFluidToC;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.event.CraftingFoodEvent;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.machine.client.gui.CoffeeMakerMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCTank;
import defeatedcrow.hac.machine.material.fluid.IFluidTankTileDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

/**
 * コーヒーのみを扱う特殊なプロセスタイル
 */
public class CoffeeMakerTile extends ProcessTileBaseDC implements IFluidTankTileDC, IIntReceiver {

	public CoffeeMakerTile(BlockPos pos, BlockState state) {
		this(MachineInit.COFFEE_SIPHON_TILE.get(), pos, state);
	}

	public CoffeeMakerTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		totalProgress = maxProgressTime();
	}

	/* inventory */

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			return switch (id) {
			case 0 -> CoffeeMakerTile.this.currentProgress;
			default -> 0;
			};
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				CoffeeMakerTile.this.currentProgress = data;
				break;
			}
		}

		@Override
		public int getCount() {
			return 1;
		}
	};

	@Override
	public int getContainerSize() {
		return 4;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 0, 2 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 1, 3 };
	}

	@Override
	protected int[] getSideSlots() {
		return new int[] { 0, 1, 2, 3 };
	}

	protected int intankS1 = 2;
	protected int intankS2 = 3;

	protected int maxInSlot() {
		return 1;
	}

	/* DeviceRecipe */

	protected int maxProgressTime() {
		return 300;
	}

	public static final int TANK_CAP = 4000;
	public DCTank inputTank = new DCTank(TANK_CAP);

	/* レシピは固定 */
	protected boolean isEnoughHeat() {
		return currentClimate != null && currentClimate.getHeat()
		    .getTier() >= DCHeatTier.BOIL.getTier();
	}

	protected boolean isInputItem(ItemStack stack) {
		return !DCUtil.isEmpty(stack) && stack.is(TagDC.ItemTag.COFFEE_POWDER);
	}

	protected boolean isInputFluid() {
		return !inputTank.isEmpty() && inputTank.getFluid()
		    .getFluid()
		    .is(TagDC.FluidTag.ALL_WATER)
		    && inputTank.getFluid()
		        .getAmount() >= 1000;
	}

	protected ItemStack getOutputItem() {
		ItemStack res = new ItemStack(FoodInit.TEA_COFFEE.get());
		ItemStack input = this.inventory.getItem(0);
		NonNullList<ItemStack> inputs = NonNullList.of(input);
		int[] cons = { 0 };
		if (!res.isEmpty() && res.getItem() instanceof IFoodTaste food) {
			int taste = CraftingFoodEvent.getResultTaste(inputs, cons);
			food.setTaste(res, taste);
		}
		if (!res.isEmpty() && res.isEdible()) {
			boolean unsafe = CraftingFoodEvent.checkUnsafe(inputs, cons);
			if (unsafe) {
				CompoundTag tag = res.getOrCreateTag();
				tag.putBoolean(TagKeyDC.UNSAFE, true);
				res.setTag(tag);
			}
		}
		return res;
	}

	@Override
	public boolean isInProcess() {
		return totalProgress > 0;
	}

	@Override
	public boolean continueProcess(Level level, BlockPos pos, BlockState state) {
		ItemStack input = this.inventory.getItem(0);
		if (isEnoughHeat() && isInputItem(input) && isInputFluid()) {
			return inventory.canIncrSlot(1, getOutputItem()) > 0;
		}
		return false;
	}

	@Override
	public boolean finishProcess(Level level, BlockPos pos, BlockState state) {
		boolean flag = false;
		ItemStack res = getOutputItem();
		if (!DCUtil.isEmpty(res) && inventory.insertResult(res, 1, 1) > 0) {
			inputTank.drain(1000, FluidAction.EXECUTE);
			flag = true;
		}
		if (flag) {
			BlockEntity.setChanged(level, pos, state);
		}
		return flag;
	}

	@Override
	public boolean consumeInputs() {
		inventory.removeItem(0, 1);
		return false;
	}

	@Override
	public boolean resetProcess() {
		recipe = null;
		this.totalProgress = 0;
		this.currentProgress = 0;
		this.lastProgress = 0;
		return true;
	}

	@Override
	public boolean startProcess(Level level, BlockPos pos, BlockState state) {
		ItemStack input = this.inventory.getItem(0);
		if (isEnoughHeat() && isInputItem(input) && isInputFluid()) {
			this.totalProgress = maxProgressTime();
			return true;
		}
		return false;
	}

	int count = 4;
	private int hash1 = 0;

	public int coffeeStage = 0;

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
			return false;
		} else {
			count = 4;

			boolean flag = false;
			if (!DCUtil.isEmpty(this.inventory.getItem(intankS1)) && !this.inventory.isMaxStack(intankS2)) {
				ItemStack copy = this.inventory.getItem(intankS1)
				    .copy();
				copy.setCount(1);
				flag = FluidUtil.getFluidHandler(copy)
				    .map(handler -> {
					    FluidStack fluid = handler.getFluidInTank(0);
					    if (fluid.isEmpty() || inputTank.isFull()) {
						    int space = Math.min(inputTank.getFluidAmount(), handler.getTankCapacity(0));
						    int d = handler.fill(inputTank.drain(space, FluidAction.SIMULATE), FluidAction.EXECUTE);
						    if (d > 0 && inventory.canInsertResult(handler.getContainer(), intankS2, intankS2) != 0) {
							    // drain
							    inputTank.drain(d, FluidAction.EXECUTE);
							    ItemStack ret = handler.getContainer();
							    if (!ret.isEmpty()) {
								    ret.setCount(1);
								    inventory.incrStackInSlot(intankS2, ret);
							    }
							    inventory.removeItem(intankS1, 1);
							    return true;
						    }
					    } else if (handler.isFluidValid(TANK_CAP, fluid)) {
						    FluidStack drain = handler.drain(fluid, FluidAction.SIMULATE);
						    int f = inputTank.fill(drain, FluidAction.SIMULATE);
						    if (f > 0 && inventory.canInsertResult(handler.getContainer(), intankS2, intankS2) != 0) {
							    // fill
							    drain.setAmount(f);
							    inputTank.fill(drain, FluidAction.EXECUTE);
							    handler.drain(drain, FluidAction.EXECUTE);
							    ItemStack ret = handler.getContainer()
							        .copy();
							    if (!ret.isEmpty()) {
								    ret.setCount(1);
								    inventory.incrStackInSlot(intankS2, ret);
							    }
							    inventory.removeItem(intankS1, 1);
							    return true;
						    }
					    }
					    return false;
				    })
				    .orElse(false);
			}

			int lastHash1 = hash1;
			hash1 = inputTank.getFluid()
			    .hashCode() + inputTank.getFluidAmount();
			if (lastHash1 != hash1) {
				if (level instanceof ServerLevel sl) {
					BlockEntity.setChanged(level, pos, state);
					NonNullList<FluidStack> list = NonNullList.withSize(3, FluidStack.EMPTY);
					list.set(0, inputTank.getFluid());
					MsgTileFluidToC.sendToClient(sl, pos, list);
				}
			}

			// stage
			int lastStage = coffeeStage;
			coffeeStage = DCUtil.isEmpty(getItem(1)) ? 0 : 15;
			if (isInProcess()) {
				coffeeStage = 15 * currentProgress / totalProgress;
			}
			if (!this.inputTank.isEmpty())
				coffeeStage += 16;
			if (!DCUtil.isEmpty(getItem(0)))
				coffeeStage += 32;
			if (lastStage != coffeeStage && level instanceof ServerLevel) {
				MsgTileSimpleIntegerToC.sendToClient((ServerLevel) level, pos, coffeeStage);
			}

			CoffeeMakerBlock.changeLitState(getLevel(), getBlockPos(), isInProcess());

			return flag;
		}
	}

	public static boolean clientTick(Level level, BlockPos pos, BlockState state, CoffeeMakerTile tile) {
		RandomSource random = level.random;
		if (random.nextFloat() < 0.2F)
			if ((tile.coffeeStage & 15) > 0) {
				double d0 = pos.getX() + 0.5D;
				double d1 = pos.getY() + 0.5D;
				double d2 = pos.getZ() + 0.5D;
				level.addParticle(CoreInit.SMOKE_SMALL.get(), d0, d1 + 0.2D, d2, 0.0D, 0.01D, 0.0D);
				if (tile.coffeeStage < 12) {
					float dl = random.nextFloat() * 0.1F;
					level.playSound(null, d0, d1, d2, SoundEvents.ITEM_PICKUP, SoundSource.AMBIENT, 1.0F, 1.0F);
				}
			}
		return false;
	}

	// int

	@Override
	public void receiveInteger(int i) {
		coffeeStage = i;
	}

	// fluid

	@Override
	public int getTanks() {
		return 1;
	}

	@Override
	public DCTank getTank(int id) {
		return inputTank;
	}

	@Override
	public DCTank getTank(Direction dir) {
		return inputTank;
	}

	// nbt

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		if (tag.contains(TagKeyDC.getTankKey(1), 10)) {
			CompoundTag tankTag1 = tag.getCompound(TagKeyDC.getTankKey(1));
			inputTank.readFromNBT(tankTag1);
		}
		coffeeStage = tag.getInt(TagKeyDC.LAST_PROGRESS);
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		CompoundTag tankTag1 = new CompoundTag();
		inputTank.writeToNBT(tankTag1);
		tag.put(TagKeyDC.getTankKey(1), tankTag1);
		tag.putInt(TagKeyDC.LAST_PROGRESS, coffeeStage);
	}

	// cap

	LazyOptional<? extends IFluidHandler> fluidhandler = LazyOptional.of(() -> inputTank);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && capability == ForgeCapabilities.FLUID_HANDLER) {
			return fluidhandler.cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		fluidhandler.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		this.fluidhandler = LazyOptional.of(() -> inputTank);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.coffee_maker.with_owner", this.ownerName) : Component.translatable("dcs.container.coffee_maker");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new CoffeeMakerMenu(MachineInit.COFFEE_MAKER_MENU.get(), i, inv, this, this.dataAccess);
	}

}
