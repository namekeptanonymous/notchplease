package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.screen.world.WorldListWidget;
import net.minecraft.client.gui.widget.LayoutWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import org.nka.notchplease.mixin.client.gui.screen.ScreenMixin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(SelectWorldScreen.class)
public class SelectWorldScreenMixin extends ScreenMixin {
    @Shadow
    protected TextFieldWidget searchBox;

    protected SelectWorldScreenMixin(List<Element> children) {
        super(children);
    }

    // for 1.21.6-8
//    @Redirect(
//            method = "render",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawCenteredTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"
//            )
//    )
//    private void redirectCenteredTextWithShadow(DrawContext context, TextRenderer renderer, Text text, int x, int y, int color) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) {
//            context.drawCenteredTextWithShadow(renderer, text, x, y, color);
//            return;
//        }
//        context.drawCenteredTextWithShadow(renderer, text, x, y + notchHeight, color);
//    }
//
//    @Redirect(
//            method = "init()V",
//            at = @At(
//                    value = "NEW",
//                    target = "net/minecraft/client/gui/screen/world/WorldListWidget"
//            )
//    )
//    private WorldListWidget redirectWorldListWidget(
//            SelectWorldScreen parent, MinecraftClient client, int width, int height, int y, int itemHeight, String search, WorldListWidget oldWidget
//    ) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return new WorldListWidget(parent, client, width, height, y, itemHeight, search, oldWidget);
//
//        int newHeight = height - notchHeight;
//        int newY = y + notchHeight;
//        return new WorldListWidget(parent, client, width, newHeight, newY, itemHeight, search, oldWidget);
//    }

    // for 1.21.9-10
    @Unique
    @Final
    private LayoutWidget layout;

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void shiftElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        this.searchBox.setY(this.searchBox.getY() + notchHeight);
        for (Element e : this.getChildren()) {
            if (e instanceof TextWidget tw) {
                tw.setY(tw.getY() + notchHeight);
            }
            if (e instanceof WorldListWidget list) {
                list.setY(list.getY() + notchHeight);
                list.setHeight(list.getHeight() - notchHeight);
            }
        }
    }
}
