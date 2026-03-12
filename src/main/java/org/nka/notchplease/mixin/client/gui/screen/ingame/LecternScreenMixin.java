package org.nka.notchplease.mixin.client.gui.screen.ingame;

import net.minecraft.client.gui.screen.ingame.LecternScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(LecternScreen.class)
public class LecternScreenMixin {
//    @ModifyArg(
//            method = "addCloseButton",
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
}