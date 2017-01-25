package defeatedcrow.hac.main.client;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.client.JsonBakery;
import defeatedcrow.hac.core.client.base.ModelThinBiped;
import defeatedcrow.hac.food.FoodClientProxy;
import defeatedcrow.hac.machine.MachineClientProxy;
import defeatedcrow.hac.magic.MagicClientProxy;
import defeatedcrow.hac.magic.client.RenderPlayerEventDC;
import defeatedcrow.hac.magic.client.TESRInfernalFlame;
import defeatedcrow.hac.main.CommonMainProxy;
import defeatedcrow.hac.main.block.build.TileLowChest;
import defeatedcrow.hac.main.block.build.TileMagnetChest;
import defeatedcrow.hac.main.block.build.TileMetalChest;
import defeatedcrow.hac.main.block.build.TileVillageChest;
import defeatedcrow.hac.main.block.container.BlockCardboard;
import defeatedcrow.hac.main.block.container.BlockCropBasket;
import defeatedcrow.hac.main.block.container.BlockCropCont;
import defeatedcrow.hac.main.block.container.BlockDustBag;
import defeatedcrow.hac.main.block.container.BlockEnemyCont;
import defeatedcrow.hac.main.block.container.BlockLogCont;
import defeatedcrow.hac.main.block.container.BlockMiscCont;
import defeatedcrow.hac.main.block.device.TileBellow;
import defeatedcrow.hac.main.block.device.TileCookingStove;
import defeatedcrow.hac.main.block.device.TileNormalChamber;
import defeatedcrow.hac.main.block.device.TileShitirin;
import defeatedcrow.hac.main.block.device.TileSink;
import defeatedcrow.hac.main.block.device.TileStevensonScreen;
import defeatedcrow.hac.main.block.fluid.DCFluidBlockBase;
import defeatedcrow.hac.main.client.block.TESRBellow;
import defeatedcrow.hac.main.client.block.TESRFuelStove;
import defeatedcrow.hac.main.client.block.TESRMagnetChest;
import defeatedcrow.hac.main.client.block.TESRMetalChest;
import defeatedcrow.hac.main.client.block.TESRNormalChamber;
import defeatedcrow.hac.main.client.block.TESRShitirin;
import defeatedcrow.hac.main.client.block.TESRStevensonScreen;
import defeatedcrow.hac.main.client.block.TESRVillageChest;
import defeatedcrow.hac.main.client.entity.RenderEntityCution;
import defeatedcrow.hac.main.client.model.ModelHat;
import defeatedcrow.hac.main.client.particle.ParticleBlink;
import defeatedcrow.hac.main.client.particle.ParticleFallingStar;
import defeatedcrow.hac.main.entity.EntityCution;
import defeatedcrow.hac.main.event.AltTooltipEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
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

	@Override
	public void loadConst() {
		JsonBakery.instance.addTex(BlockLogCont.getTexList());
		JsonBakery.instance.addTex(BlockCropCont.getTexList());
		JsonBakery.instance.addTex(BlockEnemyCont.getTexList());
		JsonBakery.instance.addTex(BlockMiscCont.getTexList());
		JsonBakery.instance.addTex(BlockCardboard.getTexList());
		JsonBakery.instance.addTex(BlockCropBasket.getTexList());
		JsonBakery.instance.addTex(BlockDustBag.getTexList());
		JsonBakery.instance.addTex(DCFluidBlockBase.getTexList());

		List<String> particles = new ArrayList<String>();
		particles.add(ParticleBlink.BLINK_TEX);
		particles.add(ParticleFallingStar.STAR_TEX);

		particles.add(TESRInfernalFlame.TEX1.toString());
		particles.add(TESRInfernalFlame.TEX2.toString());

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

		FoodClientProxy.loadEntity();
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

	@Override
	public void addCropBlock(Block block, String name, int max) {
		JsonRegister.MAIN_INSTANCE.regBakedBlock(block, ClimateCore.PACKAGE_ID, ClimateCore.PACKAGE_BASE + "_" + name,
				"crop", max);
		JsonBakery.instance.regDummyCropModel(block);
	}

	/**
	 * メタ無しJson製Block。一部の階段・ハーフにのみ使用している
	 */
	@Override
	public void regBlockJson(Item item, String domein, String name, String dir, int max, boolean f) {
		int m = 0;
		if (max == 0) {
			ModelLoader.setCustomModelResourceLocation(item, m,
					new ModelResourceLocation(domein + ":" + dir + "/" + name, "inventory"));
		} else {
			while (m < max + 1) {
				if (f) {
					ModelLoader.setCustomModelResourceLocation(item, m,
							new ModelResourceLocation(domein + ":" + dir + "/" + name + m, "inventory"));
				} else {
					ModelLoader.setCustomModelResourceLocation(item, m,
							new ModelResourceLocation(domein + ":" + dir + "/" + name, "inventory"));
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
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation(domein + ":" + dir + "/" + name, "inventory"));
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
	public ModelThinBiped getArmorModel(int slot) {
		switch (slot) {
		case 3:
			return hatModel;
		default:
			return null;
		}
	}

	@Override
	public boolean hasAchivement(EntityPlayer player, Achievement acv) {
		return player != null && player instanceof EntityPlayerSP
				&& ((EntityPlayerSP) player).getStatFileWriter().hasAchievementUnlocked(acv);
	}

	@Override
	public boolean isForwardKeyDown() {
		return Keyboard.isCreated() && Keyboard.isKeyDown(getFowardKey());
	}

	@Override
	public boolean isSneakKeyDown() {
		return Keyboard.isCreated() && Keyboard.isKeyDown(getSneakKey());
	}

	private int getFowardKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode();
	}

	private int getSneakKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode();
	}
}
