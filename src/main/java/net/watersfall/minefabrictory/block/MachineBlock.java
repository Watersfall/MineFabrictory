package net.watersfall.minefabrictory.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public abstract class MachineBlock extends Block
{
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

	public MachineBlock(Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		super.appendProperties(builder);
		builder.add(FACING);
	}

	public Direction getFacing(BlockState state)
	{
		return state.get(FACING);
	}

	public void setFacing(World world, BlockPos pos, BlockState state, Direction facing)
	{
		world.setBlockState(pos, state.with(FACING, facing));
	}
}
