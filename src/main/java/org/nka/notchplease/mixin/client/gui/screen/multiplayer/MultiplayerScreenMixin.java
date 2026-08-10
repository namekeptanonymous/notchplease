package org.nka.notchplease.mixin.client.gui.screen.multiplayer;

import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import net.minecraft.network.chat.Component;
import org.nka.notchplease.mixin.client.gui.screen.ScreenMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(JoinMultiplayerScreen.class)
public class MultiplayerScreenMixin extends ScreenMixin {
    protected MultiplayerScreenMixin(List<GuiEventListener> children) {
        super(children);
    }

    @Inject(method = "repositionElements", at = @At("TAIL"))
    private void shiftElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        for (GuiEventListener e : this.getChildren()) {
            if (e instanceof StringWidget strWdgt) {
                strWdgt.setY(strWdgt.getY() + notchHeight);
            }
            if (e instanceof ServerSelectionList list) {
                list.setY(list.getY() + notchHeight);
                list.setHeight(list.getHeight() - notchHeight);
                list.setScrollAmount(0);
            }
        }
    }
}
