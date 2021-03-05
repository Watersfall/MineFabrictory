package net.watersfall.minefabrictory.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.watersfall.minefabrictory.MineFabrictory;
import net.watersfall.minefabrictory.screen.SheepShearMachineScreen;

public class SheepShearMachineGui extends HandledScreen<SheepShearMachineScreen>
{
	private static final Identifier BACKGROUND_TEXTURE = MineFabrictory.getId("textures/gui/sheep_shear_machine.png");
	private static final Identifier RECIPE_BUTTON_TEXTURE = new Identifier("textures/gui/recipe_button.png");

	public SheepShearMachineGui(SheepShearMachineScreen handler, PlayerInventory inventory, Text title)
	{
		super(handler, inventory, title);
	}

	@Override
	protected void init()
	{
		this.backgroundWidth = 176;
		this.backgroundHeight = 166;
		this.playerInventoryTitleY += 2;
		super.init();
		this.addButton(new TexturedButtonWidget(this.x + 122, this.y + 35, 20, 18, 0, 0, 19, RECIPE_BUTTON_TEXTURE, (button) -> {
			int x1 = this.handler.getPos().getX();
			int y1 = this.handler.getPos().getY();
			int z1 = this.handler.getPos().getZ();
			MinecraftClient.getInstance().player.sendMessage(new LiteralText(String.format("%d, %d, %d", x1, y1, z1)), false);
		}));
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY)
	{
		client.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
		this.drawTexture(matrices, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
	}
}
