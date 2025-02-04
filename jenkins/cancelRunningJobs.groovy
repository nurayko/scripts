import com.cloudbees.hudson.plugins.folder.Folder
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject
import jenkins.model.Jenkins

String containedString = "EDIT THIS"
Closure filter = { it.name.contains(containedString) }
for (item in Jenkins.getInstanceOrNull().getItems().findAll(filter)) {
    stopJobs(item)
}

def stopJobs(item) {
    if (item in Folder) {
        for (child in item.items) {
            stopJobs(child)
        }
    } else if (item in WorkflowMultiBranchProject) {
        for (child in item.items) {
            stopJobs(child)
        }
    } else if (item in WorkflowJob) {

        if (item.isBuilding()) {
            for (build in item.builds) {
                println("killing build $build")
                build.doStop()
                build.doKill()
            }
        }
    }
}