package defeatedcrow.hac.food.material.entity;

import java.util.Optional;

import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.core.material.entity.ObjectEntityBaseDC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class FoodEntityBase extends ObjectEntityBaseDC {

	private int count = 0;
	private IClimateSmelting currentRecipe;

	public FoodEntityBase(EntityType<? extends FoodEntityBase> type, Level level) {
		super(type, level);
	}

	public boolean isRaw() {
		return !getItem().isEmpty() && getItem().is(TagDC.ItemTag.RAW_FOOD);
	}

	@Override
	public void tick() {
		super.tick();

		if (isRaw()) {
			ClimateSupplier supplier = new ClimateSupplier(level, blockPosition());
			IClimate clm = supplier.get();

			if (currentRecipe != null) {
				if (currentRecipe.matcheInput(getItem()) && currentRecipe.matchClimate(clm) && currentRecipe.additionalRequire(getLevel(), blockPosition())) {
					if (count > 0) {
						count--;
					} else if (count <= 0) {
						this.setItem(currentRecipe.getOutput().copy());
						level.playSound(null, this, SoundEvents.LAVA_EXTINGUISH, SoundSource.AMBIENT, 1.0F, 1.0F);
						level.addParticle(ParticleTypes.SMOKE, this.getRandomX(0.1D), this.getRandomY() * 0.5D, this.getRandomZ(0.1D), 0D, 0.003D, 0D);
					}

				} else {
					currentRecipe = null;
					count = 0;
				}
			} else {
				Optional<IClimateSmelting> recipe = DCRecipes.getSmeltingRecipe(clm, getItem());
				if (recipe.isPresent()) {
					currentRecipe = recipe.get();
					count = recipe.get().recipeFrequency();
				}
			}
		}
	}

}
