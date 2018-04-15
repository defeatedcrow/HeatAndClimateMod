package defeatedcrow.hac.main.villager;

import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class HaCVillagerAgriResearchHouse extends StructureVillagePieces.Village {

	private Block cropA;
	private Block cropB;
	private Block cropC;

	public HaCVillagerAgriResearchHouse() {
		super();
	}

	public HaCVillagerAgriResearchHouse(StructureVillagePieces.Start start, int type, Random rand,
			StructureBoundingBox box, EnumFacing facing) {
		super(start, type);
		this.setCoordBaseMode(facing);
		this.boundingBox = box;
		this.cropA = this.getRandomCropType(rand);
		this.cropB = this.getRandomCropType(rand);
		this.cropC = this.getRandomCropType(rand);
	}

	private Block getRandomCropType(Random rand) {
		switch (rand.nextInt(10)) {
		case 0:
		case 1:
			return FoodInit.cropOnion;
		case 2:
		case 3:
			return FoodInit.cropSpinach;
		case 4:
			return FoodInit.cropTomato;
		case 5:
			return FoodInit.cropCotton;
		case 6:
			return FoodInit.cropCoffee;
		case 7:
			return FoodInit.cropHerb;
		default:
			return FoodInit.cropRice;
		}
	}

	@Override
	protected void writeStructureToNBT(NBTTagCompound tag) {
		super.writeStructureToNBT(tag);
		tag.setInteger("CA", Block.REGISTRY.getIDForObject(this.cropA));
		tag.setInteger("CB", Block.REGISTRY.getIDForObject(this.cropB));
		tag.setInteger("CC", Block.REGISTRY.getIDForObject(this.cropC));
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound tag, TemplateManager manager) {
		super.readStructureFromNBT(tag, manager);
		this.cropA = Block.getBlockById(tag.getInteger("CA"));
		this.cropB = Block.getBlockById(tag.getInteger("CB"));
		this.cropC = Block.getBlockById(tag.getInteger("CC"));

		if (!(this.cropA instanceof IClimateCrop)) {
			this.cropA = FoodInit.cropRice;
		}
		if (!(this.cropB instanceof IClimateCrop)) {
			this.cropB = FoodInit.cropOnion;
		}
		if (!(this.cropB instanceof IClimateCrop)) {
			this.cropB = FoodInit.cropSpinach;
		}
	}

	public static HaCVillagerAgriResearchHouse createPiece(StructureVillagePieces.Start start,
			List<StructureComponent> list, Random rand, int minX, int minY, int minZ, EnumFacing facing, int type) {
		StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(minX, minY, minZ, 0, 0, 0, 11, 7,
				11, facing);
		return StructureComponent.findIntersecting(list, box) != null ? null : new HaCVillagerAgriResearchHouse(start,
				type, rand, box, facing);
	}

	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox box) {
		if (this.averageGroundLvl < 0) {
			this.averageGroundLvl = this.getAverageGroundLevel(world, box);

			if (this.averageGroundLvl < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
		}

		IBlockState cobble = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());
		IBlockState stair1 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(
				BlockStairs.FACING, EnumFacing.NORTH));
		IBlockState stair2 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(
				BlockStairs.FACING, EnumFacing.SOUTH));
		IBlockState stair3 = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState().withProperty(
				BlockStairs.FACING, EnumFacing.WEST));
		IBlockState planks = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());
		IBlockState log = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState());
		IBlockState fence = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());
		this.fillWithBlocks(world, box, 0, 0, 0, 11, 7, 11, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(),
				false);
		this.fillWithBlocks(world, box, 0, -1, 0, 11, -1, 11, Blocks.DIRT.getDefaultState(),
				Blocks.DIRT.getDefaultState(), false);
		this.fillWithBlocks(world, box, 2, 0, 6, 8, 0, 10, Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(),
				false);
		this.fillWithBlocks(world, box, 3, 0, 7, 7, 0, 9, Blocks.FARMLAND.getDefaultState(),
				Blocks.FARMLAND.getDefaultState(), false);
		this.setBlockState(world, Blocks.WATER.getDefaultState(), 7, 0, 6, box);
		this.fillWithBlocks(world, box, 2, 1, 6, 2, 1, 10, fence, fence, false);
		this.fillWithBlocks(world, box, 8, 1, 6, 8, 1, 10, fence, fence, false);
		this.fillWithBlocks(world, box, 3, 1, 10, 7, 1, 10, fence, fence, false);

		IBlockState crop1 = cropA.getDefaultState();
		IBlockState crop2 = cropB.getDefaultState();
		IBlockState crop3 = cropC.getDefaultState();

		this.fillWithBlocks(world, box, 3, 1, 9, 7, 1, 9, crop1, crop1, false);
		this.fillWithBlocks(world, box, 3, 1, 8, 7, 1, 8, crop2, crop2, false);
		this.fillWithBlocks(world, box, 3, 1, 7, 7, 1, 7, crop3, crop3, false);

		this.fillWithBlocks(world, box, 1, 0, 0, 10, 0, 5, cobble, cobble, false);

		this.fillWithBlocks(world, box, 1, 3, 0, 10, 3, 0, log, log, false);
		this.fillWithBlocks(world, box, 1, 3, 5, 10, 3, 5, log, log, false);
		this.fillWithBlocks(world, box, 1, 3, 1, 1, 3, 4, log, log, false);
		this.fillWithBlocks(world, box, 10, 3, 1, 10, 3, 4, log, log, false);

		this.fillWithBlocks(world, box, 1, 1, 0, 1, 2, 0, log, log, false);
		this.fillWithBlocks(world, box, 10, 1, 0, 10, 2, 0, log, log, false);
		this.fillWithBlocks(world, box, 1, 1, 5, 1, 2, 5, log, log, false);
		this.fillWithBlocks(world, box, 10, 1, 5, 10, 2, 5, log, log, false);

		this.fillWithBlocks(world, box, 2, 1, 0, 9, 2, 0, planks, planks, false);
		this.fillWithBlocks(world, box, 2, 1, 5, 9, 2, 5, planks, planks, false);
		this.fillWithBlocks(world, box, 1, 1, 1, 1, 2, 4, planks, planks, false);
		this.fillWithBlocks(world, box, 10, 1, 1, 10, 2, 4, planks, planks, false);
		this.fillWithBlocks(world, box, 2, 0, 1, 9, 0, 4, planks, planks, false);

		this.fillWithBlocks(world, box, 1, 4, 2, 1, 5, 3, planks, planks, false);
		this.fillWithBlocks(world, box, 10, 4, 2, 10, 5, 3, planks, planks, false);
		this.fillWithBlocks(world, box, 1, 4, 1, 1, 4, 4, planks, planks, false);
		this.fillWithBlocks(world, box, 10, 4, 1, 10, 4, 4, planks, planks, false);

		for (int i = 1; i < 11; i++) {
			this.setBlockState(world, stair1, i, 3, -1, box);
			this.setBlockState(world, stair1, i, 4, 0, box);
			this.setBlockState(world, stair1, i, 5, 1, box);
			this.setBlockState(world, stair1, i, 6, 2, box);

			this.setBlockState(world, stair2, i, 6, 3, box);
			this.setBlockState(world, stair2, i, 5, 4, box);
			this.setBlockState(world, stair2, i, 4, 5, box);
			this.setBlockState(world, stair2, i, 3, 6, box);
		}

		this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 1, 2, 2, box);
		this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 1, 2, 3, box);
		this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 10, 2, 2, box);
		this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 10, 2, 3, box);
		this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 6, 2, 5, box);
		this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 7, 2, 5, box);
		this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 3, 2, 0, box);
		this.setBlockState(world, Blocks.GLASS_PANE.getDefaultState(), 4, 2, 0, box);

		this.setBlockState(world, Blocks.AIR.getDefaultState(), 3, 1, 5, box);
		this.setBlockState(world, Blocks.AIR.getDefaultState(), 3, 2, 5, box);
		this.createVillageDoor(world, box, rand, 3, 1, 5, EnumFacing.SOUTH);
		this.placeTorch(world, EnumFacing.SOUTH, 3, 3, 4, box);

		this.setBlockState(world, Blocks.AIR.getDefaultState(), 6, 1, 0, box);
		this.setBlockState(world, Blocks.AIR.getDefaultState(), 6, 2, 0, box);
		this.createVillageDoor(world, box, rand, 6, 1, 0, EnumFacing.NORTH);
		this.setBlockState(world, stair1, 6, 0, -1, box);
		this.placeTorch(world, EnumFacing.NORTH, 6, 3, 1, box);

		for (int k = 0; k < 11; ++k) {
			for (int l = 0; l < 11; ++l) {
				this.clearCurrentPositionBlocksUpwards(world, l, 7, k, box);
				this.replaceAirAndLiquidDownwards(world, cobble, l, -2, k, box);
			}
		}

		this.setBlockState(world, Blocks.BOOKSHELF.getDefaultState(), 2, 1, 1, box);
		this.setBlockState(world, Blocks.BOOKSHELF.getDefaultState(), 3, 1, 1, box);
		this.setBlockState(world, Blocks.BOOKSHELF.getDefaultState(), 4, 1, 1, box);
		this.setBlockState(world, MainInit.chalLamp.getDefaultState().withProperty(DCState.TYPE16, 11), 3, 2, 1, box);

		this.setBlockState(world, MainInit.chestVillage.getDefaultState().withProperty(DCState.FACING, EnumFacing.WEST),
				9, 1, 1, box);
		this.setBlockState(world, MainInit.chairWood.getDefaultState().withProperty(DCState.FACING, EnumFacing.WEST), 9,
				1, 3, box);
		this.setBlockState(world, MainInit.tableWood.getDefaultState(), 8, 1, 3, box);

		this.spawnVillagers(world, box, 4, 1, 2, 2);
		return true;
	}

	@Override
	protected int chooseProfession(int villagersSpawned, int currentVillagerProfession) {
		ForgeRegistry registry = (ForgeRegistry) ForgeRegistries.VILLAGER_PROFESSIONS;
		int id = registry.getID(MainInit.agri);
		int id2 = registry.getID(MainInit.engineer);
		return id < 0 ? currentVillagerProfession : id;
	}

}
