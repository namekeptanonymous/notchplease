package org.nka.notchplease.mixin.client.gui.components;

import net.minecraft.client.gui.components.BossHealthOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(value = BossHealthOverlay.class)
public class BossHealthOverlayMixin {
    // ideally wouldn't use ModifyConstant but no real option other than rewriting the loop (which
    // would be similarly fragile if future components are added above boss bar)
    // might rework later
    @ModifyConstant(
            method = "extractRenderState",
            constant = @Constant(intValue = 12)
    )
    private int moveBossBarDown(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
}
