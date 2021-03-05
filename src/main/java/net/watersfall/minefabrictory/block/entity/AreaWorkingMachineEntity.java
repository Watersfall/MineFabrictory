package net.watersfall.minefabrictory.block.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.watersfall.minefabrictory.block.MachineBlock;

public abstract class AreaWorkingMachineEntity extends BlockEntity implements BlockEntityClientSerializable
{
	private Box workingArea;
	private boolean showWorkingArea = false;

	public AreaWorkingMachineEntity(BlockEntityType<?> type)
	{
		super(type);
	}

	public void calculateBox(BlockState state)
	{
		int range = getRange();
		if(state.getBlock() instanceof MachineBlock)
		{
			MachineBlock block = (MachineBlock)state.getBlock();
			Direction direction = block.getFacing(state);
			Direction direction2 = null;
			BlockPos startPos = this.pos;
			switch(direction)
			{
				case EAST:
				case SOUTH:
					startPos = startPos.offset(direction);
					break;
			}
			if(direction == Direction.NORTH || direction == Direction.SOUTH)
			{
				direction2 = Direction.EAST;
			}
			else
			{
				direction2 = Direction.SOUTH;
			}
			this.workingArea = new Box(startPos.offset(direction, range).offset(direction2.getOpposite(), range / 2), startPos.up().offset(direction2, Math.round(range / 2F)));
			this.sync();
		}
	}

	public boolean shouldRenderWorkingArea()
	{
		return this.showWorkingArea;
	}

	public Box getWorkingArea()
	{
		if(this.workingArea == null)
		{
			this.calculateBox(this.world.getBlockState(pos));
		}
		return this.workingArea;
	}

	public int getRange()
	{
		return 3;
	}

	public void setShowWorkingArea(boolean showWorkingArea)
	{
		this.showWorkingArea = showWorkingArea;
	}

	@Override
	public void fromClientTag(CompoundTag tag)
	{
		this.workingArea = new Box(tag.getDouble("x1"), tag.getDouble("y1"), tag.getDouble("z1"), tag.getDouble("x2"), tag.getDouble("y2"), tag.getDouble("z2"));
	}

	@Override
	public CompoundTag toClientTag(CompoundTag tag)
	{
		if(this.workingArea != null)
		{
			tag.putDouble("x1", this.workingArea.minX - this.pos.getX());
			tag.putDouble("y1", this.workingArea.minY - this.pos.getY());
			tag.putDouble("z1", this.workingArea.minZ - this.pos.getZ());
			tag.putDouble("x2", this.workingArea.maxX - this.pos.getX());
			tag.putDouble("y2", this.workingArea.maxY - this.pos.getY());
			tag.putDouble("z2", this.workingArea.maxZ - this.pos.getZ());
		}
		return tag;
	}
}
