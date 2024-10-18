package defeatedcrow.hac.machine.material;

import java.util.function.Supplier;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.machine.client.gui.BoilerBiomassMenu;
import defeatedcrow.hac.machine.client.gui.ConveyorSorterMenu;
import defeatedcrow.hac.machine.client.gui.CookingPotMenu;
import defeatedcrow.hac.machine.client.gui.EnergyBatteryMenu;
import defeatedcrow.hac.machine.client.gui.FermentationJarMenu;
import defeatedcrow.hac.machine.client.gui.FluidChamberMenu;
import defeatedcrow.hac.machine.client.gui.HeatingChamberMenu;
import defeatedcrow.hac.machine.client.gui.HopperFilterMenu;
import defeatedcrow.hac.machine.client.gui.KichenBenchMenu;
import defeatedcrow.hac.machine.client.gui.MillMenu;
import defeatedcrow.hac.machine.client.gui.MonitorAndonMenu;
import defeatedcrow.hac.machine.client.gui.PortableTankMenu;
import defeatedcrow.hac.machine.client.gui.RollCrusherMenu;
import defeatedcrow.hac.machine.client.gui.TeaPotMenu;
import defeatedcrow.hac.machine.energy.EnergyTankItemDC;
import defeatedcrow.hac.machine.material.block.machine.BoilerBiomassBlock;
import defeatedcrow.hac.machine.material.block.machine.BoilerBiomassTile;
import defeatedcrow.hac.machine.material.block.machine.BrickChamberBlock;
import defeatedcrow.hac.machine.material.block.machine.BrickChamberTile;
import defeatedcrow.hac.machine.material.block.machine.CookingPotBlock;
import defeatedcrow.hac.machine.material.block.machine.CookingPotTile;
import defeatedcrow.hac.machine.material.block.machine.CropAspiratorBlock;
import defeatedcrow.hac.machine.material.block.machine.EnergyGeneratorTile;
import defeatedcrow.hac.machine.material.block.machine.EnergyMachineBaseDC;
import defeatedcrow.hac.machine.material.block.machine.ExhaustVentBlock;
import defeatedcrow.hac.machine.material.block.machine.ExhaustVentTile;
import defeatedcrow.hac.machine.material.block.machine.FermentationJarBlock;
import defeatedcrow.hac.machine.material.block.machine.FermentationJarTile;
import defeatedcrow.hac.machine.material.block.machine.FluidChamberBlock;
import defeatedcrow.hac.machine.material.block.machine.FluidChamberTile;
import defeatedcrow.hac.machine.material.block.machine.GeneratorSmallBlock;
import defeatedcrow.hac.machine.material.block.machine.HeatingChamberBlock;
import defeatedcrow.hac.machine.material.block.machine.HeatingChamberTile;
import defeatedcrow.hac.machine.material.block.machine.HydroTurbineBlock;
import defeatedcrow.hac.machine.material.block.machine.HydroTurbineTile;
import defeatedcrow.hac.machine.material.block.machine.IntakeFanBlock;
import defeatedcrow.hac.machine.material.block.machine.IntakeFanTile;
import defeatedcrow.hac.machine.material.block.machine.ItemAspiratorBlock;
import defeatedcrow.hac.machine.material.block.machine.ItemAspiratorTile;
import defeatedcrow.hac.machine.material.block.machine.KichenBenchBlock;
import defeatedcrow.hac.machine.material.block.machine.KichenBenchTile;
import defeatedcrow.hac.machine.material.block.machine.RollCrusherBlock;
import defeatedcrow.hac.machine.material.block.machine.RollCrusherTile;
import defeatedcrow.hac.machine.material.block.machine.SpileCupBlock;
import defeatedcrow.hac.machine.material.block.machine.StoneMillBlock;
import defeatedcrow.hac.machine.material.block.machine.StoneMillTile;
import defeatedcrow.hac.machine.material.block.machine.TeaPotBlock;
import defeatedcrow.hac.machine.material.block.machine.TeaPotTile;
import defeatedcrow.hac.machine.material.block.machine.WaterPumpBlock;
import defeatedcrow.hac.machine.material.block.machine.WaterPumpTile;
import defeatedcrow.hac.machine.material.block.monitor.MonitorAndonBlock;
import defeatedcrow.hac.machine.material.block.monitor.MonitorAndonPanelBlock;
import defeatedcrow.hac.machine.material.block.monitor.MonitorAndonTile;
import defeatedcrow.hac.machine.material.block.monitor.MonitorBlockItem;
import defeatedcrow.hac.machine.material.block.monitor.MonitorComparatorBlock;
import defeatedcrow.hac.machine.material.block.monitor.MonitorComparatorTile;
import defeatedcrow.hac.machine.material.block.monitor.MonitorEnergyBlock;
import defeatedcrow.hac.machine.material.block.monitor.MonitorEnergyTile;
import defeatedcrow.hac.machine.material.block.monitor.MonitorRSBlock;
import defeatedcrow.hac.machine.material.block.monitor.MonitorRSTile;
import defeatedcrow.hac.machine.material.block.monitor.MonitorTempBlock;
import defeatedcrow.hac.machine.material.block.monitor.MonitorTempTile;
import defeatedcrow.hac.machine.material.block.monitor.StormglassBlock;
import defeatedcrow.hac.machine.material.block.monitor.StormglassTile;
import defeatedcrow.hac.machine.material.block.transport.BatteryMiddleBlock;
import defeatedcrow.hac.machine.material.block.transport.BatterySmallBlock;
import defeatedcrow.hac.machine.material.block.transport.CableCopperBlock;
import defeatedcrow.hac.machine.material.block.transport.CableCopperTile;
import defeatedcrow.hac.machine.material.block.transport.ConveyorDropperBlock;
import defeatedcrow.hac.machine.material.block.transport.ConveyorDropperTile;
import defeatedcrow.hac.machine.material.block.transport.ConveyorFillerBlock;
import defeatedcrow.hac.machine.material.block.transport.ConveyorFillerTile;
import defeatedcrow.hac.machine.material.block.transport.ConveyorNormalBlock;
import defeatedcrow.hac.machine.material.block.transport.ConveyorSmeltingBlock;
import defeatedcrow.hac.machine.material.block.transport.ConveyorSmeltingTile;
import defeatedcrow.hac.machine.material.block.transport.ConveyorSortingBlock;
import defeatedcrow.hac.machine.material.block.transport.ConveyorSortingTile;
import defeatedcrow.hac.machine.material.block.transport.ConveyorTile;
import defeatedcrow.hac.machine.material.block.transport.EnergyBatteryTile;
import defeatedcrow.hac.machine.material.block.transport.EnergyMiddleBatteryTile;
import defeatedcrow.hac.machine.material.block.transport.FaucetBlock;
import defeatedcrow.hac.machine.material.block.transport.FaucetTile;
import defeatedcrow.hac.machine.material.block.transport.FluidPipeAlloyBlock;
import defeatedcrow.hac.machine.material.block.transport.FluidPipeAlloyTile;
import defeatedcrow.hac.machine.material.block.transport.FluidSinkBlock_Brick;
import defeatedcrow.hac.machine.material.block.transport.FluidSinkTile;
import defeatedcrow.hac.machine.material.block.transport.HopperBaseTile;
import defeatedcrow.hac.machine.material.block.transport.HopperFilterBlock;
import defeatedcrow.hac.machine.material.block.transport.HopperFilterGoldBlock;
import defeatedcrow.hac.machine.material.block.transport.HopperFilterGoldTile;
import defeatedcrow.hac.machine.material.block.transport.HopperFilterTile;
import defeatedcrow.hac.machine.material.block.transport.HopperGoldBlock;
import defeatedcrow.hac.machine.material.block.transport.HopperGoldTile;
import defeatedcrow.hac.machine.material.block.transport.IBCBlock;
import defeatedcrow.hac.machine.material.block.transport.IBCTile;
import defeatedcrow.hac.machine.material.block.transport.PortableCanBlock;
import defeatedcrow.hac.machine.material.block.transport.PortableCanTile;
import defeatedcrow.hac.machine.material.block.transport.PortableFluidTankTile;
import defeatedcrow.hac.machine.material.fluid.FluidBlockItemDC;
import defeatedcrow.hac.machine.material.item.MachineMaterialItem;
import defeatedcrow.hac.machine.material.item.MemoryCoordItem;
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
	public static final RegistryObject<Block> FUEL_BURNER = regBlock("fuel_burner", () -> new FluidChamberBlock("fuel_burner"), null);

	public static final RegistryObject<Block> KICHEN_BENCH_BRICK = regBlock("kichen_bench_brick", () -> new KichenBenchBlock("kichen_bench_brick"), null);

	public static final RegistryObject<Block> KICHEN_SINK_BRICK = regBlock("fluid_sink_brick", () -> new FluidSinkBlock_Brick("fluid_sink_brick", false), null);
	public static final RegistryObject<Block> HALF_SINK_BRICK = regBlock("half_sink_brick", () -> new FluidSinkBlock_Brick("half_sink_brick", true), null);

	public static final RegistryObject<Block> HOPPER_FILTER = regBlock("hopper_filter", () -> new HopperFilterBlock("hopper_filter"), null);
	public static final RegistryObject<Block> HOPPER_GOLD = regBlock("hopper_gold", () -> new HopperGoldBlock("hopper_gold"), null);
	public static final RegistryObject<Block> HOPPER_FILTER_GOLD = regBlock("hopper_filter_gold", () -> new HopperFilterGoldBlock("hopper_filter_gold"), null);

	public static final RegistryObject<Block> CONVEYOR = regBlock("conveyor", () -> new ConveyorNormalBlock("conveyor"), null);
	public static final RegistryObject<Block> CONVEYOR_SMELTING = regBlock("conveyor_smelting", () -> new ConveyorSmeltingBlock("conveyor_smelting"), null);
	public static final RegistryObject<Block> CONVEYOR_DROPPER = regBlock("conveyor_dropper", () -> new ConveyorDropperBlock("conveyor_dropper"), null);
	public static final RegistryObject<Block> CONVEYOR_SORTER = regBlock("conveyor_sorter", () -> new ConveyorSortingBlock("conveyor_sorter"), null);
	public static final RegistryObject<Block> CONVEYOR_FILLER = regBlock("conveyor_filler", () -> new ConveyorFillerBlock("conveyor_filler"), null);

	public static final RegistryObject<Block> PORTABLE_CAN = regFluidBlock("portable_can", () -> new PortableCanBlock("portable_can"), 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_WHITE = regFluidBlock("portable_can_white", () -> new PortableCanBlock("portable_can_white"), 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_BLUE = regFluidBlock("portable_can_blue", () -> new PortableCanBlock("portable_can_blue"), 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_BLACK = regFluidBlock("portable_can_black", () -> new PortableCanBlock("portable_can_black"), 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_RED = regFluidBlock("portable_can_red", () -> new PortableCanBlock("portable_can_red"), 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_GREEN = regFluidBlock("portable_can_green", () -> new PortableCanBlock("portable_can_green"), 18000);

	public static final RegistryObject<Block> IBC = regFluidBlock("ibc", () -> new IBCBlock("ibc"), 1000000);

	public static final RegistryObject<Block> PIPE_BRASS = regBlock("pipe_brass", () -> new FluidPipeAlloyBlock("pipe_brass"), null);

	public static final RegistryObject<Block> FAUCET_A = regBlock("faucet_a", () -> new FaucetBlock("faucet_a"), null);
	public static final RegistryObject<Block> FAUCET_B = regBlock("faucet_b", () -> new FaucetBlock("faucet_b"), null);
	public static final RegistryObject<Block> FAUCET_C = regBlock("faucet_c", () -> new FaucetBlock("faucet_c"), null);
	public static final RegistryObject<Block> FAUCET_D = regBlock("faucet_d", () -> new FaucetBlock("faucet_d"), null);

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

	public static final RegistryObject<Block> WATER_PUMP = regBlock("water_pump", () -> new WaterPumpBlock("water_pump"), null);
	public static final RegistryObject<Block> STONE_MILL = regBlock("stone_mill", () -> new StoneMillBlock("stone_mill"), null);
	public static final RegistryObject<Block> ROLL_CRUSHER = regBlock("roll_crusher", () -> new RollCrusherBlock("roll_crusher"), null);
	public static final RegistryObject<Block> INTAKE_FAN = regBlock("intake_fan", () -> new IntakeFanBlock("intake_fan"), null);
	public static final RegistryObject<Block> EXHAUST_VENT = regBlock("exhaust_vent", () -> new ExhaustVentBlock("exhaust_vent"), null);
	public static final RegistryObject<Block> ITEM_ASPIRATOR = regBlock("item_aspirator", () -> new ItemAspiratorBlock("item_aspirator"), null);
	public static final RegistryObject<Block> CROP_ASPIRATOR = regBlock("crop_aspirator", () -> new CropAspiratorBlock("crop_aspirator"), null);

	public static final RegistryObject<Block> GENERATOR_SMALL = regEnergyBlock("generator_small", () -> new GeneratorSmallBlock("generator_small"), 4000);

	public static final RegistryObject<Block> BOILER_BIOMASS = regBlock("boiler_biomass", () -> new BoilerBiomassBlock("boiler_biomass"), null);
	public static final RegistryObject<Block> HYDRO_TURBINE = regBlock("hydro_turbine", () -> new HydroTurbineBlock("hydro_turbine"), null);

	public static final RegistryObject<Block> BATTERY_SMALL = regEnergyBlock("battery_small", () -> new BatterySmallBlock("battery_small"), 32000);
	public static final RegistryObject<Block> BATTERY_MIDDLE = regEnergyBlock("battery_middle", () -> new BatteryMiddleBlock("battery_small"), 128000);

	public static final RegistryObject<Block> CABLE_COPPER = regBlock("cable_copper", () -> new CableCopperBlock("cable_copper"), TagDC.ItemTag.LEAKAGE_MACHINE);

	public static final RegistryObject<Block> CABLE_COPPER_COATED = regBlock("cable_copper_coated", () -> new CableCopperBlock("cable_copper_coated"), null);

	public static final RegistryObject<Block> MONITOR_RS = regMonitorBlock("monitor_rs_lamp", () -> new MonitorRSBlock("monitor_rs_lamp"));
	public static final RegistryObject<Block> MONITOR_RS_PILOT = regMonitorBlock("monitor_rs_pilot", () -> new MonitorRSBlock("monitor_rs_pilot"));
	public static final RegistryObject<Block> MONITOR_COMPARATOR = regMonitorBlock("monitor_comparator_lamp", () -> new MonitorComparatorBlock("monitor_comparator_lamp"));
	public static final RegistryObject<Block> MONITOR_ANDON_LAMP = regMonitorBlock("monitor_andon_lamp", () -> new MonitorAndonBlock("monitor_andon_lamp"));
	public static final RegistryObject<Block> MONITOR_TEMP = regMonitorBlock("monitor_temp", () -> new MonitorTempBlock("monitor_temp"));
	public static final RegistryObject<Block> MONITOR_ENERGY = regMonitorBlock("monitor_energy", () -> new MonitorEnergyBlock("monitor_energy"));

	public static final RegistryObject<Block> MONITOR_ANDON_PANEL_1 = regMonitorBlock("monitor_andon_panel_1", () -> new MonitorAndonPanelBlock(1));
	public static final RegistryObject<Block> MONITOR_ANDON_PANEL_2 = regMonitorBlock("monitor_andon_panel_2", () -> new MonitorAndonPanelBlock(2));
	public static final RegistryObject<Block> MONITOR_ANDON_PANEL_3 = regMonitorBlock("monitor_andon_panel_3", () -> new MonitorAndonPanelBlock(3));
	public static final RegistryObject<Block> MONITOR_ANDON_PANEL_4 = regMonitorBlock("monitor_andon_panel_4", () -> new MonitorAndonPanelBlock(4));
	public static final RegistryObject<Block> MONITOR_ANDON_PANEL_5 = regMonitorBlock("monitor_andon_panel_5", () -> new MonitorAndonPanelBlock(5));
	public static final RegistryObject<Block> MONITOR_ANDON_PANEL_6 = regMonitorBlock("monitor_andon_panel_6", () -> new MonitorAndonPanelBlock(6));
	public static final RegistryObject<Block> MONITOR_ANDON_PANEL_7 = regMonitorBlock("monitor_andon_panel_7", () -> new MonitorAndonPanelBlock(7));
	public static final RegistryObject<Block> MONITOR_ANDON_PANEL_8 = regMonitorBlock("monitor_andon_panel_8", () -> new MonitorAndonPanelBlock(8));
	public static final RegistryObject<Block> MONITOR_ANDON_PANEL_9 = regMonitorBlock("monitor_andon_panel_9", () -> new MonitorAndonPanelBlock(9));

	public static final RegistryObject<Block> STORMGLASS = regBlock("stormglass", () -> new StormglassBlock("stormglass"), null);

	public static final RegistryObject<Item> MOTOR_TIER1 = regItem("motor_small", () -> new MachineMaterialItem(Rarity.COMMON, "motor_small", TagDC.ItemTag.MOTOR_T1));
	public static final RegistryObject<Item> MOTOR_TIER2 = regItem("motor_middle", () -> new MachineMaterialItem(Rarity.UNCOMMON, "motor_middle", TagDC.ItemTag.MOTOR_T2));

	public static final RegistryObject<Item> IMPELLER_TIER1 = regItem("impeller_small", () -> new MachineMaterialItem(Rarity.COMMON, "impeller_small", TagDC.ItemTag.IMPELLER_T1));
	public static final RegistryObject<Item> IMPELLER_TIER2 = regItem("impeller_middle", () -> new MachineMaterialItem(Rarity.UNCOMMON, "impeller_middle", TagDC.ItemTag.IMPELLER_T2));

	public static final RegistryObject<Item> BATTERYITEM_TIER1 = regItem("batteryitem_small", () -> new MachineMaterialItem(Rarity.COMMON, "batteryitem_small", TagDC.ItemTag.BATTERY_T1));
	public static final RegistryObject<Item> BATTERYITEM_TIER2 = regItem("batteryitem_middle", () -> new MachineMaterialItem(Rarity.UNCOMMON, "batteryitem_middle", TagDC.ItemTag.BATTERY_T2));

	public static final RegistryObject<Item> BLADE_SUS = regItem("crusher_blade_sanitary", () -> new MachineMaterialItem(Rarity.UNCOMMON, "crusher_blade_sanitary", TagDC.ItemTag.BLADE_SANITARY));
	public static final RegistryObject<Item> BLADE_ALUMINA = regItem("crusher_blade_alumina", () -> new MachineMaterialItem(Rarity.RARE, "crusher_blade_alumina", TagDC.ItemTag.BLADE_ALUMINA));
	public static final RegistryObject<Item> BLADE_SCREEN = regItem("crusher_blade_screen", () -> new MachineMaterialItem(Rarity.UNCOMMON, "crusher_blade_screen", TagDC.ItemTag.BLADE_SCREEN));

	public static final RegistryObject<Item> MEMORY_COORD = regItem("memory_coord", () -> new MemoryCoordItem(Rarity.COMMON, "memory_coord"));

	// TileEntity
	public static final RegistryObject<BlockEntityType<BrickChamberTile>> CHAMBER_BRICK_TILE = CoreInit.BLOCK_ENTITIES.register("chamber_brick_tile",
			() -> BlockEntityType.Builder.of(BrickChamberTile::new, new Block[] { CHAMBER_BRICK_A.get(), CHAMBER_BRICK_B.get() }).build(null));

	public static final RegistryObject<BlockEntityType<HeatingChamberTile>> CHAMBER_IRON_TILE = CoreInit.BLOCK_ENTITIES.register("chamber_iron_tile",
			() -> BlockEntityType.Builder.of(HeatingChamberTile::new, new Block[] { CHAMBER_IRON.get() }).build(null));

	public static final RegistryObject<BlockEntityType<FluidChamberTile>> FUEL_BURNER_TILE = CoreInit.BLOCK_ENTITIES.register("fuel_burner_tile",
			() -> BlockEntityType.Builder.of(FluidChamberTile::new, new Block[] { FUEL_BURNER.get() }).build(null));

	public static final RegistryObject<BlockEntityType<HopperFilterTile>> HOPPER_FILTER_TILE = CoreInit.BLOCK_ENTITIES.register("hopper_filter_tile",
			() -> BlockEntityType.Builder.of(HopperFilterTile::new, new Block[] { HOPPER_FILTER.get() }).build(null));

	public static final RegistryObject<BlockEntityType<HopperGoldTile>> HOPPER_GOLD_TILE = CoreInit.BLOCK_ENTITIES.register("hopper_gold_tile",
			() -> BlockEntityType.Builder.of(HopperGoldTile::new, new Block[] { HOPPER_GOLD.get() }).build(null));

	public static final RegistryObject<BlockEntityType<HopperFilterGoldTile>> HOPPER_FILTER_GOLD_TILE = CoreInit.BLOCK_ENTITIES.register("hopper_filter_gold_tile",
			() -> BlockEntityType.Builder.of(HopperFilterGoldTile::new, new Block[] { HOPPER_FILTER_GOLD.get() }).build(null));

	public static final RegistryObject<BlockEntityType<ConveyorTile>> CONVEYOR_TILE = CoreInit.BLOCK_ENTITIES.register("conveyor_tile",
			() -> BlockEntityType.Builder.of(ConveyorTile::new, new Block[] { CONVEYOR.get() }).build(null));

	public static final RegistryObject<BlockEntityType<ConveyorSmeltingTile>> CONVEYOR_SMELTING_TILE = CoreInit.BLOCK_ENTITIES.register("conveyor_smelting_tile",
			() -> BlockEntityType.Builder.of(ConveyorSmeltingTile::new, new Block[] { CONVEYOR_SMELTING.get() }).build(null));

	public static final RegistryObject<BlockEntityType<ConveyorDropperTile>> CONVEYOR_DROPPER_TILE = CoreInit.BLOCK_ENTITIES.register("conveyor_dropper_tile",
			() -> BlockEntityType.Builder.of(ConveyorDropperTile::new, new Block[] { CONVEYOR_DROPPER.get() }).build(null));

	public static final RegistryObject<BlockEntityType<ConveyorSortingTile>> CONVEYOR_SORTER_TILE = CoreInit.BLOCK_ENTITIES.register("conveyor_sorter_tile",
			() -> BlockEntityType.Builder.of(ConveyorSortingTile::new, new Block[] { CONVEYOR_SORTER.get() }).build(null));

	public static final RegistryObject<BlockEntityType<ConveyorFillerTile>> CONVEYOR_FILLER_TILE = CoreInit.BLOCK_ENTITIES.register("conveyor_filler_tile",
			() -> BlockEntityType.Builder.of(ConveyorFillerTile::new, new Block[] { CONVEYOR_FILLER.get() }).build(null));

	public static final RegistryObject<BlockEntityType<PortableCanTile>> PORTABLE_CAN_TILE = CoreInit.BLOCK_ENTITIES.register("portable_can_tile",
			() -> BlockEntityType.Builder.of(PortableCanTile::new, new Block[] { PORTABLE_CAN.get(), PORTABLE_CAN_WHITE.get(), PORTABLE_CAN_BLUE.get(), PORTABLE_CAN_BLACK.get(), PORTABLE_CAN_RED
					.get(), PORTABLE_CAN_GREEN
							.get() }).build(null));

	public static final RegistryObject<BlockEntityType<IBCTile>> IBC_TILE = CoreInit.BLOCK_ENTITIES.register("ibc_tile",
			() -> BlockEntityType.Builder.of(IBCTile::new, new Block[] { IBC.get() }).build(null));

	public static final RegistryObject<BlockEntityType<FluidPipeAlloyTile>> PIPE_BRASS_TILE = CoreInit.BLOCK_ENTITIES.register("pipe_brass_tile",
			() -> BlockEntityType.Builder.of(FluidPipeAlloyTile::new, new Block[] { PIPE_BRASS.get() }).build(null));

	public static final RegistryObject<BlockEntityType<FaucetTile>> FAUCET_TILE = CoreInit.BLOCK_ENTITIES.register("faucet_tile",
			() -> BlockEntityType.Builder.of(FaucetTile::new, new Block[] { FAUCET_A.get(), FAUCET_B.get(), FAUCET_C.get(), FAUCET_D.get() }).build(null));

	public static final RegistryObject<BlockEntityType<CookingPotTile>> COOKING_POT_TILE = CoreInit.BLOCK_ENTITIES.register("cooking_pot_tile",
			() -> BlockEntityType.Builder.of(CookingPotTile::new, new Block[] { COOKING_POT_NORMAL.get(), COOKING_POT_WHITE.get(), COOKING_POT_BLUE.get(), COOKING_POT_BLACK.get(), COOKING_POT_RED
					.get(), COOKING_POT_GREEN
							.get() }).build(null));

	public static final RegistryObject<BlockEntityType<TeaPotTile>> TEA_POT_TILE = CoreInit.BLOCK_ENTITIES.register("tea_pot_tile",
			() -> BlockEntityType.Builder.of(TeaPotTile::new, new Block[] { TEA_POT_NORMAL.get(), TEA_POT_WHITE.get(), TEA_POT_BLUE.get(), TEA_POT_BLACK.get(), TEA_POT_RED.get(), TEA_POT_GREEN
					.get() }).build(null));

	public static final RegistryObject<BlockEntityType<FermentationJarTile>> FERMANTATION_JAR_TILE = CoreInit.BLOCK_ENTITIES.register("fermentation_jar_tile",
			() -> BlockEntityType.Builder.of(FermentationJarTile::new, new Block[] { FERMANTATION_JAR_NORMAL.get(), FERMANTATION_JAR_WHITE.get(), FERMANTATION_JAR_BLUE.get(), FERMANTATION_JAR_BLACK
					.get(),
				FERMANTATION_JAR_RED.get(), FERMANTATION_JAR_GREEN.get() }).build(null));

	public static final RegistryObject<BlockEntityType<StoneMillTile>> MILL_TILE = CoreInit.BLOCK_ENTITIES.register("mill_tile",
			() -> BlockEntityType.Builder.of(StoneMillTile::new, new Block[] { STONE_MILL.get() }).build(null));

	public static final RegistryObject<BlockEntityType<RollCrusherTile>> CRUSHER_TILE = CoreInit.BLOCK_ENTITIES.register("roll_crusher_tile",
			() -> BlockEntityType.Builder.of(RollCrusherTile::new, new Block[] { ROLL_CRUSHER.get() }).build(null));

	public static final RegistryObject<BlockEntityType<WaterPumpTile>> WATER_PUMP_TILE = CoreInit.BLOCK_ENTITIES.register("water_pump_tile",
			() -> BlockEntityType.Builder.of(WaterPumpTile::new, new Block[] { WATER_PUMP.get() }).build(null));

	public static final RegistryObject<BlockEntityType<IntakeFanTile>> INTAKE_FAN_TILE = CoreInit.BLOCK_ENTITIES.register("intake_fan_tile",
			() -> BlockEntityType.Builder.of(IntakeFanTile::new, new Block[] { INTAKE_FAN.get() }).build(null));

	public static final RegistryObject<BlockEntityType<ExhaustVentTile>> EXHAUST_VENT_TILE = CoreInit.BLOCK_ENTITIES.register("exhaust_vent_tile",
			() -> BlockEntityType.Builder.of(ExhaustVentTile::new, new Block[] { EXHAUST_VENT.get() }).build(null));

	public static final RegistryObject<BlockEntityType<ItemAspiratorTile>> ITEM_ASPIRATOR_TILE = CoreInit.BLOCK_ENTITIES.register("item_aspirator_tile",
			() -> BlockEntityType.Builder.of(ItemAspiratorTile::new, new Block[] { ITEM_ASPIRATOR.get(), CROP_ASPIRATOR.get() }).build(null));

	public static final RegistryObject<BlockEntityType<EnergyBatteryTile>> BATTERY_SMALL_TILE = CoreInit.BLOCK_ENTITIES.register("battery_small_tile",
			() -> BlockEntityType.Builder.of(EnergyBatteryTile::new, new Block[] { BATTERY_SMALL.get() }).build(null));

	public static final RegistryObject<BlockEntityType<EnergyMiddleBatteryTile>> BATTERY_MIDDLE_TILE = CoreInit.BLOCK_ENTITIES.register("battery_middle_tile",
			() -> BlockEntityType.Builder.of(EnergyMiddleBatteryTile::new, new Block[] { BATTERY_MIDDLE.get() }).build(null));

	public static final RegistryObject<BlockEntityType<EnergyGeneratorTile>> GENERATOR_SMALL_TILE = CoreInit.BLOCK_ENTITIES.register("generator_small_tile",
			() -> BlockEntityType.Builder.of(EnergyGeneratorTile::new, new Block[] { GENERATOR_SMALL.get() }).build(null));

	public static final RegistryObject<BlockEntityType<BoilerBiomassTile>> BOILER_BIOMASS_TILE = CoreInit.BLOCK_ENTITIES.register("boiler_biomass_tile",
			() -> BlockEntityType.Builder.of(BoilerBiomassTile::new, new Block[] { BOILER_BIOMASS.get() }).build(null));

	public static final RegistryObject<BlockEntityType<HydroTurbineTile>> HYDRO_TURBINE_TILE = CoreInit.BLOCK_ENTITIES.register("hydro_turbine_tile",
			() -> BlockEntityType.Builder.of(HydroTurbineTile::new, new Block[] { HYDRO_TURBINE.get() }).build(null));

	public static final RegistryObject<BlockEntityType<CableCopperTile>> CABLE_COPPER_TILE = CoreInit.BLOCK_ENTITIES.register("cable_copper_tile",
			() -> BlockEntityType.Builder.of(CableCopperTile::new, new Block[] { CABLE_COPPER.get(), CABLE_COPPER_COATED.get() }).build(null));

	public static final RegistryObject<BlockEntityType<MonitorRSTile>> MONITOR_RS_TILE = CoreInit.BLOCK_ENTITIES.register("monitor_rs_tile",
			() -> BlockEntityType.Builder.of(MonitorRSTile::new, new Block[] { MONITOR_RS.get(), MONITOR_RS_PILOT.get() }).build(null));

	public static final RegistryObject<BlockEntityType<MonitorComparatorTile>> MONITOR_COMPARATOR_TILE = CoreInit.BLOCK_ENTITIES.register("monitor_comparator_tile",
			() -> BlockEntityType.Builder.of(MonitorComparatorTile::new, new Block[] { MONITOR_COMPARATOR.get() }).build(null));

	public static final RegistryObject<BlockEntityType<MonitorAndonTile>> MONITOR_ANDON_TILE = CoreInit.BLOCK_ENTITIES.register("monitor_andon_tile",
			() -> BlockEntityType.Builder.of(MonitorAndonTile::new, new Block[] { MONITOR_ANDON_LAMP.get(), MONITOR_ANDON_PANEL_1.get(), MONITOR_ANDON_PANEL_2.get(), MONITOR_ANDON_PANEL_3.get(),
				MONITOR_ANDON_PANEL_4.get(), MONITOR_ANDON_PANEL_5.get(), MONITOR_ANDON_PANEL_6.get(), MONITOR_ANDON_PANEL_7.get(), MONITOR_ANDON_PANEL_8.get(), MONITOR_ANDON_PANEL_9.get() }).build(
						null));

	public static final RegistryObject<BlockEntityType<MonitorTempTile>> MONITOR_TEMP_TILE = CoreInit.BLOCK_ENTITIES.register("monitor_temp_tile",
			() -> BlockEntityType.Builder.of(MonitorTempTile::new, new Block[] { MONITOR_TEMP.get() }).build(null));

	public static final RegistryObject<BlockEntityType<MonitorEnergyTile>> MONITOR_ENERGY_TILE = CoreInit.BLOCK_ENTITIES.register("monitor_energy_tile",
			() -> BlockEntityType.Builder.of(MonitorEnergyTile::new, new Block[] { MONITOR_ENERGY.get() }).build(null));

	public static final RegistryObject<BlockEntityType<StormglassTile>> STORMGLASS_TILE = CoreInit.BLOCK_ENTITIES.register("stormglass_tile",
			() -> BlockEntityType.Builder.of(StormglassTile::new, new Block[] { STORMGLASS.get() }).build(null));

	public static final RegistryObject<BlockEntityType<KichenBenchTile>> KICHEN_BENCH_TILE = CoreInit.BLOCK_ENTITIES.register("kichen_bench_tile",
			() -> BlockEntityType.Builder.of(KichenBenchTile::new, new Block[] { KICHEN_BENCH_BRICK.get() }).build(null));

	public static final RegistryObject<BlockEntityType<FluidSinkTile>> FLUID_SINK_TILE = CoreInit.BLOCK_ENTITIES.register("fluid_sink_tile",
			() -> BlockEntityType.Builder.of(FluidSinkTile::new, new Block[] { KICHEN_SINK_BRICK.get(), HALF_SINK_BRICK.get() }).build(null));

	// Menu
	public static final RegistryObject<MenuType<HeatingChamberMenu>> CHAMBER_MENU = CoreInit.register("dcs_chamber_item", (IContainerFactory<HeatingChamberMenu>) (id, playerInv, data) -> {
		HeatingChamberTile cont = (HeatingChamberTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return HeatingChamberMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<FluidChamberMenu>> FLUID_CHAMBER_MENU = CoreInit.register("dcs_chamber_fluid", (IContainerFactory<FluidChamberMenu>) (id, playerInv, data) -> {
		FluidChamberTile cont = (FluidChamberTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return FluidChamberMenu.getMenu(id, playerInv, cont);
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

	public static final RegistryObject<MenuType<RollCrusherMenu>> CRUSHER_MENU = CoreInit.register("dcs_roll_crusher", (IContainerFactory<RollCrusherMenu>) (id, playerInv, data) -> {
		RollCrusherTile cont = (RollCrusherTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return RollCrusherMenu.getMenu(id, playerInv, cont);
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

	public static final RegistryObject<MenuType<HopperFilterMenu>> HOPPER_FILTER_MENU = CoreInit.register("dcs_filter_hopper", (IContainerFactory<HopperFilterMenu>) (id, playerInv, data) -> {
		HopperBaseTile cont = (HopperBaseTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return HopperFilterMenu.filterMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<HopperFilterMenu>> HOPPER_GOLD_MENU = CoreInit.register("dcs_gold_hopper", (IContainerFactory<HopperFilterMenu>) (id, playerInv, data) -> {
		HopperBaseTile cont = (HopperBaseTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return HopperFilterMenu.goldMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<HopperFilterMenu>> HOPPER_FILTER_GOLD_MENU = CoreInit.register("dcs_filter_gold_hopper", (IContainerFactory<HopperFilterMenu>) (id, playerInv,
			data) -> {
		HopperBaseTile cont = (HopperBaseTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return HopperFilterMenu.filterGoldMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<ConveyorSorterMenu>> CONVEYOR_SORTER_MENU = CoreInit.register("dcs_conveyor_sorter", (IContainerFactory<ConveyorSorterMenu>) (id, playerInv, data) -> {
		ConveyorSortingTile cont = (ConveyorSortingTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return ConveyorSorterMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<KichenBenchMenu>> KICHEN_BENCH_MENU = CoreInit.register("dcs_kichen_bench", (IContainerFactory<KichenBenchMenu>) (id, playerInv,
			data) -> {
		KichenBenchTile cont = (KichenBenchTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return KichenBenchMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<MonitorAndonMenu>> MONITOR_ANDON_MENU = CoreInit.register("dcs_monitor_andon", (IContainerFactory<MonitorAndonMenu>) (id, playerInv,
			data) -> {
		MonitorAndonTile cont = (MonitorAndonTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return MonitorAndonMenu.getMenu(id, playerInv, cont);
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

	public static RegistryObject<Block> regMonitorBlock(String name, Supplier<Block> block) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("machine/" + name, block);
		regItem(name, () -> new MonitorBlockItem(name, obj.get(), new Item.Properties().tab(CoreInit.MACHINE), null));
		return obj;
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("machine/" + name, item);
	}

}
