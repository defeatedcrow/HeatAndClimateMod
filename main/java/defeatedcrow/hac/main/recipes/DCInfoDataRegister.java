package defeatedcrow.hac.main.recipes;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.IDCInfoData;
import defeatedcrow.hac.main.api.IDCInfoDataRegister;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class DCInfoDataRegister implements IDCInfoDataRegister {

	public IDCInfoDataRegister instance() {
		return MainAPIManager.infoRegister;
	}

	private static List<IDCInfoData> list = new ArrayList<IDCInfoData>();

	@Override
	public List<IDCInfoData> getList() {
		return list;
	}

	@Override
	public void registerInfo(IDCInfoData data) {
		if (data != null) {
			list.add(data);
		}
	}

	public static void registerInfo() {
		if (WorldGenConfig.depositGen[0] > 0) {
			DCInfoData r1 = new DCInfoData(null, null, new ItemStack(MainInit.oreNew, 1, 0), "dcs.info.title.stone_r",
					"dcs.info.desc.stone_r");
			r1.machine.add(new ItemStack(MainInit.oreNew, 1, 5));
			r1.machine.add(new ItemStack(MainInit.oreNew, 1, 10));
			r1.machine.add(new ItemStack(MainInit.oreNew, 1, 11));
			MainAPIManager.infoRegister.registerInfo(r1);
		}

		if (WorldGenConfig.depositGen[1] > 0) {
			DCInfoData g1 = new DCInfoData(null, null, new ItemStack(MainInit.oreNew, 1, 1), "dcs.info.title.stone_g",
					"dcs.info.desc.stone_g");
			g1.machine.add(new ItemStack(MainInit.oreNew, 1, 6));
			MainAPIManager.infoRegister.registerInfo(g1);
		}

		if (WorldGenConfig.depositGen[2] > 0) {
			DCInfoData u1 = new DCInfoData(null, null, new ItemStack(MainInit.oreNew, 1, 2), "dcs.info.title.stone_u",
					"dcs.info.desc.stone_u");
			u1.machine.add(new ItemStack(MainInit.oreNew, 1, 7));
			MainAPIManager.infoRegister.registerInfo(u1);
		}

		if (WorldGenConfig.depositGen[3] > 0) {
			DCInfoData w1 = new DCInfoData(null, null, new ItemStack(MainInit.oreNew, 1, 3), "dcs.info.title.stone_w",
					"dcs.info.desc.stone_w");
			w1.machine.add(new ItemStack(MainInit.oreNew, 1, 8));
			MainAPIManager.infoRegister.registerInfo(w1);
		}

		if (WorldGenConfig.depositGen[4] > 0) {
			DCInfoData b1 = new DCInfoData(null, null, new ItemStack(MainInit.oreNew, 1, 4), "dcs.info.title.stone_b",
					"dcs.info.desc.stone_b");
			b1.machine.add(new ItemStack(MainInit.oreNew, 1, 9));
			b1.machine.add(new ItemStack(MainInit.oreNew, 1, 12));
			b1.machine.add(new ItemStack(MainInit.oreNew, 1, 13));
			MainAPIManager.infoRegister.registerInfo(b1);
		}

		DCInfoData dust = new DCInfoData(null, null, new ItemStack(MainInit.dustBlock, 1, 32767),
				"dcs.info.title.metal_dust", "dcs.info.desc.metal_dust");
		MainAPIManager.infoRegister.registerInfo(dust);

		DCInfoData gem1 = new DCInfoData(null, null, new ItemStack(MainInit.gems, 1, 0), "dcs.info.title.gem_u",
				"dcs.info.desc.gem_u");
		gem1.machine.add(new ItemStack(MainInit.gems, 1, 4));
		MainAPIManager.infoRegister.registerInfo(gem1);

		DCInfoData gem2 = new DCInfoData(null, null, new ItemStack(MainInit.gems, 1, 1), "dcs.info.title.gem_r",
				"dcs.info.desc.gem_r");
		gem2.machine.add(new ItemStack(MainInit.gems, 1, 14));
		MainAPIManager.infoRegister.registerInfo(gem2);

		DCInfoData gem3 = new DCInfoData(null, null, new ItemStack(MainInit.gems, 1, 5), "dcs.info.title.gem_g",
				"dcs.info.desc.gem_g");
		gem3.machine.add(new ItemStack(MainInit.gems, 1, 13));
		MainAPIManager.infoRegister.registerInfo(gem3);

		DCInfoData gem4 = new DCInfoData(null, null, new ItemStack(MainInit.gems, 1, 23), "dcs.info.title.gem_b",
				"dcs.info.desc.gem_b");
		gem4.machine.add(new ItemStack(MainInit.gems, 1, 11));
		MainAPIManager.infoRegister.registerInfo(gem4);

		DCInfoData gem5 = new DCInfoData(null, null, new ItemStack(MainInit.gems, 1, 2), "dcs.info.title.gem_w",
				"dcs.info.desc.gem_w");
		gem5.machine.add(new ItemStack(MainInit.gems, 1, 15));
		MainAPIManager.infoRegister.registerInfo(gem5);

		DCInfoData gem6 = new DCInfoData(null, null, new ItemStack(MainInit.gems, 1, 20), "dcs.info.title.gem_u2",
				"dcs.info.desc.gem_u2");
		MainAPIManager.infoRegister.registerInfo(gem6);

		DCInfoData gem7 = new DCInfoData(null, null, new ItemStack(MainInit.gems, 1, 21), "dcs.info.title.gem_r2",
				"dcs.info.desc.gem_r2");
		MainAPIManager.infoRegister.registerInfo(gem7);

		DCInfoData gem8 = new DCInfoData(null, null, new ItemStack(MainInit.gems, 1, 19), "dcs.info.title.gem_g2",
				"dcs.info.desc.gem_g2");
		MainAPIManager.infoRegister.registerInfo(gem8);

		DCInfoData gem9 = new DCInfoData(null, null, new ItemStack(MainInit.gems, 1, 22), "dcs.info.title.gem_b2",
				"dcs.info.desc.gem_b2");
		MainAPIManager.infoRegister.registerInfo(gem9);

		DCInfoData gem10 = new DCInfoData(null, null, new ItemStack(MainInit.gems, 1, 6), "dcs.info.title.gem_w2",
				"dcs.info.desc.gem_w2");
		MainAPIManager.infoRegister.registerInfo(gem10);

		if (ModuleConfig.tool) {
			DCInfoData scythe = new DCInfoData(null, null, new ItemStack(MainInit.dcScythe[0], 1, 0),
					"dcs.info.title.scythe", "dcs.info.desc.scythe");
			scythe.machine.add(new ItemStack(MainInit.dcScythe[1], 1, 0));
			scythe.machine.add(new ItemStack(MainInit.dcScythe[2], 1, 0));
			scythe.machine.add(new ItemStack(MainInit.dcScythe[3], 1, 0));
			scythe.machine.add(new ItemStack(MainInit.dcScythe[4], 1, 0));
			scythe.machine.add(new ItemStack(MainInit.dcScythe[5], 1, 0));
			MainAPIManager.infoRegister.registerInfo(scythe);

			DCInfoData spade_earth = new DCInfoData(null, null, new ItemStack(MainInit.earthSpade, 1, 0),
					"dcs.info.title.spade_earth", "dcs.info.desc.spade_earth");
			MainAPIManager.infoRegister.registerInfo(spade_earth);

			DCInfoData rake_earth = new DCInfoData(null, null, new ItemStack(MainInit.earthRake, 1, 0),
					"dcs.info.title.rake_earth", "dcs.info.desc.rake_earth");
			MainAPIManager.infoRegister.registerInfo(rake_earth);
		}

		DCInfoData chamber = new DCInfoData(null, null, new ItemStack(MainInit.chamber), "dcs.info.title.chamber",
				"dcs.info.desc.chamber");
		MainAPIManager.infoRegister.registerInfo(chamber);

		DCInfoData silk = new DCInfoData(new ItemStack(MainInit.silkworm, 1, 0), new ItemStack(MainInit.silkworm, 1, 2),
				new ItemStack(FoodInit.silkwormBox), "dcs.info.title.silk", "dcs.info.desc.silk");
		MainAPIManager.infoRegister.registerInfo(silk);

		DCInfoData silk2 = new DCInfoData(null, null, new ItemStack(MainInit.silkworm, 1, 0), "dcs.info.title.silkegg",
				"dcs.info.desc.silkegg");
		MainAPIManager.infoRegister.registerInfo(silk2);

		if (ModuleConfig.machine) {

			DCInfoData milk = new DCInfoData(null, null, new ItemStack(MachineInit.hopperFluid), "dcs.info.title.milk",
					"dcs.info.desc.milk").setAdditionalFluid(new FluidStack(MainInit.milk, 1000));
			MainAPIManager.infoRegister.registerInfo(milk);

			DCInfoData wrench = new DCInfoData(null, null, new ItemStack(MainInit.wrench), "dcs.info.title.wrench",
					"dcs.info.desc.wrench");
			MainAPIManager.infoRegister.registerInfo(wrench);

			DCInfoData shaft = new DCInfoData(null, null, new ItemStack(MachineInit.shaft_l), "dcs.info.title.shaft",
					"dcs.info.desc.shaft");
			shaft.machine.add(new ItemStack(MachineInit.shaft_s));
			shaft.machine.add(new ItemStack(MachineInit.shaft_t_a));
			shaft.machine.add(new ItemStack(MachineInit.shaft_t_b));
			shaft.machine.add(new ItemStack(MachineInit.shaft_x));
			shaft.machine.add(new ItemStack(MachineInit.shaft2_l));
			shaft.machine.add(new ItemStack(MachineInit.shaft2_s));
			shaft.machine.add(new ItemStack(MachineInit.shaft2_t_a));
			shaft.machine.add(new ItemStack(MachineInit.shaft2_t_b));
			shaft.machine.add(new ItemStack(MachineInit.shaft2_x));
			shaft.machine.add(new ItemStack(MachineInit.shaft3_l));
			shaft.machine.add(new ItemStack(MachineInit.shaft3_s));
			shaft.machine.add(new ItemStack(MachineInit.shaft3_t_a));
			shaft.machine.add(new ItemStack(MachineInit.shaft3_t_b));
			shaft.machine.add(new ItemStack(MachineInit.shaft3_x));
			MainAPIManager.infoRegister.registerInfo(shaft);

			DCInfoData conveyor = new DCInfoData(null, null, new ItemStack(MachineInit.conveyor),
					"dcs.info.title.conveyor", "dcs.info.desc.conveyor");
			MainAPIManager.infoRegister.registerInfo(conveyor);

			DCInfoData exc = new DCInfoData(null, null, new ItemStack(MachineInit.heatPump), "dcs.info.title.exchanger",
					"dcs.info.desc.exchanger");
			MainAPIManager.infoRegister.registerInfo(exc);

			DCInfoData scooter = new DCInfoData(null, null, new ItemStack(MachineInit.scooter),
					"dcs.info.title.scooter", "dcs.info.desc.scooter");
			MainAPIManager.infoRegister.registerInfo(scooter);

			DCInfoData hover = new DCInfoData(null, null, new ItemStack(MachineInit.magneticHover),
					"dcs.info.title.hover", "dcs.info.desc.hover");
			MainAPIManager.infoRegister.registerInfo(hover);

			DCInfoData coat = new DCInfoData(null, null, new ItemStack(MachineInit.platingChrome, 1, 32767),
					"dcs.info.title.coating", "dcs.info.desc.coating");
			MainAPIManager.infoRegister.registerInfo(coat);
		}

		if (ModuleConfig.magic) {
			DCInfoData cube1 = new DCInfoData(new ItemStack(MagicInit.colorCube, 1, 0), new ItemStack(
					MagicInit.colorCube, 1, 5), null, "dcs.info.title.cube_blue", "dcs.info.desc.cube_blue");
			MainAPIManager.infoRegister.registerInfo(cube1);

			DCInfoData cube2 = new DCInfoData(new ItemStack(MagicInit.colorCube, 1, 1), new ItemStack(
					MagicInit.colorCube, 1, 6), null, "dcs.info.title.cube_green", "dcs.info.desc.cube_green");
			MainAPIManager.infoRegister.registerInfo(cube2);

			DCInfoData cube3 = new DCInfoData(new ItemStack(MagicInit.colorCube, 1, 2), new ItemStack(
					MagicInit.colorCube, 1, 7), null, "dcs.info.title.cube_red", "dcs.info.desc.cube_red");
			MainAPIManager.infoRegister.registerInfo(cube3);

			DCInfoData cube4 = new DCInfoData(new ItemStack(MagicInit.colorCube, 1, 3), new ItemStack(
					MagicInit.colorCube, 1, 8), null, "dcs.info.title.cube_black", "dcs.info.desc.cube_black");
			MainAPIManager.infoRegister.registerInfo(cube4);

			DCInfoData cube5 = new DCInfoData(new ItemStack(MagicInit.colorCube, 1, 4), new ItemStack(
					MagicInit.colorCube, 1, 9), null, "dcs.info.title.cube_white", "dcs.info.desc.cube_white");
			MainAPIManager.infoRegister.registerInfo(cube5);
		}

		if (ModuleConfig.food) {
			DCInfoData viscera = new DCInfoData(null, null, new ItemStack(FoodInit.meat, 1, 0),
					"dcs.info.title.viscera", "dcs.info.desc.viscera");
			MainAPIManager.infoRegister.registerInfo(viscera);

			DCInfoData squid = new DCInfoData(null, null, new ItemStack(FoodInit.meat, 1, 2), "dcs.info.title.squid",
					"dcs.info.desc.squid");
			MainAPIManager.infoRegister.registerInfo(squid);

			DCInfoData cup = new DCInfoData(null, null, new ItemStack(FoodInit.cupSilver, 1, 0), "dcs.info.title.cup",
					"dcs.info.desc.cup");
			cup.machine.add(new ItemStack(FoodInit.cupSilver, 1, 1));
			cup.machine.add(new ItemStack(FoodInit.cupSilver, 1, 2));
			MainAPIManager.infoRegister.registerInfo(cup);

			DCInfoData food = new DCInfoData(null, null, new ItemStack(FoodInit.sticks, 1, 32767),
					"dcs.info.title.food", "dcs.info.desc.food");
			MainAPIManager.infoRegister.registerInfo(food);
		}

		if (ModuleConfig.build_advanced) {
			DCInfoData asphalt = new DCInfoData(null, null, new ItemStack(MainInit.builds, 1, 5),
					"dcs.info.title.asphalt", "dcs.info.desc.asphalt");
			MainAPIManager.infoRegister.registerInfo(asphalt);

			DCInfoData ladder = new DCInfoData(null, null, new ItemStack(MainInit.fenceLadder, 1, 0),
					"dcs.info.title.ladder", "dcs.info.desc.ladder");
			ladder.machine.add(new ItemStack(MainInit.fenceLadderSteel, 1, 0));
			MainAPIManager.infoRegister.registerInfo(ladder);

			DCInfoData clock = new DCInfoData(null, null, new ItemStack(MainInit.realtimeClock, 1, 0),
					"dcs.info.title.real_clock", "dcs.info.desc.real_clock");
			MainAPIManager.infoRegister.registerInfo(clock);

			DCInfoData curtain = new DCInfoData(null, null, new ItemStack(MainInit.curtainWhite, 1, 0),
					"dcs.info.title.curtain", "dcs.info.desc.curtain");
			MainAPIManager.infoRegister.registerInfo(curtain);

			DCInfoData pot = new DCInfoData(null, null, new ItemStack(MainInit.flowerPot, 1, 0),
					"dcs.info.title.flower_pot", "dcs.info.desc.flower_pot");
			pot.machine.add(new ItemStack(MainInit.flowerPot, 1, 1));
			MainAPIManager.infoRegister.registerInfo(pot);

			DCInfoData cushion = new DCInfoData(null, null, new ItemStack(MainInit.cushionGray, 1, 0),
					"dcs.info.title.cushion", "dcs.info.desc.cushion");
			cushion.machine.add(new ItemStack(MainInit.cushionGray, 1, 1));
			MainAPIManager.infoRegister.registerInfo(cushion);
		}
	}

}
