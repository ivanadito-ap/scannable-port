package li.cil.scannable.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import li.cil.scannable.client.renderer.ScannerRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LightTexture;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Inject(
        method = "renderLevel",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/OutlineBufferSource;endOutlineBatch()V",
            shift = At.Shift.BEFORE
        )
    )
    private void renderLevel(
        final float tickDelta,
        final long nanos,
        final boolean shouldRenderBlockOutline,
        final Camera camera,
        final GameRenderer gameRenderer,
        final LightTexture lightTexture,
        final Matrix4f frustumMatrix,
        final Matrix4f projectionMatrix,
        final CallbackInfo ci
    ) {
        final PoseStack poseStack = new PoseStack();
        poseStack.last().pose().set(frustumMatrix);

        final Matrix3f normal = new Matrix3f(frustumMatrix);
        normal.invert().transpose();
        poseStack.last().normal().set(normal);

        ScannerRenderer.render(poseStack);
    }
}
