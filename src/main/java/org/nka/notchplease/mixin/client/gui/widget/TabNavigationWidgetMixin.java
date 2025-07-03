package org.nka.notchplease.mixin.client.gui.widget;

import net.minecraft.client.gui.widget.TabNavigationWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(TabNavigationWidget.class)
public class TabNavigationWidgetMixin {

    // Intercept the call to grid.setY(0) in the init() method
    @ModifyArg(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/widget/DirectionalLayoutWidget;setY(I)V"
            ),
            index = 0
    )
    private int modifyGridY(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
}