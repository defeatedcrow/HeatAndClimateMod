package defeatedcrow.hac.main.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.JsonBakery;
import defeatedcrow.hac.food.client.BeefStickRenderer;
import defeatedcrow.hac.food.client.FishStickRenderer;
import defeatedcrow.hac.food.client.PorkStickRenderer;
import defeatedcrow.hac.food.client.RoundBreadRenderer;
import defeatedcrow.hac.food.client.SquareBreadRenderer;
import defeatedcrow.hac.food.client.YakitoriStickRenderer;
import defeatedcrow.hac.food.entity.BeefStickEntity;
import defeatedcrow.hac.food.entity.FishStickEntity;
import defeatedcrow.hac.food.entity.PorkStickEntity;
import defeatedcrow.hac.food.entity.RoundBreadEntity;
import defeatedcrow.hac.food.entity.SquareBreadEntity;
import defeatedcrow.hac.food.entity.YakitoriStickEntity;
import defeatedcrow.hac.main.CommonMainProxy;
import defeatedcrow.hac.main.block.container.BlockCardboard;
import defeatedcrow.hac.main.block.container.BlockCropCont;
import defeatedcrow.hac.main.block.container.BlockEnemyCont;
import defeatedcrow.hac.main.block.container.BlockLogCont;
import defeatedcrow.hac.main.block.container.BlockMiscCont;
import defeatedcrow.hac.main.block.device.TileNormalChamber;
import defeatedcrow.hac.main.client.block.TESRNormalChamber;
import defeatedcrow.hac.main.event.AltTooltipEvent;

@SideOnly(Side.CLIENT)
public class ClientMainProxy extends CommonMainProxy {

	@Override
	public void loadConst() {
		JsonBakery.instance.addTex(BlockLogCont.getTexList());
		JsonBakery.instance.addTex(BlockCropCont.getTexList());
		JsonBakery.instance.addTex(BlockEnemyCont.getTexList());
		JsonBakery.instance.addTex(BlockMiscCont.getTexList());
		JsonBakery.instance.addTex(BlockCardboard.getTexList());
	}

	@Override
	public void loadMaterial() {
		super.loadMaterial();
		JsonRegister.load();
	}

	@Override
	public void loadEntity() {
		super.loadEntity();
		registRender(RoundBreadEntity.class, RoundBreadRenderer.class);
		registRender(SquareBreadEntity.class, SquareBreadRenderer.class);
		registRender(FishStickEntity.class, FishStickRenderer.class);
		registRender(YakitoriStickEntity.class, YakitoriStickRenderer.class);
		registRender(PorkStickEntity.class, PorkStickRenderer.class);
		registRender(BeefStickEntity.class, BeefStickRenderer.class);
	}

	@Override
	public void loadTE() {
		ClientRegistry.registerTileEntity(TileNormalChamber.class, "dcs_te_chamber_normal", new TESRNormalChamber());
	}

	@Override
	public void loadInit() {
		super.loadInit();
		MinecraftForge.EVENT_BUS.register(new AltTooltipEvent());
	}

	@Override
	public void addSidedBlock(Block block, String name, int max) {
		JsonRegister.MAIN_INSTANCE.regBakedBlock(block, ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" + name,
				"cont", max);
		JsonBakery.instance.regDummySidedModel(block);
	}

	@Override
	public void addTBBlock(Block block, String name, int max) {
		JsonRegister.MAIN_INSTANCE.regBakedBlock(block, ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" + name,
				"cont", max);
		JsonBakery.instance.regDummyTBModel(block);
	}

	/**
	 * メタ無しJson製Block。一部の階段・ハーフにのみ使用している
	 */
	@Override
	public void regBlockJson(Item item, String domein, String name, String dir, int max, boolean f) {
		int m = 0;
		if (max == 0) {
			ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(domein + ":" + dir + "/"
					+ name, "inventory"));
		} else {
			while (m < max + 1) {
				if (f) {
					ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(domein + ":" + dir
							+ "/" + name + m, "inventory"));
				} else {
					ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(domein + ":" + dir
							+ "/" + name, "inventory"));
				}
				m++;
			}
		}
	}

	// ruby氏に無限に感謝
	/**
	 * @param cls
	 *            えんちちーのくらす
	 * @param render
	 *            Renderの継承クラス
	 */
	private void registRender(Class<? extends Entity> cls, final Class<? extends Render> render) {
		RenderingRegistry.registerEntityRenderingHandler(cls, new IRenderFactory() {
			@Override
			public Render createRenderFor(RenderManager manager) {
				try {
					return render.getConstructor(manager.getClass()).newInstance(manager);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
