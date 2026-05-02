package defeatedcrow.hac.machine.material.block.transport;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class FluidPipeNickelsilverBlock extends FluidPipeAlloyBlock {

	public FluidPipeNickelsilverBlock(String s) {
		super(s);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new FluidPipeNickelsilverTile(pos, state);
	}

	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return level.isClientSide ? null : createTickerHelper(type, MachineInit.PIPE_NICKELSILVER_TILE.get(), FluidPipeNickelsilverTile::serverTick);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable BlockGetter level, List<Component> list, TooltipFlag flag) {
		MutableComponent tex1 = Component.translatable("dcs.tip.flow.tier2").withStyle(ChatFormatting.YELLOW);
		list.add(tex1);
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		MutableComponent tex2 = Component.translatable("dcs.tip.fluid_pipe");
		if (flag) {
			list.add(tex2);
		}
	}

}
