File log = new File("process1.log");
File dir = new File("/tmp")

List<String> cmds = [
        "command",
        "arg",
        "arg2"
]
ProcessBuilder pb =
        new ProcessBuilder(cmds)
pb.redirectErrorStream(true)
pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log))
pb.directory(dir)
Process p = pb.start()