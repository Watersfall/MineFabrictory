package net.watersfall.minefabrictory.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.watersfall.minefabrictory.block.entity.SheepShearBlockEntity;
import net.watersfall.minefabrictory.screen.SheepShearMachineScreen;
import org.jetbrains.annotations.Nullable;

public class SheepShearBlock extends MachineBlock implements InventoryProvider, BlockEntityProvider
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
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		BlockEntity test = world.getBlockEntity(pos);
		if(test instanceof SheepShearBlockEntity)
		{
			if(!world.isClient)
			{
				SheepShearBlockEntity entity = (SheepShearBlockEntity)test;
				player.openHandledScreen(createScreenHandlerFactory(pos, entity));
			}
			return ActionResult.success(world.isClient);
		}
		return ActionResult.PASS;
	}

	private ExtendedScreenHandlerFactory createScreenHandlerFactory(BlockPos pos, SheepShearBlockEntity entity)
	{
		return new ExtendedScreenHandlerFactory()
		{
			@Override
			public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf)
			{
				buf.writeBlockPos(pos);
			}

			@Override
			public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player)
			{
				return new SheepShearMachineScreen(syncId, entity, inventory, pos);
			}

			@Override
			public Text getDisplayName()
			{
				return new LiteralText("Test");
			}
		};
	}
}
