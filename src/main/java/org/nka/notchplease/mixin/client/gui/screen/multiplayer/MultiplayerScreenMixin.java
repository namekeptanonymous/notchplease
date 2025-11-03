package org.nka.notchplease.mixin.client.gui.screen.multiplayer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.gui.screen.world.WorldListWidget;
import net.minecraft.client.gui.widget.LayoutWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import net.minecraft.text.Text;
import org.nka.notchplease.mixin.client.gui.screen.ScreenMixin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin extends ScreenMixin {
    @Shadow
    @Final
    private ThreePartsLayoutWidget field_62178;

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
//            method = "init",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/screen/multiplayer/MultiplayerServerListWidget;setDimensionsAndPosition(IIII)V"
//            )
//    )
//    private void redirectSetDimensionsAndPosition(MultiplayerServerListWidget instance, int width, int height, int x, int y) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) {
//            instance.setDimensionsAndPosition(width, height, x, y);
//            return;
//        }
//        // Added extra spacing (8 - same as Video Settings) to make
//        // default UI look a
//        bit more spaced out.
//        int newHeight = height - notchHeight - 8;
//        int newY = y +  notchHeight + 8;
//        instance.setDimensionsAndPosition(width, newHeight, x, newY);
//    }

//    @Redirect(
//            method = "init()V",
//            at = @At(
//                    value = "NEW",
//                    target = "net/minecraft/client/gui/screen/multiplayer/MultiplayerServerListWidget"
//            )
//    )
//    private MultiplayerServerListWidget redirectMultiplayerServerListWidget(MultiplayerScreen screen, MinecraftClient client, int width, int height, int top, int bottom) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) {
//            return new MultiplayerServerListWidget(screen, client, width, height, top, bottom);
//        }
//        // Added extra spacing (8 - same as Video Settings) to make
//        // default UI look a bit more spaced out.
//        int newHeight = height - notchHeight - 8;
//        int newTop = top + notchHeight + 8;
//        return new MultiplayerServerListWidget(screen, client, width, newHeight, newTop, bottom);
//    }

    // for 1.21.9-10
    protected MultiplayerScreenMixin(List<Element> children) {
        super(children);
    }

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void shiftElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        for (Element e : this.getChildren()) {
            if (e instanceof TextWidget tw) {
                tw.setY(tw.getY() + notchHeight);
            }
            if (e instanceof MultiplayerServerListWidget list) {
                list.setY(list.getY() + notchHeight);
                list.setHeight(list.getHeight() - notchHeight);
            }
        }
    }
}
