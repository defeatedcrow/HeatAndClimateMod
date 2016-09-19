package defeatedcrow.hac.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
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
		return "HeatAndCimate:Machines";
	}

	@Override
	public Item getTabIconItem() {
		return MainInit.wrench;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getIconItemDamage() {
		return 0;
	}

}
