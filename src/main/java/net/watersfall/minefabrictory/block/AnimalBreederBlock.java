package net.watersfall.minefabrictory.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.watersfall.minefabrictory.block.entity.AnimalBreederBlockEntity;
import net.watersfall.minefabrictory.block.entity.AreaWorkingMachineEntity;
import net.watersfall.minefabrictory.screen.SheepShearMachineScreen;
import org.jetbrains.annotations.Nullable;

public class AnimalBreederBlock extends WorkingAreaMachineBlock implements InventoryProvider, BlockEntityProvider
{
	public AnimalBreederBlock()
	{
		super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockView world)
	{
		return new AnimalBreederBlockEntity();
	}

	@Override
	protected ExtendedScreenHandlerFactory createScreenHandlerFactory(BlockPos pos, AreaWorkingMachineEntity entity)
	{
		return new WorkingAreaHandlerFactory(pos, new TranslatableText(this.getTranslationKey()))
		{
			@Override
			public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player)
			{
				return new SheepShearMachineScreen(syncId, (Inventory)entity, inventory, pos);
			}
		};
	}
}
