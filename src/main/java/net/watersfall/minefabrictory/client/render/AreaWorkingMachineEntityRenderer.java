package net.watersfall.minefabrictory.client.render;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.watersfall.minefabrictory.block.entity.AreaWorkingMachineEntity;

public class AreaWorkingMachineEntityRenderer<T extends AreaWorkingMachineEntity> extends BlockEntityRenderer<T>
{
	public AreaWorkingMachineEntityRenderer(BlockEntityRenderDispatcher dispatcher)
	{
		super(dispatcher);
	}

	@Override
	public void render(AreaWorkingMachineEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
	{
		if(entity.getWorkingArea() != null && entity.shouldRenderWorkingArea())
		{
			matrices.push();
			WorldRenderer.drawBox(matrices, vertexConsumers.getBuffer(RenderLayer.getLines()), entity.getWorkingArea(), 1, 0, 0, 1);
			matrices.pop();
		}
	}
}
