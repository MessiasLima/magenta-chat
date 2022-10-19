# Magenta Chat

Magenta chat is a simple application that simulates a chat feature between 2 users.

![Video](docs/video.mp4)

## Project decisions

### 📚 Used libraries and frameworks
- [_Jetpack Compose_](https://developer.android.com/jetpack/compose) for construct the interfaces
- [_Hilt_](https://dagger.dev/hilt/) for dependency injection 
- [_Room_](https://developer.android.com/training/data-storage/room) for data persistence
- [_Kotlin Coroutines_](https://kotlinlang.org/docs/coroutines-overview.html) for managing asynchronous tasks and data flows
- [_JUnit_](https://junit.org/junit4/) for test setup and assertions
- [_Mockk_](https://mockk.io/) for creating mock objects for testing
- [_Kotlin fixture_](https://github.com/appmattus/kotlinfixture) for creating dummy objects for testing

### I decided to follow conventional commits convention

Conventional commits is an specification of how we should write commit messages in order to make it
more readable and organized. It also make possible automatic changelog creation. The full
documentation can be found [here](https://www.conventionalcommits.org/en/v1.0.0/). In a normal
situation I would setup some gradle plugins
like [this commit lint plugin](https://plugins.gradle.org/plugin/ru.netris.commitlint)
and [this git hook plugin](https://plugins.gradle.org/plugin/com.star-zero.gradle.githook) to ensure
that everyone is following this pattern. I decided not to do it in this project to keep it simpler.
