/*
 * Copyright (C) 2017 Mindgamesnl
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package net.openaudiomc.commands.player;

import me.mindgamesnl.openaudiomc.publicApi.OpenAudioApi;
import net.openaudiomc.core.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VolumeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (OpenAudioApi.isConnected((Player) sender)) {
                if (args.length > 0) {
                    if (args[0].chars().allMatch(Character::isDigit) && (Integer.parseInt(args[0]) <= 100 && Integer.parseInt(args[0]) > -1)) {
                        sender.sendMessage(Main.getFormattedMessage(Main.get().getWebConfig().getVolumeSet().replace("{0}", args[0])));
                        net.openaudiomc.actions.Command.setVolume(sender.getName(), args[0]);
                        return true;
                    } else {
                        sender.sendMessage(Main.getFormattedMessage(Main.get().getWebConfig().getVolumeError()));
                        return true;
                    }
                } else {
                    sender.sendMessage(Main.getFormattedMessage(Main.get().getWebConfig().getVolumeError()));
                    return true;
                }
            } else {
                sender.sendMessage(Main.getFormattedMessage(Main.get().getWebConfig().getNeedConnected()));
                return true;
            }
        } else {
            //error(sender, AdminCommands.NO_PLAYER_INSTANCE);
            return true;
        }
    }
}