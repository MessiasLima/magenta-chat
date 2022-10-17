# Magenta Chat
Sample chat application

## Project decisions
Here, I will list and explain why I did (or didn't) some stuff in this project

### I decided to follow conventional commits convention
Conventional commits is an specification of how we should write commit messages in order to make it more readable and organized. It also make possible automatic changelog creation. The full documentation can be found [here](https://www.conventionalcommits.org/en/v1.0.0/).
In a normal situation I would setup some gradle plugins like [this commit lint plugin](https://plugins.gradle.org/plugin/ru.netris.commitlint) and [this git hook plugin](https://plugins.gradle.org/plugin/com.star-zero.gradle.githook) to ensure that everyone is following this pattern. I decided not to do it in this project to keep it simpler.
