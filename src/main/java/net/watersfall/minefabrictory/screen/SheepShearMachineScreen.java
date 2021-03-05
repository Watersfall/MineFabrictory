package net.watersfall.minefabrictory.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class SheepShearMachineScreen extends ScreenHandler
{
	private BlockPos pos;

	public SheepShearMachineScreen(int syncId, PlayerInventory inventory, PacketByteBuf buf)
	{
		this(syncId, new SimpleInventory(12), inventory, buf.readBlockPos());
	}

	public SheepShearMachineScreen(int syncId, Inventory inventory, PlayerInventory playerInventory, BlockPos pos)
	{
		super(MineFabrictoryScreenHandlers.SHEEP_SHEAR_MACHINE_SCREEN_HANDLER, syncId);
		this.pos = pos;

		//Machine Inventory
		byte index = 0;
		for(int y = 0; y < 4; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				this.addSlot(new Slot(inventory, index, 8 + (x * 18), 18 + (y * 18)));
				index++;
			}
		}

		//Future upgrade slots
		SimpleInventory tempUpgradeInv = new SimpleInventory(3);
		for(int y = 0; y < 3; y++)
		{
			this.addSlot(new Slot(tempUpgradeInv, y, 152, 18 + (y * 18)));
		}

		//Player Inventory
		int m;
		int l;
		for (m = 0; m < 3; ++m)
		{
			for (l = 0; l < 9; ++l)
			{
				this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 86 + m * 18));
			}
		}
		for (m = 0; m < 9; ++m)
		{
			this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 144));
		}
	}

	@Override
	public boolean canUse(PlayerEntity player)
	{
		return true;
	}

	public BlockPos getPos()
	{
		return this.pos;
	}
}
