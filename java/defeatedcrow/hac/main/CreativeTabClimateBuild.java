package defeatedcrow.hac.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabClimateBuild extends CreativeTabs {

	// クリエイティブタブのアイコン画像や名称の登録クラス
	public CreativeTabClimateBuild(String type) {
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "HeatAndClimate:Buildings";
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(MainInit.bricks);
	}

}
