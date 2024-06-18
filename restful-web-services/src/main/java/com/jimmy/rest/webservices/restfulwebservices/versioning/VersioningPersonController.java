package com.jimmy.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("John Smith");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Henry", "Wells"));
    }

    // uses query params ?version=1
    @GetMapping(value = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParam(){
        return new PersonV1("John Smith");
    }

    @GetMapping(value = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParam(){
        return new PersonV2(new Name("Henry", "Wells"));
    }

    // uses headers
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader(){
        return new PersonV1("John Smith");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader(){
        return new PersonV2(new Name("Henry", "Wells"));
    }

    // uses accept header
    @GetMapping(value = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader(){
        return new PersonV1("John Smith");
    }

    @GetMapping(value = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader(){
        return new PersonV2(new Name("Henry", "Wells"));
    }

/*
* notes factors to consider:
* uri pollution -> there's a tradeoff between storing meaning in the uri and having too many versions of your app.
* at that point you could use the other ways to version an app.
*
* misuse of http header -> it was never meant to be used for this purpose, but it is
* caching -> you cannot cache based on url, you must consider the headers
* api version -> depends on the tool u use to document. some may be able to read the headers and some can only read the url.
* swagger can do both forms of api version documenting.
* */
}
