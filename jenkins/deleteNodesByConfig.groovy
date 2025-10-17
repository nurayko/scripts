import hudson.model.Hudson
import hudson.model.labels.LabelAtom
import jenkins.model.Jenkins
import hudson.model.Node

Jenkins instance = Jenkins.getInstanceOrNull()
Map nodesMapping = [
        "v1-epc"     : [
                label: new LabelAtom("v1-epc"),
                nodes: [],
        ],
        "v1-epc-vdc2": [
                label: new LabelAtom("v1-epc-vdc2"),
                nodes: [],
        ],
        "v1-epc-vdc3": [
                label: new LabelAtom("v1-epc-vdc3"),
                nodes: [],
        ],
]
List<Node> instanceNodes = instance.getNodes()

nodesMapping.each { name, mapping ->
    instanceNodes.findAll()
    mapping.nodes = instanceNodes.findAll {
        it.getAssignedLabels().contains(mapping.label)
    }
}

nodesMapping.each { name, mapping ->
    println("$name has nodes:")
    println(mapping.nodes)
    // uncomment to delete nodes
//    deleteOfflineNodes(mapping.nodes)
}

// delete nodes from jenkins
def deleteOfflineNodes(List<Node> nodes) {
    for (Node node : nodes) {
        if (node.toComputer().isOffline()) {
            println("Deleting node ${node.getDisplayName()}")
            node.toComputer().doDoDelete()
        }
    }
}
