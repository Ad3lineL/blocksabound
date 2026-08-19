package com.addyberry.blocksabound.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class TarBubbleParticle extends TextureSheetParticle {

    protected TarBubbleParticle(ClientLevel level, SpriteSet spriteSet, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.pickSprite(spriteSet);
        this.setSize(1f, 1f);
        this.lifetime = (int) (20);
        this.friction = 0.8F;
    }

    @Override
    public void tick() {
        this.gravity = (float) ((float) this.age / 100 + 0.02);
        if (this.age++ >= this.lifetime) {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new TarBubbleParticle(level, this.sprite, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
