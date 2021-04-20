@Library('piper-lib-os') _
node() {
     stage('init') {
           cleanWs()
           checkout scm
           setupCommonPipelineEnvironment script:this

        }

}
   stage('build and nexusUpload') {
              mavenExecute(
                 script: this,
                 goals: ['deploy']
              )
           }
