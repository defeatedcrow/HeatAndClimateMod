package defeatedcrow.hac.machine;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
import defeatedcrow.hac.machine.block.TileBoilerTurbine;
import defeatedcrow.hac.machine.block.TileCrank_S;
import defeatedcrow.hac.machine.block.TileCreativeBox;
import defeatedcrow.hac.machine.block.TileFan;
import defeatedcrow.hac.machine.block.TileFauset;
import defeatedcrow.hac.machine.block.TileGearBox;
import defeatedcrow.hac.machine.block.TileGearBox_SUS;
import defeatedcrow.hac.machine.block.TileHandCrank;
import defeatedcrow.hac.machine.block.TileHeatExchanger;
import defeatedcrow.hac.machine.block.TileIBC;
import defeatedcrow.hac.machine.block.TileKineticMotor;
import defeatedcrow.hac.machine.block.TilePressMachine;
import defeatedcrow.hac.machine.block.TileRedBox;
import defeatedcrow.hac.machine.block.TileShaft_L;
import defeatedcrow.hac.machine.block.TileShaft_L_SUS;
import defeatedcrow.hac.machine.block.TileShaft_S;
import defeatedcrow.hac.machine.block.TileShaft_S_SUS;
import defeatedcrow.hac.machine.block.TileShaft_TA;
import defeatedcrow.hac.machine.block.TileShaft_TA_SUS;
import defeatedcrow.hac.machine.block.TileShaft_TB;
import defeatedcrow.hac.machine.block.TileShaft_TB_SUS;
import defeatedcrow.hac.machine.block.TileStoneMill;
import defeatedcrow.hac.machine.block.TileWaterPump;
import defeatedcrow.hac.machine.block.TileWindmill;
import defeatedcrow.hac.machine.block.TileWindmill_L;
import defeatedcrow.hac.machine.client.BoilerTurbineTESR;
import defeatedcrow.hac.machine.client.CreativeBoxTESR;
import defeatedcrow.hac.machine.client.FanTESR;
import defeatedcrow.hac.machine.client.GearBoxTESR;
import defeatedcrow.hac.machine.client.HandCrankTESR;
import defeatedcrow.hac.machine.client.HeatExchangerTESR;
import defeatedcrow.hac.machine.client.IBCTESR;
import defeatedcrow.hac.machine.client.KineticMotorTESR;
import defeatedcrow.hac.machine.client.L_ShaftTESR;
import defeatedcrow.hac.machine.client.L_WindmillTESR;
import defeatedcrow.hac.machine.client.PressMachineTESR;
import defeatedcrow.hac.machine.client.RedBoxTESR;
import defeatedcrow.hac.machine.client.SUS_GearBoxTESR;
import defeatedcrow.hac.machine.client.SUS_L_ShaftTESR;
import defeatedcrow.hac.machine.client.SUS_S_ShaftTESR;
import defeatedcrow.hac.machine.client.SUS_TA_ShaftTESR;
import defeatedcrow.hac.machine.client.SUS_TB_ShaftTESR;
import defeatedcrow.hac.machine.client.S_CrankTESR;
import defeatedcrow.hac.machine.client.S_ShaftTESR;
import defeatedcrow.hac.machine.client.StoneMillTESR;
import defeatedcrow.hac.machine.client.TA_ShaftTESR;
import defeatedcrow.hac.machine.client.TB_ShaftTESR;
import defeatedcrow.hac.machine.client.WaterPumpTESR;
import defeatedcrow.hac.machine.client.WindmillTESR;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MachineClientProxy {

	public static void loadTE() {
		ClientRegistry.registerTileEntity(TileWindmill.class, "dcs_te_windmill", new WindmillTESR());
		ClientRegistry.registerTileEntity(TileWindmill_L.class, "dcs_te_windmill_l", new L_WindmillTESR());
		ClientRegistry.registerTileEntity(TileShaft_S.class, "dcs_te_shaft_s", new S_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_L.class, "dcs_te_shaft_l", new L_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_TA.class, "dcs_te_shaft_ta", new TA_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_TB.class, "dcs_te_shaft_tb", new TB_ShaftTESR());
		ClientRegistry.registerTileEntity(TileGearBox.class, "dcs_te_gearbox", new GearBoxTESR());
		ClientRegistry.registerTileEntity(TileCrank_S.class, "dcs_te_crank_s", new S_CrankTESR());
		ClientRegistry.registerTileEntity(TileHandCrank.class, "dcs_te_handcrank", new HandCrankTESR());
		ClientRegistry.registerTileEntity(TileStoneMill.class, "dcs_te_stonemill", new StoneMillTESR());
		ClientRegistry.registerTileEntity(TileRedBox.class, "dcs_te_redbox", new RedBoxTESR());
		ClientRegistry.registerTileEntity(TileFan.class, "dcs_te_fan", new FanTESR());
		GameRegistry.registerTileEntity(TileFauset.class, "dcs_te_fauset");
		ClientRegistry.registerTileEntity(TileIBC.class, "dcs_te_ibc", new IBCTESR());
		ClientRegistry.registerTileEntity(TileHeatExchanger.class, "dcs_te_heatexchanger", new HeatExchangerTESR());
		ClientRegistry.registerTileEntity(TileShaft_S_SUS.class, "dcs_te_shaft_s_sus", new SUS_S_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_L_SUS.class, "dcs_te_shaft_l_sus", new SUS_L_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_TA_SUS.class, "dcs_te_shaft_ta_sus", new SUS_TA_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_TB_SUS.class, "dcs_te_shaft_tb_sus", new SUS_TB_ShaftTESR());
		ClientRegistry.registerTileEntity(TileGearBox_SUS.class, "dcs_te_gearbox_sus", new SUS_GearBoxTESR());
		ClientRegistry.registerTileEntity(TileBoilerTurbine.class, "dcs_te_boiler_turbine", new BoilerTurbineTESR());
		ClientRegistry.registerTileEntity(TileKineticMotor.class, "dcs_te_kinetic_motor", new KineticMotorTESR());
		ClientRegistry.registerTileEntity(TilePressMachine.class, "dcs_te_press_machine", new PressMachineTESR());
		ClientRegistry.registerTileEntity(TileWaterPump.class, "dcs_te_water_pump", new WaterPumpTESR());
		ClientRegistry.registerTileEntity(TileCreativeBox.class, "dcs_te_creative", new CreativeBoxTESR());
	}

	public static void regJson(JsonRegisterHelper instance) {
		// block

		instance.regTETorqueBlock(MachineInit.windmill, ClimateCore.PACKAGE_ID, "dcs_device_windmill", "machine", 0);
		instance.regTETorqueBlock(MachineInit.windmill_l, ClimateCore.PACKAGE_ID, "dcs_device_windmill_l", "machine",
				0);
		instance.regTETorqueBlock(MachineInit.shaft_s, ClimateCore.PACKAGE_ID, "dcs_device_shaft_s", "machine", 0);
		instance.regTETorqueBlock(MachineInit.shaft_l, ClimateCore.PACKAGE_ID, "dcs_device_shaft_l", "machine", 0);
		instance.regTETorqueBlock(MachineInit.shaft_t_a, ClimateCore.PACKAGE_ID, "dcs_device_shaft_ta", "machine", 0);
		instance.regTETorqueBlock(MachineInit.shaft_t_b, ClimateCore.PACKAGE_ID, "dcs_device_shaft_tb", "machine", 0);
		instance.regTETorqueBlock(MachineInit.gearbox, ClimateCore.PACKAGE_ID, "dcs_device_gearbox", "machine", 0);
		instance.regTETorqueBlock(MachineInit.piston, ClimateCore.PACKAGE_ID, "dcs_device_crank_s", "machine", 0);
		instance.regTETorqueBlock(MachineInit.handcrank, ClimateCore.PACKAGE_ID, "dcs_device_handcrank", "machine", 0);
		instance.regTETorqueBlock(MachineInit.stonemill, ClimateCore.PACKAGE_ID, "dcs_device_stonemill", "machine", 0);
		instance.regTETorqueBlock(MachineInit.fan, ClimateCore.PACKAGE_ID, "dcs_device_fan", "machine", 0);
		instance.regTETorqueBlock(MachineInit.redbox, ClimateCore.PACKAGE_ID, "dcs_device_redbox", "machine", 0);
		instance.regTETorqueBlock(MachineInit.heatPump, ClimateCore.PACKAGE_ID, "dcs_device_heat_exchanger", "machine",
				0);
		instance.regTETorqueBlock(MachineInit.shaft2_s, ClimateCore.PACKAGE_ID, "dcs_device_shaft_s_sus", "machine", 0);
		instance.regTETorqueBlock(MachineInit.shaft2_l, ClimateCore.PACKAGE_ID, "dcs_device_shaft_l_sus", "machine", 0);
		instance.regTETorqueBlock(MachineInit.shaft2_t_a, ClimateCore.PACKAGE_ID, "dcs_device_shaft_ta_sus", "machine",
				0);
		instance.regTETorqueBlock(MachineInit.shaft2_t_b, ClimateCore.PACKAGE_ID, "dcs_device_shaft_tb_sus", "machine",
				0);
		instance.regTETorqueBlock(MachineInit.gearbox2, ClimateCore.PACKAGE_ID, "dcs_device_gearbox_sus", "machine", 0);
		instance.regTETorqueBlock(MachineInit.boilerTurbine, ClimateCore.PACKAGE_ID, "dcs_device_boiler_turbine",
				"machine", 0);
		instance.regTETorqueBlock(MachineInit.motor, ClimateCore.PACKAGE_ID, "dcs_device_kinetic_motor", "machine", 0);
		instance.regTETorqueBlock(MachineInit.pressMachine, ClimateCore.PACKAGE_ID, "dcs_device_press_machine",
				"machine", 0);
		instance.regTETorqueBlock(MachineInit.waterPump, ClimateCore.PACKAGE_ID, "dcs_device_water_pump", "machine", 0);

		instance.regTEBlock(MachineInit.IBC, ClimateCore.PACKAGE_ID, "dcs_device_ibc", "machine", 0);
		instance.regSimpleBlock(MachineInit.fauset, ClimateCore.PACKAGE_ID, "dcs_device_fauset", "machine", 0);

		instance.regSimpleItem(MachineInit.machimeMaterials, ClimateCore.PACKAGE_ID, "dcs_device_mechanical", "machine",
				1);
		instance.regSimpleItem(MachineInit.mold, ClimateCore.PACKAGE_ID, "dcs_device_mold_steel", "machine", 0);
		instance.regSimpleItem(MachineInit.torqueChecker, ClimateCore.PACKAGE_ID, "dcs_device_torque_checker_steel",
				"machine", 0);

		instance.regTETorqueBlock(MachineInit.creativeBox, ClimateCore.PACKAGE_ID, "dcs_device_creative_box", "machine",
				0);
	}

}
