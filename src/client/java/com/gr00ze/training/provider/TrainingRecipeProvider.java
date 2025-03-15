package com.gr00ze.training.provider;

import com.gr00ze.training.item.ItemList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;



public class TrainingRecipeProvider extends FabricRecipeProvider {
    public TrainingRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);

    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {

        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShapeless(RecipeCategory.MISC, ItemList.SIMPLE_ITEM)
                        .input(Items.STICK)
                        .criterion("Name of Advncement", conditionsFromItem(Items.AIR))
                        .offerTo(recipeExporter);

                createShaped(RecipeCategory.MISC, ItemList.SIMPLE_BLOCK_ITEM)
                        .pattern("xx")
                        .pattern("xx")
                        .input('x', ItemList.SIMPLE_ITEM)
                        .criterion("Name of criterion", conditionsFromItem(Items.AIR))
                        .offerTo(recipeExporter);

            }
        };
    }


    @Override
    public String getName() {
        return "TrainingRecipeProvider";
    }
}