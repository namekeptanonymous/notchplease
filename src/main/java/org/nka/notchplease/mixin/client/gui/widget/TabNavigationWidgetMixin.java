package org.nka.notchplease.mixin.client.gui.widget;

import net.minecraft.client.gui.components.tabs.TabNavigationBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(TabNavigationBar.class)
public class TabNavigationWidgetMixin {
    @ModifyArg(
            method = "arrangeElements",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/layouts/LinearLayout;setY(I)V"
            ),
            index = 0
    )
    private int modifyElementsY(int originalY) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return originalY;
        return originalY + notchHeight;
    }
}