package com.addyberry.blocksabound.core.registry;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BASounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, BlocksAbound.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> TAR_BUBBLE_POP = registerSound("particle.tar_bubble.pop");


    protected static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(BlocksAbound.location(name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
