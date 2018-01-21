package defeatedcrow.hac.machine;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCMaterialReg;
import defeatedcrow.hac.machine.block.BlockAdapterPanel;
import defeatedcrow.hac.machine.block.BlockBoilerTurbine;
import defeatedcrow.hac.machine.block.BlockCatapult;
import defeatedcrow.hac.machine.block.BlockConveyor;
import defeatedcrow.hac.machine.block.BlockCrank_S;
import defeatedcrow.hac.machine.block.BlockCreativeBox;
import defeatedcrow.hac.machine.block.BlockDieselEngine;
import defeatedcrow.hac.machine.block.BlockDynamo;
import defeatedcrow.hac.machine.block.BlockFan;
import defeatedcrow.hac.machine.block.BlockFauset;
import defeatedcrow.hac.machine.block.BlockFreezer;
import defeatedcrow.hac.machine.block.BlockGasBurner;
import defeatedcrow.hac.machine.block.BlockGearBox;
import defeatedcrow.hac.machine.block.BlockGearBox_SUS;
import defeatedcrow.hac.machine.block.BlockHandCrank;
import defeatedcrow.hac.machine.block.BlockHeatExchanger;
import defeatedcrow.hac.machine.block.BlockHopperFilter;
import defeatedcrow.hac.machine.block.BlockHopperFluid;
import defeatedcrow.hac.machine.block.BlockIBC;
import defeatedcrow.hac.machine.block.BlockKineticMotor;
import defeatedcrow.hac.machine.block.BlockPortalManager;
import defeatedcrow.hac.machine.block.BlockPressMachine;
import defeatedcrow.hac.machine.block.BlockReactor;
import defeatedcrow.hac.machine.block.BlockRedBox;
import defeatedcrow.hac.machine.block.BlockShaft_L;
import defeatedcrow.hac.machine.block.BlockShaft_L_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_L_Steel;
import defeatedcrow.hac.machine.block.BlockShaft_S;
import defeatedcrow.hac.machine.block.BlockShaft_S_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_S_Steel;
import defeatedcrow.hac.machine.block.BlockShaft_TA;
import defeatedcrow.hac.machine.block.BlockShaft_TA_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_TA_Steel;
import defeatedcrow.hac.machine.block.BlockShaft_TB;
import defeatedcrow.hac.machine.block.BlockShaft_TB_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_TB_Steel;
import defeatedcrow.hac.machine.block.BlockSpinningMachine;
import defeatedcrow.hac.machine.block.BlockStoneMill;
import defeatedcrow.hac.machine.block.BlockWaterPump;
import defeatedcrow.hac.machine.block.BlockWatermill;
import defeatedcrow.hac.machine.block.BlockWindmill;
import defeatedcrow.hac.machine.block.BlockWindmill_L;
import defeatedcrow.hac.machine.block.ItemAdapterPanel;
import defeatedcrow.hac.machine.block.ItemBlockHighTier;
import defeatedcrow.hac.machine.block.ItemIBC;
import defeatedcrow.hac.machine.block.cont.BlockFuelCont;
import defeatedcrow.hac.machine.item.ItemAdapterCard;
import defeatedcrow.hac.machine.item.ItemAlloyMold;
import defeatedcrow.hac.machine.item.ItemAluminiumMold;
import defeatedcrow.hac.machine.item.ItemCatalyst;
import defeatedcrow.hac.machine.item.ItemDynamite;
import defeatedcrow.hac.machine.item.ItemGemCore;
import defeatedcrow.hac.machine.item.ItemMachineMaterial;
import defeatedcrow.hac.machine.item.ItemMagneticHover;
import defeatedcrow.hac.machine.item.ItemMinecartMotor;
import defeatedcrow.hac.machine.item.ItemReagents;
import defeatedcrow.hac.machine.item.ItemScooter;
import defeatedcrow.hac.machine.item.ItemSteelMold;
import defeatedcrow.hac.machine.item.ItemSynthetic;
import defeatedcrow.hac.machine.item.ItemTorqueChecker;
import defeatedcrow.hac.machine.item.plating.ItemPlatingChrome;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainMaterialRegister;
import defeatedcrow.hac.main.block.fluid.DCFluidBlockBase;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MachineInitRegister {

	private MachineInitRegister() {}

	public static void load() {
		loadBlocks();
		loadItems();
		loadFluids();

		if (ModuleConfig.machine) {
			loadCreativeTab();
		}
	}

	static void loadBlocks() {
		MachineInit.windmill = new BlockWindmill(ClimateCore.PACKAGE_BASE + "_device_windmill");
		registerTierBlock(MachineInit.windmill, ClimateCore.PACKAGE_BASE + "_device_windmill", 1);

		MachineInit.windmill_l = new BlockWindmill_L(ClimateCore.PACKAGE_BASE + "_device_windmill_l");
		registerTierBlock(MachineInit.windmill_l, ClimateCore.PACKAGE_BASE + "_device_windmill_l", 1);

		MachineInit.shaft_s = new BlockShaft_S(ClimateCore.PACKAGE_BASE + "_device_shaft_s");
		registerTierBlock(MachineInit.shaft_s, ClimateCore.PACKAGE_BASE + "_device_shaft_s", 1);

		MachineInit.shaft_l = new BlockShaft_L(ClimateCore.PACKAGE_BASE + "_device_shaft_l");
		registerTierBlock(MachineInit.shaft_l, ClimateCore.PACKAGE_BASE + "_device_shaft_l", 1);

		MachineInit.shaft_t_a = new BlockShaft_TA(ClimateCore.PACKAGE_BASE + "_device_shaft_ta");
		registerTierBlock(MachineInit.shaft_t_a, ClimateCore.PACKAGE_BASE + "_device_shaft_ta", 1);

		MachineInit.shaft_t_b = new BlockShaft_TB(ClimateCore.PACKAGE_BASE + "_device_shaft_tb");
		registerTierBlock(MachineInit.shaft_t_b, ClimateCore.PACKAGE_BASE + "_device_shaft_tb", 1);

		MachineInit.gearbox = new BlockGearBox(ClimateCore.PACKAGE_BASE + "_device_gearbox");
		registerTierBlock(MachineInit.gearbox, ClimateCore.PACKAGE_BASE + "_device_gearbox", 2);

		MachineInit.piston = new BlockCrank_S(ClimateCore.PACKAGE_BASE + "_device_crank_s");
		registerTierBlock(MachineInit.piston, ClimateCore.PACKAGE_BASE + "_device_crank_s", 1);

		MachineInit.handcrank = new BlockHandCrank(ClimateCore.PACKAGE_BASE + "_device_handcrank");
		registerTierBlock(MachineInit.handcrank, ClimateCore.PACKAGE_BASE + "_device_handcrank", 1);

		MachineInit.stonemill = new BlockStoneMill(ClimateCore.PACKAGE_BASE + "_device_stonemill");
		registerTierBlock(MachineInit.stonemill, ClimateCore.PACKAGE_BASE + "_device_stonemill", 2);

		MachineInit.fan = new BlockFan(ClimateCore.PACKAGE_BASE + "_device_fan");
		registerTierBlock(MachineInit.fan, ClimateCore.PACKAGE_BASE + "_device_fan", 1);

		MachineInit.spinning = new BlockSpinningMachine(ClimateCore.PACKAGE_BASE + "_device_spinning_machine");
		registerTierBlock(MachineInit.spinning, ClimateCore.PACKAGE_BASE + "_device_spinning_machine", 1);

		MachineInit.watermill = new BlockWatermill(ClimateCore.PACKAGE_BASE + "_device_watermill");
		registerTierBlock(MachineInit.watermill, ClimateCore.PACKAGE_BASE + "_device_watermill", 2);

		MachineInit.shaft3_s = new BlockShaft_S_Steel(ClimateCore.PACKAGE_BASE + "_device_shaft_s_steel");
		registerTierBlock(MachineInit.shaft3_s, ClimateCore.PACKAGE_BASE + "_device_shaft_s_steel", 2);

		MachineInit.shaft3_l = new BlockShaft_L_Steel(ClimateCore.PACKAGE_BASE + "_device_shaft_l_steel");
		registerTierBlock(MachineInit.shaft3_l, ClimateCore.PACKAGE_BASE + "_device_shaft_l_steel", 2);

		MachineInit.shaft3_t_a = new BlockShaft_TA_Steel(ClimateCore.PACKAGE_BASE + "_device_shaft_ta_steel");
		registerTierBlock(MachineInit.shaft3_t_a, ClimateCore.PACKAGE_BASE + "_device_shaft_ta_steel", 2);

		MachineInit.shaft3_t_b = new BlockShaft_TB_Steel(ClimateCore.PACKAGE_BASE + "_device_shaft_tb_steel");
		registerTierBlock(MachineInit.shaft3_t_b, ClimateCore.PACKAGE_BASE + "_device_shaft_tb_steel", 2);

		MachineInit.redbox = new BlockRedBox(ClimateCore.PACKAGE_BASE + "_device_redbox");
		registerTierBlock(MachineInit.redbox, ClimateCore.PACKAGE_BASE + "_device_redbox", 2);

		MachineInit.conveyor = new BlockConveyor(ClimateCore.PACKAGE_BASE + "_device_conveyor");
		registerTierBlock(MachineInit.conveyor, ClimateCore.PACKAGE_BASE + "_device_conveyor", 2);

		MachineInit.catapult = new BlockCatapult(ClimateCore.PACKAGE_BASE + "_device_catapult");
		registerTierBlock(MachineInit.catapult, ClimateCore.PACKAGE_BASE + "_device_catapult", 2);

		MachineInit.hopperFilter = new BlockHopperFilter(ClimateCore.PACKAGE_BASE + "_device_hopper_filter");
		registerTierBlock(MachineInit.hopperFilter, ClimateCore.PACKAGE_BASE + "_device_hopper_filter", 2);
		ClimateMain.proxy.regTEJson(MachineInit.hopperFilter, "dcs_climate", "dcs_device_hopper_filter", "machine");

		MachineInit.fauset = new BlockFauset(ClimateCore.PACKAGE_BASE + "_device_fauset");
		registerTierBlock(MachineInit.fauset, ClimateCore.PACKAGE_BASE + "_device_fauset", 2);

		MachineInit.IBC = new BlockIBC(ClimateCore.PACKAGE_BASE + "_device_ibc");
		MachineInit.IBC.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_ibc");
		ForgeRegistries.BLOCKS.register(MachineInit.IBC);
		ForgeRegistries.ITEMS.register(new ItemIBC(MachineInit.IBC));

		MachineInit.hopperFluid = new BlockHopperFluid(ClimateCore.PACKAGE_BASE + "_device_hopper_fluid");
		registerTierBlock(MachineInit.hopperFluid, ClimateCore.PACKAGE_BASE + "_device_hopper_fluid", 2);

		MachineInit.waterPump = new BlockWaterPump(ClimateCore.PACKAGE_BASE + "_device_water_pump");
		registerTierBlock(MachineInit.waterPump, ClimateCore.PACKAGE_BASE + "_device_water_pump", 2);

		MachineInit.heatPump = new BlockHeatExchanger(ClimateCore.PACKAGE_BASE + "_device_heat_exchanger");
		registerTierBlock(MachineInit.heatPump, ClimateCore.PACKAGE_BASE + "_device_heat_exchanger", 2);

		MachineInit.shaft2_s = new BlockShaft_S_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_s_sus");
		registerTierBlock(MachineInit.shaft2_s, ClimateCore.PACKAGE_BASE + "_device_shaft_s_sus", 3);

		MachineInit.shaft2_l = new BlockShaft_L_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_l_sus");
		registerTierBlock(MachineInit.shaft2_l, ClimateCore.PACKAGE_BASE + "_device_shaft_l_sus", 3);

		MachineInit.shaft2_t_a = new BlockShaft_TA_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_ta_sus");
		registerTierBlock(MachineInit.shaft2_t_a, ClimateCore.PACKAGE_BASE + "_device_shaft_ta_sus", 3);

		MachineInit.shaft2_t_b = new BlockShaft_TB_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_tb_sus");
		registerTierBlock(MachineInit.shaft2_t_b, ClimateCore.PACKAGE_BASE + "_device_shaft_tb_sus", 3);

		MachineInit.gearbox2 = new BlockGearBox_SUS(ClimateCore.PACKAGE_BASE + "_device_gearbox_sus");
		registerTierBlock(MachineInit.gearbox2, ClimateCore.PACKAGE_BASE + "_device_gearbox_sus", 3);

		MachineInit.boilerTurbine = new BlockBoilerTurbine(ClimateCore.PACKAGE_BASE + "_device_boiler_turbine");
		registerTierBlock(MachineInit.boilerTurbine, ClimateCore.PACKAGE_BASE + "_device_boiler_turbine", 3);

		MachineInit.motor = new BlockKineticMotor(ClimateCore.PACKAGE_BASE + "_device_kinetic_motor");
		registerTierBlock(MachineInit.motor, ClimateCore.PACKAGE_BASE + "_device_kinetic_motor", 3);

		MachineInit.dynamo = new BlockDynamo(ClimateCore.PACKAGE_BASE + "_device_dynamo");
		registerTierBlock(MachineInit.dynamo, ClimateCore.PACKAGE_BASE + "_device_dynamo", 3);

		MachineInit.reactor = new BlockReactor(ClimateCore.PACKAGE_BASE + "_device_reactor");
		registerTierBlock(MachineInit.reactor, ClimateCore.PACKAGE_BASE + "_device_reactor", 3);

		MachineInit.freezer = new BlockFreezer(ClimateCore.PACKAGE_BASE + "_device_freezer");
		registerTierBlock(MachineInit.freezer, ClimateCore.PACKAGE_BASE + "_device_freezer", 3);

		MachineInit.pressMachine = new BlockPressMachine(ClimateCore.PACKAGE_BASE + "_device_press_machine");
		registerTierBlock(MachineInit.pressMachine, ClimateCore.PACKAGE_BASE + "_device_press_machine", 3);

		MachineInit.creativeBox = new BlockCreativeBox(ClimateCore.PACKAGE_BASE + "_device_creative_box");
		registerTierBlock(MachineInit.creativeBox, ClimateCore.PACKAGE_BASE + "_device_creative_box", 3);

		MachineInit.adapterPanel = new BlockAdapterPanel(ClimateCore.PACKAGE_BASE + "_device_adapter_item", false);
		MachineInit.adapterPanel.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_adapter_item");
		ForgeRegistries.BLOCKS.register(MachineInit.adapterPanel);
		ForgeRegistries.ITEMS.register(new ItemAdapterPanel(MachineInit.adapterPanel));

		MachineInit.acceptorPanel = new BlockAdapterPanel(ClimateCore.PACKAGE_BASE + "_device_acceptor_item", true);
		MachineInit.acceptorPanel.setRegistryName(ClimateMain.MOD_ID,
				ClimateCore.PACKAGE_BASE + "_device_acceptor_item");
		ForgeRegistries.BLOCKS.register(MachineInit.acceptorPanel);
		ForgeRegistries.ITEMS.register(new ItemAdapterPanel(MachineInit.acceptorPanel));

		MachineInit.wirelessPortal = new BlockPortalManager(ClimateCore.PACKAGE_BASE + "_device_portal_manager");
		registerTierBlock(MachineInit.wirelessPortal, ClimateCore.PACKAGE_BASE + "_device_portal_manager", 3);

		MachineInit.burner = new BlockGasBurner(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_gas_burner", 3);
		registerTierBlock(MachineInit.burner, ClimateCore.PACKAGE_BASE + "_device_gas_burner", 3);

		MachineInit.dieselEngine = new BlockDieselEngine(ClimateCore.PACKAGE_BASE + "_device_diesel_engine");
		registerTierBlock(MachineInit.dieselEngine, ClimateCore.PACKAGE_BASE + "_device_diesel_engine", 3);

		MachineInit.fuelCont = new BlockFuelCont(ClimateCore.PACKAGE_BASE + "_cont_fuel");
		MainMaterialRegister.registerBlock(MachineInit.fuelCont, ClimateCore.PACKAGE_BASE + "_cont_fuel",
				ClimateMain.MOD_ID, new int[] {
						54000, 128000
				});
		ClimateMain.proxy.addSidedBlock(MachineInit.fuelCont, "cont_fuel", 1);

		// entity
		MachineInit.motorMinecart = new ItemMinecartMotor()
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_motor_minecart");
		DCMaterialReg.registerItem(MachineInit.motorMinecart, ClimateCore.PACKAGE_BASE + "_motor_minecart",
				ClimateMain.MOD_ID);

		MachineInit.scooter = new ItemScooter().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_motor_scooter");
		DCMaterialReg.registerItem(MachineInit.scooter, ClimateCore.PACKAGE_BASE + "_motor_scooter",
				ClimateMain.MOD_ID);

		MachineInit.magneticHover = new ItemMagneticHover()
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_magnetic_hover");
		DCMaterialReg.registerItem(MachineInit.magneticHover, ClimateCore.PACKAGE_BASE + "_magnetic_hover",
				ClimateMain.MOD_ID);

	}

	static void loadItems() {
		MachineInit.machimeMaterials = new ItemMachineMaterial(5)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_mechanical");
		DCMaterialReg.registerItem(MachineInit.machimeMaterials, ClimateCore.PACKAGE_BASE + "_mechanical",
				ClimateMain.MOD_ID);

		MachineInit.mold = new ItemSteelMold().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_mold");
		DCMaterialReg.registerItem(MachineInit.mold, ClimateCore.PACKAGE_BASE + "_mold", ClimateMain.MOD_ID);

		MachineInit.moldAluminium = new ItemAluminiumMold()
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_aluminium_mold");
		DCMaterialReg.registerItem(MachineInit.moldAluminium, ClimateCore.PACKAGE_BASE + "_aluminium_mold",
				ClimateMain.MOD_ID);

		MachineInit.moldAlloy = new ItemAlloyMold().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_alloy_mold");
		DCMaterialReg.registerItem(MachineInit.moldAlloy, ClimateCore.PACKAGE_BASE + "_alloy_mold", ClimateMain.MOD_ID);

		MachineInit.torqueChecker = new ItemTorqueChecker()
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_torque_checker");
		DCMaterialReg.registerItem(MachineInit.torqueChecker, ClimateCore.PACKAGE_BASE + "_torque_checker",
				ClimateMain.MOD_ID);

		MachineInit.reagent = new ItemReagents().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_reagent");
		DCMaterialReg.registerItem(MachineInit.reagent, ClimateCore.PACKAGE_BASE + "_reagent", ClimateMain.MOD_ID);

		MachineInit.synthetic = new ItemSynthetic().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_synthetic");
		DCMaterialReg.registerItem(MachineInit.synthetic, ClimateCore.PACKAGE_BASE + "_synthetic", ClimateMain.MOD_ID);

		MachineInit.catalyst = new ItemCatalyst().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_catalyst");
		DCMaterialReg.registerItem(MachineInit.catalyst, ClimateCore.PACKAGE_BASE + "_catalyst", ClimateMain.MOD_ID);

		MachineInit.gemcore = new ItemGemCore().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_gemcore");
		DCMaterialReg.registerItem(MachineInit.gemcore, ClimateCore.PACKAGE_BASE + "_gemcore", ClimateMain.MOD_ID);

		MachineInit.adapterCard = new ItemAdapterCard().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_adapter_card");
		DCMaterialReg.registerItem(MachineInit.adapterCard, ClimateCore.PACKAGE_BASE + "_adapter_card",
				ClimateMain.MOD_ID);

		MachineInit.dynamite = new ItemDynamite().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_dynamite");
		DCMaterialReg.registerItem(MachineInit.dynamite, ClimateCore.PACKAGE_BASE + "_dynamite", ClimateMain.MOD_ID);

		MachineInit.platingChrome = new ItemPlatingChrome()
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_coating_tool");
		DCMaterialReg.registerItem(MachineInit.platingChrome, ClimateCore.PACKAGE_BASE + "_coating_tool",
				ClimateMain.MOD_ID);
	}

	static void loadCreativeTab() {
		MachineInit.windmill.setCreativeTab(ClimateMain.machine);
		MachineInit.windmill_l.setCreativeTab(ClimateMain.machine);
		MachineInit.handcrank.setCreativeTab(ClimateMain.machine);
		MachineInit.watermill.setCreativeTab(ClimateMain.machine);
		MachineInit.boilerTurbine.setCreativeTab(ClimateMain.machine);
		MachineInit.motor.setCreativeTab(ClimateMain.machine);
		MachineInit.dynamo.setCreativeTab(ClimateMain.machine);
		MachineInit.creativeBox.setCreativeTab(ClimateMain.machine);

		MachineInit.shaft_s.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft_l.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft_t_a.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft_t_b.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft3_s.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft3_l.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft3_t_a.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft3_t_b.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft2_s.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft2_l.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft2_t_a.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft2_t_b.setCreativeTab(ClimateMain.machine);
		MachineInit.gearbox.setCreativeTab(ClimateMain.machine);
		MachineInit.gearbox2.setCreativeTab(ClimateMain.machine);
		MachineInit.piston.setCreativeTab(ClimateMain.machine);

		MachineInit.stonemill.setCreativeTab(ClimateMain.machine);
		MachineInit.fan.setCreativeTab(ClimateMain.machine);
		MachineInit.spinning.setCreativeTab(ClimateMain.machine);
		MachineInit.redbox.setCreativeTab(ClimateMain.machine);
		MachineInit.catapult.setCreativeTab(ClimateMain.machine);
		MachineInit.heatPump.setCreativeTab(ClimateMain.machine);
		MachineInit.waterPump.setCreativeTab(ClimateMain.machine);
		MachineInit.pressMachine.setCreativeTab(ClimateMain.machine);
		MachineInit.freezer.setCreativeTab(ClimateMain.machine);

		MachineInit.hopperFilter.setCreativeTab(ClimateMain.machine);
		MachineInit.conveyor.setCreativeTab(ClimateMain.machine);
		MachineInit.fauset.setCreativeTab(ClimateMain.machine);
		MachineInit.IBC.setCreativeTab(ClimateMain.machine);
		MachineInit.hopperFluid.setCreativeTab(ClimateMain.machine);

		MachineInit.torqueChecker.setCreativeTab(ClimateMain.machine);
		MachineInit.machimeMaterials.setCreativeTab(ClimateCore.climate);
		MachineInit.mold.setCreativeTab(ClimateMain.machine);

		if (ModuleConfig.machine_advanced) {
			MachineInit.reactor.setCreativeTab(ClimateMain.machine);
			MachineInit.burner.setCreativeTab(ClimateMain.machine);
			MachineInit.dieselEngine.setCreativeTab(ClimateMain.machine);

			MachineInit.moldAluminium.setCreativeTab(ClimateMain.machine);
			MachineInit.moldAlloy.setCreativeTab(ClimateMain.machine);

			MachineInit.reagent.setCreativeTab(ClimateMain.machine);
			MachineInit.synthetic.setCreativeTab(ClimateMain.machine);
			MachineInit.catalyst.setCreativeTab(ClimateMain.machine);
			MachineInit.gemcore.setCreativeTab(ClimateMain.tool);

			MachineInit.adapterPanel.setCreativeTab(ClimateMain.machine);
			MachineInit.acceptorPanel.setCreativeTab(ClimateMain.machine);
			MachineInit.wirelessPortal.setCreativeTab(ClimateMain.machine);
			MachineInit.adapterCard.setCreativeTab(ClimateMain.machine);
			MachineInit.dynamite.setCreativeTab(ClimateMain.machine);
			MachineInit.motorMinecart.setCreativeTab(ClimateMain.machine);
			MachineInit.scooter.setCreativeTab(ClimateMain.machine);
			MachineInit.magneticHover.setCreativeTab(ClimateMain.machine);
			MachineInit.platingChrome.setCreativeTab(ClimateMain.machine);

			MachineInit.fuelCont.setCreativeTab(ClimateMain.machine);
		}
	}

	static void loadFluids() {

		MachineInit.hydrogen = new Fluid("dcs.hydrogen",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/hydrogen_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/hydrogen_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".hydrogen").setDensity(-1000).setViscosity(300)
						.setGaseous(true);
		FluidRegistry.registerFluid(MachineInit.hydrogen);
		MachineInit.hydrogenBlock = new DCFluidBlockBase(MachineInit.hydrogen, "hydrogen_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen");
		DCMaterialReg.registerBlock(MachineInit.hydrogenBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen",
				ClimateMain.MOD_ID);
		MachineInit.hydrogen.setBlock(MachineInit.hydrogenBlock);

		MachineInit.ammonia = new Fluid("dcs.ammonia",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/ammonia_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/ammonia_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".ammonia").setDensity(-1000).setViscosity(300)
						.setGaseous(true);
		FluidRegistry.registerFluid(MachineInit.ammonia);
		MachineInit.ammoniaBlock = new DCFluidBlockBase(MachineInit.ammonia, "ammonia_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia");
		DCMaterialReg.registerBlock(MachineInit.ammoniaBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia",
				ClimateMain.MOD_ID);
		MachineInit.ammonia.setBlock(MachineInit.ammoniaBlock);

		MachineInit.nitricAcid = new Fluid("dcs.nitric_acid",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/nitric_acid_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/nitric_acid_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".nitric_acid").setDensity(1200)
						.setViscosity(1200);
		FluidRegistry.registerFluid(MachineInit.nitricAcid);
		MachineInit.nitricAcidBlock = new DCFluidBlockBase(MachineInit.nitricAcid, "nitric_acid_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid");
		DCMaterialReg.registerBlock(MachineInit.nitricAcidBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid",
				ClimateMain.MOD_ID);
		MachineInit.nitricAcid.setBlock(MachineInit.nitricAcidBlock);

		MachineInit.sulfuricAcid = new Fluid("dcs.sulfuric_acid",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/sulfuric_acid_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/sulfuric_acid_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".sulfuric_acid").setDensity(1200)
						.setViscosity(1200);
		FluidRegistry.registerFluid(MachineInit.sulfuricAcid);
		MachineInit.sulfuricAcidBlock = new DCFluidBlockBase(MachineInit.sulfuricAcid, "sulfuric_acid_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid");
		DCMaterialReg.registerBlock(MachineInit.sulfuricAcidBlock,
				ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid", ClimateMain.MOD_ID);
		MachineInit.sulfuricAcid.setBlock(MachineInit.sulfuricAcidBlock);

		MachineInit.fuelGas = new Fluid("dcs.fuel_gas",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/fuel_gas_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/fuel_gas_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".fuel_gas").setDensity(-500).setViscosity(300)
						.setGaseous(true);
		FluidRegistry.registerFluid(MachineInit.fuelGas);
		MachineInit.fuelGasBlock = new DCFluidBlockBase(MachineInit.fuelGas, "fuel_gas_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas");
		DCMaterialReg.registerBlock(MachineInit.fuelGasBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas",
				ClimateMain.MOD_ID);
		MachineInit.fuelGas.setBlock(MachineInit.fuelGasBlock);

		MachineInit.fuelOil = new Fluid("dcs.fuel_oil",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/fuel_oil_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/fuel_oil_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".fuel_oil").setDensity(800).setViscosity(1500);
		FluidRegistry.registerFluid(MachineInit.fuelOil);
		MachineInit.fuelOilBlock = new DCFluidBlockBase(MachineInit.fuelOil, "fuel_oil_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil");
		DCMaterialReg.registerBlock(MachineInit.fuelOilBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil",
				ClimateMain.MOD_ID);
		MachineInit.fuelOil.setBlock(MachineInit.fuelOilBlock);

		MachineInit.nitrogen = new Fluid("dcs.nitrogen",
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/nitrogen_still"),
				new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/nitrogen_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".nitrogen").setDensity(1100).setViscosity(1100)
						.setTemperature(77);
		FluidRegistry.registerFluid(MachineInit.nitrogen);
		MachineInit.nitrogenBlock = new DCFluidBlockBase(MachineInit.nitrogen, "nitrogen_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_nitrogen");
		DCMaterialReg.registerBlock(MachineInit.nitrogenBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_nitrogen",
				ClimateMain.MOD_ID);
		MachineInit.nitrogen.setBlock(MachineInit.nitrogenBlock);

		// bucket
		FluidRegistry.addBucketForFluid(MachineInit.hydrogen);
		FluidRegistry.addBucketForFluid(MachineInit.ammonia);
		FluidRegistry.addBucketForFluid(MachineInit.nitricAcid);
		FluidRegistry.addBucketForFluid(MachineInit.sulfuricAcid);
		FluidRegistry.addBucketForFluid(MachineInit.fuelGas);
		FluidRegistry.addBucketForFluid(MachineInit.fuelOil);
		FluidRegistry.addBucketForFluid(MachineInit.nitrogen);

		ClimateAPI.registerBlock.registerHeatBlock(MachineInit.nitrogenBlock, 32767, DCHeatTier.FROSTBITE);
	}

	public static void registerTierBlock(Block block, String name, int i) {
		Block reg = block.setRegistryName(ClimateMain.MOD_ID, name);
		ForgeRegistries.BLOCKS.register(reg);
		ForgeRegistries.ITEMS.register(new ItemBlockHighTier(reg, i));
	}

}
