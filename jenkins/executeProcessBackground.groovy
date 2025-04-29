File log = new File("/tmp/process1.log");

List<String> cmds = [
        "command",
        "arg",
        "arg2"
]
ProcessBuilder pb =
        new ProcessBuilder(cmds)
pb.redirectErrorStream(true)
pb.redirectOutput(ProcessBuilder.Redirect.appendTo(log))
Process p = pb.start()