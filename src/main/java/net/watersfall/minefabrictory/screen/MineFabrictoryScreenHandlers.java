package net.watersfall.minefabrictory.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.watersfall.minefabrictory.MineFabrictory;

public class MineFabrictoryScreenHandlers
{
	public static final ScreenHandlerType<SheepShearMachineScreen> SHEEP_SHEAR_MACHINE_SCREEN_HANDLER;

	static
	{
		SHEEP_SHEAR_MACHINE_SCREEN_HANDLER = ScreenHandlerRegistry.registerExtended(MineFabrictory.getId("sheep_shear_machine_handler"), SheepShearMachineScreen::new);
	}
}
