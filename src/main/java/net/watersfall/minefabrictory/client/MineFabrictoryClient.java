package net.watersfall.minefabrictory.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.watersfall.minefabrictory.block.entity.MineFabrictoryBlockEntities;
import net.watersfall.minefabrictory.client.gui.SheepShearMachineGui;
import net.watersfall.minefabrictory.client.render.AreaWorkingMachineEntityRenderer;
import net.watersfall.minefabrictory.screen.MineFabrictoryScreenHandlers;

@Environment(EnvType.CLIENT)
public class MineFabrictoryClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		ScreenRegistry.register(MineFabrictoryScreenHandlers.SHEEP_SHEAR_MACHINE_SCREEN_HANDLER, SheepShearMachineGui::new);
		BlockEntityRendererRegistry.INSTANCE.register(MineFabrictoryBlockEntities.SHEEP_SHEAR_ENTITY, AreaWorkingMachineEntityRenderer::new);
	}
}
