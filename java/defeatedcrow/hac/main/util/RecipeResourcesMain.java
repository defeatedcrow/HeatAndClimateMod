package defeatedcrow.hac.main.util;

import defeatedcrow.hac.main.ClimateMain;
import net.minecraft.util.ResourceLocation;

public class RecipeResourcesMain {

	private final String domein;
	private int count = 0;

	public RecipeResourcesMain(String d) {
		domein = d;
	}

	public static final RecipeResourcesMain MAIN = new RecipeResourcesMain("main");

	public ResourceLocation getRecipeName() {
		count++;
		return new ResourceLocation(ClimateMain.MOD_ID, domein + "_" + count);
	}

}
