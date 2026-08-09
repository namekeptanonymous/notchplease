package org.nka.notchplease.mixin.client.gui.screen.multiplayer;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerServerListWidget;
import net.minecraft.client.gui.widget.TextWidget;
import org.nka.notchplease.mixin.client.gui.screen.ScreenMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static org.nka.notchplease.Notchplease.getScaledNotchHeight;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin extends ScreenMixin {
    protected MultiplayerScreenMixin(List<Element> children) {
        super(children);
    }

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void shiftElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        for (Element e : this.getChildren()) {
            if (e instanceof TextWidget tw) {
                tw.setY(tw.getY() + notchHeight);
            }
            if (e instanceof MultiplayerServerListWidget list) {
                list.setY(list.getY() + notchHeight);
                list.setHeight(list.getHeight() - notchHeight);
                list.setScrollY(0);
            }
        }
    }
}
