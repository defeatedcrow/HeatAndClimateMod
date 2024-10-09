package defeatedcrow.hac.machine.material.block.machine;

import java.util.Optional;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.api.recipe.RecipeTypeDC;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.network.packet.message.IIntReceiver;
import defeatedcrow.hac.core.network.packet.message.MsgTileFluidToC;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.event.CraftingFoodEvent;
import defeatedcrow.hac.machine.client.gui.RollCrusherMenu;
import defeatedcrow.hac.machine.energy.SidedEnergyReceiver;
import defeatedcrow.hac.machine.energy.SidedEnergyTankDC;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCTank;
import defeatedcrow.hac.machine.material.fluid.IFluidTankTileDC;
import defeatedcrow.hac.machine.material.fluid.SidedFluidWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class RollCrusherTile extends EnergyProcessTile implements IFluidTankTileDC, IRenderBlockData, IIntReceiver {

	public final ContainerData crusherDataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return RollCrusherTile.this.currentProgress;
			case 1:
				return RollCrusherTile.this.currentRate;
			case 2:
				return RollCrusherTile.this.getEnergyHandler().getEnergyStored();
			case 3:
				return RollCrusherTile.this.errorData[0];
			case 4:
				return RollCrusherTile.this.errorData[1];
			case 5:
				return RollCrusherTile.this.errorData[2];
			case 6:
				return RollCrusherTile.this.errorData[3];
			case 7:
				return RollCrusherTile.this.errorData[4];
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				RollCrusherTile.this.currentProgress = data;
				break;
			case 1:
				RollCrusherTile.this.currentRate = data;
				break;
			case 2:
				RollCrusherTile.this.clientEnergy = data;
				break;
			case 3:
				RollCrusherTile.this.errorData[0] = data;
				break;
			case 4:
				RollCrusherTile.this.errorData[1] = data;
				break;
			case 5:
				RollCrusherTile.this.errorData[2] = data;
				break;
			case 6:
				RollCrusherTile.this.errorData[3] = data;
				break;
			case 7:
				RollCrusherTile.this.errorData[4] = data;
				break;
			}

		}

		@Override
		public int getCount() {
			return 8;
		}
	};

	public RollCrusherTile(BlockPos pos, BlockState state) {
		this(MachineInit.CRUSHER_TILE.get(), pos, state);
	}

	public RollCrusherTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		totalProgress = maxProgressTime();
	}

	int count = 4;
	private int lastHash = 0;
	private int lastHash2 = 0;
	private int[] errorData = new int[5];

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = 4;

			boolean flag = false;
			if (!DCUtil.isEmpty(this.inventory.getItem(outtankS1)) && !this.inventory.isMaxStack(11)) {
				ItemStack copy = this.inventory.getItem(outtankS1).copy();
				copy.setCount(1);
				flag = FluidUtil.getFluidHandler(copy)
						.map(handler -> {
							FluidStack fluid = handler.getFluidInTank(0);
							if (fluid.isEmpty() || outputTank.isFull()) {
								int space = Math.min(outputTank.getFluidAmount(), handler.getTankCapacity(0));
								int d = handler.fill(outputTank.drain(space, FluidAction.SIMULATE), FluidAction.EXECUTE);
								if (d > 0 && inventory.canInsertResult(handler.getContainer(), outtankS2, outtankS2) != 0) {
									// drain
									outputTank.drain(d, FluidAction.EXECUTE);
									ItemStack ret = handler.getContainer();
									if (!ret.isEmpty()) {
										ret.setCount(1);
										inventory.incrStackInSlot(outtankS2, ret);
									}
									inventory.removeItem(outtankS1, 1);
									return true;
								}
							} else if (handler.isFluidValid(TANK_CAP, fluid)) {
								FluidStack drain = handler.drain(fluid, FluidAction.SIMULATE);
								int f = outputTank.fill(drain, FluidAction.SIMULATE);
								if (f > 0 && inventory.canInsertResult(handler.getContainer(), outtankS2, outtankS2) != 0) {
									// fill
									drain.setAmount(f);
									outputTank.fill(drain, FluidAction.EXECUTE);
									handler.drain(drain, FluidAction.EXECUTE);
									ItemStack ret = handler.getContainer().copy();
									if (!ret.isEmpty()) {
										ret.setCount(1);
										inventory.incrStackInSlot(outtankS2, ret);
									}
									inventory.removeItem(outtankS1, 1);
									return true;
								}
							}
							return false;
						}).orElse(false);
			}

			if (lastHash != currentProgress) {
				lastHash = currentProgress;
				if (level instanceof ServerLevel)
					MsgTileSimpleIntegerToC.sendToClient((ServerLevel) level, pos, currentProgress);
			}

			int hash2 = outputTank.getFluid().hashCode() + outputTank.getFluidAmount();
			if (lastHash2 != hash2) {
				lastHash2 = hash2;
				this.setChanged(level, pos, state);
				NonNullList<FluidStack> list = NonNullList.withSize(3, FluidStack.EMPTY);
				list.set(0, outputTank.getFluid());
				MsgTileFluidToC.sendToClient((ServerLevel) level, pos, list);
			}
		}
		return super.onTickProcess(level, pos, state);
	}

	public int rotation = 0;

	public static void clientTick(Level level, BlockPos pos, BlockState state, RollCrusherTile tile) {
		if (tile.currentProgress > 0) {
			tile.rotation++;
			if (tile.rotation > 360) {
				tile.rotation -= 360;
			}
		}
	}

	@Override
	public void receiveInteger(int i) {
		currentProgress = i;
	}

	/* inventory */

	@Override
	public int getContainerSize() {
		return 10;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 0 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 3, 4, 5, 6, 7, 8, 9 };
	}

	@Override
	protected int[] getSideSlots() {
		return new int[] { 0, 3, 4, 5, 6, 7, 8, 9 };
	}

	protected int maxInSlot() {
		return 0;
	}

	protected int maxProgressTime() {
		return 640;
	}

	protected int catalystS = 1;
	protected int outtankS1 = 2;
	protected int outtankS2 = 3;

	/* DeviceRecipe */

	@Override
	public boolean isInProcess() {
		return recipe != null;
	}

	protected int[] consume = new int[0];

	private NonNullList<ItemStack> getResults(boolean random) {
		NonNullList<ItemStack> ret = NonNullList.withSize(4, ItemStack.EMPTY);
		if (recipe != null && consume != null) {
			NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
			// container優先
			if (consume.length > 0) {
				for (int i = 0; i < consume.length && i < inputs.size(); i++) {
					if (!inputs.get(i).isEmpty()) {
						ItemStack check = inputs.get(i).copy();
						check.setCount(1);
						if (!check.getCraftingRemainingItem().isEmpty()) {
							ret.set(0, check.getCraftingRemainingItem().copy());
						} else if (FluidUtil.getFluidContained(check).isPresent()) {
							ItemStack cont = FluidUtil.getFluidHandler(check)
									.map(handler -> {
										FluidStack fluid = handler.getFluidInTank(0);
										if (!fluid.isEmpty()) {
											handler.drain(fluid, FluidAction.EXECUTE);
											return handler.getContainer().copy();
										}
										return ItemStack.EMPTY;
									}).orElse(ItemStack.EMPTY);
							if (!DCUtil.isEmpty(cont)) {
								ret.set(0, cont);
							}
						}
					}
				}
			}
			if ((recipe.getType() != RecipeTypeDC.SQUEEZE || recipe.getOutputFluid().isEmpty())) {
				ret.set(1, recipe.getOutput()); // squeezeはPrimaryがない
			}
			if (recipe.getSecondaryRate() > 0 && (!random || level.random.nextInt(100) < recipe.getSecondaryRate())) {
				ret.set(2, recipe.getSecondaryOutput());
			}
			if (recipe.getTertiaryRate() > 0 && (!random || level.random.nextInt(100) < recipe.getTertiaryRate())) {
				ret.set(3, recipe.getTertiaryOutput());
			}
		}
		return ret;
	}

	private Optional<IDeviceRecipe> getNextRecipe() {
		NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
		ItemStack cat = this.inventory.getItem(catalystS);
		if (!DCUtil.isEmpty(cat)) {
			return DCRecipes.getCrusherRecipe(inputs, cat);
		}
		return Optional.empty();
	}

	@Override
	public boolean continueProcess(Level level, BlockPos pos, BlockState state) {
		// priority check
		if (recipe != null) {
			Optional<IDeviceRecipe> check = getNextRecipe();

			if (check.isPresent() && recipe.getPriority() >= check.get().getPriority()) {
				boolean result = true;
				NonNullList<ItemStack> outputs = getResults(false);
				for (int i = 0; i < 4; i++) {
					ItemStack out = outputs.get(i);
					if (inventory.canInsertResult(out, 4, 9) == 0) {
						result = false;
						errorData[i] = 1;
					} else {
						errorData[i] = 0;
					}
				}
				if (outputTank.fill(recipe.getOutputFluid(), FluidAction.SIMULATE) < recipe.getOutputFluid().getAmount()) {
					result = false;
					errorData[4] = 1;
				} else {
					outputTank.fill(recipe.getOutputFluid(), FluidAction.EXECUTE);
					errorData[4] = 0;
				}
				return result;
			}
		}
		return false;
	}

	@Override
	public int consumeEnergy() {
		int e = Math.min(getEnergyHandler().getFlowRate(), totalProgress - currentProgress);
		return getEnergyHandler().consumeEnergy(e);
	}

	@Override
	public boolean finishProcess(Level level, BlockPos pos, BlockState state) {
		NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
		consume = recipe.matcheInput(inputs);
		if (recipe != null) {
			boolean flag = false;
			NonNullList<ItemStack> outputs = getResults(true);
			for (int i = 0; i < 4; i++) {
				ItemStack res = outputs.get(i);
				if (!res.isEmpty() && res.getItem() instanceof IFoodTaste food) {
					int taste = CraftingFoodEvent.getResultTaste(inputs, consume);
					food.setTaste(res, taste);
				}
				if (!res.isEmpty() && res.isEdible()) {
					boolean unsafe = CraftingFoodEvent.checkUnsafe(inputs, consume);
					if (unsafe) {
						CompoundTag tag = res.getOrCreateTag();
						tag.putBoolean(TagKeyDC.UNSAFE, true);
						res.setTag(tag);
					}
				}
				if (!DCUtil.isEmpty(res) && inventory.insertResult(res, 4, 9) > 0) {
					flag = true;
				}
			}
			if (outputTank.fill(recipe.getOutputFluid(), FluidAction.EXECUTE) > 0) {
				flag = true;
			}
			if (flag) {
				this.setChanged(level, pos, state);
			}
			return flag;
		}
		return false;
	}

	@Override
	public boolean consumeInputs() {
		if (consume.length > 0) {
			for (int i = 0; i < consume.length; i++) {
				inventory.removeItem(i, consume[i]);
			}
		}
		return false;
	}

	@Override
	public boolean resetProcess() {
		recipe = null;
		consume = new int[0];
		this.totalProgress = 0;
		this.currentProgress = 0;
		this.lastProgress = 0;
		return true;
	}

	@Override
	public boolean startProcess(Level level, BlockPos pos, BlockState state) {
		Optional<IDeviceRecipe> check = getNextRecipe();
		if (check.isPresent()) {
			recipe = check.get();
			this.totalProgress = maxProgressTime();
			// for (int i = 0; i < 5; i++) {
			// errorData[i] = 0;
			// }
			return true;
		}
		return false;
	}

	/* battery */

	public SidedEnergyTankDC battery = new SidedEnergyReceiver(this, getMaxEnergy(), 128);

	protected int getMaxEnergy() {
		return 16000;
	}

	@Override
	public SidedEnergyTankDC getEnergyHandler() {
		return battery;
	}

	/* fluid */

	public static final int TANK_CAP = 4000;
	// dummy
	public DCTank inputTank = new DCTank(0);
	public DCTank outputTank = new DCTank(TANK_CAP);

	@Override
	public int getTanks() {
		return 1;
	}

	@Override
	public DCTank getTank(int id) {
		return outputTank;
	}

	@Override
	public DCTank getTank(Direction dir) {
		return outputTank;
	}

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		if (tag.contains(TagKeyDC.getTankKey(1), 10)) {
			CompoundTag tankTag1 = tag.getCompound(TagKeyDC.getTankKey(1));
			outputTank.readFromNBT(tankTag1);
		}
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		CompoundTag tankTag1 = new CompoundTag();
		outputTank.writeToNBT(tankTag1);
		tag.put(TagKeyDC.getTankKey(1), tankTag1);
	}

	LazyOptional<? extends IFluidHandler> fluidhandler = LazyOptional.of(() -> new SidedFluidWrapper(inputTank, outputTank));

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
		this.fluidhandler = LazyOptional.of(() -> new SidedFluidWrapper(inputTank, outputTank));
	}

	/* menu */

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new RollCrusherMenu(MachineInit.CRUSHER_MENU.get(), i, inv, this, crusherDataAccess);
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		return NORMAL;
	}

	public static final EntityRenderData NORMAL = new EntityRenderData("tile/roll_crusher", 1F, -0.5F);

}
