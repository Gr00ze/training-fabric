package com.gr00ze.training.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RopeItem extends Item {
    public RopeItem(Settings settings) {
        super(settings);
    }

    public static Item.Settings getItemSetting(){
        return new Settings();
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient){
            double reach = 10;

            HitResult hit = user.raycast(reach, 0 , false);
            if (hit != null && hit instanceof EntityHitResult entityHitResult) {

                // Calcola la distanza e applica la forza come in useOnEntity
                Entity target = entityHitResult.getEntity();
                Vec3d distance = user.getPos().subtract(target.getPos());
                target.addVelocity(distance);
                user.sendMessage(Text.literal("Target colpito!"), true);

                Vec3d from = user.getEyePos();
                Vec3d to = target.getEyePos();
                int points = 20;
                for (int i = 0; i <= points; i++) {
                    double t = i / (double) points;
                    double x = from.x + (to.x - from.x) * t;
                    double y = from.y + (to.y - from.y) * t;
                    double z = from.z + (to.z - from.z) * t;
                    world.addParticleClient(ParticleTypes.SMOKE, x, y, z, 0, 0, 0);
                }

                return ActionResult.SUCCESS;
            }

        }





        return ActionResult.PASS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        Vec3d userPos = user.getPos();
        Vec3d entityPos = entity.getPos();

        Vec3d distance = userPos.subtract(entityPos);

        Vec3d v = distance;

        entity.addVelocity(v);
        return super.useOnEntity(stack, user, entity, hand);
    }
}