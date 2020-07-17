# AuraControl
AuraControl is a small addon mod for Thaumcraft 6 to control Aura generation by biome. It is targeted towards modpack authors or advanced players that want to customize their Thaumcraft experience by limiting the aura to certain biomes or dimensions.

# Getting Started
By default, this mod does (almost) nothing. To make it start controlling Aura generation, you need to edit the config. There are two options - one determines if AuraControl will block specified biomes, or block biomes *other than* those specified. The other is the list of biomes to block or allow. The following is an example config:
```
# Configuration file

general {
    # The list of biomes that will or will not have an Aura generated.
    # Defaults to specifying the biomes to block, set ListIsAllowList to true to invert behavior.
    # Enter the biomes as namespaced IDs, i.e. thaumcraft:magical_forest
    S:BiomeList <
        thaumcraft:magical_forest
     >

    # Determines if the biome list specifies biomes that are allowed to have an Aura,
    # instead of listing those that aren't.
    B:ListIsAllowList=true
}
```
This example config will only allow the Aura to be generated in Magical Forest biomes. As the instructions say, all biomes must be entered as namespaced IDs, including vanilla biomes. This means you should write `minecraft:ocean` instead of just `ocean`, for example.

Lastly, the biome at the center of each chunk is what determines if the Aura is allowed to generate or not. This means that some biome edges may appear to have the incorrect aura, but this is a limitation of the chunk-based Aura system.
