import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPassword
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey
import com.cloudbees.plugins.credentials.Credentials
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl
import groovy.transform.Field
import org.jenkinsci.plugins.plaincredentials.impl.FileCredentialsImpl
import org.jenkinsci.plugins.plaincredentials.impl.StringCredentialsImpl

@Field
SystemCredentialsProvider systemCredentialsProvider = SystemCredentialsProvider.getInstance()
@Field
List<Credentials> credentials = systemCredentialsProvider.getCredentials()

def printAllCredentials() {
    credentials.each { Credentials credential ->
        if (credential instanceof BasicSSHUserPrivateKey) {
            println("Private key of secret: ${credential.id}")
            println(credential.privateKey)
        }
        if (credential instanceof StringCredentialsImpl) {
            println("String secret: ${credential.id}")
            println(credential.secret)
        }
        if (credential instanceof BasicSSHUserPassword || credential instanceof UsernamePasswordCredentialsImpl) {
            println("User-Password secret: ${credential.id}")
            println("Username: $credential.username")
            println("Password: $credential.password")
        }
        if (credential instanceof FileCredentialsImpl) {
            println("FileCredentialsImpl secret: ${credential.id}")
            println(credential.fileName)
            println(credential.content.text)
        }
    }
}

printAllCredentials()