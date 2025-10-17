import jenkins.model.Jenkins

String jobName = ""

Jenkins instance = Jenkins.getInstanceOrNull()
def item = instance.getItemByFullName(jobName)
item.logRotator.perform(item)