package org.nka.notchplease.mixin.client.gui.screen.world;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.screen.world.WorldListWidget;
import net.minecraft.client.gui.widget.LayoutWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
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
    @Shadow
    protected TextFieldWidget searchBox;

    protected SelectWorldScreenMixin(List<Element> children) {
        super(children);
    }

    @Unique
    @Final
    private LayoutWidget layout;

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void shiftElements(CallbackInfo ci) {
        int notchHeight = getScaledNotchHeight();
        if (notchHeight == -1) return;

        this.searchBox.setY(this.searchBox.getY() + notchHeight);
        for (Element e : this.getChildren()) {
            if (e instanceof TextWidget tw) {
                tw.setY(tw.getY() + notchHeight);
            }
            if (e instanceof WorldListWidget list) {
                list.setY(list.getY() + notchHeight);
                list.setHeight(list.getHeight() - notchHeight);
                list.setScrollY(0);
            }
        }
    }
}
