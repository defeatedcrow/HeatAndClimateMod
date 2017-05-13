package defeatedcrow.hac.machine;

import defeatedcrow.hac.machine.block.TileBoilerTurbine;
import defeatedcrow.hac.machine.block.TileCatapult;
import defeatedcrow.hac.machine.block.TileConveyor;
import defeatedcrow.hac.machine.block.TileCrank_S;
import defeatedcrow.hac.machine.block.TileCreativeBox;
import defeatedcrow.hac.machine.block.TileDynamo;
import defeatedcrow.hac.machine.block.TileFan;
import defeatedcrow.hac.machine.block.TileFauset;
import defeatedcrow.hac.machine.block.TileGearBox;
import defeatedcrow.hac.machine.block.TileGearBox_SUS;
import defeatedcrow.hac.machine.block.TileHandCrank;
import defeatedcrow.hac.machine.block.TileHeatExchanger;
import defeatedcrow.hac.machine.block.TileHopperFilter;
import defeatedcrow.hac.machine.block.TileHopperFluid;
import defeatedcrow.hac.machine.block.TileIBC;
import defeatedcrow.hac.machine.block.TileKineticMotor;
import defeatedcrow.hac.machine.block.TilePressMachine;
import defeatedcrow.hac.machine.block.TileReactor;
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
import defeatedcrow.hac.machine.block.TileWatermill;
import defeatedcrow.hac.machine.block.TileWindmill;
import defeatedcrow.hac.machine.block.TileWindmill_L;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MachineCommonProxy {

	public static void loadTE() {
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
		GameRegistry.registerTileEntity(TileFauset.class, "dcs_te_fauset");
		GameRegistry.registerTileEntity(TileIBC.class, "dcs_te_ibc");
		GameRegistry.registerTileEntity(TileHeatExchanger.class, "dcs_te_heatexchanger");
		GameRegistry.registerTileEntity(TileShaft_S_SUS.class, "dcs_te_shaft_s_sus");
		GameRegistry.registerTileEntity(TileShaft_L_SUS.class, "dcs_te_shaft_l_sus");
		GameRegistry.registerTileEntity(TileShaft_TA_SUS.class, "dcs_te_shaft_ta_sus");
		GameRegistry.registerTileEntity(TileShaft_TB_SUS.class, "dcs_te_shaft_tb_sus");
		GameRegistry.registerTileEntity(TileGearBox_SUS.class, "dcs_te_gearbox_sus");
		GameRegistry.registerTileEntity(TileBoilerTurbine.class, "dcs_te_boiler_turbine");
		GameRegistry.registerTileEntity(TileKineticMotor.class, "dcs_te_kinetic_motor");
		GameRegistry.registerTileEntity(TilePressMachine.class, "dcs_te_press_machine");
		GameRegistry.registerTileEntity(TileWaterPump.class, "dcs_te_water_pump");
		GameRegistry.registerTileEntity(TileCreativeBox.class, "dcs_te_creative");
		GameRegistry.registerTileEntity(TileConveyor.class, "dcs_te_conveyor");
		GameRegistry.registerTileEntity(TileCatapult.class, "dcs_te_catapult");
		GameRegistry.registerTileEntity(TileHopperFilter.class, "dcs_te_hopper_filter");
		GameRegistry.registerTileEntity(TileHopperFluid.class, "dcs_te_hopper_fluid");
		GameRegistry.registerTileEntity(TileWatermill.class, "dcs_te_watermill");
		GameRegistry.registerTileEntity(TileDynamo.class, "dcs_te_dynamo");
		GameRegistry.registerTileEntity(TileReactor.class, "dcs_te_reactor");
	}

}
