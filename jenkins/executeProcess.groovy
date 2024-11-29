StringBuilder stdOut = new StringBuilder()
StringBuilder stdErr = new StringBuilder()
File dir = new File("/tmp")
List envProperties = []
Process proc = 'command'.execute(envProperties,dir)
proc.consumeProcessOutput(stdOut, stdErr)
proc.waitFor()
println "out> $stdOut\nerr> $stdErr"