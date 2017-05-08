package defeatedcrow.hac.machine;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;

public class MachineInit {

	private MachineInit() {}

	// tier 1

	public static Block windmill;
	public static Block windmill_l;
	public static Block handcrank;

	public static Block shaft_s;
	public static Block shaft_l;
	public static Block shaft_t_a;
	public static Block shaft_t_b;
	public static Block gearbox;

	public static Block piston;

	public static Block fan;
	public static Block redbox;

	// tier 2

	public static Block fauset;
	public static Block IBC;

	public static Block conveyor;
	public static Block hopperFilter;
	public static Block hopperFluid;

	public static Block watermill;

	public static Block stonemill;
	public static Block heatPump;
	public static Block catapult;

	// tier 3

	public static Block shaft2_s;
	public static Block shaft2_l;
	public static Block shaft2_t_a;
	public static Block shaft2_t_b;
	public static Block gearbox2;

	public static Block boilerTurbine; // Climate -> Torque
	public static Block motor; // FU/RF -> Torque
	public static Block dynamo; // Torque -> FU/RF
	public static Block converter; // EU/RF -> FU

	public static Block waterPump;
	public static Block pressMachine;

	public static Item torqueChecker;
	public static Item mold;
	public static Item machimeMaterials;

	public static Block creativeBox;

	// advanced

	public static Fluid hydrogen;
	public static Fluid ammonia;
	public static Fluid nitricAcid;
	public static Fluid sulfuricAcid;
	public static Fluid fuelOil;
	public static Fluid fuelGas;

	public static Block hydrogenBlock;
	public static Block ammoniaBlock;
	public static Block nitricAcidBlock;
	public static Block sulfuricAcidBlock;
	public static Block fuelOilBlock;
	public static Block fuelGasBlock;

	public static Item reagent;
	public static Item synthetic;
	public static Item catalyst;

	public static Item moldAlloy;
	public static Item moldAluminium;
	public static Item adapterCard;

	public static Block energyTank;
	public static Block adapterPanel;
	public static Block wirelessPortal;

	public static Block reactor;
	public static Block freezer;
}
