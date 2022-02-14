import jenkins.*
import hudson.*

node {

try {
        /****** Source code Checkout ******/

        stage('Source Code Checkout') {
            echo "******* Source Code Checkout *******"
            // checkout to a specific branch
            checkout // get checkout command from jenkins

        }

        /****** Gradle Build ******/

        stage('Gradle Build') {

            echo "******* Gradle Build *******"
            steps.sh "chmod +x gradlew"
            steps.sh "./gradlew clean assembleDebug"
            echo "******** Build Successfully Completed ********"

        }

        /****** Running Unit Tests ******/

        stage('JUnit Tests') {

            echo "******* Running Unit Tests *******"
            steps.sh "chmod +x gradlew"
            steps.sh "./gradlew testDebugUnitTest"
            echo "******** Unit Tests Successfully Completed ********"

        }

        /****** Running Instrumentation Tests ******/

        stage('Instrumentation Tests') {

            echo "******* Running Instrumentation Tests *******"
            steps.sh "chmod +x gradlew"
            steps.sh "./gradlew connectedDebugAndroidTest"
            echo "******** Instrumentation Tests Successfully Completed ********"

        }


        /****** Archiving APK ******/

        stage('Archive APK') {

            echo "******* Archiving APK *******"
            steps.sh "cp app/build/output/apk/debug/app-debug.apk /Users/ravish/Work/UITestsWithJenkins/app-debug-${currentBuild.startTimeInMillis}.apk"
            echo "******** APK Archived Completed ********"

        }

     }

        catch (Exception err) {
            echo "Catch Clock"
            echo err
            currentBuild.result = 'FAILURE'
            steps.sh "exit 1"
        }

        finally {

            step([$class: 'WsCleanup'])
            def currentResult = currentBuild.result
            echo currentResult
            if (currentResult == 'FAILURE') {
                echo '''Build Status!
                ================================================
                                Ahh! Build Failed
                ================================================'''
                steps.sh "exit 1"
            } else {
                echo '''Build Status!
                ================================================
                                Awesome! Build Success
                ================================================'''
            }
        }
}