package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.world.PresetsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import org.nka.notchplease.mixin.client.gui.screen.ScreenMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(PresetsScreen.class)
public class PresetsScreenMixin extends ScreenMixin {

    protected PresetsScreenMixin(List<Element> children) {
        super(children);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void afterInit(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        for (Element element : this.getChildren()) {
            if (element instanceof ClickableWidget w && !(element instanceof ButtonWidget)) {
                w.setY(w.getY() + notchHeight);
                System.out.println("caught: " + w + " " + w.getMessage());
                if (w.toString().contains("$") && w.getMessage().toString().contains("empty")) {
                    w.setHeight(w.getHeight() - notchHeight);
                }
            }
        }
    }

    // for 1.21.4-5
    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I"
            )
    )
    private int redirectTextWithShadow_int(DrawContext context, TextRenderer renderer, Text text, int x, int y, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            return context.drawTextWithShadow(renderer, text, x, y, color);
        }
        return context.drawTextWithShadow(renderer, text, x, y + notchHeight, color);
    }

    // for 1.21.6-7
//    @Redirect(
//            method = "render",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"
//            )
//    )
//    private void redirectTextWithShadow_void(DrawContext context, TextRenderer renderer, Text text, int x, int y, int color) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) {
//            context.drawTextWithShadow(renderer, text, x, y, color);
//            return;
//        }
//        context.drawTextWithShadow(renderer, text, x, y + notchHeight, color);
//    }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawCenteredTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"
            )
    )
    private void redirectCenteredTextWithShadow(DrawContext context, TextRenderer renderer, Text text, int x, int y, int color) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            context.drawCenteredTextWithShadow(renderer, text, x, y, color);
            return;
        }
        context.drawCenteredTextWithShadow(renderer, text, x, y + notchHeight, color);
    }
}