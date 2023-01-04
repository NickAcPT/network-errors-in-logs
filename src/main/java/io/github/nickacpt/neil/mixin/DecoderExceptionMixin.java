package io.github.nickacpt.neil.mixin;

import io.netty.handler.codec.DecoderException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DecoderException.class)
public class DecoderExceptionMixin {
	@Inject(at = @At("TAIL"), method = "<init>(Ljava/lang/String;)V", remap = false)
	private static void init(String message, CallbackInfo ci) {
		System.err.println("Decoder exception was thrown, here's a stacktrace:");
		new RuntimeException(message).printStackTrace();
	}
}
