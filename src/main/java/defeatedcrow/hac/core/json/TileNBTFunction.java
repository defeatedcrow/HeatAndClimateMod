package defeatedcrow.hac.core.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import defeatedcrow.hac.core.material.tile.ITileNBTHolder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class TileNBTFunction extends LootItemConditionalFunction {

	private static LootItemFunctionType instance;

	public static void init() {

		if (instance != null) {
			return;
		}
		instance = Registry.register(Registry.LOOT_FUNCTION_TYPE, new ResourceLocation(
				"dcs_climate:nbt_tile"), new LootItemFunctionType(new Serializer()));
	}

	protected TileNBTFunction(LootItemCondition[] conditions) {
		super(conditions);
	}

	@Override
	public LootItemFunctionType getType() {
		return instance;
	}

	@Override
	protected ItemStack run(ItemStack item, LootContext context) {
		BlockEntity tile = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
		if (tile instanceof ITileNBTHolder) {
			ITileNBTHolder holder = (ITileNBTHolder) tile;
			return holder.getDropItem(item);
		}

		return item;
	}

	public static class Serializer extends LootItemConditionalFunction.Serializer<TileNBTFunction> {

		@Override
		public TileNBTFunction deserialize(JsonObject object, JsonDeserializationContext context,
				LootItemCondition[] conditions) {
			return new TileNBTFunction(conditions);
		}

	}

}
