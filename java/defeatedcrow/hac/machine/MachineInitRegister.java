package defeatedcrow.hac.machine;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.machine.block.BlockBoilerTurbine;
import defeatedcrow.hac.machine.block.BlockCrank_S;
import defeatedcrow.hac.machine.block.BlockCreativeBox;
import defeatedcrow.hac.machine.block.BlockFan;
import defeatedcrow.hac.machine.block.BlockFauset;
import defeatedcrow.hac.machine.block.BlockGearBox;
import defeatedcrow.hac.machine.block.BlockGearBox_SUS;
import defeatedcrow.hac.machine.block.BlockHandCrank;
import defeatedcrow.hac.machine.block.BlockHeatExchanger;
import defeatedcrow.hac.machine.block.BlockIBC;
import defeatedcrow.hac.machine.block.BlockKineticMotor;
import defeatedcrow.hac.machine.block.BlockPressMachine;
import defeatedcrow.hac.machine.block.BlockRedBox;
import defeatedcrow.hac.machine.block.BlockShaft_L;
import defeatedcrow.hac.machine.block.BlockShaft_L_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_S;
import defeatedcrow.hac.machine.block.BlockShaft_S_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_TA;
import defeatedcrow.hac.machine.block.BlockShaft_TA_SUS;
import defeatedcrow.hac.machine.block.BlockShaft_TB;
import defeatedcrow.hac.machine.block.BlockShaft_TB_SUS;
import defeatedcrow.hac.machine.block.BlockStoneMill;
import defeatedcrow.hac.machine.block.BlockWaterPump;
import defeatedcrow.hac.machine.block.BlockWindmill;
import defeatedcrow.hac.machine.block.BlockWindmill_L;
import defeatedcrow.hac.machine.block.ItemBlockHighTier;
import defeatedcrow.hac.machine.block.ItemIBC;
import defeatedcrow.hac.machine.item.ItemSteelMold;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MachineInitRegister {

	private MachineInitRegister() {
	}

	public static void load() {
		loadBlocks();
		loadItems();

		if (ModuleConfig.machine)
			loadCreativeTab();
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
		registerTierBlock(MachineInit.gearbox, ClimateCore.PACKAGE_BASE + "_device_gearbox", 1);

		MachineInit.piston = new BlockCrank_S(ClimateCore.PACKAGE_BASE + "_device_crank_s");
		registerTierBlock(MachineInit.piston, ClimateCore.PACKAGE_BASE + "_device_crank_s", 1);

		MachineInit.handcrank = new BlockHandCrank(ClimateCore.PACKAGE_BASE + "_device_handcrank");
		registerTierBlock(MachineInit.handcrank, ClimateCore.PACKAGE_BASE + "_device_handcrank", 1);

		MachineInit.stonemill = new BlockStoneMill(ClimateCore.PACKAGE_BASE + "_device_stonemill");
		registerTierBlock(MachineInit.stonemill, ClimateCore.PACKAGE_BASE + "_device_stonemill", 1);

		MachineInit.fan = new BlockFan(ClimateCore.PACKAGE_BASE + "_device_fan");
		registerTierBlock(MachineInit.fan, ClimateCore.PACKAGE_BASE + "_device_fan", 1);

		MachineInit.redbox = new BlockRedBox(ClimateCore.PACKAGE_BASE + "_device_redbox");
		registerTierBlock(MachineInit.redbox, ClimateCore.PACKAGE_BASE + "_device_redbox", 1);

		MachineInit.fauset = new BlockFauset(ClimateCore.PACKAGE_BASE + "_device_fauset");
		registerTierBlock(MachineInit.fauset, ClimateCore.PACKAGE_BASE + "_device_fauset", 2);

		MachineInit.IBC = new BlockIBC(ClimateCore.PACKAGE_BASE + "_device_ibc");
		MachineInit.IBC.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_ibc");
		GameRegistry.register(MachineInit.IBC);
		GameRegistry.register(new ItemIBC(MachineInit.IBC));

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

		MachineInit.pressMachine = new BlockPressMachine(ClimateCore.PACKAGE_BASE + "_device_press_machine");
		registerTierBlock(MachineInit.pressMachine, ClimateCore.PACKAGE_BASE + "_device_press_machine", 3);

		MachineInit.waterPump = new BlockWaterPump(ClimateCore.PACKAGE_BASE + "_device_water_pump");
		registerTierBlock(MachineInit.waterPump, ClimateCore.PACKAGE_BASE + "_device_water_pump", 3);

		MachineInit.creativeBox = new BlockCreativeBox(ClimateCore.PACKAGE_BASE + "_device_creative_box");
		registerTierBlock(MachineInit.creativeBox, ClimateCore.PACKAGE_BASE + "_device_creative_box", 3);
	}

	static void loadItems() {
		MachineInit.mold = new ItemSteelMold().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_mold");
		GameRegistry.register(MachineInit.mold.setRegistryName(ClimateCore.PACKAGE_BASE + "_mold"));
	}

	static void loadCreativeTab() {
		MachineInit.windmill.setCreativeTab(ClimateMain.machine);
		MachineInit.windmill_l.setCreativeTab(ClimateMain.machine);
		MachineInit.handcrank.setCreativeTab(ClimateMain.machine);

		MachineInit.shaft_s.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft_l.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft_t_a.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft_t_b.setCreativeTab(ClimateMain.machine);
		MachineInit.gearbox.setCreativeTab(ClimateMain.machine);

		MachineInit.piston.setCreativeTab(ClimateMain.machine);

		MachineInit.stonemill.setCreativeTab(ClimateMain.machine);
		MachineInit.fan.setCreativeTab(ClimateMain.machine);
		MachineInit.redbox.setCreativeTab(ClimateMain.machine);

		MachineInit.fauset.setCreativeTab(ClimateMain.machine);
		MachineInit.IBC.setCreativeTab(ClimateMain.machine);
		MachineInit.heatPump.setCreativeTab(ClimateMain.machine);

		MachineInit.shaft2_s.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft2_l.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft2_t_a.setCreativeTab(ClimateMain.machine);
		MachineInit.shaft2_t_b.setCreativeTab(ClimateMain.machine);
		MachineInit.gearbox2.setCreativeTab(ClimateMain.machine);

		MachineInit.boilerTurbine.setCreativeTab(ClimateMain.machine);
		MachineInit.motor.setCreativeTab(ClimateMain.machine);

		MachineInit.pressMachine.setCreativeTab(ClimateMain.machine);
		MachineInit.waterPump.setCreativeTab(ClimateMain.machine);

		MachineInit.mold.setCreativeTab(ClimateMain.machine);

		MachineInit.creativeBox.setCreativeTab(ClimateMain.machine);
	}

	public static void registerTierBlock(Block block, String name, int i) {
		Block reg = block.setRegistryName(name);
		GameRegistry.register(reg);
		GameRegistry.register(new ItemBlockHighTier(reg, i));
	}

}
