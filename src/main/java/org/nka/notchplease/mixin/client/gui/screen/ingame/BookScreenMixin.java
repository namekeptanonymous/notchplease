package org.nka.notchplease.mixin.client.gui.screen.ingame;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.gui.screen.ingame.BookScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(BookScreen.class)
public class BookScreenMixin {
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