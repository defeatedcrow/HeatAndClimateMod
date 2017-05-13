package defeatedcrow.hac.plugin;

import defeatedcrow.hac.food.gui.GuiFluidProcessor;
import defeatedcrow.hac.food.gui.GuiTeaPot;
import defeatedcrow.hac.machine.gui.GuiReactor;
import defeatedcrow.hac.machine.gui.GuiStoneMill;
import defeatedcrow.hac.main.config.ModuleConfig;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;

@JEIPlugin
public class DCsJEIPlugin2 implements IModPlugin {

	private IJeiHelpers helper;

	@Override
	public void register(IModRegistry registry) {
		if (ModuleConfig.machine) {
			registry.addRecipeClickArea(GuiStoneMill.class, 80, 32, 16, 16, new String[] {
					"dcs_climate.mill"
			});

			registry.addRecipeClickArea(GuiReactor.class, 120, 18, 16, 16, new String[] {
					"dcs_climate.reactor"
			});
		}

		if (ModuleConfig.food) {
			registry.addRecipeClickArea(GuiFluidProcessor.class, 80, 35, 16, 16, new String[] {
					"dcs_climate.fluidcraft"
			});

			registry.addRecipeClickArea(GuiTeaPot.class, 80, 35, 16, 16, new String[] {
					"dcs_climate.fluidcraft"
			});
		}
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {}

	@Override
	public void registerIngredients(IModIngredientRegistration registry) {}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {}

}
