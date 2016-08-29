package defeatedcrow.hac.machine;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
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
import defeatedcrow.hac.machine.client.FanTESR;
import defeatedcrow.hac.machine.client.GearBoxTESR;
import defeatedcrow.hac.machine.client.HandCrankTESR;
import defeatedcrow.hac.machine.client.L_ShaftTESR;
import defeatedcrow.hac.machine.client.L_WindmillTESR;
import defeatedcrow.hac.machine.client.RedBoxTESR;
import defeatedcrow.hac.machine.client.S_CrankTESR;
import defeatedcrow.hac.machine.client.S_ShaftTESR;
import defeatedcrow.hac.machine.client.StoneMillTESR;
import defeatedcrow.hac.machine.client.TA_ShaftTESR;
import defeatedcrow.hac.machine.client.TB_ShaftTESR;
import defeatedcrow.hac.machine.client.WindmillTESR;

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
	}

	public static void regJson(JsonRegisterHelper instance) {
		// block

		instance.regTETorqueBlock(MachineInit.windmill, ClimateCore.PACKAGE_ID, "dcs_device_windmill", "machine", 0);
		instance.regTETorqueBlock(MachineInit.windmill_l, ClimateCore.PACKAGE_ID, "dcs_device_windmill_l", "machine", 0);
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
	}

}
