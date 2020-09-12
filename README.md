# Do Not Track

DNT is a minecraft mod that stop a server its tracking of an individual minecraft client.

## How it disable tracking?

Many Popular Minecraft mod loader and server software changes its brand name. It send to server right after joining server.  
However some servers try to detect modification user's client with brand name(or forge mod list with fake forge handshaking). 
Server should not try to collect client information not related to gameplay. This mod will replace to "vanilla" every sent payload message to from "minecraft:brand" channel to server.

### Alternative for fake forge handshaking

[ModProtector 1.12.2](https://github.com/storycraft/mod-protector)