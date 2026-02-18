package com.example.aimod.mixins;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ChatMixin {
    @Inject(method = "sendMessage", at = @At("HEAD"))
    private void onSendMessage(Text message, CallbackInfo ci) {
        String msg = message.getString();
        if (msg.startsWith("#query ")) {
            String query = msg.replace("#query ", "");
            // Logique pour traiter la commande #query
            ((ServerPlayerEntity)(Object)this).sendMessage(Text.literal("Requête API : " + query));
        }
    }
}