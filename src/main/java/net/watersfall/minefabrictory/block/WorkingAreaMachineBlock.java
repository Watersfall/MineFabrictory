package net.watersfall.minefabrictory.block;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.watersfall.minefabrictory.block.entity.AreaWorkingMachineEntity;
import org.jetbrains.annotations.Nullable;

public abstract class WorkingAreaMachineBlock extends MachineBlock
{
	public WorkingAreaMachineBlock(Settings settings)
	{
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		BlockEntity test = world.getBlockEntity(pos);
		if(test instanceof AreaWorkingMachineEntity)
		{
			if(!world.isClient)
			{
				AreaWorkingMachineEntity entity = (AreaWorkingMachineEntity)test;
				player.openHandledScreen(createScreenHandlerFactory(pos, entity));
			}
			return ActionResult.success(world.isClient);
		}
		return ActionResult.PASS;
	}

	 protected abstract ExtendedScreenHandlerFactory createScreenHandlerFactory(BlockPos pos, AreaWorkingMachineEntity entity);

	public abstract class WorkingAreaHandlerFactory implements ExtendedScreenHandlerFactory
	{
		private BlockPos pos;
		private TranslatableText text;
		public WorkingAreaHandlerFactory(BlockPos pos, TranslatableText text)
		{
			this.pos = pos;
			this.text = text;
		}

		@Override
		public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf)
		{
			buf.writeBlockPos(pos);
		}

		@Override
		public Text getDisplayName()
		{
			return text;
		}
	}
}
