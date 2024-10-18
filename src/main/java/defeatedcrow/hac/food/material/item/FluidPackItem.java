package defeatedcrow.hac.food.material.item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.machine.material.fluid.DCFluidUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class FluidPackItem extends FoodMaterialItemDC {

	private final Type type;

	public FluidPackItem(CreativeModeTab tab, String s, TagKey<Item> pair, Type t) {
		super(tab, s, pair);
		type = t;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (type != null && type != EMPTY) {
			FluidStack fluid = type.fluid().get();
			if (fluid.isEmpty()) {
				MutableComponent com = Component.literal("EMPTY").withStyle(ChatFormatting.GRAY);
				list.add(com);
			} else {
				MutableComponent com = Component.literal("FLUID: ");
				com.append(fluid.getDisplayName()).append(" " + fluid.getAmount() + "mB").withStyle(ChatFormatting.AQUA);
				list.add(com);
			}
		}
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag tag) {
		return new FluidItemWrapperDisposable(stack, type);
	}

	public static Type EMPTY = new Type(FoodInit.FOOD_EMPTY_PACK, () -> FluidStack.EMPTY);
	public static Type WATER = new Type(FoodInit.FOOD_WATER, () -> new FluidStack(Fluids.WATER, 250));
	public static Type SPARKLING = new Type(FoodInit.FOOD_SPARKLING, () -> new FluidStack(CoreInit.SPARKLING.getStillFluid().get(), 250));
	public static Type MILK = new Type(FoodInit.FOOD_MILK, () -> new FluidStack(ForgeMod.MILK.get(), 250));
	public static Type PLANT_OIL = new Type(FoodInit.FOOD_PLANT_OIL, () -> new FluidStack(CoreInit.PLANT_OIL.getStillFluid().get(), 250));

	public static List<Type> TYPES = ImmutableList.of(WATER, SPARKLING, MILK, PLANT_OIL);

	public record Type(
			Supplier<Item> cont,
			Supplier<FluidStack> fluid) {}

	// Drain Only
	public class FluidItemWrapperDisposable implements IFluidHandlerItem, ICapabilityProvider {

		@NotNull
		protected final FluidPackItem.Type type;
		@NotNull
		protected ItemStack container;

		public FluidItemWrapperDisposable(@NotNull ItemStack cont, FluidPackItem.Type t) {
			container = cont;
			type = t;
		}

		@Override
		public @NotNull ItemStack getContainer() {
			return container;
		}

		@Override
		public int getTanks() {
			return 1;
		}

		@Override
		public @NotNull FluidStack getFluidInTank(int i) {
			if (type == null || type == FluidPackItem.EMPTY)
				return FluidStack.EMPTY;

			return type.fluid().get();
		}

		@Override
		public int getTankCapacity(int tank) {
			return type.fluid().get().getAmount();
		}

		@Override
		public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
			if (type == FluidPackItem.EMPTY || !stack.isEmpty()) {
				if (stack.getFluid().isSame(type.fluid().get().getFluid())) {
					return true;
				}
			}
			return false;
		}

		@Override
		public int fill(FluidStack fill, FluidAction action) {
			return 0;
		}

		@Override
		public @NotNull FluidStack drain(FluidStack drain, FluidAction action) {
			FluidStack target = getFluidInTank(1);
			if (drain.isEmpty() || !DCFluidUtil.isSameFluid(target, drain)) {
				return FluidStack.EMPTY;
			}
			return drain(drain.getAmount(), action);
		}

		@Override
		public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
			FluidStack target = getFluidInTank(1);
			if (maxDrain < 250 || target.isEmpty()) {
				return FluidStack.EMPTY;
			}

			FluidStack ret = target.copy();
			ret.setAmount(250);

			if (action.execute()) {
				container.shrink(1);
			}

			return ret;
		}

		private final LazyOptional<IFluidHandlerItem> holder = LazyOptional.of(() -> this);

		@Override
		@NotNull
		public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
			return ForgeCapabilities.FLUID_HANDLER_ITEM.orEmpty(capability, holder);
		}

	}

}
