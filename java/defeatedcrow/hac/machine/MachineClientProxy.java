package defeatedcrow.hac.machine;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
import defeatedcrow.hac.machine.block.TileAcceptorPanel;
import defeatedcrow.hac.machine.block.TileAdapterPanel;
import defeatedcrow.hac.machine.block.TileBoilerTurbine;
import defeatedcrow.hac.machine.block.TileCatapult;
import defeatedcrow.hac.machine.block.TileConveyor;
import defeatedcrow.hac.machine.block.TileCrank_S;
import defeatedcrow.hac.machine.block.TileCreativeBox;
import defeatedcrow.hac.machine.block.TileDynamo;
import defeatedcrow.hac.machine.block.TileFan;
import defeatedcrow.hac.machine.block.TileFauset;
import defeatedcrow.hac.machine.block.TileFreezer;
import defeatedcrow.hac.machine.block.TileGasBurner;
import defeatedcrow.hac.machine.block.TileGearBox;
import defeatedcrow.hac.machine.block.TileGearBox_SUS;
import defeatedcrow.hac.machine.block.TileHandCrank;
import defeatedcrow.hac.machine.block.TileHeatExchanger;
import defeatedcrow.hac.machine.block.TileHopperFilter;
import defeatedcrow.hac.machine.block.TileHopperFluid;
import defeatedcrow.hac.machine.block.TileIBC;
import defeatedcrow.hac.machine.block.TileKineticMotor;
import defeatedcrow.hac.machine.block.TilePortalManager;
import defeatedcrow.hac.machine.block.TilePressMachine;
import defeatedcrow.hac.machine.block.TileReactor;
import defeatedcrow.hac.machine.block.TileRedBox;
import defeatedcrow.hac.machine.block.TileShaft_L;
import defeatedcrow.hac.machine.block.TileShaft_L_SUS;
import defeatedcrow.hac.machine.block.TileShaft_L_Steel;
import defeatedcrow.hac.machine.block.TileShaft_S;
import defeatedcrow.hac.machine.block.TileShaft_S_SUS;
import defeatedcrow.hac.machine.block.TileShaft_S_Steel;
import defeatedcrow.hac.machine.block.TileShaft_TA;
import defeatedcrow.hac.machine.block.TileShaft_TA_SUS;
import defeatedcrow.hac.machine.block.TileShaft_TA_Steel;
import defeatedcrow.hac.machine.block.TileShaft_TB;
import defeatedcrow.hac.machine.block.TileShaft_TB_SUS;
import defeatedcrow.hac.machine.block.TileShaft_TB_Steel;
import defeatedcrow.hac.machine.block.TileSpinningMachine;
import defeatedcrow.hac.machine.block.TileStoneMill;
import defeatedcrow.hac.machine.block.TileWaterPump;
import defeatedcrow.hac.machine.block.TileWatermill;
import defeatedcrow.hac.machine.block.TileWindmill;
import defeatedcrow.hac.machine.block.TileWindmill_L;
import defeatedcrow.hac.machine.client.BoilerTurbineTESR;
import defeatedcrow.hac.machine.client.CatapultTESR;
import defeatedcrow.hac.machine.client.ConveyorTESR;
import defeatedcrow.hac.machine.client.CreativeBoxTESR;
import defeatedcrow.hac.machine.client.DynamoTESR;
import defeatedcrow.hac.machine.client.FanTESR;
import defeatedcrow.hac.machine.client.FreezerTESR;
import defeatedcrow.hac.machine.client.GasBurnerTESR;
import defeatedcrow.hac.machine.client.GearBoxTESR;
import defeatedcrow.hac.machine.client.HandCrankTESR;
import defeatedcrow.hac.machine.client.HeatExchangerTESR;
import defeatedcrow.hac.machine.client.HopperFluidTESR;
import defeatedcrow.hac.machine.client.IBCTESR;
import defeatedcrow.hac.machine.client.KineticMotorTESR;
import defeatedcrow.hac.machine.client.L_ShaftTESR;
import defeatedcrow.hac.machine.client.L_WindmillTESR;
import defeatedcrow.hac.machine.client.PortalManagerTESR;
import defeatedcrow.hac.machine.client.PressMachineTESR;
import defeatedcrow.hac.machine.client.ReactorTESR;
import defeatedcrow.hac.machine.client.RedBoxTESR;
import defeatedcrow.hac.machine.client.RenderCartMotor;
import defeatedcrow.hac.machine.client.RenderMagneticHover;
import defeatedcrow.hac.machine.client.RenderScooter;
import defeatedcrow.hac.machine.client.SUS_GearBoxTESR;
import defeatedcrow.hac.machine.client.SUS_L_ShaftTESR;
import defeatedcrow.hac.machine.client.SUS_S_ShaftTESR;
import defeatedcrow.hac.machine.client.SUS_TA_ShaftTESR;
import defeatedcrow.hac.machine.client.SUS_TB_ShaftTESR;
import defeatedcrow.hac.machine.client.S_CrankTESR;
import defeatedcrow.hac.machine.client.S_ShaftTESR;
import defeatedcrow.hac.machine.client.SpinningMachineTESR;
import defeatedcrow.hac.machine.client.Steel_L_ShaftTESR;
import defeatedcrow.hac.machine.client.Steel_S_ShaftTESR;
import defeatedcrow.hac.machine.client.Steel_TA_ShaftTESR;
import defeatedcrow.hac.machine.client.Steel_TB_ShaftTESR;
import defeatedcrow.hac.machine.client.StoneMillTESR;
import defeatedcrow.hac.machine.client.TA_ShaftTESR;
import defeatedcrow.hac.machine.client.TB_ShaftTESR;
import defeatedcrow.hac.machine.client.WaterPumpTESR;
import defeatedcrow.hac.machine.client.WatermillTESR;
import defeatedcrow.hac.machine.client.WindmillTESR;
import defeatedcrow.hac.machine.entity.EntityMagneticHover;
import defeatedcrow.hac.machine.entity.EntityMinecartMotor;
import defeatedcrow.hac.machine.entity.EntityScooter;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.client.ClientMainProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MachineClientProxy {

	public static void loadEntity() {
		ClientMainProxy.registRender(EntityMinecartMotor.class, RenderCartMotor.class);
		ClientMainProxy.registRender(EntityScooter.class, RenderScooter.class);
		ClientMainProxy.registRender(EntityMagneticHover.class, RenderMagneticHover.class);
	}

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
		ClientRegistry.registerTileEntity(TileConveyor.class, "dcs_te_conveyor", new ConveyorTESR());
		ClientRegistry.registerTileEntity(TileCatapult.class, "dcs_te_catapult", new CatapultTESR());
		GameRegistry.registerTileEntity(TileHopperFilter.class, "dcs_te_hopper_filter");
		ClientRegistry.registerTileEntity(TileHopperFluid.class, "dcs_te_hopper_fluid", new HopperFluidTESR());
		ClientRegistry.registerTileEntity(TileWatermill.class, "dcs_te_watermill", new WatermillTESR());
		ClientRegistry.registerTileEntity(TileDynamo.class, "dcs_te_dynamo", new DynamoTESR());
		ClientRegistry.registerTileEntity(TileReactor.class, "dcs_te_reactor", new ReactorTESR());
		ClientRegistry.registerTileEntity(TileFreezer.class, "dcs_te_freezer", new FreezerTESR());
		ClientRegistry.registerTileEntity(TileSpinningMachine.class, "dcs_te_spinning_machine",
				new SpinningMachineTESR());
		ClientRegistry.registerTileEntity(TilePortalManager.class, "dcs_te_portal_manager", new PortalManagerTESR());
		GameRegistry.registerTileEntity(TileAdapterPanel.class, "dcs_te_adapter_item");
		GameRegistry.registerTileEntity(TileAcceptorPanel.class, "dcs_te_acceptor_item");
		ClientRegistry.registerTileEntity(TileGasBurner.class, "dcs_te_gas_burner", new GasBurnerTESR());
		ClientRegistry.registerTileEntity(TileShaft_S_Steel.class, "dcs_te_shaft_s_steel", new Steel_S_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_L_Steel.class, "dcs_te_shaft_l_steel", new Steel_L_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_TA_Steel.class, "dcs_te_shaft_ta_steel", new Steel_TA_ShaftTESR());
		ClientRegistry.registerTileEntity(TileShaft_TB_Steel.class, "dcs_te_shaft_tb_steel", new Steel_TB_ShaftTESR());
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
		instance.regTETorqueBlock(MachineInit.shaft3_s, ClimateCore.PACKAGE_ID, "dcs_device_shaft_s_steel", "machine",
				0);
		instance.regTETorqueBlock(MachineInit.shaft3_l, ClimateCore.PACKAGE_ID, "dcs_device_shaft_l_steel", "machine",
				0);
		instance.regTETorqueBlock(MachineInit.shaft3_t_a, ClimateCore.PACKAGE_ID, "dcs_device_shaft_ta_steel",
				"machine", 0);
		instance.regTETorqueBlock(MachineInit.shaft3_t_b, ClimateCore.PACKAGE_ID, "dcs_device_shaft_tb_steel",
				"machine", 0);
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
				5);
		instance.regSimpleItem(MachineInit.mold, ClimateCore.PACKAGE_ID, "dcs_device_mold_steel", "machine", 0);
		instance.regSimpleItem(MachineInit.torqueChecker, ClimateCore.PACKAGE_ID, "dcs_device_torque_checker_steel",
				"machine", 0);
		instance.regTETorqueBlock(MachineInit.creativeBox, ClimateCore.PACKAGE_ID, "dcs_device_creative_box", "machine",
				0);
		instance.regTETorqueBlock(MachineInit.catapult, ClimateCore.PACKAGE_ID, "dcs_device_catapult", "machine", 0);
		instance.regSimpleBlock(MachineInit.conveyor, ClimateCore.PACKAGE_ID, "dcs_device_conveyor", "machine", 0);
		instance.regSimpleBlock(MachineInit.hopperFilter, ClimateCore.PACKAGE_ID, "dcs_device_hopper_filter", "machine",
				0);
		instance.regTETorqueBlock(MachineInit.hopperFluid, ClimateCore.PACKAGE_ID, "dcs_device_hopper_fluid", "machine",
				0);
		instance.regTETorqueBlock(MachineInit.watermill, ClimateCore.PACKAGE_ID, "dcs_device_watermill", "machine", 0);
		instance.regTETorqueBlock(MachineInit.dynamo, ClimateCore.PACKAGE_ID, "dcs_device_dynamo", "machine", 0);
		instance.regTETorqueBlock(MachineInit.reactor, ClimateCore.PACKAGE_ID, "dcs_device_reactor", "machine", 0);
		instance.regTETorqueBlock(MachineInit.freezer, ClimateCore.PACKAGE_ID, "dcs_device_freezer", "machine", 0);
		instance.regTETorqueBlock(MachineInit.spinning, ClimateCore.PACKAGE_ID, "dcs_device_spinning_machine",
				"machine", 0);
		instance.regTETorqueBlock(MachineInit.wirelessPortal, ClimateCore.PACKAGE_ID, "dcs_device_portal_manager",
				"machine", 0);
		instance.regTEBlock(MachineInit.burner, ClimateCore.PACKAGE_ID, "dcs_device_gas_burner", "machine", 0);

		instance.regSimpleItem(MachineInit.reagent, ClimateCore.PACKAGE_ID, "dcs_misc_reagent", "misc", 12);
		instance.regSimpleItem(MachineInit.synthetic, ClimateCore.PACKAGE_ID, "dcs_misc_synthetic", "misc", 2);
		instance.regSimpleItem(MachineInit.catalyst, ClimateCore.PACKAGE_ID, "dcs_misc_catalyst", "misc", 3);
		instance.regSimpleItem(MachineInit.gemcore, ClimateCore.PACKAGE_ID, "dcs_misc_gemcore", "misc", 1);
		instance.regSimpleItem(MachineInit.moldAluminium, ClimateCore.PACKAGE_ID, "dcs_device_mold_aluminium",
				"machine", 3);
		instance.regSimpleItem(MachineInit.moldAlloy, ClimateCore.PACKAGE_ID, "dcs_device_mold_alloy", "machine", 3);
		instance.regSimpleItem(MachineInit.adapterCard, ClimateCore.PACKAGE_ID, "dcs_device_adapter_card", "machine",
				3);
		instance.regSimpleItem(MachineInit.dynamite, ClimateCore.PACKAGE_ID, "dcs_tool_dynamite", "tool", 1);

		instance.regSimpleBlock(MachineInit.adapterPanel, ClimateCore.PACKAGE_ID, "dcs_device_adapter_item", "machine",
				0);
		instance.regSimpleBlock(MachineInit.acceptorPanel, ClimateCore.PACKAGE_ID, "dcs_device_acceptor_item",
				"machine", 0);
		instance.regSimpleItem(MachineInit.motorMinecart, ClimateCore.PACKAGE_ID, "dcs_motor_minecart", "device", 0);
		instance.regSimpleItem(MachineInit.scooter, ClimateCore.PACKAGE_ID, "dcs_motor_scooter", "device", 3);
		instance.regSimpleItem(MachineInit.magneticHover, ClimateCore.PACKAGE_ID, "dcs_magnetic_hover", "device", 0);
		instance.regSimpleItem(MachineInit.platingChrome, ClimateCore.PACKAGE_ID, "dcs_coating_tool", "misc", 9);

		// fluid
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MachineInit.hydrogenBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MachineInit.hydrogenBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MachineInit.ammoniaBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MachineInit.ammoniaBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MachineInit.fuelGasBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MachineInit.fuelGasBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MachineInit.fuelOilBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MachineInit.fuelOilBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MachineInit.nitricAcidBlock),
				new ItemMeshDefinition() {
					final ModelResourceLocation fluidModel = new ModelResourceLocation(
							ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid", "fluid");

					@Override
					public ModelResourceLocation getModelLocation(ItemStack stack) {
						return fluidModel;
					}
				});
		ModelLoader.setCustomStateMapper(MachineInit.nitricAcidBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MachineInit.sulfuricAcidBlock),
				new ItemMeshDefinition() {
					final ModelResourceLocation fluidModel = new ModelResourceLocation(
							ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid", "fluid");

					@Override
					public ModelResourceLocation getModelLocation(ItemStack stack) {
						return fluidModel;
					}
				});
		ModelLoader.setCustomStateMapper(MachineInit.sulfuricAcidBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MachineInit.nitrogenBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitrogen", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MachineInit.nitrogenBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitrogen", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
	}

}
