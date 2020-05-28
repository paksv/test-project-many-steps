package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'TestWorkflow'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("TestWorkflow")) {
    dependencies {
        expect(RelativeId("E2E")) {
            snapshot {
            }
        }
        update(RelativeId("E2E")) {
            snapshot {
                runOnSameAgent = true
            }
        }

        expect(RelativeId("SmokeTests")) {
            snapshot {
            }
        }
        update(RelativeId("SmokeTests")) {
            snapshot {
                runOnSameAgent = true
            }
        }

    }
}
