package org.grails.plugin.foursquare

class Category {
    String id
    String name
    String pluralName
    String shortName
    String iconUrl
    Boolean primary

    Category() {

    }

    Category(def categoryData) {
        id = categoryData.id
        name = categoryData.name
        pluralName = categoryData.pluralName
        shortName = categoryData.shortName
        primary = categoryData.primary
        iconUrl = "${categoryData.icon.prefix}30x30${categoryData.icon.suffix}"
    }
}
