package sh.pancake.dnt.mixin;

import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import sh.pancake.dnt.Mod;
import sh.pancake.dnt.util.reflect.Reflect;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.GenericFutureListener;

@Mixin(ClientConnection.class)
public abstract class BrandHideMixin {

	private static final Reflect.WrappedField<Identifier, CustomPayloadC2SPacket> CHANNEL_FIELD;

	static {
		CHANNEL_FIELD = Reflect.getField(CustomPayloadC2SPacket.class, "field_12830");
	}

	@Shadow
    public abstract void send(Packet<?> packet, GenericFutureListener<?> callback);

	@Inject(method = "send", at = @At("HEAD"), cancellable = true)
	public void onSend(Packet<?> packet, CallbackInfo info) {
		if (packet instanceof CustomPayloadC2SPacket) {
			CustomPayloadC2SPacket brandPacket = (CustomPayloadC2SPacket) packet;

			Identifier channel = CHANNEL_FIELD.get(brandPacket);

			if (CustomPayloadC2SPacket.BRAND.toString().equals(channel.toString())) {
				info.cancel();

				Mod.logger.info("Hiding brand name. original was ( " + ClientBrandRetriever.getClientModName() + " ) .");

				this.send(new CustomPayloadC2SPacket(CustomPayloadC2SPacket.BRAND, (new PacketByteBuf(Unpooled.buffer())).writeString("vanilla")), null);
			}
		}
	}
	
}