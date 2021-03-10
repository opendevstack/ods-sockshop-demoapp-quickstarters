var casper = require('casper').create({
    exitOnError: false,
    verbose: true,
    logLevel: 'info'
})
var system = require('system')

// Workaround to avoid use of Buffer class not existing
var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9\+\/\=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/\r\n/g,"\n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}

var appURL = "http://" + system.env.APP_NAME + "-" + system.env.OPENSHIFT_BUILD_NAMESPACE + "." + system.env.OPENSHIFT_HOST
console.log("Setting application URL to: " + appURL)

var randCatalogueItemId

casper.start(appURL + "/catalogue", function() {
    this.echo("Loading /catalogue")
    try {
        $json = JSON.parse(this.getPageContent().replace(/<(.|\n)*?>/g, ''))
        randCatalogueItem = $json[Math.floor(Math.random() * $json.length)]
        randCatalogueItemId = randCatalogueItem.id
        this.echo("Item selected: " + randCatalogueItemId)
    } catch (err) {
        this.echo("Error obtaining catalog: " + err.message)
        randCatalogueItemId = "zzz4f044-b040-410d-8ead-4de0446aec7e"
        this.echo("Item selected (default value): " + randCatalogueItemId)
    }
})

casper.thenOpen(appURL, function() {
    this.echo("Loading /")
})

casper.thenOpen(appURL + "/login", {
    method: "get",
    headers: {
        Authorization: "Basic " + Base64.encode(system.env.USER_TEST_LOGIN + ":" + system.env.USER_TEST_PASSWORD)
    }
}, function() {
    this.echo("Loading /login")
})

casper.thenOpen(appURL + "/card", function() {
    this.echo("Loading /card")
})

casper.thenOpen(appURL + "/address", function() {
    this.echo("Loading /address")
})

casper.thenOpen(appURL + "/category.html", function() {
    this.echo("Loading /category.html")
})

// Wrapping with then to get value in: randCatalogueItemId
casper.then(function () {
    casper.thenOpen(appURL + "/detail.html?id=" + randCatalogueItemId, function() {
        this.echo("Loading /detail.html?id=" + randCatalogueItemId)
    })
})

casper.thenOpen(appURL + "/cart", {
    method: "delete"
}, function() {
    this.echo("Delete /cart")
})

// Wrapping with then to get value in: randCatalogueItemId
casper.then(function () {
    casper.thenOpen(appURL + "/cart", {
        method: "post",
        headers: {
            'Content-Type': 'application/json'
        },
        data: {
            id: randCatalogueItemId,
            quantity: '1'
        }
    }, function() {
        this.echo("Adding /cart, item: " + randCatalogueItemId)
    })
})

// Wrapping with then to get value in: randCatalogueItemId
casper.then(function () {
    casper.thenOpen(appURL + "/cart/update", {
        method: "post",
        headers: {
            'Content-Type': 'application/json'
        },
        data: {
            id: randCatalogueItemId,
            quantity: '2'
        }
    }, function() {
        this.echo("Updating /cart, 2 items: " + randCatalogueItemId)
    })
})

casper.thenOpen(appURL + "/basket.html", function() {
    this.echo("Loading /basket.html")
})

casper.thenOpen(appURL + "/orders", {
    method: "post"
}, function() {
    this.echo("Loading /orders")
})

// Common endpoints
casper.thenOpen(appURL + "/customers", function() {
    this.echo("Loading /customers")
})

casper.thenOpen(appURL + "/cards", function() {
    this.echo("Loading /cards")
})

casper.thenOpen(appURL + "/addresses", function() {
    this.echo("Loading /addresses")
})

casper.thenOpen(appURL + "/tags", function() {
    this.echo("Loading /tags")
})

casper.run()
