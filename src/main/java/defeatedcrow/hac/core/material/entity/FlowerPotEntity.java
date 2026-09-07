package defeatedcrow.hac.core.material.entity;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.crops.ClimateCropBaseBlock;
import defeatedcrow.hac.food.material.block.crops.LeavesCropBlockDC;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;

public class FlowerPotEntity extends ObjectEntityBaseDC {

	private static final EntityDataAccessor<ItemStack> FLOWER = SynchedEntityData.defineId(ObjectEntityBaseDC.class, EntityDataSerializers.ITEM_STACK);
	private static final List<Flower> FLOWER_MAP = Lists.newArrayList();

	public FlowerPotEntity(EntityType<? extends ObjectEntityBaseDC> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData()
		    .define(FLOWER, ItemStack.EMPTY);
	}

	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (!this.isAlive()) {
			return super.interact(player, hand);
		} else if (player != null) {
			ItemStack held = player.getItemInHand(hand);
			if (!DCUtil.isEmpty(held) && isFlowerOrLeaves(held)) {
				if (!getFlowerItem().isEmpty()) {
					Vec3 vec3 = position().add(0D, this.getBbHeight() + 0.2D, 0D);
					this.dropFlowerItem(vec3);
				}
				this.setFlowerItem(held.split(1));
				return InteractionResult.SUCCESS;
			} else if (!getFlowerItem().isEmpty()) {
				this.dropFlowerItem(player.position());
				this.setFlowerItem(ItemStack.EMPTY);
				return InteractionResult.SUCCESS;
			} else {
				return super.interact(player, hand);
			}
		} else {
			return super.interact(player, hand);
		}
	}

	private boolean isFlowerOrLeaves(ItemStack item) {
		if (item.isEmpty())
			return false;
		if (item.is(ItemTags.LEAVES) || item.is(ItemTags.FLOWERS)) {
			return true;
		}
		if (item.getItem() instanceof BlockItem blockitem) {
			Block block = blockitem.getBlock();
			return block instanceof BushBlock;
		}
		return false;
	}

	@Override
	public void dropItem(Vec3 pos) {
		super.dropItem(pos);
		dropFlowerItem(pos);
	}

	public void dropFlowerItem(Vec3 pos) {
		if (!level().isClientSide && !getFlowerItem().isEmpty()) {
			ItemEntity drop = new ItemEntity(level(), pos.x, pos.y + 0.1D, pos.z, getFlowerItem().copy());
			level().addFreshEntity(drop);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		if (!getFlowerItem().isEmpty()) {
			tag.put("dcs.flower_item", getFlowerItem().save(new CompoundTag()));
		}

	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		CompoundTag flowertag = tag.getCompound("dcs.flower_item");
		setFlowerItem(ItemStack.of(flowertag));
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}

	public ItemStack getFlowerItem() {
		return this.getEntityData()
		    .get(FLOWER);
	}

	public void setFlowerItem(ItemStack item) {
		this.getEntityData()
		    .set(FLOWER, item);
	}

	public static BlockState getRenderState(ItemStack item) {
		if (!item.isEmpty()) {
			for (Flower flower : FLOWER_MAP) {
				if (item.is(flower.item.get())) {
					Block block = flower.block.get();
					BlockState state = block.defaultBlockState();
					if (state.hasProperty(DCState.STAGE6)) {
						if (state.hasProperty(DCState.DOUBLE)) {
							return state.setValue(DCState.STAGE6, flower.stage)
							    .setValue(DCState.DOUBLE, true);
						} else {
							return state.setValue(DCState.STAGE6, flower.stage);
						}
					} else {
						return state;
					}
				}
			}
			if (item.getItem() instanceof BlockItem blockitem) {
				Block block = blockitem.getBlock();
				BlockState state = block.defaultBlockState();
				if (block instanceof LeavesCropBlockDC) {
					int stage = DCTimeHelper.staticSeason.getSeasonLimitedID();
					return state.setValue(DCState.STAGE6, stage);
				} else if (block instanceof ClimateCropBaseBlock && state.hasProperty(DCState.STAGE6)) {
					if (state.hasProperty(DCState.DOUBLE)) {
						return state.setValue(DCState.STAGE6, 3)
						    .setValue(DCState.DOUBLE, true);
					} else {
						return state.setValue(DCState.STAGE6, 3);
					}
				}
				if (block instanceof DoublePlantBlock) {
					return state.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER);
				}
				return block == null ? Blocks.POPPY.defaultBlockState() : state;
			}
		}
		return Blocks.POPPY.defaultBlockState();
	}

	public static void clear() {
		FLOWER_MAP.clear();
	}

	public static void init() {
		FLOWER_MAP.add(new Flower(FoodInit.CROP_AMR_SNOWDROP, FoodInit.BLOCK_AMR_SNOWDROP, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_AMR_AMARYLLIS, FoodInit.BLOCK_AMR_AMARYLLIS, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_AMR_DAFFODIL, FoodInit.BLOCK_AMR_DAFFODIL, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_AMR_LYCORIS, FoodInit.BLOCK_AMR_LYCORIS, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_AS_PYRETHRUM, FoodInit.BLOCK_AS_PYRETHRUM, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_AS_FLOWER, FoodInit.BLOCK_AS_FLOWER, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_BR_RAPESEED, FoodInit.BLOCK_BR_RAPESEED, 3));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_HB_LAVENDER, FoodInit.BLOCK_HB_LAVENDER, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_IR_CROCUS, FoodInit.BLOCK_IR_CROCUS, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_IR_SAFFRON, FoodInit.BLOCK_IR_SAFFRON, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_IR_IRIS, FoodInit.BLOCK_IR_IRIS, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_LI_AMANA, FoodInit.BLOCK_LI_AMANA, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_LI_FAWN, FoodInit.BLOCK_LI_FAWN, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_LI_GOLDBAND, FoodInit.BLOCK_LI_GOLDBAND, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_ML_BLUE, FoodInit.BLOCK_ML_BLUE, 3));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_ML_TROPICAL, FoodInit.BLOCK_ML_TROPICAL, 3));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_MO_BINDWEED, FoodInit.BLOCK_MO_BINDWEED, 3));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_MO_FLOWER, FoodInit.BLOCK_MO_FLOWER, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_OR_SPIRANTHES, FoodInit.BLOCK_OR_SPIRANTHES, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_OR_CYMBIDIUM, FoodInit.BLOCK_OR_CYMBIDIUM, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_OR_CATTLEYA, FoodInit.BLOCK_OR_CATTLEYA, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_RA_ANEMONE, FoodInit.BLOCK_RA_ANEMONE, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_RA_DELPHINIUM, FoodInit.BLOCK_RA_DELPHINIUM, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_RA_CLEMATIS, FoodInit.BLOCK_RA_CLEMATIS, 3));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_RA_MONKSHOOD, FoodInit.BLOCK_RA_MONKSHOOD, 4));
		FLOWER_MAP.add(new Flower(FoodInit.FLOWER_KONJAC, FoodInit.BLOCK_AR_KONJAC, 5));
		FLOWER_MAP.add(new Flower(FoodInit.FLOWER_CHERRY, FoodInit.LEAVES_CH_WILD, 4));
		FLOWER_MAP.add(new Flower(FoodInit.FLOWER_PLUM, FoodInit.LEAVES_CH_PLUM, 4));
		FLOWER_MAP.add(new Flower(FoodInit.FLOWER_CAMELLIA, FoodInit.LEAVES_CM_OIL, 4));
		FLOWER_MAP.add(new Flower(FoodInit.FLOWER_SCHIMA, FoodInit.LEAVES_CM_SCHIMA, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_ER_HEATH, FoodInit.LEAVES_ER_HEATH, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_ER_RHODODENDRON, FoodInit.LEAVES_ER_RHODODENDRON, 4));
		FLOWER_MAP.add(new Flower(FoodInit.FOOD_PALM_FLOWER, FoodInit.CROPBLOCK_PL_COCONUT, 1));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_OL_OSMANTHUS, FoodInit.LEAVES_OL_OSMANTHUS, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_OL_JASMINE, FoodInit.LEAVES_OL_JASMINE, 4));
		FLOWER_MAP.add(new Flower(FoodInit.FLOWER_GARDENIA, FoodInit.LEAVES_RU_GARDENIA, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_RO_DAMASCHENA, FoodInit.LEAVES_RO_DAMASCHENA, 4));
		FLOWER_MAP.add(new Flower(FoodInit.CROP_RU_IXORA, FoodInit.LEAVES_RU_IXORA, 4));
	}

	public record Flower(Supplier<Item> item, Supplier<Block> block, int stage) {}

}
