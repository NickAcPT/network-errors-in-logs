package io.github.nickacpt.neil.mixin;

import io.netty.handler.codec.DecoderException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DecoderException.class)
public class DecoderExceptionMixin {
	@Inject(at = @At("RETURN"), method = "<init>(Ljava/lang/String;)V", remap = false)
	private void init(String message, CallbackInfo ci) {
		System.err.println("Decoder exception was thrown (with message), here's a stacktrace:");
		new RuntimeException("Decoder exception: " + message).printStackTrace();
	}

	@Inject(at = @At("RETURN"), method = "<init>()V", remap = false)
	private void init(CallbackInfo ci) {
		System.err.println("Decoder exception was thrown (without message), here's a stacktrace:");
		new RuntimeException("Decoder exception").printStackTrace();
	}

	@Inject(at = @At("RETURN"), method = "<init>(Ljava/lang/Throwable;)V", remap = false)
	private void init(Throwable throwable, CallbackInfo ci) {
		System.err.println("Decoder exception was thrown (with exception), here's a stacktrace:");
		new RuntimeException("Decoder exception", throwable).printStackTrace();
	}

	@Inject(at = @At("RETURN"), method = "<init>(Ljava/lang/String;Ljava/lang/Throwable;)V", remap = false)
	private void init(String message, Throwable throwable, CallbackInfo ci) {
		System.err.println("Decoder exception was thrown (with message and exception), here's a stacktrace:");
		new RuntimeException("Decoder exception: " + message, throwable).printStackTrace();
	}
}
