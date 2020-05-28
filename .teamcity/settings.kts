import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.1"

project {

    buildType(Long_1)
    buildType(Run)
}

object Long_1 : BuildType({
    id("Long")
    name = "Long"

    steps {
        script {
            scriptContent = "sleep 2000"
        }
    }

    requirements {
        equals("docker.server.version", "18.06.3")
    }
})

object Run : BuildType({
    name = "Run"

    triggers {
        schedule {
            schedulingPolicy = cron {
                minutes = "*/30"
            }
            branchFilter = ""
            triggerBuild = always()
            withPendingChangesOnly = false
        }
    }

    requirements {
        exists("docker.server.version")
        startsWith("teamcity.agent.name", "Linux-small")
    }
})
