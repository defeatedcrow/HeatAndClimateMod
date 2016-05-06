package defeatedcrow.hac.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabClimateTool extends CreativeTabs {

	// クリエイティブタブのアイコン画像や名称の登録クラス
	public CreativeTabClimateTool(String type) {
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "HeatAndCimate:Tools";
	}

	@Override
	public Item getTabIconItem() {
		return MainInit.dcPickaxe[0];
	}

}
