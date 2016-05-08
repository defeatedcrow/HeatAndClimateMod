package defeatedcrow.hac.main.client;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import defeatedcrow.hac.core.client.JsonBakery;
import defeatedcrow.hac.main.CommonMainProxy;
import defeatedcrow.hac.main.block.container.BlockCropCont;
import defeatedcrow.hac.main.block.container.BlockEnemyCont;
import defeatedcrow.hac.main.block.container.BlockLogCont;
import defeatedcrow.hac.main.block.container.BlockMiscCont;
import defeatedcrow.hac.main.block.device.TileNormalChamber;
import defeatedcrow.hac.main.client.block.TESRNormalChamber;
import defeatedcrow.hac.main.event.AltTooltipEvent;

public class ClientMainProxy extends CommonMainProxy {

	@Override
	public void loadConst() {
		JsonBakery.instance.addTex(BlockLogCont.getTexList());
		JsonBakery.instance.addTex(BlockCropCont.getTexList());
		JsonBakery.instance.addTex(BlockEnemyCont.getTexList());
		JsonBakery.instance.addTex(BlockMiscCont.getTexList());
	}

	@Override
	public void loadMaterial() {
		super.loadMaterial();
		JsonRegister.load();
	}

	@Override
	public void loadTE() {
		super.loadTE();
		ClientRegistry.bindTileEntitySpecialRenderer(TileNormalChamber.class, new TESRNormalChamber());
	}

	@Override
	public void loadInit() {
		super.loadInit();
		MinecraftForge.EVENT_BUS.register(new AltTooltipEvent());
	}

	@Override
	public void addSidedBlock(Block block) {
		JsonBakery.instance.regDummySidedModel(block);
	}

	@Override
	public void addTBBlock(Block block) {
		JsonBakery.instance.regDummyTBModel(block);
	}

	/**
	 * メタ無しJson製Block。一部の階段・ハーフにのみ使用している
	 */
	@Override
	public void regBlockJson(Item item, String domein, String name, String dir, int max, boolean f) {
		int m = 0;
		if (max == 0) {
			ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(domein + ":" + dir + "/" + name, "inventory"));
		} else {
			while (m < max + 1) {
				if (f) {
					ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(domein + ":" + dir + "/" + name + m,
							"inventory"));
				} else {
					ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(domein + ":" + dir + "/" + name,
							"inventory"));
				}
				m++;
			}
		}
	}

}
