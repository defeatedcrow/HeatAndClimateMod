package defeatedcrow.hac.main.client;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.placeable.ISidedTexture;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.client.JsonBakery;
import defeatedcrow.hac.core.client.base.ModelThinBiped;
import defeatedcrow.hac.food.FoodClientProxy;
import defeatedcrow.hac.machine.MachineClientProxy;
import defeatedcrow.hac.machine.client.GasBurnerTESR;
import defeatedcrow.hac.magic.MagicClientProxy;
import defeatedcrow.hac.magic.client.TESRInfernalFlame;
import defeatedcrow.hac.main.CommonMainProxy;
import defeatedcrow.hac.main.block.build.TileChandelierGypsum;
import defeatedcrow.hac.main.block.build.TileLowChest;
import defeatedcrow.hac.main.block.build.TileMCClock_L;
import defeatedcrow.hac.main.block.build.TileMagnetChest;
import defeatedcrow.hac.main.block.build.TileMetalChest;
import defeatedcrow.hac.main.block.build.TileRealtimeClock;
import defeatedcrow.hac.main.block.build.TileRealtimeClock_L;
import defeatedcrow.hac.main.block.build.TileVillageChest;
import defeatedcrow.hac.main.block.device.TileAcvShield;
import defeatedcrow.hac.main.block.device.TileBellow;
import defeatedcrow.hac.main.block.device.TileCookingStove;
import defeatedcrow.hac.main.block.device.TileNormalChamber;
import defeatedcrow.hac.main.block.device.TileShitirin;
import defeatedcrow.hac.main.block.device.TileSink;
import defeatedcrow.hac.main.block.device.TileStevensonScreen;
import defeatedcrow.hac.main.block.device.TileThermometer;
import defeatedcrow.hac.main.block.device.TileWindVane;
import defeatedcrow.hac.main.block.fluid.FluidUtil;
import defeatedcrow.hac.main.client.block.TESRAnalogClock;
import defeatedcrow.hac.main.client.block.TESRBellow;
import defeatedcrow.hac.main.client.block.TESRChandelier;
import defeatedcrow.hac.main.client.block.TESRFuelStove;
import defeatedcrow.hac.main.client.block.TESRLargeClock;
import defeatedcrow.hac.main.client.block.TESRMCClock;
import defeatedcrow.hac.main.client.block.TESRMagnetChest;
import defeatedcrow.hac.main.client.block.TESRMetalChest;
import defeatedcrow.hac.main.client.block.TESRNormalChamber;
import defeatedcrow.hac.main.client.block.TESRShitirin;
import defeatedcrow.hac.main.client.block.TESRStevensonScreen;
import defeatedcrow.hac.main.client.block.TESRThermometer;
import defeatedcrow.hac.main.client.block.TESRVillageChest;
import defeatedcrow.hac.main.client.block.TESRWindVane;
import defeatedcrow.hac.main.client.entity.BoltRenderer;
import defeatedcrow.hac.main.client.entity.RenderEntityBalloon;
import defeatedcrow.hac.main.client.entity.RenderEntityBigCushion;
import defeatedcrow.hac.main.client.entity.RenderEntityBigCushionB;
import defeatedcrow.hac.main.client.entity.RenderEntityCution;
import defeatedcrow.hac.main.client.entity.RenderEntityDynamite;
import defeatedcrow.hac.main.client.entity.RenderEntityFlowerPot;
import defeatedcrow.hac.main.client.model.ModelHat;
import defeatedcrow.hac.main.client.model.ModelHoodie;
import defeatedcrow.hac.main.client.model.ModelOvercoat;
import defeatedcrow.hac.main.client.model.ModelWoolWear;
import defeatedcrow.hac.main.client.particle.ParticleBlink;
import defeatedcrow.hac.main.client.particle.ParticleCloudDC;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleFlameDC;
import defeatedcrow.hac.main.client.particle.ParticleOrb;
import defeatedcrow.hac.main.entity.EntityBigCushion;
import defeatedcrow.hac.main.entity.EntityBigCushionBrown;
import defeatedcrow.hac.main.entity.EntityCrowBalloon;
import defeatedcrow.hac.main.entity.EntityCrowBullet;
import defeatedcrow.hac.main.entity.EntityCution;
import defeatedcrow.hac.main.entity.EntityDynamite;
import defeatedcrow.hac.main.entity.EntityDynamiteBlue;
import defeatedcrow.hac.main.entity.EntityExtinctionBullet;
import defeatedcrow.hac.main.entity.EntityFlowerPot;
import defeatedcrow.hac.main.entity.EntityGhostBullet;
import defeatedcrow.hac.main.entity.EntityIronBolt;
import defeatedcrow.hac.main.entity.EntityIronBullet;
import defeatedcrow.hac.main.entity.EntityLightBullet;
import defeatedcrow.hac.main.entity.EntityShotgunBullet;
import defeatedcrow.hac.main.entity.EntitySilverBullet;
import defeatedcrow.hac.main.event.AltTooltipEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientMainProxy extends CommonMainProxy {

	private static final ModelHat hatModel = new ModelHat(0);
	private static final ModelHoodie hoodieModel = new ModelHoodie(0);
	private static final ModelWoolWear woolModel = new ModelWoolWear(0);
	private static final ModelWoolWear woolHatModel = new ModelWoolWear(3);
	private static final ModelOvercoat coatModel = new ModelOvercoat(0);
	private static final ModelOvercoat coatModel2 = new ModelOvercoat(1);
	private static final ModelThinBiped thinModel = new ModelThinBiped(0.40F, 3);

	@Override
	public void loadConst() {
		JsonBakery.instance.addTex(FluidUtil.getTexList());

		List<String> particles = new ArrayList<String>();
		particles.add(ParticleBlink.BLINK_TEX);
		particles.add(ParticleFallingStar.STAR_TEX);
		particles.add(ParticleCloudDC.CLOUD_TEX);
		particles.add(ParticleOrb.ORB_TEX);
		particles.add(ParticleFlameDC.FLAME_TEX);

		particles.add(TESRInfernalFlame.TEX1.toString());
		particles.add(TESRInfernalFlame.TEX2.toString());

		particles.add(GasBurnerTESR.TEX1.toString());

		JsonBakery.instance.addTex(particles);

		FoodClientProxy.loadConst();
	}

	@Override
	public void loadMaterial() {
		super.loadMaterial();
		JsonRegister.load();

		FoodClientProxy.regJson(JsonRegister.MAIN_INSTANCE);
		MachineClientProxy.regJson(JsonRegister.MAIN_INSTANCE);
		MagicClientProxy.regJson(JsonRegister.MAIN_INSTANCE);
	}

	@Override
	public void loadEntity() {
		super.loadEntity();
		registRender(EntityCution.class, RenderEntityCution.class);
		registRender(EntityFlowerPot.class, RenderEntityFlowerPot.class);
		registRender(EntityIronBolt.class, BoltRenderer.class);
		registRender(EntityIronBullet.class, BoltRenderer.class);
		registRender(EntitySilverBullet.class, BoltRenderer.class);
		registRender(EntityShotgunBullet.class, BoltRenderer.class);
		registRender(EntityGhostBullet.class, BoltRenderer.class);
		registRender(EntityDynamite.class, RenderEntityDynamite.class);
		registRender(EntityDynamiteBlue.class, RenderEntityDynamite.class);
		registRender(EntityLightBullet.class, BoltRenderer.class);
		registRender(EntityExtinctionBullet.class, BoltRenderer.class);
		registRender(EntityCrowBullet.class, BoltRenderer.class);
		registRender(EntityCrowBalloon.class, RenderEntityBalloon.class);
		registRender(EntityBigCushion.class, RenderEntityBigCushion.class);
		registRender(EntityBigCushionBrown.class, RenderEntityBigCushionB.class);

		FoodClientProxy.loadEntity();
		MachineClientProxy.loadEntity();
		MagicClientProxy.loadEntity();
	}

	@Override
	public void loadTE() {
		ClientRegistry.registerTileEntity(TileNormalChamber.class, "dcs_te_chamber_normal", new TESRNormalChamber());
		ClientRegistry.registerTileEntity(TileShitirin.class, "dcs_te_shitirin", new TESRShitirin());
		ClientRegistry.registerTileEntity(TileCookingStove.class, "dcs_te_fuel_stove", new TESRFuelStove());
		ClientRegistry.registerTileEntity(TileStevensonScreen.class, "dcs_te_stevenson_screen",
				new TESRStevensonScreen());
		GameRegistry.registerTileEntity(TileLowChest.class, "dcs_te_lowchest");
		ClientRegistry.registerTileEntity(TileMetalChest.class, "dcs_te_metalchest", new TESRMetalChest());
		ClientRegistry.registerTileEntity(TileMagnetChest.class, "dcs_te_magnetchest", new TESRMagnetChest());
		ClientRegistry.registerTileEntity(TileVillageChest.class, "dcs_te_villagechest", new TESRVillageChest());
		GameRegistry.registerTileEntity(TileSink.class, "dcs_te_sink");
		ClientRegistry.registerTileEntity(TileBellow.class, "dcs_te_bellow", new TESRBellow());
		ClientRegistry.registerTileEntity(TileThermometer.class, "dcs_te_thermometer", new TESRThermometer());
		ClientRegistry.registerTileEntity(TileWindVane.class, "dcs_te_windvane", new TESRWindVane());
		GameRegistry.registerTileEntity(TileAcvShield.class, "dcs_te_acv_shield");
		ClientRegistry.registerTileEntity(TileChandelierGypsum.class, "dcs_te_chandelier_gypsum", new TESRChandelier());
		ClientRegistry.registerTileEntity(TileRealtimeClock.class, "dcs_te_realtime_clock", new TESRAnalogClock());
		ClientRegistry.registerTileEntity(TileRealtimeClock_L.class, "dcs_te_realtime_clock_l", new TESRLargeClock());
		ClientRegistry.registerTileEntity(TileMCClock_L.class, "dcs_te_mc_clock_l", new TESRMCClock());

		FoodClientProxy.loadTE();
		MachineClientProxy.loadTE();
		MagicClientProxy.loadTE();
	}

	@Override
	public void loadInit() {
		super.loadInit();
		MinecraftForge.EVENT_BUS.register(new AltTooltipEvent());
		MinecraftForge.EVENT_BUS.register(AdvancedHUDEvent.INSTANCE);
		MinecraftForge.EVENT_BUS.register(new RenderPlayerEventDC());
	}

	@Override
	public void addSidedBlock(Block block, String name, int max) {
		JsonRegister.regSidedCube(((ISidedTexture) block), ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" +
				name, "cont", max);
		JsonRegister.MAIN_INSTANCE.regSimpleBlock(block, ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" + name,
				"cont", max);
	}

	@Override
	public void addCropBlock(Block block, String name, int max) {
		JsonRegister.regCross(((ISidedTexture) block), ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" + name,
				"crop", max);
		JsonRegister.MAIN_INSTANCE.regSimpleBlock(block, ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" + name,
				"crop", max);
	}

	/**
	 * メタ無しJson製Block。一部の階段・ハーフにのみ使用している
	 */
	@Override
	public void regBlockJson(Item item, String domein, String name, String dir, int max, boolean f) {
		int m = 0;
		if (max == 0) {
			ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(domein + ":" + dir + "/" +
					name, "inventory"));
		} else {
			while (m < max + 1) {
				if (f) {
					ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(domein + ":" + dir +
							"/" + name + m, "inventory"));
				} else {
					ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(domein + ":" + dir +
							"/" + name, "inventory"));
				}
				m++;
			}
		}
	}

	@Override
	public void regTEJson(Block block, String domein, String name, String dir) {
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(DCState.TYPE4).build());
		// ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new
		// ModelResourceLocation(domein + ":" + name));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(domein +
				":" + dir + "/" + name, "inventory"));
	}

	// ruby氏に無限に感謝
	/**
	 * @param cls
	 *        えんちちーのくらす
	 * @param render
	 *        Renderの継承クラス
	 */
	public static void registRender(Class<? extends Entity> cls, final Class<? extends Render> render) {
		RenderingRegistry.registerEntityRenderingHandler(cls, new IRenderFactory() {
			@Override
			public Render createRenderFor(RenderManager manager) {
				try {
					return render.getConstructor(manager.getClass()).newInstance(manager);
				} catch (Exception e) {
					DCLogger.infoLog("dcs_climate", "failed to register entity render: " + cls.getName());
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	// mainで追加したBipedModel
	@Override
	public net.minecraft.client.model.ModelBiped getArmorModel(int slot) {
		switch (slot) {
		case 0:
			return hatModel;
		case 1:
			return hoodieModel;
		case 2:
			return woolHatModel;
		case 3:
			return woolModel;
		case 4:
			return coatModel;
		case 5:
			return coatModel2;
		case 6:
			return thinModel;
		default:
			return null;
		}
	}

	@Override
	public boolean isForwardKeyDown() {
		return Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown();
	}

	@Override
	public boolean isSneakKeyDown() {
		return Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown();
	}
}
