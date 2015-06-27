
withConfig(configuration) {
    inline(phase: 'CONVERSION') { source, context, classNode ->
        classNode.putNodeMetaData('projectVersion', '1.0-SNAPSHOT')
        classNode.putNodeMetaData('projectName', 'Foursquare-Grails-Plugin')
        classNode.putNodeMetaData('isPlugin', 'true')
    }
}
