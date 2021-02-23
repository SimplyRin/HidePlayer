package net.simplyrin.hideplayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by SimplyRin on 2021/02/23.
 *
 * Copyright (c) 2021 SimplyRin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class HidePlayer extends JavaPlugin implements CommandExecutor {

	@Override
	public void onEnable() {
		this.getCommand("hideplayer").setExecutor(this);
		this.getCommand("showplayer").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("hideplayer.command")) {
			sender.sendMessage("§cYou dont have permission!");
			return true;
		}

		if (!(sender instanceof Player)) {
			sender.sendMessage("§cIn-Game only!");
			return true;
		}

		Player player = (Player) sender;
		Player t = null;
		if (args.length > 0) {
			t = this.getServer().getPlayer(args[0]);
		}
		final Player target = t;

		if (target != null && command.getName().equalsIgnoreCase("hideplayer")) {
			if (args.length > 0) {
				this.getServer().getScheduler().runTask(this, () -> {
					player.hidePlayer(target);
				});
				player.sendMessage("§cYou hid " + target.getName());
				return true;
			}
		}
		if (target != null && command.getName().equalsIgnoreCase("showplayer")) {
			if (args.length > 0) {
				this.getServer().getScheduler().runTask(this, () -> {
					player.showPlayer(target);
				});
				player.sendMessage("§aYou show " + target.getName());
				return true;
			}
		}
		sender.sendMessage("§cUsage: /" + command.getName() + " <player>");
		return true;
	}

}
