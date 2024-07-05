Thread.State state = Thread.State.BLOCKED
Set<Thread> threads = Thread.getAllStackTraces().keySet().findAll({ Thread thread ->
    (thread.getState() == state)
}
)

println("Threads in ${state.toString()} state :")
threads.each { Thread thread ->
    println(thread.name)
}

println("Done")