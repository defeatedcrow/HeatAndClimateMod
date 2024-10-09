package defeatedcrow.hac.machine.material.block.machine;

import java.util.Optional;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.network.packet.message.MsgTileFluidToC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.event.CraftingFoodEvent;
import defeatedcrow.hac.machine.client.gui.FermentationJarMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCTank;
import defeatedcrow.hac.machine.material.fluid.IFluidTankTileDC;
import defeatedcrow.hac.machine.material.fluid.SidedFluidWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class FermentationJarTile extends ProcessTileBaseDC implements IFluidTankTileDC, IRenderBlockData {

	public FermentationJarTile(BlockPos pos, BlockState state) {
		this(MachineInit.FERMANTATION_JAR_TILE.get(), pos, state);
	}

	public FermentationJarTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		totalProgress = maxProgressTime();
	}

	/* inventory */

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return FermentationJarTile.this.currentProgress;
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				FermentationJarTile.this.currentProgress = data;
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
		return 9;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 0, 1, 2, 5, 7 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 3, 4, 6, 8 };
	}

	@Override
	protected int[] getSideSlots() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	}

	protected int intankS1 = maxInSlot() + 3;
	protected int intankS2 = maxInSlot() + 4;
	protected int outtankS1 = maxInSlot() + 5;
	protected int outtankS2 = maxInSlot() + 6;

	protected int maxInSlot() {
		return 2;
	}

	protected int maxProgressTime() {
		return 300;
	}

	/* DeviceRecipe */

	public static final int TANK_CAP = 4000;
	public DCTank inputTank = new DCTank(TANK_CAP);
	public DCTank outputTank = new DCTank(TANK_CAP);

	@Override
	public boolean isInProcess() {
		return recipe != null;
	}

	protected int[] consume = new int[0];

	private ItemStack getSecondaryOrContainer() {
		if (recipe != null && consume != null) {
			NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
			// container優先
			if (consume.length > 0) {
				for (int i = 0; i < consume.length && i < inputs.size(); i++) {
					if (!inputs.get(i).isEmpty()) {
						ItemStack check = inputs.get(i).copy();
						check.setCount(1);
						if (!check.getCraftingRemainingItem().isEmpty()) {
							return check.getCraftingRemainingItem().copy();
						} else if (FluidUtil.getFluidContained(check).isPresent()) {
							ItemStack ret = FluidUtil.getFluidHandler(check)
									.map(handler -> {
										FluidStack fluid = handler.getFluidInTank(0);
										if (!fluid.isEmpty()) {
											handler.drain(fluid, FluidAction.EXECUTE);
											return handler.getContainer().copy();
										}
										return handler.getContainer().copy();
									}).orElse(ItemStack.EMPTY);
							if (!DCUtil.isEmpty(ret)) {
								return ret;
							}
						}
					}
				}
			}
			if (recipe.getSecondaryRate() > 0 && level.random.nextInt(100) < recipe.getSecondaryRate()) {
				return recipe.getSecondaryOutput().copy();
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public boolean continueProcess(Level level, BlockPos pos, BlockState state) {
		// priority check
		if (recipe != null) {
			NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
			Optional<IDeviceRecipe> check = DCRecipes.getFermentationRecipe(currentClimate, inputs, inputTank.getFluid());

			if (check.isPresent() && check.get().getPriority() == recipe.getPriority()) {
				boolean result = inventory.canInsertResult(recipe.getOutput(), maxInSlot() + 1, maxInSlot() + 2) > 0 && outputTank.fill(recipe.getOutputFluid(), FluidAction.SIMULATE) >= recipe
						.getOutputFluid()
						.getAmount();
				if (recipe.getSecondaryRate() > 0 && inventory.canInsertResult(recipe.getSecondaryOutput(), maxInSlot() + 1, maxInSlot() + 2) == 0) {
					result = false;
				}
				return result;
			}
		}
		return false;
	}

	@Override
	public boolean finishProcess(Level level, BlockPos pos, BlockState state) {
		NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
		consume = recipe.matcheInput(inputs);
		if (recipe != null) {
			boolean flag = false;
			ItemStack res = recipe.getOutput();
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
			if (!DCUtil.isEmpty(res) && inventory.insertResult(res, maxInSlot() + 1, maxInSlot() + 2) > 0) {
				flag = true;
			}
			ItemStack sec = getSecondaryOrContainer();
			if (!DCUtil.isEmpty(sec) && inventory.insertResult(sec, maxInSlot() + 1, maxInSlot() + 2) > 0) {
				flag = true;
			}
			FluidStack outF = recipe.getOutputFluid();
			if (!outF.isEmpty() && outputTank.fill(recipe.getOutputFluid(), FluidAction.EXECUTE) > 0) {
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
		if (!recipe.getInputFluids().isEmpty()) {
			TagKey<Fluid> tag = recipe.getInputFluids().get(0);
			if (!inputTank.isEmpty() && inputTank.getFluidType().is(tag)) {
				inputTank.drain(1000, FluidAction.EXECUTE);
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
		NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
		Optional<IDeviceRecipe> check = DCRecipes.getFermentationRecipe(currentClimate, inputs, inputTank.getFluid());
		if (check.isPresent()) {
			recipe = check.get();
			this.totalProgress = maxProgressTime();
			return true;
		}
		return false;
	}

	int count = 4;
	private int lastHash1 = 0;

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
			return false;
		} else {
			count = 4;

			boolean flag = false;
			if (!DCUtil.isEmpty(this.inventory.getItem(intankS1)) && !this.inventory.isMaxStack(intankS2)) {
				ItemStack copy = this.inventory.getItem(intankS1).copy();
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
									ItemStack ret = handler.getContainer().copy();
									if (!ret.isEmpty()) {
										ret.setCount(1);
										inventory.incrStackInSlot(intankS2, ret);
									}
									inventory.removeItem(intankS1, 1);
									return true;
								}
							}
							return false;
						}).orElse(false);
			}

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

			int hash1 = inputTank.getFluid().hashCode() + inputTank.getFluidAmount();
			int hash2 = outputTank.getFluid().hashCode() + outputTank.getFluidAmount();
			if (lastHash1 != hash1 + hash2) {
				lastHash1 = hash1 + hash2;
				flag = true;
			}

			if (flag && level instanceof ServerLevel) {
				this.setChanged(level, pos, state);
				NonNullList<FluidStack> list = NonNullList.withSize(3, FluidStack.EMPTY);
				list.set(0, inputTank.getFluid());
				list.set(1, outputTank.getFluid());
				MsgTileFluidToC.sendToClient((ServerLevel) level, pos, list);
			}

			return flag;
		}
	}

	// fluid

	@Override
	public int getTanks() {
		return 2;
	}

	@Override
	public DCTank getTank(int id) {
		return id == 0 ? inputTank : outputTank;
	}

	@Override
	public DCTank getTank(Direction dir) {
		return dir == Direction.UP ? inputTank : outputTank;
	}

	// nbt

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		if (tag.contains(TagKeyDC.getTankKey(1), 10)) {
			CompoundTag tankTag1 = tag.getCompound(TagKeyDC.getTankKey(1));
			inputTank.readFromNBT(tankTag1);
		}
		if (tag.contains(TagKeyDC.getTankKey(2), 10)) {
			CompoundTag tankTag2 = tag.getCompound(TagKeyDC.getTankKey(2));
			outputTank.readFromNBT(tankTag2);
		}
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		CompoundTag tankTag1 = new CompoundTag();
		inputTank.writeToNBT(tankTag1);
		tag.put(TagKeyDC.getTankKey(1), tankTag1);
		CompoundTag tankTag2 = new CompoundTag();
		outputTank.writeToNBT(tankTag2);
		tag.put(TagKeyDC.getTankKey(2), tankTag2);
	}

	// cap

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

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new FermentationJarMenu(MachineInit.JAR_MENU.get(), i, inv, this, this.dataAccess);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.cooking.with_owner", this.ownerName) : Component.translatable("dcs.container.cooking");
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == MachineInit.FERMANTATION_JAR_BLUE.get())
			return BLUE;
		if (block == MachineInit.FERMANTATION_JAR_BLACK.get())
			return BLACK;
		if (block == MachineInit.FERMANTATION_JAR_RED.get())
			return RED;
		if (block == MachineInit.FERMANTATION_JAR_GREEN.get())
			return GREEN;
		if (block == MachineInit.FERMANTATION_JAR_WHITE.get())
			return WHITE;
		if (block == MachineInit.FERMANTATION_JAR_NORMAL.get())
			return NORMAL;
		return NORMAL;
	}

	public static final EntityRenderData WHITE = new EntityRenderData("tile/fermentation_jar_white", 1F, -0.5F);
	public static final EntityRenderData BLUE = new EntityRenderData("tile/fermentation_jar_blue", 1F, -0.5F);
	public static final EntityRenderData BLACK = new EntityRenderData("tile/fermentation_jar_black", 1F, -0.5F);
	public static final EntityRenderData RED = new EntityRenderData("tile/fermentation_jar_red", 1F, -0.5F);
	public static final EntityRenderData GREEN = new EntityRenderData("tile/fermentation_jar_green", 1F, -0.5F);
	public static final EntityRenderData NORMAL = new EntityRenderData("tile/fermentation_jar_normal", 1F, -0.5F);

}
