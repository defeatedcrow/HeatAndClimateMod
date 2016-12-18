package defeatedcrow.hac.machine;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class MachineInit {

	private MachineInit() {
	}

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
	public static Block hopperFluid;
	public static Block IBC;

	public static Block stonemill;
	public static Block heatPump;
	public static Block conveyor;

	// tier 3

	public static Block shaft2_s;
	public static Block shaft2_l;
	public static Block shaft2_t_a;
	public static Block shaft2_t_b;
	public static Block gearbox2;

	public static Block boilerTurbine; // Climate -> Torque
	public static Block motor; // FU -> Torque
	public static Block generator; // Torque -> FU
	public static Block converter; // EU/RF -> FU

	public static Block waterPump;
	public static Block pressMachine;
	public static Block hopperR;
	public static Block catapult;

	public static Item torqueChecker;
	public static Item mold;
	public static Item machimeMaterials;

	public static Block creativeBox;
}
