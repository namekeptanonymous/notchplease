package org.nka.notchplease.mixin.client.gui.screen.ingame;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.BookSigningScreen;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(BookSigningScreen.class)
public class BookSigningScreenMixin {
    // This entire file is for 1.21.6-8 only.
    @ModifyArg(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;"
            ),
            index = 1
    )
    private int adjustCloseAndSignButton(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }

    @ModifyArg(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;<init>(Lnet/minecraft/client/font/TextRenderer;IIIILnet/minecraft/text/Text;)V"
            ),
            index = 2
    )
    private int adjustBookTitleBox(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }

   @Redirect(
           method = "render",
           at = @At(
                   value = "INVOKE",
                   target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)V"
           )
   )
   private void redirectText_void(DrawContext context, TextRenderer renderer, Text text, int x, int y, int color, boolean shadow) {
       int notchHeight = getScaledNotchHeight();
       if (notchHeight == -1) {
           context.drawText(renderer, text, x, y, color, shadow);
           return;
       }
       context.drawText(renderer, text, x, y + notchHeight, color, shadow);
   }

    @Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawWrappedText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/StringVisitable;IIIIZ)V"
            )
    )
    private void redirectWrappedText(DrawContext context, TextRenderer renderer, StringVisitable text, int x, int y, int width, int color, boolean shadow) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            context.drawWrappedText(renderer, text, x, y, width, color, shadow);
            return;
        }
        context.drawWrappedText(renderer, text, x, y + notchHeight, width, color, shadow);
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