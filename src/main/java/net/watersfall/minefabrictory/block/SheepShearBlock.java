package net.watersfall.minefabrictory.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.watersfall.minefabrictory.block.entity.AreaWorkingMachineEntity;
import net.watersfall.minefabrictory.block.entity.SheepShearBlockEntity;
import net.watersfall.minefabrictory.screen.SheepShearMachineScreen;
import org.jetbrains.annotations.Nullable;

public class SheepShearBlock extends WorkingAreaMachineBlock implements InventoryProvider, BlockEntityProvider
{
	public SheepShearBlock()
	{
		super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockView world)
	{
		return new SheepShearBlockEntity();
	}

	@Override
	public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos)
	{
		BlockEntity test = world.getBlockEntity(pos);
		if(test instanceof SheepShearBlockEntity)
		{
			return (SidedInventory)test;
		}
		return null;
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
