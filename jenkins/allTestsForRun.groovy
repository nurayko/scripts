import hudson.model.Job
import hudson.model.Run
import hudson.model.TopLevelItem
import hudson.tasks.junit.CaseResult
import hudson.tasks.junit.TestResultAction
import jenkins.model.Jenkins

String jobName = ""
Integer buildNumber = 747

Jenkins instance = Jenkins.getInstanceOrNull()
Job job = instance.getItem(jobName) as Job
Run build = job.getBuildByNumber(buildNumber)

List tests = []
TestResultAction action = build.getAction(TestResultAction.class)

if (action) {
    List<CaseResult> allTests = action.getPassedTests() + action.getFailedTests()
    println("Test Result: Ran: ${action.getTotalCount()} Error: ${action.getFailCount()} Skipped: ${action.getSkipCount()}")
    for (CaseResult failure in allTests) {
        tests.add([
                'name'       : failure.name,
                'status'     : failure.status,
                'duration'   : failure.duration,
                'failedSince': failure.failedSince,
                'className'  : failure.className,
                'url'        : failure.url,
                jobName      : jobName,
                jobRun       : buildNumber
        ])
    }
}

println(tests)