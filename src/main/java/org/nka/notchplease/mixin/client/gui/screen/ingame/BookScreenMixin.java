package org.nka.notchplease.mixin.client.gui.screen.ingame;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
//import net.minecraft.client.font.TextRenderer;
//import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.BookScreen;
//import net.minecraft.text.OrderedText;
//import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.ModifyArg;
//import org.spongepowered.asm.mixin.injection.Redirect;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(BookScreen.class)
public class BookScreenMixin {
//    @ModifyArg(
//            method = "addPageButtons",
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

//    @ModifyReturnValue(
//            method = "getCloseButtonY",
//            at = @At("RETURN")
//    )
//    private int adjustGetCloseButtonY(int original) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return original;
//        return original + notchHeight;
//    }

//    @Redirect(
//            method = "render", // this being ambiguous doesn't matter as only one render() has our target
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;IIIZ)V"
//            )
//    )
//    private void redirectText_void(DrawContext context, TextRenderer renderer, OrderedText text, int x, int y, int color, boolean shadow) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) {
//            context.drawText(renderer, text, x, y, color, shadow);
//            return;
//        }
//        context.drawText(renderer, text, x, y + notchHeight, color, shadow);
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
}