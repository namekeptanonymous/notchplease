package org.nka.notchplease.mixin.client.gui.screens.inventory;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(BookEditScreen.class)
public class BookEditScreenMixin {
    @ModifyArg(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/components/MultiLineEditBox$Builder;setY(I)Lnet/minecraft/client/gui/components/MultiLineEditBox$Builder;"
            )
    )
    private int adjustBookTextBox(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }

    @ModifyReturnValue(
            method = "backgroundTop",
            at = @At("RETURN")
    )
    private int adjustGetTop(int original) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return original;
        return original + notchHeight;
    }
}