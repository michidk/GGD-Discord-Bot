# GGD-Bot
A bot for the German Game Developers Discord

## Discord
If you want to talk with us about game developer things (in german!), join our Discord [here](https://discord.gg/k2XWWKZ)!

## Contribute
Feel free to contribute.

## Building
Just open the project with IntelliJ and run the gradle build configuration. Thanks to gradle, you don't have to download any dependencies. You just need JRE 8 and Gradle.

## Running
1. Create a discord application [here](https://discordapp.com/developers/applications/me)
2. Click on "Create a Bot User"
3. Create a token.txt in the resources folder, paste the Bot User token into this file
4. Set the variable "GGD_ID" in Main.java to the ID of your testing Discord server [--> How do I find the ID?](https://support.discordapp.com/hc/en-us/articles/206346498-Where-can-I-find-my-User-Server-Message-ID-)
5. Open the link `https://discordapp.com/oauth2/authorize?&client_id=YOUR_CLIENT_ID_HERE&scope=bot&permissions=0` with your bot users CLIENT_ID filled in, in order to add the bot to your testing server
6. Run the Main Class

## Depdendencies
- [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle](https://gradle.org/)

### Gradle Dependencies
- [SLF4J Simple](https://www.slf4j.org/api/org/slf4j/impl/SimpleLogger.html)
- [Guava](https://github.com/google/guava)
- [Gson](https://github.com/google/gson)
- [JDA](https://github.com/DV8FromTheWorld/JDA/)
- [JDA-Utilities](https://github.com/JDA-Applications/JDA-Utilities)

