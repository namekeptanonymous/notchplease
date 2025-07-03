package org.nka.notchplease.mixin.client.gui.screen;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.*;

import java.util.List;

@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Mutable
    @Shadow @Final private final List<Element> children;

    protected ScreenMixin(List<Element> children) {
        this.children = children;
    }

    @Unique
    public List<Element> getChildren() {
        return this.children;
    }
}
