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
import defeatedcrow.hac.magic.client.MagicCushionRenderer;
import defeatedcrow.hac.magic.client.TESRVeinBeacon;
import defeatedcrow.hac.magic.event.MagicClientEvent;
import defeatedcrow.hac.magic.proj.EntityFlowerBolt;
import defeatedcrow.hac.main.CommonMainProxy;
import defeatedcrow.hac.main.block.build.BlockMetalFenceBase;
import defeatedcrow.hac.main.block.build.TileAwning;
import defeatedcrow.hac.main.block.build.TileBedDC;
import defeatedcrow.hac.main.block.build.TileBedDCFuton;
import defeatedcrow.hac.main.block.build.TileBedDCHammock;
import defeatedcrow.hac.main.block.build.TileBedDCRattan;
import defeatedcrow.hac.main.block.build.TileBedDCWhite;
import defeatedcrow.hac.main.block.build.TileChandelierGypsum;
import defeatedcrow.hac.main.block.build.TileDisplayShelf;
import defeatedcrow.hac.main.block.build.TileDisplayStand;
import defeatedcrow.hac.main.block.build.TileDoorHikido;
import defeatedcrow.hac.main.block.build.TileLampCarbide;
import defeatedcrow.hac.main.block.build.TileLampGas;
import defeatedcrow.hac.main.block.build.TileLowChest;
import defeatedcrow.hac.main.block.build.TileMCClock_L;
import defeatedcrow.hac.main.block.build.TileMFence;
import defeatedcrow.hac.main.block.build.TileMFenceGlass;
import defeatedcrow.hac.main.block.build.TileMFenceNet;
import defeatedcrow.hac.main.block.build.TileMagnetChest;
import defeatedcrow.hac.main.block.build.TileMetalChest;
import defeatedcrow.hac.main.block.build.TileRealtimeClock;
import defeatedcrow.hac.main.block.build.TileRealtimeClock_L;
import defeatedcrow.hac.main.block.build.TileStairsRoof;
import defeatedcrow.hac.main.block.build.TileTatami;
import defeatedcrow.hac.main.block.build.TileVillageChest;
import defeatedcrow.hac.main.block.build.TileWallDecoration;
import defeatedcrow.hac.main.block.build.TileWindowBlinds;
import defeatedcrow.hac.main.block.device.TileBellow;
import defeatedcrow.hac.main.block.device.TileCookingStove;
import defeatedcrow.hac.main.block.device.TileCraftingCounter;
import defeatedcrow.hac.main.block.device.TileFirestand;
import defeatedcrow.hac.main.block.device.TileGeyser;
import defeatedcrow.hac.main.block.device.TileHopperChest;
import defeatedcrow.hac.main.block.device.TileKitchenHood;
import defeatedcrow.hac.main.block.device.TileNormalChamber;
import defeatedcrow.hac.main.block.device.TilePail;
import defeatedcrow.hac.main.block.device.TileShitirin;
import defeatedcrow.hac.main.block.device.TileSink;
import defeatedcrow.hac.main.block.device.TileSinkFull;
import defeatedcrow.hac.main.block.device.TileStevensonScreen;
import defeatedcrow.hac.main.block.device.TileSwedishTorch;
import defeatedcrow.hac.main.block.device.TileThermometer;
import defeatedcrow.hac.main.block.device.TileWindVane;
import defeatedcrow.hac.main.block.fluid.FluidUtil;
import defeatedcrow.hac.main.client.block.TESRAnalogClock;
import defeatedcrow.hac.main.client.block.TESRBedFuton;
import defeatedcrow.hac.main.client.block.TESRBedHammock;
import defeatedcrow.hac.main.client.block.TESRBedIron;
import defeatedcrow.hac.main.client.block.TESRBedRattan;
import defeatedcrow.hac.main.client.block.TESRBedWhite;
import defeatedcrow.hac.main.client.block.TESRBellow;
import defeatedcrow.hac.main.client.block.TESRCarbideLamp;
import defeatedcrow.hac.main.client.block.TESRChandelier;
import defeatedcrow.hac.main.client.block.TESRCraftingCounter;
import defeatedcrow.hac.main.client.block.TESRDisplayShelf;
import defeatedcrow.hac.main.client.block.TESRDisplayStand;
import defeatedcrow.hac.main.client.block.TESRDoorHikido;
import defeatedcrow.hac.main.client.block.TESRFirestand;
import defeatedcrow.hac.main.client.block.TESRFuelStove;
import defeatedcrow.hac.main.client.block.TESRGasLamp;
import defeatedcrow.hac.main.client.block.TESRHopperChest;
import defeatedcrow.hac.main.client.block.TESRKitchenHood;
import defeatedcrow.hac.main.client.block.TESRLargeClock;
import defeatedcrow.hac.main.client.block.TESRMCClock;
import defeatedcrow.hac.main.client.block.TESRMFence;
import defeatedcrow.hac.main.client.block.TESRMFenceGlass;
import defeatedcrow.hac.main.client.block.TESRMFenceNet;
import defeatedcrow.hac.main.client.block.TESRMagnetChest;
import defeatedcrow.hac.main.client.block.TESRMetalChest;
import defeatedcrow.hac.main.client.block.TESRNormalChamber;
import defeatedcrow.hac.main.client.block.TESRPail;
import defeatedcrow.hac.main.client.block.TESRShitirin;
import defeatedcrow.hac.main.client.block.TESRSinkFull;
import defeatedcrow.hac.main.client.block.TESRSinkHalf;
import defeatedcrow.hac.main.client.block.TESRStairsRoof;
import defeatedcrow.hac.main.client.block.TESRStevensonScreen;
import defeatedcrow.hac.main.client.block.TESRSwedishTorch;
import defeatedcrow.hac.main.client.block.TESRThermometer;
import defeatedcrow.hac.main.client.block.TESRVillageChest;
import defeatedcrow.hac.main.client.block.TESRWallDecoration;
import defeatedcrow.hac.main.client.block.TESRWindVane;
import defeatedcrow.hac.main.client.block.TESRWindowBlinds;
import defeatedcrow.hac.main.client.entity.BoltRenderer;
import defeatedcrow.hac.main.client.entity.RenderCoatRack;
import defeatedcrow.hac.main.client.entity.RenderEntityBalloon;
import defeatedcrow.hac.main.client.entity.RenderEntityBigCushion;
import defeatedcrow.hac.main.client.entity.RenderEntityBigCushionB;
import defeatedcrow.hac.main.client.entity.RenderEntityCution;
import defeatedcrow.hac.main.client.entity.RenderEntityDesktopAccessories_A;
import defeatedcrow.hac.main.client.entity.RenderEntityDesktopAccessories_B;
import defeatedcrow.hac.main.client.entity.RenderEntityDesktopAccessories_C;
import defeatedcrow.hac.main.client.entity.RenderEntityDesktopAccessories_D;
import defeatedcrow.hac.main.client.entity.RenderEntityDesktopAccessories_E;
import defeatedcrow.hac.main.client.entity.RenderEntityDesktopAccessories_F;
import defeatedcrow.hac.main.client.entity.RenderEntityDesktopAccessories_G;
import defeatedcrow.hac.main.client.entity.RenderEntityDesktopAccessories_H;
import defeatedcrow.hac.main.client.entity.RenderEntityDesktopAccessories_I;
import defeatedcrow.hac.main.client.entity.RenderEntityDynamite;
import defeatedcrow.hac.main.client.entity.RenderEntityDynamiteSmall;
import defeatedcrow.hac.main.client.entity.RenderEntityFlowerPot;
import defeatedcrow.hac.main.client.entity.RenderEntitySmallCushionA;
import defeatedcrow.hac.main.client.entity.RenderEntitySmallCushionB;
import defeatedcrow.hac.main.client.entity.RenderEntitySmallCushionC;
import defeatedcrow.hac.main.client.model.ModelDress;
import defeatedcrow.hac.main.client.model.ModelHat;
import defeatedcrow.hac.main.client.model.ModelHoodie;
import defeatedcrow.hac.main.client.model.ModelOvercoat;
import defeatedcrow.hac.main.client.model.ModelSkirt;
import defeatedcrow.hac.main.client.model.ModelSkirtSilk;
import defeatedcrow.hac.main.client.model.ModelWoolWear;
import defeatedcrow.hac.main.client.particle.ParticleBlink;
import defeatedcrow.hac.main.client.particle.ParticleCloudDC;
import defeatedcrow.hac.main.client.particle.ParticleCloudRev;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.client.particle.ParticleFlameDC;
import defeatedcrow.hac.main.client.particle.ParticleOrb;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.entity.EntityBigCushion;
import defeatedcrow.hac.main.entity.EntityBigCushionBrown;
import defeatedcrow.hac.main.entity.EntityCoatRack;
import defeatedcrow.hac.main.entity.EntityCrowBalloon;
import defeatedcrow.hac.main.entity.EntityCrowBullet;
import defeatedcrow.hac.main.entity.EntityCution;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_A;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_B;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_C;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_D;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_E;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_F;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_G;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_H;
import defeatedcrow.hac.main.entity.EntityDesktopAccessories_I;
import defeatedcrow.hac.main.entity.EntityDynamite;
import defeatedcrow.hac.main.entity.EntityDynamiteBlue;
import defeatedcrow.hac.main.entity.EntityDynamiteSmall;
import defeatedcrow.hac.main.entity.EntityExtinctionBullet;
import defeatedcrow.hac.main.entity.EntityFlowerPot;
import defeatedcrow.hac.main.entity.EntityGhostBullet;
import defeatedcrow.hac.main.entity.EntityIronBolt;
import defeatedcrow.hac.main.entity.EntityIronBullet;
import defeatedcrow.hac.main.entity.EntityLightBullet;
import defeatedcrow.hac.main.entity.EntityShotgunBullet;
import defeatedcrow.hac.main.entity.EntitySilverBullet;
import defeatedcrow.hac.main.entity.EntitySmallCushionA;
import defeatedcrow.hac.main.entity.EntitySmallCushionB;
import defeatedcrow.hac.main.entity.EntitySmallCushionC;
import defeatedcrow.hac.main.entity.EntityThrowingArrow;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
	private static final ModelDress dressModel = new ModelDress(2);
	private static final ModelSkirt skirtModel = new ModelSkirt(3);
	private static final ModelDress shirtModel2 = new ModelDress(2).setShort();
	private static final ModelSkirtSilk skirtSilkModel = new ModelSkirtSilk(3);
	private static final ModelThinBiped thinBootsModel = new ModelThinBiped(0.40F, 3);
	private static final ModelThinBiped legginsModel2 = new ModelThinBiped(0.45F, 2);
	private static final ModelThinBiped bodyModel2 = new ModelThinBiped(0.60F, 1);
	private static final ModelThinBiped bodyModel3 = new ModelThinBiped(1.1F, 1);

	@Override
	public void loadConst() {
		JsonBakery.instance.addTex(FluidUtil.getTexList());

		List<String> particles = new ArrayList<String>();
		particles.add(ParticleBlink.BLINK_TEX);
		particles.add(ParticleFallingStar.STAR_TEX);
		particles.add(ParticleCloudDC.CLOUD_TEX);
		particles.add(ParticleCloudRev.CLOUD_TEX);
		particles.add(ParticleOrb.ORB_TEX);
		particles.add(ParticleFlameDC.FLAME_TEX);
		particles.add(MagicCushionRenderer.PARTICLE_TEX);
		particles.add(GasBurnerTESR.TEX1.toString());
		particles.add(TESRVeinBeacon.RED);
		particles.add(TESRVeinBeacon.GREEN);
		particles.add(TESRVeinBeacon.BLUE);
		particles.add(TESRVeinBeacon.WHITE);
		particles.add(TESRVeinBeacon.BLACK);
		particles.add(TESRVeinBeacon.GUANO);

		JsonBakery.instance.addTex(particles);

		MagicClientProxy.loadConst();
		FoodClientProxy.loadConst();
	}

	@Override
	public void loadMaterial() {
		super.loadMaterial();
		JsonRegister.load();

		if (ModuleConfig.food)
			FoodClientProxy.regJson(JsonRegister.MAIN_INSTANCE);

		if (ModuleConfig.machine)
			MachineClientProxy.regJson(JsonRegister.MAIN_INSTANCE);

		if (ModuleConfig.magic)
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
		registRender(EntitySmallCushionA.class, RenderEntitySmallCushionA.class);
		registRender(EntitySmallCushionB.class, RenderEntitySmallCushionB.class);
		registRender(EntitySmallCushionC.class, RenderEntitySmallCushionC.class);
		registRender(EntityBigCushionBrown.class, RenderEntityBigCushionB.class);
		registRender(EntityThrowingArrow.class, BoltRenderer.class);
		registRender(EntityDynamiteSmall.class, RenderEntityDynamiteSmall.class);
		registRender(EntityFlowerBolt.class, BoltRenderer.class);
		registRender(EntityDesktopAccessories_A.class, RenderEntityDesktopAccessories_A.class);
		registRender(EntityDesktopAccessories_B.class, RenderEntityDesktopAccessories_B.class);
		registRender(EntityDesktopAccessories_C.class, RenderEntityDesktopAccessories_C.class);
		registRender(EntityDesktopAccessories_D.class, RenderEntityDesktopAccessories_D.class);
		registRender(EntityDesktopAccessories_E.class, RenderEntityDesktopAccessories_E.class);
		registRender(EntityDesktopAccessories_F.class, RenderEntityDesktopAccessories_F.class);
		registRender(EntityDesktopAccessories_G.class, RenderEntityDesktopAccessories_G.class);
		registRender(EntityDesktopAccessories_H.class, RenderEntityDesktopAccessories_H.class);
		registRender(EntityDesktopAccessories_I.class, RenderEntityDesktopAccessories_I.class);
		registRender(EntityCoatRack.class, RenderCoatRack.class);

		if (ModuleConfig.food)
			FoodClientProxy.loadEntity();

		if (ModuleConfig.machine)
			MachineClientProxy.loadEntity();

		if (ModuleConfig.magic)
			MagicClientProxy.loadEntity();
	}

	@Override
	public void loadTE() {
		registerTileEntity(TileNormalChamber.class, "dcs_te_chamber_normal", new TESRNormalChamber());
		registerTileEntity(TileShitirin.class, "dcs_te_shitirin", new TESRShitirin());
		registerTileEntity(TileCookingStove.class, "dcs_te_fuel_stove", new TESRFuelStove());
		registerTileEntity(TileStevensonScreen.class, "dcs_te_stevenson_screen", new TESRStevensonScreen());
		GameRegistry.registerTileEntity(TileLowChest.class, "dcs_te_lowchest");
		registerTileEntity(TileMetalChest.class, "dcs_te_metalchest", new TESRMetalChest());
		registerTileEntity(TileMagnetChest.class, "dcs_te_magnetchest", new TESRMagnetChest());
		registerTileEntity(TileVillageChest.class, "dcs_te_villagechest", new TESRVillageChest());
		registerTileEntity(TileSink.class, "dcs_te_sink", new TESRSinkHalf());
		registerTileEntity(TileSinkFull.class, "dcs_te_sink_full", new TESRSinkFull());
		registerTileEntity(TileCraftingCounter.class, "dcs_te_crafting_counter", new TESRCraftingCounter());
		registerTileEntity(TileKitchenHood.class, "dcs_te_kitchen_hood", new TESRKitchenHood());
		registerTileEntity(TileBellow.class, "dcs_te_bellow", new TESRBellow());
		registerTileEntity(TileThermometer.class, "dcs_te_thermometer", new TESRThermometer());
		registerTileEntity(TileWindVane.class, "dcs_te_windvane", new TESRWindVane());
		registerTileEntity(TileChandelierGypsum.class, "dcs_te_chandelier_gypsum", new TESRChandelier());
		registerTileEntity(TileRealtimeClock.class, "dcs_te_realtime_clock", new TESRAnalogClock());
		registerTileEntity(TileRealtimeClock_L.class, "dcs_te_realtime_clock_l", new TESRLargeClock());
		registerTileEntity(TileMCClock_L.class, "dcs_te_mc_clock_l", new TESRMCClock());
		registerTileEntity(TilePail.class, "dcs_te_pail", new TESRPail());
		registerTileEntity(TileBedDC.class, "dcs_te_bed_iron", new TESRBedIron());
		registerTileEntity(TileBedDCWhite.class, "dcs_te_bed_white", new TESRBedWhite());
		registerTileEntity(TileBedDCRattan.class, "dcs_te_bed_rattan", new TESRBedRattan());
		registerTileEntity(TileBedDCFuton.class, "dcs_te_bed_futon", new TESRBedFuton());
		registerTileEntity(TileBedDCHammock.class, "dcs_te_bed_hammock", new TESRBedHammock());
		GameRegistry.registerTileEntity(TileGeyser.class, "dcs_te_geyser");
		registerTileEntity(TileFirestand.class, "dcs_te_firestand", new TESRFirestand());
		registerTileEntity(TileDisplayShelf.class, "dcs_te_display_shelf", new TESRDisplayShelf());
		registerTileEntity(TileMFence.class, "dcs_te_mfence_normal", new TESRMFence());
		registerTileEntity(TileMFenceGlass.class, "dcs_te_mfence_glass", new TESRMFenceGlass());
		registerTileEntity(TileMFenceNet.class, "dcs_te_mfence_net", new TESRMFenceNet());
		registerTileEntity(TileDisplayStand.class, "dcs_te_display_stand", new TESRDisplayStand());
		registerTileEntity(TileSwedishTorch.class, "dcs_te_swedish_torch", new TESRSwedishTorch());
		GameRegistry.registerTileEntity(TileTatami.class, "dcs_te_carpet_tatami");
		registerTileEntity(TileDoorHikido.class, "dcs_te_door_hikido", new TESRDoorHikido());
		registerTileEntity(TileWindowBlinds.class, "dcs_te_window_blinds", new TESRWindowBlinds());
		registerTileEntity(TileHopperChest.class, "dcs_te_hopper_chest", new TESRHopperChest());
		registerTileEntity(TileLampCarbide.class, "dcs_te_carbide_lamp", new TESRCarbideLamp());
		registerTileEntity(TileLampGas.class, "dcs_te_gas_lamp", new TESRGasLamp());
		GameRegistry.registerTileEntity(TileAwning.class, "dcs_te_awning");
		registerTileEntity(TileStairsRoof.class, "dcs_te_stairs_roof", new TESRStairsRoof());
		registerTileEntity(TileWallDecoration.class, "dcs_te_wall_decoration", new TESRWallDecoration());

		if (ModuleConfig.food)
			FoodClientProxy.loadTE();

		if (ModuleConfig.machine)
			MachineClientProxy.loadTE();

		if (ModuleConfig.magic)
			MagicClientProxy.loadTE();
	}

	public static <T extends TileEntity> void registerTileEntity(Class<T> teClass, String id,
			TileEntitySpecialRenderer<? super T> renderer) {
		GameRegistry.registerTileEntity(teClass, id);
		ClientRegistry.bindTileEntitySpecialRenderer(teClass, renderer);
	}

	@Override
	public void loadInit() {
		super.loadInit();
		MinecraftForge.EVENT_BUS.register(new RenderPlayerEventDC());
		if (ModuleConfig.magic) {
			MinecraftForge.EVENT_BUS.register(new MagicClientEvent());
		}
	}

	@Override
	public void addSidedBlock(Block block, String name, int max) {
		if (block == null)
			return;
		JsonRegister
				.regSidedCube(((ISidedTexture) block), ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" + name, "cont", max);
		JsonRegister.MAIN_INSTANCE
				.regSimpleBlock(block, ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" + name, "cont", max);
	}

	@Override
	public void addCropBlock(Block block, String name, int max) {
		if (block == null)
			return;
		JsonRegister
				.regCross(((ISidedTexture) block), ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" + name, "crop", max);
		JsonRegister.MAIN_INSTANCE
				.regSimpleBlock(block, ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" + name, "crop", max);
	}

	/**
	 * メタ無しJson製Block。一部の階段・ハーフにのみ使用している
	 */
	@Override
	public void regBlockJson(Item item, String domein, String name, String dir, int max, boolean f) {
		if (item == null)
			return;
		int m = 0;
		if (max == 0) {
			ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(
					domein + ":" + dir + "/" + name, "inventory"));
		} else {
			while (m < max + 1) {
				if (f) {
					ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(
							domein + ":" + dir + "/" + name + m, "inventory"));
				} else {
					ModelLoader.setCustomModelResourceLocation(item, m, new ModelResourceLocation(
							domein + ":" + dir + "/" + name, "inventory"));
				}
				m++;
			}
		}
	}

	/** MetalFence専用 */
	@Override
	public void regBlockMFence(Block block, String domein, String name, String dir) {
		if (block == null)
			return;
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder())
				.ignore(BlockMetalFenceBase.BACK_FACING, BlockMetalFenceBase.BACK_UNDER, BlockMetalFenceBase.BACK_UPPER, DCState.FACING, BlockMetalFenceBase.UNDER, BlockMetalFenceBase.UPPER)
				.build());
		ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new ModelResourceLocation(
				domein + ":" + "basetile"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(
				domein + ":" + dir + "/" + name, "inventory"));
	}

	@Override
	public void regTEJson(Block block, String domein, String name, String dir) {
		if (block == null)
			return;
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(DCState.TYPE4).build());
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(
				domein + ":" + dir + "/" + name, "inventory"));
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
			return dressModel;
		case 7:
			return skirtModel;
		case 8:
			return thinBootsModel;
		case 9:
			return bodyModel2;
		case 10:
			return legginsModel2;
		case 11:
			return bodyModel3;
		case 12:
			return shirtModel2;
		case 13:
			return skirtSilkModel;
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

	@Override
	public boolean isItemUseKeyDown() {
		return Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown();
	}

	@Override
	public int getParticleCount() {
		int i = Minecraft.getMinecraft().gameSettings.particleSetting;
		switch (i) {
		case 0:
			return 12;
		case 1:
			return 6;
		case 2:
			return 0;
		default:
			return 6;
		}
	}

	@Override
	public boolean isOP(EntityPlayer player) {
		if (FMLCommonHandler.instance().getMinecraftServerInstance().isSinglePlayer()) {
			return true;
		}
		return false;
	}
}
