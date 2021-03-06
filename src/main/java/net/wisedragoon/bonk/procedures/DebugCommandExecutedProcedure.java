package net.wisedragoon.bonk.procedures;

import net.wisedragoon.bonk.world.inventory.NameChangerGUIMenu;
import net.wisedragoon.bonk.world.inventory.MultimenuMenu;
import net.wisedragoon.bonk.world.inventory.AskToSetSpawnDimensionMenu;
import net.wisedragoon.bonk.network.BonkModVariables;
import net.wisedragoon.bonk.init.BonkModGameRules;

import net.minecraftforge.fmllegacy.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.HashMap;

import io.netty.buffer.Unpooled;

public class DebugCommandExecutedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap cmdparams) {
		if (entity == null || cmdparams == null)
			return;
		if ((cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "").equals("variable")) {
			if (world.getLevelData().getGameRules().getBoolean(BonkModGameRules.BONKDEBUGCOMMANDS)) {
				if ((cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "").equals("set")) {
					if ((cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "").equals("spawndimension")) {
						BonkModVariables.MapVariables.get(world).spawndimension = cmdparams.containsKey("3") ? cmdparams.get("3").toString() : "";
						BonkModVariables.MapVariables.get(world).syncData(world);
					} else if ((cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "").equals("isdamachecked")) {
						BonkModVariables.MapVariables.get(world).isdamachecked = cmdparams.containsKey("3") ? cmdparams.get("3").toString() : "";
						BonkModVariables.MapVariables.get(world).syncData(world);
					} else if ((cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "").equals("synceditem")) {
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(new TextComponent("Unused variable. It was for the Item Syncer, but it got deleted."),
									(false));
					} else if ((cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "").equals("iteminmainhand")) {
						if (!(cmdparams.containsKey("3") ? cmdparams.get("3").toString() : "").equals("")) {
							if (entity instanceof Player _player && !_player.level.isClientSide())
								_player.displayClientMessage(new TextComponent(
										"This variable does not support text input, and needs an item. Please hold the item you would like to set to this variable, and run /debugbonk variable set (variable name)."),
										(false));
						} else {
							BonkModVariables.MapVariables.get(
									world).iteminmainhand = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
							BonkModVariables.MapVariables.get(world).syncData(world);
						}
					} else if ((cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "").equals("amountitemsynced")) {
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(new TextComponent("Unused variable. It was for the Item Syncer, but it got deleted."),
									(false));
					} else {
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(new TextComponent("Please specify a variable. Use /debugbonk help if you need help."),
									(false));
					}
				} else if ((cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "").equals("get")) {
					if ((cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "").equals("spawndimension")) {
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(
									new TextComponent(
											("The variable spawndimension is set to " + BonkModVariables.MapVariables.get(world).spawndimension)),
									(false));
					} else if ((cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "").equals("isdamachecked")) {
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(
									new TextComponent(
											("The variable isdamachecked is set to " + BonkModVariables.MapVariables.get(world).isdamachecked)),
									(false));
					} else if ((cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "").equals("synceditem")) {
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(new TextComponent("Unused variable. It was for the Item Syncer, but it got deleted."),
									(false));
					} else if ((cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "").equals("iteminmainhand")) {
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(new TextComponent(("The variable iteminmainhand is set to an item named "
									+ (BonkModVariables.MapVariables.get(world).iteminmainhand).getDisplayName().getString()
									+ ". Please check the exact item using a GUI that can see it.")), (false));
					} else if ((cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "").equals("amountitemsynced")) {
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(new TextComponent("Unused variable. It was for the Item Syncer, but it got deleted."),
									(false));
					} else {
						if (entity instanceof Player _player && !_player.level.isClientSide())
							_player.displayClientMessage(new TextComponent("Please specify a variable. Use /debugbonk help if you need help."),
									(false));
					}
				} else {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(new TextComponent("Please specify a subcommand. Use /debugbonk help if you need help."),
								(false));
				}
			} else {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(new TextComponent("Bonk Debug Commands are disabled. Please enable them to use this command."),
							(false));
			}
		} else if ((cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "").equals("help")) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Bonk Debug Commands"), (false));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("(NOTE: These only work if you have Bonk Debug Commands enabled)"), (false));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("- \"variable\": Does stuff with variables based around the Bonk mod."), (false));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Subparameters:"), (false));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("\"get\": Shows the state of the specified variable in parameter 3."), (false));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(
						new TextComponent("\"set\": Sets the state of the specified variable in parameter 3 to whatever is in parameter 4.."),
						(false));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("-----END OF SUBPARAMETERS-----"), (false));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("- \"help\": Shows this help message."), (false));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("- \"gui\": Opens the GUI specified in parameter 2."), (false));
		} else if ((cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "").equals("gui")) {
			if (world.getLevelData().getGameRules().getBoolean(BonkModGameRules.BONKDEBUGCOMMANDS)) {
				if ((cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "").equals("Multimenu")) {
					{
						if (entity instanceof ServerPlayer _ent) {
							BlockPos _bpos = new BlockPos((int) x, (int) y, (int) z);
							NetworkHooks.openGui((ServerPlayer) _ent, new MenuProvider() {
								@Override
								public Component getDisplayName() {
									return new TextComponent("Multimenu");
								}

								@Override
								public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
									return new MultimenuMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
								}
							}, _bpos);
						}
					}
				} else if ((cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "").equals("AskToSetSpawnDimension")) {
					{
						if (entity instanceof ServerPlayer _ent) {
							BlockPos _bpos = new BlockPos((int) x, (int) y, (int) z);
							NetworkHooks.openGui((ServerPlayer) _ent, new MenuProvider() {
								@Override
								public Component getDisplayName() {
									return new TextComponent("AskToSetSpawnDimension");
								}

								@Override
								public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
									return new AskToSetSpawnDimensionMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
								}
							}, _bpos);
						}
					}
				} else if ((cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "").equals("MilkContainerGUI")) {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(new TextComponent("The Milk Container no longer exists, sorry."), (false));
				} else if ((cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "").equals("NameChangerGUI")) {
					{
						if (entity instanceof ServerPlayer _ent) {
							BlockPos _bpos = new BlockPos((int) x, (int) y, (int) z);
							NetworkHooks.openGui((ServerPlayer) _ent, new MenuProvider() {
								@Override
								public Component getDisplayName() {
									return new TextComponent("NameChangerGUI");
								}

								@Override
								public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
									return new NameChangerGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
								}
							}, _bpos);
						}
					}
				} else if ((cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "").equals("ItemSyncerGUI")) {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(new TextComponent("The Item Syncer no longer exists, sorry."), (false));
				} else {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(new TextComponent("Please specify a GUI. Use /debugbonk help if you need help."), (false));
				}
			} else {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(new TextComponent("Bonk Debug Commands are disabled. Please enable them to use this command."),
							(false));
			}
		} else {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Please specify a debug command. Use /debugbonk help if you need help."), (false));
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Looking for gameplay commands? They can be found using the TAB key."), (false));
		}
	}
}
