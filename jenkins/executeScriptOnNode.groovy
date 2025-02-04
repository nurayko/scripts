import hudson.util.RemotingDiagnostics
import hudson.model.Node
import jenkins.model.Jenkins

String script = ""

List<Node> nodes = Jenkins.getInstanceOrNull().getNodes()
for (Node node : nodes) {
    if (node.toComputer().isOnline()) {
        println("Executing script on ${node.getNodeName()}")
        result = RemotingDiagnostics.executeGroovy(script, node.channel)
    }
}