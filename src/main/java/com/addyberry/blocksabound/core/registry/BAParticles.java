package com.addyberry.blocksabound.core.registry;

import com.addyberry.blocksabound.BlocksAbound;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BAParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, BlocksAbound.MODID);

    public static final Supplier<SimpleParticleType> TAR_BUBBLE = register("tar_bubble");

    public static Supplier<SimpleParticleType> register(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
