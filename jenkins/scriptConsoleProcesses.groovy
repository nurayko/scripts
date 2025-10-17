Process execProcess(Map m = [:]) {
    Boolean wait = m.wait
    List<String> cmd = m.cmd
    ProcessBuilder pb =
            new ProcessBuilder(cmd)
    pb.redirectErrorStream(true)
    Process p = pb.start()
    println("$p started successfully.")
    if (wait) {
        println("Waiting for it to finish")
        p.waitFor()
    }
    return p
}


Process result = execProcess(
        cmd: ['/bin/bash',
              '-c',
              'ls -lha'],
        wait: true
)

println("Exit code of process: ${result.exitValue()}")
println("Output: ${result.getText()}")