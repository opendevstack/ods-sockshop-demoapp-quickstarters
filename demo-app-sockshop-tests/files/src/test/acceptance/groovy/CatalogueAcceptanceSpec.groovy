import geb.Page
import geb.spock.GebReportingSpec
import spock.lang.Stepwise
import geb.Browser

class CataloguePage extends Page {
    static url = "/category.html"
    static at = { title == "WeaveSocks" }
    static content = {
	    products(wait: true, cache: false) { $("#products") }
	    productsNumber(wait: true, cache: false) { $("#products").children().size() }
        addFirstProductLink(to: HomePage, wait: true, cache: false) { $("#products > div:nth-child(1) > div > div.text > p.buttons > a.btn.btn-primary") }
    }
}


/**
 * Based on: https://github.com/microservices-demo/front-end/blob/master/test/e2e/catalogue_test.js
 */
@Stepwise
class CatalogueAcceptanceSpec extends GebReportingSpec {

    def "User shows catalogue"() {
        expect:
        given: "The user goes to catalogue page"
        to CataloguePage

        and: "User is presented with 6 products by default"
        assert productsNumber == 6

        SpecHelper.printEvidenceForPageElement(this, 2, CataloguePage, $("#products"), "products available")
    }
}
