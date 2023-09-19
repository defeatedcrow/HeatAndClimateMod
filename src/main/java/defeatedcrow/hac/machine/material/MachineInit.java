package defeatedcrow.hac.machine.material;

import java.util.function.Supplier;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.machine.client.gui.HeatingChamberMenu;
import defeatedcrow.hac.machine.client.gui.PortableTankMenu;
import defeatedcrow.hac.machine.material.block.BrickChamberBlock;
import defeatedcrow.hac.machine.material.block.BrickChamberTile;
import defeatedcrow.hac.machine.material.block.HeatingChamberBlock;
import defeatedcrow.hac.machine.material.block.HeatingChamberTile;
import defeatedcrow.hac.machine.material.block.IBCBlock;
import defeatedcrow.hac.machine.material.block.IBCTile;
import defeatedcrow.hac.machine.material.block.PortableCanBlock;
import defeatedcrow.hac.machine.material.block.PortableCanTile;
import defeatedcrow.hac.machine.material.block.PortableFluidTankTile;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.RegistryObject;

public class MachineInit {

	public static void init() {}

	public static final RegistryObject<Block> CHAMBER_BRICK_A = regBlock("chamber_brick_a", () -> new BrickChamberBlock("chamber_brick_a"), null);
	public static final RegistryObject<Block> CHAMBER_BRICK_B = regBlock("chamber_brick_b", () -> new BrickChamberBlock("chamber_brick_b"), null);
	public static final RegistryObject<Block> CHAMBER_IRON = regBlock("chamber_iron", () -> new HeatingChamberBlock("chamber_iron"), null);

	public static final RegistryObject<Block> PORTABLE_CAN = regBlock("portable_can", () -> new PortableCanBlock("portable_can"), null);
	public static final RegistryObject<Block> PORTABLE_CAN_WHITE = regBlock("portable_can_white", () -> new PortableCanBlock("portable_can_white"), null);
	public static final RegistryObject<Block> PORTABLE_CAN_BLUE = regBlock("portable_can_blue", () -> new PortableCanBlock("portable_can_blue"), null);
	public static final RegistryObject<Block> PORTABLE_CAN_BLACK = regBlock("portable_can_black", () -> new PortableCanBlock("portable_can_black"), null);
	public static final RegistryObject<Block> PORTABLE_CAN_RED = regBlock("portable_can_red", () -> new PortableCanBlock("portable_can_red"), null);
	public static final RegistryObject<Block> PORTABLE_CAN_GREEN = regBlock("portable_can_green", () -> new PortableCanBlock("portable_can_green"), null);

	public static final RegistryObject<Block> IBC = regBlock("ibc", () -> new IBCBlock("ibc"), null);

	// TileEntity
	public static final RegistryObject<BlockEntityType<BrickChamberTile>> CHAMBER_BRICK_TILE = CoreInit.BLOCK_ENTITIES.register("chamber_brick_tile",
		() -> BlockEntityType.Builder.of(BrickChamberTile::new, new Block[] { CHAMBER_BRICK_A.get(), CHAMBER_BRICK_B.get() }).build(null));

	public static final RegistryObject<BlockEntityType<HeatingChamberTile>> CHAMBER_IRON_TILE = CoreInit.BLOCK_ENTITIES.register("chamber_iron_tile",
		() -> BlockEntityType.Builder.of(HeatingChamberTile::new, new Block[] { CHAMBER_IRON.get() }).build(null));

	public static final RegistryObject<BlockEntityType<PortableCanTile>> PORTABLE_CAN_TILE = CoreInit.BLOCK_ENTITIES.register("portable_can_tile",
		() -> BlockEntityType.Builder.of(PortableCanTile::new, new Block[] { PORTABLE_CAN.get(), PORTABLE_CAN_WHITE.get(), PORTABLE_CAN_BLUE.get(), PORTABLE_CAN_BLACK.get(), PORTABLE_CAN_RED.get(), PORTABLE_CAN_GREEN
			.get() }).build(null));

	public static final RegistryObject<BlockEntityType<IBCTile>> IBC_TILE = CoreInit.BLOCK_ENTITIES.register("ibc_tile",
		() -> BlockEntityType.Builder.of(IBCTile::new, new Block[] { IBC.get() }).build(null));

	// Menu
	public static final RegistryObject<MenuType<HeatingChamberMenu>> CHAMBER_MENU = CoreInit.register("dcs_chamber_item", (IContainerFactory<HeatingChamberMenu>) (id, playerInv, data) -> {
		HeatingChamberTile cont = (HeatingChamberTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return HeatingChamberMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<PortableTankMenu>> FLUID_MENU = CoreInit.register("dcs_fluid_tank", (IContainerFactory<PortableTankMenu>) (id, playerInv, data) -> {
		PortableFluidTankTile cont = (PortableFluidTankTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return PortableTankMenu.getMenu(id, playerInv, cont);
	});

	public static final RegistryObject<MenuType<PortableTankMenu>> FLUID_MENU_LARGE = CoreInit.register("dcs_fluid_tank_large", (IContainerFactory<PortableTankMenu>) (id, playerInv, data) -> {
		PortableFluidTankTile cont = (PortableFluidTankTile) playerInv.player.level.getBlockEntity(data.readBlockPos());
		return PortableTankMenu.getLargeMenu(id, playerInv, cont);
	});

	public static RegistryObject<Block> regBlock(String name, Supplier<Block> block, TagKey<Item> tag) {
		RegistryObject<Block> obj = CoreInit.BLOCKS.register("machine/" + name, block);
		regItem(name, () -> new BlockItemDC(name, obj.get(), new Item.Properties().tab(CoreInit.MACHINE), tag));
		return obj;
	}

	public static RegistryObject<Item> regItem(String name, Supplier<Item> item) {
		return CoreInit.ITEMS.register("machine/" + name, item);
	}

}
