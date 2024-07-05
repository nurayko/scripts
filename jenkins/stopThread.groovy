String threadToStop = ""

Set<Thread> threads = Thread.getAllStackTraces().keySet().findAll({ Thread thread ->
    thread.name.contains(threadToStop)
}
)

threads.each { Thread thread ->
    if (thread.name.contains(threadToStop)) {
        println("Stopping thread - ${thread.name}")
        thread.interrupt()
    }
}