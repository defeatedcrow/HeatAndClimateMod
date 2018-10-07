package defeatedcrow.hac.food;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LeavesColorsDC {

	@SubscribeEvent
	public void onLoadBlockColors(ColorHandlerEvent.Block event) {
		event.getBlockColors().registerBlockColorHandler(new IBlockColor() {

			@Override
			public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
				if (tintIndex == 0) {
					if (state.getBlock() == FoodInit.leavesDates) {
						return ColorizerFoliage.getFoliageColorBirch();
					} else if (state.getBlock() == FoodInit.leavesWalnut) {
						return ColorizerFoliage.getFoliageColorPine();
					} else {
						if (worldIn != null && pos != null) {
							return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
						} else {
							return ColorizerFoliage.getFoliageColorBasic();
						}
					}
				} else {
					return -1;
				}
			}

		}, FoodInit.leavesLemon, FoodInit.leavesOlive, FoodInit.leavesMorus, FoodInit.leavesWalnut, FoodInit.leavesDates);
	}

	@SubscribeEvent
	public void onLoadItemColors(ColorHandlerEvent.Item event) {
		event.getItemColors().registerItemColorHandler(new IItemColor() {

			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				IBlockState iblockstate = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
				return event.getBlockColors().colorMultiplier(iblockstate, (IBlockAccess) null, (BlockPos) null, tintIndex);
			}

		}, FoodInit.leavesLemon, FoodInit.leavesOlive, FoodInit.leavesMorus, FoodInit.leavesWalnut, FoodInit.leavesDates);
	}

}
