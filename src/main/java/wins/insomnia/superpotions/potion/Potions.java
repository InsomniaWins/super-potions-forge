package wins.insomnia.superpotions.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import wins.insomnia.superpotions.SuperPotions;

public class Potions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, SuperPotions.MODID);


    public static final Holder<Potion> SUPER_LEAPING = POTIONS.register("super_leaping",
            () -> new Potion("leaping", new MobEffectInstance(MobEffects.JUMP, 3600, 1))
    );
    public static final Holder<Potion> SUPER_SWIFTNESS = POTIONS.register("super_swiftness",
            () -> new Potion("swiftness", new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 1))
    );
    public static final Holder<Potion> SUPER_SLOWNESS = POTIONS.register("super_slowness",
            () -> new Potion("slowness", new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1800, 3))
    );
    public static final Holder<Potion> SUPER_TURTLE_MASTER = POTIONS.register("super_turtle_master",
            () -> new Potion("turtle_master", new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 800, 5), new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 800, 3))
    );
    public static final Holder<Potion> SUPER_POISON = POTIONS.register("super_poison",
            () -> new Potion("poison", new MobEffectInstance(MobEffects.POISON, 900, 1))
    );
    public static final Holder<Potion> SUPER_REGENERATION = POTIONS.register("super_regeneration",
            () -> new Potion("regeneration", new MobEffectInstance(MobEffects.REGENERATION, 900, 1))
    );
    public static final Holder<Potion> SUPER_STRENGTH = POTIONS.register("super_strength",
            () -> new Potion("strength", new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 1))
    );



    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }


    @SubscribeEvent
    public static void onBrewingRecipesRegister(RegisterBrewingRecipesEvent event) {

        PotionBrewing.Builder builder = event.getBuilder();

        SuperPotionRecipe[] superPotionRecipes = new SuperPotionRecipe[] {
                new SuperPotionRecipe(
                        net.minecraft.world.item.alchemy.Potions.LONG_LEAPING,
                        net.minecraft.world.item.alchemy.Potions.STRONG_LEAPING,
                        SUPER_LEAPING
                ),
                new SuperPotionRecipe(
                        net.minecraft.world.item.alchemy.Potions.LONG_SWIFTNESS,
                        net.minecraft.world.item.alchemy.Potions.STRONG_SWIFTNESS,
                        SUPER_SWIFTNESS
                ),
                new SuperPotionRecipe(
                        net.minecraft.world.item.alchemy.Potions.LONG_SLOWNESS,
                        net.minecraft.world.item.alchemy.Potions.STRONG_SLOWNESS,
                        SUPER_SLOWNESS
                ),
                new SuperPotionRecipe(
                        net.minecraft.world.item.alchemy.Potions.LONG_TURTLE_MASTER,
                        net.minecraft.world.item.alchemy.Potions.STRONG_TURTLE_MASTER,
                        SUPER_TURTLE_MASTER
                ),
                new SuperPotionRecipe(
                        net.minecraft.world.item.alchemy.Potions.LONG_POISON,
                        net.minecraft.world.item.alchemy.Potions.STRONG_POISON,
                        SUPER_POISON
                ),
                new SuperPotionRecipe(
                        net.minecraft.world.item.alchemy.Potions.LONG_REGENERATION,
                        net.minecraft.world.item.alchemy.Potions.STRONG_REGENERATION,
                        SUPER_REGENERATION
                ),
                new SuperPotionRecipe(
                        net.minecraft.world.item.alchemy.Potions.LONG_STRENGTH,
                        net.minecraft.world.item.alchemy.Potions.STRONG_STRENGTH,
                        SUPER_STRENGTH
                ),
        };


        for (SuperPotionRecipe recipe : superPotionRecipes) {

            builder.addMix(recipe.longPotion, Items.GLOWSTONE, recipe.superPotion);
            builder.addMix(recipe.strongPotion, Items.REDSTONE_BLOCK, recipe.superPotion);

        }


    }

    public record SuperPotionRecipe(Holder<Potion> longPotion, Holder<Potion> strongPotion, Holder<Potion> superPotion) {};
}
