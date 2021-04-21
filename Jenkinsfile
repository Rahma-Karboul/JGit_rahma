@Library('piper-lib-os') _node() {
     stage('init') {
           cleanWs()
           checkout scm
           setupCommonPipelineEnvironment script:this

        }

   stage('build and nexusUpload') {
              mavenExecute(
                 script: this,
                 goals: ['deploy']
              )
           }
}
