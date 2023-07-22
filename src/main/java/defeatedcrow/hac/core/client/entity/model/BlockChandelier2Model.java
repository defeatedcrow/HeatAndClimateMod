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
public class BlockChandelier2Model extends Model {

	private final ModelPart main;
	private final ModelPart light;
	private final ModelPart part;
	private final ModelPart part2;
	private final ModelPart part3;
	private final ModelPart part4;
	private final ModelPart part5;
	private final ModelPart part6;
	private final ModelPart light21;
	private final ModelPart light22;
	private final ModelPart light23;
	private final ModelPart light24;
	private final ModelPart light25;
	private final ModelPart light26;

	public BlockChandelier2Model(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.main = root.getChild("main");
		this.light = root.getChild("light");
		this.part = root.getChild("part");
		this.part2 = root.getChild("part2");
		this.part3 = root.getChild("part3");
		this.part4 = root.getChild("part4");
		this.part5 = root.getChild("part5");
		this.part6 = root.getChild("part6");
		this.light21 = root.getChild("light21");
		this.light22 = root.getChild("light22");
		this.light23 = root.getChild("light23");
		this.light24 = root.getChild("light24");
		this.light25 = root.getChild("light25");
		this.light26 = root.getChild("light26");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(22, 1).addBox(-2.0F, -10.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(59, 3).addBox(-1.0F, -14.0F, 0.0F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(59, 1).addBox(0.0F, -14.0F, -1.0F, 0.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(0, 0).addBox(-3.5F, -4.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
			.texOffs(0, 8).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
			.texOffs(0, 16).addBox(-2.5F, -1.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
			.texOffs(0, 22).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition light = partdefinition.addOrReplaceChild("light", CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition cube_r1 = light.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(39, 14).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F,
			0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition cube_r2 = light.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(39, 14).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F,
			0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = light.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(34, 14).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.5F,
			0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = light.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(34, 14).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F,
			0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition part = partdefinition.addOrReplaceChild("part", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(44, 25).addBox(-12.5F, -12.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(33, 24).addBox(-12.0F, -12.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(46, 15).addBox(-9.0F, -1.0F, 0.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		PartDefinition cube_r5 = part.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r6 = part.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part2 = partdefinition.addOrReplaceChild("part2", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(44, 25).addBox(-12.5F, -12.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(33, 24).addBox(-12.0F, -12.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(46, 15).addBox(-9.0F, -1.0F, 0.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

		PartDefinition cube_r7 = part2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r8 = part2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part3 = partdefinition.addOrReplaceChild("part3", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(44, 25).addBox(-12.5F, -12.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(33, 24).addBox(-12.0F, -12.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(46, 15).addBox(-9.0F, -1.0F, 0.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

		PartDefinition cube_r9 = part3.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r10 = part3.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part4 = partdefinition.addOrReplaceChild("part4", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(44, 25).addBox(-12.5F, -12.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(33, 24).addBox(-12.0F, -12.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(46, 15).addBox(-9.0F, -1.0F, 0.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r11 = part4.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r12 = part4.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part5 = partdefinition.addOrReplaceChild("part5", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(44, 25).addBox(-12.5F, -12.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(33, 24).addBox(-12.0F, -12.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(46, 15).addBox(-9.0F, -1.0F, 0.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

		PartDefinition cube_r13 = part5.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r14 = part5.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition part6 = partdefinition.addOrReplaceChild("part6", CubeListBuilder.create().texOffs(34, 7).addBox(-14.0F, -7.0F, 0.0F, 12.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
			.texOffs(30, 19).addBox(-13.0F, -7.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(43, 19).addBox(-12.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(44, 25).addBox(-12.5F, -12.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(33, 24).addBox(-12.0F, -12.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
			.texOffs(46, 15).addBox(-9.0F, -1.0F, 0.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

		PartDefinition cube_r15 = part6.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(-1, 30).addBox(-12.0F, 0.0F, -0.5F, 10.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			-10.0F, 0.0F, 0.0F, -0.4363F, -0.3491F));

		PartDefinition cube_r16 = part6.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(-1, 28).addBox(-6.0F, -6.5F, 10.0F, 12.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F,
			0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition light21 = partdefinition.addOrReplaceChild("light21", CubeListBuilder.create().texOffs(24, 25).addBox(-12.0F, -11.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F,
			0.0F, 0.0F));

		PartDefinition cube_r17 = light21.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(39, 14).addBox(-2.0F, 1.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r18 = light21.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(39, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition cube_r19 = light21.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(34, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-2.5F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition light22 = partdefinition.addOrReplaceChild("light22", CubeListBuilder.create().texOffs(24, 25).addBox(-12.0F, -11.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.0472F, 0.0F));

		PartDefinition cube_r20 = light22.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(39, 14).addBox(-2.0F, 1.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r21 = light22.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(39, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition cube_r22 = light22.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(34, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-2.5F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition light23 = partdefinition.addOrReplaceChild("light23", CubeListBuilder.create().texOffs(24, 25).addBox(-12.0F, -11.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.0944F, 0.0F));

		PartDefinition cube_r23 = light23.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(39, 14).addBox(-2.0F, 1.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r24 = light23.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(39, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition cube_r25 = light23.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(34, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-2.5F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition light24 = partdefinition.addOrReplaceChild("light24", CubeListBuilder.create().texOffs(24, 25).addBox(-12.0F, -11.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r26 = light24.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(39, 14).addBox(-2.0F, 1.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r27 = light24.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(39, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition cube_r28 = light24.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(34, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-2.5F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition light25 = partdefinition.addOrReplaceChild("light25", CubeListBuilder.create().texOffs(24, 25).addBox(-12.0F, -11.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

		PartDefinition cube_r29 = light25.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(39, 14).addBox(-2.0F, 1.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r30 = light25.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(39, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition cube_r31 = light25.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(34, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-2.5F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition light26 = partdefinition.addOrReplaceChild("light26", CubeListBuilder.create().texOffs(24, 25).addBox(-12.0F, -11.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose
			.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.0472F, 0.0F));

		PartDefinition cube_r32 = light26.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(39, 14).addBox(-2.0F, 1.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r33 = light26.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(39, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-1.0F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		PartDefinition cube_r34 = light26.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(34, 14).addBox(-0.5F, 0.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F,
			-2.5F, 0.0F, -0.7854F, -1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part2.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part3.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part4.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part5.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		part6.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void renderFlowers(PoseStack poseStack, VertexConsumer vertex, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		light.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		light21.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		light22.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		light23.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		light24.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		light25.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
		light26.render(poseStack, vertex, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
