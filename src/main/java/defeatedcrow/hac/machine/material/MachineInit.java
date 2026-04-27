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
import defeatedcrow.hac.machine.client.gui.HopperEXPMenu;
import defeatedcrow.hac.machine.client.gui.HopperFilterMenu;
import defeatedcrow.hac.machine.client.gui.KichenBenchMenu;
import defeatedcrow.hac.machine.client.gui.KichenOvenMenu;
import defeatedcrow.hac.machine.client.gui.KichenStoveMenu;
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
import defeatedcrow.hac.machine.material.block.machine.KichenOvenBlock;
import defeatedcrow.hac.machine.material.block.machine.KichenOvenTile;
import defeatedcrow.hac.machine.material.block.machine.KichenStoveBlock;
import defeatedcrow.hac.machine.material.block.machine.KichenStoveTile;
import defeatedcrow.hac.machine.material.block.machine.RollCrusherBlock;
import defeatedcrow.hac.machine.material.block.machine.RollCrusherTile;
import defeatedcrow.hac.machine.material.block.machine.SpileCupBlock;
import defeatedcrow.hac.machine.material.block.machine.SpileCupTile;
import defeatedcrow.hac.machine.material.block.machine.StoneMillBlock;
import defeatedcrow.hac.machine.material.block.machine.StoneMillTile;
import defeatedcrow.hac.machine.material.block.machine.TeaPotBlock;
import defeatedcrow.hac.machine.material.block.machine.TeaPotTile;
import defeatedcrow.hac.machine.material.block.machine.WaterPumpBlock;
import defeatedcrow.hac.machine.material.block.machine.WaterPumpTile;
import defeatedcrow.hac.machine.material.block.monitor.EntityCameraBlock;
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
import defeatedcrow.hac.machine.material.block.monitor.PlayerPressurePlateBlock;
import defeatedcrow.hac.machine.material.block.monitor.ReflectiveSensorBlock;
import defeatedcrow.hac.machine.material.block.monitor.StormglassBlock;
import defeatedcrow.hac.machine.material.block.monitor.StormglassTile;
import defeatedcrow.hac.machine.material.block.transport.AutoMilkerBlock;
import defeatedcrow.hac.machine.material.block.transport.AutoMilkerTank;
import defeatedcrow.hac.machine.material.block.transport.BatteryMiddleBlock;
import defeatedcrow.hac.machine.material.block.transport.BatterySmallBlock;
import defeatedcrow.hac.machine.material.block.transport.CableAluminumBlock;
import defeatedcrow.hac.machine.material.block.transport.CableAluminumTile;
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
import defeatedcrow.hac.machine.material.block.transport.FluidPipeNickelsilverBlock;
import defeatedcrow.hac.machine.material.block.transport.FluidPipeNickelsilverTile;
import defeatedcrow.hac.machine.material.block.transport.FluidSinkBlock_Brick;
import defeatedcrow.hac.machine.material.block.transport.FluidSinkTile;
import defeatedcrow.hac.machine.material.block.transport.HopperBaseTile;
import defeatedcrow.hac.machine.material.block.transport.HopperEXPBlock;
import defeatedcrow.hac.machine.material.block.transport.HopperEXPTile;
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
import defeatedcrow.hac.machine.material.block.transport.SprinklerBlock;
import defeatedcrow.hac.machine.material.block.transport.SprinklerTile;
import defeatedcrow.hac.machine.material.block.transport.WaterIntakeBasinBlock;
import defeatedcrow.hac.machine.material.block.transport.WaterIntakeBasinTile;
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

	public static final RegistryObject<Block> CHAMBER_BRICK_A = regBlock("chamber_brick_a", () -> new BrickChamberBlock("chamber_brick_a"), Rarity.COMMON, null);
	public static final RegistryObject<Block> CHAMBER_BRICK_B = regBlock("chamber_brick_b", () -> new BrickChamberBlock("chamber_brick_b"), Rarity.COMMON, null);
	public static final RegistryObject<Block> CHAMBER_IRON = regBlock("chamber_iron", () -> new HeatingChamberBlock("chamber_iron"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> FUEL_BURNER = regBlock("fuel_burner", () -> new FluidChamberBlock("fuel_burner"), Rarity.RARE, null);
	public static final RegistryObject<Block> KICHEN_STOVE = regBlock("kitchen_stove", () -> new KichenStoveBlock("kitchen_stove"), Rarity.RARE, null);
	public static final RegistryObject<Block> KICHEN_OVEN_WOOD = regBlock("kitchen_oven_wood", () -> new KichenOvenBlock("kitchen_oven_wood"), Rarity.RARE, null);
	public static final RegistryObject<Block> KICHEN_OVEN_BLACK = regBlock("kitchen_oven_black", () -> new KichenOvenBlock("kitchen_oven_black"), Rarity.RARE, null);
	public static final RegistryObject<Block> KICHEN_OVEN_LAB = regBlock("kitchen_oven_lab", () -> new KichenOvenBlock("kitchen_oven_lab"), Rarity.RARE, null);

	public static final RegistryObject<Block> KICHEN_BENCH_BRICK = regBlock("kitchen_bench_brick", () -> new KichenBenchBlock("kitchen_bench_brick"), Rarity.COMMON, null);
	public static final RegistryObject<Block> KICHEN_BENCH_SUS = regBlock("kitchen_bench_sus", () -> new KichenBenchBlock("kitchen_bench_sus"), Rarity.RARE, null);
	public static final RegistryObject<Block> KICHEN_BENCH_WOOD_A = regBlock("kitchen_bench_wood_a", () -> new KichenBenchBlock("kitchen_bench_wood_a"), Rarity.COMMON, null);
	public static final RegistryObject<Block> KICHEN_BENCH_WOOD_B = regBlock("kitchen_bench_wood_b", () -> new KichenBenchBlock("kitchen_bench_wood_b"), Rarity.COMMON, null);
	public static final RegistryObject<Block> KICHEN_BENCH_BLACK_A = regBlock("kitchen_bench_black_a", () -> new KichenBenchBlock("kitchen_bench_black_a"), Rarity.COMMON, null);
	public static final RegistryObject<Block> KICHEN_BENCH_BLACK_B = regBlock("kitchen_bench_black_b", () -> new KichenBenchBlock("kitchen_bench_black_b"), Rarity.COMMON, null);
	public static final RegistryObject<Block> KICHEN_BENCH_LAB_A = regBlock("kitchen_bench_lab_a", () -> new KichenBenchBlock("kitchen_bench_lab_a"), Rarity.COMMON, null);
	public static final RegistryObject<Block> KICHEN_BENCH_LAB_B = regBlock("kitchen_bench_lab_b", () -> new KichenBenchBlock("kitchen_bench_lab_b"), Rarity.COMMON, null);

	public static final RegistryObject<Block> KICHEN_SINK_BRICK = regBlock("fluid_sink_brick", () -> new FluidSinkBlock_Brick("fluid_sink_brick", false), Rarity.COMMON, null);
	public static final RegistryObject<Block> HALF_SINK_BRICK = regBlock("half_sink_brick", () -> new FluidSinkBlock_Brick("half_sink_brick", true), Rarity.COMMON, null);
	public static final RegistryObject<Block> KICHEN_SINK_SUS = regBlock("fluid_sink_sus", () -> new FluidSinkBlock_Brick("fluid_sink_sus", false), Rarity.RARE, null);
	public static final RegistryObject<Block> HALF_SINK_SUS = regBlock("half_sink_sus", () -> new FluidSinkBlock_Brick("half_sink_sus", true), Rarity.RARE, null);
	public static final RegistryObject<Block> KICHEN_SINK_WOOD = regBlock("fluid_sink_wood", () -> new FluidSinkBlock_Brick("fluid_sink_wood", false), Rarity.COMMON, null);
	public static final RegistryObject<Block> KICHEN_SINK_BLACK = regBlock("fluid_sink_black", () -> new FluidSinkBlock_Brick("fluid_sink_black", false), Rarity.COMMON, null);
	public static final RegistryObject<Block> KICHEN_SINK_LAB = regBlock("fluid_sink_lab", () -> new FluidSinkBlock_Brick("fluid_sink_lab", false), Rarity.COMMON, null);

	public static final RegistryObject<Block> HOPPER_FILTER = regBlock("hopper_filter", () -> new HopperFilterBlock("hopper_filter"), Rarity.COMMON, null);
	public static final RegistryObject<Block> HOPPER_GOLD = regBlock("hopper_gold", () -> new HopperGoldBlock("hopper_gold"), Rarity.COMMON, null);
	public static final RegistryObject<Block> HOPPER_FILTER_GOLD = regBlock("hopper_filter_gold", () -> new HopperFilterGoldBlock("hopper_filter_gold"), Rarity.COMMON, null);
	public static final RegistryObject<Block> HOPPER_EXP = regBlock("hopper_exp", () -> new HopperEXPBlock("hopper_exp"), Rarity.UNCOMMON, null);

	public static final RegistryObject<Block> CONVEYOR = regBlock("conveyor", () -> new ConveyorNormalBlock("conveyor"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> CONVEYOR_SMELTING = regBlock("conveyor_smelting", () -> new ConveyorSmeltingBlock("conveyor_smelting"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> CONVEYOR_DROPPER = regBlock("conveyor_dropper", () -> new ConveyorDropperBlock("conveyor_dropper"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> CONVEYOR_SORTER = regBlock("conveyor_sorter", () -> new ConveyorSortingBlock("conveyor_sorter"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> CONVEYOR_FILLER = regBlock("conveyor_filler", () -> new ConveyorFillerBlock("conveyor_filler"), Rarity.UNCOMMON, null);

	public static final RegistryObject<Block> PORTABLE_CAN = regFluidBlock("portable_can", () -> new PortableCanBlock("portable_can"), Rarity.COMMON, 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_WHITE = regFluidBlock("portable_can_white", () -> new PortableCanBlock("portable_can_white"), Rarity.COMMON, 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_BLUE = regFluidBlock("portable_can_blue", () -> new PortableCanBlock("portable_can_blue"), Rarity.COMMON, 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_BLACK = regFluidBlock("portable_can_black", () -> new PortableCanBlock("portable_can_black"), Rarity.COMMON, 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_RED = regFluidBlock("portable_can_red", () -> new PortableCanBlock("portable_can_red"), Rarity.COMMON, 18000);
	public static final RegistryObject<Block> PORTABLE_CAN_GREEN = regFluidBlock("portable_can_green", () -> new PortableCanBlock("portable_can_green"), Rarity.COMMON, 18000);

	public static final RegistryObject<Block> IBC = regFluidBlock("ibc", () -> new IBCBlock("ibc"), Rarity.UNCOMMON, 1000000);

	public static final RegistryObject<Block> PIPE_BRASS = regBlock("pipe_brass", () -> new FluidPipeAlloyBlock("pipe_brass"), Rarity.COMMON, null);
	public static final RegistryObject<Block> PIPE_NICKELSILVER = regBlock("pipe_nickelsilver", () -> new FluidPipeNickelsilverBlock("pipe_nickelsilver"), Rarity.UNCOMMON, null);

	public static final RegistryObject<Block> WATER_BASIN = regFluidBlock("water_basin", () -> new WaterIntakeBasinBlock("water_basin"), Rarity.COMMON, 16000);

	public static final RegistryObject<Block> SPRINKLER = regBlock("sprinkler", () -> new SprinklerBlock("sprinkler"), Rarity.UNCOMMON, null);

	public static final RegistryObject<Block> AUTO_MILKER = regFluidBlock("auto_milker", () -> new AutoMilkerBlock("auto_milker"), Rarity.RARE, 8000);

	public static final RegistryObject<Block> FAUCET_A = regBlock("faucet_a", () -> new FaucetBlock("faucet_a"), Rarity.RARE, null);
	public static final RegistryObject<Block> FAUCET_B = regBlock("faucet_b", () -> new FaucetBlock("faucet_b"), Rarity.RARE, null);
	public static final RegistryObject<Block> FAUCET_C = regBlock("faucet_c", () -> new FaucetBlock("faucet_c"), Rarity.RARE, null);
	public static final RegistryObject<Block> FAUCET_D = regBlock("faucet_d", () -> new FaucetBlock("faucet_d"), Rarity.RARE, null);

	public static final RegistryObject<Block> SPILE = regBlock("spilecup", () -> new SpileCupBlock("spilecup"), Rarity.COMMON, null);

	public static final RegistryObject<Block> COOKING_POT_NORMAL = regBlock("cooking_pot_normal", () -> new CookingPotBlock("cooking_pot_normal"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> COOKING_POT_WHITE = regBlock("cooking_pot_white", () -> new CookingPotBlock("cooking_pot_white"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> COOKING_POT_BLUE = regBlock("cooking_pot_blue", () -> new CookingPotBlock("cooking_pot_blue"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> COOKING_POT_BLACK = regBlock("cooking_pot_black", () -> new CookingPotBlock("cooking_pot_black"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> COOKING_POT_RED = regBlock("cooking_pot_red", () -> new CookingPotBlock("cooking_pot_red"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> COOKING_POT_GREEN = regBlock("cooking_pot_green", () -> new CookingPotBlock("cooking_pot_green"), Rarity.UNCOMMON, null);

	public static final RegistryObject<Block> TEA_POT_NORMAL = regBlock("tea_pot_normal", () -> new TeaPotBlock("tea_pot_normal"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> TEA_POT_WHITE = regBlock("tea_pot_white", () -> new TeaPotBlock("tea_pot_white"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> TEA_POT_BLUE = regBlock("tea_pot_blue", () -> new TeaPotBlock("tea_pot_blue"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> TEA_POT_BLACK = regBlock("tea_pot_black", () -> new TeaPotBlock("tea_pot_black"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> TEA_POT_RED = regBlock("tea_pot_red", () -> new TeaPotBlock("tea_pot_red"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> TEA_POT_GREEN = regBlock("tea_pot_green", () -> new TeaPotBlock("tea_pot_green"), Rarity.UNCOMMON, null);

	public static final RegistryObject<Block> FERMENTATION_JAR_NORMAL = regBlock("fermentation_jar_normal", () -> new FermentationJarBlock("fermentation_jar_normal", false), Rarity.COMMON, null);
	public static final RegistryObject<Block> FERMENTATION_JAR_WHITE = regBlock("fermentation_jar_white", () -> new FermentationJarBlock("fermentation_jar_white", false), Rarity.COMMON, null);
	public static final RegistryObject<Block> FERMENTATION_JAR_BLUE = regBlock("fermentation_jar_blue", () -> new FermentationJarBlock("fermentation_jar_blue", true), Rarity.COMMON, null);
	public static final RegistryObject<Block> FERMENTATION_JAR_BLACK = regBlock("fermentation_jar_black", () -> new FermentationJarBlock("fermentation_jar_black", false), Rarity.COMMON, null);
	public static final RegistryObject<Block> FERMENTATION_JAR_RED = regBlock("fermentation_jar_red", () -> new FermentationJarBlock("fermentation_jar_red", true), Rarity.COMMON, null);
	public static final RegistryObject<Block> FERMENTATION_JAR_GREEN = regBlock("fermentation_jar_green", () -> new FermentationJarBlock("fermentation_jar_green", true), Rarity.COMMON, null);

	public static final RegistryObject<Block> WATER_PUMP = regBlock("water_pump", () -> new WaterPumpBlock("water_pump"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> STONE_MILL = regBlock("stone_mill", () -> new StoneMillBlock("stone_mill"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> ROLL_CRUSHER = regBlock("roll_crusher", () -> new RollCrusherBlock("roll_crusher"), Rarity.RARE, null);
	public static final RegistryObject<Block> INTAKE_FAN = regBlock("intake_fan", () -> new IntakeFanBlock("intake_fan"), Rarity.RARE, null);
	public static final RegistryObject<Block> EXHAUST_VENT = regBlock("exhaust_vent", () -> new ExhaustVentBlock("exhaust_vent"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> ITEM_ASPIRATOR = regBlock("item_aspirator", () -> new ItemAspiratorBlock("item_aspirator"), Rarity.RARE, null);
	public static final RegistryObject<Block> CROP_ASPIRATOR = regBlock("crop_aspirator", () -> new CropAspiratorBlock("crop_aspirator"), Rarity.RARE, null);

	public static final RegistryObject<Block> GENERATOR_SMALL = regEnergyBlock("generator_small", () -> new GeneratorSmallBlock("generator_small"), Rarity.UNCOMMON, 4000);

	public static final RegistryObject<Block> BOILER_BIOMASS = regBlock("boiler_biomass", () -> new BoilerBiomassBlock("boiler_biomass"), Rarity.UNCOMMON, null);
	public static final RegistryObject<Block> HYDRO_TURBINE = regBlock("hydro_turbine", () -> new HydroTurbineBlock("hydro_turbine"), Rarity.RARE, null);

	public static final RegistryObject<Block> BATTERY_SMALL = regEnergyBlock("battery_small", () -> new BatterySmallBlock("battery_small"), Rarity.UNCOMMON, 32000);
	public static final RegistryObject<Block> BATTERY_MIDDLE = regEnergyBlock("battery_middle", () -> new BatteryMiddleBlock("battery_small"), Rarity.RARE, 128000);

	public static final RegistryObject<Block> CABLE_COPPER = regBlock("cable_copper", () -> new CableCopperBlock("cable_copper"), Rarity.COMMON, TagDC.ItemTag.LEAKAGE_MACHINE);
	public static final RegistryObject<Block> CABLE_COPPER_COATED = regBlock("cable_copper_coated", () -> new CableCopperBlock("cable_copper_coated"), Rarity.COMMON, null);
	public static final RegistryObject<Block> CABLE_ALUMINUM_COATED = regBlock("cable_aluminum_coated", () -> new CableAluminumBlock("cable_aluminum_coated"), Rarity.UNCOMMON, null);

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

	public static final RegistryObject<Block> REFLECTIVE_SENSOR = regBlock("reflective_sensor", ReflectiveSensorBlock::new, Rarity.RARE, null);
	public static final RegistryObject<Block> ENTITY_CAMERA = regBlock("entity_camera", EntityCameraBlock::new, Rarity.RARE, null);

	public static final RegistryObject<Block> STORMGLASS = regBlock("stormglass", () -> new StormglassBlock("stormglass"), Rarity.COMMON, null);

	public static final RegistryObject<Block> PLAYER_PRESSURE_PLATE = regBlock("player_pressure_plate", () -> new PlayerPressurePlateBlock("player_pressure_plate"), Rarity.COMMON, null);

	public static final RegistryObject<Item> MOTOR_TIER1 = regItem("motor_small", () -> new MachineMaterialItem(Rarity.COMMON, "motor_small", TagDC.ItemTag.MOTOR_T1));
	public static final RegistryObject<Item> MOTOR_TIER2 = regItem("motor_middle", () -> new MachineMaterialItem(Rarity.UNCOMMON, "motor_middle", TagDC.ItemTag.MOTOR_T2));

	public static final RegistryObject<Item> IMPELLER_TIER1 = regItem("impeller_small", () -> new MachineMaterialItem(Rarity.COMMON, "impeller_small", TagDC.ItemTag.IMPELLER_T1));
	public static final RegistryObject<Item> IMPELLER_TIER2 = regItem("impeller_middle", () -> new MachineMaterialItem(Rarity.UNCOMMON, "impeller_middle", TagDC.ItemTag.IMPELLER_T2));

	public static final RegistryObject<Item> BATTERYITEM_TIER1 = regItem("batteryitem_small", () -> new MachineMaterialItem(Rarity.COMMON, "batteryitem_small", TagDC.ItemTag.BATTERY_T1));
	public static final RegistryObject<Item> BATTERYITEM_TIER2 = regItem("batteryitem_middle", () -> new MachineMaterialItem(Rarity.UNCOMMON, "batteryitem_middle", TagDC.ItemTag.BATTERY_T2));

	public static final RegistryObject<Item> BLADE_SUS = regItem("crusher_blade_sanitary", () -> new MachineMaterialItem(Rarity.UNCOMMON, "crusher_blade_sanitary", TagDC.ItemTag.BLADE_SANITARY));
	public static final RegistryObject<Item> BLADE_ALUMINA = regItem("crusher_blade_alumina", () -> new MachineMaterialItem(Rarity.RARE, "crusher_blade_alumina", TagDC.ItemTag.BLADE_ALUMINA));
	public static final RegistryObject<Item> BLADE_SCREEN = regItem("crusher_blade_screen", () -> new MachineMaterialItem(Rarity.UNCOMMON, "crusher_blade_screen", TagDC.ItemTag.BLADE_SCREEN));

	public static final RegistryObject<Item> OPTICAL_SENSOR = regItem("optical_sensor", () -> new MachineMaterialItem(Rarity.RARE, "optical_sensor", TagDC.ItemTag.SENSOR_OPTICAL));

	public static final RegistryObject<Item> MEMORY_COORD = regItem("memory_coord", () -> new MemoryCoordItem(Rarity.COMMON, "memory_coord"));

	// TileEntity
	public static final RegistryObject<BlockEntityType<BrickChamberTile>> CHAMBER_BRICK_TILE = CoreInit.BLOCK_ENTITIES.register("chamber_brick_tile",
	    () -> BlockEntityType.Builder.of(BrickChamberTile::new, CHAMBER_BRICK_A.get(), CHAMBER_BRICK_B.get()).build(null));

	public static final RegistryObject<BlockEntityType<HeatingChamberTile>> CHAMBER_IRON_TILE = CoreInit.BLOCK_ENTITIES.register("chamber_iron_tile",
	    () -> BlockEntityType.Builder.of(HeatingChamberTile::new, CHAMBER_IRON.get()).build(null));

	public static final RegistryObject<BlockEntityType<FluidChamberTile>> FUEL_BURNER_TILE = CoreInit.BLOCK_ENTITIES.register("fuel_burner_tile",
	    () -> BlockEntityType.Builder.of(FluidChamberTile::new, FUEL_BURNER.get()).build(null));

	public static final RegistryObject<BlockEntityType<KichenStoveTile>> KICHEN_STOVE_TILE = CoreInit.BLOCK_ENTITIES.register("kitchen_stove_tile",
	    () -> BlockEntityType.Builder.of(KichenStoveTile::new, KICHEN_STOVE.get()).build(null));

	public static final RegistryObject<BlockEntityType<KichenOvenTile>> KICHEN_OVEN_TILE = CoreInit.BLOCK_ENTITIES.register("kitchen_oven_tile",
	    () -> BlockEntityType.Builder.of(KichenOvenTile::new, KICHEN_OVEN_LAB.get(), KICHEN_OVEN_WOOD.get(), KICHEN_OVEN_BLACK.get()).build(null));

	public static final RegistryObject<BlockEntityType<HopperFilterTile>> HOPPER_FILTER_TILE = CoreInit.BLOCK_ENTITIES.register("hopper_filter_tile",
	    () -> BlockEntityType.Builder.of(HopperFilterTile::new, HOPPER_FILTER.get()).build(null));

	public static final RegistryObject<BlockEntityType<HopperGoldTile>> HOPPER_GOLD_TILE = CoreInit.BLOCK_ENTITIES.register("hopper_gold_tile",
	    () -> BlockEntityType.Builder.of(HopperGoldTile::new, HOPPER_GOLD.get()).build(null));

	public static final RegistryObject<BlockEntityType<HopperFilterGoldTile>> HOPPER_FILTER_GOLD_TILE = CoreInit.BLOCK_ENTITIES.register("hopper_filter_gold_tile",
	    () -> BlockEntityType.Builder.of(HopperFilterGoldTile::new, HOPPER_FILTER_GOLD.get()).build(null));

	public static final RegistryObject<BlockEntityType<HopperEXPTile>> HOPPER_EXP_TILE = CoreInit.BLOCK_ENTITIES.register("hopper_exp_tile",
	    () -> BlockEntityType.Builder.of(HopperEXPTile::new, HOPPER_EXP.get()).build(null));

	public static final RegistryObject<BlockEntityType<ConveyorTile>> CONVEYOR_TILE = CoreInit.BLOCK_ENTITIES.register("conveyor_tile",
	    () -> BlockEntityType.Builder.of(ConveyorTile::new, CONVEYOR.get()).build(null));

	public static final RegistryObject<BlockEntityType<ConveyorSmeltingTile>> CONVEYOR_SMELTING_TILE = CoreInit.BLOCK_ENTITIES.register("conveyor_smelting_tile",
	    () -> BlockEntityType.Builder.of(ConveyorSmeltingTile::new, CONVEYOR_SMELTING.get()).build(null));

	public static final RegistryObject<BlockEntityType<ConveyorDropperTile>> CONVEYOR_DROPPER_TILE = CoreInit.BLOCK_ENTITIES.register("conveyor_dropper_tile",
	    () -> BlockEntityType.Builder.of(ConveyorDropperTile::new, CONVEYOR_DROPPER.get()).build(null));

	public static final RegistryObject<BlockEntityType<ConveyorSortingTile>> CONVEYOR_SORTER_TILE = CoreInit.BLOCK_ENTITIES.register("conveyor_sorter_tile",
	    () -> BlockEntityType.Builder.of(ConveyorSortingTile::new, CONVEYOR_SORTER.get()).build(null));

	public static final RegistryObject<BlockEntityType<ConveyorFillerTile>> CONVEYOR_FILLER_TILE = CoreInit.BLOCK_ENTITIES.register("conveyor_filler_tile",
	    () -> BlockEntityType.Builder.of(ConveyorFillerTile::new, CONVEYOR_FILLER.get()).build(null));

	public static final RegistryObject<BlockEntityType<PortableCanTile>> PORTABLE_CAN_TILE = CoreInit.BLOCK_ENTITIES.register("portable_can_tile",
	    () -> BlockEntityType.Builder.of(PortableCanTile::new, PORTABLE_CAN.get(), PORTABLE_CAN_WHITE.get(), PORTABLE_CAN_BLUE.get(), PORTABLE_CAN_BLACK.get(), PORTABLE_CAN_RED.get(), PORTABLE_CAN_GREEN.get()).build(null));

	public static final RegistryObject<BlockEntityType<IBCTile>> IBC_TILE = CoreInit.BLOCK_ENTITIES.register("ibc_tile",
	    () -> BlockEntityType.Builder.of(IBCTile::new, IBC.get()).build(null));

	public static final RegistryObject<BlockEntityType<AutoMilkerTank>> AUTO_MILKER_TILE = CoreInit.BLOCK_ENTITIES.register("auto_milker_tile", () -> BlockEntityType.Builder.of(AutoMilkerTank::new, AUTO_MILKER.get()).build(null));

	public static final RegistryObject<BlockEntityType<WaterIntakeBasinTile>> WATER_BASIN_TILE
	    = CoreInit.BLOCK_ENTITIES.register("water_basin_tile", () -> BlockEntityType.Builder.of(WaterIntakeBasinTile::new, WATER_BASIN.get()).build(null));

	public static final RegistryObject<BlockEntityType<FluidPipeAlloyTile>> PIPE_BRASS_TILE = CoreInit.BLOCK_ENTITIES.register("pipe_brass_tile",
	    () -> BlockEntityType.Builder.of(FluidPipeAlloyTile::new, PIPE_BRASS.get()).build(null));

	public static final RegistryObject<BlockEntityType<FluidPipeNickelsilverTile>> PIPE_NICKELSILVER_TILE = CoreInit.BLOCK_ENTITIES.register("pipe_nickelsilver_tile",
	    () -> BlockEntityType.Builder.of(FluidPipeNickelsilverTile::new, PIPE_NICKELSILVER.get()).build(null));

	public static final RegistryObject<BlockEntityType<FaucetTile>> FAUCET_TILE = CoreInit.BLOCK_ENTITIES.register("faucet_tile",
	    () -> BlockEntityType.Builder.of(FaucetTile::new, FAUCET_A.get(), FAUCET_B.get(), FAUCET_C.get(), FAUCET_D.get()).build(null));

	public static final RegistryObject<BlockEntityType<SprinklerTile>> SPRINKLER_TILE = CoreInit.BLOCK_ENTITIES.register("sprinkler_tile",
	    () -> BlockEntityType.Builder.of(SprinklerTile::new, SPRINKLER.get()).build(null));

	public static final RegistryObject<BlockEntityType<SpileCupTile>> SPILE_TILE = CoreInit.BLOCK_ENTITIES.register("spilecup_tile",
	    () -> BlockEntityType.Builder.of(SpileCupTile::new, SPILE.get()).build(null));

	public static final RegistryObject<BlockEntityType<CookingPotTile>> COOKING_POT_TILE = CoreInit.BLOCK_ENTITIES.register("cooking_pot_tile",
	    () -> BlockEntityType.Builder.of(CookingPotTile::new, COOKING_POT_NORMAL.get(), COOKING_POT_WHITE.get(), COOKING_POT_BLUE.get(), COOKING_POT_BLACK.get(), COOKING_POT_RED.get(), COOKING_POT_GREEN.get()).build(null));

	public static final RegistryObject<BlockEntityType<TeaPotTile>> TEA_POT_TILE = CoreInit.BLOCK_ENTITIES.register("tea_pot_tile",
	    () -> BlockEntityType.Builder.of(TeaPotTile::new, TEA_POT_NORMAL.get(), TEA_POT_WHITE.get(), TEA_POT_BLUE.get(), TEA_POT_BLACK.get(), TEA_POT_RED.get(), TEA_POT_GREEN.get()).build(null));

	public static final RegistryObject<BlockEntityType<FermentationJarTile>> FERMENTATION_JAR_TILE = CoreInit.BLOCK_ENTITIES.register("fermentation_jar_tile",
	    () -> BlockEntityType.Builder.of(FermentationJarTile::new, FERMENTATION_JAR_NORMAL.get(), FERMENTATION_JAR_WHITE.get(), FERMENTATION_JAR_BLUE.get(), FERMENTATION_JAR_BLACK.get(), FERMENTATION_JAR_RED.get(),
	        FERMENTATION_JAR_GREEN.get()).build(null));

	public static final RegistryObject<BlockEntityType<StoneMillTile>> MILL_TILE = CoreInit.BLOCK_ENTITIES.register("mill_tile",
	    () -> BlockEntityType.Builder.of(StoneMillTile::new, STONE_MILL.get()).build(null));

	public static final RegistryObject<BlockEntityType<RollCrusherTile>> CRUSHER_TILE = CoreInit.BLOCK_ENTITIES.register("roll_crusher_tile",
	    () -> BlockEntityType.Builder.of(RollCrusherTile::new, ROLL_CRUSHER.get()).build(null));

	public static final RegistryObject<BlockEntityType<WaterPumpTile>> WATER_PUMP_TILE = CoreInit.BLOCK_ENTITIES.register("water_pump_tile",
	    () -> BlockEntityType.Builder.of(WaterPumpTile::new, WATER_PUMP.get()).build(null));

	public static final RegistryObject<BlockEntityType<IntakeFanTile>> INTAKE_FAN_TILE = CoreInit.BLOCK_ENTITIES.register("intake_fan_tile",
	    () -> BlockEntityType.Builder.of(IntakeFanTile::new, INTAKE_FAN.get()).build(null));

	public static final RegistryObject<BlockEntityType<ExhaustVentTile>> EXHAUST_VENT_TILE = CoreInit.BLOCK_ENTITIES.register("exhaust_vent_tile",
	    () -> BlockEntityType.Builder.of(ExhaustVentTile::new, EXHAUST_VENT.get()).build(null));

	public static final RegistryObject<BlockEntityType<ItemAspiratorTile>> ITEM_ASPIRATOR_TILE = CoreInit.BLOCK_ENTITIES.register("item_aspirator_tile",
	    () -> BlockEntityType.Builder.of(ItemAspiratorTile::new, ITEM_ASPIRATOR.get(), CROP_ASPIRATOR.get()).build(null));

	public static final RegistryObject<BlockEntityType<EnergyBatteryTile>> BATTERY_SMALL_TILE = CoreInit.BLOCK_ENTITIES.register("battery_small_tile",
	    () -> BlockEntityType.Builder.of(EnergyBatteryTile::new, BATTERY_SMALL.get()).build(null));

	public static final RegistryObject<BlockEntityType<EnergyMiddleBatteryTile>> BATTERY_MIDDLE_TILE = CoreInit.BLOCK_ENTITIES.register("battery_middle_tile",
	    () -> BlockEntityType.Builder.of(EnergyMiddleBatteryTile::new, BATTERY_MIDDLE.get()).build(null));

	public static final RegistryObject<BlockEntityType<EnergyGeneratorTile>> GENERATOR_SMALL_TILE = CoreInit.BLOCK_ENTITIES.register("generator_small_tile",
	    () -> BlockEntityType.Builder.of(EnergyGeneratorTile::new, GENERATOR_SMALL.get()).build(null));

	public static final RegistryObject<BlockEntityType<BoilerBiomassTile>> BOILER_BIOMASS_TILE = CoreInit.BLOCK_ENTITIES.register("boiler_biomass_tile",
	    () -> BlockEntityType.Builder.of(BoilerBiomassTile::new, BOILER_BIOMASS.get()).build(null));

	public static final RegistryObject<BlockEntityType<HydroTurbineTile>> HYDRO_TURBINE_TILE = CoreInit.BLOCK_ENTITIES.register("hydro_turbine_tile",
	    () -> BlockEntityType.Builder.of(HydroTurbineTile::new, HYDRO_TURBINE.get()).build(null));

	public static final RegistryObject<BlockEntityType<CableCopperTile>> CABLE_COPPER_TILE = CoreInit.BLOCK_ENTITIES.register("cable_copper_tile",
	    () -> BlockEntityType.Builder.of(CableCopperTile::new, CABLE_COPPER.get(), CABLE_COPPER_COATED.get()).build(null));

	public static final RegistryObject<BlockEntityType<CableAluminumTile>> CABLE_ALUMINUM_TILE = CoreInit.BLOCK_ENTITIES.register("cable_aluminum_tile",
	    () -> BlockEntityType.Builder.of(CableAluminumTile::new, CABLE_ALUMINUM_COATED.get()).build(null));

	public static final RegistryObject<BlockEntityType<MonitorRSTile>> MONITOR_RS_TILE = CoreInit.BLOCK_ENTITIES.register("monitor_rs_tile",
	    () -> BlockEntityType.Builder.of(MonitorRSTile::new, MONITOR_RS.get(), MONITOR_RS_PILOT.get()).build(null));

	public static final RegistryObject<BlockEntityType<MonitorComparatorTile>> MONITOR_COMPARATOR_TILE = CoreInit.BLOCK_ENTITIES.register("monitor_comparator_tile",
	    () -> BlockEntityType.Builder.of(MonitorComparatorTile::new, MONITOR_COMPARATOR.get()).build(null));

	public static final RegistryObject<BlockEntityType<MonitorAndonTile>> MONITOR_ANDON_TILE = CoreInit.BLOCK_ENTITIES.register("monitor_andon_tile",
	    () -> BlockEntityType.Builder.of(MonitorAndonTile::new, MONITOR_ANDON_LAMP.get(), MONITOR_ANDON_PANEL_1.get(), MONITOR_ANDON_PANEL_2.get(), MONITOR_ANDON_PANEL_3.get(), MONITOR_ANDON_PANEL_4.get(), MONITOR_ANDON_PANEL_5.get(),
	        MONITOR_ANDON_PANEL_6.get(), MONITOR_ANDON_PANEL_7.get(), MONITOR_ANDON_PANEL_8.get(), MONITOR_ANDON_PANEL_9.get()).build(
	            null));

	public static final RegistryObject<BlockEntityType<MonitorTempTile>> MONITOR_TEMP_TILE = CoreInit.BLOCK_ENTITIES.register("monitor_temp_tile",
	    () -> BlockEntityType.Builder.of(MonitorTempTile::new, MONITOR_TEMP.get()).build(null));

	public static final RegistryObject<BlockEntityType<MonitorEnergyTile>> MONITOR_ENERGY_TILE = CoreInit.BLOCK_ENTITIES.register("monitor_energy_tile",
	    () -> BlockEntityType.Builder.of(MonitorEnergyTile::new, MONITOR_ENERGY.get()).build(null));

	public static final RegistryObject<BlockEntityType<StormglassTile>> STORMGLASS_TILE = CoreInit.BLOCK_ENTITIES.register("stormglass_tile",
	    () -> BlockEntityType.Builder.of(StormglassTile::new, STORMGLASS.get()).build(null));

	public static final RegistryObject<BlockEntityType<KichenBenchTile>> KICHEN_BENCH_TILE = CoreInit.BLOCK_ENTITIES.register("kitchen_bench_tile",
	    () -> BlockEntityType.Builder.of(KichenBenchTile::new, KICHEN_BENCH_BRICK.get(), KICHEN_BENCH_LAB_A.get(), KICHEN_BENCH_LAB_B.get(), KICHEN_BENCH_WOOD_A.get(), KICHEN_BENCH_WOOD_B.get(), KICHEN_BENCH_BLACK_A.get(),
	        KICHEN_BENCH_BLACK_B.get(), KICHEN_BENCH_SUS.get()).build(null));

	public static final RegistryObject<BlockEntityType<FluidSinkTile>> FLUID_SINK_TILE = CoreInit.BLOCK_ENTITIES.register("fluid_sink_tile",
	    () -> BlockEntityType.Builder.of(FluidSinkTile::new, KICHEN_SINK_BRICK.get(), HALF_SINK_BRICK.get(), KICHEN_SINK_SUS.get(), HALF_SINK_SUS.get(), KICHEN_SINK_LAB.get(), KICHEN_SINK_WOOD.get(), KICHEN_SINK_BLACK.get()).build(null));

	// Menu
	public static final RegistryObject<MenuType<HeatingChamberMenu>> CHAMBER_MENU = CoreInit.register("dcs_chamber_item", (IContainerFactory<HeatingChamberMenu>) (id, playerInv, data) -> {
		HeatingChamberTile cont = (HeatingChamberTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return HeatingChamberMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<FluidChamberMenu>> FLUID_CHAMBER_MENU = CoreInit.register("dcs_chamber_fluid", (IContainerFactory<FluidChamberMenu>) (id, playerInv, data) -> {
		FluidChamberTile cont = (FluidChamberTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return FluidChamberMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<KichenStoveMenu>> KICHEN_STOVE_MENU = CoreInit.register("dcs_chamber_kitchen", (IContainerFactory<KichenStoveMenu>) (id, playerInv, data) -> {
		KichenStoveTile cont = (KichenStoveTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return KichenStoveMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<KichenOvenMenu>> KICHEN_OVEN_MENU = CoreInit.register("dcs_chamber_oven", (IContainerFactory<KichenOvenMenu>) (id, playerInv, data) -> {
		KichenOvenTile cont = (KichenOvenTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return KichenOvenMenu.getMenu(id, playerInv, cont);
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

	public static final RegistryObject<MenuType<HopperEXPMenu>> HOPPER_EXP_MENU = CoreInit.register("dcs_exp_hopper", (IContainerFactory<HopperEXPMenu>) (id, playerInv, data) -> {
		HopperEXPTile cont = (HopperEXPTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return HopperEXPMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<ConveyorSorterMenu>> CONVEYOR_SORTER_MENU = CoreInit.register("dcs_conveyor_sorter", (IContainerFactory<ConveyorSorterMenu>) (id, playerInv, data) -> {
		ConveyorSortingTile cont = (ConveyorSortingTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return ConveyorSorterMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<KichenBenchMenu>> KICHEN_BENCH_MENU = CoreInit.register("dcs_kitchen_bench", (IContainerFactory<KichenBenchMenu>) (id, playerInv,
	    data) -> {
		KichenBenchTile cont = (KichenBenchTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return KichenBenchMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<MonitorAndonMenu>> MONITOR_ANDON_MENU = CoreInit.register("dcs_monitor_andon", (IContainerFactory<MonitorAndonMenu>) (id, playerInv,
	    data) -> {
		MonitorAndonTile cont = (MonitorAndonTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return MonitorAndonMenu.getMenu(id, playerInv, cont);
	});

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, Rarity rare, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("machine/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(CoreInit.MACHINE).rarity(rare), tag));
		return obj;
	}

	public static RegistryObject<Block> regFluidBlock(String name, Supplier<Block> block, Rarity rare, int cap) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("machine/" + name, block);
		regItem(name, () -> new FluidBlockItemDC(name, obj.get(), new Item.Properties().tab(CoreInit.MACHINE).rarity(rare), null).setCap(cap));
		return obj;
	}

	public static RegistryObject<Block> regEnergyBlock(String name, Supplier<Block> block, Rarity rare, int cap) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("machine/" + name, block);
		regItem(name, () -> new EnergyTankItemDC(name, obj.get(), new Item.Properties().tab(CoreInit.MACHINE).rarity(rare), null).setCap(cap));
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
