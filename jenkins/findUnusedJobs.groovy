import jenkins.model.Jenkins

String jobNameRegexp = ".*"
Long maxThresholdDays = 60 // days
Date comparisonDate = new Date(new Date().getTime() - (maxThresholdDays * 24L * 60 * 60 * 1000))

def getLastBuildTime(Job job) {
    def lastBuild = job.getLastBuild()
    if (lastBuild == null) {

    } else {
        return lastBuild.getTime()
    }
}

def folders = Jenkins.getInstanceOrNull().items.findAll { job -> job.name =~ jobNameRegexp }
def oldJobs = []
def neverRunJobs = []
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