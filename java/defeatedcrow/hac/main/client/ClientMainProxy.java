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
import defeatedcrow.hac.machine.block.TileCrank_S;
import defeatedcrow.hac.machine.block.TileFan;
import defeatedcrow.hac.machine.block.TileGearBox;
import defeatedcrow.hac.machine.block.TileHandCrank;
import defeatedcrow.hac.machine.block.TileRedBox;
import defeatedcrow.hac.machine.block.TileShaft_L;
import defeatedcrow.hac.machine.block.TileShaft_S;
import defeatedcrow.hac.machine.block.TileShaft_TA;
import defeatedcrow.hac.machine.block.TileShaft_TB;
import defeatedcrow.hac.machine.block.TileStoneMill;
import defeatedcrow.hac.machine.block.TileWindmill;
import defeatedcrow.hac.machine.block.TileWindmill_L;
import defeatedcrow.hac.machine.client.FanTESR;
import defeatedcrow.hac.machine.client.GearBoxTESR;
import defeatedcrow.hac.machine.client.HandCrankTESR;
import defeatedcrow.hac.machine.client.L_ShaftTESR;
import defeatedcrow.hac.machine.client.L_WindmillTESR;
import defeatedcrow.hac.machine.client.RedBoxTESR;
import defeatedcrow.hac.machine.client.S_CrankTESR;
import defeatedcrow.hac.machine.client.S_ShaftTESR;
import defeatedcrow.hac.machine.client.StoneMillTESR;
import defeatedcrow.hac.machine.client.TA_ShaftTESR;
import defeatedcrow.hac.machine.client.TB_ShaftTESR;
import defeatedcrow.hac.machine.client.WindmillTESR;
import defeatedcrow.hac.main.CommonMainProxy;
import defeatedcrow.hac.main.block.container.BlockCardboard;
import defeatedcrow.hac.main.block.container.BlockCropCont;
import defeatedcrow.hac.main.block.container.BlockEnemyCont;
import defeatedcrow.hac.main.block.container.BlockLogCont;
import defeatedcrow.hac.main.block.container.BlockMiscCont;
import defeatedcrow.hac.main.block.device.TileNormalChamber;
import defeatedcrow.hac.main.block.device.TileShitirin;
import defeatedcrow.hac.main.block.device.TileStevensonScreen;
import defeatedcrow.hac.main.client.block.TESRNormalChamber;
import defeatedcrow.hac.main.client.block.TESRShitirin;
import defeatedcrow.hac.main.client.block.TESRStevensonScreen;
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
		ClientRegistry.registerTileEntity(TileShitirin.class, "dcs_te_shitirin", new TESRShitirin());
		ClientRegistry.registerTileEntity(TileWindmill.class, "dcs_te_windmill", new WindmillTESR());
		ClientRegistry.registerTileEntity(TileWindmill_L.class, "dcs_te_windmill_l", new L_WindmillTESR());
		ClientRegistry.registerTileEntity(TileShaft_S.class, "dcs_te_shaft_s", new S_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_L.class, "dcs_te_shaft_l", new L_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_TA.class, "dcs_te_shaft_ta", new TA_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_TB.class, "dcs_te_shaft_tb", new TB_ShaftTESR());
		ClientRegistry.registerTileEntity(TileGearBox.class, "dcs_te_gearbox", new GearBoxTESR());
		ClientRegistry.registerTileEntity(TileCrank_S.class, "dcs_te_crank_s", new S_CrankTESR());
		ClientRegistry.registerTileEntity(TileHandCrank.class, "dcs_te_handcrank", new HandCrankTESR());
		ClientRegistry.registerTileEntity(TileStoneMill.class, "dcs_te_stonemill", new StoneMillTESR());
		ClientRegistry.registerTileEntity(TileRedBox.class, "dcs_te_redbox", new RedBoxTESR());
		ClientRegistry.registerTileEntity(TileFan.class, "dcs_te_fan", new FanTESR());
		ClientRegistry.registerTileEntity(TileStevensonScreen.class, "dcs_te_stevenson_screen",
				new TESRStevensonScreen());
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
