package org.nka.notchplease.mixin.client.gui.screens.inventory;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(BookViewScreen.class)
public class BookViewScreenMixin {
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