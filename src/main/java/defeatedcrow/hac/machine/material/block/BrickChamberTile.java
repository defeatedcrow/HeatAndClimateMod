package defeatedcrow.hac.machine.material.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;

public class BrickChamberTile extends HeatingChamberTile {

	public BrickChamberTile(BlockPos pos, BlockState state) {
		super(MachineInit.CHAMBER_BRICK_TILE.get(), pos, state);
	}

	@Override
	public DCHeatTier baseHeatTier() {
		return DCHeatTier.OVEN;
	}

	@Override
	protected int getFuel(ItemStack item) {
		ItemStack check = item.copy();
		check.setCount(1);
		int fuel = ForgeHooks.getBurnTime(check, RecipeType.SMELTING) * 2;
		return fuel;
	}

}
