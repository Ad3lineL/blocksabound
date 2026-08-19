package com.addyberry.blocksabound.client.events;

import com.addyberry.blocksabound.BlocksAbound;
import com.addyberry.blocksabound.client.particle.TarBubbleParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import static com.addyberry.blocksabound.core.registry.BAParticles.*;

@EventBusSubscriber(modid = BlocksAbound.MODID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(TAR_BUBBLE.get(), TarBubbleParticle.Provider::new);
    }
}
