package net.wisedragoon.bonk.procedures;

import net.wisedragoon.bonk.network.BonkModVariables;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.client.gui.components.Checkbox;

import java.util.HashMap;

public class AskToSetSpawnDimensionThisGUIIsClosedProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		if (guistate.containsKey("checkbox:dontaskmeagain") ? ((Checkbox) guistate.get("checkbox:dontaskmeagain")).selected() : false) {
			BonkModVariables.MapVariables.get(world).isdamachecked = "true";
			BonkModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
