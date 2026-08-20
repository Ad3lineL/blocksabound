package com.addyberry.blocksabound.client.particle;

import com.addyberry.blocksabound.core.registry.BASounds;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.Nullable;

public class TarBubbleParticle extends TextureSheetParticle {

    protected TarBubbleParticle(ClientLevel level, SpriteSet spriteSet, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.pickSprite(spriteSet);
        this.lifetime = (int) (40 * (Math.random() + 1.5));
        this.friction = 0.8F;
        this.gravity = 0.0005F;
        this.quadSize = 0.2F;
    }

    @Override
    public void tick() {
        if (this.age + 3 >= this.lifetime) {
            this.move(0, -0.005, 0);
        } else if (this.age + 6 >= this.lifetime) {
            this.move(0, -0.002, 0);
        }
        if (this.age++ >= this.lifetime) {
            if (this.random.nextInt(12) == 0)
                this.level.playLocalSound(this.x, this.y, this.z, BASounds.TAR_BUBBLE_POP.get(), SoundSource.AMBIENT, 0.1F + (float) Math.random()/4F, this.random.nextFloat()+0.4F, true);
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
