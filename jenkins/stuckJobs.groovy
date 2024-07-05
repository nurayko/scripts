import hudson.model.Computer
import hudson.model.Executor
import jenkins.model.Jenkins

int stuckJobs = 0

List busyExecutors = Jenkins.getInstanceOrNull().computers.collect { Computer it ->
    it.executors.findAll { Executor executor ->
        executor.isBusy()
    }
}.flatten()

busyExecutors.each { Executor executor ->
    if (executor.getElapsedTime() > 24 * 60 * 60 * 1000) {
        println("Job " + executor.getCurrentWorkUnit().work.getDisplayName() + " has been running on " + executor.getOwner().nodeName + " for " + executor.getTimestampString())
        executor.interrupt()
        stuckJobs = stuckJobs + 1
    }
}
println("Number of stuck jobs - $stuckJobs")