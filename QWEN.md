# MC-Plugin Project Context

## Project Overview
MC-Plugin is a comprehensive Minecraft plugin designed for Paper/Purpur servers (version 1.21.11+) that adds advanced mechanics and systems to enhance gameplay. The plugin is written in Java 21 and uses the Gradle build system.

### Key Features
- **Energy System**: Complete energy generation, storage, and transmission system with generators, cables, batteries, and energy workbenches
- **Cable Networks**: Advanced power distribution network with visual feedback and loss calculations
- **Code Panels**: Interactive security panels with customizable codes and commands
- **Custom Crafting**: Energy-based crafting system requiring connection to power networks
- **Database Integration**: SQLite-based data persistence for plugin state
- **Datapack Integration**: Custom datapacks for advancements, loot tables, recipes, and world generation
- **Advanced Mechanics**: Custom damage types, dimension changes, block tags, and world generation features

## Project Structure
```
C:\MC-Plugin\
├── build.gradle          # Gradle build configuration
├── setting.gradle        # Gradle project settings
├── gradlew/gradlew.bat   # Gradle wrapper scripts
├── src/
│   └── main/
│       ├── java/com/mcplugin/  # Main plugin source code
│       │   ├── Main.java      # Plugin entry point
│       │   ├── cable/         # Cable network system
│       │   ├── commands/      # Plugin commands
│       │   ├── cp/           # Code panel system
│       │   ├── crafting/      # Custom crafting recipes
│       │   ├── database/      # SQLite database management
│       │   ├── energy/       # Energy system components
│       │   ├── listeners/     # Event listeners
│       │   ├── server/       # Server-related functionality
│       │   ├── tasks/        # Background tasks
│       │   └── util/         # Utility classes
│       └── resources/
│           ├── plugin.yml      # Plugin metadata and commands
│           ├── config.yml     # Plugin configuration
│           └── datapacks/     # Minecraft datapack files
└── target/                 # Build output directory
```

## Building and Running

### Prerequisites
- Java 21 or higher
- Paper/Purpur server version 1.21.11 or higher (not Spigot/Bukkit)

### Build Commands
```bash
# Build the plugin
./gradlew build

# Generate development JAR with obfuscation
./gradlew assemble

# Clean build artifacts
./gradlew clean
```

### Running/Installation
1. Build the plugin using `./gradlew build`
2. Copy the generated JAR file from `build/libs/` to your server's plugins folder
3. Start/restart the server
4. The plugin will automatically install its datapack to the first world's datapacks directory

### Development Setup
- Use an IDE with Java 21 support
- The Paperweight Gradle plugin provides Paper development libraries
- Debugging is supported through standard Java debugging mechanisms

## Configuration

### Plugin Configuration (`config.yml`)
The plugin uses a comprehensive configuration file with sections for:
- **Energy System**: Generator settings, cable loss, battery drain, energy balancer
- **Cable Networks**: Save intervals and network management
- **Code Panel System**: Security panel settings, codes, and messages
- **Energy Crafting**: Crafting requirements, energy consumption, and recipes
- **Custom Crafts**: Custom recipe definitions and materials

### Commands
- `/cp` - Open code panel interface
- `/cp_click` - Internal code panel interaction command
- `/mcplugin reload` - Reload plugin configuration (requires permission)

### Permissions
- `mcplugin.reload` - Allows plugin reloading (defaults to op)

## Development Conventions

### Code Structure
- Main plugin class extends `JavaPlugin`
- Event listeners registered in `onEnable()`
- Database operations use SQLite with connection pooling
- Background tasks use Bukkit's scheduler system
- Plugin includes automatic datapack installation and management

### Key Systems
1. **Energy System**: Complex energy flow management with generators, cables, and storage
2. **Cable Networks**: Visual representation of power distribution with loss calculations
3. **Code Panels**: Security system with customizable codes and commands
4. **Database**: SQLite persistence for plugin state and configuration
5. **Tasks**: Scheduled tasks for energy generation, cable loss, and visual updates

### Testing and Quality
- Plugin includes comprehensive error handling with detailed logging
- Graceful shutdown procedures for proper resource cleanup
- Configuration validation with helpful error messages
- Automatic datapack installation and management

### Plugin Lifecycle
- `onEnable()`: Initialize database, systems, events, and tasks
- `onDisable()`: Stop tasks, save networks, and close database connections
- Plugin reload support through command and configuration management

## Additional Information
- Author: MrCotik337
- License: GNU AGPL v3
- Support: Discord @error404_user.not.found
- GitHub: https://github.com/Minecraft337