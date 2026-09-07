package defeatedcrow.hac.magic.material.item.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.magic.IColorDC;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IEntityItem;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.material.item.ItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.entity.MagicPictureEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public abstract class MagicPictureItem extends ItemDC implements IEntityItem, IColorDC {

	private final String name;
	private final MagicColor color;

	public MagicPictureItem(String s, MagicColor c) {
		super(prop(), TagDC.ItemTag.MAGIC_PICTURE);
		defeatedcrow.hac.core.material.tabs.CreativeTabDC.add(MagicInit.MAGIC, this);
		name = s;
		color = c;
	}

	private static Properties prop() {
		return new Item.Properties().stacksTo(1).rarity(Rarity.EPIC);
	}

	@Override
	public MagicColor getColor() {
		return color;
	}

	@Override
	public MagicType getMagicType() {
		return MagicType.ENTITY;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Player player = context.getPlayer();
		Direction face = context.getClickedFace();
		Vec3 vec = context.getClickLocation();
		if (!face.getAxis().isVertical() && canSpawnHere(level, pos.relative(face))) {
			vec = Vec3.atBottomCenterOf(pos);
			Vec3 place = new Vec3(vec.x + face.getStepX() * 0.5125D, vec.y, vec.z + face.getStepZ() * 0.5125D);
			ItemStack item = context.getItemInHand();
			if (!level.isClientSide)
				spawnPlacementEntity(level, player, place, face.getOpposite(), item);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public boolean canSpawnHere(Level level, BlockPos pos) {
		AABB aabb = new AABB(pos);
		return level.getEntitiesOfClass(MagicPictureEntity.class, aabb).isEmpty();
	}

	@Override
	public boolean spawnPlacementEntity(Level level, Player player, Vec3 vec, Direction targetDir, ItemStack item) {
		if (getType() == null)
			return false;
		Entity entity = getType().create(level);
		if (entity instanceof MagicPictureEntity pic) {
			BlockPos pos = BlockPos.containing(vec.x, vec.y, vec.z);
			pic.setPos(vec);
			pic.setXRot(0F);
			pic.setYRot(targetDir.get2DDataValue() * 90F);
			pic.setDeltaMovement(0D, 0D, 0D);
			pic.setOwnerUUID(player.getUUID());
			if (level.addFreshEntity(pic)) {
				item.split(1);
			}
			level.gameEvent(pic, GameEvent.ENTITY_PLACE, vec);
			return true;
		}
		return false;
	}

	@Override
	public boolean spawnPlacementEntity(Level level, Player player, Vec3 vec, ItemStack item) {
		return spawnPlacementEntity(level, player, vec, Direction.NORTH, item);
	}

	@Override
	public String getRegistryName() {
		return "magic/picture_item_" + name;
	}

	@Override
	public JsonModelDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/magic/picture_item_" + name));
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent tier = Component.literal(color.isBasic ? color.name() + " " + item.getRarity() : color.name());
		tier.withStyle(color.chatColor);
		list.add(tier);
		MutableComponent itemName = Component.translatable("dcs.tip.picture.name." + name);
		itemName.withStyle(color.chatColor).withStyle(ChatFormatting.ITALIC);
		list.add(itemName);
	}

	@Override
	public void advTooltipText(ItemStack item, @Nullable BlockGetter level, List<Component> list, boolean flag) {
		if (flag) {
			MutableComponent itemTip = Component.translatable("dcs.tip.picture.desc." + name);
			list.add(itemTip);
			if (ConfigCommonBuilder.INSTANCE.enFlavorText.get()) {
				MutableComponent itemTip2 = Component.translatable("dcs.tip.picture.flavor." + getColor().toString());
				itemTip2.withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY);
				list.add(itemTip2);
			}
		}
	}

	public abstract EntityRenderData getOuterRenderData(Item item);
}
