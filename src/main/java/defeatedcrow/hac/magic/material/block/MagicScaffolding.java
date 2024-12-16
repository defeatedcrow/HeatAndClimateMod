package defeatedcrow.hac.magic.material.block;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.material.block.BlockDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MagicScaffolding extends BlockDC implements SimpleWaterloggedBlock {

	protected static final VoxelShape N_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public MagicScaffolding() {
		super(BlockBehaviour.Properties.of(Material.BAMBOO, MaterialColor.COLOR_GREEN).strength(0.1F, 1.0F).instabreak().noOcclusion().noLootTable());
		this.registerDefaultState(this.stateDefinition.any());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cont) {
		return N_AABB;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		if (hand != InteractionHand.MAIN_HAND || player.isCrouching()) {
			return InteractionResult.PASS;
		}

		ItemStack held = player.getItemInHand(hand);
		if (!DCUtil.isEmpty(held) && held.getItem() instanceof BlockItem blockItem) {
			if (!level.isClientSide()) {
				Block block = blockItem.getBlock();
				int limit = Math.min(held.getCount() - 1, ConfigCommonBuilder.INSTANCE.vTimberLimit.get());
				BlockPlaceContext cont = new BlockPlaceContext(level, player, hand, held, hitRes);
				BlockState place = block.getStateForPlacement(cont);
				Set<BlockPos> set = DCUtil.getConnectedTargetList(level, pos, this, limit);
				set.add(pos);
				int count = 0;
				for (BlockPos p1 : set) {
					level.setBlock(p1, place, 2);
					count++;
				}
				held.shrink(count);
				player.getInventory().setChanged();
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		return InteractionResult.PASS;
	}

	@Override
	public ItemStack getMainDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getSilkyDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return "magic/magic_scaffolding";
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return Lists.newArrayList();
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/magic/magic_scaffolding_0");
	}

	@Override
	public BlockType getDropType() {
		return BlockType.NORMAL;
	}

	@Override
	public ToolType getToolType() {
		return ToolType.NONE;
	}

	@Override
	public int getToolTier() {
		return 0;
	}

}
