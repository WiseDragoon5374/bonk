
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bonk.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.bonk.block.entity.NameChangerBlockEntity;
import net.mcreator.bonk.block.entity.MultiblockBlockEntity;
import net.mcreator.bonk.block.entity.ItemSyncerBlockEntity;

import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BonkModBlockEntities {
	private static final List<BlockEntityType<?>> REGISTRY = new ArrayList<>();
	public static final BlockEntityType<?> MULTIBLOCK = register("bonk:multiblock", BonkModBlocks.MULTIBLOCK, MultiblockBlockEntity::new);
	public static final BlockEntityType<?> NAME_CHANGER = register("bonk:name_changer", BonkModBlocks.NAME_CHANGER, NameChangerBlockEntity::new);
	public static final BlockEntityType<?> ITEM_SYNCER = register("bonk:item_syncer", BonkModBlocks.ITEM_SYNCER, ItemSyncerBlockEntity::new);

	private static BlockEntityType<?> register(String registryname, Block block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		BlockEntityType<?> blockEntityType = BlockEntityType.Builder.of(supplier, block).build(null).setRegistryName(registryname);
		REGISTRY.add(blockEntityType);
		return blockEntityType;
	}

	@SubscribeEvent
	public static void registerTileEntity(RegistryEvent.Register<BlockEntityType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new BlockEntityType[0]));
	}
}
