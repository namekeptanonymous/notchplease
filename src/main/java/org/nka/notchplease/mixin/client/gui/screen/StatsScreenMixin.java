package org.nka.notchplease.mixin.client.gui.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.tabs.TabNavigationBar;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.achievement.StatsScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(StatsScreen.class)
public class StatsScreenMixin {
    @Final
    @Shadow
    private HeaderAndFooterLayout layout;

    @Nullable
    @Unique
    private TabNavigationBar tabNavigationBar;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        this.layout.visitChildren((element) -> {
            if (!(element instanceof Button)) {
                element.setY(element.getY() + notchHeight);
            }
        });
//        if (this.tabNavigationBar != null) {
//            this.tabNavigationBar.setY(this.tabNavigationBar.getY() + notchHeight);
//            this.tabNavigationBar.setHeight(this.tabNavigationBar.getHeight() - notchHeight);
//            this.tabNavigationBar.setScrollAmount(0);
//        }
    }
}