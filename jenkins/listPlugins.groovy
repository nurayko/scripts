import jenkins.model.Jenkins
import hudson.PluginWrapper

println("Installed plugins")
Jenkins.getInstanceOrNull().pluginManager.plugins.each{
    PluginWrapper plugin ->
        println ("Plugin ${plugin.getDisplayName()} with id and version - ${plugin.getShortName()}:${plugin.getVersion()}")
}