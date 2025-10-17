import hudson.util.RemotingDiagnostics
import jenkins.model.Jenkins

String script = '''
"ls /tmp".execute().text
'''


String result

Jenkins.getInstanceOrNull().nodes.findAll { node ->
    node.isAcceptingTasks()
}.each { node ->
    result = RemotingDiagnostics.executeGroovy(script,node.channel)
    println("Result from script: ${node.nodeName}")
    println(result)
}
