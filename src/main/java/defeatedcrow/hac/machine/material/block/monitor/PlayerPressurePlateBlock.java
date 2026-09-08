package defeatedcrow.hac.machine.material.block.monitor;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.core.json.IJsonDataDC;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.IBlockDC;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class PlayerPressurePlateBlock extends WeightedPressurePlateBlock implements IBlockDC, IJsonDataDC {

	final String name;

	public PlayerPressurePlateBlock(String n) {
		super(1, getProp(), net.minecraft.world.level.block.state.properties.BlockSetType.IRON);
		name = n;
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity instanceof Player) {
			super.entityInside(state, level, pos, entity);
		}
	}

	public static BlockBehaviour.Properties getProp() {
		return BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).sound(SoundType.STONE).strength(0.3F).randomTicks();
	}

	@Override
	public String getRegistryName() {
		return "machine/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList
		    .of(new JsonModelDC("minecraft:block/pressure_plate_up", ImmutableMap.of("texture", "dcs_climate:block/metal/gemblock_chalcedony")),
		        new JsonModelDC("minecraft:block/pressure_plate_down", ImmutableMap.of("texture", "dcs_climate:block/metal/gemblock_chalcedony")));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return ImmutableList.of("up", "down");
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_up");
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(this);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
		List<ItemStack> ret = Lists.newArrayList();
		return ret;
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.PICKAXE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.energy.indicator").withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.BOLD);
		list.add(tex1);
	}

}
