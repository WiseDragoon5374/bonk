package net.wisedragoon.bonk.procedures;

import net.wisedragoon.bonk.init.BonkModMobEffects;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

public class BeanAppleFoodEatenProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player) {
			_player.getAbilities().mayfly = (true);
			_player.onUpdateAbilities();
		}
		if (entity instanceof LivingEntity _entity)
			_entity.addEffect(new MobEffectInstance(BonkModMobEffects.REC_BUFF, 1080000, 1, (false), (true)));
	}
}
