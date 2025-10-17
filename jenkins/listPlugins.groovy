import jenkins.model.Jenkins
import hudson.PluginWrapper

println("Installed plugins")
Jenkins.getInstanceOrNull().pluginManager.plugins.each{
    PluginWrapper plugin ->
        println ("${plugin.getShortName()}:${plugin.getVersion()}")
}
return