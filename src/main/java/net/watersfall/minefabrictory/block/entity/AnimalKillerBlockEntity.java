package net.watersfall.minefabrictory.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.watersfall.minefabrictory.inventory.BasicInventory;

import java.util.HashMap;
import java.util.List;

public class AnimalKillerBlockEntity extends AreaWorkingMachineEntity implements Tickable, BasicInventory
{
	private final DefaultedList<ItemStack> contents;

	public AnimalKillerBlockEntity()
	{
		super(MineFabrictoryBlockEntities.ANIMAL_KILLER_ENTITY);
		this.contents = DefaultedList.ofSize(12, ItemStack.EMPTY);
	}

	@Override
	public void tick()
	{
		if(this.world != null && !this.world.isClient && this.world.getTime() % 100 == 0)
		{
			List<AnimalEntity> entities = world.getEntitiesByClass(AnimalEntity.class, this.getWorkingArea(), (entity) -> !entity.isBaby());
			HashMap<EntityType, Integer> types = new HashMap<>();
			for(int i = 0; i < entities.size(); i++)
			{
				int count = types.compute(entities.get(i).getType(), (k, v) -> (v == null) ? 1 : ++v);
				if(count > 2)
				{
					LootTable loot = world.getServer().getLootManager().getTable(entities.get(i).getLootTable());
					List<ItemStack> stacks = loot.generateLoot(new LootContext.Builder((ServerWorld) world)
							.parameter(LootContextParameters.DAMAGE_SOURCE, DamageSource.GENERIC)
							.parameter(LootContextParameters.ORIGIN, Vec3d.ZERO)
							.parameter(LootContextParameters.THIS_ENTITY, entities.get(i))
							.build(LootContextTypes.ENTITY));
					stacks.forEach((stack) -> {
						for(int o = 0; o < this.size() && !stack.isEmpty(); o++)
						{
							stack = this.addStack(o, stack);
						}
					});
					entities.get(i).setHealth(0);
					break;
				}
			}
		}
	}

	@Override
	public DefaultedList<ItemStack> getContents()
	{
		return this.contents;
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag)
	{
		super.fromTag(state, tag);
		Inventories.fromTag(tag, this.contents);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag)
	{
		super.toTag(tag);
		return Inventories.toTag(tag, this.contents);
	}
}
