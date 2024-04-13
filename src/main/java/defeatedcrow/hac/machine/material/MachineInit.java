package defeatedcrow.hac.machine.material;

import java.util.function.Supplier;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.machine.client.gui.BoilerBiomassMenu;
import defeatedcrow.hac.machine.client.gui.CookingPotMenu;
import defeatedcrow.hac.machine.client.gui.EnergyBatteryMenu;
import defeatedcrow.hac.machine.client.gui.FermentationJarMenu;
import defeatedcrow.hac.machine.client.gui.HeatingChamberMenu;
import defeatedcrow.hac.machine.client.gui.MillMenu;
import defeatedcrow.hac.machine.client.gui.PortableTankMenu;
import defeatedcrow.hac.machine.client.gui.TeaPotMenu;
import defeatedcrow.hac.machine.energy.EnergyTankItemDC;
import defeatedcrow.hac.machine.material.block.BatterySmallBlock;
import defeatedcrow.hac.machine.material.block.BoilerBiomassBlock;
import defeatedcrow.hac.machine.material.block.BoilerBiomassTile;
import defeatedcrow.hac.machine.material.block.BrickChamberBlock;
import defeatedcrow.hac.machine.material.block.BrickChamberTile;
import defeatedcrow.hac.machine.material.block.CableCopperBlock;
import defeatedcrow.hac.machine.material.block.CableCopperTile;
import defeatedcrow.hac.machine.material.block.CookingPotBlock;
import defeatedcrow.hac.machine.material.block.CookingPotTile;
import defeatedcrow.hac.machine.material.block.EnergyBatteryTile;
import defeatedcrow.hac.machine.material.block.EnergyGeneratorTile;
import defeatedcrow.hac.machine.material.block.EnergyMachineBaseDC;
import defeatedcrow.hac.machine.material.block.FermentationJarBlock;
import defeatedcrow.hac.machine.material.block.FermentationJarTile;
import defeatedcrow.hac.machine.material.block.FluidPipeAlloyBlock;
import defeatedcrow.hac.machine.material.block.FluidPipeAlloyTile;
import defeatedcrow.hac.machine.material.block.GeneratorSmallBlock;
import defeatedcrow.hac.machine.material.block.HeatingChamberBlock;
import defeatedcrow.hac.machine.material.block.HeatingChamberTile;
import defeatedcrow.hac.machine.material.block.IBCBlock;
import defeatedcrow.hac.machine.material.block.IBCTile;
import defeatedcrow.hac.machine.material.block.PortableCanBlock;
import defeatedcrow.hac.machine.material.block.PortableCanTile;
import defeatedcrow.hac.machine.material.block.PortableFluidTankTile;
import defeatedcrow.hac.machine.material.block.SpileCupBlock;
import defeatedcrow.hac.machine.material.block.StoneMillBlock;
import defeatedcrow.hac.machine.material.block.StoneMillTile;
import defeatedcrow.hac.machine.material.block.TeaPotBlock;
import defeatedcrow.hac.machine.material.block.TeaPotTile;
import defeatedcrow.hac.machine.material.block.WaterPumpBlock;
import defeatedcrow.hac.machine.material.block.WaterPumpTile;
import defeatedcrow.hac.machine.material.fluid.FluidBlockItemDC;
import defeatedcrow.hac.machine.material.item.MachineMaterialItem;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.RegistryObject;

public class MachineInit {

	public static void init() {}

	public static final RegistryObject<Block> CHAMBER_BRICK_A = regBlock("chamber_brick_a", () -> new BrickChamberBlock("chamber_brick_a"), null);
	public static final RegistryObject<Block> CHAMBER_BRICK_B = regBlock("chamber_brick_b", () -> new BrickChamberBlock("chamber_brick_b"), null);
	public static final RegistryObject<Block> CHAMBER_IRON = regBlock("chamber_iron", () -> new HeatingChamberBlock("chamber_iron"), null);

	public static final RegistryObject<Block> PORTABLE_CAN = regFluidBlock("portable_can", () -> new PortableCanBlock("portable_can"), 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_WHITE = regFluidBlock("portable_can_white", () -> new PortableCanBlock("portable_can_white"), 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_BLUE = regFluidBlock("portable_can_blue", () -> new PortableCanBlock("portable_can_blue"), 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_BLACK = regFluidBlock("portable_can_black", () -> new PortableCanBlock("portable_can_black"), 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_RED = regFluidBlock("portable_can_red", () -> new PortableCanBlock("portable_can_red"), 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_GREEN = regFluidBlock("portable_can_green", () -> new PortableCanBlock("portable_can_green"), 18000);

	public static final RegistryObject<Block> IBC = regFluidBlock("ibc", () -> new IBCBlock("ibc"), 1000000);

	public static final RegistryObject<Block> PIPE_BRASS = regBlock("pipe_brass", () -> new FluidPipeAlloyBlock("pipe_brass"), null);

	public static final RegistryObject<Block> SPILE = regBlock("spilecup", () -> new SpileCupBlock("spilecup"), null);

	public static final RegistryObject<Block> COOKING_POT_NORMAL = regBlock("cooking_pot_normal", () -> new CookingPotBlock("cooking_pot_normal"), null);
	public static final RegistryObject<Block> COOKING_POT_WHITE = regBlock("cooking_pot_white", () -> new CookingPotBlock("cooking_pot_white"), null);
	public static final RegistryObject<Block> COOKING_POT_BLUE = regBlock("cooking_pot_blue", () -> new CookingPotBlock("cooking_pot_blue"), null);
	public static final RegistryObject<Block> COOKING_POT_BLACK = regBlock("cooking_pot_black", () -> new CookingPotBlock("cooking_pot_black"), null);
	public static final RegistryObject<Block> COOKING_POT_RED = regBlock("cooking_pot_red", () -> new CookingPotBlock("cooking_pot_red"), null);
	public static final RegistryObject<Block> COOKING_POT_GREEN = regBlock("cooking_pot_green", () -> new CookingPotBlock("cooking_pot_green"), null);

	public static final RegistryObject<Block> TEA_POT_NORMAL = regBlock("tea_pot_normal", () -> new TeaPotBlock("tea_pot_normal"), null);
	public static final RegistryObject<Block> TEA_POT_WHITE = regBlock("tea_pot_white", () -> new TeaPotBlock("tea_pot_white"), null);
	public static final RegistryObject<Block> TEA_POT_BLUE = regBlock("tea_pot_blue", () -> new TeaPotBlock("tea_pot_blue"), null);
	public static final RegistryObject<Block> TEA_POT_BLACK = regBlock("tea_pot_black", () -> new TeaPotBlock("tea_pot_black"), null);
	public static final RegistryObject<Block> TEA_POT_RED = regBlock("tea_pot_red", () -> new TeaPotBlock("tea_pot_red"), null);
	public static final RegistryObject<Block> TEA_POT_GREEN = regBlock("tea_pot_green", () -> new TeaPotBlock("tea_pot_green"), null);

	public static final RegistryObject<Block> FERMANTATION_JAR_NORMAL = regBlock("fermentation_jar_normal", () -> new FermentationJarBlock("fermentation_jar_normal", false), null);
	public static final RegistryObject<Block> FERMANTATION_JAR_WHITE = regBlock("fermentation_jar_white", () -> new FermentationJarBlock("fermentation_jar_white", false), null);
	public static final RegistryObject<Block> FERMANTATION_JAR_BLUE = regBlock("fermentation_jar_blue", () -> new FermentationJarBlock("fermentation_jar_blue", true), null);
	public static final RegistryObject<Block> FERMANTATION_JAR_BLACK = regBlock("fermentation_jar_black", () -> new FermentationJarBlock("fermentation_jar_black", false), null);
	public static final RegistryObject<Block> FERMANTATION_JAR_RED = regBlock("fermentation_jar_red", () -> new FermentationJarBlock("fermentation_jar_red", true), null);
	public static final RegistryObject<Block> FERMANTATION_JAR_GREEN = regBlock("fermentation_jar_green", () -> new FermentationJarBlock("fermentation_jar_green", true), null);

	public static final RegistryObject<Block> STONE_MILL = regBlock("stone_mill", () -> new StoneMillBlock("stone_mill"), null);

	public static final RegistryObject<Block> WATER_PUMP = regBlock("water_pump", () -> new WaterPumpBlock("water_pump"), null);

	public static final RegistryObject<Block> GENERATOR_SMALL = regEnergyBlock("generator_small", () -> new GeneratorSmallBlock("generator_small"), 4000);

	public static final RegistryObject<Block> BOILER_BIOMASS = regBlock("boiler_biomass", () -> new BoilerBiomassBlock("boiler_biomass"), null);

	public static final RegistryObject<Block> BATTERY_SMALL = regEnergyBlock("battery_small", () -> new BatterySmallBlock("battery_small"), 32000);

	public static final RegistryObject<Block> CABLE_COPPER = regBlock("cable_copper", () -> new CableCopperBlock("cable_copper"), TagDC.ItemTag.LEAKAGE_MACHINE);

	public static final RegistryObject<Block> CABLE_COPPER_COATED = regBlock("cable_copper_coated", () -> new CableCopperBlock("cable_copper_coated"), null);

	public static final RegistryObject<Item> MOTOR_TIER1 = regItem("motor_small", () -> new MachineMaterialItem(Rarity.COMMON, "motor_small", TagDC.ItemTag.MOTOR_T1));

	public static final RegistryObject<Item> IMPELLER_TIER1 = regItem("impeller_small", () -> new MachineMaterialItem(Rarity.COMMON, "impeller_small", TagDC.ItemTag.IMPELLER_T1));

	public static final RegistryObject<Item> BATTERYITEM_TIER1 = regItem("batteryitem_small", () -> new MachineMaterialItem(Rarity.COMMON, "batteryitem_small", TagDC.ItemTag.BATTERY_T1));

	// TileEntity
	public static final RegistryObject<BlockEntityType<BrickChamberTile>> CHAMBER_BRICK_TILE = CoreInit.BLOCK_ENTITIES.register("chamber_brick_tile",
		() -> BlockEntityType.Builder.of(BrickChamberTile::new, new Block[] { CHAMBER_BRICK_A.get(), CHAMBER_BRICK_B.get() }).build(null));

	public static final RegistryObject<BlockEntityType<HeatingChamberTile>> CHAMBER_IRON_TILE = CoreInit.BLOCK_ENTITIES.register("chamber_iron_tile",
		() -> BlockEntityType.Builder.of(HeatingChamberTile::new, new Block[] { CHAMBER_IRON.get() }).build(null));

	public static final RegistryObject<BlockEntityType<PortableCanTile>> PORTABLE_CAN_TILE = CoreInit.BLOCK_ENTITIES.register("portable_can_tile",
		() -> BlockEntityType.Builder.of(PortableCanTile::new, new Block[] { PORTABLE_CAN.get(), PORTABLE_CAN_WHITE.get(), PORTABLE_CAN_BLUE.get(), PORTABLE_CAN_BLACK.get(), PORTABLE_CAN_RED.get(), PORTABLE_CAN_GREEN
			.get() }).build(null));

	public static final RegistryObject<BlockEntityType<IBCTile>> IBC_TILE = CoreInit.BLOCK_ENTITIES.register("ibc_tile",
		() -> BlockEntityType.Builder.of(IBCTile::new, new Block[] { IBC.get() }).build(null));

	public static final RegistryObject<BlockEntityType<FluidPipeAlloyTile>> PIPE_BRASS_TILE = CoreInit.BLOCK_ENTITIES.register("pipe_brass_tile",
		() -> BlockEntityType.Builder.of(FluidPipeAlloyTile::new, new Block[] { PIPE_BRASS.get() }).build(null));

	public static final RegistryObject<BlockEntityType<CookingPotTile>> COOKING_POT_TILE = CoreInit.BLOCK_ENTITIES.register("cooking_pot_tile",
		() -> BlockEntityType.Builder.of(CookingPotTile::new, new Block[] { COOKING_POT_NORMAL.get(), COOKING_POT_WHITE.get(), COOKING_POT_BLUE.get(), COOKING_POT_BLACK.get(), COOKING_POT_RED.get(), COOKING_POT_GREEN
			.get() }).build(null));

	public static final RegistryObject<BlockEntityType<TeaPotTile>> TEA_POT_TILE = CoreInit.BLOCK_ENTITIES.register("tea_pot_tile",
		() -> BlockEntityType.Builder.of(TeaPotTile::new, new Block[] { TEA_POT_NORMAL.get(), TEA_POT_WHITE.get(), TEA_POT_BLUE.get(), TEA_POT_BLACK.get(), TEA_POT_RED.get(), TEA_POT_GREEN.get() }).build(null));

	public static final RegistryObject<BlockEntityType<FermentationJarTile>> FERMANTATION_JAR_TILE = CoreInit.BLOCK_ENTITIES.register("fermentation_jar_tile",
		() -> BlockEntityType.Builder.of(FermentationJarTile::new, new Block[] { FERMANTATION_JAR_NORMAL.get(), FERMANTATION_JAR_WHITE.get(), FERMANTATION_JAR_BLUE.get(), FERMANTATION_JAR_BLACK.get(),
			FERMANTATION_JAR_RED.get(), FERMANTATION_JAR_GREEN.get() }).build(null));

	public static final RegistryObject<BlockEntityType<StoneMillTile>> MILL_TILE = CoreInit.BLOCK_ENTITIES.register("mill_tile",
		() -> BlockEntityType.Builder.of(StoneMillTile::new, new Block[] { STONE_MILL.get() }).build(null));

	public static final RegistryObject<BlockEntityType<WaterPumpTile>> WATER_PUMP_TILE = CoreInit.BLOCK_ENTITIES.register("water_pump_tile",
		() -> BlockEntityType.Builder.of(WaterPumpTile::new, new Block[] { WATER_PUMP.get() }).build(null));

	public static final RegistryObject<BlockEntityType<EnergyBatteryTile>> BATTERY_SMALL_TILE = CoreInit.BLOCK_ENTITIES.register("battery_small_tile",
		() -> BlockEntityType.Builder.of(EnergyBatteryTile::new, new Block[] { BATTERY_SMALL.get() }).build(null));

	public static final RegistryObject<BlockEntityType<EnergyGeneratorTile>> GENERATOR_SMALL_TILE = CoreInit.BLOCK_ENTITIES.register("generator_small_tile",
		() -> BlockEntityType.Builder.of(EnergyGeneratorTile::new, new Block[] { GENERATOR_SMALL.get() }).build(null));

	public static final RegistryObject<BlockEntityType<BoilerBiomassTile>> BOILER_BIOMASS_TILE = CoreInit.BLOCK_ENTITIES.register("boiler_biomass_tile",
		() -> BlockEntityType.Builder.of(BoilerBiomassTile::new, new Block[] { BOILER_BIOMASS.get() }).build(null));

	public static final RegistryObject<BlockEntityType<CableCopperTile>> CABLE_COPPER_TILE = CoreInit.BLOCK_ENTITIES.register("cable_copper_tile",
		() -> BlockEntityType.Builder.of(CableCopperTile::new, new Block[] { CABLE_COPPER.get(), CABLE_COPPER_COATED.get() }).build(null));

	// Menu
	public static final RegistryObject<MenuType<HeatingChamberMenu>> CHAMBER_MENU = CoreInit.register("dcs_chamber_item", (IContainerFactory<HeatingChamberMenu>) (id, playerInv, data) -> {
		HeatingChamberTile cont = (HeatingChamberTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return HeatingChamberMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<PortableTankMenu>> FLUID_MENU = CoreInit.register("dcs_fluid_tank", (IContainerFactory<PortableTankMenu>) (id, playerInv, data) -> {
		PortableFluidTankTile cont = (PortableFluidTankTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return PortableTankMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<PortableTankMenu>> FLUID_MENU_LARGE = CoreInit.register("dcs_fluid_tank_large", (IContainerFactory<PortableTankMenu>) (id, playerInv, data) -> {
		PortableFluidTankTile cont = (PortableFluidTankTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return PortableTankMenu.getLargeMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<CookingPotMenu>> POT_MENU = CoreInit.register("dcs_cooking_pot", (IContainerFactory<CookingPotMenu>) (id, playerInv, data) -> {
		CookingPotTile cont = (CookingPotTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return CookingPotMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<FermentationJarMenu>> JAR_MENU = CoreInit.register("dcs_fermentation_jar", (IContainerFactory<FermentationJarMenu>) (id, playerInv, data) -> {
		FermentationJarTile cont = (FermentationJarTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return FermentationJarMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<TeaPotMenu>> TEA_POT_MENU = CoreInit.register("dcs_tea_pot", (IContainerFactory<TeaPotMenu>) (id, playerInv, data) -> {
		TeaPotTile cont = (TeaPotTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return TeaPotMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<MillMenu>> MILL_MENU = CoreInit.register("dcs_pulveriser", (IContainerFactory<MillMenu>) (id, playerInv, data) -> {
		StoneMillTile cont = (StoneMillTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return MillMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<EnergyBatteryMenu>> BATTERY_MENU = CoreInit.register("dcs_battery", (IContainerFactory<EnergyBatteryMenu>) (id, playerInv, data) -> {
		EnergyMachineBaseDC cont = (EnergyMachineBaseDC) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return EnergyBatteryMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<EnergyBatteryMenu>> GENERATOR_MENU = CoreInit.register("dcs_generator", (IContainerFactory<EnergyBatteryMenu>) (id, playerInv, data) -> {
		EnergyMachineBaseDC cont = (EnergyMachineBaseDC) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return EnergyBatteryMenu.getGeneratorMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<BoilerBiomassMenu>> BOILER_BIOMASS_MENU = CoreInit.register("dcs_boiler_biomass", (IContainerFactory<BoilerBiomassMenu>) (id, playerInv, data) -> {
		BoilerBiomassTile cont = (BoilerBiomassTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return BoilerBiomassMenu.getMenu(id, playerInv, cont);
	});

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("machine/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(CoreInit.MACHINE), tag));
		return obj;
	}

	public static RegistryObject<Block> regFluidBlock(String name, Supplier<Block> block, int cap) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("machine/" + name, block);
		regItem(name, () -> new FluidBlockItemDC(name, obj.get(), new Item.Properties().tab(CoreInit.MACHINE), null).setCap(cap));
		return obj;
	}

	public static RegistryObject<Block> regEnergyBlock(String name, Supplier<Block> block, int cap) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("machine/" + name, block);
		regItem(name, () -> new EnergyTankItemDC(name, obj.get(), new Item.Properties().tab(CoreInit.MACHINE), null).setCap(cap));
		return obj;
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("machine/" + name, item);
	}

}
