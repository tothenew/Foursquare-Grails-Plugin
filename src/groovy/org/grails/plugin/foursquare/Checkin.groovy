package org.grails.plugin.foursquare

import org.codehaus.groovy.grails.web.json.JSONArray

class Checkin {

    String id
    String type
    Boolean isPrivate = false
    User user
    Long timeZoneOffset
    Venue venue
    Location location
    String shout
    Date createdAt
    String source
    String event
    List<VenuePhoto> photos = []
    Long photosCount = 0
    List<Tip> comments = []
    Long likesCount = 0
    Long commentsCount
    Long score
    Long overlapCount
    List<Category> categories = []

    Checkin() {

    }

    Checkin(def checkinData) {
        this.id = checkinData.id
        this.source = checkinData.source?.name
        this.shout = checkinData.shout
        this.score = checkinData.score?.total
        this.photosCount = checkinData?.photos?.count
        this.timeZoneOffset = checkinData?.timeZoneOffset
        this.createdAt = new Date(checkinData.createdAt?.toLong() * 1000)
        this.likesCount = checkinData.linkes?.count
        this.commentsCount = checkinData.comments?.count
        this.type = checkinData.type
        populateVenue(checkinData)
        populateLocation(checkinData)
        if (checkinData?.venue?.categories) {
            JSONArray jsonArray = new JSONArray(checkinData?.venue?.categories)
            jsonArray.each {def categoryData ->
                Category category = new Category(categoryData)
                this.categories.add(category)
            }
        }
    }

    Checkin(def checkinData, Boolean fromDetailsPage) {
        this.id = checkinData.id
        this.type = checkinData.type
        this.createdAt = new Date(checkinData.createdAt?.toLong() * 1000)
        this.score = checkinData.score?.total
        this.shout = checkinData.shout
        this.timeZoneOffset = checkinData?.timeZoneOffset
        this.source = checkinData.source?.name
        this.photosCount = checkinData?.photos?.count
        this.likesCount = checkinData.linkes?.count
        this.commentsCount = checkinData.comments?.count
        populateVenue(checkinData)
        populateLocation(checkinData)
        User user = new User(checkinData?.user)
        this.user = user
        if (checkinData?.venue?.categories) {
            JSONArray jsonArray = new JSONArray(checkinData?.venue?.categories)
            jsonArray.each {def categoryData ->
                Category category = new Category(categoryData)
                this.categories.add(category)
            }
        }
    }

    private populateVenue(checkinData) {
        Venue venue = new Venue()
        venue.id = checkinData?.venue?.id
        venue.name = checkinData?.venue?.name
        venue.checkinsCount = checkinData?.venue?.checkinsCount
        venue.userCount = checkinData?.venue?.usersCount
        venue.tipsCount = checkinData?.venue?.tipCount
        this.venue = venue
    }

    private populateLocation(checkinData) {
        Location location = new Location()
        location.address = checkinData?.venue?.location?.address
        location.crossStreet = checkinData?.venue?.location?.crossStreet
        location.latitude = checkinData?.venue?.location?.lat
        location.longitude = checkinData?.venue?.location?.lng
        location.postalCode = checkinData?.venue?.location?.postalCode
        location.city = checkinData?.venue?.location?.city
        location.state = checkinData?.venue?.location?.state
        location.country = checkinData?.venue?.location?.country
        this.location = location
    }
}
