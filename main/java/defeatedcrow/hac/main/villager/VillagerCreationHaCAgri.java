package defeatedcrow.hac.main.villager;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class VillagerCreationHaCAgri implements IVillageCreationHandler {
	public static void registerVillageComponents() {
		MapGenStructureIO.registerStructureComponent(HaCVillagerAgriResearchHouse.class,
				"HeatAndClimate:AgriResearchHouse");
	}

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int size) {
		return new PieceWeight(HaCVillagerAgriResearchHouse.class, 15, MathHelper.getInt(random, size, 1 + size));
	}

	@Override
	public Class<?> getComponentClass() {
		return HaCVillagerAgriResearchHouse.class;
	}

	@Override
	@Nullable
	public Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces,
			Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
		return HaCVillagerAgriResearchHouse.createPiece(startPiece, pieces, random, p1, p2, p3, facing, p5);
	}
}
