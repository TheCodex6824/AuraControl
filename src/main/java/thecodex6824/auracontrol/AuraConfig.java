/**
 *  AuraControl
 *  Copyright (c) 2020 TheCodex6824.
 *
 *  This file is part of AuraControl.
 *
 *  AuraControl is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  AuraControl is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with AuraControl.  If not, see <https://www.gnu.org/licenses/>.
 */

package thecodex6824.auracontrol;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thecodex6824.auracontrol.api.AuraControlAPI;

@Config(modid = AuraControlAPI.MODID)
@EventBusSubscriber(modid = AuraControlAPI.MODID)
public final class AuraConfig {

    private AuraConfig() {}
    
    @Name("ListIsAllowList")
    @Comment({
        "Determines if the biome list specifies biomes that are allowed to have an Aura,",
        "instead of listing those that aren't."
    })
    @RequiresMcRestart
    public static boolean allowList = false;
    
    @Name("BiomeList")
    @Comment({
        "The list of biomes that will or will not have an Aura generated.",
        "Defaults to specifying the biomes to block, set ListIsAllowList to true to invert behavior.",
        "Enter the biomes as namespaced IDs, i.e. thaumcraft:magical_forest"
    })
    @RequiresMcRestart
    public static String[] biomeList = {};
    
    @Name("ControlCrystals")
    @Comment({
        "Whether the biome list should affect Vis Crystal generation, in addition to the aura."
    })
    @RequiresMcRestart
    public static boolean crystals = false;
    
    @Name("ControlTrees")
    @Comment({
        "Whether the biome list should affect Thaumcraft tree generation, in addition to the aura."
    })
    @RequiresMcRestart
    public static boolean trees = false;
    
    @SubscribeEvent
    public static void onConfigSync(OnConfigChangedEvent event) {
        if (event.getModID().equals(AuraControlAPI.MODID))
            ConfigManager.sync(AuraControlAPI.MODID, Type.INSTANCE);
    }
    
}
