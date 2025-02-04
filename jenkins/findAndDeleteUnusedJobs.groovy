import jenkins.model.Jenkins
import com.cloudbees.hudson.plugins.folder.Folder
import hudson.model.Item
import hudson.model.TopLevelItem
import jenkins.model.Jenkins


expression = /regex/
dryRun = true

Long maxThresholdDays = 60 // days
Date comparisonDate = new Date(new Date().getTime() - (maxThresholdDays * 24L * 60 * 60 * 1000))

def folders = Jenkins.getInstanceOrNull().items.findAll { job -> job.name =~ expression }
List oldJobs = []
List neverRunJobs = []
folders.each {
    for (job in it.getAllJobs()) {
        if (job != null) { // && job.buildable
            def lastBuildTime = job.getLastBuild()?.getTime()
            if (lastBuildTime == null) {
                neverRunJobs.add(job)
            } else if (lastBuildTime.before(comparisonDate)) {
                oldJobs.add(job)
            }
        }
    }
}
String delimiter = "============================================\n"

oldJobs.sort { it.getLastBuild()?.getTime() }
println(delimiter)
println "Jobs not built since $maxThresholdDays number of days. Total: $oldJobs.size"
oldJobs.each {
    println "'${it.getFullName()}' - ${it.getLastBuild()?.getTime()}, disabled = ${!it.buildable}"
}
println(delimiter)
println "Jobs never built. Total: $neverRunJobs.size"
neverRunJobs.each {
    println "'${it.getFullName()}' disabled = ${!it.buildable}"
}
println(delimiter)
deleteJobs(neverRunJobs)

def deleteJobs(items) {
    if (dryRun)
        return
    println("Deleting items:")
    items.each { println(it.getFullName()) }
    items.each { item ->
        item.delete()
    }
    println("Done")
}
