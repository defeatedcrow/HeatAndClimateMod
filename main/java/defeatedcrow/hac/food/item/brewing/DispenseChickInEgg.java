package defeatedcrow.hac.food.item.brewing;

import javax.annotation.Nonnull;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DispenseChickInEgg extends BehaviorDefaultDispenseItem {

	private static final DispenseChickInEgg INSTANCE = new DispenseChickInEgg();

	public static DispenseChickInEgg getInstance() {
		return INSTANCE;
	}

	private DispenseChickInEgg() {}

	private final BehaviorDefaultDispenseItem dispenseBehavior = new BehaviorDefaultDispenseItem();

	@Override
	@Nonnull
	public ItemStack dispenseStack(@Nonnull IBlockSource source, @Nonnull ItemStack stack) {
		if (!DCUtil.isEmpty(stack) && stack.getItem() == FoodInit.chickInEgg) {
			return spawnItemEntity(source, stack);
		} else {
			return super.dispenseStack(source, stack);
		}
	}

	@Nonnull
	private ItemStack spawnItemEntity(@Nonnull IBlockSource source, @Nonnull ItemStack stack) {
		World world = source.getWorld();
		EnumFacing face = source.getBlockState().getValue(BlockDispenser.FACING);
		BlockPos pos = source.getBlockPos().offset(face);
		EntityChicken chicken = new EntityChicken(world);
		chicken.setGrowingAge(-24000);
		chicken.setLocationAndAngles(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, world.rand
				.nextFloat(), 0.0F);

		if (world.spawnEntity(chicken)) {
			DCUtil.reduceStackSize(stack, 1);
		}
		return stack;
	}

}
