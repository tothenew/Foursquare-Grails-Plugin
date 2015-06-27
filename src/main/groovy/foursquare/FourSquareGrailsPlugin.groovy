package foursquare

import grails.plugins.*

class FourSquareGrailsPlugin extends Plugin {

    def version = "0.2"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.0.2 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Foursquare Plugin" // Headline display name of the plugin
    def author = "Vishal Sahu"
    def authorEmail = "vishal@intelligrape.com"
    def description = '''The plugin helps us to search location/venue on foursquare and related venues near that place using foursquare API. It helps to get the checkins, people, photos, tips, user comments/reviews about that venue and much more. It can search/retrieve profile info of user, create new checkins, comment etc.'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/IntelliGrape/Foursquare-Grails-Plugin/wiki/Foursquare-Grails-plugin-documentation"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "To The New Digital", url: "http://www.tothenew.com/" ]

//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    Closure doWithSpring() { {->
            // TODO Implement runtime spring config (optional)
        } 
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
