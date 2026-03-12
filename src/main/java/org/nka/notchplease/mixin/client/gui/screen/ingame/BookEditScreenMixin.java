package org.nka.notchplease.mixin.client.gui.screen.ingame;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(BookEditScreen.class)
public class BookEditScreenMixin {
    @ModifyArg(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/PageTurnWidget;<init>(IIZLnet/minecraft/client/gui/widget/ButtonWidget$PressAction;Z)V"
            ),
            index = 1
    )
    private int adjustPageTurnButtons(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }

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

    // for 1.21.4-5
//    @Redirect(
//            method = "render",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"
//            )
//    )
//    private int redirectText_int(DrawContext context, TextRenderer renderer, Text text, int x, int y, int color, boolean shadow) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return context.drawText(renderer, text, x, y, color, shadow);
//        return context.drawText(renderer, text, x, y + notchHeight, color, shadow);
//    }
//    @Redirect(
//            method = "render",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;IIIZ)I"
//            )
//    )
//    private int redirectText_int(DrawContext context, TextRenderer renderer, OrderedText text, int x, int y, int color, boolean shadow) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return context.drawText(renderer, text, x, y, color, shadow);
//        return context.drawText(renderer, text, x, y + notchHeight, color, shadow);
//    }
//    @Redirect(
//            method = "render",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawWrappedText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/StringVisitable;IIIIZ)V"
//            )
//    )
//    private void redirectWrappedText(DrawContext context, TextRenderer renderer, StringVisitable text, int x, int y, int width, int color, boolean shadow) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) {
//            context.drawWrappedText(renderer, text, x, y, width, color, shadow);
//            return;
//        }
//        context.drawWrappedText(renderer, text, x, y + notchHeight, width, color, shadow);
//    }
//    @ModifyArg(
//            method = "renderBackground",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIFFIIII)V"
//            ),
//            index = 3
//    )
//    private int adjustBookBackground(int originalY) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return originalY;
//        return originalY + notchHeight;
//    }
//    @Redirect(
//            method = "drawCursor",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"
//            )
//    )
//    private int redirectTextCursor_(DrawContext context, TextRenderer renderer, String text, int x, int y, int color, boolean shadow) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return context.drawText(renderer, text, x, y, color, shadow);
//        return context.drawText(renderer, text, x, y + notchHeight, color, shadow);
//    }
//    @ModifyArgs(
//            method = "drawCursor",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"
//            )
//    )
//    private void adjustTextCursorRect(Args args) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return;
//
//        int y1 = args.get(1);
//        int y2 = args.get(3);
//
//        args.set(1, y1 + notchHeight);
//        args.set(3, y2 + notchHeight);
//    }
//    @ModifyArgs(
//            method = "drawSelection",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;fill(Lnet/minecraft/client/render/RenderLayer;IIIII)V"
//            )
//
//    )
//    private void adjustBackgroundFill(Args args) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return;
//
//        int y1 = args.get(2);
//        int y2 = args.get(4);
//
//        args.set(2, y1 + notchHeight);
//        args.set(4, y2 + notchHeight);
//    }
//    @ModifyArg(
//            method = "screenPositionToAbsolutePosition",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/screen/ingame/BookEditScreen$Position;<init>(II)V"
//            ),
//            index = 1
//    )
//    private int adjustScreenPositionToAbsolutePosition(int originalY) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return originalY;
//        return originalY - notchHeight;
//    }

    // for 1.21.6-10
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
    @ModifyArg(
            method = "renderBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIFFIIII)V"
            ),
            index = 3
    )
    private int adjustBookBackground(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
}