package li.cil.scannable.mixin.client;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRenderer.class)
public interface GameRendererInvoker {
    @Invoker("renderItemInHand")
    void scannable$renderItemInHand(Camera camera, float partialTicks, Matrix4f modelViewMatrix);
}
