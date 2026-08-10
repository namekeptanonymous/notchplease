package org.nka.notchplease.mixin.client.gui.screens.inventory;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.BookSignScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(BookSignScreen.class)
public class BookSignScreenMixin {
    @ModifyArg(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;"
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
                    target = "Lnet/minecraft/client/gui/components/EditBox;<init>(Lnet/minecraft/client/gui/Font;IIIILnet/minecraft/network/chat/Component;)V"
            ),
            index = 2
    )
    private int adjustBookTitleBox(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }

   @Redirect(
           method = "extractRenderState",
           at = @At(
                   value = "INVOKE",
                   target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"
           )
   )
   private void redirectLabelText(GuiGraphicsExtractor instance, Font font, Component str, int x, int y, int color, boolean dropShadow) {
       int notchHeight = getScaledNotchHeight();
       if (notchHeight == -1) {
           instance.text(font, str, x, y, color, dropShadow);
           return;
       }
       instance.text(font, str, x, y + notchHeight, color, dropShadow);
   }

    @Redirect(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;textWithWordWrap(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/FormattedText;IIIIZ)V"
            )
    )
    private void redirectWrappedText(GuiGraphicsExtractor instance, Font font, FormattedText string, int x, int y, int width, int col, boolean dropShadow) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) {
            instance.textWithWordWrap(font, string, x, y, width, col, dropShadow);
            return;
        }
        instance.textWithWordWrap(font, string, x, y + notchHeight, width, col, dropShadow);
    }

    @ModifyArg(
            method = "extractBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V"
            ),
            index = 3
    )
    private int adjustBookBackground(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
}