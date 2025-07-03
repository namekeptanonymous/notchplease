package org.nka.notchplease.mixin.client.gui.hud;

import net.minecraft.client.gui.hud.BossBarHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(value = BossBarHud.class)
public class BossBarHudMixin {
    // ideally wouldn't use ModifyConstant but no real option other than rewriting the loop (which
    // would be similarly fragile if future components are added above boss bar)
    // might rework later
    @ModifyConstant(
            method = "render(Lnet/minecraft/client/gui/DrawContext;)V",
            constant = @Constant(intValue = 12)
    )
    private int moveBossBarDown(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
}
