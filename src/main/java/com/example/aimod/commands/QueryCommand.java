package com.example.aimod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class QueryCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        String query = context.getInput().replace("#query ", "");
        // Logique pour faire la requête API ici
        context.getSource().sendFeedback(() -> Text.literal("Requête API : " + query), false);
        return 1;
    }
}