// Delete jobs based on regex pattern


import com.cloudbees.hudson.plugins.folder.Folder
import hudson.model.Item
import hudson.model.TopLevelItem
import jenkins.model.Jenkins

expression = /regex/
dryRun = true

def deleteJobs() {
    Jenkins instance = Jenkins.getInstanceOrNull()
    if(dryRun)
        println("dryRun set to true , will only print")
    instance.getItems().each {
        TopLevelItem item ->
            if (item instanceof Folder) {
                processFolder(item)
            } else {
                processJob(item)
            }
    }
    println("Done")
}

def processJob(Item job) {
    if (job.name ==~ expression) {
        println "Deleting ${job.name}"
        if(!dryRun)
            job.delete()
    }
}

def processFolder(Folder folder) {
    folder.getItems().each { TopLevelItem item ->
        if (item instanceof Folder) {
            processFolder(item)
        } else {
            processJob(item)
        }
    }
}


deleteJobs()
