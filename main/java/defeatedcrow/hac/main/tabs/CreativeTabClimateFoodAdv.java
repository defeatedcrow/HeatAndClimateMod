package defeatedcrow.hac.main.tabs;

import defeatedcrow.hac.core.CreativeTabClimate;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabClimateFoodAdv extends CreativeTabClimate {

	// クリエイティブタブのアイコン画像や名称の登録クラス
	public CreativeTabClimateFoodAdv(String type) {
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "HeatAndClimate:Foods_Advanced";
	}

	@Override
	public ItemStack getTabIconItem() {
		return ModuleConfig.food_advanced ? new ItemStack(FoodInit.unidentified, 1, 1) : new ItemStack(
				MainInit.bakedApple, 1, 1);
	}

}
