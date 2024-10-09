package defeatedcrow.hac.machine.material.block.machine;

import java.util.Optional;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.network.packet.message.IIntReceiver;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.event.CraftingFoodEvent;
import defeatedcrow.hac.machine.client.gui.MillMenu;
import defeatedcrow.hac.machine.energy.SidedEnergyReceiver;
import defeatedcrow.hac.machine.energy.SidedEnergyTankDC;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class StoneMillTile extends EnergyProcessTile implements IRenderBlockData, IIntReceiver {

	public StoneMillTile(BlockPos pos, BlockState state) {
		this(MachineInit.MILL_TILE.get(), pos, state);
	}

	public StoneMillTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		totalProgress = maxProgressTime();
	}

	int count = 4;
	private int lastHash = 0;

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
		} else {
			count = 4;

			if (lastHash != currentProgress) {
				lastHash = currentProgress;
				if (level instanceof ServerLevel)
					MsgTileSimpleIntegerToC.sendToClient((ServerLevel) level, pos, currentProgress);
			}
		}
		return super.onTickProcess(level, pos, state);
	}

	public int rotation = 0;

	public static void clientTick(Level level, BlockPos pos, BlockState state, StoneMillTile tile) {
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
		return 5;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 0 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 1, 2, 3, 4 };
	}

	@Override
	protected int[] getSideSlots() {
		return new int[] { 0, 1, 2, 3, 4 };
	}

	protected int maxInSlot() {
		return 0;
	}

	protected int maxProgressTime() {
		return 320;
	}

	/* DeviceRecipe */

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
										return ItemStack.EMPTY;
									}).orElse(ItemStack.EMPTY);
							if (!DCUtil.isEmpty(ret)) {
								return ret;
							}
						}
					}
				}
			}
			// Millの場合はセカンダリ確率が半分になる
			if (recipe.getSecondaryRate() > 0 && level.random.nextInt(200) < recipe.getSecondaryRate()) {
				return recipe.getSecondaryOutput().copy();
			}
		}
		return ItemStack.EMPTY;
	}

	protected int maxOutSlot() {
		return 4;
	}

	@Override
	public boolean continueProcess(Level level, BlockPos pos, BlockState state) {
		// priority check
		if (recipe != null) {
			NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
			Optional<IDeviceRecipe> check = DCRecipes.getMillRecipe(inputs);

			if (check.isPresent()) {
				ItemStack ret = recipe.getOutput().copy();
				if (ret.getCount() > 4)
					ret.shrink(2);
				else if (ret.getCount() > 1)
					ret.shrink(1);
				boolean result = inventory.canInsertResult(recipe.getOutput(), maxInSlot() + 1, maxOutSlot()) > 0;
				if (recipe.getSecondaryRate() > 0 && inventory.canInsertResult(recipe.getSecondaryOutput(), maxInSlot() + 1, maxOutSlot()) == 0) {
					result = false;
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
			ItemStack res = recipe.getOutput();
			if (res.getCount() > 4)
				res.shrink(2);
			else if (res.getCount() > 1)
				res.shrink(1);
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
			if (!DCUtil.isEmpty(res) && inventory.insertResult(res, maxInSlot() + 1, maxOutSlot()) > 0) {
				flag = true;
			}
			ItemStack sec = getSecondaryOrContainer();
			if (!DCUtil.isEmpty(sec) && inventory.insertResult(sec, maxInSlot() + 1, maxOutSlot()) > 0) {
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
		NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
		Optional<IDeviceRecipe> check = DCRecipes.getMillRecipe(inputs);
		if (check.isPresent()) {
			recipe = check.get();
			this.totalProgress = maxProgressTime();
			return true;
		}
		return false;
	}

	/* battery */

	public SidedEnergyTankDC battery = new SidedEnergyReceiver(this, getMaxEnergy(), 32);

	protected int getMaxEnergy() {
		return 4000;
	}

	@Override
	public SidedEnergyTankDC getEnergyHandler() {
		return battery;
	}

	/* menu */

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new MillMenu(MachineInit.MILL_MENU.get(), i, inv, this, this.dataAccess);
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		return NORMAL;
	}

	public static final EntityRenderData NORMAL = new EntityRenderData("tile/stone_mill", 1F, -0.5F);

}
