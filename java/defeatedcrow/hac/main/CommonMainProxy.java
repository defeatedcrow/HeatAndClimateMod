package defeatedcrow.hac.main;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.food.block.TileFluidProcessorBase;
import defeatedcrow.hac.food.block.TilePotteryPot;
import defeatedcrow.hac.food.block.TileSteelPot;
import defeatedcrow.hac.food.gui.ContainerFluidProcessor;
import defeatedcrow.hac.food.gui.GuiFluidProcessor;
import defeatedcrow.hac.food.recipes.FoodRecipes;
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
import defeatedcrow.hac.machine.gui.ContainerStoneMill;
import defeatedcrow.hac.machine.gui.GuiStoneMill;
import defeatedcrow.hac.machine.recipes.MachineRecipes;
import defeatedcrow.hac.main.block.device.TileNormalChamber;
import defeatedcrow.hac.main.block.device.TileShitirin;
import defeatedcrow.hac.main.block.device.TileStevensonScreen;
import defeatedcrow.hac.main.client.gui.ContainerNormalChamber;
import defeatedcrow.hac.main.client.gui.ContainerStevensonScreen;
import defeatedcrow.hac.main.client.gui.GuiNormalChamber;
import defeatedcrow.hac.main.client.gui.GuiStevensonScreen;
import defeatedcrow.hac.main.event.OnDeathEventDC;
import defeatedcrow.hac.main.event.OnMiningEventDC;
import defeatedcrow.hac.main.recipes.BasicRecipeRegister;
import defeatedcrow.hac.main.recipes.MachineRecipeRegister;
import defeatedcrow.hac.main.recipes.OreDicRegister;
import defeatedcrow.hac.main.worldgen.WorldGenOres;
import defeatedcrow.hac.main.worldgen.WorldGenSkarn;

public class CommonMainProxy implements IGuiHandler {

	public void loadConst() {
	}

	public void loadMaterial() {
		MainMaterialRegister.load();
		GameRegistry.registerFuelHandler(new DCFuelHandler());
	}

	public void loadRecipes() {
		OreDicRegister.load();
		BasicRecipeRegister.load();
		MachineRecipeRegister.load();

		FoodRecipes.load();
		MachineRecipes.load();
	}

	public void loadEntity() {
		MainEntityRegister.load();
	}

	public void loadTE() {
		GameRegistry.registerTileEntity(TileNormalChamber.class, "dcs_te_chamber_normal");
		GameRegistry.registerTileEntity(TileShitirin.class, "dcs_te_shitirin");
		GameRegistry.registerTileEntity(TileWindmill.class, "dcs_te_windmill");
		GameRegistry.registerTileEntity(TileWindmill_L.class, "dcs_te_windmill_l");
		GameRegistry.registerTileEntity(TileShaft_S.class, "dcs_te_shaft_s");
		GameRegistry.registerTileEntity(TileShaft_L.class, "dcs_te_shaft_l");
		GameRegistry.registerTileEntity(TileShaft_TA.class, "dcs_te_shaft_ta");
		GameRegistry.registerTileEntity(TileShaft_TB.class, "dcs_te_shaft_tb");
		GameRegistry.registerTileEntity(TileGearBox.class, "dcs_te_gearbox");
		GameRegistry.registerTileEntity(TileCrank_S.class, "dcs_te_crank_s");
		GameRegistry.registerTileEntity(TileHandCrank.class, "dcs_te_handcrank");
		GameRegistry.registerTileEntity(TileStoneMill.class, "dcs_te_stonemill");
		GameRegistry.registerTileEntity(TileRedBox.class, "dcs_te_redbox");
		GameRegistry.registerTileEntity(TileFan.class, "dcs_te_fan");
		GameRegistry.registerTileEntity(TileStevensonScreen.class, "dcs_te_stevenson_screen");
		GameRegistry.registerTileEntity(TilePotteryPot.class, "dcs_te_pottery_pot");
		GameRegistry.registerTileEntity(TileSteelPot.class, "dcs_te_steel_pot");
	}

	public void loadWorldGen() {
		// gen
		GameRegistry.registerWorldGenerator(new WorldGenOres(), 2);
		GameRegistry.registerWorldGenerator(new WorldGenSkarn(false), 3);
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

	public void loadInit() {
		MinecraftForge.EVENT_BUS.register(new OnMiningEventDC());
		MinecraftForge.EVENT_BUS.register(new OnDeathEventDC());
		// MinecraftForge.EVENT_BUS.register(new OnCraftingDC());
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
		if (tile instanceof TileFluidProcessorBase) {
			return new ContainerFluidProcessor((TileFluidProcessorBase) tile, player.inventory);
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
		if (tile instanceof TileFluidProcessorBase) {
			return new GuiFluidProcessor((TileFluidProcessorBase) tile, player.inventory);
		}
		return null;
	}

}
