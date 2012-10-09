package org.grails.plugin.foursquare

import org.codehaus.groovy.grails.web.json.JSONArray

class Venue {

    String id
    String name
    String phoneNo
    String phoneNoFormatted
    String twitterId
    Boolean verified
    Date createdAt
    String referralId
    Long checkinsCount
    Long likesSummary
    Long likesCount
    Long photosCount
    Long userCount
    Long tipsCount

    String url
    String shortUrl
    String canonicalUrl
    String description
    String rating

    Location location

    List<Tip> tips = []
    List<Category> categories = []
    List<VenuePhoto> photos = []


    Venue() {

    }

    Venue(def venueData) {
        Location location = new Location()
        this.checkinsCount = venueData?.stats?.checkinsCount
        this.userCount = venueData?.stats?.usersCount
        this.tipsCount = venueData?.stats?.tipCount
        this.rating = "${venueData?.rating ?: 0}/10"
        this.id = venueData?.id
        this.name = venueData?.name
        this.url = venueData?.url
        this.likesCount = venueData?.likes?.count
        this.likesSummary = venueData?.likes?.summary
        this.photosCount = venueData?.photos?.count
        this.shortUrl = venueData?.shortUrl
        this.canonicalUrl = venueData?.canonicalUrl
        this.twitterId = venueData?.contact?.twitter
        this.phoneNo = venueData?.contact?.formattedPhone
        location.address = venueData?.location?.address
        location.state = venueData?.location?.state
        location.country = venueData?.location?.country
        location.city = venueData?.location?.city
        location.latitude = venueData?.location?.lat
        location.longitude = venueData?.location?.lng
        this.location = location
        this.description = venueData?.description
        this.referralId = venueData?.referralId
        this.verified = venueData?.verified
        if (venueData.categories) {
            JSONArray jsonArray = new JSONArray(venueData.categories)
            jsonArray.each {def categoryData ->
                Category category = new Category(categoryData)
                this.categories.add(category)
            }
        }
        if (venueData.photos) {
            JSONArray jsonArray = new JSONArray(venueData.photos)
            jsonArray.each {def photoData ->
                VenuePhoto venuePhoto = new VenuePhoto(photoData)
                this.photos.add(venuePhoto)
            }
        }
    }

    String getFoursquareVenueCompleteAddress() {
        return "${this.location.address ?: ''}${this.location.city ? ((this.location.address ? ', ' : '') + this.location.city) : ''}${this.location.state ? ', ' + this.location.state : ''}${this.location.country ? ', ' + this.location.country : ''}"
    }
}
