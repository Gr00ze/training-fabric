package com.gr00ze.training.screen;

import com.gr00ze.training.item.ItemList;
import com.gr00ze.training.item.TrainingCustomRenderItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class TrainingScreenForItemComponents extends Screen {
    private TextFieldWidget searchField;
    private String searchQuery = "";

    public TrainingScreenForItemComponents() {
        super(Text.literal("Training Screen"));


    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        //this.renderBackground(context);

        ItemStack stack = ItemList.CUSTOM_RENDERING_ITEM.getDefaultStack();

        MinecraftClient client = MinecraftClient.getInstance();
        ItemRenderer itemRenderer = client.getItemRenderer();
        MatrixStack matrices = new MatrixStack();

        matrices.push();
        // Spostiamo l’item nella GUI
        matrices.translate(3.3, 1.8, -3);
        matrices.scale(0.5f, 0.5f, 0.5f); // scala ragionevole

        stack.set(TrainingCustomRenderItem.ELECTRIC, true);

        itemRenderer.renderItem(
                null,                       // nessun LivingEntity
                stack,
                ItemDisplayContext.GUI,
                matrices,
                client.getBufferBuilders().getEntityVertexConsumers(),
                null,                       // world null perché GUI
                0xF000F0,                   // luce massima
                OverlayTexture.DEFAULT_UV,
                0
        );
        matrices.pop();



        renderSearchBar(stack, context, mouseX, mouseY, delta);
        renderComponentMenu(stack, context, mouseX, mouseY);


        super.render(context, mouseX, mouseY, delta);
    }




    private void renderComponentMenu(ItemStack stack, DrawContext context, int mouseX, int mouseY) {
        ComponentMap componentMap = stack.getComponents();

        int menuX = 10;
        int menuY = 40; // sotto la barra di ricerca
        int lineHeight = 14;
        int spacing = 4;

        int index = 0;

        // filtro
        String filter = (searchQuery == null) ? "" : searchQuery.toLowerCase();

        for (ComponentType<?> type : componentMap.getTypes()) {

            String name = type.toString().toLowerCase();
            Object value = stack.get(type);
            String valueContent = value.toString().toLowerCase();

            String text = type + ": " + value;
            // se non matcha, skippa
            if (!text.toLowerCase().contains(filter)) continue;



            int y = menuY + index * (lineHeight + spacing);

            // sfondo riga (opzionale)
            context.fill(menuX - 4, y - 2, menuX + (text.length() +1) * 5, y + lineHeight, 0x66000000);

            // testo
            context.drawText(
                    this.textRenderer,
                    text,
                    menuX,
                    y,
                    0xFFFFFFFF,
                    false
            );

            index++;
        }
    }


    @Override
    protected void init() {
        super.init();
        initSearchBar();
    }

    private void initSearchBar(){
        int searchWidth = 120;
        int searchHeight = 20;
        int searchX = 10;
        int searchY = 10;

        searchField = new TextFieldWidget(this.textRenderer, searchX, searchY, searchWidth, searchHeight, Text.literal("Search"));
        searchField.setMaxLength(64);
        searchField.setEditableColor(0xFFFFFFFF);
        searchField.setUneditableColor(0xFFAAAAAA);
        searchField.setChangedListener(str -> searchQuery = str);

        this.addSelectableChild(searchField);

        this.setFocused(searchField);
    }


    private void renderSearchBar(ItemStack stack, DrawContext context, int mouseX, int mouseY, float delta){

        searchField.render(context,mouseX, mouseY, delta);
        //searchQuery = searchField.getText();



    }
    @Override
    protected void applyBlur(DrawContext context) {
        //super.applyBlur(context);
    }

    public void renderBackground(DrawContext context) {
        context.fill(0, 0, this.width, this.height, 0x0000000); // sfondo uniforme
    }
}

