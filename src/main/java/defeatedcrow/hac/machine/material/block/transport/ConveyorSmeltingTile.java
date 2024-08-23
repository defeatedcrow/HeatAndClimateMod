package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.state.BlockState;

public class ConveyorSmeltingTile extends ConveyorTile {

	private final RecipeManager.CachedCheck<Container, SmeltingRecipe> quickCheck;

	public ConveyorSmeltingTile(BlockPos pos, BlockState state) {
		super(MachineInit.CONVEYOR_SMELTING_TILE.get(), pos, state);
		this.quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
	}

	@Override
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
				if (DCRecipes.hasAnyHeatTreatmentRecipe(target.getItem()).isPresent()) {
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
				} else {
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
		}
		return true;
	}
}
