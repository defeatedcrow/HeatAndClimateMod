package defeatedcrow.hac.main;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.base.ModelThinBiped;
import defeatedcrow.hac.food.FoodCommonProxy;
import defeatedcrow.hac.food.block.TileFluidProcessorBase;
import defeatedcrow.hac.food.block.TileTeaPot;
import defeatedcrow.hac.food.gui.ContainerFluidProcessor;
import defeatedcrow.hac.food.gui.ContainerTeaPot;
import defeatedcrow.hac.food.gui.GuiFluidProcessor;
import defeatedcrow.hac.food.gui.GuiTeaPot;
import defeatedcrow.hac.food.recipes.FoodRecipes;
import defeatedcrow.hac.machine.MachineCommonProxy;
import defeatedcrow.hac.machine.block.TileStoneMill;
import defeatedcrow.hac.machine.gui.ContainerStoneMill;
import defeatedcrow.hac.machine.gui.GuiStoneMill;
import defeatedcrow.hac.machine.recipes.MachineRecipes;
import defeatedcrow.hac.magic.MagicCommonProxy;
import defeatedcrow.hac.magic.recipe.MagicRecipeRegister;
import defeatedcrow.hac.main.block.build.TileLowChest;
import defeatedcrow.hac.main.block.build.TileMagnetChest;
import defeatedcrow.hac.main.block.build.TileMetalChest;
import defeatedcrow.hac.main.block.device.TileCookingStove;
import defeatedcrow.hac.main.block.device.TileNormalChamber;
import defeatedcrow.hac.main.block.device.TileShitirin;
import defeatedcrow.hac.main.block.device.TileSink;
import defeatedcrow.hac.main.block.device.TileStevensonScreen;
import defeatedcrow.hac.main.client.gui.ContainerFuelStove;
import defeatedcrow.hac.main.client.gui.ContainerLowChest;
import defeatedcrow.hac.main.client.gui.ContainerNormalChamber;
import defeatedcrow.hac.main.client.gui.ContainerStevensonScreen;
import defeatedcrow.hac.main.client.gui.GuiFuelStove;
import defeatedcrow.hac.main.client.gui.GuiLowChest;
import defeatedcrow.hac.main.client.gui.GuiNormalChamber;
import defeatedcrow.hac.main.client.gui.GuiStevensonScreen;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.entity.EntityCution;
import defeatedcrow.hac.main.event.AchievementEventDC;
import defeatedcrow.hac.main.event.OnCraftingDC;
import defeatedcrow.hac.main.event.OnDeathEventDC;
import defeatedcrow.hac.main.event.OnJumpEventDC;
import defeatedcrow.hac.main.event.OnMiningEventDC;
import defeatedcrow.hac.main.potion.PotionGravityDC;
import defeatedcrow.hac.main.recipes.BasicRecipeRegister;
import defeatedcrow.hac.main.recipes.MachineRecipeRegister;
import defeatedcrow.hac.main.recipes.OreDicRegister;
import defeatedcrow.hac.main.worldgen.WorldGenAltOres;
import defeatedcrow.hac.main.worldgen.WorldGenAltSkarn;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonMainProxy implements IGuiHandler {

	public void loadConst() {
	}

	public void loadMaterial() {
		MainMaterialRegister.load();
		GameRegistry.registerFuelHandler(new DCFuelHandler());
		MainCreativeTabRegister.load();
	}

	public void loadPotion() {
		MainInit.gravity = new PotionGravityDC();
		GameRegistry.register(MainInit.gravity, new ResourceLocation(ClimateMain.MOD_ID, "dcs.potion.gravity"));
		MainInit.gravityType = new PotionType("dcs.gravity", new PotionEffect(MainInit.gravity));
		GameRegistry.register(MainInit.gravityType, new ResourceLocation(ClimateMain.MOD_ID, "dcs.gravity"));
	}

	public void loadRecipes() {
		OreDicRegister.load();
		BasicRecipeRegister.load();
		MachineRecipeRegister.load();

		if (ModuleConfig.food)
			FoodRecipes.load();

		if (ModuleConfig.machine)
			MachineRecipes.load();

		if (ModuleConfig.magic)
			MagicRecipeRegister.load();
	}

	public void loadEntity() {
		EntityRegistry.registerModEntity(EntityCution.class, ClimateCore.PACKAGE_BASE + "entity.main.cution", 99,
				ClimateMain.instance, 128, 5, false);

		FoodCommonProxy.loadEntity();
		MagicCommonProxy.loadEntity();
	}

	public void loadTE() {
		GameRegistry.registerTileEntity(TileNormalChamber.class, "dcs_te_chamber_normal");
		GameRegistry.registerTileEntity(TileShitirin.class, "dcs_te_shitirin");
		GameRegistry.registerTileEntity(TileCookingStove.class, "dcs_te_fuel_stove");
		GameRegistry.registerTileEntity(TileStevensonScreen.class, "dcs_te_stevenson_screen");
		GameRegistry.registerTileEntity(TileLowChest.class, "dcs_te_lowchest");
		GameRegistry.registerTileEntity(TileMetalChest.class, "dcs_te_metalchest");
		GameRegistry.registerTileEntity(TileMagnetChest.class, "dcs_te_magnetchest");
		GameRegistry.registerTileEntity(TileSink.class, "dcs_te_sink");

		FoodCommonProxy.loadTE();
		MachineCommonProxy.loadTE();
		MagicCommonProxy.loadTE();
	}

	public void loadWorldGen() {
		// gen
		GameRegistry.registerWorldGenerator(new WorldGenAltOres(), 2);
		GameRegistry.registerWorldGenerator(new WorldGenAltSkarn(false), 3);
	}

	public void addSidedBlock(Block block, String name, int max) {
	}

	public void addTBBlock(Block block, String name, int max) {
	}

	public void addCropBlock(Block block, String name, int max) {
	}

	/**
	 * メタ無しJson製Block。一部の階段・ハーフにのみ使用している
	 */
	public void regBlockJson(Item item, String domein, String name, String dir, int max, boolean f) {
	}

	/**
	 * TEの向きのみ対応させたJsonタイプモデル
	 */
	public void regTEJson(Block block, String domein, String name, String dir) {
	}

	public void loadInit() {
		MinecraftForge.EVENT_BUS.register(new OnMiningEventDC());
		MinecraftForge.EVENT_BUS.register(new OnDeathEventDC());
		MinecraftForge.EVENT_BUS.register(new OnJumpEventDC());
		MinecraftForge.EVENT_BUS.register(new OnCraftingDC());
		MinecraftForge.EVENT_BUS.register(new AchievementEventDC());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		if (!world.isBlockLoaded(pos))
			return null;
		TileEntity tile = world.getTileEntity(pos);
		if (tile == null)
			return null;
		if (tile instanceof TileNormalChamber) {
			return new ContainerNormalChamber((TileNormalChamber) tile, player.inventory);
		}
		if (tile instanceof TileStevensonScreen) {
			return new ContainerStevensonScreen((TileStevensonScreen) tile, player);
		}
		if (tile instanceof TileStoneMill) {
			return new ContainerStoneMill((TileStoneMill) tile, player.inventory);
		}
		if (tile instanceof TileTeaPot) {
			return new ContainerTeaPot((TileTeaPot) tile, player.inventory);
		}
		if (tile instanceof TileFluidProcessorBase) {
			return new ContainerFluidProcessor((TileFluidProcessorBase) tile, player.inventory);
		}
		if (tile instanceof TileLowChest) {
			return new ContainerLowChest((TileLowChest) tile, player);
		}
		if (tile instanceof TileCookingStove) {
			return new ContainerFuelStove((TileCookingStove) tile, player.inventory);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		if (!world.isBlockLoaded(pos))
			return null;
		TileEntity tile = world.getTileEntity(pos);
		if (tile == null)
			return null;
		if (tile instanceof TileNormalChamber) {
			return new GuiNormalChamber((TileNormalChamber) tile, player.inventory);
		}
		if (tile instanceof TileStevensonScreen) {
			return new GuiStevensonScreen((TileStevensonScreen) tile, player);
		}
		if (tile instanceof TileStoneMill) {
			return new GuiStoneMill((TileStoneMill) tile, player.inventory);
		}
		if (tile instanceof TileTeaPot) {
			return new GuiTeaPot((TileTeaPot) tile, player.inventory);
		}
		if (tile instanceof TileFluidProcessorBase) {
			return new GuiFluidProcessor((TileFluidProcessorBase) tile, player.inventory);
		}
		if (tile instanceof TileLowChest) {
			return new GuiLowChest((TileLowChest) tile, player);
		}
		if (tile instanceof TileCookingStove) {
			return new GuiFuelStove((TileCookingStove) tile, player.inventory);
		}
		return null;
	}

	public ModelThinBiped getArmorModel(int slot) {
		return null;
	}

	public boolean hasAchivement(EntityPlayer player, Achievement acv) {
		return player != null && player.hasAchievement(acv);
	}

}
