package me.quesia.peepopractice.mixin.access;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftClient.class)
public interface MinecraftClientAccessor {
    @Accessor("openProfilerSection") void peepoPractice$setOpenProfilerSection(String value);
}
