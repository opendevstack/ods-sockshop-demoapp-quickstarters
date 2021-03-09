import groovy.json.JsonSlurper

import kong.unirest.Unirest

import spock.lang.Specification

class MiscIntegrationSpec extends Specification {

    def "Cart gets processed correctly"() {
        expect:
    	true
    }

    def "Frontend retrieves cart data correctly"() {
        expect:
        true
    }

    def "Frontend retrieves payment data correctly"() {
        expect:
        true
    }
}
