package defeatedcrow.hac.core.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlockChandelierModel extends Model {

	private final ModelPart main;
	private final ModelPart flower;
	private final ModelPart flower2;
	private final ModelPart flower3;
	private final ModelPart flower4;
	private final ModelPart lod;
	private final ModelPart lod2;
	private final ModelPart lod3;
	private final ModelPart lod4;

	public BlockChandelierModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
		this.flower = root.getChild("flower");
		this.flower2 = root.getChild("flower2");
		this.flower3 = root.getChild("flower3");
		this.flower4 = root.getChild("flower4");
		this.lod = root.getChild("lod");
		this.lod2 = root.getChild("lod2");
		this.lod3 = root.getChild("lod3");
		this.lod4 = root.getChild("lod4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -16.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(0, 9).addBox(-2.0F, -14.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(18, 9).addBox(-0.5F, -13.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(16, 13).addBox(-1.0F, -12.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(18, 9).addBox(-0.5F, -10.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(0, 15).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition flower = partdefinition.addOrReplaceChild("flower", CubeListBuilder.create().texOffs(27, 1).addBox(-1.5F, 2.0F, -10.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(41, 1).addBox(-1.0F, 3.0F, -9.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(36, 9).addBox(-1.5F, 3.0F, -11.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(54, 9).addBox(-1.5F, 3.0F, -7.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(27, 7).addBox(1.5F, 3.0F, -10.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(45, 7).addBox(-2.5F, 3.0F, -10.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r1 = flower.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(45, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.8727F, 0.7854F, 0.0F));

		PartDefinition cube_r2 = flower.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(27, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.8727F, -0.7854F, 0.0F));

		PartDefinition cube_r3 = flower.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(45, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F,
			-8.5F, -0.8727F, -0.7854F, 0.0F));

		PartDefinition cube_r4 = flower.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(27, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F,
			-8.5F, -0.8727F, 0.7854F, 0.0F));

		PartDefinition cube_r5 = flower.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(36, 22).addBox(-1.5F, 4.0F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r6 = flower.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(27, 22).addBox(0.5F, 4.0F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F,
			-8.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r7 = flower.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(36, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r8 = flower.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(36, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F,
			-8.5F, -0.7854F, 0.0F, 0.0F));

		PartDefinition flower2 = partdefinition.addOrReplaceChild("flower2", CubeListBuilder.create().texOffs(27, 1).addBox(-1.5F, 2.0F, -10.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(41, 1).addBox(-1.0F, 3.0F, -9.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(36, 9).addBox(-1.5F, 3.0F, -11.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(54, 9).addBox(-1.5F, 3.0F, -7.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(27, 7).addBox(1.5F, 3.0F, -10.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(45, 7).addBox(-2.5F, 3.0F, -10.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -9.0F, 0.0F, -0.2618F, -1.5708F, 0.0F));

		PartDefinition cube_r9 = flower2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(45, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.8727F, 0.7854F, 0.0F));

		PartDefinition cube_r10 = flower2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(27, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.8727F, -0.7854F, 0.0F));

		PartDefinition cube_r11 = flower2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(45, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, -0.8727F, -0.7854F, 0.0F));

		PartDefinition cube_r12 = flower2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(27, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, -0.8727F, 0.7854F, 0.0F));

		PartDefinition cube_r13 = flower2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(36, 22).addBox(-1.5F, 4.0F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r14 = flower2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(27, 22).addBox(0.5F, 4.0F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r15 = flower2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(36, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r16 = flower2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(36, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, -0.7854F, 0.0F, 0.0F));

		PartDefinition flower3 = partdefinition.addOrReplaceChild("flower3", CubeListBuilder.create().texOffs(27, 1).addBox(-1.5F, 2.0F, -10.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(41, 1).addBox(-1.0F, 3.0F, -9.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(36, 9).addBox(-1.5F, 3.0F, -11.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(54, 9).addBox(-1.5F, 3.0F, -7.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(27, 7).addBox(1.5F, 3.0F, -10.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(45, 7).addBox(-2.5F, 3.0F, -10.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -1.0F, -0.2618F, 3.1416F, 0.0F));

		PartDefinition cube_r17 = flower3.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(45, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.8727F, 0.7854F, 0.0F));

		PartDefinition cube_r18 = flower3.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(27, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.8727F, -0.7854F, 0.0F));

		PartDefinition cube_r19 = flower3.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(45, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, -0.8727F, -0.7854F, 0.0F));

		PartDefinition cube_r20 = flower3.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(27, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, -0.8727F, 0.7854F, 0.0F));

		PartDefinition cube_r21 = flower3.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(36, 22).addBox(-1.5F, 4.0F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r22 = flower3.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(27, 22).addBox(0.5F, 4.0F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r23 = flower3.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(36, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r24 = flower3.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(36, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, -0.7854F, 0.0F, 0.0F));

		PartDefinition flower4 = partdefinition.addOrReplaceChild("flower4", CubeListBuilder.create().texOffs(27, 1).addBox(-1.5F, 2.0F, -10.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(41, 1).addBox(-1.0F, 3.0F, -9.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(36, 9).addBox(-1.5F, 3.0F, -11.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(54, 9).addBox(-1.5F, 3.0F, -7.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(27, 7).addBox(1.5F, 3.0F, -10.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(45, 7).addBox(-2.5F, 3.0F, -10.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -9.0F, 0.0F, -0.2618F, 1.5708F, 0.0F));

		PartDefinition cube_r25 = flower4.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(45, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.8727F, 0.7854F, 0.0F));

		PartDefinition cube_r26 = flower4.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(27, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.8727F, -0.7854F, 0.0F));

		PartDefinition cube_r27 = flower4.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(45, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, -0.8727F, -0.7854F, 0.0F));

		PartDefinition cube_r28 = flower4.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(27, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, -0.8727F, 0.7854F, 0.0F));

		PartDefinition cube_r29 = flower4.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(36, 22).addBox(-1.5F, 4.0F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r30 = flower4.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(27, 22).addBox(0.5F, 4.0F, -1.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r31 = flower4.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(36, 18).addBox(-1.5F, 4.0F, -1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r32 = flower4.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(36, 14).addBox(-1.5F, 4.0F, 0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			2.0F, -8.5F, -0.7854F, 0.0F, 0.0F));

		PartDefinition lod = partdefinition.addOrReplaceChild("lod", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -2.0F, -9.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-9.0F, 1.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition lod2 = partdefinition.addOrReplaceChild("lod2", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -2.0F, -9.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F,
			-9.0F, 0.0F, -0.2618F, -1.5708F, 0.0F));

		PartDefinition lod3 = partdefinition.addOrReplaceChild("lod3", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -2.0F, -9.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-9.0F, -1.0F, -0.2618F, 3.1416F, 0.0F));

		PartDefinition lod4 = partdefinition.addOrReplaceChild("lod4", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -2.0F, -9.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F,
			-9.0F, 0.0F, -0.2618F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		lod.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		lod2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		lod3.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		lod4.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderFlowers(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		flower.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		flower2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		flower3.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		flower4.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
