package org.nka.notchplease.mixin.client.gui.screen.option;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.options.LanguageSelectScreen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(LanguageSelectScreen.class)
public class LanguageOptionsScreenMixin extends GameOptionsScreenMixin {
    @Shadow @Nullable
    private EditBox search;

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void onRepositionElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;
        assert this.search != null;
        this.search.setY(this.search.getY() + notchHeight);
        this.layout.visitChildren((element) -> {
            if (element instanceof LinearLayout) {
                ((LinearLayout) element).visitChildren((child) -> {
                    if (child instanceof StringWidget && ((StringWidget) child).getMessage().contains(Component.nullToEmpty("Language"))) {
                        child.setY(child.getY() + notchHeight);
                    }
                });
            }
            if (element instanceof ObjectSelectionList<?> langList) {
                langList.setY(langList.getY() + notchHeight);
                langList.setHeight(langList.getHeight() - notchHeight);
                langList.setScrollAmount(0);
            }
        });
    }
}