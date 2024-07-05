import hudson.model.Result
import jenkins.model.Jenkins

String jobName = ""
String branch = ""
String buildNumber = ""

if (buildNumber =~ /![0-9]/ || buildNumber.isEmpty()) {
    println("buildNumber contains non-digit symbols or is empty")
    return
}

if (jobName.isEmpty()) {
    println("jobName is empty")
    return
}

if (!branch.isEmpty()) {
    branch = branch.replaceAll("/", "%2F")
    jobName = "$jobName/$branch"
}
build = Jenkins.getInstanceOrNull()
        .getItemByFullName(jobName)
        .getBuildByNumber(buildNumber as int)
if (build == null) {
    println("Could not find build for job $jobName and build number $buildNumber")
}
build.doStop()
build.doKill()
build.finish(Result.ABORTED,
        new IOException("Build has been aborted"))
