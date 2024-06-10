package defeatedcrow.hac.core.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.material.IRapidCollectables;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.Tags;

public class AlloyHeatTreatmentBlock extends ClimateBlock implements IRapidCollectables {

	final String name;
	private String domain = "main";
	private final Phase phase;

	public AlloyHeatTreatmentBlock(String s, int p, boolean lit) {
		super(getProp(lit), true);
		name = s;
		if (p == 1) {
			phase = Phase.HEATING;
		} else if (p == 2) {
			phase = Phase.COOLING;
		} else {
			phase = Phase.FAIL;
		}
	}

	@Override
	public boolean onClimateChange(Level level, BlockPos pos, BlockState state, IClimate clm) {
		if (clm != null) {
			ItemStack check = new ItemStack(state.getBlock(), 1);
			boolean flag = DCRecipes.INSTANCE.hasAnyHeatTreatmentRecipe(state.getBlock()).filter(recipe -> !recipe.getCurrentOutput(check, clm).isEmpty()).map((recipe) -> {
				ItemStack output = recipe.getCurrentOutput(check, clm);
				Block ret = ((BlockItem) output.getItem()).getBlock();
				BlockState retS = ret.defaultBlockState();
				if (level.setBlock(pos, retS, 2)) {
					level.updateNeighborsAt(pos, this);

					// 効果音
					if (playSEOnChanging()) {
						level.playSound(null, pos, getSE(), SoundSource.BLOCKS, 0.8F, 1.5F);
						DCLogger.debugLog("Smelting! " + output.getDisplayName().getString());
					}

					if (this.isForcedTickUpdate()) {
						level.scheduleTick(pos, retS.getBlock(), recipe.getHeatingTime());
					}
				}
				return true;
			}).orElse(false);
			if (flag)
				return true;
		}
		return super.onClimateChange(level, pos, state, clm);
	}

	public AlloyHeatTreatmentBlock setDomain(String s) {
		domain = s;
		return this;
	}

	public static BlockBehaviour.Properties getProp(boolean lit) {
		if (lit)
			return BlockBehaviour.Properties.of(Material.STONE, MaterialColor.METAL).randomTicks().strength(3.0F, 6.0F).lightLevel((state) -> {
				return 10;
			});
		else
			return BlockBehaviour.Properties.of(Material.STONE, MaterialColor.METAL).randomTicks().strength(3.0F, 6.0F);
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
		return Lists.newArrayList();
	}

	@Override
	public String getRegistryName() {
		return domain + "/" + name;
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(new JsonModelDC("minecraft:block/cube_all", ImmutableMap.of("all", "dcs_climate:block/metal/" + name)));
	}

	@Override
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelSimpleDC("dcs_climate:block/" + getRegistryName() + "_0");
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

	/* IRapidCollectables */

	@Override
	public TagKey<Item> collectableToolTag() {
		return Tags.Items.TOOLS_SHOVELS;
	}

	@Override
	public List<ItemStack> getDropList(Level level, BlockPos pos, BlockState state, ItemStack tool) {
		return ImmutableList.of(new ItemStack(this));
	}

	@Override
	public boolean doCollect(Level level, BlockPos pos, BlockState state, @Nullable Player player, ItemStack tool) {
		ItemEntity drop;
		ItemStack ret = new ItemStack(this);
		if (player != null) {
			drop = new ItemEntity(level, player.getX(), player.getY() + 0.15D, player.getZ(), ret);
		} else {
			drop = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.15D, pos.getZ() + 0.5D, ret);
		}
		if (drop != null && !level.isClientSide) {
			level.addFreshEntity(drop);
			level.removeBlock(pos, false);
		}
		return true;
	}

	public enum Phase {
		HEATING,
		COOLING,
		FAIL;
	}

}
