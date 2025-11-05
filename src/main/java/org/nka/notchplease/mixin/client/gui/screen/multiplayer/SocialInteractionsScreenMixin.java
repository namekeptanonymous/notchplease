package org.nka.notchplease.mixin.client.gui.screen.multiplayer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsPlayerListWidget;
import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsScreen;
import net.minecraft.client.gui.screen.world.WorldListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(value = SocialInteractionsScreen.class)
public class SocialInteractionsScreenMixin {
    @Shadow
    TextFieldWidget searchBox;

    @Shadow
    SocialInteractionsPlayerListWidget playerList;

    @Shadow
    private ButtonWidget blockingButton;

    @Final
    @Shadow
    private ThreePartsLayoutWidget layout;

    // for 1.21.4-5
//    @Redirect(
//            method = "render",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I"
//            )
//    )
//    private int redirectTextWithShadow_int(DrawContext context, TextRenderer renderer, Text text, int x, int y, int color) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) {
//            return context.drawTextWithShadow(renderer, text, x, y, color);
//        }
//        return context.drawTextWithShadow(renderer, text, x, y + notchHeight, color);
//    }

    // for 1.21.6-10
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

//    @Inject(
//            method = "refreshWidgetPositions",
//            at = @At("TAIL")
//    )
//    private void adjustYPosition(CallbackInfo ci) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return;
//
//        this.searchBox.setY(this.searchBox.getY() + notchHeight);
//        this.playerList.setY(this.playerList.getY() + notchHeight);
//        this.playerList.setHeight((this.playerList.getHeight() - notchHeight));
//
//        this.blockingButton.setY(this.blockingButton.getY() + notchHeight);
//    }

//    @ModifyArg(
//            method = "init",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;dimensions(IIII)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;"
//            ),
//            index = 1
//    )
//    private int adjustTabButtons(int originalY) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return originalY;
//        return originalY + notchHeight;
//    }

//    @Redirect(
//            method = "init()V",
//            at = @At(
//                    value = "NEW",
//                    target = "(Lnet/minecraft/client/gui/screen/multiplayer/SocialInteractionsScreen;Lnet/minecraft/client/MinecraftClient;IIII)Lnet/minecraft/client/gui/screen/multiplayer/SocialInteractionsPlayerListWidget;"
//            )
//    )
//    private SocialInteractionsPlayerListWidget redirectSocialIntPlayerListWidget(
//            SocialInteractionsScreen parent, MinecraftClient client, int width, int height, int y, int itemHeight
//    ) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1)
//            return new SocialInteractionsPlayerListWidget(parent, client, width, height, y, itemHeight);
//        int newHeight = height - notchHeight;
//        int newY = y + notchHeight;
//        return new SocialInteractionsPlayerListWidget(parent, client, width, newHeight, newY, itemHeight);
//    }

//    @Shadow private ButtonWidget allTabButton;
//    @Shadow private ButtonWidget hiddenTabButton;
//    @Shadow private ButtonWidget blockedTabButton;
//
//    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
//    private void shiftWidgetYPositions(CallbackInfo ci) {
//        int notchHeight = getScaledNotchHeight();
//        if (notchHeight == -1) return;
//
//        // Adjust each widget's Y position
//        this.searchBox.setY(this.searchBox.getY() + notchHeight);
//        this.playerList.setY(this.playerList.getY() + notchHeight);
//        this.playerList.setHeight(this.playerList.getHeight() - notchHeight);
//        this.allTabButton.setY(this.allTabButton.getY() + notchHeight);
//        this.hiddenTabButton.setY(this.hiddenTabButton.getY() + notchHeight);
//        this.blockedTabButton.setY(this.blockedTabButton.getY() + notchHeight);
//        this.blockingButton.setY(this.blockingButton.getY() + notchHeight);
//    }
}
