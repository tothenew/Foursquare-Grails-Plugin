package org.grails.plugin.foursquare

class User {

    String id
    String firstName
    String lastName
    String homeCity
    String photoUrl
    String gender
    String facebookId
    String bio
    Long tipsCount = 0

    User() {

    }

    User(def userData) {
        this.id = userData.id
        this.lastName = userData.lastName
        this.bio = userData.bio
        this.homeCity = userData?.homeCity
        this.gender = userData?.gender
        this.tipsCount = userData?.tips?.count
        this.firstName = userData?.firstName
        this.facebookId = userData?.contact?.facebook
        this.photoUrl = userData.photo?.prefix + userData.photo?.suffix
    }
}
