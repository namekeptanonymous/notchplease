package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import org.nka.notchplease.mixin.client.gui.screen.ScreenMixin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(SelectWorldScreen.class)
public class SelectWorldScreenMixin extends ScreenMixin {
    protected SelectWorldScreenMixin(List<GuiEventListener> children) {
        super(children);
    }
    @Shadow
    protected EditBox searchBox;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void shiftElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        this.searchBox.setY(this.searchBox.getY() + notchHeight);
        for (GuiEventListener e : this.getChildren()) {
            if (e instanceof StringWidget strWdgt) {
                strWdgt.setY(strWdgt.getY() + notchHeight);
            }
            if (e instanceof WorldSelectionList list) {
                list.setY(list.getY() + notchHeight);
                list.setHeight(list.getHeight() - notchHeight);
                list.setScrollAmount(0);
            }
        }
    }
}
