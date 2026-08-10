package org.nka.notchplease.mixin.client.gui.components;

import net.minecraft.client.gui.components.SplashRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(SplashRenderer.class)
public class SplashRendererMixin {
    @ModifyArg(
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/joml/Matrix3x2f;translate(FF)Lorg/joml/Matrix3x2f;"
            ),
            index = 1
    )
    private float modifyTranslateY(float originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
}
