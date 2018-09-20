package defeatedcrow.hac.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabClimateMachine extends CreativeTabs {

	// クリエイティブタブのアイコン画像や名称の登録クラス
	public CreativeTabClimateMachine(String type) {
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "HeatAndClimate:Machines";
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(MainInit.wrench);
	}

}
