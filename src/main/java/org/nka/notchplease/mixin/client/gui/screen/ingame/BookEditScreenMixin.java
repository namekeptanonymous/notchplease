package org.nka.notchplease.mixin.client.gui.screen.ingame;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
//import net.minecraft.client.font.TextRenderer;
//import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
//import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(BookEditScreen.class)
public class BookEditScreenMixin {
//    @ModifyArg(
//            method = "init",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/widget/PageTurnWidget;<init>(IIZLnet/minecraft/client/gui/widget/ButtonWidget$PressAction;Z)V"
//            ),
//            index = 1
//    )
//    private int adjustPageTurnButtons(int originalY) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return originalY;
//        return originalY + notchHeight;
//    }

//    @ModifyArg(
//            method = "init",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;"
//            ),
//            index = 1
//    )
//    private int adjustCloseButton(int originalY) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return originalY;
//        return originalY + notchHeight;
//    }

//    @Redirect(
//            method = "render",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)V"
//            )
//    )
//    private void redirectText_void(DrawContext context, TextRenderer renderer, Text text, int x, int y, int color, boolean shadow) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) {
//            context.drawText(renderer, text, x, y, color, shadow);
//            return;
//        }
//        context.drawText(renderer, text, x, y + notchHeight, color, shadow);
//    }

    @ModifyArg(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/EditBoxWidget$Builder;y(I)Lnet/minecraft/client/gui/widget/EditBoxWidget$Builder;"
            ),
            index = 0
    )
    private int adjustBookTextBox(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }

//    @ModifyArg(
//            method = "renderBackground",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIFFIIII)V"
//            ),
//            index = 3
//    )
//    private int adjustBookBackground(int originalY) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return originalY;
//        return originalY + notchHeight;
//    }

    @ModifyReturnValue(
            method = "getTop",
            at = @At("RETURN")
    )
    private int adjustGetTop(int original) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return original;
        return original + notchHeight;
    }
}