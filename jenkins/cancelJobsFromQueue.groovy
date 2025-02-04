import hudson.model.*
import jenkins.model.Jenkins

String containedString = "Edit This"
Queue queue = Jenkins.instance.queue
queue.items.findAll { Queue.Item item ->
    if (item.task.name.contains(containedString)) {
        println("Cancelling job ${item.task.name}")
        queue.cancel(item.task)
    }
}
