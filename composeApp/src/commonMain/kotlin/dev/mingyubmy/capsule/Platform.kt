package dev.mingyubmy.capsule

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform