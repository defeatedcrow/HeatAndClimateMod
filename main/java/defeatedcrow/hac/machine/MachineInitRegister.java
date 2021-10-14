package defeatedcrow.hac.machine;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCMaterialReg;
import defeatedcrow.hac.machine.block.BlockAdapterFluidPanel;
import defeatedcrow.hac.machine.block.BlockAdapterPanel;
import defeatedcrow.hac.machine.block.BlockBoilerTurbine;
import defeatedcrow.hac.machine.block.BlockCatapult;
import defeatedcrow.hac.machine.block.BlockConveyor;
import defeatedcrow.hac.machine.block.BlockCreativeBox;
import defeatedcrow.hac.machine.block.BlockDieselEngine;
import defeatedcrow.hac.machine.block.BlockDynamo;
import defeatedcrow.hac.machine.block.BlockEntityPanel;
import defeatedcrow.hac.machine.block.BlockFan;
import defeatedcrow.hac.machine.block.BlockFaucet;
import defeatedcrow.hac.machine.block.BlockFaucetR;
import defeatedcrow.hac.machine.block.BlockFaucet_SUS;
import defeatedcrow.hac.machine.block.BlockFreezer;
import defeatedcrow.hac.machine.block.BlockGasBurner;
import defeatedcrow.hac.machine.block.BlockGearBox;
import defeatedcrow.hac.machine.block.BlockGearBox_SUS;
import defeatedcrow.hac.machine.block.BlockHandCrank;
import defeatedcrow.hac.machine.block.BlockHeatExchanger;
import defeatedcrow.hac.machine.block.BlockHopperFilter;
import defeatedcrow.hac.machine.block.BlockHopperFilterG;
import defeatedcrow.hac.machine.block.BlockHopperFilterSUS;
import defeatedcrow.hac.machine.block.BlockHopperFluid;
import defeatedcrow.hac.machine.block.BlockHopperGold;
import defeatedcrow.hac.machine.block.BlockHopperSilver;
import defeatedcrow.hac.machine.block.BlockIBC;
import defeatedcrow.hac.machine.block.BlockKineticMotor;
import defeatedcrow.hac.machine.block.BlockMonitorComparator;
import defeatedcrow.hac.machine.block.BlockMonitorFluid;
import defeatedcrow.hac.machine.block.BlockMonitorInventory;
import defeatedcrow.hac.machine.block.BlockMonitorRF;
import defeatedcrow.hac.machine.block.BlockMonitorRedStone;
import defeatedcrow.hac.machine.block.BlockMonitorTemp;
import defeatedcrow.hac.machine.block.BlockMonitorTorque;
import defeatedcrow.hac.machine.block.BlockOscillator;
import defeatedcrow.hac.machine.block.BlockPlayerPanel;
import defeatedcrow.hac.machine.block.BlockPortalManager;
import defeatedcrow.hac.machine.block.BlockPressMachine;
import defeatedcrow.hac.machine.block.BlockReactor;
import defeatedcrow.hac.machine.block.BlockReactorIBC;
import defeatedcrow.hac.machine.block.BlockRollerCrusher;
import defeatedcrow.hac.machine.block.BlockShaft_L;
import defeatedcrow.hac.machine.block.BlockShaft_L_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_L_Steel;
import defeatedcrow.hac.machine.block.BlockShaft_S;
import defeatedcrow.hac.machine.block.BlockShaft_S_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_S_Steel;
import defeatedcrow.hac.machine.block.BlockShaft_Switch;
import defeatedcrow.hac.machine.block.BlockShaft_Switch_Steel;
import defeatedcrow.hac.machine.block.BlockShaft_TA;
import defeatedcrow.hac.machine.block.BlockShaft_TA_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_TA_Steel;
import defeatedcrow.hac.machine.block.BlockShaft_TB;
import defeatedcrow.hac.machine.block.BlockShaft_TB_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_TB_Steel;
import defeatedcrow.hac.machine.block.BlockShaft_X;
import defeatedcrow.hac.machine.block.BlockShaft_X_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_X_Steel;
import defeatedcrow.hac.machine.block.BlockSpinningMachine;
import defeatedcrow.hac.machine.block.BlockStoneMill;
import defeatedcrow.hac.machine.block.BlockWaterPump;
import defeatedcrow.hac.machine.block.BlockWatermill;
import defeatedcrow.hac.machine.block.BlockWindmill;
import defeatedcrow.hac.machine.block.BlockWindmill_EX;
import defeatedcrow.hac.machine.block.BlockWindmill_L;
import defeatedcrow.hac.machine.block.ItemAdapterPanel;
import defeatedcrow.hac.machine.block.ItemBlockHighTier;
import defeatedcrow.hac.machine.block.ItemIBC;
import defeatedcrow.hac.machine.block.ItemMonitor;
import defeatedcrow.hac.machine.block.cont.BlockFuelCont;
import defeatedcrow.hac.machine.block.tankyard.BlockTankYard;
import defeatedcrow.hac.machine.block.tankyard.BlockYardPart;
import defeatedcrow.hac.machine.block.tankyard.ItemTankYard;
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
import defeatedcrow.hac.machine.item.ItemRotaryBlade;
import defeatedcrow.hac.machine.item.ItemScooter;
import defeatedcrow.hac.machine.item.ItemSlotCard;
import defeatedcrow.hac.machine.item.ItemSteelMold;
import defeatedcrow.hac.machine.item.ItemSynthetic;
import defeatedcrow.hac.machine.item.ItemTorqueChecker;
import defeatedcrow.hac.machine.item.plating.ItemPlatingChrome;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.MainMaterialRegister;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MachineInitRegister {

	private MachineInitRegister() {}

	public static void load() {
		if (ModuleConfig.machine) {
			loadBlocks();
			loadItems();
			loadFluids();

			loadCreativeTab();
		}
	}

	static void loadBlocks() {
		MachineInit.windmill = new BlockWindmill(ClimateCore.PACKAGE_BASE + "_device_windmill");
		registerTierBlock(MachineInit.windmill, ClimateCore.PACKAGE_BASE + "_device_windmill", 1);

		MachineInit.windmill_l = new BlockWindmill_L(ClimateCore.PACKAGE_BASE + "_device_windmill_l");
		registerTierBlock(MachineInit.windmill_l, ClimateCore.PACKAGE_BASE + "_device_windmill_l", 2);

		MachineInit.windmill_ex = new BlockWindmill_EX(ClimateCore.PACKAGE_BASE + "_device_windmill_ex");
		registerTierBlock(MachineInit.windmill_ex, ClimateCore.PACKAGE_BASE + "_device_windmill_ex", 3);

		MachineInit.shaft_s = new BlockShaft_S(ClimateCore.PACKAGE_BASE + "_device_shaft_s");
		registerTierBlock(MachineInit.shaft_s, ClimateCore.PACKAGE_BASE + "_device_shaft_s", 1);

		MachineInit.shaft_l = new BlockShaft_L(ClimateCore.PACKAGE_BASE + "_device_shaft_l");
		registerTierBlock(MachineInit.shaft_l, ClimateCore.PACKAGE_BASE + "_device_shaft_l", 1);

		MachineInit.shaft_t_a = new BlockShaft_TA(ClimateCore.PACKAGE_BASE + "_device_shaft_ta");
		registerTierBlock(MachineInit.shaft_t_a, ClimateCore.PACKAGE_BASE + "_device_shaft_ta", 1);

		MachineInit.shaft_t_b = new BlockShaft_TB(ClimateCore.PACKAGE_BASE + "_device_shaft_tb");
		registerTierBlock(MachineInit.shaft_t_b, ClimateCore.PACKAGE_BASE + "_device_shaft_tb", 1);

		MachineInit.shaft_x = new BlockShaft_X(ClimateCore.PACKAGE_BASE + "_device_shaft_x");
		registerTierBlock(MachineInit.shaft_x, ClimateCore.PACKAGE_BASE + "_device_shaft_x", 1);

		MachineInit.shaft_switch = new BlockShaft_Switch(ClimateCore.PACKAGE_BASE + "_device_shaft_switch");
		registerTierBlock(MachineInit.shaft_switch, ClimateCore.PACKAGE_BASE + "_device_shaft_switch", 1);

		MachineInit.gearbox = new BlockGearBox(ClimateCore.PACKAGE_BASE + "_device_gearbox");
		registerTierBlock(MachineInit.gearbox, ClimateCore.PACKAGE_BASE + "_device_gearbox", 2);

		MachineInit.handcrank = new BlockHandCrank(ClimateCore.PACKAGE_BASE + "_device_handcrank");
		registerTierBlock(MachineInit.handcrank, ClimateCore.PACKAGE_BASE + "_device_handcrank", 1);

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

		MachineInit.shaft3_x = new BlockShaft_X_Steel(ClimateCore.PACKAGE_BASE + "_device_shaft_x_steel");
		registerTierBlock(MachineInit.shaft3_x, ClimateCore.PACKAGE_BASE + "_device_shaft_x_steel", 2);

		MachineInit.shaft3_switch = new BlockShaft_Switch_Steel(
				ClimateCore.PACKAGE_BASE + "_device_shaft_switch_steel");
		registerTierBlock(MachineInit.shaft3_switch, ClimateCore.PACKAGE_BASE + "_device_shaft_switch_steel", 2);

		MachineInit.conveyor = new BlockConveyor(ClimateCore.PACKAGE_BASE + "_device_conveyor");
		registerTierBlock(MachineInit.conveyor, ClimateCore.PACKAGE_BASE + "_device_conveyor", 2);

		MachineInit.stonemill = new BlockStoneMill(ClimateCore.PACKAGE_BASE + "_device_stonemill");
		registerTierBlock(MachineInit.stonemill, ClimateCore.PACKAGE_BASE + "_device_stonemill", 2);

		MachineInit.catapult = new BlockCatapult(ClimateCore.PACKAGE_BASE + "_device_catapult");
		registerTierBlock(MachineInit.catapult, ClimateCore.PACKAGE_BASE + "_device_catapult", 2);

		MachineInit.hopperFilter = new BlockHopperFilter(ClimateCore.PACKAGE_BASE + "_device_hopper_filter");
		registerTierBlock(MachineInit.hopperFilter, ClimateCore.PACKAGE_BASE + "_device_hopper_filter", 2);
		ClimateMain.proxy.regTEJson(MachineInit.hopperFilter, "dcs_climate", "dcs_device_hopper_filter", "machine");

		MachineInit.hopperGold = new BlockHopperGold(ClimateCore.PACKAGE_BASE + "_device_hopper_gold");
		registerTierBlock(MachineInit.hopperGold, ClimateCore.PACKAGE_BASE + "_device_hopper_gold", 2);
		ClimateMain.proxy.regTEJson(MachineInit.hopperGold, "dcs_climate", "dcs_device_hopper_gold", "machine");

		MachineInit.hopperFilterG = new BlockHopperFilterG(ClimateCore.PACKAGE_BASE + "_device_hopper_filter_gold");
		registerTierBlock(MachineInit.hopperFilterG, ClimateCore.PACKAGE_BASE + "_device_hopper_filter_gold", 2);
		ClimateMain.proxy
				.regTEJson(MachineInit.hopperFilterG, "dcs_climate", "dcs_device_hopper_filter_gold", "machine");

		MachineInit.hopperSilver = new BlockHopperSilver(ClimateCore.PACKAGE_BASE + "_device_hopper_silver");
		registerTierBlock(MachineInit.hopperSilver, ClimateCore.PACKAGE_BASE + "_device_hopper_silver", 2);
		ClimateMain.proxy.regTEJson(MachineInit.hopperSilver, "dcs_climate", "dcs_device_hopper_silver", "machine");

		if (ModuleConfig.machine_advanced) {
			MachineInit.hopperSUS = new BlockHopperFilterSUS(ClimateCore.PACKAGE_BASE + "_device_hopper_sus");
			registerTierBlock(MachineInit.hopperSUS, ClimateCore.PACKAGE_BASE + "_device_hopper_sus", 3);
			ClimateMain.proxy.regTEJson(MachineInit.hopperSUS, "dcs_climate", "dcs_device_hopper_sus", "machine");
		}

		MachineInit.faucet = new BlockFaucet(ClimateCore.PACKAGE_BASE + "_device_faucet");
		registerTierBlock(MachineInit.faucet, ClimateCore.PACKAGE_BASE + "_device_faucet", 2);

		MachineInit.faucet_r = new BlockFaucetR(ClimateCore.PACKAGE_BASE + "_device_faucet_r");
		registerTierBlock(MachineInit.faucet_r, ClimateCore.PACKAGE_BASE + "_device_faucet_r", 2);

		MachineInit.faucet_sus = new BlockFaucet_SUS(ClimateCore.PACKAGE_BASE + "_device_faucet_sus");
		registerTierBlock(MachineInit.faucet_sus, ClimateCore.PACKAGE_BASE + "_device_faucet_sus", 3);

		MachineInit.IBC = new BlockIBC(ClimateCore.PACKAGE_BASE + "_device_ibc");
		MachineInit.IBC.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_ibc");
		ForgeRegistries.BLOCKS.register(MachineInit.IBC);
		ForgeRegistries.ITEMS.register(new ItemIBC(MachineInit.IBC));

		MachineInit.IBC_reactor = new BlockReactorIBC(ClimateCore.PACKAGE_BASE + "_device_reactor_ibc");
		registerTierBlock(MachineInit.IBC_reactor, ClimateCore.PACKAGE_BASE + "_device_reactor_ibc", 2);

		MachineInit.hopperFluid = new BlockHopperFluid(ClimateCore.PACKAGE_BASE + "_device_hopper_fluid");
		registerTierBlock(MachineInit.hopperFluid, ClimateCore.PACKAGE_BASE + "_device_hopper_fluid", 2);

		MachineInit.waterPump = new BlockWaterPump(ClimateCore.PACKAGE_BASE + "_device_water_pump");
		registerTierBlock(MachineInit.waterPump, ClimateCore.PACKAGE_BASE + "_device_water_pump", 2);

		MachineInit.heatPump = new BlockHeatExchanger(ClimateCore.PACKAGE_BASE + "_device_heat_exchanger");
		registerTierBlock(MachineInit.heatPump, ClimateCore.PACKAGE_BASE + "_device_heat_exchanger", 2);

		MachineInit.oscillator = new BlockOscillator(ClimateCore.PACKAGE_BASE + "_device_oscillator");
		registerTierBlock(MachineInit.oscillator, ClimateCore.PACKAGE_BASE + "_device_oscillator", 2);

		MachineInit.shaft2_s = new BlockShaft_S_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_s_sus");
		registerTierBlock(MachineInit.shaft2_s, ClimateCore.PACKAGE_BASE + "_device_shaft_s_sus", 3);

		MachineInit.shaft2_l = new BlockShaft_L_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_l_sus");
		registerTierBlock(MachineInit.shaft2_l, ClimateCore.PACKAGE_BASE + "_device_shaft_l_sus", 3);

		MachineInit.shaft2_t_a = new BlockShaft_TA_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_ta_sus");
		registerTierBlock(MachineInit.shaft2_t_a, ClimateCore.PACKAGE_BASE + "_device_shaft_ta_sus", 3);

		MachineInit.shaft2_t_b = new BlockShaft_TB_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_tb_sus");
		registerTierBlock(MachineInit.shaft2_t_b, ClimateCore.PACKAGE_BASE + "_device_shaft_tb_sus", 3);

		MachineInit.shaft2_x = new BlockShaft_X_SUS(ClimateCore.PACKAGE_BASE + "_device_shaft_x_sus");
		registerTierBlock(MachineInit.shaft2_x, ClimateCore.PACKAGE_BASE + "_device_shaft_x_sus", 3);

		MachineInit.gearbox2 = new BlockGearBox_SUS(ClimateCore.PACKAGE_BASE + "_device_gearbox_sus");
		registerTierBlock(MachineInit.gearbox2, ClimateCore.PACKAGE_BASE + "_device_gearbox_sus", 3);

		MachineInit.fuelCont = new BlockFuelCont(ClimateCore.PACKAGE_BASE + "_cont_fuel");
		MainMaterialRegister
				.registerBlock(MachineInit.fuelCont, ClimateCore.PACKAGE_BASE + "_cont_fuel", ClimateMain.MOD_ID, new int[] {
						54000,
						128000,
						0,
						0
				});
		ClimateMain.proxy.addSidedBlock(MachineInit.fuelCont, "cont_fuel", 3);

		MachineInit.boilerTurbine = new BlockBoilerTurbine(ClimateCore.PACKAGE_BASE + "_device_boiler_turbine");
		registerTierBlock(MachineInit.boilerTurbine, ClimateCore.PACKAGE_BASE + "_device_boiler_turbine", 3);

		MachineInit.creativeBox = new BlockCreativeBox(ClimateCore.PACKAGE_BASE + "_device_creative_box");
		registerTierBlock(MachineInit.creativeBox, ClimateCore.PACKAGE_BASE + "_device_creative_box", 3);

		if (ModuleConfig.machine_advanced) {

			MachineInit.burner = new BlockGasBurner(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_gas_burner", 3);
			registerTierBlock(MachineInit.burner, ClimateCore.PACKAGE_BASE + "_device_gas_burner", 3);

			MachineInit.dieselEngine = new BlockDieselEngine(ClimateCore.PACKAGE_BASE + "_device_diesel_engine");
			registerTierBlock(MachineInit.dieselEngine, ClimateCore.PACKAGE_BASE + "_device_diesel_engine", 3);

			MachineInit.motor = new BlockKineticMotor(ClimateCore.PACKAGE_BASE + "_device_kinetic_motor");
			registerTierBlock(MachineInit.motor, ClimateCore.PACKAGE_BASE + "_device_kinetic_motor", 3);

			MachineInit.dynamo = new BlockDynamo(ClimateCore.PACKAGE_BASE + "_device_dynamo");
			registerTierBlock(MachineInit.dynamo, ClimateCore.PACKAGE_BASE + "_device_dynamo", 3);

			MachineInit.reactor = new BlockReactor(ClimateCore.PACKAGE_BASE + "_device_reactor");
			registerTierBlock(MachineInit.reactor, ClimateCore.PACKAGE_BASE + "_device_reactor", 3);

			MachineInit.crusher = new BlockRollerCrusher(ClimateCore.PACKAGE_BASE + "_device_crusher");
			registerTierBlock(MachineInit.crusher, ClimateCore.PACKAGE_BASE + "_device_crusher", 3);

			MachineInit.freezer = new BlockFreezer(ClimateCore.PACKAGE_BASE + "_device_freezer");
			registerTierBlock(MachineInit.freezer, ClimateCore.PACKAGE_BASE + "_device_freezer", 3);

			MachineInit.tankYard = new BlockTankYard(ClimateCore.PACKAGE_BASE + "_device_tankyard");
			MachineInit.tankYard.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_tankyard");
			ForgeRegistries.BLOCKS.register(MachineInit.tankYard);
			ForgeRegistries.ITEMS.register(new ItemTankYard(MachineInit.tankYard));

			MachineInit.tankYardPart = new BlockYardPart(ClimateCore.PACKAGE_BASE + "_device_yardpart");
			registerTierBlock(MachineInit.tankYardPart, ClimateCore.PACKAGE_BASE + "_device_yardpart", 3);

			MachineInit.pressMachine = new BlockPressMachine(ClimateCore.PACKAGE_BASE + "_device_press_machine");
			registerTierBlock(MachineInit.pressMachine, ClimateCore.PACKAGE_BASE + "_device_press_machine", 3);

			MachineInit.adapterPanel = new BlockAdapterPanel(ClimateCore.PACKAGE_BASE + "_device_adapter_item", false);
			MachineInit.adapterPanel
					.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_adapter_item");
			ForgeRegistries.BLOCKS.register(MachineInit.adapterPanel);
			ForgeRegistries.ITEMS.register(new ItemAdapterPanel(MachineInit.adapterPanel));

			MachineInit.acceptorPanel = new BlockAdapterPanel(ClimateCore.PACKAGE_BASE + "_device_acceptor_item", true);
			MachineInit.acceptorPanel
					.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_acceptor_item");
			ForgeRegistries.BLOCKS.register(MachineInit.acceptorPanel);
			ForgeRegistries.ITEMS.register(new ItemAdapterPanel(MachineInit.acceptorPanel));

			MachineInit.adapterFluidPanel = new BlockAdapterFluidPanel(
					ClimateCore.PACKAGE_BASE + "_device_adapter_fluid", false);
			MachineInit.adapterFluidPanel
					.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_adapter_fluid");
			ForgeRegistries.BLOCKS.register(MachineInit.adapterFluidPanel);
			ForgeRegistries.ITEMS.register(new ItemAdapterPanel(MachineInit.adapterFluidPanel));

			MachineInit.acceptorFluidPanel = new BlockAdapterFluidPanel(
					ClimateCore.PACKAGE_BASE + "_device_acceptor_fluid", true);
			MachineInit.acceptorFluidPanel
					.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_acceptor_fluid");
			ForgeRegistries.BLOCKS.register(MachineInit.acceptorFluidPanel);
			ForgeRegistries.ITEMS.register(new ItemAdapterPanel(MachineInit.acceptorFluidPanel));

			MachineInit.wirelessPortal = new BlockPortalManager(ClimateCore.PACKAGE_BASE + "_device_portal_manager");
			registerTierBlock(MachineInit.wirelessPortal, ClimateCore.PACKAGE_BASE + "_device_portal_manager", 3);

			MachineInit.playerPanel = new BlockPlayerPanel(ClimateCore.PACKAGE_BASE + "_device_player_panel");
			registerTierBlock(MachineInit.playerPanel, ClimateCore.PACKAGE_BASE + "_device_player_panel", 3);

			MachineInit.monitorRS = new BlockMonitorRedStone(ClimateCore.PACKAGE_BASE + "_device_monitor_rs");
			MachineInit.monitorRS.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_monitor_rs");
			ForgeRegistries.BLOCKS.register(MachineInit.monitorRS);
			ForgeRegistries.ITEMS.register(new ItemMonitor(MachineInit.monitorRS));

			MachineInit.monitorCM = new BlockMonitorComparator(ClimateCore.PACKAGE_BASE + "_device_monitor_cm");
			MachineInit.monitorCM.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_monitor_cm");
			ForgeRegistries.BLOCKS.register(MachineInit.monitorCM);
			ForgeRegistries.ITEMS.register(new ItemMonitor(MachineInit.monitorCM));

			MachineInit.monitorTorque = new BlockMonitorTorque(ClimateCore.PACKAGE_BASE + "_device_monitor_torque");
			MachineInit.monitorTorque
					.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_monitor_torque");
			ForgeRegistries.BLOCKS.register(MachineInit.monitorTorque);
			ForgeRegistries.ITEMS.register(new ItemMonitor(MachineInit.monitorTorque));

			MachineInit.monitorRF = new BlockMonitorRF(ClimateCore.PACKAGE_BASE + "_device_monitor_rf");
			MachineInit.monitorRF.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_monitor_rf");
			ForgeRegistries.BLOCKS.register(MachineInit.monitorRF);
			ForgeRegistries.ITEMS.register(new ItemMonitor(MachineInit.monitorRF));

			MachineInit.monitorFluid = new BlockMonitorFluid(ClimateCore.PACKAGE_BASE + "_device_monitor_fluid");
			MachineInit.monitorFluid
					.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_monitor_fluid");
			ForgeRegistries.BLOCKS.register(MachineInit.monitorFluid);
			ForgeRegistries.ITEMS.register(new ItemMonitor(MachineInit.monitorFluid));

			MachineInit.monitorItem = new BlockMonitorInventory(ClimateCore.PACKAGE_BASE + "_device_monitor_item");
			MachineInit.monitorItem
					.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_monitor_item");
			ForgeRegistries.BLOCKS.register(MachineInit.monitorItem);
			ForgeRegistries.ITEMS.register(new ItemMonitor(MachineInit.monitorItem));

			MachineInit.monitorTemp = new BlockMonitorTemp(ClimateCore.PACKAGE_BASE + "_device_monitor_temp");
			MachineInit.monitorTemp
					.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_monitor_temp");
			ForgeRegistries.BLOCKS.register(MachineInit.monitorTemp);
			ForgeRegistries.ITEMS.register(new ItemMonitor(MachineInit.monitorTemp));

			MachineInit.entityPanel = new BlockEntityPanel(ClimateCore.PACKAGE_BASE + "_device_entity_panel");
			registerTierBlock(MachineInit.entityPanel, ClimateCore.PACKAGE_BASE + "_device_entity_panel", 3);

			// entity
			MachineInit.motorMinecart = new ItemMinecartMotor()
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_motor_minecart");
			DCMaterialReg
					.registerItem(MachineInit.motorMinecart, ClimateCore.PACKAGE_BASE + "_motor_minecart", ClimateMain.MOD_ID);

			MachineInit.scooter = new ItemScooter().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_motor_scooter");
			DCMaterialReg
					.registerItem(MachineInit.scooter, ClimateCore.PACKAGE_BASE + "_motor_scooter", ClimateMain.MOD_ID);

			MachineInit.magneticHover = new ItemMagneticHover()
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_magnetic_hover");
			DCMaterialReg
					.registerItem(MachineInit.magneticHover, ClimateCore.PACKAGE_BASE + "_magnetic_hover", ClimateMain.MOD_ID);
		}
	}

	static void loadItems() {
		MachineInit.machimeMaterials = new ItemMachineMaterial(9)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_mechanical");
		DCMaterialReg
				.registerItem(MachineInit.machimeMaterials, ClimateCore.PACKAGE_BASE + "_mechanical", ClimateMain.MOD_ID);

		MachineInit.torqueChecker = new ItemTorqueChecker()
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_torque_checker");
		DCMaterialReg
				.registerItem(MachineInit.torqueChecker, ClimateCore.PACKAGE_BASE + "_torque_checker", ClimateMain.MOD_ID);

		MachineInit.reagent = new ItemReagents().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_reagent");
		DCMaterialReg.registerItem(MachineInit.reagent, ClimateCore.PACKAGE_BASE + "_reagent", ClimateMain.MOD_ID);

		MachineInit.mold = new ItemSteelMold().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_mold");
		DCMaterialReg.registerItem(MachineInit.mold, ClimateCore.PACKAGE_BASE + "_mold", ClimateMain.MOD_ID);

		MachineInit.slotCard = new ItemSlotCard().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_slotcard");
		DCMaterialReg.registerItem(MachineInit.slotCard, ClimateCore.PACKAGE_BASE + "_slotcard", ClimateMain.MOD_ID);

		MachineInit.rotaryBlade = new ItemRotaryBlade()
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_rotaryblade");
		DCMaterialReg
				.registerItem(MachineInit.rotaryBlade, ClimateCore.PACKAGE_BASE + "_rotaryblade", ClimateMain.MOD_ID);

		if (ModuleConfig.machine_advanced) {

			MachineInit.moldAluminium = new ItemAluminiumMold()
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_aluminium_mold");
			DCMaterialReg
					.registerItem(MachineInit.moldAluminium, ClimateCore.PACKAGE_BASE + "_aluminium_mold", ClimateMain.MOD_ID);

			MachineInit.moldAlloy = new ItemAlloyMold().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_alloy_mold");
			DCMaterialReg
					.registerItem(MachineInit.moldAlloy, ClimateCore.PACKAGE_BASE + "_alloy_mold", ClimateMain.MOD_ID);

			MachineInit.synthetic = new ItemSynthetic().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_synthetic");
			DCMaterialReg
					.registerItem(MachineInit.synthetic, ClimateCore.PACKAGE_BASE + "_synthetic", ClimateMain.MOD_ID);

			MachineInit.catalyst = new ItemCatalyst().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_catalyst");
			DCMaterialReg
					.registerItem(MachineInit.catalyst, ClimateCore.PACKAGE_BASE + "_catalyst", ClimateMain.MOD_ID);

			MachineInit.gemcore = new ItemGemCore().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_gemcore");
			DCMaterialReg.registerItem(MachineInit.gemcore, ClimateCore.PACKAGE_BASE + "_gemcore", ClimateMain.MOD_ID);

			MachineInit.adapterCard = new ItemAdapterCard()
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_adapter_card");
			DCMaterialReg
					.registerItem(MachineInit.adapterCard, ClimateCore.PACKAGE_BASE + "_adapter_card", ClimateMain.MOD_ID);

			MachineInit.dynamite = new ItemDynamite().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_dynamite");
			DCMaterialReg
					.registerItem(MachineInit.dynamite, ClimateCore.PACKAGE_BASE + "_dynamite", ClimateMain.MOD_ID);

			MachineInit.platingChrome = new ItemPlatingChrome()
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_coating_tool");
			DCMaterialReg
					.registerItem(MachineInit.platingChrome, ClimateCore.PACKAGE_BASE + "_coating_tool", ClimateMain.MOD_ID);

		}
	}

	static void loadCreativeTab() {

		ClimateMain.machine.addSubItem(MainInit.wrench);
		ClimateMain.machine.addSubItem(MachineInit.torqueChecker);
		ClimateMain.machine.addSubItem(MainInit.gears);

		ClimateMain.machine.addSubItem(MachineInit.machimeMaterials);
		ClimateMain.machine.addSubItem(MachineInit.reagent);
		if (ModuleConfig.machine_advanced) {
			ClimateMain.machine.addSubItem(MachineInit.synthetic);
		}

		// climate
		ClimateMain.machine.addSubItem(MainInit.swedishTorch);
		ClimateMain.machine.addSubItem(MainInit.shitirin);
		ClimateMain.machine.addSubItem(MainInit.firestand);
		ClimateMain.machine.addSubItem(MainInit.chamber);
		ClimateMain.machine.addSubItem(MainInit.fuelStove);

		if (ModuleConfig.machine_advanced) {
			ClimateMain.machine.addSubItem(MachineInit.burner);
			ClimateMain.machine.addSubItem(MachineInit.freezer);
		}

		ClimateMain.machine.addSubItem(MainInit.bellow);
		ClimateMain.machine.addSubItem(MachineInit.fan);
		ClimateMain.machine.addSubItem(MachineInit.heatPump);

		// power source
		ClimateMain.machine.addSubItem(MachineInit.handcrank);
		ClimateMain.machine.addSubItem(MachineInit.windmill);
		ClimateMain.machine.addSubItem(MachineInit.windmill_l);
		ClimateMain.machine.addSubItem(MachineInit.windmill_ex);
		ClimateMain.machine.addSubItem(MachineInit.watermill);
		ClimateMain.machine.addSubItem(MachineInit.boilerTurbine);

		if (ModuleConfig.machine_advanced) {
			ClimateMain.machine.addSubItem(MachineInit.dieselEngine);
			ClimateMain.machine.addSubItem(MachineInit.motor);
			ClimateMain.machine.addSubItem(MachineInit.dynamo);
		}

		ClimateMain.machine.addSubItem(MachineInit.creativeBox);

		// shaft
		ClimateMain.machine.addSubItem(MachineInit.shaft_s);
		ClimateMain.machine.addSubItem(MachineInit.shaft_l);
		ClimateMain.machine.addSubItem(MachineInit.shaft_t_a);
		ClimateMain.machine.addSubItem(MachineInit.shaft_t_b);
		ClimateMain.machine.addSubItem(MachineInit.shaft_x);
		ClimateMain.machine.addSubItem(MachineInit.shaft_switch);
		ClimateMain.machine.addSubItem(MachineInit.shaft3_s);
		ClimateMain.machine.addSubItem(MachineInit.shaft3_l);
		ClimateMain.machine.addSubItem(MachineInit.shaft3_t_a);
		ClimateMain.machine.addSubItem(MachineInit.shaft3_t_b);
		ClimateMain.machine.addSubItem(MachineInit.shaft3_x);
		ClimateMain.machine.addSubItem(MachineInit.shaft3_switch);
		ClimateMain.machine.addSubItem(MachineInit.shaft2_s);
		ClimateMain.machine.addSubItem(MachineInit.shaft2_l);
		ClimateMain.machine.addSubItem(MachineInit.shaft2_t_a);
		ClimateMain.machine.addSubItem(MachineInit.shaft2_t_b);
		ClimateMain.machine.addSubItem(MachineInit.shaft2_x);
		ClimateMain.machine.addSubItem(MachineInit.gearbox);
		ClimateMain.machine.addSubItem(MachineInit.gearbox2);

		// inventory
		ClimateMain.machine.addSubItem(MachineInit.hopperFilter);
		ClimateMain.machine.addSubItem(MachineInit.hopperGold);
		ClimateMain.machine.addSubItem(MachineInit.hopperFilterG);
		ClimateMain.machine.addSubItem(MachineInit.hopperSilver);
		if (ModuleConfig.machine_advanced) {
			ClimateMain.machine.addSubItem(MachineInit.hopperSUS);
		}
		ClimateMain.machine.addSubItem(MachineInit.conveyor);
		ClimateMain.machine.addSubItem(MachineInit.catapult);

		// fluid
		ClimateMain.machine.addSubItem(MachineInit.faucet);
		ClimateMain.machine.addSubItem(MachineInit.faucet_r);
		ClimateMain.machine.addSubItem(MachineInit.faucet_sus);
		ClimateMain.machine.addSubItem(MainInit.pail);
		ClimateMain.machine.addSubItem(MachineInit.IBC);
		ClimateMain.machine.addSubItem(MachineInit.hopperFluid);

		if (ModuleConfig.machine_advanced) {
			ClimateMain.machine.addSubItem(MachineInit.waterPump);
			ClimateMain.machine.addSubItem(MachineInit.tankYard);
			ClimateMain.machine.addSubItem(MachineInit.tankYardPart);
		}

		// reactor
		ClimateMain.machine.addSubItem(MachineInit.spinning);
		ClimateMain.machine.addSubItem(MachineInit.stonemill);
		ClimateMain.machine.addSubItem(MachineInit.IBC_reactor);
		if (ModuleConfig.machine_advanced) {
			ClimateMain.machine.addSubItem(MachineInit.pressMachine);
			ClimateMain.machine.addSubItem(MachineInit.reactor);
			ClimateMain.machine.addSubItem(MachineInit.crusher);
			ClimateMain.machine.addSubItem(MachineInit.oscillator);
		}
		if (ModuleConfig.machine_advanced) {
			// portal
			ClimateMain.machine.addSubItem(MachineInit.adapterPanel);
			ClimateMain.machine.addSubItem(MachineInit.acceptorPanel);
			ClimateMain.machine.addSubItem(MachineInit.adapterFluidPanel);
			ClimateMain.machine.addSubItem(MachineInit.acceptorFluidPanel);
			ClimateMain.machine.addSubItem(MachineInit.wirelessPortal);
			ClimateMain.machine.addSubItem(MachineInit.adapterCard);
			ClimateMain.machine.addSubItem(MachineInit.playerPanel);
			ClimateMain.machine.addSubItem(MachineInit.entityPanel);
			ClimateMain.machine.addSubItem(MachineInit.monitorRS);
			ClimateMain.machine.addSubItem(MachineInit.monitorCM);
			ClimateMain.machine.addSubItem(MachineInit.monitorTorque);
			ClimateMain.machine.addSubItem(MachineInit.monitorRF);
			ClimateMain.machine.addSubItem(MachineInit.monitorFluid);
			ClimateMain.machine.addSubItem(MachineInit.monitorItem);
			ClimateMain.machine.addSubItem(MachineInit.monitorTemp);
		}

		ClimateMain.machine.addSubItem(MachineInit.slotCard);

		if (ModuleConfig.machine_advanced) {
			ClimateMain.machine.addSubItem(MachineInit.mold);
			ClimateMain.machine.addSubItem(MachineInit.moldAluminium);
			ClimateMain.machine.addSubItem(MachineInit.moldAlloy);
			ClimateMain.machine.addSubItem(MachineInit.catalyst);
			ClimateMain.machine.addSubItem(MachineInit.rotaryBlade);

			ClimateMain.machine.addSubItem(MachineInit.dynamite);
			ClimateMain.machine.addSubItem(MachineInit.motorMinecart);
			ClimateMain.machine.addSubItem(MachineInit.scooter);
			ClimateMain.machine.addSubItem(MachineInit.magneticHover);

			ClimateMain.machine.addSubItem(MachineInit.platingChrome);
			ClimateMain.machine.addSubItem(MachineInit.gemcore);
		}

	}

	static void loadFluids() {

	}

	public static void registerTierBlock(Block block, String name, int i) {
		Block reg = block.setRegistryName(ClimateMain.MOD_ID, name);
		ForgeRegistries.BLOCKS.register(reg);
		ForgeRegistries.ITEMS.register(new ItemBlockHighTier(reg, i));
	}

}
