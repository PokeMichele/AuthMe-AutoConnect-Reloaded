# AuthMe AutoConnect Reloaded
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) ![Environment-Server](https://img.shields.io/badge/environment-server-orangered?style=flat-square) [![License: GPL v3](https://img.shields.io/badge/License-GPL_v3-orange.svg)](https://www.gnu.org/licenses/old-licenses/gpl-3.0.en.html)
AuthMe AutoConnect Reloaded is a Plugin that send the players to the Lobby after they have logged in.

## Use Cases
This Plugin can be useful if you want to setup an AuthLobby or a Limbo where the player joins before connecting to the Main Lobby.
This Reloaded version of the old Plugin also adds an on-screen countdown, that you can set up in the config file, that can be useful if you need to give time to the players to protect their account with a Premium Authentication before being sent to the Main Lobby.

## Usage
These are the customizable options that you can find int the "config.yml" file:

    #The server to be sent.
    lobby-server: Lobby
    #Delay before sending players to lobby (in seconds).
    send-delay: 2
    #Customizable Countdown Titles
    titleText: "&e&lConnecting to the Server..."
    subtitleText: "&e&lPlease wait"

Remember that the subtitle format is always: subtitleText + [Remaining Seconds] + seconds...

## Credits & License
AuthMe AutoConnect Reloaded is a fork of [AuthMe AutoConnect](https://github.com/Martiii11/AuthMe-AutoConnect) by [Martiii11](https://github.com/Martiii11) released under [GPLv3 License](https://www.gnu.org/licenses/old-licenses/gpl-3.0.en.html), respecting the limitations imposed by the license itselt, under which [AuthMe](https://github.com/AuthMe/AuthMeReloaded/blob/master/LICENSE) was also released.