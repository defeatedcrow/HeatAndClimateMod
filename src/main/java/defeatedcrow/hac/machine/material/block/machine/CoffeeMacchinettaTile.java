package defeatedcrow.hac.machine.material.block.machine;

import defeatedcrow.hac.api.material.IFoodTaste;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.food.event.CraftingFoodEvent;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * コーヒーのみを扱う特殊なプロセスタイル
 */
public class CoffeeMacchinettaTile extends CoffeeMakerTile {

	public CoffeeMacchinettaTile(BlockPos pos, BlockState state) {
		this(MachineInit.COFFEE_MACCHINETTA_TILE.get(), pos, state);
	}

	public CoffeeMacchinettaTile(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	/* レシピは固定 */

	@Override
	protected ItemStack getOutputItem() {
		ItemStack res = new ItemStack(FoodInit.ESPRESSO.get());
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

}
