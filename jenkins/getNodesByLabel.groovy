import hudson.model.Hudson
import hudson.model.labels.LabelAtom
import jenkins.model.Jenkins
import hudson.model.Node

Jenkins instance = Jenkins.getInstanceOrNull()
LabelAtom targetLabel = new LabelAtom("scaled")
List<Node> nodes = []

instance.getNodes().each { Node node ->
    if (node.getAssignedLabels().contains(targetLabel)) {
        println("Node ${node.getDisplayName()} contains $targetLabel")
        nodes.add(node)
    }
}
println(nodes)